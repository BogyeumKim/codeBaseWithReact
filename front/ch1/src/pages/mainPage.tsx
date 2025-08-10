import { NavLink } from "react-router";
import useZustandCount from "../zstore/useZustandCount";

export default function MainPage() {
  const { current, dec, inc, changeAmount, amount } = useZustandCount();
  return (
    <div className=" text-3xl">
      <div className="text-3xl">
        <NavLink to={"/about"}>About</NavLink>
      </div>
      <div>Main Page</div>
      <div>{current}</div>
      <button onClick={inc}>INC</button>
      <button onClick={dec}>DEC</button>

      <div>
        <div onClick={() => changeAmount(5)}>5</div>
        <div onClick={() => changeAmount(10)}>10</div>
        <div>{amount}</div>
      </div>
    </div>
  );
}
