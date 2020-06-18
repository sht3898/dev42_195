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
          <v-col cols="6"> <v-text-field type="Number" label="점수"></v-text-field></v-col>
          <v-col cols="12">
            <v-textarea
         filled
          auto-grow
          label="Four rows"
          rows="4"
          row-height="30"
          shaped
                :value="team_coments"
            ></v-textarea>
          </v-col>
          </v-row>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-1"
            
            @click="dialog = function(){dialog = false; team_num=-1; score = 0; team_coments = '';}"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            
            @click="function(){dialog = false; team_num=-1; score = 0; team_coments = '';}"
          >
            Agree                                     
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
    
    <v-row>
    <v-col v-for="(team, i) in teams" :key="i" cols="3" >
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
        board_id : Number,
      }
    },
    methods:{
      init_teams(boardId){
         http.get('/team/apply/' + boardId)
         .then(response=>{
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
      }
    },
    mounted(){
      this.init_teams(this.$route.params.hackaton_id);
      this.board_id = this.$route.params.hackaton_id
    },
    watch: {
      team_num(){
        if(this.team_num>-1){
          this.dialog = true;
        }else{
          this.dialog = false;
        }
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