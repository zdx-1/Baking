<!--首页-->
<template>
  <!-- 1.设置首页版心内容定宽居中 -->
  <div style="width: 1200px;margin: 0 auto;">
    <!--2.添加首页轮播图 -->
    <el-carousel>
      <!--3.每个轮播项就是一张图片，可以利用v-for拼接不同的banner1.jpg banner2.jpg banner3.jpg -->
      <el-carousel-item v-for="item in 3">
        <img :src="'/imgs/banner'+item+'.jpg'" style="width: 100%;height: 100%;">
      </el-carousel-item>
    </el-carousel>
    <!-- 4."烘焙食谱"部分开始 -->
    <el-row :gutter="10" style="background-color:#fff;margin-top:10px;">
      <el-col :span="3">
        <p style="font-size:28px;margin:10px;">烘焙食谱</p>
      </el-col>
      <el-col :span="21">
        <!--default-active="1"表示模式激活的是第1项"全部" 现在要改成首页"0"-->
        <!--给首页二级分类内容 绑定选择事件 注意方法名没有小括号 @select="selectRecipe" 这个组件会自动传递过去参数-->
        <el-menu mode="horizontal" active-text-color="orange" default-active="0" @select="selectRecipe">
          <el-menu-item index="0">全部</el-menu-item>
          <el-menu-item v-for="c in recipeCatArr" :index="c.id">{{c.name}}</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in recipeArr" style="margin-top: 10px;">
        <el-card>
          <!--7.1给每张卡片的图片与描述增加router-link跳转到详情页/detail -->
          <!--:to="'/detail?id='+item.id" 出现变量 属性绑定 改完在首页点击跳到详情页 地址栏后有id值了-->
          <router-link :to="'/detail?id='+item.id" style="text-decoration: none;color:#333;">
            <img :src="'http://localhost:8080'+item.imgUrl" style="width:100%;height:150px;">
            <p style="height: 40px;text-decoration: none;">{{item.title}}</p>
          </router-link>
          <!--完成图片下的作者与分类-->
          <el-row :gutter="10">
            <el-col :span="4">
              <!--el-avatar是专门用来设置头像的标签 头像是圆形的-->
              <el-avatar :src="'http://localhost:8080'+item.userImgUrl"></el-avatar>
            </el-col>
            <el-col :span="16" style="font-size:20px;line-height: 40px;">{{ item.nickname }}</el-col>
            <el-col :span="4" style="line-height: 40px;">{{ item.categoryName }}</el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <div style="text-align: center;margin:10px;">
      <!-- 8.2.1点击此按钮跳转至listView列表页面 -->
      <el-button @click="router.push('/list')">点击加载更多</el-button>
    </div>
    <!-- 4."烘焙食谱"部分结束 -->
    <!-- 5."烘焙视频"部分开始 -->
    <el-row :gutter="10" style="background-color:#fff;margin-top:10px;">
      <el-col :span="3">
        <p style="font-size:28px;margin:10px;">烘焙视频</p>
      </el-col>
      <el-col :span="21">
        <el-menu mode="horizontal" active-text-color="orange" default-active="0" @select="selectVideo">
          <el-menu-item index="0">全部</el-menu-item>
          <el-menu-item v-for="c in videoCatArr" :index="c.id">{{c.name}}</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in videoArr" style="margin-top: 10px;">
        <el-card>
          <router-link :to="'/detail?id='+item.id" style="text-decoration: none;color:#333;">
            <img :src="'http://localhost:8080'+item.imgUrl" style="width:100%;height:150px;">
            <p style="height: 40px;text-decoration: none;">{{item.title}}</p>
          </router-link>
          <el-row :gutter="10">
            <el-col :span="4">
              <el-avatar :src="'http://localhost:8080'+item.userImgUrl"></el-avatar>
            </el-col>
            <el-col :span="13" style="font-size:20px;line-height: 40px;">{{ item.nickname }}</el-col>
            <el-col :span="7" style="line-height: 40px;">{{ item.categoryName }}</el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <div style="text-align: center;margin:10px;">
      <!-- 8.2.2点击此按钮跳转至listView列表页面 -->
      <el-button @click="router.push('/list')">点击加载更多</el-button>
    </div>
    <!-- 5."烘焙视频"部分结束 -->
    <!-- 6."行业资讯"部分开始 -->
    <el-row :gutter="10" style="background-color:#fff;margin-top:10px;">
      <el-col :span="3">
        <p style="font-size:28px;margin:10px;">行业资讯</p>
      </el-col>
      <el-col :span="21">
        <el-menu mode="horizontal" active-text-color="orange" default-active="0" @select="selectInfo">
          <el-menu-item index="0">全部</el-menu-item>
          <el-menu-item v-for="c in infoCatArr" :index="c.id">{{c.name}}</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in infoArr" style="margin-top: 10px;">
        <el-card>
          <router-link :to="'/detail?id='+item.id" style="text-decoration: none;color:#333;">
            <img :src="'http://localhost:8080'+item.imgUrl" style="width:100%;height:150px;">
            <p style="height: 40px;text-decoration: none;">{{item.title}}</p>
          </router-link>
          <el-row :gutter="10">
            <el-col :span="4">
              <!--el-avatar是专门用来设置头像的标签 头像是圆形的-->
              <el-avatar :src="'http://localhost:8080'+item.userImgUrl"></el-avatar>
            </el-col>
            <el-col :span="13" style="font-size:20px;line-height: 40px;">{{ item.nickname }}</el-col>
            <el-col :span="7" style="line-height: 40px;">{{ item.categoryName }}</el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <div style="text-align: center;margin:10px;">
      <!-- 8.2.3点击此按钮跳转至listView列表页面 -->
      <el-button @click="router.push('/list')">点击加载更多</el-button>
    </div>
    <!-- 6."行业资讯"部分结束 -->
  </div>
