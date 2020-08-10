import axios from "axios";

export const AXIOS = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-type": "application/json"
  }
});

class Repository {
  getAll() {
    return AXIOS.get("/index");
  }  
  getAllPhones(brandId){
    return AXIOS.get("/brand/"+brandId);
  }
  getBranImg(brandName){
    return AXIOS.get("/img/"+brandName , {
      responseType: 'blob',
      type: "image/png"
    });
  }
  //WORKS
  getPhoneImg(brand,id){
    return AXIOS.get("/img/"+brand+"/"+id, {
      responseType: 'blob',
      type: "image/jpg"
    });
  }
  //NOT WORK
  getUserImg(){
    return AXIOS.get("/img/profile", {
      responseType: 'blob',
      type: "image/jpg"
    });
  }
  getPhoneSpecs(id){
    return AXIOS.get("/phone/"+id);
  }
  getPhonePost(id){
    return AXIOS.get("/phone/comments/"+id);
 
  }
  uploadComment(info){
    AXIOS.post("/upload/mssg", {
      comment: info[0],
      user: info[1],
      phoneid: info[2]
    });
  }

}

export default new Repository();