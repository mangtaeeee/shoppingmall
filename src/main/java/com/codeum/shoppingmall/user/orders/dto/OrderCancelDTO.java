package com.codeum.shoppingmall.user.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCancelDTO {
    private String merchantUid;
    private int amount;
}
