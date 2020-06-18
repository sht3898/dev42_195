<template>
    <searchlist></searchlist>
</template>
<script>
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
}
</script>