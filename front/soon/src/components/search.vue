<template>
        <v-card @click="clickCard()">
            <v-img :src="contents.img" height="45vh"></v-img>
            <v-card-title primary-title>
                <!-- <div :id="color" style="background:black;width:30px;height:30px;border-radius:75px"/><div class="headline font-weight-bold text-truncate">{{contents.title}}</div> -->
                <v-row>
                <v-col
                cols="2"
                :id="color" 
                :style="`background:${init()};border-radius:75px`">
                </v-col>
                <v-col
                cols="9"
                class="headline font-weight-bold text-truncate">
                {{contents.title}}
                </v-col>
                </v-row>
            </v-card-title>
            <v-card-text>
                <p class="grey--text text-center"> 대회 기간: {{contents.start}} ~ {{contents.end}}</p>
                <p class="grey--text text-center"> 모집 기간: {{contents.applyStart}} ~ {{contents.applyEnd}}</p>
                <p class="grey--text text-center">{{contents.price}}</p>
            </v-card-text>
          
        </v-card>
</template>
<script>
export default {
    name:'search',
    props:{
        contents:{type:Array}
    },
    data(){
        return{
        }
    },
    methods:{
        clickCard(){
            sessionStorage.setItem("contents", JSON.stringify(this.contents))            
            this.$router.push({name:'detailpage'})
        },
        init(){
           var now = new Date()
           var today_y = now.getFullYear().toString();
           var today_m = now.getMonth() + 1;
           if (today_m < 10){
               today_m = '0' + (today_m).toString()
           } else{
               today_m = (today_m).toString()
           }
           var today_d = now.getDate();
           if (today_d < 10){
               today_d = '0' + (today_d).toString()
           }else{
               today_d = (today_d).toString()
           }
            var now_date = parseInt(today_y + today_m + today_d);
            var content_start = parseInt(this.contents.start.split('-').join(''));
            var content_end = parseInt(this.contents.end.split('-').join(''));
            var content_a_start = parseInt(this.contents.applyStart.split('-').join(''));
            var content_a_end = parseInt(this.contents.applyEnd.split('-').join(''));
            if (now_date > content_end){
                return "red"
            }else if(content_start <= now_date && now_date <= content_end){
                return "yellow"
            }else if(content_a_start <= now_date && now_date <= content_a_end){
                return "green"
            }else{
                return "black"
            }   
        }
    },
    mounted(){
        console.log(this.contents)
        this.init()
    }
   
}
</script>
