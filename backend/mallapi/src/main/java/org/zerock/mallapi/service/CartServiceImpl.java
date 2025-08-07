package org.zerock.mallapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.domain.Cart;
import org.zerock.mallapi.domain.CartItem;
import org.zerock.mallapi.domain.Member;
import org.zerock.mallapi.domain.Product;
import org.zerock.mallapi.dto.CartItemDTO;
import org.zerock.mallapi.dto.CartItemListDTO;
import org.zerock.mallapi.repository.CartItemRepository;
import org.zerock.mallapi.repository.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemListDTO> addOrModify(CartItemDTO cartItemDTO) {

        String email = cartItemDTO.getEmail();
        Long pno = cartItemDTO.getPno();
        int qty = cartItemDTO.getQty();
        Long cino = cartItemDTO.getCino();

        // 기존 담겨있는 상품 처리
        if ( cino != null ) {

            Optional<CartItem> cartItemResult = cartItemRepository.findById(cino);
            CartItem cartItem = cartItemResult.orElseThrow();

            cartItem.changeQuty(qty);

            cartItemRepository.save(cartItem);

            return getCartImtes(email);
        }

        Cart cart = getCart(email);

        CartItem cartItem = null;

        cartItem = cartItemRepository.getItemOfPno(email, pno);

        if ( cartItem == null ) {
            Product product = Product.builder().pno(pno).build();
            cartItem = CartItem.builder().product(product).cart(cart).qty(qty).build();
        } else {
            cartItem.changeQuty(qty);
        }

        cartItemRepository.save(cartItem);

        return getCartImtes(email);

    }

    private Cart getCart(String email) {

        // 해당 email의 장바구니 ( Cart ) 가 있는지 확인 있으면 반환

        // 없으면 Cart 객체 생성 후 반환

        Cart cart = null;
        Optional<Cart> result = cartRepository.getCartOfMember(email);
        if(result.isEmpty()) {
            log.info("Cart of the member is not exist!!");
            Member member = Member.builder().email(email).build();
            Cart tempCart = Cart.builder().owner(member).build();
            cart = cartRepository.save(tempCart);
        }else {
            cart = result.get();
        }
        return cart;
    }

    @Override
    public List<CartItemListDTO> getCartImtes(String email) {
        return cartItemRepository.getItemsOfCartDTOByEmail(email);
    }

    @Override
    public List<CartItemListDTO> remove(Long cino) {

        Long cno = cartItemRepository.getCartFromItem(cino);
        cartItemRepository.deleteById(cino);

        return cartItemRepository.getItemsOfCartDTOByCart(cno);
    }
}
