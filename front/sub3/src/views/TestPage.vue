<template>
        <v-row>
            <test></test>
        </v-row>
</template>
<script>
import test from '../components/test'
export default {
    
    name:'TestPage',
    components :{
        test,
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