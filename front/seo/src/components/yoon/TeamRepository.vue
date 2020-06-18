<template>
    <v-container>
        <v-row>
          <!-- 팀의 팀원 정보 및 info 지원 날짜-->

          <!-- 지원중인 해커톤에 대한 간단한 정보를 볼 수 있어야 겠다. -->
        <v-col cols="6">
            <material-card
                  color="orange"
                  title="Employee Stats"
                  text="New employees on 15th September, 2016"
                >
            <zingchart v-if="chart_on" :data="chartData"></zingchart>
            </material-card>
        </v-col>
        <v-col cols="6">
                <material-card
                  color="orange"
                  title="Employee Stats"
                  text="New employees on 15th September, 2016"
                >
                <v-layout v-if="chart_on">
                  <v-data-table
                    :headers="headers"
                    :items="items"
                    hide-default-actions
                  >
                    <template
                      slot="items"
                      slot-scope="{ index, item }"
                    >
                      <td>{{ index + 1 }}</td>
                      <td>{{ item.name }}</td>
                      <td class="text-xs-right">{{ item.date }}</td>
                      
                      
                    </template>
                  </v-data-table>
                </v-layout>
                </material-card>
        </v-col>
        <v-col cols="12">
          <material-card
                  color="orange"
                  title="Employee Stats"
                  text="New employees on 15th September, 2016"
                >
            <div v-html="input"></div>
          </material-card>
        </v-col>
        </v-row>

    </v-container>
</template>
<script src="https://unpkg.com/marked@0.3.6"></script>
<script src="https://unpkg.com/lodash@4.16.0"></script>
<script>
import MaterialCard from './material/Card';
import http from '../../http-common';
export default {
    components : {
        MaterialCard,
        
    },
    data() {
        return {
            input: "",
            chart_on : false,
            chartData: {
                type: "pie3d",
                title: {
                  text: ""
                },
                plot: {
                  'offset-r': "25%"
                },
                series: [
                  // { values: [59], text:"123"},
                ]
            },
                   headers: [
          {
            text: 'name',
            align: 'left',
            value: 'name',
          },
          { text: 'Date', value: 'date' },
          
        ],
            items: [
                // {
                  // name: 'Dakota Rice',
                  // country: 'Niger',
                  // city: 'Oud-Tunrhout',
                  // salary: '$35,738'
                // },
            ],
        team_id : "",
        board_id : "",
        }//return
    },//data
watch: {
    input(){
      return marked(this.input, { sanitize: true })
    },
},
    methods: {
      getMarkDown1(){
        let url = "https://github.com/EunQ/devProject"
        url = url.substring(18);
        http.get('/team/getMarkDown/'+url)
        .then(response=>{
          // console.log(response.data);
          return this.input = marked(response.data, { sanitize: true })
        })
        .catch(err=>{
          console.log(err);
        })
      },
      getUsers(){
        http.get('/commit/users/url?url='+'https://github.com/mincheol6073/stoveLeague')
        .then(response=>{
          // console.log(response.data.data);
          return this.chart_init(response.data.data);
        })
        .catch(err=>{
          console.log(err);
        })
      },
      getLastCommits(){
         http.get('/commit/lastCommits/url/5?url='+'https://github.com/mincheol6073/stoveLeague')
         .then(response=>{
           console.log(response.data.data);
           return this.card_data_init(response.data.data); 
         })
         .catch(err=>{
           console.log(err);
         })
      },
      chart_init(value){
        let arr = Object.keys(value);
        console.log(">>><<<<<<<<")
        for(let i=0; i<arr.length; i++){
          this.chartData.series.push({text:arr[i],values:[value[arr[i]]]});
          console.log({text:arr[i],values:[value[arr[i]]]});
        }
      },
      card_data_init(arr){
        for(let i =0; i<arr.length; i++){
          this.items.push({name : arr[i].author, date:arr[i].date, sha : arr[i].sha});
        }
      },
      get_team_info(team_id){
        http.get(`/team/${team_id}`)
        .then(response=>{
          console.log(response.data);
        })
        .catch(err=>{
          console.log(err);
        })
      }
  },
  mounted(){
    this.getMarkDown1();
    this.getUsers();
    this.getLastCommits();
    this.team_id = this.$route.params.team_id;
    this.get_team_info();
    setTimeout(()=>{
      this.chart_on = true;
      // console.log(this.chart_on);
      // console.log(this.chartData.series);
      console.log(this.items)
    },3000);
  }
}
</script>


<!--
  /team/{teamId}
  /getBoardByID/{boardID}
-->