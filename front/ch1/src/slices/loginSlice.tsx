import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/memberApi";

export interface LoginInfo {
  email: string;
  nickname: string;
  accessToken: string;
  refreshToken: string;
  roleNames: string[];
  status: string;
}

const initState: LoginInfo = {
  email: "",
  nickname: "",
  accessToken: "",
  refreshToken: "",
  roleNames: [],
  status: "",
};

export const loginPostAsync = createAsyncThunk(
  "loginPostAsync",
  ({ email, pw }: { email: string; pw: string }) => {
    console.log("---------------loginPostAsync---------------------");
    console.log(email, pw);

    return loginPost(email, pw);
  }
);

const loginSlice = createSlice({
  name: "loginSlice",
  initialState: initState,
  reducers: {
    // login: (state, action) => {
    //   console.log("login .............", action.payload);
    //   return { email: action.payload.email };
    // },
    logout: (state, action) => {
      console.log("logout ............");
      //   return { email: "" };
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log(" === fulfilled ===");
        state.status = "fulfilled";
      })
      .addCase(loginPostAsync.pending, (state, action) => {
        console.log(" === pending ===");
        state.status = "pending";
      })
      .addCase(loginPostAsync.rejected, (state, action) => {
        console.log(" === rejected ===");
        state.status = "rejected";
      });
  },
});

export const { logout } = loginSlice.actions;
export default loginSlice.reducer;
