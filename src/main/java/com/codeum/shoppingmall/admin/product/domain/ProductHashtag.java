package com.codeum.shoppingmall.admin.product.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "PRODUCT_HASHTAG")
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ProductHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_hashtad_id")
    private Long id;
    @Column
    private String productHashtagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductHashtag(Product product, String productHashtagName) {
        this.product = product;
        this.productHashtagName = productHashtagName;
    }

}
