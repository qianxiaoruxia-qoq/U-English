package com.ue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("top")
public class TopController {

    @RequestMapping("login")
    public String login() {
        return "login";
    }
}
