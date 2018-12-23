package org.sopt.smatching.service;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public DefaultRes getTest() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, "GET 메소드 테스트 성공!! 서버 개발자에게 말해주세요");
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
