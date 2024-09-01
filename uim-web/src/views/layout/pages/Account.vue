<template>
    <el-space wrap>
        <el-card class="box-card" style="width: 300px">
            <el-skeleton/>
            <br/>
            <el-skeleton style="--el-skeleton-circle-size: 100px">
                <template #template>
                    <el-skeleton-item variant="circle"/>
                </template>
            </el-skeleton>
        </el-card>
    </el-space>
</template>
<style scoped>
.box-card {
    height: 400px;
    margin: 20px;
}
</style>
