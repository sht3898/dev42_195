<template>
  <v-row  justify="center">
      <v-card width="60vw" img ="https://www.onlygfx.com/wp-content/uploads/2017/07/yellow-watercolor-brush-stroke-4.png">
        <v-card-title >
          <span class="headline" style="margin:auto ">회원 가입</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="6" sm="6">
                <v-text-field solo-inverted v-model="id" :color="checkIDColor" label="Email_ID*" required></v-text-field>
              </v-col>
              <v-col cols="6" sm="6">
                <v-select
                  :color="checkMailColor"
                  v-if="!ismailOther"
                  :items="['naver.com', 'gmail.com', 'hanmail.net', 'hotmail.com','직접입력']"
                  label="*SelectMail"
                  v-model="mail"
                  required
                ></v-select>
                <v-text-field solo-inverted id="_choosenOther" :color="checkMailColor" @blur="blurOtherChoose()" v-model="mail" v-if="ismailOther" label="직접입력*" required></v-text-field>
              </v-col>     
               <v-col  xs = "12" sm="12" md ="12" lg="12">
                <v-btn class="myBtn" :color="duplicateCheckColor" @click="duplicateCheck()">중복 체크</v-btn>
              </v-col>
                <v-col xs = "6" sm="6" md ="6" lg="6" style="margin:auto">
                  <v-text-field :color="checkPwdColor" v-model="pwd" label="Password*" type="password" required></v-text-field>
                </v-col>
                <v-col xs = "6" sm="6" md ="6" lg="6" style="margin:auto">
                  <v-text-field solo-inverted v-model="pwd_conf" label="PasswordConf*" type="password" required></v-text-field>
                </v-col>
                <v-col xs = "12" sm="12" md ="12" lg="12">
                  <v-btn class="myBtn" :color="pwd_conf_CheckColor">암호 확인</v-btn>
                </v-col>
              <v-col cols="12" sm="6" md="5">
                <v-text-field solo-inverted :color="checkNameColor" v-model="name" label="이름*" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="5">
                <v-text-field  :color="checkBirthToColor" type="number" v-model="birth" label="생년월일(8자리)" required></v-text-field>
              </v-col>
              
              <v-col cols="12" sm="6" md="2">
              </v-col>
              <v-col cols="12">
                  <v-textarea 
                  filled auto-grow rows="4" row-height="30" shaped
                  v-model="info" label="Info" required></v-textarea>
              </v-col>
            </v-row>
            <v-row>
                <v-btn style="margin:auto 3px auto auto;" width="3vw" color="warning" large dark @click="false">Close</v-btn>
                <v-btn style="margin:auto auto auto 3px;" width="3vw" color="success" large dark @click="clickSaveBtn()">Save</v-btn>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
  </v-row>
</template>

<script>
    import http from '../../http-common';
