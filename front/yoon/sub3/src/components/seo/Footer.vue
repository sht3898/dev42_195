<template>
    <v-footer
    color="#424242"
    padless
  >

  <!-- 로고 위치 -->
  <v-col
  cols="2"
  style="text-align: -webkit-center">
    <v-img
    style="width:80%"
    :src="images.logo"
    >
    </v-img>
  </v-col>


  <!-- 나머지 정보 -->
  <v-col
  cols="10"
  >
    <v-row
      no-gutters
    >
  
    <!-- 개인정보 처리 방침 -->

      <Policy color="white" title="개인정보처리약관 | "></Policy>

      <!-- 개발자들/ 문의 이메일 -->
      <v-btn
        v-for="item in firstMenuItems"
        :key="item.key"
        :to="item.path"
        color="white"
        text
        style="padding-left:0"
        :ripple="false"
      >
        {{ item.title }} | 
      </v-btn>
    </v-row>

    <!-- 주소 -->
    <OurMap></OurMap>
    <!-- <v-row
      no-gutters
    >
      <v-btn
        v-for="item in secondMenuItems"
        :key="item.key"
        :to="item.path"
        color="white"
        text
        style="padding-left:0"
      >
        {{ item.title }}
      </v-btn>
    </v-row> -->
    
    <!-- 저작권 및 날짜 표시 -->
    <v-row
      no-gutters
      class="lighten-1 white--text"
    >
    <p
      text
      class="my-2"
    >
      Copyright by M5 Co, Ltd. All rights reserved ⓒ{{ new Date().getFullYear() }}
    </p>
    </v-row>

  </v-col>
  </v-footer>
</template>
<script>
import Policy from './Policy'
import OurMap from './OurMap'
export default {
    components: {
      Policy,
      OurMap,
    },
    name: 'Footer',
    data() {
        return {
          images: {
            logo: require('../../assets/m5.png')
          },
          firstMenuItems: [
              {title: '만든 사람들', path:'/aboutuspage'},
              {title: '문의사항: godofssafy@ssafy.com'},
          ],
          secondMenuItems: [
              {title: '대전광역시 유성구 동서대로 98-39 삼성화재 유성캠퍼스',
              },
          ],
        }
    },
    methods: {
        
    }
}
</script>