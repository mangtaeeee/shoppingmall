package com.codeum.shoppingmall.user.viewcontroller;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
=======
import org.springframework.data.repository.query.Param;
>>>>>>> master
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ProductService productService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("productId", id);
        model.addAttribute("product", productDTO);

        return "detail";
    }

}
