import Cookies from "js-cookie";
export const setCookie = (label: string, value: any, cookieExpires?: any) =>
  Cookies.set(label, value, { expires: cookieExpires });
export const getCookie = (label: string) => {
  const value = Cookies.get(label);
  if (value) return value;
  else return false;
};
export const delCookie = (label: string) => Cookies.remove(label);
