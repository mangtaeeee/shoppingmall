package com.codeum.shoppingmall.user.member.service;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.product.repository.ProductRepository;
import com.codeum.shoppingmall.main.exception.AppException;
import com.codeum.shoppingmall.user.member.domain.UserLike;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.member.dto.UserLikeDto;
import com.codeum.shoppingmall.user.member.repository.UserLIkeRepository;
import com.codeum.shoppingmall.user.member.repository.UserMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codeum.shoppingmall.main.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserLikeService {

    private final UserMemberRepository userMemberRepository;
    private final ProductRepository productRepository;
    private final UserLIkeRepository userLIkeRepository;

    public List<UserLikeDto> getInterestProduct(Long memberId) {

        UserMember userMember = userMemberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));

        List<UserLike> userLikes = userLIkeRepository.findAllByUserMemberOrderByUpdatedAtDesc(userMember)
                .orElseThrow(() -> new AppException(LIKE_LIST_NOT_FOUND));

        List<UserLikeDto> userLikeDtoList = new ArrayList<>();

        for (UserLike userLike : userLikes) {
            userLikeDtoList.add(UserLikeDto.toLikeList(userLike));
        }

        return userLikeDtoList;
    }

    public UserLike addInterestProduct(Long memberId, Long productId) {

        UserMember userMember = userMemberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(PRODUCT_NOT_FOUND));

        UserLike userLike = UserLike.builder()
                .userMember(userMember)
                .product(product)
                .build();

        return userLIkeRepository.save(userLike);
    }
}
