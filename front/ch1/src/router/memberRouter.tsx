import { Suspense, lazy } from "react";
const Loading = <div>Loading....</div>;
const Login = lazy(() => import("../pages/member/loginpage"));
const Logout = lazy(() => import("../pages/member/logoutPage"));
const KaKaoRedirect = lazy(() => import("../pages/member/kakaoRedirectPage"));
const MemberModify = lazy(() => import("../pages/member/modifyPage"));

export default function memberRouter() {
  return {
    path: "member",
    children: [
      {
        path: "login",
        element: (
          <Suspense fallback={Loading}>
            <Login />
          </Suspense>
        ),
      },
      {
        path: "logout",
        element: (
          <Suspense fallback={Loading}>
            <Logout />
          </Suspense>
        ),
      },
      {
        path: "kakao",
        element: (
          <Suspense fallback={Loading}>
            <KaKaoRedirect />
          </Suspense>
        ),
      },
      {
        path: "modify",
        element: (
          <Suspense fallback={Loading}>
            <MemberModify />
          </Suspense>
        ),
      },
    ],
  };
}
