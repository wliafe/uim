import { getToken, setToken, removeToken } from "@/utils/auth";
declare module "./auth.js" {
  export function getToken(): string;
  export function setToken(token: string): any;
  export function removeToken(): any;
}
