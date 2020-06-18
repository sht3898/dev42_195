<template>
    <v-card 
    style="text-align:center;font-size:2.3vmin;height:30vmin;">
        <v-row style="margin:0">
            <v-col
            cols="4" style="border-bottom:solid grey 1px">
            팀명</v-col>
            <v-col
            cols="4"
            style="border-bottom:solid grey 1px">
                아이디어
            </v-col>
            <v-col
            cols="4"
            style="border-bottom:solid grey 1px">
                인원수
            </v-col>
        </v-row>
        <div v-if="data.length !== 0" style="height:20vmin; overflow-x:scroll">
        <v-row style="margin:0" v-for="team in data" :key="team.key">
            <v-col
            cols="4" style="border-bottom:solid grey 1px">
                {{team.teamName}}
            </v-col>
            <v-col
            cols="4" style="border-bottom:solid grey 1px">
                {{team.teamState}}
            </v-col>
            <v-col
            cols="4" style="border-bottom:solid grey 1px">
                {{team.teamMemberNum}}
            </v-col>
        </v-row>
        </div>
        <div v-else>
            <div
            cols="12"
            style="color:grey;font-size:1rem;text-align:center;margin-top:10px">
            등록한 팀이 존재하지 않습니다
            </div>
        </div>
    </v-card>  
</template>
<script>
import http from '../http-common'
export default {
    name:'current_hackerthon',
    props : {
        boardId:{type: Number}
    },
    data(){
        return {
            data : [],
            token : ''
        }
    },
    methods:{
        init(){
            http.patch('/team/teams/' + this.boardId)
            .then(response =>{
                this.data = response.data                
            })
        }
    },
    mounted(){
        this.init()
    }
}
</script>