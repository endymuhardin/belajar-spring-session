package com.muhardin.endy.belajar.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes("userdata")
@Controller @RequestMapping("/sessiondata")
public class UserDataController {

    @ModelAttribute("userdata")
    public List<String> userData() {
        return new ArrayList<>();
    }

    @GetMapping("/list")
    public void displayData() {}

    @PostMapping("/save")
    public String saveData(@RequestParam String data, @ModelAttribute("userdata") List<String> userdata) {
        userdata.add(data);
        return "redirect:list";
    }

    @PostMapping("/clear")
    public String clearData(@ModelAttribute("userdata") List<String> userdata) {
        userdata.clear();
        return "redirect:list";
    }

}
