package org.sopt.smatching.service;


import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.dto.UserCond;
import org.sopt.smatching.dto.UserInfo;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.LoginReq;
import org.sopt.smatching.model.SignUpReq;
import org.sopt.smatching.model.UserModifyReq;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

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
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, signUpReq.getNickname());

        } catch (DuplicateKeyException e) { // 이메일 중복
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            if(e.getMessage().contains("for key 'UC_email'") == true) {
                log.error(e.getMessage());
                return DefaultRes.res(StatusCode.ALREADY_EXSIST_EMAIL, ResponseMessage.ALREADY_EXIST_EMAIL);
            }
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);

        } catch (Exception e) { // DB 에러
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error(e.getMessage());
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
            return DefaultRes.res(StatusCode.FORBIDDEN, ResponseMessage.WRONG_PASSWORD, null);

        // 새 비밀번호 존재하면 해당 값으로 비밀번호 덮어쓰기 (없어도 기존 값 그대로 update는 됨)
        if(userModifyReq.getNewPassword() != null)
            userModifyReq.setPassword(userModifyReq.getNewPassword());

        try {
            final int updatedCnt = userMapper.modifyUserByUserIdx(userIdx, userModifyReq);
            if(updatedCnt != 1)
                return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_UPDATE_IS_NOT_ONE);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATED_USER, true);

        } catch (Exception e) { // DB 에러
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

}
