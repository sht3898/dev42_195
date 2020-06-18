<!-- 평가 페이지 -->
<template>
<v-container>
  <v-layout row justify-center>
    <h1>
      평가 페이지 
    </h1>
    <v-dialog
      v-if="dialog"
      v-model="dialog"
      max-width="35vw"
    >
      <v-card>
        <v-card-title class="headline">{{teams[team_num].team.teamName}}</v-card-title>
        <v-card-text>
          <v-row>
            <!-- 팀 간단 info -->
            <v-col cols="12">
            <v-card>
              <v-card-text>
                <v-row>
                  <v-col cols = "12">
                    <h3>
                    상태 : {{teams[team_num].team.teamState}}
                    </h3>
                  </v-col>
                  <v-col cols = "12">
                    <h3>
                    팀원수 : {{teams[team_num].team.teamMemberNum}}
                    </h3>
                  </v-col>
                  <v-col cols = "12">
                    <h3>
                    githubURL : {{teams[team_num].team.githubRepoUrl}}
                    </h3>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
            </v-col>
          <v-col cols="10"> <v-text-field v-model="score" type="Number" label="점수"></v-text-field></v-col>
          <v-col cols="12">
            <v-textarea
            style="margine:auto;"
         filled
          auto-grow
          label="Four rows"
          rows="4"
          row-height="30"
          shaped
          v-model="info"
                :value="team_coments"
            ></v-textarea>
          </v-col>
          </v-row>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="red darken-1"
            @click="click_fall_btn()"
          >
            탈락
          </v-btn>
          <v-btn
            color="green darken-1"
            @click="click_eval_btn()"
          >
            평가                                     
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
  <v-card>
    <v-row>
    <v-col v-for="team in teams.slice(inPageCard*(page-1),inPageCard*(page-1)+inPageCard)" :key="team.keys" cols="3" >
          <v-layout>
            <v-flex >
              <v-card>
                <v-card-title primary-title>
                <div>
                  <h3 class="headline mb-0">{{team.team.teamName}}</h3>            
                </div>
                </v-card-title>
                <v-card-text>
                  <div>{{team.team.teamDate}}</div>
                </v-card-text>   
                <v-card-actions>
                  <v-btn  color="orange" @click="team_num=i">평가하기</v-btn>
                  <v-btn color="orange" @click="team_num=i">상세 보기</v-btn>
                </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
        </v-col>
    </v-row>
    <v-pagination
            v-model="page"
            :length="pageLength"
        ></v-pagination>
  </v-card>
    </v-container>
</template>
<script>
import http from '../../http-common';
  export default {
    data () {
      return {
        dialog: false,
        teams:null,
        team_num : -1,
        team_coments : "",
        score : 0,
        info : "",
        board_id : Number,
        page: 1,
        pageLength: 1,
        inPageCard : 12,
        
      }
    },
    methods:{
      init_teams(boardId){
         http.get('/team/apply/' + boardId)
         .then(response=>{
           console.log(response.data);
           
           return this.teams = response.data;
         })
      },
      eval_team(){
        http.post('/eval/host',{
          board_id : this.board_id,
          info : this.team_coments,
          score : this.score,
          team_id : this.team_num,
        })
        .then(response=>{
          console.log(response.data);
        })
        .catch(err=>{
          console.log(err); 
        })
      },
      click_fall_btn(){
        http.patch(`/sponsor/${this.board_id}/half/${this.team_num}`)
        .then(response=>{
          console.log(response.data);
          return this.callback_indialog_btn();
        })
        .catch(err=>{
          console.log(err);
        })
      },
      click_eval_btn(){
        http.patch(`/sponsor/${this.board_id}/full/${this.team_num}`)
        .then(response=>{
          console.log(response.data);
          return this.callback_indialog_btn();
        })
        .catch(err=>{
          console.log(err);
        })
      },
      callback_indialog_btn(){
        http.post('/eval/host',{
          board_id: this.board_id,
          info: this.info,
          score: this.score,
          team_id: this.team_num,
        })
        .then(response=>{
          console.log(response);
          return this.init_eval_value();
        })
        .catch(err=>{
          console.log(err);
          return this.init_eval_value();
        })
      },
      init_eval_value(){
        this.dialog = false; 
        this.team_num=-1; 
        this.score = 0; 
        this.info = "";
        this.team_coments = '';
      }
    },
    mounted(){
      this.init_teams(this.$route.params.hackaton_id);
      this.board_id = this.$route.params.hackaton_id;
      
      
    },
    watch: {
      team_num(){
        if(this.team_num>-1){
          this.dialog = true;
        }else{
          this.dialog = false;
        }
      },
      teams(){
        this.pageLength = parseInt(this.teams.length/this.inPageCard)
        if(this.pageLength<this.teams.length/this.inPageCard){
          this.pageLength+=1;
        }
        console.log("!@!!@@!@"+this.pageLength);
      }
   },
  }
</script>




<!--
{
  "board_id": 0,
  "info": "string",
  "score": 0,
  "team_id": 0
}
/eval/host
-->