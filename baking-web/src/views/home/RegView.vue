<template>
  <!-- 注册页分为左右两侧 各占12列 -->
  <div style="width: 1200px;margin: 0 auto;">
    <el-row :gutter="10">
      <!--左侧内容为一张卡片-->
      <el-col :span="12">
        <el-card>
          <img src="/imgs/reg.png" style="width:100%;">
        </el-card>
      </el-col>
      <!--右侧内容为-->
      <el-col :span="12">
        <el-form label-width="80px" style="margin-top:100px;">
          <el-form-item>
            <h1 style="width: 515px;font-size:40px;position: relative;">立即注册
              <router-link to="/login" style="font-size:20px;color:#0aa1ed;position: absolute;right:0;text-decoration:none;">
                已有账号?立即登录
              </router-link>
            </h1>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input placeholder="请输入用户名" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input placeholder="请输入密码" type="password" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input placeholder="请输入昵称" v-model="user.nickname"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width:100%;" @click="reg()">注册</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
//1.给上面的3个输入框进行双向绑定,给按钮添加点击事件
import {ref} from "vue";
import qs from "qs";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";

//2.定义用户对象
const user = ref({username:'',password:'',nickname:''});
//3.创建注册函数
const reg = ()=>{
  //4.使用qs将JS对象转为查询字符串格式,注意导入qs
  let data = qs.stringify(user.value);
  //5.用axios向后端发请求,注意需要导入
  axios.post('/v1/users/reg',data).then((response)=>{
    if(response.data.code==2001){
      //6.注册成功显示消息并跳转至登录页
      ElMessage.success("注册成功!");
      router.push('/login');
    }else{
      //7.注册失败显示响应数据中的提示消息
      ElMessage.error(response.data.msg);
    }
  })
}
</script>

<style scoped></style>