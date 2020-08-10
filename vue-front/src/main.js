import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
Vue.prototype.$userVariables = [ {brandName: "",brandList: [], brandId: "", phoneId: "", phoneName: "", phoneImgUrl: ""} ];

new Vue({
  render: h => h(App),
  router

}).$mount('#app')
