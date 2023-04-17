package com.codeum.shoppingmall.admin.product.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "PRODUCT_IMG")
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long id;
    @Column
    private String originProductFileName;
    @Column
    private String savedProductFileName;
    @Column
    private String productImgThumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductImg (Product product, String originProductFileName, String savedProductFileName, String productImgThumbnail) {
        this.product = product;
        this.originProductFileName = originProductFileName;
        this.savedProductFileName = savedProductFileName;
        this.productImgThumbnail = productImgThumbnail;
    }

}
