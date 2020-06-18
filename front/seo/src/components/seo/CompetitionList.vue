<template>
    <v-container>
        <v-simple-table fixed-header height="30rem">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">공모전명</th>
          <th class="text-left">기간</th>
          <th class="text-left">장소</th>
          <th class="text-left">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in lists" :key="item.name">
          <td @click="detailBoard(item)"><v-btn text>{{ item.title }}</v-btn></td>
          <td>{{ item.applyStart }}~{{ item.applyEnd}}</td>
          <td>{{ item.location }}</td>
          <td><v-btn color="red" text @click="deleteCompetetion(item.boardId)">Delete</v-btn></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
    
    </v-container>
</template>
<script>
// import Competition from './Competition'
import http from '../../http-common'
export default {
    name:'CompetitionList',
    components:{
        // Competition,
    },
    props: {
        // lists:{type:Object}
    },
    data(){
        return{
            lists:{},
            page : 1,
        }
    },
    mounted(){
        this.setting()
    },
    methods:{
        setting(){
             http.get('./getBoard')
            .then(message =>{
                this.lists=message.data;
                console.log(this.lists)
            })
        },
        detailBoard(item){
          sessionStorage.setItem("contents", JSON.stringify(item))
          console.log(sessionStorage.getItem('contents'))
          this.$router.push({name:'detailpage'})
        },
        deleteCompetetion(id) {
          console.log(id)
          http.delete('/deleteBoard/'+ id)
          .then(response =>{
            console.log(response.data)
            if(response.data.state=='succ'){
              alert('삭제 성공')
              this.setting()
            }else{
              alert('삭제 실패')
              this.setting()
            }
          })
        }
}
}
</script>