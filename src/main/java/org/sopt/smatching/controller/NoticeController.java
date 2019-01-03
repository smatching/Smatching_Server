package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.model.notice.NoticeInput;
import org.sopt.smatching.service.NoticeService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    // 지원사업 개수 조회
    @GetMapping("/count")
    public ResponseEntity getNoticeCnt(@RequestParam(value = "cond_idx") final Optional<Integer> condIdx,
                                       @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes;
            // 맞춤 지원사업 개수 조회 - condIdx 값o
            if(condIdx.isPresent()) {
                defaultRes = noticeService.getNoticeCnt(condIdx.get().intValue());
                defaultRes.setData(
                        new HashMap<String, Integer>() {{
                            put("num", (Integer)defaultRes.getData());
                        }}
                );
                return new ResponseEntity<>(defaultRes, HttpStatus.OK);
            }

            // 전체 지원사업 개수 조회 - condIdx 값x
            defaultRes = noticeService.getNoticeCnt();
            defaultRes.setData(
                    new HashMap<String, Integer>() {{
                        put("num", (Integer)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        // 맞춤 지원사업 개수 조회 - condIdx 값o
        if(condIdx.isPresent())
            return new ResponseEntity<>(noticeService.getNoticeCnt(condIdx.get().intValue()), HttpStatus.OK);

        // 전체 지원사업 개수 조회 - condIdx 값x
        return new ResponseEntity<>(noticeService.getNoticeCnt(), HttpStatus.OK);
    }


    // 지원사업 목록 조회 - 최신등록순으로 요청된 갯수만큼 리턴
    // 메소드 특성상 @Auth 사용 불가
    @GetMapping("/list")
    public ResponseEntity getFitNoticeList(@RequestHeader(value = "Authorization", required = false) String jwt,
                                           @RequestParam(value = "request_num") final int reqNum,
                                           @RequestParam(value = "exist_num") final int existNum,
                                           @RequestParam(value = "cond_idx") final Optional<Integer> condIdx) {

        // 맞춤 지원사업 목록 조회 - condIdx 값o
        if(condIdx.isPresent())
            return new ResponseEntity<>(noticeService.getNoticeSummaryList(jwt, reqNum, existNum, condIdx.get().intValue()), HttpStatus.OK);

        // 전체 지원사업 목록 조회 - condIdx 값x
        return new ResponseEntity<>(noticeService.getNoticeSummaryList(jwt, reqNum, existNum), HttpStatus.OK);
    }


    // 지원사업 스크랩 조회
    @Auth
    @GetMapping("/scrap")
    public ResponseEntity getScrap(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                   @RequestParam(value = "notice_idx") final int noticeIdx,
                                   @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = noticeService.getScrap(idx_variable, noticeIdx);
            defaultRes.setData(
                    new HashMap<String, Integer>() {{
                        put("scrap", (Integer)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(noticeService.getScrap(idx_variable, noticeIdx), HttpStatus.OK);
    }

    // 지원사업 스크랩 설정 or 해제
    @Auth
    @PutMapping("/scrap/{noticeIdx}")
    public ResponseEntity changeScrap(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                      @PathVariable(value = "noticeIdx") final int noticeIdx,
                                      @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = noticeService.changeScrap(idx_variable, noticeIdx);
            defaultRes.setData(
                    new HashMap<String, Integer>() {{
                        put("scrap", (Integer)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(noticeService.changeScrap(idx_variable, noticeIdx), HttpStatus.OK);
    }


    // 지원사업 상세조회
    @GetMapping("/detail")
    public ResponseEntity getNoticeDetail(@RequestParam(value = "notice_idx") final int noticeIdx) {
        return new ResponseEntity<>(noticeService.getDetail(noticeIdx), HttpStatus.OK);
    }


    // 지원사업 추가
    @PostMapping("")
    public ResponseEntity addNotice(@RequestBody final NoticeInput noticeInput) {
        return new ResponseEntity<>(noticeService.addNotice(noticeInput), HttpStatus.OK);
    }

    // 지원사업 비활성화
    @PutMapping("/invalidate/{noticeIdx}")
    public ResponseEntity invalidateNotice(@PathVariable(value = "noticeIdx") final int noticeIdx) {
        return new ResponseEntity<>(noticeService.invalidateNotice(noticeIdx), HttpStatus.OK);
    }

}
