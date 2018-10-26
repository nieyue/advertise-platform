<!-- 右侧主体 -->
<template>
  <Layout class="rightbody-wrap">
            <content class="rightbody-content">
                <Card class="rightbody-content-body" >
                    <div style="height: auto">
                         <router-view></router-view>
                    </div>
                </Card>
            </content>
        </Layout>
</template>
<script>
    export default {
    name: 'RightBody',
    created(){
        
    },
    data () {
      return {
       
      }
    },
    methods: {
    //返回历史路径
     goBack () {
       // this.$router.go(-1)
        this.$router.back()
        console.log(this.$router)
        console.log(this.$router.history.current.fullPath)

       setTimeout(()=>{
            this.Hub.$emit('routerChange',this.$router.history.current.fullPath); //Hub触发事件
       },200)
      },
    } 
  }
</script>
<style lang="less">
@import "RightBody.less";
</style>