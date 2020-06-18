<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on" style="margin-bottom:1rem">공지사항 추가</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">공지사항 추가</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  v-model="title"
                  label="제목 *"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field 
                v-model="content"
                label="내용 *" 
                required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                  <v-checkbox
                  v-model="ncheck"
                  label="필수 여부 *"
                  required
                  ></v-checkbox>
              </v-col>
            </v-row>
          </v-container>
          <small>*는 필수 사항 입니다.</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="clickSaveBtn()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>
<script>
import http from '../../http-common.js'
export default {
    name: 'AddNoticeModal',
    data() {
        return {
            dialog: false,
            title: '',
            content: '',
            ncheck: '',
            chk: '',
        }
    },
    methods: {
      clickSaveBtn(){
        if(this.ncheck===true){
          this.chk = 1
        }else{
          this.chk = 0
        }
        let dto = {
          title: this.title,
          content: this.content,
          ncheck: this.chk
        }
        console.log(dto)
        http.post('/addNotice',{
          title: this.title,
          content: this.content,
          ncheck: this.chk
        })
        .then(response => {
          if(response.data.state==='succ'){
            alert('작성 성공')
            this.dialog = false
            this.$emit('reset')
          }else{
            alert('작성 실패')
            this.dialog = false
          }
        })

      }
    }
}
</script>
