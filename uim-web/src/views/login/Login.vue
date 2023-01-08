<template>
  <el-form ref="rulesRef" :model="form1" :rules="rules" v-if="loginchoice == 'password'">
    <el-form-item prop="user">
      <el-input
        v-model="form1.user"
        placeholder="用户名/手机号/邮箱"
        size="large"
        :autocomplete="autocomplete"
      />
    </el-form-item>
    <el-form-item prop="key" v-if="loginchoice == 'password'">
      <el-input
        type="password"
        v-model="form1.key"
        placeholder="密码"
        size="large"
        :autocomplete="autocomplete"
      />
    </el-form-item>
    <div class="login-password">
      <el-checkbox v-model="form1.autocomplete" label="记住密码" />
      <el-link :underline="false" @click="loginchoice = 'code'">验证码登录</el-link>
    </div>
    <el-form-item>
      <el-button class="login-button" type="primary" @click="login(rulesRef, form1)">
        登录
      </el-button>
    </el-form-item>
  </el-form>
  <el-form ref="rulesRef" :model="form2" :rules="rules" v-if="loginchoice == 'code'">
    <el-form-item prop="user">
      <el-input
        v-model="form2.user"
        placeholder="邮箱"
        size="large"
        :autocomplete="autocomplete"
      />
    </el-form-item>
    <el-form-item prop="key">
      <el-input class="input-code" v-model="form2.key" placeholder="验证码" size="large">
        <template #append>
          <el-button @click="getCode(rulesRef, form2.user)">获取验证码</el-button>
        </template>
      </el-input>
    </el-form-item>
    <div class="login-code">
      <el-link :underline="false" @click="loginchoice = 'password'"> 密码登录 </el-link>
    </div>
    <el-form-item>
      <el-button class="login-button" type="primary" @click="login(rulesRef, form2)">
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
import { codeEmail, loginEmailCode } from "@/api/login";
import type { FormInstance, FormRules } from "element-plus";
import type { LoginData, LoginForm, MyResponse } from "@/utils/types";
import { setToken } from "@/utils/auth";
const autocomplete = ref<boolean>(false);
const loginchoice = ref<string>("password");
const rulesRef = ref<FormInstance>();
const form1 = reactive<LoginForm>({
  user: "",
  key: "",
  autocomplete: false,
});
const form2 = reactive<LoginForm>({
  user: "",
  key: "",
  autocomplete: false,
});
const rules = reactive<FormRules>({
  user: [{ required: true, message: "用户名不能为空", trigger: "blur" }],
  key: [{ required: true, message: "该处不能为空", trigger: "blur" }],
});
function login(formRef: FormInstance | undefined, form: LoginForm | undefined) {
  if (!formRef || !form) return;
  formRef.validate(async (valid, fields) => {
    if (valid) {
      autocomplete.value = form.autocomplete;
      const data: LoginData = {
        email: form.user,
        code: form.key,
      };
      const response: MyResponse | null = await loginEmailCode(data);
      if (response) setToken(response.data.token);
    }
  });
  formRef.resetFields();
}
async function getCode(formRef: FormInstance | undefined, email: string | undefined) {
  if (!formRef || !email) return;
  formRef.validate();
  await codeEmail(email);
}
</script>

<style scoped>
.login-button {
  width: 300px;
}
.input-code {
  width: 300px;
}
.login-code {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.login-footer,
.login-password {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
