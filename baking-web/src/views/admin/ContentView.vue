<!--内容管理页-->
<template>
  <!-- 2.编写表格 -->
  <el-table :data="arr">
    <el-table-column type="index" label="编号" width="80" align="center"></el-table-column>
    <el-table-column prop="title" label="标题" width="290" align="center"></el-table-column>
    <el-table-column label="封面" width="100" align="center">
      <template #default="scope">
        <img :src="BASE_URL+scope.row.imgUrl" style="width:100%;">
      </template>
    </el-table-column>
    <el-table-column prop="brief" label="摘要" width="380" align="center"></el-table-column>
    <el-table-column prop="categoryName" label="分类" width="80" align="center"></el-table-column>
    <el-table-column prop="viewCount" label="浏览量" width="80" align="center"></el-table-column>
    <el-table-column prop="commentCount" label="评论量" width="80" align="center"></el-table-column>
    <el-table-column prop="createTime" label="发布时间" width="120" align="center"></el-table-column>
    <el-table-column label="操作" align="center">
      <template #default="scope">
        <el-button type="success" @click="edit(scope.row)">编辑</el-button>
        <el-button type="danger" @click="del(scope.$index,scope.row)">删除</el-button>
      </template>
    </el-table-column>

  </el-table>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";
import router from "@/router";
//版本1：可以服用编辑页为修改，但是修改完毕没有回到后台内容管理页，还在内容发布页
// const edit = (content)=>{
//   //跳转到对应内容的发布页面--复用发布页面当修改页面
//   router.push('/personal/post?id='+content.id);
// }
//版本2：从后台改回到后台，从发布页改回到发布页
const edit = (content)=>{
  //设置来源页面
  localStorage.from="admin";
  router.push('/personal/post?id='+content.id);
}
const del = (i,content)=>{
  if(confirm('您确认删除此内容吗?')){
    axios.post(BASE_URL+'/v1/contents/'+content.id+'/delete').then((response)=>{
      if(response.data.code==2001){
        arr.value.splice(i,1);
        ElMessage.success('删除成功!')
      }
    })
  }
}
//1.清除原来arr中的假数据
const arr = ref([]);
//2.发请求获取数据
onMounted(()=>{
  axios.get(BASE_URL+'/v1/contents/admin').then((response)=>{
    if(response.data.code==2001){
      arr.value = response.data.data
    }
  })
})
</script>

<style scoped>
</style>