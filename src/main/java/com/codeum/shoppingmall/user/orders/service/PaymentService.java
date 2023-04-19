package com.codeum.shoppingmall.user.orders.service;

import com.codeum.shoppingmall.user.orders.domain.Orders;
import com.codeum.shoppingmall.user.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${iamport.api.key}")
    private String apiKey;
    @Value("${iamport.api.secret}")
    private String apiSecret;
    @Value("${iamport.api.url}")
    private String apiUrl;

    private final OrdersRepository ordersRepository;

    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("imp_key", apiKey);
        requestBody.put("imp_secret", apiSecret);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        String response = responseEntity.getBody();
        return response;
    }

    public String preparePayment(String merchantUid, int amount, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Authorization 헤더에 인증 토큰 정보 추가
        headers.set("Authorization", token);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("merchant_uid", merchantUid);
        requestBody.put("amount", amount);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Map> response = restTemplate.exchange(apiUrl + "/payments/prepare", HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            // API 호출 실패 처리
            System.out.println("결제정보 사전 검증 API 호출 실패");
            return "fail";
        }
        // API 호출 성공 시 처리
        System.out.println("사전 결제정보 등록 완료");
        return "success";
    }


    public String completePayment(String impUid, String merchantUid, int amount, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Authorization 헤더에 인증 토큰 정보 추가
        headers.set("Authorization", token);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("merchant_uid", merchantUid);
        requestBody.put("imp_uid", impUid);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<Map> response = restTemplate.exchange(apiUrl + "/payments/" + impUid, HttpMethod.POST, request, Map.class);

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody().get("response");
        int amt = (int) responseBody.get("amount");

        System.out.println("amt = " + amt);

//        if (response.getStatusCode() != HttpStatus.OK) {
//            // API 호출 실패 처리
//            System.out.println("결제정보 사후 검증 API 호출 실패");
//            return "fail";
//        }
//
//        Map<String, Object> responseData = response.getBody();
//        Map<String, Object> responseContent = (Map<String, Object>) responseData.get("response");
//        String responseMerchantUid = (String) responseContent.get("merchant_uid");
//
//        if (!responseMerchantUid.equals(merchantUid)) {
//            // Merchant UID 불일치 처리
//            System.out.println("Merchant UID 불일치");
//            return "fail";
//        }
//
//        String status = (String) responseContent.get("status");
//        int paidAmount = (int) responseContent.get("amount");
//
//        if (!status.equals("paid") || paidAmount != amount) {
//            // 결제 실패 처리
//            String failReason = (String) responseContent.get("fail_reason");
//            System.out.println("최종 결제 실패");
//            return "fail";
//        } else {
//            // 결제 성공 처리
//            Orders orders = ordersRepository.findByMerchantId(merchantUid);
//            orders.updateImpUid(impUid);
//            return "success";
//        }
        return null;
    }
}
