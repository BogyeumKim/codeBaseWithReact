import { create } from "zustand";
import { loginPost } from "../api/memberApi";
import { removeCookie, setCookie } from "../util/cookieUtil";

export interface MemberInfo {
  email: string;
  nickname: string;
  accessToken: string;
  refreshToken: string;
  roleNames: string[];
}

const initState: MemberInfo = {
  email: "",
  nickname: "",
  accessToken: "",
  refreshToken: "",
  roleNames: [],
};

export interface MemberStore {
  member: MemberInfo;
  status: "" | "pending" | "fulfilled" | "error";
  login: (email: string, pw: string) => void;
  logout: () => void;
  save: (memberInfo: MemberInfo) => void;
}

const useZustandMember = create<MemberStore>((set, get) => {
  return {
    member: initState,
    status: "",
    login: async (email: string, pw: string) => {
      const data = await loginPost(email, pw);

      console.log("data", data);

      set({ member: data, status: "fulfilled" });

      const newState = { ...data, status: "fulfilled" };
      setCookie("member", JSON.stringify(newState), 1);
    },
    logout: () => {
      set({ member: { ...initState }, status: "" });
      removeCookie("member");
    },
    save: (memberInfo: MemberInfo) => {
      set({ member: memberInfo, status: "fulfilled" });
    },
  };
});

export default useZustandMember;
