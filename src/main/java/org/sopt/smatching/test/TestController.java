package org.sopt.smatching.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public ResponseEntity testMainGet() { return new ResponseEntity<>(testService.getTestMain(), HttpStatus.OK); }

    @PostMapping("")
    public ResponseEntity testMainPost() {
        return new ResponseEntity<>(testService.postTestMain(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity testGetMethod() {
        return new ResponseEntity<>(testService.getTest(), HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity testPostMethod() {
        return new ResponseEntity<>(testService.postTest(), HttpStatus.OK);
    }

    @PutMapping("/test")
    public ResponseEntity testPutMethod() {
        return new ResponseEntity<>(testService.putTest(), HttpStatus.OK);
    }

    @DeleteMapping("/test")
    public ResponseEntity testDeleteMethod() {
        return new ResponseEntity<>(testService.deleteTest(), HttpStatus.OK);
    }




}
