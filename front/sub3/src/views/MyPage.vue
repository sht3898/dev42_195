<template>
    <div>
      <v-dialog
      v-if="evaluating"
      v-model="evaluating"
      max-width="35vw"
      >
      <v-card>
        <v-card-title> {{team.team.teamName}} </v-card-title>
        <v-card-text>
            <v-row
            v-for="member in team.teamMember"
            :key="member.key"
            >
              <v-row 
              v-if="member.email !== u_id">
                <v-col
                cols="12">
                <span class="headline">{{member.email}}/ {{member}}</span>
                </v-col>
                <v-col
                cols="2"
                style="width:100%;margin:auto;">
                <span > 점수 </span>
                </v-col>
                <v-col
                cols="2"
                >
                <v-text-field
                type="Number"
                :v-model="member.points.score"
                >
                </v-text-field>
                </v-col>
                <v-col
                cols="8"
                style="margin:auto">
                점
                </v-col>
                <v-col
                cols="6"
                style="margin:auto">
                <span> 점수에 대한 이유를 설명해주세요(필수)</span>
                </v-col>
                <v-col
                cols="6"
                >
                <v-textarea 
                filled
                auto-grow
                required
                rows="4"
                row-height="30"
                :v-model="member.points.str"
               >
               </v-textarea>
                </v-col>
              </v-row>
            </v-row>
        </v-card-text>
        <v-card-actions>
          <v-btn @click="pyunga()">확인</v-btn>
          <v-btn @click="evaluating=false">취소</v-btn>
        </v-card-actions>
      </v-card>
      </v-dialog>
    <v-row style="margin:10px" justify="center">
        <v-col cols="4">
    <v-card style="height:22vw">
      <v-expansion-panels class="mb-6" style="height:22vw;overflow:scroll;overflow-x:hidden">
      <div 
      v-if="alarams.length === 0"
      style="color:grey;margin:auto">
        알람이 존재하지 않습니다  
      </div>
      <v-expansion-panel
      v-for="i in alarams"
      :key="i.key">
        <v-expansion-panel-header expand-icon="mdi-menu-down"> {{i.team.teamName}}으로부터의 알람이 왔습니다</v-expansion-panel-header>
        <v-expansion-panel-content>
          <div> 
          {{i.team.teamName}}팀으로 초대받으셨습니다.<br/>
          수락하시겠습니까?
          </div>
          <div style="align-items:end">
            <button @click="accept(i)"> 수락 </button> / <button @click="refuse(i)"> 거절</button>
          </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-card>
</v-col>
        <v-col cols="8">
            <v-card width="80vw" >
            <v-container>
                <v-card-title class="headline" style="margin:auto" > 신청자 정보 </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col cols="6">
                            이름
                            <v-text-field   readonly required :value="u_name"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            ID
                            <v-text-field  readonly required :value="u_id"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            소속
                            <v-text-field :value="u_job"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            전화번호
                            <v-text-field  readonly  required :value="u_phone"></v-text-field>
                        </v-col>
                    </v-row>
                </v-card-text>
                </v-container>
                </v-card>
        </v-col>
        <v-col
        cols="6">
          <v-row>
            <v-card v-for="item in myHackerThon" :key="item.key"
            style="width:100%">
            <v-col
            cols="10">
              <v-row>
                <v-col
                cols="2"
                style="margin:auto;width:100%">
                <v-img
                    :src="item.img"
                    >
                    </v-img>
                  
                </v-col>
                <v-col
                cols="10">
                  <h3>{{item.title}}</h3>
                  <h4>{{item.start}} ~ {{item.end}}</h4>
                  <h4>{{item.peopleNow}} / {{item.peopleNum}}</h4>
                  <div>
                    {{item.info}}
                  </div>
                </v-col>
              </v-row>
            </v-col>
            </v-card>
          </v-row>
        </v-col>
        <v-col 
        cols="6">
          <v-col
          cols="12"
          v-if="timeout" 
          >
          <v-card 
            v-for="item in myTeam"
            :key="item.key"
            >
            <v-row>
              <v-col
              cols="3">
                <div style="text-align:center;font-weight:bold;font-size:1.2rem">{{item.team.teamName}}</div>
                <div style="text-align:center">{{item.team.teamDate}}</div>
                <div style="text-align:center">{{item.team.teamMemberNum}}명</div>
                <div style="text-align:center"><a style="text-decoration:none" :href="item.team.githubRepoUrl">Repository</a></div>
              </v-col>
              <v-col
              cols="4" 
              >
                <p v-for="person in item.teamMember" :key="person.key">
                 {{person.email}} <v-icon v-if="(item.role === 'LEADER') && (person.email !== item.email)" @click="remove(item.team.teamId, person.email)" style="margin-left:15px">fa-cut</v-icon> 
                </p>
            </v-col>
            <v-col
            cols="5"
            style="margin-bottom:auto;margin-top:auto"
           >
        
          <v-btn
          fab
          style="margin-left:10px"
          @click="create_modal(item)"
          v-if="(item.team.teamMemberNum !== 1)"
          >
          <v-icon>
            fa-clipboard
          </v-icon>
          </v-btn>
          <v-btn
          style="margin-left:10px"
          fab
          v-if="item.role === 'LEADER'"
          @click="delete_t(item.team.teamId)">
            <v-icon>
              fa-times
            </v-icon>
          </v-btn>
          <v-btn
          fab
          style="margin-left:10px"
          @click="start_h(item.team.teamId)"
          v-if="(item.team.teamState === 'READY') && (item.role === 'LEADER')"
          >
          <v-icon>
            stream
          </v-icon>
          </v-btn>

            </v-col>
            </v-row>
            </v-card>

          </v-col>  
        </v-col>
    </v-row>
    </div>
