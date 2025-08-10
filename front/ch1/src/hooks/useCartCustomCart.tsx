import { useDispatch, useSelector } from "react-redux";

import { useEffect } from "react";
// import useCustomLogin from "./useCustomLogin";
// import { getCartItemsAsync, postChangeCartAsync } from "../slices/cartSlice";
// import type { AppDispatch, RootState } from "../store";
import useZustandMember from "../zstore/useZustandMember";
import useZustandCart from "../zstore/useZustandCart";

function useCustomCart() {
  const { member: loginState, status: loginStatus } = useZustandMember();

  const { items, getItems, requestChangeCart, status } = useZustandCart();

  const cartItems = { items: items, status: status };
  // const loginState = mem
  // const { loginState, loginStatus } = useCustomLogin();

  // const cartItems = useSelector((state: RootState) => state.cartSlice);

  // const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    if (loginStatus) {
      // dispatch(getCartItemsAsync());
      getItems();
    }
  }, [loginStatus]);

  const changeCart = (cino: number | null, pno: number, amount: number) => {
    const email = loginState.email;

    let qty = 1;

    if (cino) {
      const targetArr = cartItems.items.filter((item) => item.cino === cino);
      if (targetArr.length > 0) {
        qty = targetArr[0].qty + amount;
      }
    }
    const requestItem: CartItemRequest = cino
      ? { email, cino, pno, qty }
      : { email, pno, qty };

    console.log(requestItem);

    // dispatch(postChangeCartAsync(requestItem));
    requestChangeCart(requestItem);
  };

  return { loginState, loginStatus, cartItems, changeCart };
}

export default useCustomCart;
