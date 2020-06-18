<template>
    <v-row style="margin:10px" justify="center">
        <v-col cols="2">
            <!-- 여기에 공모전 데이터를 보여주고 -->
        <v-card-title class="headline" style="margin:auto" > {{contents.title}} </v-card-title>

            <v-row>
                <v-col cols="12">
                    <v-img :src="contents.img"></v-img>
                </v-col>
                <v-col cols="12">
                    인원
                    <v-text-field readonly :value="contents.peopleNum"></v-text-field>
                </v-col>
                <v-col cols="12">
                    장소
                    <v-text-field readonly :value="contents.location"></v-text-field>
                </v-col>
            </v-row>
        </v-col>
        <v-col cols = "10">    
        <v-card-title class="headline" style="margin:auto" > 참여 PAGE </v-card-title>
            <v-card width="80vw" >
            <v-container>
                <v-card-title class="headline" style="margin:auto" > 신청자 정보 </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col cols="6">
                            이름
                            <v-text-field  :value="name" readonly required></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            ID
                            <v-text-field :value="email" readonly required></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            소속
                            <v-text-field  label="소속"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            전화번호
                            <v-text-field :value="phone" readonly  required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                        <v-row justify="center">
                        <span style="margin:auto 0px auto auto">개인정보동의(필수)</span>  
                        <Policy color="black" title="(더보기)" style="margin:auto auto auto 0px"></Policy>
                            <v-checkbox v-model="agreement"></v-checkbox>
                        <span style="margin:auto auto auto 0px"></span>
                        </v-row>
                        
                        </v-col>
                    </v-row>
                </v-card-text>
                </v-container>
                </v-card>
        <v-card width="80vw" >
            <v-container>
                <v-card-title class="headline" style="margin:auto" > 정보 입력 </v-card-title>
                <v-card-text>
                    <v-row>
                        <!-- 신청자 정보 -->

                        <!-- 입력 form -->
                        <v-col cols="6" md="6" sm="12">
                            <v-text-field v-model="idea_title" label="아이디어 명*" required></v-text-field>
                        </v-col>
                        <v-col cols="6" md="6" sm="12">
                            <v-text-field v-model="team_title" label="팀 명*" required></v-text-field>
                        </v-col>
                        <v-col cols="3">
                            <v-text-field v-model="team_chef" label="팀장 명 *" required></v-text-field>
                        </v-col>
                        <v-col cols="6" md="6" sm="6">
                            <v-autocomplete
                                v-model="friends" :disabled="isUpdating" :items="people" filled chips color="blue-grey lighten-2" label="Select" item-text="name" item-value="name" multiple>
                                <template v-slot:selection="data">
                                    <v-chip v-bind="data.attrs" :input-value="data.selected" close @click="data.select" @click:close="remove(data.item)">
                                        {{ data.item.name }}
                                    </v-chip>
                                </template>
                                <template v-slot:item="data">
                                    <template v-if="typeof data.item !== 'object'">
                                        <v-list-item-content v-text="data.item"></v-list-item-content>
                                    </template>
                                    <template v-else>
                                        <v-list-item-content>
                                        <v-list-item-title v-html="data.item.name"></v-list-item-title>
                                        </v-list-item-content>
                                    </template>
                                </template>
                            </v-autocomplete>
                        </v-col>
                        <v-col cols="3" md="3" sm="3">
                            <v-text-field type="number" v-model="team_many" readonly solo-inverted label=" 명*" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-textarea 
                                filled auto-grow rows="4" row-height="30" shaped
                                v-model="info" label="아이디어 설명" required>
                            </v-textarea>
                    </v-col>
                    </v-row>
                    <v-row>
                <!-- <v-btn style="margin:auto 3px auto auto;" width="3vw" color="warning" large dark @click="dialog = false">Close</v-btn> -->
                <v-btn style="margin:auto 3px auto auto;" width="3vw" color="warning" large dark @click="cancle()">Close</v-btn>
                <v-btn style="margin:auto auto auto 3px;" width="3vw" color="success" large dark @click="clickSaveBtn()">Save</v-btn>
                </v-row>
                </v-card-text>
            </v-container>
        </v-card>
        </v-col>
    </v-row>
