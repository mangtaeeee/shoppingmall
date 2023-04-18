package com.codeum.shoppingmall.admin.viewcontroller;


import com.codeum.shoppingmall.admin.store.service.AdminStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    private final AdminStoreService adminStoreService;


    @GetMapping("/admin")
    public String loginPage() {
        return "admin-login";
    }

    @GetMapping("/admin/main")
    public String mainPage(Model model) {

        model.addAttribute("adminMainList",adminStoreService.findAll());

        return "admin-main";
    }

    @GetMapping("/admin/store")
    public String storePage(){
        return "admin-store";
    }


    @GetMapping("/admin/uploadproduct")
    public String uploadProductPage() {
        return "admin-uploadproduct";
    }

}
