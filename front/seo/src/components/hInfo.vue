<template>
   <v-row>
       <v-col
       cols="12">
        <p style="font-weight:bold;font-size:3rem;border-bottom:solid black 2px"> 기간</p>
       </v-col>
       <v-col
       cols="2">
        <p style="font-weight:bold; font-size:2rem;"> 모집기간 </p>
       </v-col>
       <v-col
       cols="10">
        <p style="font-size:2rem;">{{content.applyStart}} ~ {{content.applyEnd}}</p>
       </v-col>
       <br>
       <v-col
       cols="2">
       <p style="font-weight:bold; font-size:2rem;"> 대회기간 </p>
       </v-col>
       <v-col
       cols="10">
       <p style="font-size:2rem;"> {{content.start}} ~ {{content.end}}</p>
       </v-col>
       <v-col
       cols="12">
       <p style="font-weight:bold;font-size:3rem; border-bottom:solid black 2px">위치 </p>
       <v-col
       cols="10">
       <p style="font-size:2rem;">{{content.location}}</p>
       </v-col>
       </v-col>
       <v-col
       cols="12">
       <p style="font-weight:bold;font-size:3rem;border-bottom:solid black 2px"> 추가 정보</p>
       </v-col>
       <v-col
       cols="12">
       <span style="font-size:2rem">{{content.info}}</span>
       </v-col>
   </v-row>
</template>
<script>
export default {
    name:'hInfo',
    props:{
        content:{type:Object}
    }
}
</script>