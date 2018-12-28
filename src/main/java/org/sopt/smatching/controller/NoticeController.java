package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    // 지원사업 개수 조회
    @GetMapping("/count")
    public ResponseEntity getNoticeCnt(@RequestParam(value = "cond_idx") final Optional<Integer> condIdx) {
        // 맞춤 지원사업 개수 조회 - condIdx 값o
        if(condIdx.isPresent())
            return new ResponseEntity<>(noticeService.getNoticeCnt(condIdx.get().intValue()), HttpStatus.OK);

        // 전체 지원사업 개수 조회 - condIdx 값x
        return new ResponseEntity<>(noticeService.getNoticeCnt(), HttpStatus.OK);
    }

    // 지원사업 목록 조회 - 최신등록순으로 요청된 갯수만큼 리턴
    @GetMapping("/list")
    public ResponseEntity getFitNoticeList(@RequestParam(value = "request_num") final int reqNum,
                                           @RequestParam(value = "exist_num") final int existNum,
                                           @RequestParam(value = "cond_idx") final Optional<Integer> condIdx) {

        String jwt = httpServletRequest.getHeader("Authorization");

        // 맞춤 지원사업 목록 조회 - condIdx 값o
        if(condIdx.isPresent())
            return new ResponseEntity<>(noticeService.getNoticeSummaryList(jwt, reqNum, existNum, condIdx.get().intValue()), HttpStatus.OK);

        // 전체 지원사업 목록 조회 - condIdx 값x
        return new ResponseEntity<>(noticeService.getNoticeSummaryList(jwt, reqNum, existNum), HttpStatus.OK);
    }


    // 지원사업 상세조회
    @GetMapping("/detail")
    public ResponseEntity getNoticeDetail(@RequestParam(value = "noticeIdx") final int noticeIdx) {
        return null;
    }

}
