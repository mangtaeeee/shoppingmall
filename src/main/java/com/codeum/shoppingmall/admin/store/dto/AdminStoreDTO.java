package com.codeum.shoppingmall.admin.store.dto;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Getter
public class AdminStoreDTO {


    private String adminStoreName;

    private String adminStoreContent;

    private StoreImg storeImg;


    private List<AdminStoreProductHashTag> productHashtagList ;
    private List<AdminStoreProductImg> productImgList ;
    @Builder
    public AdminStoreDTO(String adminStoreName, String adminStoreContent, StoreImg storeImg, List<AdminStoreProductHashTag> productHashtagList, List<AdminStoreProductImg> productImgList) {
        this.adminStoreName = adminStoreName;
        this.adminStoreContent = adminStoreContent;
        this.storeImg = storeImg;
        this.productHashtagList = productHashtagList;
        this.productImgList = productImgList;
    }
}
