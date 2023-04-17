package com.codeum.shoppingmall.admin.product.service;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.product.domain.ProductHashtag;
import com.codeum.shoppingmall.admin.product.domain.ProductImg;
import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.repository.ProductHashtagRepository;
import com.codeum.shoppingmall.admin.product.repository.ProductImgRepository;
import com.codeum.shoppingmall.admin.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final ProductHashtagRepository productHashtagRepository;


    // 상품 이미지 파일이 저장될 저장소 설정 ( 추후 변경해야함 )
    //String localSavedPath = "/files/";
    String ImgSavedPath = "/C:/Users/USER/Desktop/dev/uploads/";
    String ThumbnailsavedPath = "/C:/Users/USER/Desktop/dev/uploads/thumbnails";

    public void uploadProduct(ProductDTO productDTO) throws IOException {

        //product 객체 생성
        Product product = Product.builder()
                .productName(productDTO.getProductName())
                .productPrice(productDTO.getProductPrice())
                .productContent(productDTO.getProductContent())
                .build();

        Long productId = productRepository.save(product).getId();
        Product savedProduct = productRepository.findById(productId).get();

        //해시태그 저장
        for (String productHashtagName : productDTO.getProductHashtagName()) {

            ProductHashtag productHashtag = ProductHashtag.builder()
                    .product(savedProduct)
                    .productHashtagName(productHashtagName)
                    .build();
            productHashtagRepository.save(productHashtag);
        }

        //첨부 파일 저장
        for (MultipartFile productImgFile : productDTO.getProductImgFile()) {

            // 파일 확장자 검사
            String contentType = productImgFile.getContentType();

            // 확장자명이 존재하지 않으면 처리 X
            if (ObjectUtils.isEmpty(contentType)) {
                break;
            } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                if (contentType.contains("image/jpeg") || contentType.contains("image/png")) {
                    String originProductFileName = productImgFile.getOriginalFilename();
                    String savedProductFileName = System.currentTimeMillis() + "_" + originProductFileName;
                    String savedProductFilePath = ImgSavedPath + savedProductFileName;
                    productImgFile.transferTo(new File(savedProductFilePath));

                    //원본 사진을 resize 한 썸네일 이미지 생성
                    String productImgThumbnailName = "thumbnail_"+savedProductFileName;
                    File thumbnailFile = new File(ThumbnailsavedPath, productImgThumbnailName);
                    Thumbnails.of(savedProductFilePath)
                            .size(200, 200)
                            .toFile(thumbnailFile);

                    // 상품 이미지 엔티티에 데이터를 담아 DB등록
                    ProductImg productImg = ProductImg.builder()
                            .product(savedProduct)
                            .originProductFileName(originProductFileName)
                            .savedProductFileName(savedProductFileName)
                            .productImgThumbnail(productImgThumbnailName)
                            .build();
                    productImgRepository.save(productImg);
                } else  // 다른 확장자일 경우 처리 x
                    break;
            }

        }

    }

    @Transactional
    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(ProductDTO.toProductDTO(product));
        }
        return productDTOList;
    }

    @Transactional
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDTO productDTO = ProductDTO.toProductDTO(product);
            return productDTO;
        } else {
            return null;
        }
    }

}
