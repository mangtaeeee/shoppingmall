package com.codeum.shoppingmall.user.orders.dto;

import com.codeum.shoppingmall.user.orders.domain.Orders;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long ordersId;
    private Long memberId;
    private String ordersProduct;
    private int ordersAmount;
    private Date ordersDate;
    private String ordersState;
    private String merchantId;
    private boolean order_del_yn;


    // 주문 상세정보

    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String buyerAddr;
    private String buyerPostcode;
    private String payMethod;

    public OrdersDTO(String ordersProduct, Long memberId, int ordersAmount, Date ordersDate, String merchantId) {
        this.ordersProduct = ordersProduct;
        this.memberId = memberId;
        this.ordersAmount = ordersAmount;
        this.ordersDate = ordersDate;
        this.merchantId = merchantId;
    }

    public static OrdersDTO toOrdersDTO(Orders orders) {

        OrdersDTO ordersDTO = new OrdersDTO();

        ordersDTO.setOrdersId(orders.getId());
        ordersDTO.setOrdersProduct(orders.getOrdersProduct());
        ordersDTO.setOrdersAmount(orders.getOrdersAmount());
        ordersDTO.setOrdersState(orders.getOrdersState());
        ordersDTO.setMerchantId(orders.getMerchantId());

        OrdersDetail ordersDetail = new OrdersDetail();

        ordersDTO.setBuyerName(ordersDetail.getBuyerName());
        ordersDTO.setBuyerTel(ordersDetail.getBuyerTel());
        ordersDTO.setBuyerEmail(ordersDetail.getBuyerEmail());
        ordersDTO.setBuyerAddr(ordersDetail.getBuyerAddr());
        ordersDTO.setBuyerPostcode(ordersDetail.getBuyerPostcode());
        ordersDTO.setPayMethod(ordersDetail.getPayMethod());

        return ordersDTO;
    }

}
