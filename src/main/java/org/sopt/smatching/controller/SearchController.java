package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.SearchService;
import org.sopt.smatching.utils.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @GetMapping("")
    public ResponseEntity fromEverywhere(@RequestHeader(value = "Authorization", required = false) String jwt,
                                         @RequestParam(value = "query") final String query) {
        return new ResponseEntity<>(searchService.fromEverywhere(jwt, query), HttpStatus.OK);
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
