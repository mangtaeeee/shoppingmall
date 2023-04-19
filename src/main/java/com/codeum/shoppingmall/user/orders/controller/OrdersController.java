package com.codeum.shoppingmall.user.orders.controller;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.service.ProductService;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.member.service.UserMemberService;
import com.codeum.shoppingmall.user.orders.dto.OrdersDTO;
import com.codeum.shoppingmall.user.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {

    private final UserMemberService userMemberService;
    private final ProductService productService;
    private final OrdersService ordersService;

    @PostMapping("/create-order")
    public ResponseEntity<OrdersDTO> getMember(@RequestBody Map<String, String> map) {
        System.out.println("cerate order 실행");
        Long Uid = Long.parseLong(map.get("userId"));
        Long Pid = Long.parseLong(map.get("productId"));
        String payMethod = map.get("payMethod");
        UserMember userMember = userMemberService.findById(Uid);
        ProductDTO productDTO = productService.findById(Pid);
        Long ordersId = ordersService.createOrder(userMember, productDTO, payMethod);
        OrdersDTO ordersDTO = ordersService.findById(ordersId);

        System.out.println("cerate order 종료");
        return ResponseEntity.status(HttpStatus.OK).body(ordersDTO);
    }

}
