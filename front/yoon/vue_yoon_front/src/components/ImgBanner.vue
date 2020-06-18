<template>
  <div>
    <v-img src="https://www.onlygfx.com/wp-content/uploads/2017/07/yellow-watercolor-brush-stroke-2.png"
           aspect-ratio="3">
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span class="text-shadow display-2 font-weight-light">
             <div style="line-height:1.2em;font-size:10vw;font-weight:bold;font-style:italic" class="text-center">DEV 42.195</div>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
  </div>
    
</template>



<script>

export default {
	name: 'ImgBanner',

	methods: {
	},
}
</script>
<style>
  .text-shadow {
    text-shadow: 0 0 15px rgb(255,255,255);
  }
</style>
