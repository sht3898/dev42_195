<template>
    <v-container>
        <h1 style="text-align: center; margin: 30px">
            공지사항
        </h1>
        <br>
        <v-simple-table fixed-header height="30rem">
        <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">공지번호</th>
          <th class="text-left">제목</th>
          <th class="text-left">내용</th>
          <th class="text-left">필수</th>
          <th class="text-left">작성날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="notice in notices.slice(10*(page-1),10*(page))" :key="notice.key">
          <td><p>{{notice.noticeId}}</p></td>
          <td><p>{{notice.title}}</p></td>
          <td><p>{{notice.content}}</p></td>
          <td><p v-if="notice.ncheck==1">필수</p><p v-else></p></td>
          <td><p>{{notice.date}}</p></td>
        </tr>
      </tbody>
    </template>
    </v-simple-table>
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
        },
    },
    mounted() {
        this.init()
    },

}
</script>