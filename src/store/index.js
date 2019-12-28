import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'

Vue.use(Vuex);

export default new Vuex.Store({
  modules:{  //声明并挂载模块数据user
    user
  }
})
