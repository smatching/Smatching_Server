package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.DefaultRes;
import org.sopt.smatching.service.SearchService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;



    @GetMapping("/notices")
    public ResponseEntity fromEverywhere(@RequestHeader(value = "Authorization", required = false) String jwt,
                                         @RequestParam(value = "query") final String query,
                                         @RequestParam(value = "request_num") final int reqNum,
                                         @RequestParam(value = "exist_num") final int existNum) {
        return new ResponseEntity<>(searchService.fromEverywhere(jwt, query, reqNum, existNum), HttpStatus.OK);
    }


    @GetMapping("/notices/count")
    public ResponseEntity countFromEverywhere(@RequestParam(value = "query") final String query,
                                              @RequestHeader(value = "Client", required = false, defaultValue = "Android") final String client) {

        /**
         * iOS 요청에 따른 임시 방편
         */
        if(client.equals("iOS")) {
            DefaultRes defaultRes = searchService.countFromEverywhere(query);
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

        return new ResponseEntity<>(searchService.countFromEverywhere(query), HttpStatus.OK);
    }


    @Auth
    @GetMapping("/log")
    public ResponseEntity getUserQueryLogs(@RequestHeader(required = false, defaultValue = "0") int idx_variable) {
        return new ResponseEntity<>(searchService.getUserQueryLogs(idx_variable), HttpStatus.OK);
    }


    @Auth
    @DeleteMapping("/log/{order}")
    public ResponseEntity deleteUserQueryLog(@RequestHeader(required = false, defaultValue = "0") int idx_variable,
                                             @PathVariable(value = "order") final int order) {
        return new ResponseEntity<>(searchService.deleteUserQueryLog(idx_variable, order), HttpStatus.OK);
    }

}
