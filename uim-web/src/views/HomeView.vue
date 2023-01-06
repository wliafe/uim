<template>
  <div class="common-layout">
    <el-container class="el-container-base">
      <el-aside><BaseAside /></el-aside>
      <el-container>
        <el-header><BaseHeader /></el-header>
        <el-main><RouterView /></el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import BaseHeader from "@/components/layiout/BaseHeader.vue";
import BaseAside from "@/components/layiout/BaseAside.vue";
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
.el-footer {
  background-color: blanchedalmond;
}
</style>
