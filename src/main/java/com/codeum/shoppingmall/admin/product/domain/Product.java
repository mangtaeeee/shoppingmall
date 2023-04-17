package com.codeum.shoppingmall.admin.product.domain;

import com.codeum.shoppingmall.user.member.domain.UserLike;
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
    private String productContent;
    @Column
    private int productPrice;
    @Column(name = "product_del_yn", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean productDelYn;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductHashtag> productHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImg> productImgList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<UserLike> userLikes = new ArrayList<>();

    @Builder
    public Product(String productName, String productContent, int productPrice) {
        this.productName = productName;
        this.productContent = productContent;
        this.productPrice = productPrice;
    }

}
