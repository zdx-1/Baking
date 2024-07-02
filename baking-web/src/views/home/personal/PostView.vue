<!--发布页-->
<template>
  <h1 style="color:orange;">{{ content.id==null?'发布内容页面':'修改内容页面'}}</h1>
  <el-form ref="form" label-width="100px">
    <el-form-item label="文章标题">
      <el-input placeholder="请输入文章标题" v-model="content.title"></el-input>
    </el-form-item>
    <el-form-item label="文章类型">
      <!--@change=""选项发生改变时触发-->
      <!-- :disabled="content.id!=null" id不为空也就是修改时禁用改变文章类型     -->
      <el-radio-group :disabled="content.id!=null" @change="typeChange()" v-model="content.type" >
        <!--        <el-radio-button label="1">烘焙食谱</el-radio-button>-->
        <!--        <el-radio-button label="2">烘焙视频</el-radio-button>-->
        <!--        <el-radio-button label="3">行业资讯</el-radio-button>-->
        <!--使用遍历的方式展示类型-->
        <el-radio-button v-for="c of catTypeArr" :label="c.type">{{ c.name }}</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="二级分类">
      <!-- 下拉选框设置双向绑定与文本占位符 -->
      <el-select v-model="content.categoryId" placeholder="请选择">
        <!--        <el-option label="面包" value="1"></el-option>-->
        <!--        <el-option label="小食" value="2"></el-option>-->
        <el-option v-for="c in catgoryArr" :label="c.name" :value="c.id"></el-option>
      </el-select>
    </el-form-item>
    <!--封面上传开始-->
    <el-form-item label="封面">
      <!-- 注意:只有修改时才有id值,需要回显封面 v-if="content.id!=null"
      新发布稿件没有值(id 封面 等都没有)
      &&fileList.length==0要确保封面图片只能有一张-->
      <img v-if="content.id!=null&&fileList.length==0"
           :src="'http://localhost:8080'+content.imgUrl"
           style="width: 145px;height: 145px;margin-right:10px;"
      >
      <!-- 此部分内容与InfoView是一样的,可以直接拿过来 -->
      <el-upload
          v-model:file-list="fileList"
          action="http://localhost:8080/v1/upload"
          name="file"
          limit="1"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
      >
        <el-icon><Plus /></el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" />
      </el-dialog>
    </el-form-item>
    <!--封面上传结束-->

    <!--复制一份完整的封面上传 改成视频上传开始 v-show="content.type==2"只有类型为2是烘焙视频的时候才显示-->
    <el-form-item label="视频" v-show="content.type==2">
      <!--content.id!=null 修改时才显示   videoList.length==0 原来的那个没有才显示新修改的这个，同一时间只能显示一个-->
      <video v-if="content.id!=null&&videoList.length==0"
             :src="'http://localhost:8080'+content.videoUrl"
             style="width:300px;margin-right:10px;" type="video/mp4" controls></video>
      <el-upload
          v-model:file-list="videoList"
          action="http://localhost:8080/v1/upload"
          name="file"
          limit="1"
          accept="video/*"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
      >
        <el-button type="warning">点击上传视频</el-button>
        <div>（只能上传不超过100M的mp4文件）</div>
      </el-upload>
      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image" />
      </el-dialog>
    </el-form-item>
    <!--视频上传结束-->
    <!--v-show="content.type!=2"只有不是视频才显示 是视频不需要上传文章-->
    <el-form-item label="文章内容" v-show="content.type!=2">
      <!-- 4.在此div使用第三方富文本编辑器 ref="editorDiv"让editorDiv变量指向当前的这个div
      也就是将此div与下方的响应式变量editorDiv进行关联 -->
      <div ref="editorDiv"></div>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="post()">{{ content.id==null?'发布内容':'修改内容'}}</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import { Plus } from '@element-plus/icons-vue'
//1.安装富文本编辑器 指令: npm install wangeditor
//2.导入富文本编辑器
import Editor from 'wangeditor';
import axios from "axios";
import {ElMessage} from "element-plus";
import qs from "qs";
import router from "@/router";

const catTypeArr = ref([]);
const catgoryArr = ref([]);
//定义变量保存文章的标题与类型 类型1表示默认是烘焙食谱类型
//给content对象追加一个categoryId:''属性,默认值为空
const content = ref({title:'',type:'1',categoryId:''})
//当选择的文章类型发生改变时，发请求拿到本类型对应的二级分类数据
const typeChange = ()=>{
  // 加这个是为了防止切换一级标题时把原来的带过去
  content.value.categoryId='';
  //发请求获取二级分类的数据
  axios.get('http://localhost:8080/v1/categories/'+content.value.type+'/sub').then((response)=>{
    if(response.data.code==2001){
      catgoryArr.value = response.data.data;
    }
  })
}
onMounted(()=>{
  //location包含了关于当前URL的信息
  //当以get方式在url中传递了请求参数时，可用location的search属性提取参数的值,获取的是?以及后面的参数
  if(location.search.includes('id')){
    let id = new URLSearchParams(location.search).get('id');//get()获取指定搜索参数的第一个值
    axios.get('http://localhost:8080/v1/contents/'+id+'/update').then((response)=>{
      if(response.data.code=2001){
        //将查询到的指定id详细内容装到content中显示
        content.value = response.data.data;
        //让富文本编辑器显示文章内容
        editor.txt.html(content.value.content);
        //二级分类无法正常回显
        //原因:当前type 1 中没有其它类型的二级分类项
        //解决:重新请求当前content对象中type对应的二级分类
        //复制typeChange()方法中发请求获取二级分类的数据的代码到此处
        axios.get('http://localhost:8080/v1/categories/'+content.value.type+'/sub').then((response)=>{
          if(response.data.code==2001){
            catgoryArr.value = response.data.data;
          }
        })
      }
    })
  }
  //发请求获取二级分类的数据
  axios.get('http://localhost:8080/v1/categories/1/sub').then((response)=>{
    if(response.data.code==2001){
      catgoryArr.value = response.data.data;
    }
  })

  axios.get('http://localhost:8080/v1/categories/type').then((response)=>{
    if(response.data.code==2001){
      catTypeArr.value = response.data.data;
    }
  })

})

