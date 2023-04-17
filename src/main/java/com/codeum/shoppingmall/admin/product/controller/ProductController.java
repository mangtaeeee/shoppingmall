package com.codeum.shoppingmall.admin.product.controller;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductDTO>> findAll(@Param("offset") String offset,
                                                    @Param("limit") String limit) {
        List<ProductDTO> productDTOList = productService.findAll(offset, limit);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

}
