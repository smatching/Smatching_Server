package org.sopt.smatching.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @GetMapping("")
    public String viewMain(Model model) {

        return "index"; // ftl 파일이름
    }

    @GetMapping("/add")
    public String viewAddPopup(Model model) {
        model.addAttribute("titles", "요고나와라");
        return "add"; // ftl 파일이름
    }

}
