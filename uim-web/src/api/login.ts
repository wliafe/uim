import type { Login, Register } from "@/utils/types";
import fetch from "@/utils/request";
export const codeEmail = (email: string) =>
  fetch("GET", "/sys/code/email?email=" + email);
export const loginEmailCode = (data: Login) =>
  fetch("POST", "/sys/login/email/code", data);
export const registerEmail = (data: Register) =>
  fetch("POST", "/sys/register/email", data);
export const logout = () => fetch("POST", "/sys/logout", null, true);
