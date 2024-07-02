<!--个人中心共有的部分 左侧侧边栏-->
<template>
  <el-row :gutter="10" style="width:1200px;margin: 0 auto;">
    <el-col :span="4">
      <el-card style="height:100%;">
        <el-menu router active-text-color="orange" default-active="/personal/info" >
          <el-menu-item index="/personal/info">个人信息</el-menu-item>
          <el-menu-item index="/personal/management">稿件管理</el-menu-item>
        </el-menu>
      </el-card>
    </el-col>
    <!-- 右侧是可变区域 -->
    <el-col :span="20">
      <el-card>
        <router-view/>
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup>

</script>

<style scoped>

</style>