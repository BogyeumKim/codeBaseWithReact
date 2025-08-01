import { Suspense, lazy } from "react";
const Loading = <div>Loading....</div>;
const Login = lazy(() => import("../pages/member/loginpage"));
const Logout = lazy(() => import("../pages/member/logoutPage"));

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
    ],
  };
}
