import { getToken } from "@/utils/auth";
import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: () => import("@/views/LoginView.vue"),
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
      component: () => import("@/views/HomeView.vue"),
      children: [
        {
          path: "account",
          name: "account",
          component: () => import("@/views/layout/pages/AccountPage.vue"),
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  const token: string = getToken();
  if (to.path == "/login" || to.path == "/register") {
    if (token) next("/home");
    else next();
  } else {
    if (token) next();
    else next("/login");
  }
});

export default router;
