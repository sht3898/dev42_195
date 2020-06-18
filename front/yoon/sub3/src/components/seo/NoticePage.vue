<template>
    <v-container>
        <h1 style="text-align: center; margin: 30px">
            공지사항
        </h1>
        <br>
        <v-row dense>
            <v-col cols="1"><h3>공지번호</h3></v-col>
            <v-col cols="9"><h3>제목</h3></v-col>
            <v-col cols="2"><h3>작성날짜</h3></v-col>
        </v-row>
        <hr>
        <hr>
        <v-row
        v-for="notice in notices.slice(10*(page-1),10*(page))"
        :key="notice.key"
        >
            <v-col cols="1"><p style="text-align: center;">{{notice.notice_id}}</p></v-col>
            <v-col cols="9"><p>{{notice.title}}</p></v-col>
            <v-col cols="2"><p>{{notice.date}}</p></v-col>
            <v-col cols="12"><hr></v-col>
        </v-row>
        <div class="text-center">

    <v-pagination
      v-model="page"
      :length="parseInt(notices.length / 10) + 1"
      :total-visible="5"
    ></v-pagination>

  </div>
    </v-container>
</template>
<script>
import http from '../../http-common'
export default {
    name: 'NoticePage',
    components: {
        
    },
    data() {
        return {
            page : 1,
            notices:[
                // {title: '정대윤 관련 긴급사항', content: '오늘은 대윤갓의 생일입니다.', date: '20200206', type: 1},
                // {title: '홍순범 관련 공지사항', content: '지각했읍니다.', date: '20200206', type: 0},
                // {title: '정대윤 관련 긴급사항', content: '오늘은 대윤갓의 생일입니다.', date: '20200206', type: 1},
                // {title: '홍순범 관련 공지사항', content: '지각했읍니다.', date: '20200206', type: 0},
                // {title: '정대윤 관련 긴급사항', content: '오늘은 대윤갓의 생일입니다.', date: '20200206', type: 1},
                // {title: '홍순범 관련 공지사항', content: '지각했읍니다.', date: '20200206', type: 0},
                // {title: '정대윤 관련 긴급사항', content: '오늘은 대윤갓의 생일입니다.', date: '20200206', type: 1},
                // {title: '홍순범 관련 공지사항', content: '지각했읍니다.', date: '20200206', type: 0},
            ],
        }
    },
    methods: {
        init(){
            http.get('./getNotice')
            .then(message =>{
                this.notices = message.data;
                console.log(this.message)
            })
        }
    },
    mounted() {
        this.init()
    },

}
</script>