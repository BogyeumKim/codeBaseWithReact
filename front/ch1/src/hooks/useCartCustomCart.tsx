import { useDispatch, useSelector } from "react-redux";

import { useEffect } from "react";
import useCustomLogin from "./useCustomLogin";
import { getCartItemsAsync } from "../slices/cartSlice";
import type { AppDispatch, RootState } from "../store";

function useCustomCart() {
  const { loginState, loginStatus } = useCustomLogin();

  const cartItems = useSelector((state: RootState) => state.cartSlice);

  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    if (loginStatus) {
      dispatch(getCartItemsAsync());
    }
  }, [loginStatus]);

  return { loginState, loginStatus, cartItems };
}
export default useCustomCart;
