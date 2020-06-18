<template>
  <v-app >
     <side   @onLogin="onLogin_2" :isLoginsub="isLogin" @logOut="outLog"/>
      <ImgBanner/>
      <router-view/>
      <Footer/>
      <RegisterBefore :dialog="onLoginModal" @onLogin="onLogin_2"/>

  </v-app>
</template>

<script>
import side from './components/side'
import ImgBanner from './components/ImgBanner'
import Footer from './components/seo/Footer'
import RegisterBefore from './components/yoon/RegisterBefore'
import http from './http-common';
export default {
  name: 'App',
  components : {
    side,
    ImgBanner,
    Footer,
    RegisterBefore,
  },
  data(){
    return {
      onLoginModal:false,
      isLogin:false,
    }
  },
  methods:{
    outLog(data){
      if(data){
        sessionStorage.setItem('x-access-token',null);
        this.isLogin = false;
        return this.callback();
      }
    },
    onLogin_2(data){
      console.log("!!!!!!!!!!!!!!!!!!!");
      if(sessionStorage.getItem('x-access-token')==null||sessionStorage.getItem('x-access-token')==undefined||sessionStorage.getItem('x-access-token')==""||sessionStorage.getItem('x-access-token')=="null"){
        this.isLogin = false;
        console.log("App.vue>>-----------this.isLogin = null >>");
      }else{
        this.isLogin = true;
        console.log("App.vue>>-----------this.isLogin Not null >>"+this.isLogin);
        console.log(sessionStorage.getItem('x-access-token'));
      }
      this.onLoginModal = data;
    },
    checkLogin(){
      let code = location.search.split('code=')[1];
      console.log(code);
      http.post('/github/accessToken',{"code":code})
      .then(response=>{
        console.log(response.data);
        return this.isJWTNull(response.data);
      })
      .catch(err=>{
        console.log(err);
      })
      
    },
    isJWTNull(value){
      sessionStorage.setItem('github-access-token',value.access_token);
      if(value.login_access_token==null||value.login_access_token===null||value.login_access_token=='null'){
        console.log(value);
        this.ifLogin = true;
      }else{
          sessionStorage.setItem('x-access-token',value.login_access_token);
          http.defaults.headers.common['x-access-token'] = value.login_access_token;
          console.log("App.vue>>mounted()----"+sessionStorage.getItem('x-access-token'));
          console.log("App.vue>>mounted()----"+sessionStorage.getItem('github-access-token'));
      }
      return this.callback();
    },
    callback(){
      if(sessionStorage.getItem('x-access-token')==null||
    sessionStorage.getItem('x-access-token')===undefined||
    sessionStorage.getItem('x-access-token')===""||
    sessionStorage.getItem('x-access-token')==="null"){
      this.isLogin = false;
    }else{
      this.isLogin = true;
    }
    console.log("App.vue>>------callback()----isLogin>>"+this.isLogin);
    console.log("App.vue>>-----callback()-----token>>"+sessionStorage.getItem('x-access-token'));
    }
  },
  watch:{
        onLoginModal(){
          console.log("App.vue>>----------onLoginModal()>>"+this.onLoginModal);
        },
  },
  mounted(){
    
    let code = location.search.split('code=')[1];
    if(code!=null&&code!=""&&code!=undefined){
      this.checkLogin();
    }
    
  },


};
</script>
