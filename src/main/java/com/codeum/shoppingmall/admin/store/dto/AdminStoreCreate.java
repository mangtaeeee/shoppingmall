package com.codeum.shoppingmall.admin.store.dto;


import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
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





}
