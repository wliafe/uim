<template>
  <el-menu
    default-active="1"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-radio-group style="margin-bottom: 20px">
      <el-radio-button :label="false">expand</el-radio-button>
      <el-radio-button :label="true">collapse</el-radio-button>
    </el-radio-group>
    <div class="flex-grow" />
    <el-sub-menu index="1">
      <template #title>管理人员</template>
      <RouterLink to="/account">
        <el-menu-item index="1-1">个人信息</el-menu-item>
      </RouterLink>
      <el-menu-item index="1-2">切换账号</el-menu-item>
      <el-menu-item index="1-3">退出</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}
</style>
