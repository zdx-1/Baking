<!--轮播图管理页面-->
<template>
  <el-table :data="arr" >
    <el-table-column type="index" label="编号" width="120px" align="center"></el-table-column>
    <el-table-column label="轮播图" width="450" align="center">
      <!-- #default="scope"固定写法 保存当前表格中的所有数据 -->
      <template #default="scope">
        <!--scope.row表示当前表格行对象-->
        <img :src="scope.row.imgUrl" style="width:100%">
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="发布时间" align="center"></el-table-column>
    <el-table-column label="操作"  align="center">
      <template #default="scope">
        <el-button type="danger" @click="del(scope.$index)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";
//删除之前的假数据
// const arr = ref([
//   {imgUrl:'/imgs/banner1.jpg',createTime:'2024/2/14'},
//   {imgUrl:'/imgs/banner2.jpg',createTime:'2024/2/14'},
//   {imgUrl:'/imgs/banner3.jpg',createTime:'2024/2/14'}
// ]);
//发请求获取后台管理轮播图的数据
const arr = ref([]);
onMounted(()=>{
  axios.get(BASE_URL+'/v1/banners/admin').then((response)=>{
    if(response.data.code==2001){
      arr.value = response.data.data;
    }
  })
})
const del = (i) => {
  if(confirm('您确认删除此轮播图吗?')){
    arr.value.splice(i,1);
    ElMessage.success('删除成功!')
  }
}
</script>

<style scoped></style>