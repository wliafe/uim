<template>
    <el-form
            ref="registerRef"
            :model="register"
            :rules="registerRules"
            status-icon
    >
        <el-form-item prop="email">
            <el-input v-model="register.email" placeholder="邮箱" size="large"/>
        </el-form-item>
        <el-form-item prop="code">
            <el-input
                    class="input-message"
                    v-model="register.code"
                    placeholder="短信验证码"
                    size="large"
            >
                <template #append>
                    <el-button @click="getCode">获取邮箱验证码</el-button>
                </template>
            </el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input
                    type="password"
                    v-model="register.password"
                    placeholder="密码"
                    size="large"
            />
        </el-form-item>
        <el-form-item prop="checkPass">
            <el-input
                    type="password"
                    v-model="register.checkPass"
                    placeholder="确认密码"
                    size="large"
            />
        </el-form-item>
        <el-form-item>
            <el-button class="button-register" type="primary" @click="onSubmit">
                注册
            </el-button>
        </el-form-item>
        <div>
            <RouterLink to="/login">
                <span style="float: right">登录</span>
            </RouterLink>
        </div>
    </el-form>
</template>
<script setup>
import {ref, reactive} from "vue";
import {ApiGetCodeByEmail} from "@/api/system";

const registerRef = ref();
const register = reactive({
    email: "",
    code: "",
    password: "",
    checkPass: "",
});

function checkEmail(rule, value, callback) {
    if (!value) {
        return callback(new Error("请输入邮箱"));
    }
    callback();
}

function checkCode(rule, value, callback) {
    if (!value) {
        return callback(new Error("请输入验证码"));
    }
    callback();
}

function validatePassword(rule, value, callback) {
    if (value === "") {
        callback(new Error("请输入密码"));
    } else {
        if (register.checkPass !== "") {
            if (!registerRef.value) return;
            registerRef.value.validateField("checkPass", () => null);
        }
        callback();
    }
}

function validateCheckPass(rule, value, callback) {
    if (value === "") {
        callback(new Error("请再一次输入密码"));
    } else if (value !== register.password) {
        callback(new Error("密码不一致，请重新输入"));
    } else {
        callback();
    }
}

const registerRules = reactive({
    email: [{validator: checkEmail, trigger: "blur"}],
    code: [{validator: checkCode, trigger: "blur"}],
    password: [{validator: validatePassword, trigger: "blur"}],
    checkPass: [{validator: validateCheckPass, trigger: "blur"}],
});

async function onSubmit() {
    alert("submit!");
}

async function getCode() {
    await ApiGetCodeByEmail(register.email);
}
</script>

<style scoped>
.input-message {
    width: 300px;
}

.el-input-group__append {
    width: 130px;
}

.button-register {
    width: 300px;
}
</style>
