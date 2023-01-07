export interface Request {
  method: string;
  headers: {
    "Content-Type": "application/json";
    token?: string;
  };
  body?: any;
}
export interface Login {
  phone?: string;
  email?: string;
  password?: string;
  code?: string;
}
export interface Register {
  phone?: string;
  email?: string;
  roleId: string;
  code?: string;
}
