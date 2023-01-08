<template>
  <div class="common-layout">
    <el-container class="el-container-base">
      <el-aside><BaseAside /></el-aside>
      <el-container>
        <el-header><BaseHeader /></el-header>
        <el-main><RouterView /></el-main>
        <el-footer><BaseFooter /></el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import BaseHeader from "@/views/layout/BaseHeader.vue";
import BaseAside from "@/views/layout/BaseAside.vue";
import BaseFooter from "@/views/layout/BaseFooter.vue";
</script>

<style scoped>
.common-layout {
  width: 100%;
  height: 100%;
}
.el-container-base {
  width: 100%;
  height: 100%;
}
.el-header {
  padding: 0px;
}
.el-aside {
  width: auto;
}
.el-footer{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
<style>
a {
  text-decoration: none;
}
</style>
