package com.codeum.shoppingmall.user.orders.service;

import com.codeum.shoppingmall.main.constants.ErrorCode;
import com.codeum.shoppingmall.main.exception.AppException;
import com.codeum.shoppingmall.user.orders.domain.Orders;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import com.codeum.shoppingmall.user.orders.repository.OrdersDetailRepository;
import com.codeum.shoppingmall.user.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    @Value("${iamport.api.key}")
    private String apiKey;
    @Value("${iamport.api.secret}")
    private String apiSecret;
    @Value("${iamport.api.url}")
    private String apiUrl;

    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;

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
        int compareAmount = (int) responseBody.get("amount");

        Orders originalOrders = ordersRepository.findByMerchantId(merchantUid);
        Orders copiedOrders = new Orders(originalOrders);

        OrdersDetail ordersDetail = ordersDetailRepository.findByOrders(originalOrders);

        if (ordersDetail.getPayMethod().equals("vbank")) {

            Orders vBank = copiedOrders.toBuilder()
                    .impUid(impUid)
                    .build();

            ordersRepository.save(vBank);

            return "success";
        } else {

            if (amount != compareAmount) {

                Orders forgery = copiedOrders.toBuilder()
                        .ordersState("forgery")
                        .impUid(impUid)
                        .build();

                ordersRepository.save(forgery);

                throw new AppException(ErrorCode.AMOUNT_NOT_EQUAL);
            } else {
                Orders success = copiedOrders.toBuilder()
                        .ordersDelYn(true)
                        .ordersState("paid")
                        .impUid(impUid)
                        .build();

                ordersRepository.save(success);

                return "success";
            }
        }
    }

    public String cancelPayment(Long ordersId) {
        Optional<Orders> orders = ordersRepository.findById(ordersId);
        Orders order = orders.get();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("imp_key", apiKey);
        requestBody.put("imp_secret", apiSecret);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, requestEntity, Map.class);
        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody().get("response");
        String token = (String) responseBody.get("access_token");

        HttpHeaders cancelHeaders = new HttpHeaders();
        cancelHeaders.setContentType(MediaType.APPLICATION_JSON);
        cancelHeaders.set("Authorization", token);

        Map<String, Object> cancelRequestBody = new HashMap<>();
        cancelRequestBody.put("imp_uid", order.getImpUid());
        cancelRequestBody.put("merchant_uid", order.getMerchantId());
        cancelRequestBody.put("amount", order.getOrdersAmount());

        HttpEntity<Map<String, Object>> cancelRequestEntity = new HttpEntity<>(cancelRequestBody, cancelHeaders);
        ResponseEntity<Map> cancelResponseEntity = restTemplate.postForEntity(apiUrl + "/payments/cancel", cancelRequestEntity, Map.class);

        Map<String, Object> cancelResponseBody = cancelResponseEntity.getBody();

        Orders updateOrder = order.toBuilder()
                .ordersState("refunded")
                .ordersDelYn(false)
                .build();

        ordersRepository.save(updateOrder);

        return cancelResponseBody.toString();
    }
}
