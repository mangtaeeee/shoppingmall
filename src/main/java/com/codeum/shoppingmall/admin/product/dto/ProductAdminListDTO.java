package com.codeum.shoppingmall.admin.product.dto;

import com.codeum.shoppingmall.admin.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductAdminListDTO {

    private Long productId;
    private String productName;
    private int productPrice;

    private String createdDate;

    private boolean productDelYn;
    private List<String> savedProductFileName;


    @Builder
    public ProductAdminListDTO(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productDelYn = product.isProductDelYn();
        this.createdDate = product.getCreatedDate();
        this.savedProductFileName = product.getProductImgList().stream().map(productImg -> productImg.getSavedProductFileName()).collect(Collectors.toList());
    }
}


