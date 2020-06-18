<template>
    <searchlist></searchlist>
    
</template>
<script>
import http from '../http-common';
import searchlist from '../components/searchlist';
export default {
    name:'searchpage',
    components:{
        searchlist
    },
    data(){
        return{
            output:[]
        }
    },
    methods:{
        init(){
            var find = sessionStorage.getItem('search')
            http.get('/searchBoardByTitle/' + find)
            .then(response =>{
                this.output = response.data
            })
        }
    },
    mounted(){
        this.init()


    }
}
</script>