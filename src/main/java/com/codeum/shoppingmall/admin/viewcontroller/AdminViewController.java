package com.codeum.shoppingmall.admin.viewcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminViewController {

    @GetMapping("/uploadproduct")
    public String uploadProductPage() {
        return "uploadproduct";
    }

}
