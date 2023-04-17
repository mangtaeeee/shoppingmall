package com.codeum.shoppingmall.admin.store.dto;

import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminStoreResponse {


    private String adminStoreName;
    private StoreImg storeImg;

    @Builder
    public AdminStoreResponse(String adminStoreName, StoreImg storeImg) {
        this.adminStoreName = adminStoreName;
        this.storeImg = storeImg;
    }
}
