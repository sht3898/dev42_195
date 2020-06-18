<template>
<!-- 현택 수정 -->
<div>
  <v-card
    class="mx-auto"
    max-width="80%"
    outlined
    style="margin-top:1rem"
  >
    <v-list-item three-line>
      <v-img
      :src="hackatonData.img"
      aspect-ratio="0.7"
      max-width="30%"
      style="margin-right:1rem;"
      >
      </v-img>
      
      <v-list-item-content>
        <v-list-item-title class="headline mb-1">{{hackatonData.title}}</v-list-item-title>
        <p style="margin-top:1rem">모임장소  {{hackatonData.location}}</p>
        <p>참가비용  {{hackatonData.price}}원</p>
        <p>개최자  {{hackatonData.host}}</p>
        <p>지원현황  {{hackatonData.peopleNow}} / {{hackatonData.peopleNum}}</p>
      </v-list-item-content>
    </v-list-item>
    
    <current
      v-if="hackatonData.boardId !== undefined" 
      :boardId="hackatonData.boardId">
    </current>

    <v-card-actions style="justify-content:center">
      <v-btn
      @click="btnApply()"
      color="yellow"
      >신청하기
      </v-btn>
      <v-btn
      @click="btnBack()"
      color="warning"
      >돌아가기
      </v-btn>
    </v-card-actions>
  </v-card>
<!-- 수정1 끝-->

<!-- <v-col>
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
              > <div class="text-truncate" style="text-align:start;font-size:2.3vmin;">지원현황  {{hackatonData.peopleNow}} / {{hackatonData.peopleNum}}</div>
              </v-col>
            </v-row>
             <v-row style="height:30vmin; align-items:center;">
              <v-col
                cols="12"
              > 
              <current
              v-if="hackatonData.boardId !== undefined" 
              :boardId="hackatonData.boardId">
              </current>
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
                <v-btn
                  @click="btnBack()"
                  color="warning"
                  style=" height: 3rem; width: 15rem; font-size: 2rem; color:#424242; font-weight: bolder;margin-top:10px;margin-left:5px">돌아가기</v-btn>
              </v-col>
            </v-row>

        </v-card-text>
      </v-col>
    </v-row>
  </v-card>
  </v-col> -->

  <!-- 수정2 시작 -->
  <v-card
    class="mx-auto"
    max-width="80%"
    outlined
    style="margin-top:1rem"
  >
  <v-tabs
      v-model="tab"
      background-color="#424242"
      class="elevation-2"
      grow
    >
      <v-tab
        v-for="tab in tabs"
        :key="tab.key"
        style="color:white"
      >
        {{ tab_item[tab-1].title }}
      </v-tab>

      <v-tabs-items
      v-model='tab'>
      <v-tab-item
        v-for="item in tabs"
        :key="item.key"
        style="height:500px;overflow:scroll;overflow-x:hidden">
        <v-card flat>
          <hInfo v-if="item === 1" :content="hackatonData"></hInfo>
          <k_map v-if="item === 2" :address="hackatonData.location" style="overflow:scroll; overflow-x:hidden "></k_map>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    </v-tabs>
  </v-card>
  <comment v-if="hackatonData.boardId != undefined" :boardId="hackatonData.boardId"></comment>
  <!-- 수정2 끝 -->
</div>
</template>
<script>
import hInfo from './hInfo'
import k_map from './k_map'
import comment from './comment'
import current from './current_hackerthon'
export default {
    name:'detail',
    components:{
      hInfo,
      k_map,
      comment,
      current
    },
    data(){
        return{
          tab:0,
          tabs:2,
          hackatonData : {
          },
          tab_item:[
            {title:'해커톤 정보'},
            {title : '위치정보'}
          ]
        }
    },
    mounted(){
      this.hackatonData = JSON.parse(sessionStorage.getItem('contents'))
    
    },
    methods:{
      btnApply(){
            this.$router.push('applyhackatonpage');
      },
      btnBack(){
        this.$router.push({name:'join'})
      }
        
    }

   
}
</script>