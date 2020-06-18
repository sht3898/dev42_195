<template>
<v-container>
    <v-card>
        <v-card-text>완료된 공전전</v-card-text>
            
        <v-row>
            <!-- <v-col v-for="team in teams.slice(inPageCard*(page-1),inPageCard*(page-1)+inPageCard)" :key="team.keys" cols="3" > -->
            <v-col
                v-for="item in hackaton_list.slice(inPageCard*(page-1),inPageCard*(page-1)+inPageCard)"
                :key="item.key"
                cols="6">
                    <v-card
                    dark
                    @click="click_hackaton(item.boardId)"
                    >
                        <div class="d-flex flex-no-wrap ">
                            <div>
                                <v-avatar
                                class="ma-3"
                                size="125"
                                tile>
                                <v-img :src="item.img"></v-img>
                                </v-avatar>
                            </div>
                            <div>
                                <v-card-title
                                class="headline"
                                v-text="item.title"></v-card-title>
                                <v-card-subtitle v-text="item.host"></v-card-subtitle>
                                <v-card-subtitle v-text='`${item.start} ~ ${item.end}`'></v-card-subtitle>
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
        hackaton_list : [],
      }
    },
    methods:{
        get_hackaton(){
            http.get('/getEndBoardList')
            .then(response=>{
                return this.get_hackaton_callback(response.data);
            })
            .catch(err=>{
                console.log(err);
            })
        },
        get_hackaton_callback(data){
            this.hackaton_list = data;
            this.pageLength = parseInt(this.hackaton_list.length/this.inPageCard)
            if(this.pageLength<this.hackaton_list.length/this.inPageCard){
                this.pageLength+=1;
            }
        },
        click_hackaton(board_id){
            console.log(board_id);
        }

    },
    mounted(){
        this.get_hackaton();
    }
  }
</script>