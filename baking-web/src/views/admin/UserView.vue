<!--用户管理页面-->
<template>
  <el-table :data="arr">
    <el-table-column type="index" label="编号" width="100px"></el-table-column>
    <el-table-column prop="username" label="用户名"></el-table-column>
    <el-table-column prop="nickname" label="昵称"></el-table-column>
    <el-table-column label="头像">
      <template #default="scope">
        <el-avatar :src="scope.row.imgUrl"></el-avatar>
      </template>
    </el-table-column>
    <el-table-column label="管理员">
      <template #default="scope">
        <el-switch v-model="scope.row.isAdmin" :active-value="1" :inactive-value="0" @change="changeAdmin(scope.row)"></el-switch>
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="创建时间"></el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="danger" @click="del(scope.$index,scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 点击按钮，dialogVisible=true，显示下方蒙版弹框 -->
  <el-button type="primary" @click="dialogVisible=true" style="width:100%;margin-top:15px;font-weight:bold;">添加用户</el-button>
  <!-- dialogVisible 布尔类型响应式变量 控制蒙版弹框是否显示 -->
  <el-dialog title="添加用户" v-model="dialogVisible" style="padding-right:60px;border-radius:10px;">
    <el-form label-width="100px">
      <el-form-item label="用户名">
        <el-input placeholder="用户名" v-model="user.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input placeholder="密码" v-model="user.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input placeholder="昵称" v-model="user.nickname"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;" @click="add()">添加</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";
import qs from "qs";

const dialogVisible = ref(false);
const user = ref({username:'',password:'',nickname:''})
const add = ()=>{
  dialogVisible.value=false;
  let data = qs.stringify(user.value);
  axios.post('/v1/users/reg',data).then((response)=>{
    if(response.data.code==2001){
      ElMessage.success('添加成功!');
      loadData();
      //需要重置user的属性,否则再次添加时输入框还是上次添加的数据 注意不能直接置为null,没属性插不进去值
      user.value={username:'',password:'',nickname:''};
    }else{
      ElMessage.error(response.data.msg);
    }
  })
}

const changeAdmin =(user)=>{
  //查询字符串:用户id与管理员状态
  let data = qs.stringify({id:user.id,isAdmin:user.isAdmin});
  axios.post('/v1/users/update',data).then((response)=>{
    if(response.data.code==2001){
      ElMessage.success('修改完成!');
    }
  })
}

const arr = ref([]);
const loadData = ()=>{
  axios.get('/v1/users/admin').then((response)=>{
    if(response.data.code==2001){
      arr.value = response.data.data;
    }
  })
}
onMounted(()=>{
  loadData();
})
const del = (i,user)=>{
  if(confirm('您确认要删除'+user.nickname+"吗?")){
    axios.post('/v1/users/'+user.id+'/delete').then((response)=>{
      if(response.data.code==2001){
        ElMessage.success('删除成功!');
        arr.value.splice(i,1);
      }
    })
  }
}
</script>

<style scoped>

</style>