import { NavLink } from "react-router";

export default function MainPage() {
  return (
    <div className=" text-3xl">
      <div className="text-3xl">
        <NavLink to={"/about"}>About</NavLink>
      </div>
            <div>Main Page</div>
    </div>
  );
}
