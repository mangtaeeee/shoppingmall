package com.codeum.shoppingmall.admin.product.controller;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseBody
    @PostMapping("/uploadproduct")
    public ModelAndView uploadProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        System.out.println("상품 업로드 컨트롤러 호출 productDTO");
        System.out.println("productDTO: "+productDTO);
        productService.uploadProduct(productDTO);
        return new ModelAndView("redirect:/product/productlist");
    }

    @GetMapping("/productlist")
    public String findAll(Model model) {
        List<ProductDTO> productDTOList = productService.findAll();
        model.addAttribute("productList", productDTOList);
        return "index";
    }

    @GetMapping("/productdetail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        System.out.println("detail 컨트롤러 서비스 호출 후");
        System.out.println("productDTO : "+productDTO);

        model.addAttribute("productId", id);
        model.addAttribute("product", productDTO);

        return "detail";
    }

}
