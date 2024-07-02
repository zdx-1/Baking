import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import axios from "axios";
const BASE_URL = 'http://localhost:8080';
window.BASE_URL =BASE_URL;
const app = createApp(App)
app.config.globalProperties.BASE_URL =BASE_URL;
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus).use(store).use(router).mount('#app')