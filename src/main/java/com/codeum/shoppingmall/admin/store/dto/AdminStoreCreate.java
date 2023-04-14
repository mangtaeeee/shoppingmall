package com.codeum.shoppingmall.admin.store.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminStoreCreate {
    @NotBlank(message = "상점명을 입력해 주세요.")
    private String adminStoreName;
    @NotBlank(message = "상점 주소를 입력해 주세요.")
    private String adminStoreAddress;
    @NotBlank(message = "상점번호를 입력해 주세요.")
    private String adminStorePhone;

    @NotBlank(message = "상점 설명을 입력해 주세요.")
    private String adminStoreContent;

    @NotBlank(message = "로고 사진을 추가해 주세요.")
    private MultipartFile storeImgFile;

    private String storeImgId;
    private String storeImgOriginalName;
    private String storeImgSavedName;
    private String storeImgFilePath;


}
