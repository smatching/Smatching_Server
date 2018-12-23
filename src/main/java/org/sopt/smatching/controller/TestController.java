package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public ResponseEntity testGetMethod() {
        return new ResponseEntity<>(testService.getTest(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity testPostMethod() {
        return new ResponseEntity<>(testService.postTest(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity testPutMethod() {
        return new ResponseEntity<>(testService.putTest(), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity testDeleteMethod() {
        return new ResponseEntity<>(testService.deleteTest(), HttpStatus.OK);
    }




}
