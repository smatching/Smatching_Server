package org.sopt.smatching.service;


import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.mapper.NotificationMapper;
import org.sopt.smatching.model.notification.NotificationOutput;
import org.sopt.smatching.model.user.User;
import org.sopt.smatching.model.user.UserInfo;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.user.LoginReq;
import org.sopt.smatching.model.user.SignUpReq;
import org.sopt.smatching.model.user.UserModifyReq;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private JwtService jwtService;
    private S3FileUploadService s3FileUploadService;
    private UserMapper userMapper;
    private NotificationMapper notificationMapper;


    public UserService(JwtService jwtService, S3FileUploadService s3FileUploadService, UserMapper userMapper, NotificationMapper notificationMapper) {
        this.jwtService = jwtService;
        this.s3FileUploadService = s3FileUploadService;
        this.userMapper = userMapper;
        this.notificationMapper = notificationMapper;
    }

    // 로그인 기능
    public DefaultRes<JwtService.TokenRes> login(final LoginReq loginReq) {
        final User user = userMapper.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (user != null) {
            //토큰 생성
            final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(user.getUserIdx()));
            return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, tokenDto);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }


    // 회원가입 기능
    @Transactional
    public DefaultRes signUp(final SignUpReq signUpReq) {
        try { // 정상 추가
            userMapper.save(signUpReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER);

        } catch (DuplicateKeyException e) { // 이메일 중복
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            if(e.getMessage().contains("for key 'UC_email'") == true) {
                log.error("\n- Exception Detail (below)", e);
                return DefaultRes.res(StatusCode.ALREADY_EXSIT_EMAIL, ResponseMessage.ALREADY_EXIST_EMAIL);
            }
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);

        } catch (Exception e) { // DB 에러
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }


    // 마이페이지 메인에 필요한 정보 조회 기능
    public DefaultRes getMyPageMainInfo(final int userIdx) {
        UserInfo userInfo = userMapper.findUserInfoByUserIdx(userIdx);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER_INFO, userInfo);
    }


    // 회원정보 조회 기능 - 회원정보변경 창 띄울때
    public DefaultRes getUserInfo(final int userIdx) {
        final UserModifyReq userModifyReq = userMapper.findUserModifyByUserIdx(userIdx); // password랑 newPassword는 null
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userModifyReq);
    }

    // 회원정보변경 기능
    @Transactional
    public DefaultRes modifyUserInfo(final int userIdx, UserModifyReq userModifyReq) {
        // 기존 비밀번호 확인
        if(userMapper.checkPassword(userIdx, userModifyReq.getPassword()) == 0)
            return DefaultRes.res(StatusCode.FORBIDDEN, ResponseMessage.WRONG_PASSWORD);

        // 새 비밀번호가 empty string 이면 기존 비밀번호를 복사하기
        if(userModifyReq.getNewPassword().equals(""))
            userModifyReq.setNewPassword(userModifyReq.getPassword());

        // 닉네임이 empty string 이면 기존 닉네임 그대로 사용
        if(userModifyReq.getNickname().equals(""))
            userModifyReq.setNickname(userMapper.findUserModifyByUserIdx(userIdx).getNickname());


        try {
            final int updatedCnt = userMapper.modifyUserByUserIdx(userIdx, userModifyReq); // 무조건 새 비밀번호로 저장
            if(updatedCnt != 1)
                return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_UPDATE_IS_NOT_ONE);

            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.UPDATED_USER);

        } catch (Exception e) { // DB 에러
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // 프로필사진 변경 기능
    @Transactional
    public DefaultRes modifyProfilePicture(final int userIdx, final MultipartFile picture) {
        try {
            String profileUrl = s3FileUploadService.upload(picture);
            userMapper.updateProfileUrlByUserIdx(userIdx, profileUrl);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.UPDATED_USER_PIC);

        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    // 프로필사진 삭제 기능
    @Transactional
    public DefaultRes deleteProfilePicture(final int userIdx) {
        try {
            userMapper.deleteProfileUrlByUserIdx(userIdx);
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETED_USER_PIC);

        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }


    // 회원탈퇴 기능
    @Transactional
    public DefaultRes deleteUser(final int userIdx) {
        try {
            final int updatedCnt = userMapper.discardByUserIdx(userIdx, new Date().toString()); // 실제 삭제는 하지 않고 이메일 앞에 타임스탬프를 붙임
            if(updatedCnt != 1)
                return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_UPDATE_IS_NOT_ONE);

            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.DELETED_USER);

        } catch (Exception e) { // DB 에러
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error("\n- Exception Detail (below)", e);
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    // 유저의 모든 알람내역 조회
    public DefaultRes getNotificationList(final int userIdx) {
        List<NotificationOutput> list = notificationMapper.findAllByUserIdx(userIdx);

        if(list.isEmpty())
            return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_NOTIFICATION);

        // 경과 시간 계산해서 String 으로 다시 저장
        for(NotificationOutput notificationOutput : list) {
            notificationOutput.writeOutputTime();
        }

        // 읽지 않은 알람이 있으면 모두 읽은 상태로 바꿈 (리턴이 0이 올수도 있어서 updatedRow 확인 로직 넣지않음)
        notificationMapper.changeToRead(userIdx);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTIFICATION, list);
    }


    // 읽지않은 사용자 알람 개수 조회
    public DefaultRes getUncheckedNotificationCount(final int userIdx) {
        int count = notificationMapper.countUnchecked(userIdx);

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_NOTIFICATION, new HashMap<String, Integer>() {{
            put("num", count);
        }});
    }
}
