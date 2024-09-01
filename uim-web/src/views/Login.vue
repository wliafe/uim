<template>
    <div class="common-layout">
        <el-container class="el-container-base">
            <el-main>
                <div class="project-name">{{ title }}</div>
                <br/>
                <RouterView/>
            </el-main>
        </el-container>
    </div>
</template>
<script setup>
const title = import.meta.env.VITE_TITLE;
</script>

<style scoped>
.common-layout {
    display: flex;
    width: 100%;
    height: 100%;
    background-image: url(@/assets/images/background.jpg);
    background-size: cover;
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
<style>
a {
    text-decoration: none;
}
</style>
