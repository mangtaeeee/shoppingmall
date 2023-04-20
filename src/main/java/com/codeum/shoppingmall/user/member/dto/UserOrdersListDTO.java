package com.codeum.shoppingmall.user.member.dto;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserOrdersListDTO {

    private Long ordersId;
    private String ordersProduct;
    private int ordersAmount;
    private String payMethod;
    private Timestamp ordersDate;
    private boolean ordersDelYn;
    private List<String> savedProductFileName;

    @Builder
    public UserOrdersListDTO(OrdersDetail ordersDetail, Product product) {
        this.ordersId = ordersDetail.getOrders().getId();
        this.ordersProduct = ordersDetail.getOrders().getOrdersProduct();
        this.ordersAmount = ordersDetail.getOrders().getOrdersAmount();
        this.payMethod = ordersDetail.getPayMethod();
        this.ordersDate = ordersDetail.getOrders().getOrdersDate();
        this.ordersDelYn = ordersDetail.getOrders().isOrdersDelYn();
        this.savedProductFileName = product.getProductImgList().stream().map(productImg -> productImg.getSavedProductFileName()).collect(Collectors.toList());
    }

}
