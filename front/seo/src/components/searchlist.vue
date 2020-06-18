<template>
    <div>
    <v-layout wrap>
        <div style="width:100%">
        <v-card  
        flat
        style="width:100%; height:3vw; background:#fafafa">
        <v-select
        :items="items"
        solo
        hide-details
        :label="items[order]"
        v-model="order"
        background-color="white"
        style="margin-left:auto;height:1.5vw;width:20%;text-align:center;font-size:1rem"
        >
        </v-select>
        </v-card>
        </div>
        <v-flex id="test" xs12 sm6 lg3 v-for="i in sortedlists.slice(8*(page-1),8*(page-1)+8)" :key="i.key">
            <search class="ma-3"
            :contents="i"
            ></search>
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
import search from '../components/search'
import http from '../http-common'
export default {
    name:'searchlist',
    components:{
        search,
    },
  
    data(){
        return{
            page : 1,
            page_limit : 0,
            order: '날짜',
            items : ['날짜','이름','참가비용'],
            contents: []
        }
    },
    mounted(){
        this.setting()
        this.sorting()
    },
    methods:{
        setting(){
            var find = sessionStorage.getItem('search')
            http.get('/searchBoardByTitle/' + find)
            .then(response =>{
                this.contents = response.data
                if (this.contents.length % 8 === 0){
                    this.page_limit = this.contents.length / 8
                }else {
                    this.page_limit = (this.contents.length / 8) + 1
                }
            })   
        },
        sorting(order){
            var ab= this.contents.sort(function(a,b){
                if (order === '날짜'){
                    if(a.start > b.start){
                        return -1;
                    }else if(a.start < b.start){
                        return 1;
                    }return 0
                }else if(order === '이름'){
                    var c = a.title.toUpperCase();
                    var d = b.title.toUpperCase();
                    if (c > d){
                        return 1;
                    }else if(c < d){
                        return -1;
                    }return 0
                }else if(order === '참가비용'){
                    if (a.price < b.price){
                        return -1;
                    }else if(a.price > b.price){
                        return 1;
                    }return 0;
                }
            })
            return ab;
        }
    },
    computed:{
            sortedlists(){
                let sorted = this.sorting(this.order);
                return sorted;
            }
        }
}
</script>