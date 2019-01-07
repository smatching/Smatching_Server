package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.smatching.model.notice.NoticeInput;
import org.sopt.smatching.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${ADMIN.PASSWORD}")
    private String ADMIN_PASSWORD;

    @Autowired
    private NoticeService noticeService;


    // admin 페이지 메인
    @GetMapping
    public String viewMain(Model model) {
        return "index";
    }


    // 서버 로그확인 페이지
    @GetMapping("/log")
    @ResponseBody
    public ResponseEntity viewServerLog() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/home/ubuntu/nohup.out"));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }

        br.close();

        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }


    // 지원사업 공고 리스트 페이지
    @GetMapping("/notices")
    public ModelAndView viewNoticeList(Model model, @RequestParam(required = false) final String password) {
        if(password == null || !(password.equals(ADMIN_PASSWORD)))
            return null;

        HashMap<String, Object> params = new HashMap<>();
        params.put("titles", "요고나와라");

        return new ModelAndView("noticelist", params);
    }


    // 지원사업 공고 추가 팝업창
    @GetMapping("/notices/add")
    public String viewAddPopup(Model model,
                               @RequestParam(required = false) final String password) {
        if(password == null || !(password.equals(ADMIN_PASSWORD)))
            return null;

        return "addnotice"; // ftl 파일이름
    }


    // 지원사업 추가
    @PostMapping("/notices/add")
    @ResponseBody
    public ResponseEntity addNotice(@RequestBody final NoticeInput noticeInput,
                                    @RequestParam(required = false) final String password) {
        if(password == null || !(password.equals(ADMIN_PASSWORD)))
            return null;

        return new ResponseEntity<>(noticeService.addNotice(noticeInput), HttpStatus.OK);
    }


    // 지원사업 비활성화
    @PutMapping("/notices/invalidate/{noticeIdx}")
    @ResponseBody
    public ResponseEntity invalidateNotice(@PathVariable(value = "noticeIdx") final int noticeIdx,
                                           @RequestParam(required = false) final String password) {
        if(password == null || !(password.equals(ADMIN_PASSWORD)))
            return null;

        return new ResponseEntity<>(noticeService.invalidateNotice(noticeIdx), HttpStatus.OK);
    }

}
