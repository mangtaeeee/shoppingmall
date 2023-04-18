package com.codeum.shoppingmall.admin.viewcontroller;


import com.codeum.shoppingmall.admin.product.dto.ProductAdminListDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import com.codeum.shoppingmall.admin.store.service.AdminStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminViewController {

    private final AdminStoreService adminStoreService;

    private final ProductService productService;


    @GetMapping("/admin")
    public String loginPage() {
        return "admin-login";
    }

    @GetMapping("/admin/join")
    public String joinPage() {
        return "admin-join";
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

    @GetMapping("/admin/productmanage")
    public String productManagePage(Model model, @PageableDefault(page = 0, size=10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductAdminListDTO> list = productService.adminFindProduct(pageable);
        model.addAttribute("list", list);


        return "admin-productmanag";
    }

}
