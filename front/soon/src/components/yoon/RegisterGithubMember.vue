<template>
  <v-row justify="center">
    <v-dialog  v-model="dialog" persistent max-width="1000px" @keydown.esc="ifClickESC()">
      <v-card justify-center>
        <v-img :aspect-ratio="2" src="https://cdn.pixabay.com/photo/2015/10/02/10/36/the-background-968350_1280.jpg">
        <v-layout align-center fill-height>
                <v-flex >
                    <v-row style="margin:10px;" >
                    <v-col cols="12">
                        <v-col height="1px" cols="8" style="margin:auto auto 0px auto;">
                            <span style="color:white">name</span><v-text-field solo-inverted v-model="name" outlined required></v-text-field>
                        </v-col>
                        <v-col cols="8" style="margin:0px auto auto auto;">
                            <span style="color:white">birth</span><v-text-field  solo-inverted type="number" outlined v-model="birth"  required></v-text-field>
                        </v-col>
                        <v-col height="1px" cols="8" style="margin:auto auto 0px auto;">
                            <span style="color:white">phone</span><v-text-field  solo-inverted  v-model="phone" outlined required></v-text-field>
                        </v-col>
                        </v-col>                    
                    </v-row>
                </v-flex>
            </v-layout>
        </v-img>
        <v-card-actions style="background-color: darkgray;">
          <v-spacer></v-spacer>
        <v-layout justify-center>
            <v-flex >  
                <v-btn style="margin:auto 3px auto auto;" width="3vw" color="#424242" large dark @click="onLogin()">Close</v-btn>
                <v-btn style="margin:auto auto auto 3px;" width="3vw" color="#F9A825" large dark @click="clickSaveBtn()">SAVE</v-btn>
            </v-flex>
        </v-layout>
        </v-card-actions>
      </v-card>
      
    </v-dialog>
  </v-row>
</template>

<script>

import git from '../../http-common'

export default {
    components:{
    },
    props:{
        dialog:Boolean,
    }
    ,
    data(){
        return{
            name: "",
            birth: "",
            phone: "",
            token: ""
        }
    },
    methods:{
        getGithubCode(){
            //window.location.href = "https://github.com/login/oauth/authorize?redirect_uri=http://localhost:8080/github&client_id=7038e018caed836c5c38";
            window.location.href = "https://github.com/login/oauth/authorize?redirect_uri=http://localhost:8080/&client_id=7038e018caed836c5c38&scope=repo,user";
            //github에서 다른 정보를 가져오기 위해 파라메터 추가

            return false;
        },
        ifClickESC(){
           this.onLogin();
        },  
        isLogin(data){
            if(data == true){
                this.onLogin();
            }
        },
        btnTmp(){
            this.$router.push('registhackaton');
        },
        btnApply(){
            this.$router.push('applyhackatonpage');
        },
        onLogin(){
                this.$emit('onLogin',false);
                this.name = "";
                this.birth = "";
                this.phone = "";
        },
        clickSaveBtn(){
            
        this.token = sessionStorage.getItem("github-access-token");
        let dto = {
                birth: this.birth,
                name: this.name,
                phone: this.phone,
                token: this.token
        }
        console.log(dto);
            git.post("/signup/github", {
                birth: this.birth,
                name: this.name,
                phone: this.phone,
                token: this.token
        })
        .then(response => {
                console.log("clickSaveBtn() >>> "+response.data.state);
                if(response.data.state=="succ"){
                  alert("회원 가입 성공!!!!");
                  return this.onLogin();
                }
        })
        .catch(err =>{
            console.log(err);
        })
    }
    },
    mounted(){
    }
}
</script>

<style scoped>
    .button {
        font-weight: bold;
        background:darkgray;
        color:black; border-radius: 10px; margin-top: 10px;
        display: flex; align-items: center; justify-content: center;
    }
    .button:hover {
        cursor: pointer;
    }
    .image {
        width: 40px; height: auto;
    }
</style>
