<template>
<v-container>
    <v-card style="height:30rem">
    <v-expansion-panels  style="height:30vw; overflow-x:scroll">
      <v-expansion-panel
      v-for="i in 100"
      :key="i.key">
        <v-expansion-panel-header expand-icon="mdi-menu-down"> i.leader으로부터의 알람이 왔습니다</v-expansion-panel-header>
        <v-expansion-panel-content>
          <div>
          i.leader님이 i.team.teamName팀으로 초대하셨습니다.<br/>
          수락하시겠습니까?
          </div>
          <div style="align-items:end">
            <button> 수락 </button> / <button> 거절</button>
          </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-card> 
    </v-container>   
</template>
<script>
export default {
    
}
</script>