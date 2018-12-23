package org.sopt.smatching.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.sopt.smatching.model.DefaultRes.FAIL_DEFAULT_RES_IN_CONTROLLER;

@Slf4j
@ControllerAdvice // 모든 컨트롤러에 대응됨
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = Exception.class) // 모든 예외를 받음
    public ResponseEntity returnFailDefaultRes(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(FAIL_DEFAULT_RES_IN_CONTROLLER, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
