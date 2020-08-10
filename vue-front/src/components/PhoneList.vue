

<template>
    <div class="grid-container">
      <div class="row">
        <div class = "col-md-4" v-for="(item, index) in phones" :key="index">
          <div v-on:click="updateUserVariables(item.id, item.url, cleanName(item.name))" >
            <h4>{{ cleanName(item.name) }}</h4>
            <img class="img-fluid" :src="item.url" />
          </div>
        </div>
      </div>
    </div>
</template>


<script>
import Repository from "../services/Repository";

export default {
  name: "phones",
  data() {
    return {
      phones: []
    };
  },
  methods: {
    cleanName(name){
      return name.substring(this.$userVariables.brandName.length + 1 , name.length-4 );
    },
    retrievePhones() {
      Repository.getAllPhones(this.$userVariables.brandId)
        .then(response => {
          response.data.forEach(element => {
            this.retrievePhoneImg(element);
          });
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrievePhoneImg(phone) {
      Repository.getPhoneImg(this.$userVariables.brandName, phone[0])
        .then(response => {
          var myUrl = URL.createObjectURL( new Blob([response.data]));
          this.phones.push({name: phone[0], id: phone[1], url: myUrl});
          this.count = this.count +1 ;
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateUserVariables(id, imgUrl, name){
      this.$userVariables.phoneId = id;
      this.$userVariables.phoneImgUrl = imgUrl;
      this.$userVariables.phoneName = name;
      this.$router.push('/specs');
    }
  },
  mounted() {
      //pillar global variable brandId
    this.retrievePhones();
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