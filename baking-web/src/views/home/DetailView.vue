<!--详情页-->
<template>
  <el-row :gutter="10" style="width: 1200px;margin: 0 auto;">
    <el-col :span="18">
      <el-card>
        <h1 style="color:orange;text-align:center;">{{ content.title }}</h1>
        <p style="font-size:12px;color:#666;text-align:center;">作者: {{ content.nickname }} | 发布时间: {{
            content.createTime
          }}
          | 阅读次数: {{ content.viewCount }} </p>
        <hr>
        <!--新建一个div 把摘要与内容作为一个整体放入这个div中-->
        <!-- 如果type不是视频类型,显示此div       -->
        <div v-if="content.type!=2">
          <el-card>
            <span style="color:#0aa1ed;font-weight:bold;">摘要: </span>
            {{ content.brief }}
          </el-card>
          <!--注意:正文不能用插值方式显示，因为正文里有HTML标签效果,不是纯文本-->
          <!--        <p>{{ content.content }}</p>-->
          <div v-html="content.content"></div>
        </div>
        <!--反之,说明是视频,显示此div-->
        <div v-else>
          <video :src="'http://localhost:8080'+content.videoUrl" controls type="video/mp4" style="width:100%;"></video>
        </div>
      </el-card>
      <!--评论相关开始-->
      <el-card style="margin-top:10px;">
        <p>发一条友善的评论</p>
        <hr>
        <el-row :gutter="10">
          <el-col :span="22">
            <el-input placeholder="想不想说点啥?" v-model="comment.content" @keydown.enter="post()"></el-input>
          </el-col>
          <el-col :span="2">
            <el-button @click="post()">发布</el-button>
          </el-col>
        </el-row>
        <el-row :gutter="10" v-for="item in commentArr" style="margin: 10px;">
          <el-col :span="2">
            <el-avatar style="margin:10px;"><img :src="'http://localhost:8080'+item.userImgUrl"></el-avatar>
          </el-col>
          <el-col :span="22">
            <p style="color:orange;font-weight:bold;margin: 0;font-size: 15px;">{{ item.nickname }}</p>
            <p style="margin:2px 0;font-size: 13px;">{{ item.content }}</p>
            <p style="font-size:12px;color:#666;margin: 0;">{{ item.createTime }}</p>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div id="head_div"></div>
        <div style="text-align:center;position:relative;bottom:45px;">
          <el-avatar :size="90" :src="'http://localhost:8080'+content.userImgUrl"
                     style="border:5px solid #fff;"></el-avatar>
          <p style="font-size:20px;font-weight:bold;margin: 5px 0;">{{ content.nickname }}</p>
          <el-icon class="head_icon">
            <Edit/>
          </el-icon>
          <span>本文作者</span>
          <br>
          <el-icon class="head_icon">
            <Clock/>
          </el-icon>
          <span>{{ content.createTime }}</span>
        </div>
      </el-card>

      <el-card style="margin-top:10px;">
        <h2>热门文章</h2>
        <hr>
        <el-row :gutter="10" v-for="item in hotArr">
          <el-col :span="10">
            <router-link :to="'/detail?id='+item.id"><img :src="'http://localhost:8080/'+item.imgUrl" style="width:100%;"></router-link>
          </el-col>
          <el-col :span="14">
            <router-link :to="'/detail?id='+item.id"><p class="title_p">{{ item.title }}</p></router-link>
            <p style="color:#666;font-size:12px;margin: 0;">{{ item.createTime }}</p>
          </el-col>
        </el-row>
      </el-card>
      <el-card style="margin-top:10px;">
        <h2>作者其它文章</h2>
        <hr>
        <el-row :gutter="10" v-for="item in otherArr">
          <el-col :span="10">
            <router-link :to="'/detail?id='+item.id"><img :src="'http://localhost:8080/'+item.imgUrl" style="width:100%;"></router-link>
          </el-col>
          <el-col :span="14">
            <router-link :to="'/detail?id='+item.id"><p class="title_p">{{ item.title }}</p></router-link>
            <p style="color:#666;font-size:12px;margin: 0;">{{ item.createTime }}</p>
          </el-col>
        </el-row>
      </el-card>

    </el-col>
  </el-row>
</template>

<script setup>
import {onBeforeUpdate, onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import qs from "qs";
//1.定义对象保存内容详情
const content = ref({});
const otherArr = ref([]);
const hotArr = ref([]);
const comment = ref({content:''});
const commentArr = ref([]);

//加载评论需要传文章id
const loadComments = (id)=>{
  axios.get('http://localhost:8080/v1/comments/'+id).then((response)=>{
    if(response.data.code==2001){
      commentArr.value = response.data.data;
    }
  })
}

const post = ()=>{
  let user = localStorage.user?JSON.parse(localStorage.user):null;
  if(user==null){
    alert('请先登录!');
    return;
  }
  if(comment.value.content==''){
    ElMessage.error('评论不允许为空!');
    return;
  }
  comment.value.userId = user.id;//作者id
  comment.value.contentId = content.value.id;//文章id
  let data = qs.stringify(comment.value);
  axios.post('http://localhost:8080/v1/comments/add-new',data).then((response)=>{
    if(response.data.code==2001){
      ElMessage.success('评论完成!');
      //等写了评论列表,评论成功后需要刷新下评论列表
      //加载评论列表
      loadComments(content.value.id);
      comment.value.content = '';
    }
  })

}

//6.定义初始化数据的方法 封装之前的功能
const initData = ()=>{
  //3.拿到地址栏中的id
  let id = new URLSearchParams(location.search).get('id');
  //加载评论列表
  loadComments(id);
  //4.向后端发请求
  axios.get('http://localhost:8080/v1/contents/' + id + '/detail').then((response) => {
    if (response.data.code == 2001) {
      content.value = response.data.data;
      //注意这里才有用户id
      //请求作者其它文章
      axios.get('http://localhost:8080/v1/contents/' + content.value.userId + '/other/')
          .then((response) => {
            if (response.data.code == 2001) {
              otherArr.value = response.data.data;
            }
          })
    }
  })
  //5.请求热门文章 与其他内容没关系，写在onMounted里就行了
  axios.get('http://localhost:8080/v1/contents/hot').then((response) => {
    if (response.data.code == 2001) {
      hotArr.value = response.data.data;
    }
  })
}

//点击详情页面右侧热门文章,还是要打开详情页面
//2.onMounted()跳转过来立即执行,只有当A->B页面时才会执行 A->A页面不会执行,因为没有重新发请求
onMounted(() => {
  initData();
})
//8.与onMounted()相反，当A->B不执行 A->A页面才执行
onBeforeUpdate(()=>{
  initData();
})



</script>
