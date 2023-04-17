package com.codeum.shoppingmall.user.orders.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long Id;
    @Column
    private String ordersProduct;
    @Column
    private int ordersAmount;
    @Column(name = "orders_date", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate ordersDate = LocalDate.now();
    @Column(name = "order_state", nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'N'")
    private String ordersState;
    @Column(unique = true)
    private String merchantId;
    @Column(name = "order_del_yn", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean orderDelYn;
    @OneToOne(mappedBy = "orders")
    private OrdersDetail ordersDetail;

    @Builder
    public Orders (String ordersProduct, int ordersAmount, String merchantId) {
        this.ordersProduct = ordersProduct;
        this.ordersAmount = ordersAmount;
        this.merchantId = merchantId;
    }

}
