import type { LoginData, RegisterData } from "@/utils/types";
import fetch from "@/utils/request";
export const ApiGetCodeByEmail = (email: string) =>
  fetch("GET", "/sys/code/email?email=" + email);
export const ApiLoginByEmailCode = (data: LoginData) =>
  fetch("POST", "/sys/login/email/code", data);
export const ApiRegisterByEmail = (data: RegisterData) =>
  fetch("POST", "/sys/register/email", data);
export const ApiLogout = () => fetch("POST", "/sys/logout");
