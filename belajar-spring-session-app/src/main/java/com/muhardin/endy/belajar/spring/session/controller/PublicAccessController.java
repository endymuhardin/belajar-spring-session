package com.muhardin.endy.belajar.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublicAccessController {
    @GetMapping("/")
    public String hostInfo(Model model, HttpServletRequest request) {
        model
                .addAttribute("localAddress", request.getLocalAddr())
                .addAttribute("localPort", request.getLocalPort());

        return "/hostinfo";
    }
}
