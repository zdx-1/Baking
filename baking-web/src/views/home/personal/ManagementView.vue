<template>
  <!--有多个真实的一级分类数据 可以选择 默认显示第1个  -->
  <el-radio-group v-model="type" @change="loadContents()">
    <el-radio-button v-for="c in catTypeArr" :label="c.type">{{c.name}}</el-radio-button>
    <!--    <el-radio-button label="1">烘焙食谱</el-radio-button>-->
    <!--    <el-radio-button label="2">烘焙视频</el-radio-button>-->
    <!--    <el-radio-button label="3">行业资讯</el-radio-button>-->
  </el-radio-group>
  <!--点击发布内容按钮跳转到发布页面 注意导入import router from '@/router';-->
  <el-button type="primary" style="margin-left:20px;margin-top:7px;" @click="router.push('/personal/post')">
    发布内容
  </el-button>
  <el-table :data="arr">
    <el-table-column type="index" label="编号" width="80" align="center"></el-table-column>
    <el-table-column prop="title" label="标题" width="290" align="center"></el-table-column>
    <el-table-column prop="categoryName" label="分类" width="80" align="center"></el-table-column>
    <el-table-column prop="viewCount" label="浏览量" width="80" align="center"></el-table-column>
    <el-table-column prop="commentCount" label="评论量" width="80" align="center"></el-table-column>
    <el-table-column prop="createTime" label="发布时间" width="120" align="center"></el-table-column>
    <el-table-column label="操作" align="center">
      <template #default="scope">
        <el-button-group>
          <el-button type="success" size="small" @click="edit(scope.row)">编辑</el-button>
          <el-button type="danger" @click="del(scope.$index,scope.row)" size="small">删除</el-button>
        </el-button-group>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import router from '@/router';
import axios from "axios";
import qs from "qs";

//跳转到指定内容的发布页面进行编辑
const edit = (c)=>{
  router.push('/personal/post?id='+c.id);
}

const del = (i,c)=>{
  if (confirm("您确认删除此内容吗?")){
    axios.post('http://localhost:8080/v1/contents/'+c.id+'/delete').then((response)=>{
      if(response.data.code==2001){
        arr.value.splice(i,1);
        ElMessage.success("删除完成!");
      }
    })
  }
}

const arr = ref([]);//将假数据数组arr注释掉,定义一个同名空数组
const type = ref('1');//创建响应式变量type表示类型,默认为烘焙食谱
const catTypeArr = ref([]);

//立即执行
onMounted(()=>{
  //请求一级分类数据,并存入类型数组中
  axios.get('http://localhost:8080/v1/categories/type').then((response)=>{
    if(response.data.code==2001){
      catTypeArr.value = response.data.data;
    }
  })

  //把之前这里的代码剪切到loadContents(),调用loadContents()获取内容数据即可
  //只要切换了类型，就重新请求修改内容
  loadContents()
})

const loadContents = ()=>{
  //获取当前用户数据
  let user = localStorage.user?JSON.parse(localStorage.user):null;
  //准备发请求时需要用到的数据 当前用户指定类型的内容
  let data = qs.stringify({userId:user.id,type:type.value});
  //向后端发请求拿到真实内容数据
  //注意:这里是get请求,参数以 ?拼接 ?后面的是查询字符串  如果是post请求,没有?是(url,data)
  axios.get('http://localhost:8080/v1/contents/management?'+data).then((response)=>{
    if(response.data.code==2001){
      arr.value = response.data.data;
    }
  })
}
</script>

<style scoped>

</style>