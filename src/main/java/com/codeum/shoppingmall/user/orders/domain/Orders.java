package com.codeum.shoppingmall.user.orders.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long Id;
    @Column
    private String ordersProduct;
    @Column
    private Long ordersProductId;
    @Column
    private int ordersAmount;
    @Column(name = "orders_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ordersDate;
    @Column(name = "orders_state", columnDefinition = "VARCHAR(20) DEFAULT 'ready'")
    private String ordersState;
    @Column(unique = true)
    private String merchantId;
    @Column(name = "order_del_yn", columnDefinition = "BIT(1) DEFAULT 0")
    private boolean ordersDelYn;
    @Column
    private String impUid;

    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private OrdersDetail ordersDetail;

    public Orders(Orders orders) {
        this.Id = orders.getId();
        this.ordersProduct = orders.getOrdersProduct();
        this.ordersProductId = orders.getOrdersProductId();
        this.ordersAmount = orders.getOrdersAmount();
        this.ordersDate = orders.getOrdersDate();
        this.ordersState = orders.getOrdersState();
        this.merchantId = orders.getMerchantId();
        this.ordersDelYn = orders.isOrdersDelYn();
        this.impUid = orders.getImpUid();
    }

    @Builder(toBuilder = true)
    public Orders(Long Id, String ordersProduct, int ordersAmount, Long ordersProductId,  Timestamp ordersDate, String ordersState, String merchantId, boolean ordersDelYn, String impUid) {
        this.Id = Id;
        this.ordersProduct = ordersProduct;
        this.ordersProductId = ordersProductId;
        this.ordersAmount = ordersAmount;
        this.ordersDate = ordersDate;
        this.ordersState = ordersState;
        this.merchantId = merchantId;
        this.ordersDelYn = ordersDelYn;
        this.impUid = impUid;
    }
}
