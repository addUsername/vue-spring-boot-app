<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">      
        <div class="navbar-nav mr-auto">
          <li>
            <router-link to="/" class="nav-link">Home</router-link>
          </li>
          <li>
            <router-link to="/brands" class="nav-link">Brands</router-link>
          </li>
          <li>
            <router-link to="/" class="nav-link">Last Post</router-link>
          </li>
        </div>      
    </nav>
    
    <footer class="font-small footvbar-dark bg-dark fixed-bottom">
      <div class="container">
        <div class="row">
          <div class="col-9">
          </div>
          <div class="col">
              <a href="http://github.com/addusername" class="fa fa-github fa-2x" title="Github"></a>
          </div>
          <div class="col">
              <a href="https://www.linkedin.com/in/addusername/" class="fa fa-linkedin fa-2x" title="Linkedin"></a>
          </div>
          <div class="col footer-copyright text-center py-10">
              <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
          </div>
          </div>
    </div>
  </footer>
  <div>
    <div class="row">
      <div class="col-1">
        <nav  class="navbar-dark bg-dark flex-column">
          <div class="px-3 py-2" v-for="(item, index) in this.$userVariables.brandList" :key="index">
              <a href="#" class="btn btn-primary stretched-link">{{ item }}</a>
          </div>
        </nav>
      </div>
      <div class="col-9">
        <div class="container mt-3">
          <router-view />
        </div>
      </div>
    </div>
  </div>
  </div>


</template>

<script>
import Repository from "./services/Repository";

export default {
  name: 'app',
  methods:{
  getSideBar() {
      this.$userVariables.brandList=[]
      Repository.getAll()
        .then(response => {
          response.data.forEach(element => {
            this.$userVariables.brandList.push(element[0]);
          });
        })
        .catch(e => {
          console.log(e);
        });
  }
  },
  mounted() {
      //pillar global variable brandId
    this.getSideBar();
  }
};
</script>