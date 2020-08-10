<template>    
  <div class="grid-container ">
    <div id="max_width" class="m-5" :key="reload">
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
          Add Comment          
        </button>
        <div class="collapse" id="collapseExample">
          <div class="card card-body">
            <input class="m-2" type="text" v-model="user" />
            <textarea rows="4" max-rows="7" class="m-2" v-model="comment"/>
            <div>
              <button type="button" v-on:click="uploadComment()" data-toggle="collapse" data-target="#collapseExample" class="btn-sm btn-primary">Submit</button>
            </div>
          </div>
        </div>     
    </div>
    <div>
      <div class="row m-5 min-vw-100" v-for="(item, index) in post" :key="index">
        <div class="table">
          <thead>
            <tr >
              <th class="table-primary" width="200px">{{item.user}}</th>
              <th class="table-primary" width="1000px">Comment</th>
            </tr>
          </thead>
          <tbody>
            <tr >
              <td >
                <div class="wrapper">
                  <img id="userImg" class= "img-thumbnail" :src="item.userImg" />
                </div>
              </td>
              <td>
                <div id="max_width" v-html="item.text"></div>
              </td>
            </tr>
            <tr id="no_borders">
              <td></td>
              <td>                
                <div v-on:click="getComment(item.id)">
                  <button type="button" class="btn-sm btn-warning pull-right" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Quote</button>
                </div>
              </td>
            </tr>
          </tbody> 
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import Repository from "../services/Repository";

export default {
  name: "post",
  data() {
    return {
      post: [],
      user: "Uknown",
      comment: "",
      img:"",
      i:0,
      reload:0
    };
  },
  methods: {
    retrievePost() {
      Repository.getPhonePost(this.$userVariables.phoneId)
        .then(response => {
            response.data.forEach(element => {
                this.retrieveUserImg(element);
          });
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveUserImg(element){
      //picks random img
      var myUrl;
        Repository.getUserImg()
          .then(response => {
           myUrl = URL.createObjectURL(new Blob([response.data]));
           this.post.push({"id":this.i, "user": element[0], "text": element[1], "userImg" : myUrl });
           this.i++;
           return myUrl;    
        })
        .catch(e => {
            console.log(e);
          });
        
        return myUrl;
    },
    uploadComment(){
      Repository.uploadComment([this.comment, this.user, this.$userVariables.phoneId]);
      this.post.unshift({id:this.$userVariables.phoneId, user: this.user, text: this.comment, userImg: this.post[2].userImg});
      //this.reloadPage();
    },
    getComment(index){
      this.post.forEach(row => {
        if(row.id == index){
          this.comment = "<div id='max_width_quote'><h5>"+row.user+"<h5><div>"+row.text+"</div></div>";
          return
        }
      });
    },
    reloadPage(){
      this.reload=this.reload+1;
    }
  },
  mounted() {
      //pillar global variable brandId
    this.retrievePost();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
#userImg{
  width:50%;
}
#no_borders {
    border: none;
}
#max_width{
  max-width: 600px;
}
#max_width_quote{
  max-width: 850px;
  background-color:bisque;
}
</style>