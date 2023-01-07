import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/home",
    },
    {
      path: "/home",
      name: "home",
      component: () => import("@/views/HomeView.vue"),
      children: [
        {
          path: "account",
          name: "account",
          component: () => import("@/components/pages/AccountPage.vue"),
        },
      ],
    },
    {
      path: "/",
      component: () => import("@/views/LoginView.vue"),
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/components/login/Login.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () => import("@/components/login/Register.vue"),
        },
      ],
    },
  ],
});

export default router;
