package com.codeum.shoppingmall.user.orders.service;

import com.codeum.shoppingmall.admin.product.dto.ProductDTO;
import com.codeum.shoppingmall.user.member.domain.UserMember;
import com.codeum.shoppingmall.user.orders.domain.Orders;
import com.codeum.shoppingmall.user.orders.domain.OrdersDetail;
import com.codeum.shoppingmall.user.orders.dto.OrdersDTO;
import com.codeum.shoppingmall.user.orders.repository.OrdersDetailRepository;
import com.codeum.shoppingmall.user.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;

    public Long createOrder(UserMember userMember, ProductDTO productDTO, String payMethod) {

        //merchant_id 값 생성
        long Num = System.nanoTime();
        String merchant_id = "ORD" + Num;
        System.out.println("create merchant_id : " + merchant_id);

        //주문 생성 후 DB 저장
        Orders orders = Orders.builder()
                .ordersProduct(productDTO.getProductName())
                .ordersAmount(productDTO.getProductPrice())
                .merchantId(merchant_id)
                .build();
        Long ordersId = ordersRepository.save(orders).getId();
        Orders savedOrders = ordersRepository.findById(ordersId).get();

        //주문 상세 생성 후 DB 저장
        OrdersDetail ordersDetail = OrdersDetail.builder()
                .orders(savedOrders)
                .buyerName(userMember.getUserMemberName())
                .buyerTel(userMember.getUserMemberPhone())
                .buyerEmail(userMember.getUserMemberEmail())
                .buyerAddr(userMember.getUserMemberAddress())
                .buyerPostcode(userMember.getUserMemberPostCode())
                .payMethod(payMethod)
                .build();
        ordersDetailRepository.save(ordersDetail);

        return ordersId;
    }

    @Transactional
    public OrdersDTO findById(Long id) {
        Optional<OrdersDetail> optionalOrdersDetail = ordersDetailRepository.findById(id);
        if (optionalOrdersDetail.isPresent()) {
            OrdersDetail ordersDetail = optionalOrdersDetail.get();
            OrdersDTO ordersDTO = OrdersDTO.toOrdersDTO(ordersDetail);
            return ordersDTO;
        } else {
            return null;
        }
    }

    public void updateStatus(String impUid, String merchantUid) {

        Orders orders = ordersRepository.findByMerchantId(merchantUid);
        orders.updateImpUid(impUid);

    }
}
