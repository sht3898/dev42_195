<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=749b9a98fa01a8272d220e2d44483af7"></script>
<template>
    <v-dialog v-model="dialog" width="500px" @click.stop="dialog = true">
      <template v-slot:activator="{ on }">
        <v-btn color="white"
        text
        style="padding-left:0" v-on="on">대전광역시 유성구 동서대로 98-39</v-btn>
      </template>
  <v-card>
        <v-card-title class="headline"><h1>찾아오시는 길</h1></v-card-title><hr>
            <KakaoMap></KakaoMap>
        <v-card-actions style="justify-content: center">
          <v-btn color="black" text @click="dialog = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>
<script>
import KakaoMap from './KakaoMap'
export default {
    name: 'OurMap',
    components: {
        KakaoMap,
    },
    data() {
        return {
            dialog: false,
        }
    },
}
</script>