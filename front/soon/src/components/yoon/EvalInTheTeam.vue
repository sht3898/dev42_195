<template>
    <div>
        
    </div>
    <!--팀원들을 찾아서 넣어줘야겠당.-->
</template>
<script>
export default {
    data(){
        return{
            team_id:Number,
            board_id:Number,
        }
    },
    mounted(){
        this.team_id = this.$route.params.team_id;
        this.board_id = this.$route.params.board_id;
        
    },
}
</script>