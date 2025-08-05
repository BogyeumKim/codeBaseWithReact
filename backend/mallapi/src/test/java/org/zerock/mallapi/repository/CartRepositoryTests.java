package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.CartItem;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    @Transactional
    @Commit
    public void testInsertByProdcut(){

        String email = "test@gmail.com";
        Long pno = 5L;
        int qty = 1;

        // 이메일 상품번호로 장바구니 아이템 확인 , 없으면 추가 있으면 수량 변경후 저장
        CartItem cartItem = cartItemRepository.getItemOfPno(email, pno);

        // 이미 사용자의 장바구니에 담겨있는 상품
        if(cartItem != null) {
            cartItem.changeQuty(qty);
            cartItemRepository.save(cartItem);
            return;
        }
    }

    @Test
    public void testListOfMember() {
        String email = "user1@aaa.com";

        cartItemRepository.getItemsOfCartDTOByEmail(email);
    }
}
