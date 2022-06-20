import { atom } from "recoil";

export const isDarkAtom = atom({
  key: "isDark",
  default: false, //true 다크 / false 밝은모드
});
