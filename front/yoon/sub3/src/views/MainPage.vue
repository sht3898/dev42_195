<template>
  <v-row>
  <v-container fill style="width:100%;align-items:center; padding:0;">
    <v-row>
      
    <v-col
      cols="12" style="background:#FED64D;padding:0">
      <card_list> </card_list>
    </v-col>
    </v-row>
    <v-row row style="align-contents:center;margin:0;">
      <v-col
      cols="12" style="margin-top:0px">
      <alarm></alarm>
      </v-col>
    </v-row>
    <v-row align-content="center" row fill-height>
      <v-col
      cols="12" style="margin-top:0px">
      </v-col>
      <!-- <card_list> </card_list> -->
      <register-github-member :dialog="ifLogin" @onLogin="onLogin_2"></register-github-member>
    </v-row>
  </v-container>
  </v-row>
  </template>
<script>
import card_list from '../components/card_list';
import alarm from '../components/alarm';
import RegisterGithubMember from '../components/yoon/RegisterGithubMember';
export default {
  name: 'HelloWorld',
  components : {
    card_list,
    alarm,
    RegisterGithubMember,
  },
  data(){
    return {
      modal : false,
      message : '',
      dialog: false,
      contents:null,
      ifLogin:false,
    }
  },
  methods:{

    onLogin_2(data){
      console.log("<<<<<<<<<<<<<<");
      this.ifLogin = data;
    }
  },
  mounted(){
  },
  watch:{
    ifLogin(){
      console.log(this.ifLogin);
    }
  }
}
</script>

