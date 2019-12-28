import Vue from 'vue'
import App from './App.vue'
import router from './router'
import config from '@/config'
import ElementUI from 'element-ui'
import store from '@/store'
import Cookies from 'js-cookie'
import 'element-ui/lib/theme-chalk/index.css'

if (process.env.NODE_ENV !== 'production') require('@/mock')

Vue.config.productionTip = false
import selfTable from "@/components/table/SelfTable.vue"
Vue.component("self-table",selfTable);
Vue.prototype.$config = config
Vue.use(Cookies)
Vue.use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
