<!--Swagger管理 -->
<template>
    <div class="body-wrap">
         <iframe :src="swaggerurl"  
            frameborder="0" style="padding: 0px; width: 100%; height: 660px;"></iframe>
    </div>
</template>
<script>
export default {
  name: 'Swagger',
  data () {
    return {
        swaggerurl:''
    }
  },
  methods: {
  },
  created () {
      this.swaggerurl=this.axios.defaults.baseURL+"/swagger-ui.html"
  },
  mounted () {

  }
}
</script>

