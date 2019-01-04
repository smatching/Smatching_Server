package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.SearchService;
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


}
