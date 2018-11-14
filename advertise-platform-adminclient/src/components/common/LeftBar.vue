<!-- 左侧导航-->
<template>
   <Sider >
            <Menu class="leftbar-wrap" :class="menuitemClasses" :active-name="menuActiveName"  @on-select="menuSelect" theme="light">
                <Submenu name="config">
                    <template slot="title">
                        <Icon type="ios-settings" />
                        <span>配置管理</span>
                    </template>
                    <MenuItem :name="menu.config.welcome">
                        <Icon type="logo-twitter" />
                        <span>欢迎页</span>
                    </MenuItem>
                    <MenuItem :name="menu.config.config">
                        <Icon type="md-medical" />
                        <span>平台配置</span>
                    </MenuItem>
                </Submenu>
                <Submenu name="notice">
                    <template slot="title">
                        <Icon type="ios-volume-up" />
                        <span>通知管理</span>
                    </template>
                    <MenuItem :name="menu.notice.systemNotice">
                        <Icon type="ios-volume-up" />
                        <span>系统通知</span>
                    </MenuItem>
                </Submenu>
                <Submenu name="media">
                    <template slot="title">
                        <Icon type="md-videocam" />
                        <span>媒体管理</span>
                    </template>
                    <MenuItem :name="menu.media.mediaCate">
                        <Icon type="ios-browsers" />
                        <span>媒体类型</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.media">
                        <Icon type="ios-film" />
                        <span>媒体</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.mediaCollection">
                        <Icon type="ios-briefcase" />
                        <span>媒体收藏</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.readMedia">
                        <Icon type="ios-cash" />
                        <span>阅读媒体</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.positionMedia">
                        <Icon type="logo-chrome" />
                        <span>位置媒体</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.mediaDailyData">
                        <Icon type="ios-pie" />
                        <span>媒体日数据</span>
                    </MenuItem>
                    <MenuItem :name="menu.media.mediaData">
                        <Icon type="ios-podium" />
                        <span>媒体详细数据</span>
                    </MenuItem>
                </Submenu>
                <Submenu name="advertise">
                    <template slot="title">
                        <Icon type="md-sunny" />
                        <span>广告管理</span>
                    </template>
                    <MenuItem :name="menu.advertise.readAdvertise">
                        <Icon type="ios-basketball" />
                        <span>阅读广告</span>
                    </MenuItem>
                    <MenuItem :name="menu.advertise.positionAdvertise">
                        <Icon type="md-basketball" />
                        <span>位置广告</span>
                    </MenuItem>
                </Submenu>
                 <Submenu name="account">
                    <template slot="title">
                        <Icon type="md-person" />
                        <span>账户管理</span>
                    </template>
                    <MenuItem name="/main/account/userAccount">
                        <Icon type="ios-contact" />
                        <span>用户</span>
                    </MenuItem> 
                    <MenuItem name="/main/account/mediaAccount">
                        <Icon type="ios-body" />
                        <span>媒体主</span>
                    </MenuItem> 
                    <MenuItem name="/main/account/advertiseAccount">
                        <Icon type="ios-man" />
                        <span>广告主</span>
                    </MenuItem>
                </Submenu>
                 <Submenu name="finance">
                    <template slot="title">
                        <Icon type="logo-yen" />
                        <span>财务管理</span>
                    </template>
                    <MenuItem :name="menu.finance.finance">
                        <Icon type="logo-yen" />
                        <span>财务</span>
                    </MenuItem> 
                </Submenu>
                <Submenu v-if="isSuperAdmin" name="managerAccount">
                    <template slot="title">
                        <Icon type="md-people" />
                        <span>管理员管理</span>
                    </template>
                    <MenuItem name="/main/account/managerAccount">
                        <Icon type="md-people" />
                        <span>管理员管理</span>
                    </MenuItem>
                </Submenu>
                <Submenu v-if="isSuperAdmin" name="rolePermission">
                    <template slot="title">
                        <Icon type="ios-people" />
                        <span>角色权限管理</span>
                    </template>
                    <MenuItem  :name="menu.rolePermission.role">
                        <Icon type="ios-man" />
                        <span>角色</span>
                    </MenuItem>
                    <MenuItem name="/main/permission">
                        <Icon type="md-sunny" />
                        <span>权限</span>
                    </MenuItem>
                </Submenu>
                <Submenu v-if="isSuperAdmin" name="system">
                    <template slot="title">
                        <Icon type="ios-people" />
                        <span>系统管理</span>
                    </template>
                    <MenuItem  name="/main/system/druid">
                        <Icon type="ios-man" />
                        <span>数据库监控</span>
                    </MenuItem>
                    <MenuItem  name="/main/system/swagger">
                        <Icon type="ios-man" />
                        <span>API接口管理(swagger)</span>
                    </MenuItem>
                </Submenu>
            </Menu>
        </Sider>
