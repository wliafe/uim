<template>
  <div class="common-layout">
    <el-container class="el-container-base">
      <el-aside><Aside /></el-aside>
      <el-container>
        <el-header><Header /></el-header>
        <el-main><RouterView /></el-main>
        <el-affix position="bottom" :offset="60">
          <el-footer><Footer /></el-footer>
        </el-affix>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Header from "@/views/layout/Header.vue";
import Aside from "@/views/layout/Aside.vue";
import Footer from "@/views/layout/Footer.vue";
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
.el-aside {
  width: auto;
}
.el-header {
  height: 50px;
  padding: 0px;
}
.el-main {
  padding: 0px;
  height: 100%;
}
.el-footer {
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
