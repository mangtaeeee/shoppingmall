package com.codeum.shoppingmall.user.member.service;

import com.codeum.shoppingmall.admin.product.domain.Product;
import com.codeum.shoppingmall.admin.product.repository.ProductRepository;
import com.codeum.shoppingmall.user.member.dto.UserOrdersListDTO;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import com.codeum.shoppingmall.user.orders.repository.OrdersDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrdersListService {

    private final OrdersDetailRepository ordersDetailRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<UserOrdersListDTO> findUserOrders(Long memberId, Pageable pageable) {

        Page<OrdersDetail> ordersDetails = ordersDetailRepository.findAllByBuyerMemberId(memberId);
        List<UserOrdersListDTO> userOrdersListDTOList = new ArrayList<>();

        for (OrdersDetail ordersDetail : ordersDetails) {

            Long ordersProductId = ordersDetail.getOrders().getOrdersProductId();
            Optional<Product> optionalProduct = productRepository.findById(ordersProductId);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                UserOrdersListDTO userOrdersListDTO = UserOrdersListDTO.builder()
                        .ordersDetail(ordersDetail)
                        .product(product)
                        .build();
                userOrdersListDTOList.add(userOrdersListDTO);
            } else {
                return null;
            }
        }
        return new PageImpl<>(userOrdersListDTOList, pageable, ordersDetails.getTotalElements());

    }


}