</template>

<script setup>
import router from "@/router";
import {onMounted, ref} from "vue";
import axios from "axios";
import qs from "qs";
//1.定义三个二级分类数组
const recipeCatArr = ref([]);
const videoCatArr = ref([]);
const infoCatArr = ref([]);
//2.立即执行
onMounted(()=>{
  //3.1发请求获取食谱二级分类
  axios.get('http://localhost:8080/v1/categories/1/sub').then((response)=>{
    if(response.data.code==2001){
      recipeCatArr.value = response.data.data;
    }
  })
  //3.2发请求获取视频二级分类
  axios.get('http://localhost:8080/v1/categories/2/sub').then((response)=>{
    if(response.data.code==2001){
      videoCatArr.value = response.data.data;
    }
  })
  //3.3发请求获取资讯二级分类
  axios.get('http://localhost:8080/v1/categories/3/sub').then((response)=>{
    if(response.data.code==2001){
      infoCatArr.value = response.data.data;
    }
  })
  //6.请求三种类型下的全部数据 全部的分类id是0
  loadContents(1,0);
  loadContents(2,0);
  loadContents(3,0);
})
//4.定义三个内容数组
const recipeArr = ref([]);
const videoArr = ref([]);
const infoArr = ref([]);
//5.定义加载内容的方法
const loadContents = (type,categoryId)=>{
  let data = qs.stringify({type:type,categoryId:categoryId});//将请求参数转为查询字符串格式
  axios.get('http://localhost:8080/v1/contents/index?'+data)//get请求的参数要 ? 拼接
      .then((response)=>{
        if(response.data.code==2001){
          //三合一:根据不同的type值将响应内容赋值给不同的数组
          switch (type){
            case 1:
              recipeArr.value = response.data.data;break;
            case 2:
              videoArr.value = response.data.data;break;
            case 3:
              infoArr.value = response.data.data;break;
          }
        }
      })
}
//6.选择食谱二级分类 时触发此函数
//index 是二级分类的id :index="c.id"
const selectRecipe = (index)=>{
  //食谱的type是1
  loadContents(1,index)
}
//选择视频二级分类 时触发此函数
const selectVideo = (index)=>{
  loadContents(2,index)
}
//选择资讯二级分类 时触发此函数
const selectInfo = (index)=>{
  loadContents(3,index)
}

//7.定义轮播图数组
const bannerArr = ref([]);
onMounted(()=>{
  //请求轮播图数据
  axios.get('http://localhost:8080/v1/banners/index').then((response)=>{
    if(response.data.code==2001){
      bannerArr.value = response.data.data;
    }
  })
})
</script>

<style scoped>
p {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>