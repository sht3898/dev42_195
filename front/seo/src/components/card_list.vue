<template>
    <v-row>
        <v-col
            cols="12"
            style="height:5vw; text-align:center">
            <div><h2 class="headline mb-3" style="font-weight:bold;font-style:italic">이거 봤어?</h2></div>
        </v-col>
        <v-col
            cols="12">
            <v-layout mt-5 wrap>
                <v-flex xs12 lg4 v-for="i in contents.length > limits? limits : contents.length" :key="i.key" style="margin-top:1rem">
                    <cards class="ma-3"
                        style="height:100%;"
                        :content="contents[i-1]">
                    </cards>
                </v-flex>
            </v-layout>
        </v-col>
    </v-row>
</template>
<script>
import cards from './cards'
import http from '../http-common'
export default {
    name:'card_list',
    components:{
        cards
    },
    data(){
        return{
            limits : 4,
            contents:[
                    
            ]
        
        }
          

    },
     mounted(){
        this.test()
    },
    methods:{
        test(){
            http.get('./getBoard')
            .then(message =>{
                this.contents=message.data;
                console.log(this.contents)
            })
        }
    },
   
}
</script>