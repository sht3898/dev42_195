<template>
    <div>
    <v-layout wrap>
        <v-flex xs12 sm6 lg3 v-for="i in lists.slice(8*(page-1),8*(page-1)+8)" :key="i.key">
            <join class="ma-3"
            :contents="i"
            ></join>
        </v-flex>
    </v-layout>

    <div class="text-center" style="margin:auto;">
        <v-pagination
            v-model="page"
            class="my-4"
            :length="page_limit"
            ></v-pagination>
    </div>
    </div>
</template>
<script>
import join from './join'
import http from '../http-common'
export default {
    name:'joinlist',
    components:{
        join,
    },
    data(){
        return{
            lists:[
                ],
            
            page : 1,
            page_limit : 0
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
                if (this.lists.length % 8 === 0){
                    this.page_limit = this.lists.length / 8
                }else {
                    this.page_limit = this.lists.length / 8 + 1
                }
            })
    }
}
}
</script>