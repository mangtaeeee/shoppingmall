package com.codeum.shoppingmall.user.orders.controller;

import com.codeum.shoppingmall.user.orders.dto.OrderCancelDTO;
import com.codeum.shoppingmall.user.orders.service.PaymentService;
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
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/getToken")
    public ResponseEntity<String> getToken() {
        System.out.println("토큰 컨트롤러 호출");
        String token = paymentService.getToken();
        System.out.println("token = " + token);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/prepare")
    public String prepare(@RequestBody Map<String, String> map) {
        System.out.println("사전검증api컨트롤러");
        System.out.println(map.get("merchant_uid"));
        System.out.println(map.get("amount"));
        System.out.println(map.get("token"));

        String merchantUid = map.get("merchant_uid");
        int amount = Integer.parseInt(map.get("amount"));
        String token = map.get("token");

        System.out.println("merchantUid:" + merchantUid);
        System.out.println("amount:" + amount);
        System.out.println("token = " + token);
        
        return paymentService.preparePayment(merchantUid, amount, token);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> complete(@RequestBody Map<String, String> map) {
        System.out.println("사후검증api컨트롤러");

        String impUid = map.get("imp_uid");
        String merchantUid = map.get("merchant_uid");
        int amount = Integer.parseInt(map.get("amount"));
        String token = map.get("token");

        System.out.println("impUid:" + impUid);
        System.out.println("merchantUid:" + merchantUid);
        System.out.println("amount:" + amount);
        System.out.println("token = " + token);

        String result = paymentService.completePayment(impUid, merchantUid, amount, token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestBody OrderCancelDTO dto) {
        String result = paymentService.cancelPayment(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
