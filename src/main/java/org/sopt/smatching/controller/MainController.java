package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MainService mainService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 메인 탭 페이지 정보 요청
    @GetMapping("")
    public ResponseEntity getMainTabInfo() {
        return new ResponseEntity<>(mainService.getMainTabInfo(httpServletRequest.getHeader("Authorization")), HttpStatus.OK);
    }
}