</template>
<script>
import http from '../http-common'
  export default {
    name : 'test',
    data () {
      return {
        timeout : false,
        hackertoned: [],
        alarams:[],
        token : '',
        myTeam : [],
        u_id : '',
        u_name : '',
        u_phone : '',
        u_job : '',
        myHackerThon : [],
        evaluating:false,
        team : {},
        points : {}
      }
    },
    methods: {
      init(){
        http.get('/getBoard')
        .then(response =>{
          this.hackertoned = response.data
          })
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
          http.get('/user')
          .then(response=>{
              this.u_id = response.data.member.email;
              this.u_name = response.data.member.name;
              this.u_phone = response.data.member.phone;
              this.job = response.data.member.job;
              this.get_my_hackerthon(this.u_id)
            })
      },
      get_alaram(){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
        http.get('team/accept')
        .then(response =>{
            this.alarams = response.data
        })
      },
      accept(info){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
        http.patch('team/accept/'+info.teamMemberId)
        .then(response =>{
          if(response.data.state==='succ'){
            this.get_alaram()  
            this.get_current()
          }
        })
      },
      refuse(info){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token');
        http.delete('team/refuse/' + info.teamMemberId)
        .then(response =>{
          if(response.data.state === 'succ'){
            this.get_alaram()
          }
        })
      },
      get_current(){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
        http.get('team/user/teams')
        .then(response => {
          this.myTeam = response.data
          for(var i = 0; i < this.myTeam.length; i++){
            this.get_member(i)
            }
        })
      },
      get_member(teamId){
        http.patch('team/members/' + this.myTeam[teamId].team.teamId)
        .then(response_2 =>{
          this.$set(this.myTeam[teamId], 'teamMember', response_2.data)
          if (teamId === (this.myTeam.length-2)){
            this.timeout = true
          }
        })
      },
      remove(teamId,member){
        var answer = confirm('이 팀원을 추방하시겠습니까?')
        if(answer){
          http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
          http.delete(`team/outTeam/${teamId}/${member}`)
          .then(response =>{
            if(response.data.state === 'succ'){
              this.get_current()
            }
          })
          }
        },
      get_my_hackerthon(email){
        http.patch('/sponsor/boards/' + email)
        .then(response => {
          this.myHackerThon = response.data
        })
      },
      delete_t(teamId){
        var answer = confirm('팀을 삭제하시겠습니까?')
        if(answer){
          http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
          http.delete('team/confirm/'+teamId)
          .then(response => {
            if(response.data.state === 'succ'){
              this.get_current()
            }
          })
        }
      },
      create_modal(Team){
        var evaluated = []
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
        http.get('eval/member/send/' + Team.team.teamId)
        .then(response =>{
          evaluated = response.data
          for(var person in Team.teamMember){
            var same = false
            for (var cp in evaluated.data){
              if(Team.teamMember[person].email === evaluated.data[cp].toMember){
                same = true
                this.$set(Team.teamMember[person], 'points',{})
                this.$set(Team.teamMember[person].points,'score',evaluated.data[cp].score)
                this.$set(Team.teamMember[person].points, 'str',evaluated.data[cp].info)
                this.$set(Team.teamMember[person].points, 'evaluated', true)
               
              }
            }
            if (!same){
              this.$set(Team.teamMember[person],'points',{})
              this.$set(Team.teamMember[person].points, 'score', 0)
              this.$set(Team.teamMember[person].points,'str','')
              this.$set(Team.teamMember[person].points, 'evaluated', false)
            }
          }
          this.team=Team;
          this.evaluating=true
        })
      },
      pyunga(){
        for(var i in this.team.teamMember){
          if(this.team.teamMember[i].email !== this.u_id && !this.team.teamMember[i].points.evaluated){
            http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
            http.post('eval/member/',{
              "info" : this.team.teamMember[i].points.str,
              "score" : this.team.teamMember[i].points.score,
              "teamId" : this.team.team.teamId,
              "toMemberId" : this.team.teamMember[i].email
            })
            .then(response => {
              if(response.data.state === 'succ'){
                this.evaluating=false;
              }
            })
          }
        }
      },
      start_h(team){
        var answer = confirm('헤커톤 시작합니다')
        if (answer){
          http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
          http.patch('team/confirm/' + team).
          then(response =>{
            if(response.data.state === 'succ'){
              var gitName = prompt('사용하실 Github Repository 이름을 입력해주세요')
              console.log(gitName)
              // this.make_github(gitName)
              this.get_Current()
            }
          })
        }
      },
      make_github(gitName){
        http.defaults.headers.common['x-access-token'] = sessionStorage.getItem('x-access-token')
        http.get('team/checkRepositoryName/'+gitName)
        .then(response => {
          console.log(response)
        })
      }
    
      
    },
    mounted(){
      this.init()
      this.get_alaram()
      this.get_current()
      
    }
  }
</script>
