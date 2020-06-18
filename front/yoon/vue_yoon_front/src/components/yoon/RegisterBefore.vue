<template>
  <v-row justify="center">
    <v-dialog  v-model="dialog" persistent max-width="1000px" @keydown.esc="ifClickESC()">
      <v-card justify-center>
        <v-img :aspect-ratio="2" src="https://cdn.pixabay.com/photo/2015/10/02/10/36/the-background-968350_1280.jpg">
        <v-layout align-center fill-height>
                <v-flex >
                    <v-row style="margin:10px;" j>
                    <v-col cols="12">
                        <v-col height="1px" cols="8" style="margin:auto auto 0px auto;">
                            <span style="color:white">ID</span><v-text-field  background-color="#EEEEEE" outlined v-model="uid" required></v-text-field>
                        </v-col>
                        <v-col cols="8" style="margin:0px auto auto auto;">
                            <span style="color:white">PWD</span><v-text-field  background-color="#EEEEEE" type="password" outlined v-model="pwd"  required></v-text-field>
                        </v-col>
                        </v-col>                    
                    </v-row>
                    <v-row style="margin:10px;">
                        <v-btn class="button" style="margin:auto;" width="350" height="50" @click="getGithubCode()">
                             <img class='image' src='https://github.githubassets.com/images/modules/logos_page/Octocat.png' />
                            GtiHub Login
                            </v-btn>
                    </v-row>
                    <v-row>
                        <v-btn class="button" @click="clickRegistMember()" style="margin:auto;" min-width="350" min-height="50">
                                회원가입
                            </v-btn>
                    </v-row>
                    <v-row>
                        <v-btn class="button" @click="btnTmp()" style="margin:auto;" min-width="350" min-height="50">
                            TMP-공모전 등록 페이지
                            </v-btn>
                        <v-btn class="button" @click="btnApply()" style="margin:auto;" min-width="350" min-height="50">
                            참여 btn
                            </v-btn>
                    </v-row>
                </v-flex>
            </v-layout>
        </v-img>
        <v-card-actions style="background-color: darkgray;">
          <v-spacer></v-spacer>
        <v-layout justify-center>
            <v-flex >  
                <v-btn style="margin:auto 3px auto auto;" width="3vw" color="#424242" large dark @click="onLogin()">Close</v-btn>
                <v-btn style="margin:auto auto auto 3px;" width="3vw" color="#F9A825" large dark @click="clickLoginBtn()">Login</v-btn>
            </v-flex>
        </v-layout>
        </v-card-actions>
      </v-card>
      
    </v-dialog>
  </v-row>
</template>

<script>

import http from '../../http-common'

export default {
    components:{
    },
    props:{
        dialog:Boolean,
    }
    ,
    data(){
        return{
            uid: '',
            pwd: '',
        }
    },
    methods:{
        getGithubCode(){
            //window.location.href = "https://github.com/login/oauth/authorize?redirect_uri=http://localhost:8080/github&client_id=7038e018caed836c5c38";
            window.location.href = "https://github.com/login/oauth/authorize?redirect_uri=http://localhost:8081/&client_id=7038e018caed836c5c38&scope=repo,user";
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
        clickLoginBtn () {
                http.post('/login', { 
                    email : this.uid, 
                    pwd : this.pwd })
                    .then(response => {
                        if (response.data.state === "succ") {
                            alert('로그인 성공');
                            console.log(response.data);
                            // document.cookie = `accessToken=${response.data.accessToken}`;
                            // http.defaults.headers.common['x-access-token'] = response.data.accessToken;
                            document.cookie = `accessToken=${response.data.name}`;
                            http.defaults.headers.common['x-access-token'] = response.data.name;
                        }else{
                            alert('로그인 실패');
                        }
                    });
                    return this.onLogin();
            },
            clickRegistMember(){
                this.onLogin();
                return this.$router.push({name:'registmemberpage'});
            },
            onLogin(){
                this.$emit('onLogin',false);
            }
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
