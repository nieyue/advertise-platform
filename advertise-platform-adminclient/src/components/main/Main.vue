<template>
<div>
    <!--顶部 -->
    <top-bar :islogin="getIslogin"></top-bar>
    <!--顶部end -->
    <!--主体 -->
     <div class="main-wrap">
        <!--左侧导航栏 -->
        <left-bar></left-bar>
        <!--左侧导航栏end -->
        <!--右侧主体 -->
        <right-body></right-body>
        <!--右侧主体end -->
        
    </div>
    <!--主体 end -->
</div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import LeftBar from '@/components/common/LeftBar'
import RightBody from '@/components/common/RightBody'
  export default {
    name: 'Main',
     components:{
      TopBar,
      LeftBar,
      RightBody
    },
    data () {
      return {
        islogin:false
       
      }
    },
    methods: {
     getIslogin(islogin){
       console.log(islogin)
     }
    },
    created(){
       
    }
  }
</script>
<style lang="less">
@import "Main.less";
</style>
