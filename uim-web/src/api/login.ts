import type { LoginData, RegisterData } from "@/utils/types";
import fetch from "@/utils/request";
export const codeEmail = (email: string) =>
  fetch("GET", "/sys/code/email?email=" + email);
export const loginEmailCode = (data: LoginData) =>
  fetch("POST", "/sys/login/email/code", data);
export const registerEmail = (data: RegisterData) =>
  fetch("POST", "/sys/register/email", data);
export const logout = () => fetch("POST", "/sys/logout");
