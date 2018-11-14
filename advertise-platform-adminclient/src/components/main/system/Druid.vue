<!--Druid Monitor管理 -->
<template>
    <div class="body-wrap">
         <iframe :src="druidurl"  
            frameborder="0" style="padding: 0px; width: 100%; height: 660px;"></iframe>
    </div>
</template>
<script>
export default {
  name: 'Druid',
  data () {
    return {
        druidurl:''
    }
  },
  methods: {
  },
  created () {
      this.druidurl=this.axios.defaults.baseURL+"/druid"
  },
  mounted () {

  }
}
</script>

