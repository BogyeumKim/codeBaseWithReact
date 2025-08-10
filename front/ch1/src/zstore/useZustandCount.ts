import { current } from "@reduxjs/toolkit";
import { create } from "zustand";

export interface CountStore {
  current: number;
  amount: number;
  inc: () => void;
  dec: () => void;
  changeAmount: (num: number) => void;
}

const useZustandCount = create<CountStore>((set, get) => {
  return {
    current: 13,
    amount: 1,
    inc: () => {
      set({ current: get().current + 1 });
    },
    dec: () => {
      set({ current: get().current - 1 });
    },
    changeAmount: (num: number) => {
      set({ amount: num });
    },
  };
});

export default useZustandCount;
