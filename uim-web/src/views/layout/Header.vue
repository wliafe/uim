<template>
  <el-affix :offset="0.1">
    <el-menu router mode="horizontal" :ellipsis="false" @select="handleSelect">
      <div class="icon-menu" @click="menuClick">
        <el-icon :size="30" v-if="isCollapse.collapse"><Expand /></el-icon>
        <el-icon :size="30" v-if="!isCollapse.collapse"><Fold /></el-icon>
      </div>
      <div class="flex-grow" />
      <el-sub-menu index="1">
        <template #title>{{ user.user.nickName }}</template>
        <el-menu-item index="/home/account">个人信息</el-menu-item>
        <el-menu-item index="/login" @click="logout">切换账号</el-menu-item>
        <el-menu-item index="/login" @click="logout">退出</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </el-affix>
</template>

<script setup lang="ts">
import { ApiLogout } from "@/api/login";
import { useCollapseStore } from "@/stores/collapse";
import { removeToken } from "@/utils/auth";
import { Expand, Fold } from "@/icons";
import { useUserStore } from "@/stores/user";
const isCollapse = useCollapseStore();
const user = useUserStore();
function menuClick() {
  isCollapse.collapse = !isCollapse.collapse;
}
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
function logout() {
  ApiLogout();
  removeToken();
}
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
