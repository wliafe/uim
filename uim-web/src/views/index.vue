<template>
  <div class="common-layout">
    <el-container class="el-container-base">
      <el-main>
        <div class="project-name">用户信息管理系统</div>
        <br />
        <RouterView />
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout {
  display: flex;
  width: 100%;
  height: 100%;
  background-image: url(../assets/images/background.jpg);
}
.el-container-base {
  justify-content: center;
  align-items: center;
}
.el-main {
  position: absolute;
  background-color: white;
}
.project-name {
  font-size: 20px;
  text-align: center;
}
</style>
