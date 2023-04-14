package com.codeum.shoppingmall.admin.product.dto;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.product.domain.ProductImg;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {

    // 상품 데이터
    private Long productId;
    private String productName;
    private String productContent;
    private int productPrice;
    private int productCount;
    private int storeId;
    private boolean productDelYn;

    // 상품 해시태그 데이터
    private List<String> productHashtag;

    // 상품 이미지 데이터
    private List<MultipartFile> productImgFile;   // controller에서 파일 받아오는 용도
    private List<String> originProductFileName;  // 원본 파일 이름
    private List<String> savedProductFileName;   // 서버 저장용 파일 이름
    private List<String> savedProductFilePath;   // 파일 전체 저장 경로

    @Builder
    public ProductDTO(String productName, String productContent, int productPrice, int productCount) {
        this.productName = productName;
        this.productContent = productContent;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public static ProductDTO toProductDTO(Product product) {

        ProductDTO productDTO = new ProductDTO();
        List<String> originProductFileNameList = new ArrayList<>();
        List<String> savedProductFileNameList = new ArrayList<>();
        List<String> savedProductFilePathList = new ArrayList<>();

        productDTO.setProductId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductContent(product.getProductContent());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductCount(product.getProductCount());

        for (ProductImg productImg : product.getProductImgList()) {

            originProductFileNameList.add(productImg.getOriginProductFileName());
            savedProductFileNameList.add(productImg.getSavedProductFileName());
            savedProductFilePathList.add(productImg.getSavedProductFilePath());
        }
        productDTO.setOriginProductFileName(originProductFileNameList);
        productDTO.setSavedProductFileName(savedProductFileNameList);
        productDTO.setSavedProductFilePath(savedProductFilePathList);

        return productDTO;
    }


}
