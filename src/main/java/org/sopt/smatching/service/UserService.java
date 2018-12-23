package org.sopt.smatching.service;


import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.dto.User;
import org.sopt.smatching.mapper.UserMapper;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.LoginReq;
import org.sopt.smatching.model.SignUpReq;
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

    /**
     * 로그인 서비스
     * @param loginReq 객체
     * @return DefaultRes (로그인 성공시 토큰값 / 실패시 null)
     */
    public DefaultRes<JwtService.TokenRes> login(final LoginReq loginReq) {
        final User user = userMapper.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (user != null) {
            //토큰 생성
            final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(user.getUserIdx()));
            return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, tokenDto);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }


    /**
     * 회원가입 서비스
     * @param signUpReq 객체
     * @return DefaultRes (
     */

    @Transactional
    public DefaultRes signUp(SignUpReq signUpReq) {
        try {
            userMapper.save(signUpReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, signUpReq.getNickname());

        } catch (DuplicateKeyException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            if(e.getMessage().contains("for key 'email_UNIQUE'") == true) {
                log.error(e.getMessage());
                return DefaultRes.res(StatusCode.ALREADY_EXSIST_EMAIL, ResponseMessage.ALREADY_EXIST_EMAIL);
            }
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //Rollback
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
