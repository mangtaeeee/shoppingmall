package com.codeum.shoppingmall.admin.store.service;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import com.codeum.shoppingmall.admin.store.dto.*;
import com.codeum.shoppingmall.admin.store.repository.AdminStoreImgRepository;
import com.codeum.shoppingmall.admin.store.repository.AdminStoreRepository;
import com.codeum.shoppingmall.main.exception.AppException;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.codeum.shoppingmall.main.constants.ErrorCode.NEED_FILE_LOGO_AUTH;
import static com.codeum.shoppingmall.main.constants.ErrorCode.NEED_IMAGE_FILE;

@Service
@RequiredArgsConstructor
public class AdminStoreService {


    @Value("${custom.ImgSavePath}")
    private String fileDir ;
    private final AdminStoreRepository storeRepository;
    private final AdminStoreImgRepository imgRepository;

    public List<AdminStoreResponse> findStoreUser(){
        List<AdminStoreResponse> result = storeRepository.findAll().stream()
                .map(adminStore -> AdminStoreResponse.builder()
                        .adminStoreName(adminStore.getAdminStoreName())
                        .storeImg(adminStore.getStoreImg())
                        .build())
                .collect(Collectors.toList());
        return result;
    }


    @Transactional(readOnly = true)
    public List<AdminStoreDTO> findAll(){
        List<AdminStoreDTO> collet = storeRepository.findAll().stream()
                .map(adminStore -> AdminStoreDTO.builder()
                        .adminStoreName(adminStore.getAdminStoreName())
                        .adminStoreContent(adminStore.getAdminStoreContent())
                        .storeImg(adminStore.getStoreImg())
                        .productImgList(adminStore.getProducts().stream().map(product -> new AdminStoreProductImg(product)).collect(Collectors.toList()))
                        .productHashtagList(adminStore.getProducts().stream().map(product -> new AdminStoreProductHashTag(product)).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        return collet;

    }

    @Transactional
    public void saveStore(AdminStoreCreate create) throws IOException {
        if (create.getStoreImgFile() == null) {
            throw new AppException(NEED_FILE_LOGO_AUTH);
        }

        String contentType = create.getStoreImgFile().getContentType();
        if (contentType.isEmpty()) {
            throw new AppException(NEED_FILE_LOGO_AUTH);
        }
        if (contentType.contains("image/jpeg") || contentType.contains("image/png")) {
            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = create.getStoreImgFile().getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            // 날짜 폴더 생성
            String folderPath = makeFolder();
            //UUID
            String uuid = UUID.randomUUID().toString();
            //저장할 파일 이름 중간에 "_"를 이용해 구분
            String saveName = fileDir + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);
            create.getStoreImgFile().transferTo(savePath);
            //섬네일 생성 -> 섬네일 파일 이름은 중간에 s_로 시작
            String thubmnailSaveName = fileDir + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
            File thumbnailFile = new File(thubmnailSaveName);
            // 섬네일 생성
            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);



            StoreImg storeImg = StoreImg.builder()
                    .storeImgThumbnail(thubmnailSaveName)
                    .storeImgSavedName(saveName)
                    .storeImgFilePath(String.valueOf(savePath))
                    .storeImgOriginalName(originalName)
                    .build();
            imgRepository.save(storeImg);

            AdminStore adminStore = AdminStore.builder()
                    .adminStoreName(create.getAdminStoreName())
                    .adminStorePhone(create.getAdminStorePhone())
                    .adminStoreContent(create.getAdminStoreContent())
                    .adminStoreAddress(create.getAdminStoreAddress())
                    .storeImg(storeImg)
                    .build();
            storeRepository.save(adminStore);



        } else {
            throw new AppException(NEED_IMAGE_FILE);
        }

    }


    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----
        File uploadPatheFolder = new File(fileDir, folderPath);

        if (uploadPatheFolder.exists() == false) {
            uploadPatheFolder.mkdirs();
        }

        return folderPath;
    }



}
