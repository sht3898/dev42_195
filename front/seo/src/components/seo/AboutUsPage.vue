<template>
    <v-container>
        <h1 style="text-align: center; margin: 30px">
            DEV 42.195 개발자들!
        </h1>

        <!-- 현택 추가 -->
        <v-card
          v-for="member in ours"
          :key="member.key"
          class="mx-auto"
          max-width="80%"
          outlined
          style="margin-top:1rem"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="headline mb-1">{{member.name}}</v-list-item-title>
              <v-list-item-subtitle>{{member.job}}</v-list-item-subtitle>
              <v-text-field v-text="member.content"></v-text-field>
            </v-list-item-content>
            <v-img
            :src="member.path"
            max-width="100"
            aspect-ratio="1"
            >
            </v-img>
          </v-list-item>

          <v-card-actions style="">
            <a target="_blank" :href="member.github"><v-img :src="images.github_logo" width="1rem" style="margin-left:1rem"></v-img></a>
            <a target="_blank" :href="member.facebook"><v-img :src="images.facebook_logo" width="1rem" style="margin-left:1rem"></v-img></a>
          </v-card-actions>
        </v-card>

  <!-- 현택 추가 끝 -->      
    </v-container>
</template>
<script>
export default {
    components: {
        
    },
    name: 'aboutus',
    data() {
        return {
          images: {
            facebook_logo: require('../../assets/facebook_logo.png'),
            instagram_logo: require('../../assets/instagram_logo.png'),
            github_logo: require('../../assets/github_logo.png')
          },
          ours: [
              {name: '서현택', job: 'Front 담당', content:'통계학과/컴퓨터공학과', path: require('../../assets/taek.png'), num: 1, facebook: 'https://www.facebook.com/', github: 'https://github.com/sht3898'},
              {name: '홍순범', job: 'Front 담당', content: '심리학과/컴퓨터공학과', path: require('../../assets/beom.png'), num: 2, facebook: 'https://www.facebook.com/', github: 'https://github.com/ssshhh0402'},
              {name: '정대윤', job: 'Front 담당', content: '경영학과/컴퓨터공학과', path: require('../../assets/yoon.png'), num: 3, facebook: 'https://www.facebook.com/', github: 'https://github.com/IT403JDY'},
              {name: '안우진', job: 'Back 담당', content: '컴퓨터공학과', path: require('../../assets/woo.png'), num: 4, facebook: 'https://www.facebook.com/', github: 'https://github.com/IT403JDY'},
              {name: '이은규', job: 'Back 담당', content: '컴퓨터공학과', path: require('../../assets/que.png'), num: 5, facebook: 'https://www.facebook.com/', github: 'https://github.com/IT403JDY'}
          ]
        }

    },
    methods: {

    }
}
</script>