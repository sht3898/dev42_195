<template>
    <div style="border: grey solid 1px; margin-top:30px">
            <v-col
            cols="12"
            style="text-align:center;padding:0"
            >
    <v-layout class="headline mb-3" style="font-weight:bold;font-style:italic;background:lightgrey;font-size:3rem !important; height:5rem">
      <v-flex style="margin:auto"><a href="/noticepage" style="text-decoration:none">공지사항</a></v-flex>
    </v-layout>
    </v-col>
        <v-row>
            <v-col
            cols="1">
            <h3 style="text-align:center;">번호</h3>
            </v-col>
            <v-col
            cols="3">
            <h3 style="text-align:center;">제목</h3>
            </v-col>
            <v-col
            cols="5">
            <h3 style="text-align:center;">내용</h3>
            </v-col>
            <v-col
            cols="3">
            <h3 style="text-align:center;">작성날짜</h3>
            </v-col>
        </v-row>
        <div class="text-center" v-for="i in 3" :key="i" style="padding:0">
            <v-sheet color="#FED64D" v-if="alarams[alarams.length-i].ncheck">
                <v-row >
                    <v-col
                    cols="1">
                    {{alarams[alarams.length-i].noticeId}}
                    </v-col>
                    <v-col
                    cols="3">
                        <div class="text-truncate">
                        {{alarams[alarams.length-i].title}}
                        </div>
                    </v-col>
                    <v-col
                    cols="5">
                        {{alarams[alarams.length-i].content}}
                    </v-col>
                    <v-col
                    cols="3">
                        {{alarams[alarams.length-i].date}}
                    </v-col>
                </v-row>
            </v-sheet>
            <v-sheet color="gray dark-2" v-else>
                   <v-row>
                    <v-col
                    cols="1">
                    {{alarams[alarams.length-i].noticeId}}
                    </v-col>
                    <v-col
                    cols="3">
                        <div class="text-truncate">
                        {{alarams[alarams.length-i].title}}
                        </div>
                    </v-col>
                    <v-col
                    cols="5">
                        {{alarams[alarams.length-i].content}}
                    </v-col>
                    <v-col
                    cols="3">
                        {{alarams[alarams.length-i].date}}
                    </v-col>
                </v-row>
            </v-sheet>

        </div>
    </div>
</template>
<script>
// import axios from 'axios'
import http from '../http-common'
export default {
    name:'alarm',
    data(){
        return{
            alarams:[
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 1번입니다',date:'20200120',user:'admin'},
                // {kind:false, content:'<필독> 홍순범 정대윤 11시내로 모든걸 끝내야함',date:'20191220',user:'admin'},
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 2번입니다',date:'20191215',user:'admin'},
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 3번입니다',date:'20191218',user:'admin'},
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 4번입니다',date:'20191228',user:'admin'},
                // {kind:false, content:'<긴급> 홍순범 정대윤 끝나고 카페 가야함',date:'20191230',user:'admin'},
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 5번입니다',date:'20191231',user:'admin'},
                // {kind:true, content:'<공지> 이것은 공지를 테스트하기위한 6번입니다',date:'20200105',user:'admin'},
            ]
        }
    },
    mounted(){
        this.setting()
    },
    methods:{
        setting(){
            //  axios.get('http://127.0.0.1:8000/board/')
            http.get('/getNotice')
            .then(message =>{
                // this.alarams = message.data[message.data.length-1]
                this.alarams = message.data
                // console.log(message.data[message.data.length-1])
                console.log(this.alarams)
            })
    }
}
}
</script>