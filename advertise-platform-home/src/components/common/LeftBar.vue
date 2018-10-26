<!-- 左侧导航-->
<template>
    <div class="leftbar-wrap">
        <Menu class="leftbar-menu" mode="horizontal" :active-name="menuActiveName"  @on-select="menuSelect" theme="light" width="auto">
            <MenuItem :name="menu.advertise.index" class="leftbar-menu-item" v-if="isAdvertise">
                首页
            </MenuItem>
            <MenuItem :name="menu.advertise.readAdvertise" class="leftbar-menu-item" v-if="isAdvertise">
                按阅读推广
            </MenuItem>
           <!--  <MenuItem :name="menu.advertise.positionAdvertise" class="leftbar-menu-item" v-if="isAdvertise">
                按位置推广
            </MenuItem> -->
            <MenuItem :name="menu.advertise.financeRecord" class="leftbar-menu-item" v-if="isAdvertise">
                财务管理
            </MenuItem>
            <MenuItem :name="menu.advertise.account" class="leftbar-menu-item" v-if="isAdvertise">
                用户中心
            </MenuItem>

            <MenuItem :name="menu.media.index" class="leftbar-menu-item" v-if="isMedia">
                首页
            </MenuItem>
            <MenuItem :name="menu.media.media" class="leftbar-menu-item" v-if="isMedia">
                媒体管理
            </MenuItem>
            <MenuItem :name="menu.media.readMedia" class="leftbar-menu-item" v-if="isMedia">
                阅读接单
            </MenuItem>
             <!-- <MenuItem :name="menu.media.positionMedia" class="leftbar-menu-item" v-if="isMedia">
                位置接单
            </MenuItem>  -->
            <MenuItem :name="menu.media.order" class="leftbar-menu-item" v-if="isMedia">
                订单管理
            </MenuItem>
            <MenuItem :name="menu.media.financeRecord" class="leftbar-menu-item" v-if="isMedia">
                财务管理
            </MenuItem>
            <MenuItem :name="menu.media.account" class="leftbar-menu-item" v-if="isMedia">
                用户中心
            </MenuItem>
            <a style="display:inline-block;margin-right:0;" :href="goQq" target="_black"><Button type="error" size="large"  icon="ios-call-outline">专属客服</Button></a>
        </Menu>
    </div>
</template>
<script>
      export default {
    name: 'LeftBar',
    data () {
      return {
          //菜单
          menu:{},
          goQq:'',
          isAdvertise:false,//默认非广告主
          isMedia:false,//默认非媒体主
          //活动的菜单，即显示菜单
          menuActiveName:null
       
      }
    },
    mounted(){
        this.goQq="http://wpa.qq.com/msgrd?v=3&uin="+JSON.parse(sessionStorage.getItem("config")).serviceQq+"&site=qq&menu=yes";
    },
    methods: {
     menuSelect(name){
         this.$router.push(name);
         this.menuActiveName=name;
     },
     //跳转qq
    //  goQq(){
    //      location.href="http://wpa.qq.com/msgrd?v=3&uin="+JSON.parse(sessionStorage.getItem("config")).serviceQq+"&site=qq&menu=yes";
    //  },
     //初始化菜单
     initMenu(){
         let pp=JSON.stringify(this.pathParams)
         this.menu={
             //广告主
            advertise:{
                //首页
                 index:"/main/advertise/index/"+pp,
                //按阅读推广
                 readAdvertise:"/main/advertise/readAdvertise/"+pp,
                 //按位置推广
                 positionAdvertise:"/main/advertise/positionAdvertise/"+pp,
                 //财务管理
                 financeRecord:"/main/advertise/financeRecord/"+pp,
                 //用户中心
                 account:"/main/advertise/account/"+pp
            },
             //媒体主
            media:{
                //首页
                 index:"/main/media/index/"+pp,
                //媒体管理
                 media:"/main/media/media/"+pp,
                //阅读接单
                 readMedia:"/main/media/readMedia/"+pp,
                 //位置接单
                 positionMedia:"/main/media/positionMedia/"+pp,
                  //订单管理
                 order:"/main/media/order/"+pp,
                 //财务管理
                 financeRecord:"/main/media/financeRecord/"+pp,
                 //用户中心
                 account:"/main/media/account/"+pp
            },
         }
         if(JSON.parse(sessionStorage.getItem("role")).name=='广告主'){
             this.menuActiveName=this.menu.advertise.index;
             this.isAdvertise=true;

         }
         if(JSON.parse(sessionStorage.getItem("role")).name=='媒体主'){
             this.menuActiveName=this.menu.media.index;
             this.isMedia=true;
         }
     }
    },
    watch:{

    },
    created(){
       //判断是否超级管理员
        this.isSuperAdmin=this.business.getIsSuperAdmin()
        this.pathParams={
          currentPage:1,//当前页
          accountId:this.business.getAccount().accountId  
        }
        this.initMenu();
    //this.isSuperAdmin= this.business.getIsSuperAdmin();
    //监听点击返回
    this.Hub.$on('routerChange', (msg) => { //Hub接收事件
        //this.msg = 'hehe';
        this.$nextTick(()=>{
            //this.menuActiveName=msg;
            this.menuSelect(msg)
       // console.log(this.menuActiveName)
        });
    });
    }
  }
</script>
<style lang="less">
@import "LeftBar.less";
</style>
