package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.NoticeService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 전체 지원사업 목록 조회 - 최신등록순으로 요청된 갯수만큼 리턴
    @GetMapping("/list")
    public ResponseEntity getAllNoticeList(@RequestParam(value = "request_num") int reqNum,
                                           @RequestParam(value = "exist_num") int existNum) {
        return new ResponseEntity<>(noticeService.getNoticeSummaryList(reqNum, existNum), HttpStatus.OK);
    }

    // 지원사업 상세조회
    @GetMapping("/detail/{noticeIdx}")
    public ResponseEntity getNoticeDetail(@PathVariable(value = "noticeIdx") int noticeIdx) {
        return null;
    }

}
