package org.sopt.smatching.test;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public DefaultRes getTestMain() {
        return DefaultRes.res(StatusCode.SERVICE_UNAVAILABLE, ResponseMessage.ALREADY_EXIST_EMAIL, "GET 메소드 메인임!!!!!");
    }

    public DefaultRes postTestMain() {
        return DefaultRes.res(StatusCode.SERVICE_UNAVAILABLE, ResponseMessage.ALREADY_EXIST_EMAIL, "POST 메소드 메인임!!!!");
    }

    public DefaultRes getTest() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, "GET 메소드 테스트 성공!! 서버 개발자에게 말해주세요");
    }

    public DefaultRes postTest() {
        return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, "POST 메소드 테스트 성공!! 서버 개발자에게 말해주세요");
    }

    public DefaultRes putTest() {
        return DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.UPDATE_USER, "PUT 메소드 테스트 성공!! 서버 개발자에게 말해주세요");
    }

    public DefaultRes deleteTest() {
        return DefaultRes.res(StatusCode.FORBIDDEN, ResponseMessage.DELETE_USER, "DELETE 메소드 테스트 성공!! 서버 개발자에게 말해주세요");
    }
}
