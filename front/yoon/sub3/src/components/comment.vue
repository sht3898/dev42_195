<template>
        <v-row>
            <v-col
            cols="12">
            <br>
            <div style="width:75%; height:40vmin;margin:auto;border:#424242 solid 2px; border-radius:15px;overflow:scroll;overflow-x:hidden;padding-top:20px;">
                <v-row style="color:#424242,font-size:2rem;width:100%;align-items:center;border-bottom:solid grey 1px;margin:auto" v-for="text in texts" :key="text.key">
                    <v-col
                    cols="11"
                    offset="1"
                    style="font-weight:bold;font-size:2rem;"
                    >
                        {{text.email}}
                    </v-col>
                    <!-- <v-col
                    cols="10"
                    style="font-size:1rem"
                    >
                        {{text.date}}
                    </v-col> -->
                    <v-col
                    cols="11"
                    offset="1"
                    style="padding-top:0;font-size:1.5rem"
                    >
                        {{text.ccontent}}
                    </v-col>
                    
                </v-row>

            </div>
            </v-col>
            <v-col
            cols="12">
                <v-text-field solo rounded label="댓글" hide-details="auto" height="5vmin" v-model="naeyong" @keyup.13="send" style="width:75%;border: yellow solid 2px;margin:auto"></v-text-field>
            </v-col>
        </v-row>
</template>
<script>
import http from '../http-common'
export default {
    name:'TestPage',
    props:{
        board_id:{type:Number}
    },
    data(){
        return{
            naeyong:'',
            texts:[],
        }
    },
    methods:{
        init(data){
            console.log(">>>>>"+data);
            http.get('/getComment/'+data)
            .then(response =>{
                this.texts=response.data
            })
        },
        send(){
            if (this.naeyong != ''){
                var dto={
                    email : 'IT403JDY',
                    ccontent : this.naeyong,
                    board_id : this.board_id
                }
                http.post('/addComment',{
                    board_id:dto.board_id,
                    ccontent:dto.ccontent,
                    email:dto.email
                })
                .then(response =>{
                    if(response.data.state === "succ"){
                        this.init()
                        this.naeyong=''
                    }
                })
            
            }
        }
    },
    mounted(){
        this.init(this.board_id);
    }
}
</script>
<style>
    div::-webkit-scrollbar { 
        width: 0;
    }
    div::-webkit-scrollbar.hover { 
        width: 10px
    }

</style>