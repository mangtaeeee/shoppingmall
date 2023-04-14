package com.codeum.shoppingmall.admin.product.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT_IMG")
@NoArgsConstructor
@AllArgsConstructor

public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long productImgId;
    @Column
    private String originProductFileName;
    @Column
    private String savedProductFileName;
    @Column
    private String savedProductFilePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductImg(Product product, String originProductFileName, String savedProductFileName, String savedProductFilePath) {
        this.originProductFileName = originProductFileName;
        this.savedProductFileName = savedProductFileName;
        this.savedProductFilePath = savedProductFilePath;
        this.product = product;
    }

    public static ProductImg toProductImgEntity (Product product, String originProductFileName, String savedProductFileName, String savedProductFilePath) {

        ProductImg productImg = new ProductImg();

        productImg.setOriginProductFileName(originProductFileName);
        productImg.setSavedProductFileName(savedProductFileName);
        productImg.setSavedProductFilePath(savedProductFilePath);
        productImg.setProduct(product);

        return productImg;
    }


}
