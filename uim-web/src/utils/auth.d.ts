import { getToken, setToken, removeToken } from "@/utils/auth";
declare module "./auth.js" {
  export function getToken(): any;
  export function setToken(): any;
  export function removeToken():any;
}
