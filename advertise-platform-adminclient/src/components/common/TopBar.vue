<!-- 顶部 -->
<template>
    <div class="topbar">
        <div class="topbar-left" :style="{width:topbarWidth}">
           <img src="/static/logo.jpg" style="width:76px;height:76px;"/>
            <span class="topbar-left-title" :style="{fontSize:titleFontSize}" >雅耀广告平台</span>
        </div>
        <div style="display:inline-block;height:82px;vertical-align:top;line-height:82px;" v-show="this.account.accountId">
          <Icon @click.native="collapsedSider"  :class="rotateIcon"  :style="{margin: '0 20px'}" type="md-menu" size="50"></Icon>
        </div>
       <!--  <div class="topbar-loginregister" v-if="!islogin">
          <router-link class="footer-bar-company" to="/login"><Button size="large" type="primary" ghost>登录</Button></router-link>
          <router-link class="footer-bar-company" to="/register"><Button size="large" type="primary">注册</Button></router-link>
        </div> -->
        <div class="topbar-right" v-if="islogin">
            <span  class="topbar-right-logintext" v-text="account.realname||account.nickname||account.phone" ></span>
            <Button type="text"  class="topbar-right-loginout" @click="loginout">退出</Button>
        </div>
    </div>
</template>
<script>
  export default {
    name: 'TopBar',
    created(){ 
      
       this.routerPath=this.$route.path;
       
      if(this.routerPath.indexOf("/main")>-1||this.routerPath.indexOf("/login")>-1||this.routerPath.indexOf("/register")>-1){
        //是否登陆
        this.getIslogin();
       }
    },
    watch: {
  //   //监听父组件的变量
  //  'isLogin': function (n, o) {
  //    console.log(n)
  //   this.islogin=n;
  //  },
  //   //监听自身的变量
  //  'islogin': function (n, o) {
  //    console.log(n)
  //   this.islogin=n;
  //  }
  },
    data () {
      return {
        routerPath:this.$route.path,
        islogin:false,
        account:{},
        isCollapsed: false,//默认
      }
    },
     computed: {
        rotateIcon () {
            return [
                'menu-icon',
                this.isCollapsed ? 'rotate-icon' : ''
            ];
        },
        topbarWidth(){
          return this.isCollapsed?'76px':'260px'
        },
        titleFontSize(){
          return this.isCollapsed?'0':'100%'
        },
     },
    methods: {
      //点击切换
        collapsedSider () {
            this.isCollapsed=!this.isCollapsed
            this.Hub.$emit('isCollapsed',this.isCollapsed);
               
            },
      //跳转到登陆页面
      gologinpage(){
        this.$router.push("/login");
      },
      //退出登陆
     loginout(){
      this.$Modal.confirm({
              title: '退出登陆？',
              content: '<p>确定退出登陆吗？</p>',
              onOk: () => {
                this.axios({
                method:"post",
                url:'/account/loginout?accountId='+this.business.getAccount().accountId,
                withCredentials: true
              }).
              then(res => {
                console.log(res)
                if (res.data.code == 200) {
                  this.islogin=true;
                  sessionStorage.clear();
                  this.$emit("islogin",this.islogin);
                  this.$Message.success('退出成功！');
                  this.$router.push('/login')
                } else {
                  this.$emit("islogin",this.islogin);
                  this.$Message.error('失败')
                }
              }).catch(res => {
                this.$Message.error('系统异常')
              })
              },
              onCancel: () => {
                  this.$Message.success('取消成功！');
              }
          });
     },
      //验证是否登陆
      getIslogin(){
        if(!sessionStorage.getItem("account")){
          sessionStorage.clear()
          this.$emit("islogin",this.islogin);
          if(this.routerPath.indexOf("/main")>-1
          ||this.routerPath.indexOf("/login")>-1
          ){
            this.$nextTick(()=>{
            this.$router.push('/login')
            });
          }
          return;
        }
        this.axios({
               method:"post",
               url:'/account/islogin',
               withCredentials: true
            }).
            then(res => {
              console.log(res)
              if (res.data.code == 200) {
                this.islogin=true;
                //设置本地account
                this.account=res.data.data[0];
                this.$emit("islogin",this.islogin);
                //this.$router.push('/main')
              } else {
                sessionStorage.clear()
                this.$emit("islogin",this.islogin);
                this.$router.push('/login')
                this.$Message.info('请登录')
              }
            }).catch(res => {
              this.$Message.error('系统异常')
            })
      }
    }
  }
</script>
<style lang="less">
@import "TopBar.less";
.menu-icon{
        transition: all .3s;
        cursor:pointer;
    }
  .rotate-icon{
      transform: rotate(-90deg);
  }
</style>
