export type method =
  | "GET"
  | "POST"
  | "PUT"
  | "DELETE"
  | "OPTIONS"
  | "HEAD"
  | "PATCH";
export interface MyResponse {
  code: number;
  message: string;
  data?: any;
}
export interface LoginData {
  phone?: string;
  email?: string;
  password?: string;
  code?: string;
}
export interface RegisterData {
  phone?: string;
  email?: string;
  roleId: string;
  code?: string;
}
export interface LoginForm {
  user: string;
  key: string;
  autocomplete: boolean;
}