</template>
<script>
import http from '../../http-common';
import Policy from  '../seo/Policy'
export default {
    components:{
        Policy,
    },
    data(){
        return{
            value: null,
            contents : null,
            idea_title:"",
            team_title:"",
            team_chef:"",
            team_many:Number,
            info:"",
            name:"",
            phone:"",
            email:"",
            allEmail:null,
            seletedEmail:"",
            autoUpdate: true,
            friends: [],
            isUpdating: false,
            people: [],
            agreement : false,
        }
    },
    methods:{
        cancle(){
            this.$router.push({name:'detailpage'})
        },
        getUserInfo(){
            http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
            http.get('/user')
            .then(response=>{
                console.log(response);
                return this.initProfile(response.data);
            })
            .catch(err=>{
                console.log(err);
            })
        },
        initMembers(arr){
            for(let i=1; i<arr.length; i++){
                if(arr[i] !== this.email){
                    this.people.push({name:arr[i]});        //나중에 백엔드 쪽에서 처리하도록 처리
                }
            }
            console.log("ApplyHackatonPage>>------initMembers()------");
            console.log(this.people.length);
        },
        searchID(){
            http.get('/getMemberByID/'+this.value)
            .then(resposne=>{
                console.log("ApplyHackatonPage>>-------------searchID()>>");
                console.log(resposne.data.array);
            })
            .catch(err=>{
                console.log(err);
            })
        },
        initProfile(data){
            console.log(">?>>>>>>>>>");
            console.log(data);
            this.name = data.member.name;
            this.email = data.member.email;
            this.phone = data.member.phone;
        },
        getFindAllEmail(){
            http.get('/findAllEmail')
            .then(response=>{
                console.log(">>ApplyHackatonPage>>---------------getFindAllEmail()");
                if(response.data.state=='succ'){
                    console.log(">>>>>>USER LIST>>>>>>");
                    console.log(response.data.data);
                    return this.initMembers(response.data.data);
                }else{
                    console.log('실패');
                    return;
                }
            })
            .catch(err=>{
                console.log(err)
            })
            console.log("----------------------------------------------------");

        },
        clickSaveBtn(){
            // http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
            // console.log('Github Token : ' + sessionStorage.getItem("github-access-token"));
            // console.log('ACCESS TOKEN : '+  sessionStorage.getItem('x-access-token'))
            // http.post('/team/createTeam/'+this.team_title)
            // .then(response=>{
            //     console.log('Create Team  : ' + response.data);
            //     if(response.data.state=="succ"){
            //         console.log("ApplyHackatonPage>>--------clickSaveBtn()-----성공");
            //         return applyTeam(response.data.data.teamId);
            //     }else{
            //         console.log("ApplyHackatonPage>>--------clickSaveBtn()-----실패");
            //     }
            // })
            // .catch(err=>{
            //     console.log(err);
            // })
            // function applyTeam(team_id){
            //     http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
            //     http.post('/team/apply',{
            //         boardId:this.contents.boardId,
            //         teamId:team_id,
            //         info : this.info })
            //     .then(response=>{
            //         console.log('ApplyTeam : ' + response.data);
            //         if(response.data.state=="succ"){
            //             console.log("ApplyHackatonPage>>---------clickSaveBtn()-applyTeam()>>성공");
            //         }else{
            //             console.log("ApplyHackatonPage>>---------clickSaveBtn()-applyTeam()>>실패");
            //         }
            //     })
            //     .catch(err=>{
            //         console.log(err);
            //     })
            // }
            if(this.agreement){
            http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
            http.post('/team/create',{
                boardId: this.contents.boardId,
                idea : this.idea_title,
                info : this.info,
                memberList : this.friends,
                teamName : this.team_title
            })
            .then(response =>{
                if (response.data.state === 'succ'){
                    alert('등록되셨습니다')
                    this.$router.push({name:'detailpage'})
                }else{
                    console.log(response)
                    alert('입력하신 정보를 확인해주세요')
                }
            })
            } else{
                alert('동의를 눌러주세요')
            }
        },
        remove(item) {
            const index = this.friends.indexOf(item.name)
            if (index >= 0) this.friends.splice(index, 1)
        }
    },
    // beforeCreate(){
    //     this.getFindAllEmail();
    // },
    mounted(){
        this.contents = JSON.parse(sessionStorage.getItem("contents"));
        console.log("ApplyHackatonPage>>--------mounted()-----");
        console.log(this.contents);
        this.getUserInfo();
        this.getFindAllEmail();

    },
    watch:{
        value(){
            console.log(this.value);
            this.seletedEmail+=this.value+", ";
        }
    }

}
</script>