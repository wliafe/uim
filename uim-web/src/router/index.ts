import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
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
          component: () => import("@/components/layiout/login/Login.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () => import("@/components/layiout/login/Register.vue"),
        },
      ],
    },
  ],
});

export default router;
