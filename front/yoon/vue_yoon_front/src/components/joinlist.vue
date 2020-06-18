<template>
    <div>
    <v-layout wrap>
        <v-flex xs12 sm6 lg3 v-for="i in lists.length" :key="i">
            <join class="ma-3"
            :people_num="lists[i-1].money"
            :i_title="lists[i-1].title"
            :s_date="lists[i-1].s_date"
            :e_date="lists[i-1].e_date"
            :img_url="lists[i-1].i_url"
            :location="lists[i-1].location"
            :host="lists[i-1].host"
            ></join>
        </v-flex>
    </v-layout>

    <div class="text-center" style="margin:auto;">
        <v-pagination
            v-model="page"
            class="my-4"
            :length="parseInt(lists.length / 4) + 1"
            ></v-pagination>
    </div>
    </div>
</template>
<script>
import join from './join'
// import http from '../http-common'
export default {
    name:'joinlist',
    components:{
        join,
    },
    data(){
        return{
            lists:[
                {money:0, location:'대전', host: 'Hong', i_url:'http://www.seouldesign.or.kr/file/temp/2017/201711/2f8ee2be-bfd7-47cf-b2c2-341362865135', title: 'a', s_date:'20200130', e_date:'20200130'},
                {money:0, location:'대전', host: 'Hong', i_url:'http://jccei.kr/files/editor/4cc13380-3c07-4247-acc1-0c8d43bb2264.jpg', title: 'b', s_date:'20200130', e_date:'20200130'},
                {money:0, location:'대전', host: 'Hong', i_url:'http://www.gist.ac.kr/kr/html/sub07/070103.html?mode=D&no=192467&file_id=50131', title: 'c', s_date:'20200130', e_date:'20200130'},
                {money:0, location:'대전', host: 'Hong', i_url:'http://www.kosmi.org/rang_utils/tiny_mce/UPimg/180914120930_%ED%95%B4%EC%BB%A4%ED%86%A4%ED%8F%AC%EC%8A%A4%ED%84%B0.jpg', title: 'd', s_date:'20200130',e_date:'20200130'},
                {money:0, location:'대전', host: 'Hong', i_url:'https://img9.yna.co.kr/etc/inner/KR/2018/05/09/AKR20180509095500004_01_i_P2.jpg', title: 'e', s_date:'20200130', e_date:'20200130'},
                {money:0, location:'대전', host: 'Hong', i_url:'http://www.all-con.co.kr/data/poster/1904/439292.jpg', title: 'f', s_date:'20200130', e_date:'20200130'},
                ],
            
            page : 1,
        }
    },
//     mounted(){
//         this.setting()
//     },
//     methods:{
//         setting(){
//              http.get('./getBoard')
//             .then(message =>{
//                 this.lists=message.data;
//                 console.log(this.lists)
//             })
//     }
// }
}
</script>