<template>
    <v-card
        style="background:#FFBF00">
        <v-row>
            <v-col
            cols="5" style="margin:auto">
                <v-img :src="content.img" aspect-ratio ="0.8" width="100%"/>
            </v-col>
            <v-col
            cols="7" style="margin:auto">
                <v-card-title primary-title style="width:100%;color:black; font-size:2rem;">
                    <div class="text-truncate">
                    {{content.title}}
                    </div>
                </v-card-title>
                <v-card-text style="color:black; font-size:1.5rem">
                    <p class="text"> 모집시작시간 </p>
                    <p class="text">{{start_y}}년 {{start_m}}월 {{start_d}}일 {{start_t}}시</p>
                    <p class="text"> 모집시간종료 </p>
                    <p class="text">{{end_y}}년 {{end_m}}월 {{end_d}}일 {{end_t}}시</p>
                    <p class="text"> 장소 </p>
                    <p class="text">{{content.location}}</p>
                </v-card-text>
            </v-col>
        
        </v-row>
    </v-card>
</template>
<script>
export default {
    name:'cards',
    props:{
        content: {type:Object}
    },
    data() {
        return{
        start_y : '',
        start_m : '',
        start_d : '',
        start_t : '',
        end_y : '',
        end_m : '',
        end_d : '',
        end_t : ''
        }
    },
    mounted(){
        this.setting()
    },
    methods:{
        setting(){
            this.start_y = this.content.start.slice(0,4)
            this.start_m = this.content.start.slice(4,6)
            this.start_d = this.content.start.slice(6,8)
            this.start_t = this.content.start.slice(8, 12)
            this.end_y = this.content.end.slice(0,4)
            this.end_m = this.content.end.slice(4,6)
            this.end_d = this.content.end.slice(6,8)
            this.end_t = this.content.end.slice(8,12)
        }
    }
}
</script>