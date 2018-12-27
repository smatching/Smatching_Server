package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.CondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/conds")
public class CondController {

    @Autowired
    private CondService condService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 토큰을 받아서 해당 유저가 설정한 맞춤조건 정보들을 리턴
    @GetMapping("")
    public ResponseEntity getMainTabInfo() {
        return new ResponseEntity<>(condService.getCondList(httpServletRequest.getHeader("Authorization")), HttpStatus.OK);
    }
}
