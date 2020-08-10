import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/brands",
      component: () => import("./components/BrandList")
    },
    {
      path: "/phones",
      component: () => import("./components/PhoneList")
    },
    {
      path: "/specs",
      component: () => import("./components/PhonePage")
    }

  ]
});