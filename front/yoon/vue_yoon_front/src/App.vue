<template>
  <v-app >
     <side @onLogin="onLogin_2"/>
      <v-content>
      <ImgBanner/>
      <router-view/>
      <Footer/>
      <RegisterBefore :dialog="onLoginModal" @onLogin="onLogin_2"/>
    </v-content>

  </v-app>
</template>

<script>
import side from './components/side'
import ImgBanner from './components/ImgBanner'
import Footer from './components/seo/Footer'
import RegisterBefore from './components/yoon/RegisterBefore'
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
    }
  },
  methods:{
    onLogin_2(data){
      console.log("!!!!!!!!!!!!!!!!!!!");
      this.onLoginModal = data;
    }
  },
  watch:{
        onLoginModal(){
          console.log(this.onLoginModal);
        }
  }
};
</script>