export default {
  name: 'registmemberpage',
  props: {
    msg: String,
    showModal : Boolean,
  },
  data(){
    return{
      ismailOther : false,
      mail:"",
      id : "",
      pwd: "",
      name: "",
      pwd_conf:"",
      info:"",
      birth : Number,
      checkBirthToColor : "red",
      checkIDColor : "red",
      checkMailColor : "red",
      checkPwdColor : "red",
      checkNameColor : "red", 
      duplicateCheckColor : "red",
      pwd_conf_CheckColor:"red",
    }
  },
  methods:{
    duplicateCheck(){
      if(this.checkIDColor==="blue"&&this.checkMailColor==="blue"){
      http
        .get("/checkEmail/"+this.id+"@"+this.mail)
        .then(response => {
            if(response.data.state=="succ"){
              this.duplicateCheckColor = "blue";
            }else{
              this.duplicateCheckColor = "red";
            }
        });
      }else{
        alert("email을 다시입력해 주시요");
      }
    },
    clickSaveBtn(){
      if(this.checkBirthToColor==="blue"&&this.checkIDColor==="blue"&&this.checkMailColor==="blue"&&this.checkPwdColor==="blue"
      &&this.checkNameColor==="blue"&&this.duplicateCheckColor==="blue"){
        http
          .post("/addMember", {
          birth: this.birth,
          email: this.id+"@"+this.mail,
          info: "test1",
          job: "test1",
          name: this.name,
          phone: "test1",
          pwd: this.pwd
        })
        .then(response => {
                console.log("clickSaveBtn() >>> "+response.data.state);
                if(response.data.state=="succ"){
                  alert("회원 가입 성공!!!!");
                }
        });
    }else{
      alert("정보를 정확하게 입력해 주세요!");
    }
    },
    blurOtherChoose(){
      if(this.mail===""){
        this.ismailOther = false;
      }
    },
    checkDate(){
        let min = Date.parse("1900-01-01");
        let max = Date.parse("2019-01-01");
        let dateString = this.birth.toString();
        console.log(typeof(dateString));
        let year = dateString.substring(0,4);
        let month = dateString.substring(4,6);
        let day = dateString.substring(6,8);
        let date = Date.parse(year+"-"+month+"-"+day);
        if(dateString.length==8&&isNaN(date)==false && date <= max && date >= min){
          this.checkBirthToColor = "blue";
        }else{
          this.checkBirthToColor = "red";
        }
    },
    checkID() {
      let regExp = /^[a-zA-Z0-9]{4,12}$/;
      if (regExp.test(this.id)) return true;
      else return false;
      //
    },
    checkMail(){
      let regExp = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (regExp.test(this.mail)) return true;
      else return false;
    },
    checkPwd(){
      let regExp = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
      if (regExp.test(this.pwd)) return true;
      else return false;
    },
    checkName(){
      let regExp = /^[가-힣]{2,4}$/;
      if(regExp.test(this.name)) return true;
      else return false;
    },
    isGithubLogin(){ //github login
      let code = location.search.split('code=')[1];
      var params = new URLSearchParams();
      params.append("code", code);
      http.post('', params)
      .then(response => {
        if(response.data.state=="succ"){
          console.log("성공!");
          document.cookie = `accessToken=${response.data.name}`;
          http.defaults.headers.common['x-access-token'] = response.data.name;
        }else{
          console.log("없는 회원입니다. 회원가입을 진행합니다. ");
        }
      }).catch(err => {
        console.log(err);
        return;
      });

      return this.isSituationRegister();
    },
    getCookie(cname) {
      var name = cname + "=";
      var decodedCookie = decodeURIComponent(document.cookie);
      var ca = decodedCookie.split(';');
      for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
          c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
          return c.substring(name.length, c.length);
        }
      }
      return "";
    },
    checkPwdConf(){
      if(this.pwd_conf===this.pwd&&this.pwd!==""&&this.checkPwdColor!=="red"){
        return true;
      }
      return false;
    },
  }, 
  watch:{
    mail(){
      if(this.mail==="직접입력"){
        this.ismailOther = true;
        this.mail = "";
      }
      if(this.checkMail()){
        this.checkMailColor = "blue";
      }else{
        this.checkMailColor = "red";
      }
      console.log(this.mail);
    },
    birth(){
      this.checkDate();
    },
    id(){
      if(this.checkID()){
        this.checkIDColor="blue";
      }else{
        this.checkIDColor="red";
      }
    },
    name(){
      if(this.checkName()){
        this.checkNameColor="blue";
      }else{
        this.checkNameColor="red";
      }
    },
    pwd(){
      if(this.checkPwd()){
        this.checkPwdColor="blue";
      }else{
        this.checkPwdColor="red";
      }
      if(this.checkPwdConf()){
        this.pwd_conf_CheckColor = "blue";
      }else{
        this.pwd_conf_CheckColor = "red";
      }
    },
    pwd_conf(){
      if(this.checkPwdConf()){
        this.pwd_conf_CheckColor = "blue";
      }else{
        this.pwd_conf_CheckColor = "red";
      }
    },
  },
  updated(){
    if(this.ismailOther===true&&this.mail===""){
      document.getElementById("_choosenOther").focus();
    }
    
  },
  mounted(){
    this.isGithubLogin();
  }
  
}
</script>
<style>

  .overline.align-self-center{
    display:inline-block;
    padding-left:15px;
  }
</style>