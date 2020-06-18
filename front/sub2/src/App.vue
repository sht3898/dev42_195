<template>
  <v-app >
    <v-content>
      <side/>
      <router-view/>
    </v-content>
    <Footer/>
  </v-app>
</template>

<script>
import side from './components/side'
import Footer from './components/seo/Footer'
export default {
  name: 'App',
  components : {
    side,
    Footer,
  },
  data: () => ({
    //
  }),
};
</script>
