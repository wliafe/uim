import { createRouter, createWebHistory } from "vue-router";
import { setCookie, getCookie } from "@/utils/cookie";
import type { MyResponse } from "@/utils/types";
import { ApiGetUserByToken } from "@/api/login";
import { getToken, removeToken } from "@/utils/auth";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: () => import("@/views/Login.vue"),
      redirect: "/login",
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/views/login/Login.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () => import("@/views/login/Register.vue"),
        },
      ],
    },
    {
      path: "/home",
      name: "home",
      component: () => import("@/views/Home.vue"),
      children: [
        {
          path: "account",
          name: "account",
          component: () => import("@/views/layout/pages/Account.vue"),
        },
      ],
    },
  ],
});

async function testToken(token: any): Promise<boolean> {
  if (token) {
    const user: MyResponse = getCookie("user");
    if (user) return true;
    else {
      const user = await ApiGetUserByToken();
      if (user) {
        setCookie("user", user);
        return true;
      } else {
        removeToken();
        return false;
      }
    }
  } else return false;
}

router.beforeEach(async (to, from, next) => {
  const token: any = getToken();
  if (to.path == "/login" || to.path == "/register") {
    if (await testToken(token)) next("/home");
    else next();
  } else {
    if (await testToken(token)) next();
    else next("/login");
  }
});

export default router;
