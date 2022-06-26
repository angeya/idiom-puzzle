import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false

Vue.use(ElementUI)
// 如果是开发环境，则加上api,触发代理
Vue.prototype.axios = axios.create({
  baseURL: process.env.NODE_ENV !== 'production' ? '/api/' : '',
  crossDomain: true,
});

new Vue({
  render: h => h(App),
}).$mount('#app')
