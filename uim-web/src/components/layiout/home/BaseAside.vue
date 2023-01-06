<template>
  <el-menu
    router
    default-active="/"
    :collapse="isCollapse.collapse"
    unique-opened="true"
    @open="handleOpen"
    @close="handleClose"
  >
    <el-menu-item index="/" class="home-logo">
      <el-icon><div class="logo" /></el-icon>
      <template #title><span style="color: black">用户信息管理系统</span></template>
    </el-menu-item>
    <el-menu-item index="/">
      <el-icon><House /></el-icon>
      <template #title>首页</template>
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><IconMenu /></el-icon>
        <span>内容管理</span>
      </template>
      <el-menu-item index="/">任务控制</el-menu-item>
      <el-menu-item index="/">视频监控</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="3">
      <template #title>
        <el-icon><Document /></el-icon>
        <span>信息管理</span>
      </template>
      <el-menu-item index="/">车组管理</el-menu-item>
      <el-menu-item index="/">急救人员管理</el-menu-item>
      <el-menu-item index="/">设备管理</el-menu-item>
      <el-menu-item index="/">志愿者管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
import { Document, Menu as IconMenu, House } from "@element-plus/icons-vue";
import { useCollapseStore } from "@/stores/collapse";
const isCollapse = useCollapseStore();
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.el-menu {
  height: 100%;
}
.home-logo {
  height: 50px;
}
.logo {
  width: 100%;
  height: 100%;
  background-image: url(@/assets/logo.svg);
  background-size: cover;
}
</style>
