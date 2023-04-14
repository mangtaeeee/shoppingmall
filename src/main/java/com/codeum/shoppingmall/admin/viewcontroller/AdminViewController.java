package com.codeum.shoppingmall.admin.viewcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {


    @GetMapping("/admin/store")
    public String mainPage(){
        return "admin-main";
    }
}
