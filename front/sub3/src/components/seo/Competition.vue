<template>
    <v-container>

        <v-card @click="clickCard()">
            <v-card-title primary-title>
                <div class="headline font-weight-bold text-truncate">{{contents.title}}</div>
            </v-card-title>
            <v-card-text>
                <p class="grey--text text-center">{{contents.start}} ~ {{contents.end}}</p>
                <p class="grey --text text-center">{{contents.price}}</p>
            </v-card-text>
        </v-card>
    </v-container>
</template>
<script>
export default {
    name:'Competition',
    props:{
        contents:{type:Object}
    },
    data(){
        return{

        }
    },
    methods:{
        clickCard(){
            sessionStorage.setItem("contents", JSON.stringify(this.contents))
            console.log(sessionStorage.getItem('contents'))
            this.$router.push({name:'detailpage'})
        }
    }
   
}
</script>