package com.example.FirstSpingProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") //
    public String  niceToMeetYou(Model model){
        model.addAttribute("username", "Mr.park ");
        return "greetings"; //templates/greetings.mustache 브라우저로 전송(뷰페이지 찾는다)
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","gildong ");
        return "goodbye";
    }
}
