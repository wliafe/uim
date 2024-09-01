import fetch from "@/utils/request";

export const ApiRegisterByEmail = (data) =>
    fetch("POST", "/api/v1/user/register/email", data);
export const ApiGetUser = () => fetch("GET", "/api/v1/user/get");
