<template>
  <el-menu router mode="horizontal" :ellipsis="false" @select="handleSelect">
    <div class="icon-menu" @click="menuClick">
      <div class="icon-menu" v-if="isCollapse.collapse"><IconMenuExpand /></div>
      <div class="icon-menu" v-else><IconMenuCollapse /></div>
    </div>
    <div class="flex-grow" />
    <el-sub-menu index="1">
      <template #title>管理人员</template>
      <el-menu-item index="/account">个人信息</el-menu-item>
      <el-menu-item index="1-2">切换账号</el-menu-item>
      <el-menu-item index="1-3">退出</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
import IconMenuCollapse from "@/components/icons/MenuCollapseIcon.vue";
import IconMenuExpand from "@/components/icons/MenuExpandIcon.vue";
import { useCollapseStore } from "@/stores/collapse";
const isCollapse = useCollapseStore();
function menuClick() {
  isCollapse.collapse = !isCollapse.collapse;
}
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.el-menu {
  height: 50px;
}
.icon-menu {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
}
.flex-grow {
  flex-grow: 1;
}
</style>
