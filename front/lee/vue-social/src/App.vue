<template>
  <div id="app">
    <button v-on:click="getGithubCode">get data</button>
    <button v-on:click="getGithubAccessToken">get token</button>
    <button v-on:click="postSave">save</button>
    <button v-on:click="repository">repository</button>
    <button v-on:click="singup">signup</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {

  name: 'app',
  mounted() { 
    var c  = location.search.split('code=')[1]
    console.log(c);
  },
  data(){
    return {
      githubToken : null
    };
  }
  ,
  methods:{
    getGithubCode : function(){
      window.location.href = "https://github.com/login/oauth/authorize?redirect_uri=http://localhost:8080/github&client_id=7038e018caed836c5c38";
      return false;
    },
    test: function(){
      
    },
    getGithubAccessToken : function(){


      var code = location.search.split('code=')[1];
      var url = "http://192.168.31.131:9000/v1/accessToken";
     
      console.log(code);
      var params = new URLSearchParams();
      params.append("code", code);
      axios.post(url, params)
      .then(response => {
        console.log(response);
        this.githubToken = response['data']['access_token'];
        console.log(this.githubToken);
      }).catch(err => {
        console.log(err);
      })

      return;
    },
    postSave : function(){

      var url = "http://192.168.31.131:9000/v1/signup/github";
     
      var params = new URLSearchParams();
      params.append("accessToken", this.githubToken);
      axios.post(url, params)
      .then(response => {
        console.log(response);
      }).catch(err => {
        console.log(err);
      })
      return;
    },
    singup : function(){
      axios(
        {
          url : "http://localhost:8197/api/signup/github",
          method : "post",
          headers:{
          'Authorization': 'Bearer e28aa7f1395ad455230501f661abbe83d58a6d72'
          //자신의 토큰
          }
      })
      .then(response => {
        console.log(response);
      }).catch(err => {
        console.log(err);
      })
    }
    ,
    repository : function(){

      axios(
        {
          //url : "https://api.github.com/repos/eunq2/privateTest/commits",
          url : "https://api.github.com/repos/eunq2/privateTest",
          // /repos/{:id}/{:projectName}
          //https://api.github.com/repos/eunq/devProejct/commits 커밋확인용.
          headers:{
          'Authorization': 'Bearer e28aa7f1395ad455230501f661abbe83d58a6d72'
          //7deb9185f524bdd822ec655de31638f35d4fd4d6
          //eunq = e28aa7f1395ad455230501f661abbe83d58a6d72
          //자신의 토큰
          }
      })
      .then(response => {
        console.log(response);
      }).catch(err => {
        console.log(err);
      })

    }
  }
  
}
</script>

