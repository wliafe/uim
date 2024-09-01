import { ref } from "vue";
import { defineStore } from "pinia";

export const useCollapseStore = defineStore("collapse", () => {
  const collapse = ref<boolean>(false);
  return { collapse };
});
