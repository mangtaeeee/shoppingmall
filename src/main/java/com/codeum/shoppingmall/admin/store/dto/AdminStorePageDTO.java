package com.codeum.shoppingmall.admin.store.dto;

import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@Getter
public class AdminStorePageDTO {


    private String adminStoreName;

    private String adminStoreContent;

    private StoreImg storeImg;


    private List<AdminStoreProductHashTag> productHashtagList ;
    private Page<AdminStoreProductImg> productImgList ;
    @Builder
    public AdminStorePageDTO(String adminStoreName, String adminStoreContent, StoreImg storeImg, List<AdminStoreProductHashTag> productHashtagList, Page<AdminStoreProductImg>  productImgList) {
        this.adminStoreName = adminStoreName;
        this.adminStoreContent = adminStoreContent;
        this.storeImg = storeImg;
        this.productHashtagList = productHashtagList;
        this.productImgList = productImgList;
    }
    
}
