<!--列表页面-->
<template>
  <!-- 1.列表页面的版心 定宽居中 -->
  <div style="width: 1200px;margin: 0 auto;">
    <!-- 2.复制首页中有4张卡片的el-row即可    -->
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in contentArr" style="margin-top: 10px;">
        <el-card>
          <router-link :to="'/detail?id='+item.id" style="text-decoration: none;color:#333;">
            <img :src="BASE_URL+item.imgUrl" style="width:100%;height:150px;">
            <p style="height: 40px;">{{ item.title }}</p>
          </router-link>
          <el-row :gutter="10">
            <el-col :span="4">
              <el-avatar :src="BASE_URL+item.userImgUrl"></el-avatar>
            </el-col>
            <el-col :span="13" style="font-size:20px;line-height: 40px;">{{ item.nickname }}</el-col>
            <el-col :span="7" style="line-height: 40px;">{{ item.categoryName }}</el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import {onBeforeUpdate, onMounted, ref} from "vue";
import axios from "axios";

const contentArr = ref([])

const loadContents = ()=>{
  //判断是否包含wd 如果包含,是关键词搜索
  if(location.search.includes('wd')){
    let wd = new URLSearchParams(location.search).get('wd');
    axios.get('/v1/contents/'+wd+'/search').then((response)=>{
      if(response.data.code==2001){
        contentArr.value = response.data.data;
      }
    })
  }else{//反之是之前的流程 查询某一类型下的所有内容
    let type = new URLSearchParams(location.search).get('type');
    console.log(type);
    axios.get('/v1/contents/'+type+'/type').then((response)=>{
      if(response.data.code==2001){
        contentArr.value = response.data.data;
      }
    })
  }
}
//A->B
onMounted(()=>{
  loadContents();
})
//A->A
onBeforeUpdate(()=>{
  loadContents();
})
</script>

<style scoped>

</style>