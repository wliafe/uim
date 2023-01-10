import Cookies from "js-cookie";
export function setCookie(label: string, value: any, cookieExpires?: any) {
  Cookies.set(label, JSON.stringify(value), { expires: cookieExpires });
}
export function getCookie(label: string) {
  const value = Cookies.get(label);
  if (value) return JSON.parse(value);
  else return false;
}
export const delCookie = (label: string) => Cookies.remove(label);
