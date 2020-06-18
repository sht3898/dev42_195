<template>
        <v-card @click="clickCard()">
            <v-img :src="img_url" height="45vh"></v-img>
            <v-card-title primary-title>
                <div class="headline font-weight-bold text-truncate">{{i_title}}</div>
            </v-card-title>
            <v-card-text>
                <p class="grey--text text-center">{{s_date}} ~ {{e_date}}</p>
                <p class="grey --text text-center">{{people_num}}</p>
            </v-card-text>
        </v-card>
</template>
<script>
export default {
    name:'join',
    props:{
        host : {type: String},
        img_url:{ type: String},
        i_title : {type: String},
        s_date: {type:String},
        e_date : {type:String},
        location : {type: String},
        people_num : {type: Number}
    },
    data(){
        return{

        }
    },
    methods:{
        clickCard(){
            let dto={
                img_url:this.img_url,
                i_title : this.i_title,
                s_date: this.s_date,
                e_date : this.e_date,
                location : this.location,
                host : this.host,
                people_num : this.people_num
            }
            sessionStorage.setItem("contents", JSON.stringify(dto))
            this.$router.push({name:'detailpage'})
        }
    }
   
}
</script>