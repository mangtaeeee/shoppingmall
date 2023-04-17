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
public class AdminStoreProductHashTag {

    private List<String> productHashtagName;


    @Builder
    public AdminStoreProductHashTag(Product product) {
        this.productHashtagName = product.getProductHashtagList().stream().map(productHashtag -> productHashtag.getProductHashtagName()).collect(Collectors.toList());
    }
}
