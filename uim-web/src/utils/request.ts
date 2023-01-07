import { getToken } from "@/utils/auth";
import type { Request } from "./types";
const BASE_URL = import.meta.env.VITE_BASE_URL;

async function request(
  method: "GET" | "POST",
  url: string,
  data?: any,
  token?: boolean
): Promise<any> {
  var request: Request = {
    method: method,
    headers: {
      "Content-Type": "application/json",
    },
  };
  if (data != null && data != undefined) request.body = JSON.stringify(data);
  if (token == true) request.headers.token = getToken();
  return await fetch(BASE_URL + url).then((response) => response.json());
}

export default request;
