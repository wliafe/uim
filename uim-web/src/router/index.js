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
          path: "/account",
          name: "account",
          component: () => import("@/views/pages/AccountPage.vue"),
        },
      ],
    },
    {
      path: "/login",
      component: () => import("@/views/index.vue"),
      children: [
        {
          path: "/login",
          name: "login",
          component: () => import("@/views/layiout/Login.vue"),
        },
        {
          path: "/register",
          name: "register",
          component: () => import("@/views/layiout/Register.vue"),
        },
      ],
    },
  ],
});

export default router;
