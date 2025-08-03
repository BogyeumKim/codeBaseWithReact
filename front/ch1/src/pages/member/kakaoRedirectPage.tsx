import { useEffect } from "react";
import { useSearchParams } from "react-router";
import { getAccessToken, getMemberWithAccessToken } from "../../api/kakaoApi";
import { useDispatch } from "react-redux";
import { save } from "../../slices/loginSlice";
import type { AppDispatch } from "../../store";

const KakaoRedirectPage = () => {
  const [searchParams] = useSearchParams();

  const authCode = searchParams.get("code");
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    if (authCode) {
      getAccessToken(authCode).then((accessToken) => {
        console.log("TOKEN!!!!!!!!!", accessToken);
        getMemberWithAccessToken(accessToken).then((result) => {
          console.log("=======================");
          console.log(result);
          dispatch(save(result));
        });
      });
    }
  }, [authCode]);

  return (
    <div>
         <div>Kakao Login Redirect</div>   <div>{authCode}</div> {" "}
    </div>
  );
};

export default KakaoRedirectPage;
