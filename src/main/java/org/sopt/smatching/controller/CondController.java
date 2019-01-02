package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.dto.CondDetail;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.service.CondService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/conds")
public class CondController {

    @Autowired
    private CondService condService;

    // condIdx로 맞춤조건 조회
    @GetMapping("")
    public ResponseEntity getCondInfoByCondIdx(@RequestParam(value = "cond_idx") int condIdx) {
        return new ResponseEntity<>(condService.getCondInfoByCondIdx(condIdx), HttpStatus.OK);
    }

    // 맞춤조건에 맞는 지원사업 개수 조회
    @PutMapping("/count")
    public ResponseEntity getNoticeCountByCondDetail(@RequestBody final CondDetail condDetail,
                                                     @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = condService.getNoticeCountByCondDetail(condDetail);
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


        return new ResponseEntity<>(condService.getNoticeCountByCondDetail(condDetail), HttpStatus.OK);
    }

    // 맞춤조건 추가
    @Auth
    @PostMapping("")
    public ResponseEntity createCond(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                     @RequestBody final CondDetail condDetail,
                                     @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = condService.createCond(idx_variable, condDetail);
            defaultRes.setData(
                    new HashMap<String, Integer>() {{
                        put("condIdx", (Integer)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(condService.createCond(idx_variable, condDetail), HttpStatus.OK);
    }

    // 맞춤조건 변경
    @Auth
    @PutMapping("/{condIdx}")
    public ResponseEntity updateCond(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                     @PathVariable(value = "condIdx") final int condIdx,
                                     @RequestBody final CondDetail condDetail,
                                     @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = condService.updateCond(idx_variable, condIdx, condDetail);
            defaultRes.setData(
                    new HashMap<String, Integer>() {{
                        put("condIdx", (Integer)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(condService.updateCond(idx_variable, condIdx, condDetail), HttpStatus.OK);
    }

    // 맞춤조건 삭제
    @Auth
    @DeleteMapping("/{condIdx}")
    public ResponseEntity deleteCond(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                     @PathVariable(value = "condIdx") final int condIdx) {
        return new ResponseEntity<>(condService.deleteCond(idx_variable, condIdx), HttpStatus.OK);
    }

    // 맞춤조건 알람 ON / OFF
    @Auth
    @PutMapping("/alert/{condIdx}")
    public ResponseEntity toggleAlert(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                      @PathVariable(value = "condIdx") final int condIdx,
                                      @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {
        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = condService.toggleAlert(idx_variable, condIdx);
            defaultRes.setData(
                    new HashMap<String, Boolean>() {{
                        put("result", (Boolean)defaultRes.getData());
                    }}
            );
            return new ResponseEntity<>(defaultRes, HttpStatus.OK);
        }
        /**
         * iOS 요청에 따른 임시 방편
         */


        return new ResponseEntity<>(condService.toggleAlert(idx_variable, condIdx), HttpStatus.OK);
    }
}
