package com.codeum.shoppingmall.user.viewcontroller;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import com.codeum.shoppingmall.user.member.dto.UserLikeDto;
import com.codeum.shoppingmall.user.member.dto.UserOrdersListDTO;
import com.codeum.shoppingmall.user.member.service.UserLikeService;
import com.codeum.shoppingmall.user.member.service.UserOrdersListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ProductService productService;
    private final UserLikeService userLikeService;
    private final UserOrdersListService userOrdersListService;

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

    @GetMapping("/like/{memberId}")
    public String getInterestProduct(@PathVariable("memberId") Long memberId, Model model) {
        List<UserLikeDto> result = userLikeService.getInterestProduct(memberId);
        model.addAttribute("likeList", result);
        return "like-list";
    }

    @GetMapping("/user/orderslist/{memberId}")
    public String userOrdersListPage(@PathVariable("memberId") Long memberId, Model model, @PageableDefault(page = 0, size=5) Pageable pageable) {
        System.out.println("userOrdersListPage memberId = " + memberId);
        Page<UserOrdersListDTO> userOrdersList = userOrdersListService.findUserOrders(memberId, pageable);
        model.addAttribute("list", userOrdersList);

        return "user-orderslist";
    }

}
