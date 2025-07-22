import { Suspense, lazy } from "react";
const Loading = <div>Loading....</div>;
const Login = lazy(() => import("../pages/member/loginpage"));

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
    ],
  };
}
