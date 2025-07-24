import { useActionState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { login } from "../../slices/loginSlice";
interface LoginResult {
  email: string;
  signed: boolean;
}

const initState: LoginResult = {
  email: "",
  signed: false,
};

function LoginComponent() {
  const dispatch = useDispatch();

  const [state, action, isPending] = useActionState(
    async (state: LoginResult, formData: FormData): Promise<LoginResult> => {
      //2초간 delay
      await new Promise((resolve) => setTimeout(resolve, 2000));

      const email = formData.get("email") as string;
      const pw = formData.get("pw") as string;
      console.log("email ", email, "pw ", pw);

      return { email: email, signed: true };
    },
    initState
  );

  useEffect(() => {
    if (state.signed) {
      dispatch(login(state));
    }
  }, [state.signed]);
  return (
    <div className="border-2 border-sky-200 mt-10 m-2 p-4">
      {isPending && <div>로그인 처리중...</div>}
      <form action={action}>
        <div className="flex justify-center">
          <div className="text-4xl m-4 p-4 font-extrabold text-blue-500">
            Login Component
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-full p-6 text-left font-bold">Email</div>
            <input
              className="w-full p-6 rounded-r border border-solid border-neutral-500 shadow-md"
              name="email"
              type={"text"}
            />
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full flex-wrap items-stretch">
            <div className="w-full p-6 text-left font-bold">Password</div>
            <input
              className="w-full p-6 rounded-r border border-solid border-neutral-500 shadow-md"
              name="pw"
              type={"password"}
            />
          </div>
        </div>
        <div className="flex justify-center">
          <div className="relative mb-4 flex w-full justify-center">
            <div className="w-2/5 p-6 flex justify-center font-bold">
              <button
                type="submit"
                className="rounded p-4 w-36 bg-blue-500 text-xl text-white"
              >
                LOGIN
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}
export default LoginComponent;
