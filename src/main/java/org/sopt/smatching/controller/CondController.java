package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.CondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // condIdx로 맞춤조건 조회
    @GetMapping("")
    public ResponseEntity getCondInfoByCondIdx(@RequestParam(value = "cond_idx") int condIdx) {
        return new ResponseEntity<>(condService.getCondInfoByCondIdx(condIdx), HttpStatus.OK);
    }


}
