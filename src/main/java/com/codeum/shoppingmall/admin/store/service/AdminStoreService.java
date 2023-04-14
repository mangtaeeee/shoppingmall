package com.codeum.shoppingmall.admin.store.service;

import com.codeum.shoppingmall.admin.store.domain.AdminStore;
import com.codeum.shoppingmall.admin.store.domain.StoreImg;
import com.codeum.shoppingmall.admin.store.dto.AdminStoreCreate;
import com.codeum.shoppingmall.admin.store.repository.AdminStoreImgRepository;
import com.codeum.shoppingmall.admin.store.repository.AdminStoreRepository;
import com.codeum.shoppingmall.main.exception.AppException;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.codeum.shoppingmall.main.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AdminStoreService {


    private String fileDir = "/Library/Codeum/fileupload/";
    private final AdminStoreRepository storeRepository;
    private final AdminStoreImgRepository imgRepository;


    @Transactional
    public void saveStore(AdminStoreCreate create) throws IOException {
        String contentType = create.getStoreImgFile().getContentType();
        if (contentType.isEmpty()) {
            throw new AppException(NEED_FILE_LOGO_AUTH);
        }
        if (contentType.contains("image/jpeg") || contentType.contains("image/png")) {
            String origName = create.getStoreImgFile().getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String extension = origName.substring(origName.lastIndexOf("."));
            String savedName = uuid + extension;
            String savedPath = fileDir + savedName;


            create.getStoreImgFile().transferTo(new File(savedPath));

            Path savePath = Paths.get(savedName);


            String thumbnailSaveName = savedPath + uuid + extension + "_thump";
            File thumbnailFile = new File(thumbnailSaveName);

            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 50, 50);


            StoreImg storeImg = StoreImg.builder()
                    .storeImgOriginalName(origName)
                    .storeImgSavedName(savedName)
                    .storeImgFilePath(savedPath)
                    .storeImgThumbnail(thumbnailSaveName)
                    .build();


            StoreImg savedImg = imgRepository.save(storeImg);

            AdminStore store = AdminStore.builder()
                    .adminStoreName(create.getAdminStoreName())
                    .adminStoreAddress(create.getAdminStoreAddress())
                    .adminStoreContent(create.getAdminStoreContent())
                    .adminStorePhone(create.getAdminStorePhone())
                    .storeImg(savedImg)
                    .build();
            storeRepository.save(store);
        } else {
            throw new AppException(NEED_IMAGE_FILE);
        }

    }


}
