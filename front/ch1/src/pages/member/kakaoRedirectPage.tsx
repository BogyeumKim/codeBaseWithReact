import { useEffect } from "react";
import { Navigate, useNavigate, useSearchParams } from "react-router";
import { getAccessToken, getMemberWithAccessToken } from "../../api/kakaoApi";
// import { useDispatch } from "react-redux";
import { save } from "../../slices/loginSlice";
import useZustandMember from "../../zstore/useZustandMember";
// import type { AppDispatch } from "../../store";

const KakaoRedirectPage = () => {
  const [searchParams] = useSearchParams();

  const authCode = searchParams.get("code");

  const { save } = useZustandMember();
  // const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();

  useEffect(() => {
    if (authCode) {
      getAccessToken(authCode).then((accessToken) => {
        console.log("TOKEN!!!!!!!!!", accessToken);
        getMemberWithAccessToken(accessToken).then((result) => {
          console.log("=======================");
          console.log(result);
          // dispatch(save(result));
          save(result);

          if (result.social) {
            navigate("/member/modify");
          } else {
            navigate("/");
          }
        });
      });
    }
  }, [authCode]);

  return (
    <div>
      <Navigate to={"/"}></Navigate>
    </div>
  );
};

export default KakaoRedirectPage;
