<template>
  <v-row>
  <v-container fill style="width:100%;align-items:center; padding:0;">
    <v-row style="align-contents:center;margin:0; margin-top:1rem">
    <v-col
      cols="12" style="background:orange;margin:auto">
      <card_list> </card_list>
    </v-col>
    </v-row>
    <v-row row style="align-contents:center;margin:0;">
      <v-col
      cols="12" style="margin-top:0px; justify-content: center">
      <alarm></alarm>
      </v-col>
    </v-row>
  </v-container>
  </v-row>
  </template>
<script>
import card_list from '../components/card_list';
import alarm from '../components/alarm';
export default {
  name: 'HelloWorld',
  components : {
    card_list,
    alarm,
  },
  data(){
    return {
      modal : false,
      message : '',
      dialog: false,
      contents:null,
    }
  },
  methods:{
  },
  mounted(){
  }
}
</script>