const fileList = ref([])
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
//完成删除图片方法
const handleRemove = (uploadFile) => {
  //得到删除图片的路径
  let imgUrl = uploadFile.response.data;
  //发出删除文件的请求
  axios.post('http://localhost:8080/v1/remove?imgUrl='+imgUrl).then((response)=>{
    if(response.data.code==2001){
      ElMessage.success('服务器已删除该文件!');
    }
  })
}
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true
}

//3.创建响应式变量editorDiv,此变量指向页面中的div(这个div用来装富文本编辑器组件),将变量的值与div进行绑定
const editorDiv = ref(null);
//5.创建变量,一会用来保存创建好的富文本编辑器对象
let editor = null;
//6.onMounted()是VUE生命周期方法【指VUE实例从创建到销毁过程中 自动调用的方法】
//Mounted表示挂载,页面构建初始化时,需要将VUE实例与页面中元素进行挂载
onMounted(()=>{//挂载完成后自动调用的方法
  //我们这里的富文本编辑器必须得挂载完成后执行,只有挂载完成后editorDiv变量才与上面的div建立好关系
  //7.初始化富文本编辑器(需要装到上面的div里)
  editor = new Editor(editorDiv.value);
  //8.配置富文本编辑器里的占位文本
  editor.config.placeholder="请输入内容详情";
  //9.根据上面的配置创建富文本编辑器
  editor.create();
})
//定义视频数组
const videoList = ref([]);
//完成发布功能
const post = ()=>{
  //发布前必须保证用户已登录
  let user = localStorage.user?JSON.parse(localStorage.user):null;
  if(user==null){
    ElMessage.error('请先登录!')
    router.push('/login')
    return;
  }
  //将用户id存入content中
  content.value.userId=user.id;

  //发文章之前做必要内容的非空处理
  if(content.value.title.trim()==''){ElMessage.error('请输入标题!');return;}
  if(content.value.categoryId==''){ElMessage.error('请选择二级分类!');return;}
  /**判断是发布还是修改 发布必须选封面 修改可以使用旧封面**/
  if(content.value.id==null){/**发布**/
    /**发布时没传图，提示**/
    if(fileList.value.length==0){ElMessage.error('请选择封面!');return;}
    let imgUrl = fileList.value[0].response.data;
    content.value.imgUrl = imgUrl;
  }else{/**修改**/
    /**修改时没传图，使用旧图，如果传图了，才把新传的图装到content里**/
    if(fileList.value.length>0){
      let imgUrl = fileList.value[0].response.data;
      content.value.imgUrl = imgUrl;
    }
  }

  //判断是视频还是文章
  if(content.value.type==2){//视频

    /**判断是发布还是修改 发布必须选视频 修改可以使用旧视频**/
    if(content.value.id==null){/**发布**/
      /**发布时没传视频，把原来代码放进去**/
      if(videoList.value.length==0){ElMessage.error('请先选择视频文件!');return;}
      let videoUrl = videoList.value[0].response.data;
      content.value.videoUrl = videoUrl;
    }else{/**修改**/
      /**修改时没传视频，使用视频，如果传了，才把新传的装到content里**/
      if(videoList.value.length>0){
        let videoUrl = videoList.value[0].response.data;
        content.value.videoUrl = videoUrl;
      }
    }

  }else{//是文章或资讯 就设置文章内容与摘要
    //从富文本编辑器对象中获取文章内容,以下两种方式是有区别的:
    console.log('html='+editor.txt.html());//html=<p>我是一段<i><b>测试文字</b></i></p> 有文字样式
    console.log('text='+editor.txt.text());//text=我是一段测试文字 没有格式
    //设置文章内容
    content.value.content = editor.txt.html();
    //设置文章摘要 从文章内容的纯文本内容中截取前30个字符
    content.value.brief = editor.txt.text().slice(0,30)
    //走到这里打印测试一下看看是否能拿到完整的文章数据
    //console.log(content.value)
    //{title: '奶油吐司', type: 2, categoryId: 6, imgUrl: '/2024/02/16/63990c80-09e5-4b6c-a8a8-5c5be638aced.jpeg', content: '<p>如何制作<b>奶油吐司</b>？</p>'}
  }


  //发请求
  let data = qs.stringify(content.value)
  axios.post('http://localhost:8080/v1/contents/add-new',data).then((response)=>{
    if(response.data.code==2001){
      //动态显示提示消息
      ElMessage.success(content.value.id==null?"发布成功!":"修改成功!")
      //发布成功后要判断来源，如果来源是后台内容管理页
      if(localStorage.from!=null&&localStorage.from=='admin'){
        router.push('/admin/content');//回到后台
        localStorage.from=null;//跳转后注意置空,否则下次从稿件管理页面进来也会回到后台管理页面
      }else{
        //发布成功后跳转到稿件管理页面
        router.push('/personal/management');
      }
    }
  })
}
</script>

<style scoped>

</style>