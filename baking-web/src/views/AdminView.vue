<!--共有头部与左侧侧边栏 右侧是可变区域-->
<template>
  <div class="common-layout">
    <el-container>
      <!--1.编辑共有的头部-->
      <el-header style="background-color:#368;line-height:60px;">
        <h1 style="color:#fff;font-size:30px;margin:0;">
          烘焙坊后台管理系统
          <!--使用右浮动,先浮动的在最右边,所以先写按钮再写欢迎文字-->
          <el-button style="float:right;margin:14px 0 0 5px;" @click="logout()">退出登录</el-button>
          <span style="font-size:20px;float:right;">
            欢迎{{ user.nickname }}回来!
          </span>
        </h1>
      </el-header>
      <el-container>
        <!--2.编辑共有的左侧侧边栏-->
        <el-aside width="200px">
          <!--添加router表示当选择对应导航项时,会根据index保存的路径,跳转到对应的页面-->
          <!--style="height:100%;让左侧侧边栏与右侧内容的高度一样高-->
          <el-menu router active-text-color="orange" default-active="/admin/user" style="height:100%;">
            <!--在侧边栏上添加一个logo图片-->
            <img src="/imgs/icon.png" style="width: 150px;margin:20px;">
            <el-menu-item index="/admin/user">
              <el-icon><User/></el-icon>用户管理
            </el-menu-item>
            <el-menu-item index="/admin/banner">
              <el-icon><Picture/></el-icon>轮播图管理
            </el-menu-item>
            <el-menu-item index="/admin/content">
              <el-icon><ChatDotSquare/></el-icon>内容管理
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <!--3.注意:右侧主体内容要设置可变区域!-->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup>
import {ref} from "vue";
import router from "@/router";

const user = ref(localStorage.user?JSON.parse(localStorage.user):null)
const logout = ()=>{
  if(confirm('您确认退出登录吗?')){
    localStorage.clear();
    router.push('/')
  }
}
</script>

<style scoped>

</style>