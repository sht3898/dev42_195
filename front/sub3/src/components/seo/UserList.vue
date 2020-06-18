<template>
    <v-container>
        <v-simple-table fixed-header height="30rem">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">유저이름</th>
          <th class="text-left">이메일</th>
          <th class="text-left">Github 아이디</th>
          <th class="text-left">삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in lists" :key="item.name">
          <td @click="clickCard()"><v-btn text>{{ item.name }}</v-btn></td>
          <td>{{ item.email }}</td>
          <td><p v-if="item.github != ''">{{ item.github }}</p>
          <p v-else>Github ID 없음</p></td>
          <td><v-btn color="red" v-if="item.auth != 'ADMIN'" text @click="dialog = false">Delete</v-btn>
          <v-btn color="black" v-if="item.auth == 'ADMIN'" text @click="dialog = false" disabled>관리자</v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
    
    </v-container>
</template>
<script>
import http from '../../http-common'
export default {
    name: 'UserList',
    components: {

    },
    props: {
        
    },
    data() {
        return{
            lists: {},
            page: 1,
        }
    },
    mounted() {
        this.settings()
    },
    methods: {
        settings() {
            http.get('./findAllMember')
            .then(message =>{
                this.lists = message.data.data;
                console.log(this.lists)
            })
        }
    }
}
</script>