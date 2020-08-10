

<template>    
  <div class="grid-container">
    
    <div class="row m-5">
      <img class="float-left" :src="$userVariables.phoneImgUrl" />
      <div class="float-left">
        <table class="table-sm">
          <thead>
            <tr>
              <th class="table-header  bg-dark text-white">Name</th>
              <th class="table-header  bg-dark text-white">Value</th>
              <th class="table-header  bg-dark text-white">Description</th>
            </tr>
          </thead>
          <tbody>
                <tr class="table-row" v-for="(item, index) in specs" :key="index">
                  <td class="table-col">{{item.name}}</td>
                  <td class="table-col">{{item.value}}</td>
                  <td class="table-col">{{item.description}}</td>
                </tr>
          </tbody>
        </table> 
      </div>

    </div>
    <div class="row">
      <Comments />
    </div>
        
  </div>
</template>


<script>
import Repository from "../services/Repository";
import Comments from "./Comments";


export default {
  name: "phones",
  components: {
    Comments
  },
  data() {
    return {
      specs: []
    };
  },
  methods: {
    retrieveSpecs() {
      Repository.getPhoneSpecs(this.$userVariables.phoneId)
        .then(response => {
            response.data.forEach(element => {
                this.specs.push({name: element[0], value: element[1], description: element[2]});
          });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
      //pillar global variable brandId
    this.retrieveSpecs();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>