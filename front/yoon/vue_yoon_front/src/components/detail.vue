<template>
<v-row>
<v-col>
  <v-container style="width:75%; margin:auto;border:black solid 1px">
    <v-row no-gutter>
      <v-col
        cols="6" >
        <v-container>
          <v-img
          :src="hackatonData.img_url"
          style="margin:auto"
          width="100%"
          aspect-ratio="0.8"
          />
        </v-container>
      </v-col>
      <v-col
        cols="6">
        <v-container style="border:black solid 1px">
            <v-row style="height: 10vw; align-items:center; border:black solid 1px;">
                <div class="headline font-weight-bold text-truncate" style="align-items:center;margin:auto;">{{hackatonData.i_title}}</div>
            </v-row>
            <v-row style="height: 10vw; align-items:center;font-size:3vmin; border:black solid 1px;">
                <div style="margin:auto"> 모집 기간 {{hackatonData.s_date}} ~ {{hackatonData.e_date}}</div>
            </v-row>
            <v-row style="height: 10vw; align-items:center;font-size:3vmin;border:black solid 1px;">
                <div style="margin:auto"> 개최자 {{hackatonData.host}}</div>
            </v-row>
            <v-row style="height: 10vw; align-items:center;font-size:3vmin">
                <v-row style='align-items:center'>
                  <v-col
                  cols="6"
                  >
                    <div style="text-align:center;font-size:3vmin;margin:auto" class="text-truncate"> 위치 : {{hackatonData.location}}</div>
                  </v-col>
                  <v-col
                  cols="6">
                    <div style="text-align:center;font-size:2.5vmin;margin:auto">인원수 : {{hackatonData.people_num}}</div>
                  </v-col>
              </v-row>
            </v-row>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
  <v-container height="10vw" style="width:75%">
    <v-flex>
      <v-layout>
        <a :href="$store.state.contents.img_url" target="__blank" style="text-decoration:none;margin-left:auto">
          <v-btn
          prima
          color="orange" 
          style=" height: 3rem; width: 15rem; padding: 0; font-size: 2rem; font-weight: bolder">신청하기</v-btn></a>
      </v-layout>
    </v-flex>
  </v-container>
  </v-col>
  <v-col
    cols="12">
    <v-tabs
    v-model="tab"
    class="elevation-2"
    grow>
    <v-tabs-slider></v-tabs-slider>
      
    </v-tabs>
  </v-col>
</v-row>
</template>
<script>
export default {
    name:'detail',
    data(){
        return{
          tab:null,
          tabs:3,
          hackatonData : {
          }
        }
    },
    mounted(){
      this.hackatonData = JSON.parse(sessionStorage.getItem('contents'))
    }
   
}
</script>