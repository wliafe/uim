<template>
  <el-form :model="formInline" class="demo-form-inline">
    <el-form-item>
      <el-input
        v-model="formInline.email"
        placeholder="邮箱"
        size="large"
        :prefix-icon="UserFilled"
      />
    </el-form-item>
    <el-form-item>
      <el-input
        type="password"
        v-model="formInline.password"
        placeholder="密码"
        size="large"
        :prefix-icon="Unlock"
      />
    </el-form-item>
    <el-checkbox v-model="checked1" label="记住密码" size="large" />
    <el-form-item>
      <el-button class="el-button" type="primary" @click="onSubmit">登录</el-button>
    </el-form-item>
    <div>
      <span>忘记密码？</span>
      <RouterLink to="/register">
        <span style="float: right">注册</span>
      </RouterLink>
    </div>
  </el-form>
</template>
<script setup>
import { reactive } from "vue";
import { UserFilled, Unlock } from "@element-plus/icons-vue";

const formInline = reactive({
  email: "",
  password: "",
});

function onSubmit() {
  alert("submit!");
}
</script>

<style scoped>
.el-button {
  width: 300px;
}
</style>
