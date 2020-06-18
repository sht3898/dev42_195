<template>
    <v-row style="margin:10px" justify="center">
        <v-col cols="5">
            <!-- 내 피드 알림 리스트 -->
            

<template>
  <v-layout row>
      <v-card>
        <v-toolbar color="pink" dark>
          <v-toolbar-side-icon></v-toolbar-side-icon>
          <v-toolbar-title>Inbox</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>search</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>check_circle</v-icon>
          </v-btn>
        </v-toolbar>
        <v-list two-line>
          <template v-for="(item, index) in items">
            <v-list-tile
              :key="item.title"
              avatar
              ripple
              @click="toggle(index)"
            >
              <v-list-tile-content>
                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                <v-list-tile-sub-title class="text--primary">{{ item.headline }}</v-list-tile-sub-title>
                <v-list-tile-sub-title>{{ item.subtitle }}</v-list-tile-sub-title>
              </v-list-tile-content>
              <v-list-tile-action>
                <v-list-tile-action-text>{{ item.action }}</v-list-tile-action-text>
                <v-icon
                  v-if="selected.indexOf(index) < 0"
                  color="grey lighten-1"
                >star_border</v-icon>
                <v-icon
                  v-else
                  color="yellow darken-2"
                >star</v-icon>
              </v-list-tile-action>
            </v-list-tile>
            <v-divider v-if="index + 1 < items.length" :key="index"></v-divider>
          </template>
        </v-list>
      </v-card>
  </v-layout>
</template>        
</v-col>
        <v-col cols="7">
            <!-- card and textfiled -->
                        <v-card width="80vw" >
            <v-container>
                <v-card-title class="headline" style="margin:auto" > 신청자 정보 </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col cols="6">
                            이름
                            <v-text-field   readonly required></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            ID
                            <v-text-field  readonly required></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            소속
                            <v-text-field  label="소속"></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            전화번호
                            <v-text-field  readonly  required></v-text-field>
                        </v-col>
                    </v-row>
                </v-card-text>
                </v-container>
                </v-card>
        </v-col>
        <v-col cols="6">
            참여한 공모전 수평 카드
            <v-card>
            </v-card>
        </v-col>
        <v-col cols="6">
            <v-col cols="12">
                프로젝트 세 줄 아바타
            </v-col>
            <v-col cols="12">
                조직한 해커톤 세 줄 아바타
            </v-col>
        </v-col>
    </v-row>
</template>
<script>
  export default {
    data () {
      return {
        selected: [2],
        items: [
          { action: '15 min', headline: 'Brunch this weekend?', title: 'Ali Connors', subtitle: "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?" },
          { action: '2 hr', headline: 'Summer BBQ', title: 'me, Scrott, Jennifer', subtitle: "Wish I could come, but I'm out of town this weekend." },
          { action: '6 hr', headline: 'Oui oui', title: 'Sandra Adams', subtitle: 'Do you have Paris recommendations? Have you ever been?' },
          { action: '12 hr', headline: 'Birthday gift', title: 'Trevor Hansen', subtitle: 'Have any ideas about what we should get Heidi for her birthday?' },
          { action: '18hr', headline: 'Recipe to try', title: 'Britta Holt', subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.' }
        ]
      }
    },

    methods: {
      toggle (index) {
        const i = this.selected.indexOf(index)

        if (i > -1) {
          this.selected.splice(i, 1)
        } else {
          this.selected.push(index)
        }
      }
    }
  }
</script>
