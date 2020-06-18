<template>
  <div style="width:100%">
    <v-img src="https://cdn.dribbble.com/users/1194373/screenshots/4212918/ezgif.com-gif-maker.gif"
            height="50vw">
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
