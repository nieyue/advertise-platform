<!-- 右侧主体 -->
<template>
  <Layout class="rightbody-wrap" :style="{marginLeft:marginLeft,transition:transition}">
            <div class="rightbody-header" :style="{width:headerWidth,transition:transition}" >
                <Button type="text"  class="rightbody-header-left" icon="ios-skip-backward" @click="navbackward"></Button>
                <div class="rightbody-header-body-wrap" ref="road">
                    <div class="rightbody-header-body" ref="navroad"  :style="{marginLeft:navroadMarginLeft+'px',transition:transition}">
                        <div class="rightbody-header-body-item-wrap" :style="{backgroundColor:nav.iscurrent?'#2db7f5':'#fff'}" v-for="nav in navList" >
                            <div class="rightbody-header-body-item" @click="goNav(nav)">
                                <div class="rightbody-header-body-item-name" :style="{color:nav.iscurrent?'#fff':'#000'}"  v-text="nav.name">
                                </div>
                            </div>
                            <div  class="rightbody-header-body-item-close" :style="{color:nav.iscurrent?'#fff':'#000'}" @click="deleteTag(nav)">
                                <Icon type="md-close" />
                            </div>
                        </div>
                    </div>
                </div>
                <Button type="text"  class="rightbody-header-right" icon="ios-skip-forward" @click="navforward"></Button>
            </div>
            <content class="rightbody-content">
                <Breadcrumb class="rightbody-content-crumb">
                    <BreadcrumbItem>主页</BreadcrumbItem>
                    <BreadcrumbItem > {{$route.name}}</BreadcrumbItem>
                </Breadcrumb>
                 <div style="display:inline-block">
                    <Button type="info" size = 'small' @click="goBack">返回</Button>
                </div>
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
     //监听切换toggleCollapse
    this.Hub.$on('isCollapsed', (msg) => { //Hub接收事件
       this.$nextTick(()=>{
             this.isCollapsed=msg
              this.isCollapsed ? this.marginLeft='78px' : this.marginLeft='260px'
              this.isCollapsed ? this.headerWidth='calc(100% - 78px)' : this.headerWidth='calc(100% - 260px)'
           console.log(this.isCollapsed)
        });
    });
     //监听菜单点击注册到滑道
    this.Hub.$on('navroad', (currentRoute) => { //Hub接收事件
       this.$nextTick(()=>{
           //console.log(currentRoute)
           let nav={
               iscurrent:true,//是否当前的，默认true
               name:currentRoute.name,
               path:currentRoute.path
           }
          this.navList.forEach(e=>{
               e.iscurrent=false
               })
           this.navList.forEach(e=>{
               if(e.name==nav.name){
              this.navList.splice(this.navList.indexOf(e),1)
               }
           })
            this.navList.push(nav)
            this.navforward()

        });
    });
    },
    data () {
      return {
        navList:[],//存储的导航
        //是否关闭
       isCollapsed: false,
       //左边距离
       marginLeft:'',
       //动画
       transition:'all 0.2s',
       //nav包裹宽度
       headerWidth:'',
       //nav滑道marginLeft
       navroadMarginLeft:0
      }
    },
    methods: {
    //返回历史路径
     goBack () {
        //this.$router.go(-1)
        this.$router.back()
        //console.log(this.$router)
        //console.log(this.$router.history.current.fullPath)
        setTimeout(()=>{
            this.Hub.$emit('routerChange',this.$router.history.current.fullPath); //Hub触发事件
        },200)
      },
    //跳转nav
    goNav(nav){
          this.navList.forEach(e=>{
               e.iscurrent=false
               if(e.name==nav.name){
                 e.iscurrent=true
               }
           })
        this.$router.push(nav.path)
    },
    //删除nav
    deleteTag(nav){
       this.navList.splice(this.navList.indexOf(nav),1)
    },
    //向前滑动
    navforward(){
       //console.log("navforward")
      // console.log(this.$refs.navroad.clientWidth)
       //console.log(this.$refs.navroad.scrollWidth)
       //滑动距离+道路 小于 滑道时 可滑动 
       let slat=200//延
       if(Math.abs(this.navroadMarginLeft)+this.$refs.road.offsetWidth<this.$refs.navroad.offsetWidth+slat){
           this.navroadMarginLeft-=this.$refs.road.offsetWidth/2
       console.log(this.navroadMarginLeft)
       }
    },
    //向后滑动
    navbackward(){
        //console.log("navbackward")
        if(this.navroadMarginLeft<0){
            this.navroadMarginLeft+=this.$refs.road.offsetWidth/2
        }
    },
    } 
  }
</script>
<style lang="less">
@import "RightBody.less";
</style>