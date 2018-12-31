package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.LoginReq;
import org.sopt.smatching.model.SignUpReq;
import org.sopt.smatching.service.CondService;
import org.sopt.smatching.service.UserService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CondService condService;

    // 회원 가입
    @PostMapping("")
    public ResponseEntity signUp(@RequestBody SignUpReq signUpReq) {
        return new ResponseEntity<>(userService.signUp(signUpReq), HttpStatus.OK);
    }


    // 로그인 수행, data 키의 값 : 로그인 성공시 토큰값 / 실패시 null
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody final LoginReq loginReq) {
        return new ResponseEntity<>(userService.login(loginReq), HttpStatus.OK);
    }


    // 유저의 맞춤조건 현황 조회
    @Auth
    @GetMapping("/cond")
    public ResponseEntity getCondInfoByToken(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(condService.getCondInfoByToken(idx_variable), HttpStatus.OK);
    }
}
