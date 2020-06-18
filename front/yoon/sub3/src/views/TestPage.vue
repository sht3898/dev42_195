<template>
        <v-row>
            <comment></comment>
        </v-row>
</template>
<script>
import comment from '../components/comment'
export default {
    
    name:'TestPage',
    components :{
        comment,
    },
    data(){
        return{
            naeyong:'',
            texts:[],
        }
    },
    methods:{
      
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