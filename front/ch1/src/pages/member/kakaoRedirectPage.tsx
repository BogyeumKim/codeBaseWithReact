import { useEffect } from "react";
import { useSearchParams } from "react-router";
import { getAccessToken, getMemberWithAccessToken } from "../../api/kakaoApi";

const KakaoRedirectPage = () => {
  const [searchParams] = useSearchParams();

  const authCode = searchParams.get("code");

  useEffect(() => {
    if (authCode) {
      getAccessToken(authCode).then((accessToken) => {
        console.log("TOKEN!!!!!!!!!", accessToken);
        getMemberWithAccessToken(accessToken).then((result) => {
          console.log("=======================");
          console.log(result);
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
