package com.codeum.shoppingmall.admin.product.service;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.product.domain.ProductImg;
import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.admin.product.repository.ProductImgRepository;
import com.codeum.shoppingmall.admin.product.repository.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@Getter

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;


    // 상품 이미지 파일이 저장될 저장소 설정 ( 추후 변경해야함 )
    String localSavedPath = "/files/";
    String localSavedPath2 = "/Library/Codeum/fileupload/";

    public void uploadProduct(ProductDTO productDTO) throws IOException {

        //첨부 파일 저장
        Product product = Product.toSaveImgEntity(productDTO);
        Long productId = productRepository.save(product).getId();
        Product product2 = productRepository.findById(productId).get();


        for (MultipartFile productImgFile : productDTO.getProductImgFile()) {

            // 파일 확장자 검사
            String contentType = productImgFile.getContentType();

            // 확장자명이 존재하지 않으면 처리 X
            if(ObjectUtils.isEmpty(contentType)) {
                break;
            }
            else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                if(contentType.contains("image/jpeg") || contentType.contains("image/png")) {
                    String originProductFileName = productImgFile.getOriginalFilename();
                    String savedProductFileName = System.currentTimeMillis() + "_" + originProductFileName;
                    String savedProductFilePath = localSavedPath + savedProductFileName;
                    productImgFile.transferTo(new File(savedProductFilePath));
                    ProductImg productImg = ProductImg.toProductImgEntity(product2, originProductFileName, savedProductFileName, savedProductFilePath);
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
        for (Product product: productList) {
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
