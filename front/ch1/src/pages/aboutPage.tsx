import { NavLink } from "react-router";
import useCustomLogin from "../hooks/useCustomLogin";

export default function AboutPage() {
  const { loginStatus, moveToLoginReturn } = useCustomLogin();

  console.log("loginStatus", loginStatus);

  if (!loginStatus) {
    return moveToLoginReturn();
  }
  return (
    <div className="text-3xl">
      <div className="flex">
        <NavLink to={"/"}>Main</NavLink>
      </div>
      <div>About Page</div>
    </div>
  );
}
