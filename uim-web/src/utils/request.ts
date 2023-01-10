import { getToken } from "@/utils/auth";
import { ElMessage } from "element-plus";
import type { MyResponse, method } from "./types";
const BASE_URL = import.meta.env.VITE_BASE_URL;

async function request(
  method: method,
  url: string,
  data?: any
): Promise<MyResponse | boolean> {
  const headers: HeadersInit = { "Content-Type": "application/json" };
  const token: string = getToken();
  if (token) headers.token = token;
  const request: RequestInit = {
    method: method,
    headers: headers,
  };
  if (data) request.body = JSON.stringify(data);
  const response: MyResponse = await fetch(BASE_URL + url, request).then(
    (response) => response.json()
  );
  if (response.code != 200) {
    ElMessage.error(response.message);
    return false;
  }
  return response;
}

export default request;
