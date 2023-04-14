package com.codeum.shoppingmall.user.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "sign-in";
    }
}
