import { computed } from "vue";
import { defineStore } from "pinia";
import { getCookie } from "@/utils/cookie";

export const useUserStore = defineStore("user", () => {
  const user = computed(() => getCookie("user").data);
  return { user };
});
