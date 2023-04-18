package com.codeum.shoppingmall.admin.store.dto;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminStoreSearchDTO {

    private String adminStoreName;
    private String storeImgSavedName;

    public static AdminStoreSearchDTO toStoreList(AdminStore adminStore) {
        return new AdminStoreSearchDTO(
                adminStore.getAdminStoreName(),
                adminStore.getStoreImg().getStoreImgSavedName()
        );
    }
}
