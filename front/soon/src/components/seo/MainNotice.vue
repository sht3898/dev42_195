<template>
    <div>
        <v-container :v-for="notice in notices">
            <p>ggg</p>
        </v-container>
    </div>
</template>
<script>
import http from '../../http-common'
export default {
    name: 'MainNotice',
    data() {
        return {
            notices: []
        }
    },
    methods: {
        init(){
            console.log('-----------test')
            http.get('./getNotice')
            .then(message =>{
                this.notices = message.data;
            })
            console.log('-------------end')
        },
    },
    mounted() {
        this.init()
    },
}
</script>