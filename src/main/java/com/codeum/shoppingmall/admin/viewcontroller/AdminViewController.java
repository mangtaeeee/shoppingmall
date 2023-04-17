package com.codeum.shoppingmall.admin.viewcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {


    @GetMapping("/admin")
    public String loginPage() {
        return "admin-login";
    }

    @GetMapping("/admin/main")
    public String mainPage() {
        return "admin-main";
    }

    @GetMapping("/admin/store")
    public String storePage(){
        return "admin-store";
    }


    @GetMapping("/admin/uploadproduct")
    public String uploadProductPage() {
        return "uploadproduct";
    }

}
