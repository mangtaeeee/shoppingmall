package com.codeum.shoppingmall.user.member.dto;

import com.codeum.shoppingmall.user.member.domain.UserLike;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLikeDto {

    private long id;
    private long productId;
    private String name;
    private int price;
    private String path;

    public static UserLikeDto toLikeList(UserLike userLike) {
        return new UserLikeDto(
                userLike.getId(),
                userLike.getProduct().getId(),
                userLike.getProduct().getProductName(),
                userLike.getProduct().getProductPrice(),
                userLike.getProduct().getProductImgList().get(0).getSavedProductFilePath()
        );
    }
}
