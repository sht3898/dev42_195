<template>
<v-container>
    <v-card>
        <v-card-text>완료된 공전전</v-card-text>
            
        <v-row>
            <!-- <v-col v-for="team in teams.slice(inPageCard*(page-1),inPageCard*(page-1)+inPageCard)" :key="team.keys" cols="3" > -->
            <v-col
                v-for="item in team_list"
                :key="item.key"
                cols="6">
                    <v-card
                    dark>
                        <div class="d-flex flex-no-wrap ">
                            <div>
                                <v-card-title
                                class="headline"
                                v-text="item.teamName"></v-card-title>
                                <v-card-subtitle v-text="item.teamState"></v-card-subtitle>
                                <v-card-subtitle v-text='`${item.teamDate}`'></v-card-subtitle>
                            </div>
                        </div>
                    </v-card>
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
        page: 1,
        pageLength: 1,
        inPageCard : 6,
        team_list : [],
      }
    },
    methods:{
        get_team_list(board_id){
            http.get(`/getFullTeam/${board_id}`)
            .then(response=>{
                console.log(response.data);
                return this.get_team_list_callback(response.data);
            })
            .catch(err=>{
                console.log(err);
            })
        },
        get_team_list_callback(data){
            this.team_list = data;
            this.pageLength = parseInt(this.team_list.length/this.inPageCard)
            if(this.pageLength<this.team_list.length/this.inPageCard){
                this.pageLength+=1;
            }
        }
    },
    mounted(){
        this.get_team_list(this.$route.params.board_id);
    }
  }
</script>

<!-- /getFullTeam/{boardID} 

teamId: 61
teamDate: "2020-02-19"
teamState: "FULL"
teamMemberNum: 3
githubRepoUrl: null
teamName: "테스트용"



-->


