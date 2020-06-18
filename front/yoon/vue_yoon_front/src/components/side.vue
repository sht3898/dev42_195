<template>
<div>
    <header style="min-height:0">
        <v-app-bar fixed elevate-on-scroll class="container-fluid" style="width:100%;z-index:2">
            <v-toolbar-title class="col-2">
                <router-link to="/" tag="span" style="cursor:pointer; font-weight:bold"> 
                <v-img
                width="150px"
                height="60px"
                src="../assets/m5.png"
                contain
                >
                </v-img>
                </router-link> 
            </v-toolbar-title>
            <v-container class="d-flex justify-center col-6 offset-1" style="min-height:0" >
                <v-text-field solo rounded label='검색' hide-details="auto"  style="border: yellow solid 2.5px;"></v-text-field>
            </v-container>
            <v-toolbar-items class="col-4 justify-start" >
                <v-btn
                text
                large
                v-for="item in menuItems"
                :key="item.title"
                color="white"
                style="text-align:center;">
                <h2 style="font-weight:bold;font-size:1.5rem;"
                @click="bannerClick(item.title)">{{item.title}}</h2>
            </v-btn>
            </v-toolbar-items>
        </v-app-bar>
    </header>
</div>
</template>

<script>
export default {
    name : 'side',
    data(){
        return {
            menuItems:[
                {title: '신청하기', icon: 'note_add'},
                {title:'만들기', icon: 'public'},
                {title: '내 정보', icon: 'person'}
            ],
            onLoginModal:false,
        }
    },
    methods: {
        bannerClick(data){
            if(data==='내 정보'){
                if(sessionStorage.token==""||sessionStorage.token==undefined||sessionStorage.token==null){
                    console.log("?>>>>>>>");
                    this.$emit('onLogin', true)
                }
                else{
                    //다른거
                }
            }else if(data ==='만들기'){
                
            this.$router.push('registhackaton');
            }else if(data==='신청기'){
                console.log("????>??");
            }
        }
    },
}
</script>
 <style>
 .v-toolbar__content {
     background:#424242 !important;
 }

 </style>