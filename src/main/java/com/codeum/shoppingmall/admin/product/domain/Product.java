package com.codeum.shoppingmall.admin.product.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column
    private String productName;
    @Column
    private String productHashtag;
    @Column
    private String productContent;
    @Column
    private int productPrice;
    @Column
    private int productCount;
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class) // 무한 참조 방지
    @OneToMany(
            mappedBy = "product",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<ProductImg> productImgList = new ArrayList<>();

    @Builder
    public Product(String productName, String productHashtag, String productContent, int productPrice, int productCount) {
        this.productName = productName;
        this.productHashtag = productHashtag;
        this.productContent = productContent;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }


}
