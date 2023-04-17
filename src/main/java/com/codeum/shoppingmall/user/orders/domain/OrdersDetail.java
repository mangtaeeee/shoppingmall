package com.codeum.shoppingmall.user.orders.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ORDERS_DETAIL")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_detail_id")
    private Long Id;
    @Column
    private String buyerName;
    @Column
    private String buyerTel;
    @Column
    private String buyerEmail;
    @Column
    private String buyerAddr;
    @Column
    private String buyerPostcode;
    @Column
    private String payMethod;
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Builder
    public OrdersDetail(String buyerName, String buyerTel, String buyerEmail,
                        String buyerAddr, String buyerPostcode, String payMethod) {
        this.buyerName = buyerName;
        this.buyerTel = buyerTel;
        this.buyerEmail = buyerEmail;
        this.buyerAddr = buyerAddr;
        this.buyerPostcode = buyerPostcode;
        this.payMethod = payMethod;
    }

}
