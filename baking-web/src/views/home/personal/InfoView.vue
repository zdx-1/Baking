<!--个人中心页面-->
<!--头像上传组件先暂时不做,等其它做完再来做这个-->
<template>
  <el-form label-width="100px" style="width: 500px;">
    <el-form-item label="头像">
      <!--头像上传的html代码是从Element-Plus官网的"Form表单组件"=>"Upload上传"处复制的-->
      <!--头像上传开始 action设置上传地址 limit="1"限制上传数量为1 name="file"设置上传参数名称,服务器就需要使用相同的名称-->
      <el-upload
          v-model:file-list="fileList"
          action="http://localhost:8080/v1/upload"
          limit="1"
          name="file"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
      >
        <el-icon><Plus /></el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" />
      </el-dialog>
      <!--头像上传结束-->
    </el-form-item>
    <el-form-item label="昵称">
      <el-input placeholder="请输入昵称" v-model="user.nickname"></el-input>
    </el-form-item>
    <el-form-item label="用户名">
      <!--给用户名输入框设置了禁用(只能看不能改),所以使用属性绑定就可以不用设置双向绑定-->
      <el-input placeholder="请输入用户名" :value="user.username" disabled></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="save()">保存修改</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
//头像上传的JS代码是从Element-Plus官网的"Form表单组件"=>"Upload上传"处复制的,复制script标签中所有的代码到此处
//lang="ts"表示此段代码是用TypeScript代码写的,TS是基于JS代码衍生出来的,所以我们需要改造下下面的代码
//这部分代码涉及到与后端交互，向服务器发请求并上传图片的相关内容,目前我们先不展开细讲,只要目前页面上有显示出来加号就可以
import { ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import qs from "qs";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";

//1.获取用户数据
const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
//2.实现保存方法
const save = ()=>{
  //先暂时不管修改头像的事，后面写，这里先改昵称
  let newUser = {id:user.value.id,nickname:user.value.nickname};
  //4.处理头像上传
  if(fileList.value.length>0){
    //得到图片路径
    let url = fileList.value[0].response.data;
    //console.log(url);//浏览器点击保存修改 控制台拿到/2024/02/15/4c106291-e46e-4f68-9ecf-4d5e631d58ff.png
    //将得到的图片路径装进newUser对象中：给到后端，数据库中的数据改掉
    newUser.imgUrl = url;
    //还要把图片路径放到user JS对象里,一会更新成功后存到localStorage中
    user.value.imgUrl = url;
  }
  let data = qs.stringify(newUser);
  axios.post('http://localhost:8080/v1/users/update',data).then((response)=>{
    if(response.data.code == 2001){
      ElMessage.success('修改完成!');
      //3.注意:修改完成后要更新localStorage里面的数据
      localStorage.user = JSON.stringify(user.value);
      //更新完毕后刷新下页面
      location.reload();
    }
  })
}

//响应式变量 用来保存头像数据 与头像上传控件双向绑定了 只要一上传头像 该数组里就有数据了
const fileList = ref([])

const dialogImageUrl = ref('')
const dialogVisible = ref(false)
//实现图片手动移除
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles)
}
//实现图片预览
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true
}
</script>

<style scoped>

</style>