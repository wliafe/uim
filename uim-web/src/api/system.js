import fetch from "@/utils/request";
export const ApiGetCodeByEmail = (email) =>
  fetch("GET", "/api/v1/system/code/email?email=" + email);
export const ApiLoginByEmailCode = (data) =>
  fetch("POST", "/api/v1/system/login/email/code", data);
export const ApiLogout = () => fetch("POST", "/api/v1/system/logout");
