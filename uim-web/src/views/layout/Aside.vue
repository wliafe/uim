<template>
    <el-menu
            router
            default-active="/home"
            :collapse="isCollapse.collapse"
            :unique-opened="true"
    >
        <el-menu-item index="/home" class="home-logo">
            <el-icon>
                <div class="logo"/>
            </el-icon>
            <template #title>
                <span style="color: black">{{ title }}</span>
            </template>
        </el-menu-item>
        <el-menu-item index="/home">
            <el-icon>
                <House/>
            </el-icon>
            <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu index="2">
            <template #title>
                <el-icon>
                    <Setting/>
                </el-icon>
                <span>系统管理</span>
            </template>
            <el-menu-item index="/">用户管理</el-menu-item>
            <el-menu-item index="/">角色管理</el-menu-item>
            <el-menu-item index="/">菜单管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
            <template #title>
                <el-icon :size="17">
                    <Monitor/>
                </el-icon>
                <span>系统监控</span>
            </template>
            <el-menu-item index="/">服务器监控</el-menu-item>
        </el-sub-menu>
    </el-menu>
</template>

<script setup>
import {Setting, House} from "@element-plus/icons-vue";
import {Monitor} from "@/icons";
import {useCollapseStore} from "@/stores/collapse";

const title = import.meta.env.VITE_TITLE;
const isCollapse = useCollapseStore();
</script>

<style scoped>
.el-menu {
    height: 100%;
}

.home-logo {
    height: 50px;
}

.logo {
    width: 100%;
    height: 100%;
    background-image: url(@/assets/logo.svg);
    background-size: cover;
}
</style>
