<template>
    <header style="min-height:0">
        <v-app-bar fixed elevate-on-scroll class="container-fluid" style="width:100%;z-index:2">
            <v-toolbar-title style="width:3vm">
                <router-link to="/testpage" tag="span" style="cursor:pointer; font-weight:bold"> 
                <v-img
                width="130px"
                height="60px"
                src="../assets/m5.png"
                contain
                >
                </v-img>
                </router-link> 
            </v-toolbar-title>
            <v-container class="d-flex justify-center offset-1" style="min-height:0;width:4vm" >
                <v-text-field solo rounded v-model="content" label='검색' hide-details="auto" style="border: yellow solid 2.5px;" @keydown.enter="searching"></v-text-field>
            </v-container>
            <v-app-bar-nav-icon @click="sidebar =!sidebar" class="hidden-lg-and-up" style="margin-right:20px">
                <v-icon color="yellow"> {{"drag_indicator"}}</v-icon>
            </v-app-bar-nav-icon>
             <v-toolbar-items class="justify-start hidden-md-and-down" style="width:2vm">
            <v-btn
                text
                color="white"
                style="text-align:center; width:2vm;"> 
                <h2 style="font-weight:bold; font-size:1.2rem;"
                @click="bannerClick('신청하기')">신청하기</h2>
            </v-btn>
            <v-btn
                text
                color="white"
                style="text-align:center; width:2vm;"> 
                <h2 style="font-weight:bold; font-size:1.2rem;"
                @click="bannerClick('만들기')">만들기</h2>
            </v-btn>
            <v-menu v-if="isLoginsub"  offset-y>
            <template  v-slot:activator="{ on }">
              <v-btn
                  text
                  color="white"
                  style="text-align:center; width:2vm;"
                  v-on="on"
             >
                   <h2 style="font-weight:bold; font-size:1.2rem;">내정보</h2>
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                @click="myInfoSubClick(item.title)"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>
            <v-btn
                v-if="!isLoginsub"
                text
                color="white"
                style="text-align:center; width:2vm;"> 
                <h2 style="font-weight:bold; font-size:1.2rem;"
                @click="bannerClick('로그인')">로그인</h2>
            </v-btn>


            </v-toolbar-items>
        </v-app-bar>
        <!-- <v-navigation-drawer
        v-model="sidebar"
        disable-resize-watcher
        clipped
        right
        app
        style="background:#424242">
        <v-list-item-group>
            <v-list-item
            v-for="item in menuItems"
            :key="item.key"
            style="text-align:center;"
            @click="bannerClick(item.title)">
            <v-list-item-content style="color:white; font-size:1rem;font-weight:bold;margin:auto">{{item.title}}</v-list-item-content>
            </v-list-item>
        </v-list-item-group>
        </v-navigation-drawer> -->
           <v-navigation-drawer
        v-model="sidebar"
      dark
      right
      clipped
      app
      temporary
        style="background:#424242">
        <v-list-item-group>
            <v-list-item
            v-for="item in menuItems"
            :key="item.key"
            style="text-align:center;"
            @click="bannerClick(item.title)">
            <v-list-item-content v-if="item == menuItems[menuItems.length-1]" style="color:white; font-size:1rem;font-weight:bold;margin:auto">
                <v-expansion-panels 
                flat 
                tile
                accordion>
                    <v-expansion-panel style="background:#424242; font-size:1rem;font-weight:bold;width:100%;color:white;">
                        <v-expansion-panel-header style="padding:0">{{item.title}}</v-expansion-panel-header>
                        <v-expansion-panel-content style="color:white;">
                            <v-list-item-group>
                                <v-list-item
                                v-for="content in items"
                                :key="content.key"
                                >
                                    <v-list-item-content style="color:white;text-align:end">{{content.title}}</v-list-item-content>
                                </v-list-item>
                            </v-list-item-group>
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-list-item-content>
            <v-list-item-content v-else style="color:white; font-size:1rem;font-weight:bold;margin:auto">{{item.title}}</v-list-item-content> 
            </v-list-item>
        </v-list-item-group>
        </v-navigation-drawer>
    </header>
</template>

<script>
export default {
    name : 'side',
    props:{
        isLoginsub:Boolean,
    },
    data(){
        return {
            content : '',
            sidebar: false,
            menuItems:[
                {title: '신청하기', icon: 'note_add'},
                {title:'만들기', icon: 'public'},
                {title: '로그인', icon: 'person'}
            ],
            test:"",
            onLoginModal:false,
            token:"",
            items: [
                { title: '내 정보' },
                { title: '로그아웃' },
            ],
            onMenu:true,
        }
    },
    methods: {
        bannerClick(data){
            if(data==='로그인'){
                if(sessionStorage.token==""||sessionStorage.token==undefined||sessionStorage.token==null){
                    console.log("?>>>>>>>");
                    this.$emit('onLogin', true)
                }
            }else if(data ==='만들기'){
                this.$router.push('registhackaton');
            }else if(data==='신청하기'){
                this.$router.push('/join')
            }else if(data==='내 정보'){
                // alert('성공!#@#!@#!#@!');
                this.onMenu = !this.onMenu;
                console.log(this.onMenu);
            }
        },
        myInfoSubClick(title){
            if(title==="로그아웃"){
                this.$emit('logOut',true);
            }
        },
        searching(){
            if (this.content == ''){
                alert('검색어를 입력해주세요')
            }else{
                sessionStorage.setItem("search", this.content)
                // console.log(sessionStorage.getItem("search"))

                this.$router.push({name:'searchpage'})
            }
        }
    },
    mounted(){
        console.log("side.vue>>---------mounted()>>tokken"+sessionStorage.getItem('x-access-token'));
        if(sessionStorage.getItem('x-access-token'=="")){
            console.log("로그인");
            this.menuItems[2].title = "로그인";
        }else{
            console.log("내 정보");
            this.menuItems[2].title = "내 정보";
        }
    },
    watch:{
        isLoginsub(){
            
        }
    }
}

</script>
 <style>
 .v-toolbar__content {
     background:#424242 !important;
 }

 </style>