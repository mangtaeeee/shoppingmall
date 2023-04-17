package com.codeum.shoppingmall.admin.store.dto;


import com.codeum.shoppingmall.admin.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdminStoreProductImg {


    private List<String> originProductFileName;
    private List<String> savedProductFileName;

    private List<String> productImgThumbnail;

    @Builder
    public AdminStoreProductImg(Product product) {
        this.originProductFileName = product.getProductImgList().stream().map(productImg -> productImg.getOriginProductFileName()).collect(Collectors.toList());
        this.savedProductFileName = product.getProductImgList().stream().map(productImg -> productImg.getSavedProductFileName()).collect(Collectors.toList());
        this.productImgThumbnail = product.getProductImgList().stream().map(productImg -> productImg.getProductImgThumbnail()).collect(Collectors.toList());
    }
}
