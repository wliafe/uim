const TokenKey = "Admin-Token";
export function getToken(): any {
  return localStorage.getItem(TokenKey);
}
export function setToken(token: string): void {
  localStorage.setItem(TokenKey, token);
}
export function removeToken(): void {
  localStorage.removeItem(TokenKey);
}
