import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:'/index',
    children:[
      {path:'/index',component: ()=>import('../views/home/IndexView')},
      {path:'/reg',component: ()=>import('../views/home/RegView')},
      {path:'/login',component: ()=>import('../views/home/LoginView')},
      {path:'/detail',component: ()=>import('../views/home/DetailView')},
      {path:'/list',component: ()=>import('../views/home/ListView')},
      {path:'/personal',
        redirect:'/personal/info',
        component: ()=>import('../views/home/PersonalView'),
        children:[
          {path:'info',component: ()=>import('../views/home/personal/InfoView')},
          {path:'management',component: ()=>import('../views/home/personal/ManagementView')},
          {path:'post',component: ()=>import('../views/home/personal/PostView')}
        ]
      }
    ]
  }
  ,{
    path:'/admin',
    redirect:'/admin/user',
    component: () => import( '../views/AdminView'),
    children:[
      {path:'user',component:()=>import('../views/admin/UserView')},
      {path:'banner',component:()=>import('../views/admin/BannerView')},
      {path:'content',component:()=>import('../views/admin/ContentView')}
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router