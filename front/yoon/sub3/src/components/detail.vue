<template>
<v-row>
<v-col>
  <v-card style="width:75%; margin:auto; border-radius:15px">
    <v-row no-gutter>
      <v-col
        cols="6" >
        <v-container>
          <v-img
          :src="hackatonData.img"
          style="margin:auto"
          width="70%"
          aspect-ratio="0.7"
          />
        </v-container>
      </v-col>
      <v-col
        cols="6">
        <v-card-text>
            <v-row style="height: 3vw; align-items:center;">
                <div class="headline font-weight-bold text-truncate" style="align-items:start;"><span style="font-size:3vmin">{{hackatonData.title}}</span></div>
            </v-row>
            <v-row style="height:3vw; align-items:center;">
              <v-col
                cols="12"
              > <div class="text-truncate" style="text-align:start;font-size:2.3vmin;">모임장소  {{hackatonData.location}}</div>
              </v-col>
            </v-row>
            <v-row style="height:3vw; align-items:center;">
              <v-col
                cols="12"
              > <div class="text-truncate" style="text-align:start;font-size:2.3vmin;">참가비용  {{hackatonData.price}}원</div>
              </v-col>
            </v-row>
            <v-row style="height:3vw; align-items:center;">
              <v-col
                cols="12"
              > <div class="text-truncate" style="text-align:start;font-size:2.3vmin;">개최자  {{hackatonData.host}}</div>
              </v-col>
            </v-row>
            <v-row style="height:3vw; align-items:center;">
              <v-col
                cols="12"
              > <div class="text-truncate" style="text-align:start;font-size:2.3vmin;">지원현황  {{hackatonData.people_now}} / {{hackatonData.people_num}}</div>
              </v-col>
            </v-row>
             <v-row style="height:30vmin; align-items:center;">
              <v-col
                cols="12"
              > <v-card 
                style="text-align:center;font-size:2.3vmin;height:30vmin;">
                  <v-row style="margin:0">
                    <v-col
                    cols="4" style="border-bottom:solid grey 1px">
                    팀명</v-col>
                    <v-col
                    cols="4"
                    style="border-bottom:solid grey 1px">
                    아이디어</v-col>
                    <v-col
                    cols="4"
                    style="border-bottom:solid grey 1px">
                    인원수</v-col>
                  </v-row>
                </v-card>
              </v-col>
            </v-row>
            <v-row style="height:3vw;;">
              <v-col
                cols="12"
              > <v-btn
                  prima
                  @click="btnApply()"
                  color="yellow" 
                  style=" height: 3rem; width: 15rem; font-size: 2rem; color:#424242; font-weight: bolder;margin-top:10px;">신청하기</v-btn>
              </v-col>
            </v-row>
            
        </v-card-text>
      </v-col>
    </v-row>
  </v-card>
  
  </v-col>
  <v-col
    cols="12">
    <div
    style="width:77%;margin:auto">
    <v-tabs
      v-model="tab"
      grow
      background-color="#424242"
    >
      <v-tab
        v-for="tab in tabs"
        :key="tab.key"
        style="color:white;font-size:1.3rem"
      >
        {{ tab_item[tab-1].title }}
      </v-tab>
    </v-tabs>
    <v-tabs-items
      v-model='tab'>
      <v-tab-item
        v-for="item in tabs"
        :key="item.key"
        style="height:500px;overflow:scroll;overflow-x:hidden">
        <v-card flat>
          <hInfo v-if="item === 1" :content="hackatonData"></hInfo>
          <uInfo v-if="item === 2"></uInfo>
          <k_map v-if="item === 3" :address="hackatonData.location" style="overflow:scroll; overflow-x:hidden "></k_map>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    </div>
  </v-col>
  <v-col
  cols="12">
    <comment v-if="hackatonData.board_id!==undefined" :board_id="hackatonData.board_id"></comment>
  </v-col>
</v-row>
</template>
<script>

import hInfo from './hInfo'
import uInfo from './uInfo'
import k_map from './k_map'
import comment from './comment'
export default {
    name:'detail',
    components:{
      hInfo,
      uInfo,
      k_map,
      comment
    },
    data(){
        return{
          tab:0,
          tabs:3,
          hackatonData : {
          },
          tab_item:[
            {title:'해커톤 정보'},
            {title : '개최자 정보'},
            {title : '위치정보'}
          ]
        }
    },
    mounted(){
      this.hackatonData = JSON.parse(sessionStorage.getItem('contents'));
      console.log("detail>>------mounted-----");
      console.log(this.hackatonData.board_id);
    },
    methods:{
      btnApply(){
            this.$router.push('applyhackatonpage');
        },
    }

   
}
</script>