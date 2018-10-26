<!--欢迎页管理 -->
<template>
    <div class="body-wrap">
        <div>欢迎来到雅耀广告平台配置！现在的时间见是：<span v-text="time"></span></div>
        <!-- <img src="@/assets/logo.png"/> -->
        <my-phone></my-phone>
       <!--  <my-phone></my-phone>
        <my-phone :weixinMpMenuImg="require('@/assets/weixin4.png')"></my-phone> -->
    </div>
</template>
<script>
export default {
  name: 'Welcome',
  data () {
    return {
        time:new Date().toLocaleString()
    }
  },
  methods: {
      ccc(){
          let pp=JSON.stringify({
            currentPage:Math.random(),
            accountId:Math.random()
            })
           this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      }
  },
  created () {
      setInterval(()=>{
          this.time=new Date().toLocaleString()
      },1000)
     // console.log(this.$route.params.pathParams)
  },
  mounted () {

  }
}
</script>

