package org.sopt.smatching.service;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.utils.ResponseMessage;
import org.sopt.smatching.utils.StatusCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public DefaultRes test() {
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, "Great!! Please tell Kitae if you see this message :D");
    }
}
