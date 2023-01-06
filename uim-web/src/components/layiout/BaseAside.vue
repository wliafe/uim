<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    unique-opened="true"
    @open="handleOpen"
    @close="handleClose"
  >
    <RouterLink to="/">
      <el-menu-item index="1">
        <el-icon><House /></el-icon>
        <template #title>首页</template>
      </el-menu-item>
    </RouterLink>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><IconMenu /></el-icon>
        <span>内容管理</span>
      </template>
      <el-menu-item index="2-1">任务控制</el-menu-item>
      <el-menu-item index="2-2">视频监控</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="3">
      <template #title>
        <el-icon><Document /></el-icon>
        <span>信息管理</span>
      </template>
      <el-menu-item index="3-1">车组管理</el-menu-item>
      <el-menu-item index="3-2">急救人员管理</el-menu-item>
      <el-menu-item index="3-3">设备管理</el-menu-item>
      <el-menu-item index="3-4">志愿者管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
import { Document, Menu as IconMenu, House } from "@element-plus/icons-vue";
import { RouterLink } from "vue-router";
import { useCollapseStore } from "@/stores/collapse";
const collapse = useCollapseStore();
const isCollapse = collapse.collapse;
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 170px;
  height: 100%;
}
.el-menu--collapse {
  height: 100%;
}
</style>
