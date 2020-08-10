<template>
<div class="grid-container">
  <div class="row">
    <div class = "col-md-4" v-for="(item, index) in url" :key="index">
      <div v-on:click="updateUserVariables(item.id,item.name)" >
        <h2>{{item.name}}</h2>
        <img class="img-fluid" :src="item.url" />
        <p>{{item.description}}</p>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import Repository from "../services/Repository";

export default {
  name: "brands",
  data() {
    return {
      url: []
    };
  },
  methods: {
    //This method get brand object (json) and call retrieveBrandImg(brandName) to get logoURL forEach brand object recived
    //Prop i should learn how to store imgs link on database.. all info in just 1 request
    //element = [[name,descript]]
    retrieveBrands() {
      this.$userVariables.brandList=[]
      Repository.getAll()
        .then(response => {
          response.data.forEach(element => {
            this.$userVariables.brandList.push(element[0]);
            this.retrieveBrandImg(element);
          });
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveBrandImg(brandName) {
      //This method get from spring an MediaType.IMAGE_PNG_VALUE as binary? See Repository.getBranImg() for +info 
      Repository.getBranImg(brandName[0])
        .then(response => {
          // this [ ] on response data if bc it's an array!
          var myUrl = URL.createObjectURL( new Blob([response.data]));
          this.url.push({name: brandName[0], description: brandName[1], url: myUrl, id: brandName[2]});
          console.log("This img works");
          console.log(myUrl);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateUserVariables(brandId, brandName){
      this.$userVariables.brandId = brandId;
      this.$userVariables.brandName = brandName;
      this.$router.push('/phones');
    }
  },
  mounted() {
    this.retrieveBrands();
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