<template>
  
  <v-app  :style="'background-color :'+color+';'">
    <template v-if="view_on">
     <side   @onLogin="onLogin_2" :isLoginsub="isLogin" @logOut="outLog"/>
      <ImgBanner/>
      <router-view/>
      
      <RegisterBefore :dialog="onLoginModal" @onLogin="onLogin_2"/>
      <register-github-member :dialog="ifLogin" @onLogin="onLogin_3"></register-github-member>  
      <v-btn
      bottom
      color="pink"
      dark
      fab
      fixed
      right
      @click="dialog = !dialog"
      v-if="this.isLogin"
    >
          <v-badge
          color="green"
          :content="alarm_count"
      >
      <v-icon>mdi-plus</v-icon>
          </v-badge>
    </v-btn>
    <Footer/>
    <v-dialog
      v-model="dialog"
      width="80vw"
    >
      <v-card>
        <v-card-title class="grey darken-2">
          팀 수락 / 거절
        </v-card-title>
        <v-container>
          <v-row class="mx-2">
            <v-col
              class="align-center justify-space-between"
              cols="12"
            >
               <v-card v-for="(item,i) in accpet_list" :key="i" >
                <v-card-title>{{accpet_list[i].team.teamName}}</v-card-title>
                <v-card-text>
                  <v-flex>{{accpet_list[i].role}}</v-flex>
                  <v-flex>{{accpet_list[i].team.teamDate}}</v-flex>
                </v-card-text>
                <v-card-actions >
                  <v-btn @click="accept(accpet_list[i])" color="primary">허용</v-btn>
                  <v-btn @click="refuse(accpet_list[i])" color="red">거절</v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
        <v-card-actions>
          <v-spacer />
          <v-btn
            text
            color="primary"
            @click="dialog = false"
          >Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    </template>
    <v-container   v-else>
      <v-img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/bc90cc81411939.5d2f90e336b69.gif" style="max-width: 80vw; height: auto;"/>
    </v-container>
        
  </v-app>
</template>

<script>
import side from './components/side'
import ImgBanner from './components/ImgBanner'
import Footer from './components/seo/Footer'
import RegisterBefore from './components/yoon/RegisterBefore'
import RegisterGithubMember from './components/yoon/RegisterGithubMember'
import http from './http-common';
import firebase from './Firebase';
export default {
  name: 'App',
  components : {
    side,
    ImgBanner,
    Footer, 
    RegisterBefore,
    RegisterGithubMember,
    
  },
  data(){
    return {
      onLoginModal:false,
      isLogin:false,
      ifLogin : false,
      view_on : false,
      color : 'black',
      querySnapshot:null,
      alarm_count : 0,
      dialog : false,
      accpet_list : [],
      usr_id : "",
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
      console.log("App.vue>>------checkLogin()-----"+code);
      http.post('/github/accessToken',{"code":code})
      .then(response=>{
        
        return this.isJWTNull(response.data);
      })
      .catch(err=>{
        console.log(err);
      });
      return this.isJWTNull(null);
    },
    isJWTNull(value){
      if(value!=null){
        sessionStorage.setItem('github-access-token',value.access_token);
      
      if(value.hasOwnProperty("access_token")&&(value.login_access_token==null||value.login_access_token===null||value.login_access_token=='null')){
        console.log(">>>>>>>>>>>>>"+value);
        return this.ifLogin=true;
      }else if(value.hasOwnProperty("access_token")){
          sessionStorage.setItem('x-access-token',value.login_access_token);
          http.defaults.headers.common['x-access-token'] = value.login_access_token;
          console.log("App.vue>>mounted()----"+sessionStorage.getItem('x-access-token'));
          console.log("App.vue>>mounted()----"+sessionStorage.getItem('github-access-token'));
          return this.callback();
      }
      }
    },
    callback(){
      if(sessionStorage.getItem('x-access-token')==null||
    sessionStorage.getItem('x-access-token')===undefined||
    sessionStorage.getItem('x-access-token')===""||
    sessionStorage.getItem('x-access-token')==="null"){
      this.isLogin = false;
    }else{
      this.isLogin = true;
      http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
      return this.getUser();
    }
    console.log("App.vue>>------callback()----isLogin>>"+this.isLogin);
    console.log("App.vue>>-----callback()-----token>>"+sessionStorage.getItem('x-access-token'));
    },
    onLogin_3(){
      this.ifLogin = false;
    },
    getUser(){
      
      http.get('/user')
      .then(response=>{
        // console.log("App.vue>>---------getUser()------");
        // console.log(response.data);
        return this.firebase_init(response.data);
      })
      .catch(err=>{
        console.log(err);
      })
    },
    firebase_init(user){

      console.log(user);
      this.usr_id = user.member.email;
      firebase.firestore().collection("Alarm").doc(user.member.email).get()
      .then(res=>{
        
        console.log(res.data());
        if(res.data()!=undefined||res.data()<=0){
          this.alarm_count = res.data().count;
          
        }else{
          this.alarm_count = 0;
          firebase.firestore().collection("Alarm").doc(user.member.email).set({
            count : this.alarm_count,
          })
          .then(res => {
            console.log(res);
            firebase.firestore().collection("Alarm").doc(user.member.email).onSnapshot((querySnapshot)=>{
              this.querySnapshot = querySnapshot;
              this.alarm_count = querySnapshot.data().count
            })
          })
          .catch(err =>{console.log(err)})
        }
        
        return this.getAcceptList();
      })
      firebase.firestore().collection("Alarm").doc(user.member.email).onSnapshot((querySnapshot)=>{
        this.querySnapshot = querySnapshot;
        this.alarm_count = querySnapshot.data().count
      })
    },
    firebase_count_down(){
      if(this.alarm_count>0){
        this.alarm_count-=1;
      }
      firebase.firestore().collection("Alarm").doc(this.usr_id).set({
            count : this.alarm_count,
          })
          .then(res => {
            console.log(res);
          })
          .catch(err =>{console.log(err)})
    },
    getAcceptList(){
      
        http.get('team/accept')
        .then(response =>{
          console.log("Accecpt List")
          console.log(response.data);
          this.accpet_list = response.data;
        })
    },
    accept(info){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
        http.patch('team/accept/'+info.teamMemberId)
        .then(response =>{
          if(response.data.state==='succ'){
            this.getAcceptList();
            this.firebase_count_down();
          }
        })
      },
    refuse(info){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
        http.delete('team/refuse/' + info.teamMemberId)
        .then(response =>{
          if(response.data.state === 'succ'){
            this.getAcceptList();
            this.firebase_count_down();
          }
        })
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
      this.callback();
    }else{
      this.callback();
    }
    console.log("app.vue>>>-------mounted()----"+this.view_on);
    setTimeout(()=>{this.view_on=true; this.color = ""},2500);

  },


};
</script>
