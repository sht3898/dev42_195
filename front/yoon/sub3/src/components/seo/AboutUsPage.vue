<template>
    <v-container>
        <h1 style="text-align: center; margin: 30px">
            DEV 42.195 개발자들!
        </h1>
        <v-row dense>
        <v-col
          v-for="member in ours"
          :key="member.key"
          cols="12"
        >
        <v-col xs="12" sm="10" md="8" lg="6" style="margin:auto;">
          <v-card
            v-if="member.num % 2 === 1"
          >
          <v-row>
            <v-col cols="6">
                <v-card-title
                  class="headline"
                  v-text="member.name"
                ></v-card-title>

                <v-card-subtitle v-text="member.job"></v-card-subtitle>
                <v-card-text v-text="member.content"></v-card-text>
            </v-col>
            <v-col cols="5">
              <v-avatar
                class="ma-3"
                tile
                size ="100"
              >
                <v-img style="margin:" :src="member.path" ></v-img>
              </v-avatar>
            </v-col>
          </v-row>
          </v-card>

          <v-card
            v-else-if="member.num % 2 === 0"
          >
          <v-row>
            <v-col cols="5">
              <v-avatar
                class="ma-3"
                size = "100"
                tile
              >
                <v-img :src="member.path" contain style="margin:auto"></v-img>
              </v-avatar>
            </v-col>
              <v-col cols="6">
                <v-card-title
                  class="headline"
                  v-text="member.name"
                ></v-card-title>

                <v-card-subtitle v-text="member.job"></v-card-subtitle>
                <v-card-text v-text="member.content"></v-card-text>
              </v-col>

            </v-row>
          </v-card>
        </v-col>


        </v-col>
      </v-row>


        
    </v-container>
</template>
<script>
export default {
    components: {
        
    },
    name: 'aboutus',
    data() {
        return {
            ours: [
                {name: '서현택', job: 'Front 담당', content:'통계학과/컴퓨터공학과',  path: require('../../assets/taek.png'), num: 1},
                {name: '홍순범', job: 'Front 담당', content: '심리학과/컴퓨터공학과', path: require('../../assets/beom.png'), num: 2},
                {name: '정대윤', job: 'Front 담당', content: '경영학과/컴퓨터공학과', path: require('../../assets/yoon.png'), num: 3},
                {name: '안우진', job: 'Back 담당', content: '컴퓨터공학과', path: require('../../assets/woo.png'), num: 4},
                {name: '이은규', job: 'Back 담당', content: '컴퓨터공학과', path: require('../../assets/que.png'), num: 5}
            ]
        }

    },
    methods: {

    }
}
</script>