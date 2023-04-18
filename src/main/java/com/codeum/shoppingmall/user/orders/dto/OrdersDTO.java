package com.codeum.shoppingmall.user.orders.dto;

import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long ordersId;
    private String ordersProduct;
    private int ordersAmount;
    private Timestamp ordersDate;
    private String ordersState;
    private String merchantId;
    private boolean order_del_yn;

    // 주문 상세정보
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String buyerPostcode;
    private String buyerAddr;
    private String payMethod;

    @Builder
    public OrdersDTO(String ordersProduct, int ordersAmount, String merchantId) {
        this.ordersProduct = ordersProduct;
        this.ordersAmount = ordersAmount;
        this.merchantId = merchantId;
    }

    public static OrdersDTO toOrdersDTO(OrdersDetail ordersDetail) {

        OrdersDTO ordersDTO = new OrdersDTO();

        ordersDTO.setOrdersId(ordersDetail.getOrders().getId());
        ordersDTO.setOrdersProduct(ordersDetail.getOrders().getOrdersProduct());
        ordersDTO.setOrdersAmount(ordersDetail.getOrders().getOrdersAmount());
        ordersDTO.setOrdersDate(ordersDetail.getOrders().getOrdersDate());
        ordersDTO.setOrdersState(ordersDetail.getOrders().getOrdersState());
        ordersDTO.setMerchantId(ordersDetail.getOrders().getMerchantId());

        ordersDTO.setBuyerName(ordersDetail.getBuyerName());
        ordersDTO.setBuyerTel(ordersDetail.getBuyerTel());
        ordersDTO.setBuyerEmail(ordersDetail.getBuyerEmail());
        ordersDTO.setBuyerAddr(ordersDetail.getBuyerAddr());
        ordersDTO.setBuyerPostcode(ordersDetail.getBuyerPostcode());
        ordersDTO.setPayMethod(ordersDetail.getPayMethod());

        return ordersDTO;
    }

}
