<!-- 1.找到Element-Plus官网Container布局中的"上-中-下"结构代码，复制到此处 -->
<template>
  <div class="common-layout">
    <el-container>
      <!--2.编辑头部 设置顶部条的高度与白色背景色 默认宽度撑满全屏-->
      <el-header style="height: 80px;background-color:#fff;">
        <!--3.layout分列布局 6-10-6-2共24列 作为内容要定宽居中-->
        <el-row :gutter="20" style="width: 1200px;margin: 0 auto;">
          <el-col :span="6">
            <!--4.添加logo图片 点击logo图片会自动跳转到 http://localhost:8080/index-->
            <router-link to="/">
              <img src="/imgs/icon.png" style="width: 200px;">
            </router-link>
          </el-col>
          <el-col :span="10">
            <!--5.头部导航栏 水平 激活时文字为橙色 注意handleSelect选择事件会触发下面的JS方法-->
            <el-menu mode="horizontal" active-text-color="orange" style="height: 70px;" @select="handleSelect">
              <el-menu-item index="1">食谱</el-menu-item>
              <el-menu-item index="2">视频</el-menu-item>
              <el-menu-item index="3">资讯</el-menu-item>
            </el-menu>
          </el-col>
          <el-col :span="6">
            <!--6.搜索框 -->
            <el-input placeholder="请输入您要搜索的内容" style="margin-top: 25px;" v-model="wd" @keydown.enter="search()">
              <!--在文本输入框中嵌入按钮-->
              <!--#append表示在输入框后追加内容，这里在输入框后追加了一个按钮-->
              <template #append>
                <!-- 注意:此处的按钮带图标，图标必须在下方的script中导入！-->
                <el-button :icon="Search" @click="search()"></el-button>
              </template>
            </el-input>
          </el-col>
          <el-col :span="2">
            <!-- 7.设置气泡卡片 v-if="user==null"用户为空(没登录显示)-->
            <!--popper-style="text-align:center;"给气泡卡片设置自定义样式 文字居中-->
            <el-popover title="欢迎访问烘焙坊" v-if="user==null" popper-style="text-align:center;">
              <!-- #reference触发Popover显示的HTML元素 -->
              <template #reference><el-icon size="25" style="margin-top: 30px;" color="#666"><User/></el-icon></template>
              <div style="text-align: center;">
                <!-- router.push('/reg')表示跳转到注册页面,注意此处的rouetr需要在JS中导入 -->
                <el-button type="info" size="small" @click="router.push('/reg')">注册</el-button>
                <el-button type="warning" size="small" @click="router.push('/login')">登录</el-button>
              </div>
            </el-popover>
            <!-- 设置气泡卡片2 v-else反之用户不为空(登录后显示)-->
            <el-popover v-else :title="'欢迎'+user.nickname+'回来'" :width="200" popper-style="text-align:center;">
              <template #reference><el-icon size="25" style="margin-top: 30px;" color="#666"><User/></el-icon></template>
              <el-avatar :src="BASE_URL+user.imgUrl"></el-avatar>
              <div style="text-align: center;">
                <el-button type="success" size="small" @click="router.push('/personal')">个人中心</el-button>
                <el-button type="danger" size="small" @click="logout()">退出登录</el-button>
                <br>
                <el-button v-if="user.isAdmin==1"
                           type="primary" style="width:158px;margin-top:8px;" size="small"
                           @click="router.push('/admin')">后台管理页面</el-button>
              </div>
            </el-popover>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <!--10.中间部分是可变区域-->
        <router-view/>
      </el-main>
      <!--8.设置页面的底部-->
      <el-footer style="background-color: rgb(50,50,50);color:#666;height: 300px;padding:50px;text-align:center;">
        <!--9.设置版心 定宽且居中-->
        <div style="width: 1200px;margin: 0 auto;">
          <el-row :gutter="10">
            <!--9.1 底部左侧区域 -->
            <el-col :span="8">
              <img src="/imgs/icon.png">
              <p>教程灵感就看烘焙坊</p>
              <p>烘焙行业网络社区平台</p>
              <p>全国成百上千个职位等你来</p>
            </el-col>
            <!--9.2 底部中间区域 三列信息 注意此处内容有单独的CSS内部样式 id="footer-center"-->
            <el-col :span="8" id="footer-center">
              <!--继续嵌套还是从row开始 -->
              <el-row :gutter="10">
                <el-col :span="8">
                  <h3>关于我们</h3>
                  <p>烘焙学院</p>
                  <p>烘焙食谱</p>
                  <p>分类信息</p>
                  <p>求职招聘</p>
                  <p>社区交流</p>
                </el-col>
                <el-col :span="8">
                  <h3>服务与支持</h3>
                  <p>联系我们</p>
                  <p>广告投放</p>
                  <p>用户协议</p>
                  <p>友情链接</p>
                  <p>在线反馈</p>
                </el-col>
                <el-col :span="8">
                  <h3>底部导航</h3>
                  <p>Archiver</p>
                  <p>手机版</p>
                  <p>小黑屋</p>
                </el-col>
              </el-row>
            </el-col>
            <!--9.3 底部右侧区域 -->
            <el-col :span="8">
              <p style="font-size: 60px;color:#666;margin: 10px 0;">
                <span style="color:orange;">烘焙</span>坊
              </p>
              <p>烘焙行业社区平台</p>
            </el-col>
          </el-row>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
//此处导入的是搜索框中按钮上的放大镜图标
import {Search} from '@element-plus/icons-vue';
//注意:上面注册按钮跳转到/reg需要在此处导入
import router from '@/router';
import {onMounted, ref} from "vue";
import axios from "axios";

const wd = ref('');
const search = ()=>{
  //携带搜索关键词到列表页
  router.push('/list?wd='+wd.value);
}

//1.获取保存的用户信息
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
//2.实现退出功能
const logout=()=>{
  if(confirm("您确认退出登录吗?")){
    localStorage.clear();
    user.value=null;//也让页面跟着变
    //跳到首页
    router.push('/');
  }
}

//给顶部栏的导航项被选择时添加跳转功能 跳转到列表页面
//目前本方法的两个参数都用不上
const handleSelect = (key,keyPath)=>{
  router.push('/list?type='+key);
}


</script>

<style>
/*此处设置的是页脚中间部分文字标题颜色为白色 文字内容有外间距*/
footer h3 {
  color: #fff;
}

#footer-center p {
  margin: 5px 0;
}
</style>