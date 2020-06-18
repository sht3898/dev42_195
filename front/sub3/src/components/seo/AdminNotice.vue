<template>
    <v-container>
        <div style="text-align:right">
        <AddNoticeModal @reset="init"></AddNoticeModal>

        </div>
        <v-simple-table fixed-header height="30rem">
        <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">제목</th>
          <th class="text-left">내용</th>
          <th class="text-left">작성날짜</th>
          <th class="text-left">필수여부</th>
          <th class="text-left">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="notice in notices.slice(10*(page-1),10*(page))" :key="notice.key">
          <td><p>{{notice.title}}</p></td>
          <td><p>{{notice.content}}</p></td>
          <td><p>{{notice.date}}</p></td>
          <td>
             <p v-if="notice.ncheck==1">필수</p>
             <p v-else></p>
          </td>
          <td><v-btn color="red" text @click="deleteNotice(notice.noticeId)">Delete</v-btn></td>
        </tr>
      </tbody>
    </template>
    </v-simple-table>
    </v-container>
</template>
<script>
import http from '../../http-common'
import AddNoticeModal from './AddNoticeModal.vue'
export default {
    name: 'AdminNotice',
    components: {
        AddNoticeModal
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
        deleteNotice(noticeId){
            console.log(noticeId)
            http.delete('./deleteNotice/' + noticeId)
            .then(response => {
                console.log(response.data)
                if(response.data.state=='succ'){
                    alert('삭제 성공')
                    this.init()
                }else{
                    alert('삭제 실패')
                    this.init()
                }
            })
        }
    },
    mounted() {
        this.init()
    },

}
</script>