<template >

<v-row justify="center" style="margin-bottom:5vw">
    <v-card width="80vw" img ="https://www.onlygfx.com/wp-content/uploads/2017/07/yellow-watercolor-brush-stroke-4.png">
        <v-container>
        <v-card-title >
            <span class="headline" style="margin:auto ">공모전 & 해커톤 등록</span>
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="6" md="6" sm="12">
                        <v-text-field  solo-inverted ba v-model="title" label="제목*" required></v-text-field>
                    </v-col>
                    <v-col cols="6" md="6" sm="12">
                        <v-text-field solo-inverted v-model="host" label="주최*" required></v-text-field>
                    </v-col>
                    <v-col cols="6">
                        <v-text-field  v-model="price" type="number" label="정원*" required></v-text-field>
                    </v-col>
                    <v-col cols="6"> 
                        <v-text-field   v-model="price" type="number"  label="참가비" required></v-text-field>
                    </v-col>
                    <v-col  lg="6" md="6" sm="12" xs="12">
                        <v-text-field solo-inverted readonly label="장소" :value="address" @click="daumPostcode()"></v-text-field>
                    </v-col>
                    <v-col  lg="4" md="4" sm="8" xs="8">
                        <v-text-field solo-inverted v-model="detailAddress" :value="detailAddress" label="상세 주소" id="_detailAddress" ></v-text-field>
                    </v-col>
                    <v-col lg="2" md="2" sm="4" xs="8">
                        <v-checkbox :readonly="NoplaceCheckBox"
                        
                         v-model="NoplaceCheckBox" label="장소 미정"></v-checkbox>
                    </v-col>
                    <v-col cols="6">
                      <calendar @updateColor: :color="applyStartColor" title="접수 시작" @updateDate="updateApplyStart"></calendar>
                    </v-col>
                    <v-col cols="6">
                      <calendar :color="applyEndColor" title="접수 마감" @updateDate="updateApplyEnd"></calendar>
                    </v-col>
                    <v-col cols="6">
                      <calendar :color="startColor" title="시작" @updateDate="updateStartDate"></calendar>
                    </v-col>
                    <v-col cols="6">
                      <calendar :color="endColor" title="마감" @updateDate="updateEndDate"></calendar>
                    </v-col>
                    <v-col cols="12">
                    <v-col cols="6" style="margin:auto">
                        <v-img  v-if="url!=''" :src="url"></v-img>
                    </v-col>
                    </v-col>
                    <v-col cols="12">
                        <v-file-input outlined type="file" id="file" ref="file" accept="image/*" v-model="chosenFile" v-on:change="handleFileUpload"/>
                    </v-col>
                    <v-col cols="12">
                        <v-textarea 
                        filled auto-grow rows="4" row-height="30" shaped
                        v-model="info" label="Info" required></v-textarea>
                    </v-col>
                </v-row>
                <v-row>
                <v-btn style="margin:auto 3px auto auto;" width="3vw" color="warning" large dark @click="dialog = false">Close</v-btn>
                <v-btn style="margin:auto auto auto 3px;" width="3vw" color="success" large dark @click="clickSaveBtn()">Save</v-btn>
                </v-row>
            </v-container>
        </v-card-text>
        </v-container>
        <v-card-actions>
            <v-spacer></v-spacer>

        </v-card-actions>
    </v-card>
</v-row>
</template>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
import Calendar from './Calendar';
import http from '../../http-common'
export default {
    components:{
        Calendar,
    },
    data(){
        return {
      ismailOther : false,
      title : "",
      host:"",
      people_num:Number,
      price:Number,
      file : null,
      chosenFile:null,
      startDate : String,
      endDate : String,
      apply_start : String,
      apply_end : String,
      showPreview: false,
      imagePreview: '',
      address:'',
      detailAddress:'',
      NoplaceCheckBox:false,
      url:"",
      info : "",
	titleColor:'red',
	hostColor:'red',
	peopleNumColor:'red',
	priceColor:'red',
	applyStartColor:'red',
	applyEndColor:'red',
	startColor:'red',
	endColor:'red',
        }
    },
  methods:{
    updateStartDate(newDate){
      this.startDate = newDate;
      console.log(this.startDate);
    },
    updateEndDate(newDate){
      this.endDate = newDate;
      console.log(this.endDate);
    },
    updateApplyStart(newDate){
      this.apply_start = newDate;
      console.log(this.apply_start);
    },
    updateApplyEnd(newDate){
      this.apply_end = newDate;
      console.log(this.apply_end);
    },
    handleFileUpload(e){
      this.file = e;
      if(e===undefined||e===""||e===null){
        this.url = "";
        return;
      }
      if (!this.chosenFile) {this.file = "No File Chosen"}
      var reader = new FileReader();
      reader.readAsDataURL(this.chosenFile);
      reader.onload = () => {
        this.url = reader.result;
      }
    },
    daumPostcode(){
      if(this.NoplaceCheckBox){
        return;
      }
      let addr = "";
      let func = this.callbackFunc;
        new daum.Postcode({
            oncomplete: function(data) {
                if (data.userSelectedType === 'R') {
                    addr = data.postcode+", "+data.address+", "
                }
                return func(addr);
            },
            onclose: function(state) {
              if(state === 'FORCE_CLOSE'){
                document.getElementById("_detailAddress").focus();          
              }
            }
        }).open()
    },
    callbackFunc(data){
      this.address = data;
      console.log(">>>>>>>>>>>>>>>>>")
      document.getElementById("_detailAddress").focus();
    },
    ifClickESC(){
      this.dialog = false;
    },
    registCheck(){
      //정확하게 입력하였는지 채킹해야된다.
      return false;
    },
    clickSaveBtn(){
      let formdata = new FormData();
      if(this.address===""){
        this.address="장소 미정";
      }
      let dto = {
        title:this.title,
        host:this.host,
        apply_start:this.apply_start,
        apply_end:this.apply_end,
        start:this.startDate,
        end:this.endDate,
        people_num:this.people_num,
        price:this.price,
        info:this.info,
        location:this.address+this.detailAddress,
        email:'data@data'
        };
        dto = JSON.stringify(dto);
      console.log(dto);
      formdata.append('file',this.chosenFile);
      formdata.append('dto_str',dto);
      if(this.registCheck()){
        return;
      }
      
      http.post('/addBoard',formdata,{
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      )
      .then(response => {
        if (response.data.state === "succ") {
            alert('성공');
        }else{
          alert('실패');
        }
      }).catch(()=>{
        console.log("실패")
      });
      return console.log(dto);
    }
  }, 
  watch:{
    NoplaceCheckBox(){
      if(this.NoplaceCheckBox){
        this.address = "";
        this.detailAddress = "";
      }
    },
  }
}
</script>