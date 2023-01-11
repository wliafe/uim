<template>
  <el-form ref="formRef" :model="form" :rules="formRules">
    <el-form-item prop="user" v-if="loginChoice == 'password'">
      <el-input
        v-model="form.user"
        placeholder="用户名/手机号/邮箱"
        size="large"
        autocomplete="on"
      />
    </el-form-item>
    <el-form-item prop="key" v-if="loginChoice == 'password'">
      <el-input
        type="password"
        v-model="form.key"
        placeholder="密码"
        size="large"
        autocomplete="on"
      />
    </el-form-item>
    <el-form-item prop="user" v-if="loginChoice == 'code'">
      <el-input
        v-model="form.user"
        placeholder="邮箱"
        size="large"
        autocomplete="on"
      />
    </el-form-item>
    <el-form-item prop="key" v-if="loginChoice == 'code'">
      <el-input
        class="input-code"
        v-model="form.key"
        placeholder="验证码"
        size="large"
      />
      <el-button size="large" @click="GetCode(formRef)">获取验证码</el-button>
    </el-form-item>
    <div class="login-method">
      <el-link
        :underline="false"
        @click="LoginChange(formRef)"
        v-if="loginChoice == 'code'"
      >
        密码登录
      </el-link>
      <el-link
        :underline="false"
        @click="LoginChange(formRef)"
        v-if="loginChoice == 'password'"
      >
        验证码登录
      </el-link>
    </div>
    <el-form-item>
      <el-button class="login-button" type="primary" @click="login(formRef)">
        登录
      </el-button>
    </el-form-item>
  </el-form>
  <div class="login-footer">
    <el-link :underline="false">忘记密码？</el-link>
    <RouterLink to="/register">
      <el-link :underline="false">注册</el-link>
    </RouterLink>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from "vue";
import { ApiGetCodeByEmail, ApiLoginByEmailCode } from "@/api/login";
import { ElMessage, type FormInstance, type FormRules } from "element-plus";
import type { LoginData, MyResponse } from "@/utils/types";
import { setToken } from "@/utils/auth";
import router from "@/router";
const loginChoice = ref<string>("password");
const formRef = ref<FormInstance>();
const form = reactive({
  user: "",
  key: "",
});
const formRules = reactive<FormRules>({
  user: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
  key: [{ required: true, message: "该处不能为空", trigger: "blur" }],
});
function LoginChange(formRef: FormInstance | undefined) {
  if (!formRef) return;
  if (loginChoice.value == "code") loginChoice.value = "password";
  else loginChoice.value = "code";
  formRef.resetFields();
}
async function login(formRef: FormInstance | undefined) {
  if (!formRef) return;
  await formRef.validate(async (valid, field) => {
    if (valid) {
      const data: LoginData = {
        email: form.user,
        code: form.key,
      };
      const response = await ApiLoginByEmailCode(data);
      if (!response) return;
      setToken(response.data.token);
      ElMessage.success("登录成功");
      router.push("/home");
    } else console.log(field);
  });
  formRef.resetFields();
}
async function GetCode(formRef: FormInstance | undefined) {
  if (!formRef) return;
  await formRef.validateField("user", async (valid, field) => {
    if (valid) await ApiGetCodeByEmail(form.user);
    else console.log(field);
  });
}
</script>

<style scoped>
.login-button {
  width: 300px;
}

.input-code {
  width: 190px;
}

.login-method {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.login-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