</template>
<script>
      export default {
    name: 'LeftBar',
    data () {
      return {
          isCollapsed: false,
          //菜单
          menu:{},
          //活动的菜单，即显示菜单
          menuActiveName:'/main/'
       
      }
    },
     computed: {
            menuitemClasses () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
    methods: {
     menuSelect(name){
         this.$router.push(name);
         this.menuActiveName=name;
         //this.navList.splice(this.navList.indexOf(nav),1)
         //点击的菜单注册到滑道
         setTimeout(()=>{
             this.Hub.$emit('navroad',this.$router.currentRoute); //Hub触发事件
         },200)
     },
     //初始化菜单
     initMenu(){
         let pp=JSON.stringify(this.pathParams)
         this.menu={
             //配置
            config:{
                 welcome:"/main/welcome/"+pp,
                 config:"/main/config"
            },
             //通知
            notice:{
                 systemNotice:"/main/systemNotice/"+pp
            },
             //媒体
            media:{
                 mediaCate:"/main/mediaCate/"+pp,
                 media:"/main/media/"+pp,
                 mediaCollection:"/main/mediaCollection/"+pp,
                 readMedia:"/main/readMedia/"+pp,
                 positionMedia:"/main/positionMedia/"+pp,
                 mediaDailyData:"/main/mediaDailyData/"+pp,
                 mediaData:"/main/mediaData/"+pp,
            },
             //广告
            advertise:{
                 readAdvertise:"/main/readAdvertise/"+pp,
                 positionAdvertise:"/main/positionAdvertise/"+pp,
            },
            //财务
            finance:{
                finance:"/main/finance/"+pp
            },
            //角色权限
            rolePermission:{
                 role:"/main/role/"+pp
            },
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
          //accountId:this.business.getAccount().accountId  
        }
        this.initMenu();
    //this.isSuperAdmin= this.business.getIsSuperAdmin();
    //监听点击返回
    this.Hub.$on('routerChange', (msg) => { //Hub接收事件
        //this.msg = 'hehe';
       this.$nextTick(()=>{
            //this.menuActiveName=msg;
            this.menuSelect(msg)
        });
    });
    //监听切换toggleCollapse
    this.Hub.$on('isCollapsed', (msg) => { //Hub接收事件
       this.$nextTick(()=>{
             this.isCollapsed=msg
           //console.log(this.updateActiveName)
        });
    });
    }
  }
</script>
<style lang="less">
@import "LeftBar.less";

/**
**导航切换特效
*/
 .menu-item span{
        display: inline-block;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }
    .menu-item i:first-child{
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }
    .collapsed-menu{
        width:76px !important;
        transition: width .2s ease;
        text-align:center !important;
    }
    .collapsed-menu li div{
        text-align:center !important;
    }
    .collapsed-menu span{
        font-size:0;
        width: 0px;
        transition: width .2s ease;
    }
    .collapsed-menu i:last-child:before{
        font-size:0;
        width: 0;
    }

    .collapsed-menu i:first-child{
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 16px;
        
    }


    //修改子菜单位置
    .ivu-menu-vertical .ivu-menu-submenu .ivu-menu-item{
        padding-left:30px !important;
        height:50px !important;
    }
</style>
