import Cookies from "js-cookie";
export function setCookie(label, value, cookieExpires) {
  Cookies.set(label, JSON.stringify(value), { expires: cookieExpires });
}
export function getCookie(label) {
  const value = Cookies.get(label);
  if (value) return JSON.parse(value);
  else return false;
}
export const delCookie = (label) => Cookies.remove(label);
