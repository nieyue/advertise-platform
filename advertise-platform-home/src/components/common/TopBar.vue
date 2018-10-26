<!-- 顶部 -->
<template>
<div class="topbar-wrap">
    <div class="topbar">
        <div class="topbar-left">
           <img src="/static/logo.jpg" style="width:80px;height:80px;"/>
            <span class="topbar-left-title">雅耀广告平台</span>
        </div>
        <div class="topbar-nav" v-if="!islogin">
          <a v-for="nav in navs" @click="navclick(nav)" :style="{color:nav.href==currentnav?'#4cb5ff':'#000'}">{{nav.value}}</a>
        </div>
        <div class="topbar-loginregister" v-if="!islogin">
          <!-- <Button size="large" @click="gologinpage">登录</Button> -->
          <router-link class="footer-bar-company" to="/login"><Button size="large" type="primary" ghost>登录</Button></router-link>
          <router-link class="footer-bar-company" to="/register"><Button size="large" type="primary">注册</Button></router-link>
        </div>
        <div class="topbar-right" v-if="islogin">
            <span class="topbar-right-logintext">
              可用余额：<span class="common-primary" v-text="finance.money"></span>元
              <a class="common-primary" @click="moneyGo">[<span v-text="moneytext"></span>]</a>
            </span>
            <span class="topbar-right-logintext">
              冻结余额：<span class="common-primary" v-text="finance.frozen"></span>元
            </span>
            <span class="topbar-right-logintext">
              消息：(<span class="common-primary" v-text="noticeCount"></span>)
            </span>
            <span class="topbar-right-logintext">
              账号：<span class="common-primary" v-text="account.realname||account.nickname||account.phone||account.email" ></span>
            </span>
            <span class="topbar-right-logintext">
              <a  class="common-black" @click="loginout">[退出]</a>
            </span>
        </div>
    </div>
</div>
</template>
<script>
  export default {
    name: 'TopBar',
    created(){ 
       this.routerPath=this.$route.path;
       //设定当前路径显示
       this.currentnav=this.routerPath;
       
      if(this.routerPath.indexOf("/main")>-1||this.routerPath.indexOf("/login")>-1||this.routerPath.indexOf("/register")>-1){
        //是否登陆
        this.getIslogin();
       }
       this.getConfig();
       if(this.business.getAccount()){
         this.pathParams={
           currentPage:1,//当前页
          accountId:this.business.getAccount().accountId 
        }
        if(JSON.parse(sessionStorage.getItem("role")).name=='广告主'){
          this.moneytext="充值"
        this.moneyLink=  "/main/advertise/financeRecord/"+JSON.stringify(this.pathParams)
        }
        if(JSON.parse(sessionStorage.getItem("role")).name=='媒体主'){
          this.moneytext="提现"
          this.moneyLink=  "/main/media/financeRecord/"+JSON.stringify(this.pathParams)
        }
       }
       //获取finance
       this.Hub.$on('getFinance', (accountId) => { //Hub接收事件
       this.$nextTick(()=>{
              this.getFinance(accountId)
        });
    });
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
        config:{},
        finance:{},
        noticeCount:0,
        //广告主显示充值，媒体主为提现
        moneytext:'充值',
        //跳转链接
        moneyLink:'',
        //导航栏
        navs:[
          {
            href:'/index',value:'首页',
          },
          {
            href:'/weixinspread',value:'微信推广',
          },
          {
            href:'/subscriptionfuns',value:'公众号涨粉',
          },
          {
            href:'/channelorder',value:'媒体接单',
          },
          {
            href:'/help',value:'帮助中心',
          }
        ],
        //当前导航
        currentnav:'/index',
      }
    },
    methods: {
      //跳转到充值或提现界面
      moneyGo(){
        this.$router.push(this.moneyLink)
        this.Hub.$emit('routerChange',this.moneyLink); //Hub触发事件
      },
      //导航点击
      navclick(nav){
        //location.href=nav.href;
        this.$router.push(nav.href);
      },
      //跳转到登陆页面
      gologinpage(){
        this.$router.push("/login");
      },
      //获取配置
      getConfig(){
        this.axiosbusiness.get(this,{
         url:'/config/list',
         data:'config',
         success:()=>{
           //console.log(this.finance)
           sessionStorage.setItem("config",JSON.stringify(this.config))
         }
         })
      },
      //获取财务
      getFinance(accountId){
        this.axiosbusiness.get(this,{
         url:'/finance/list?accountId='+accountId,
         data:'finance',
         success:()=>{
           //console.log(this.finance)
         }
         })
      },
      //获取消息数
      getNoticeCount(accountId){
        this.axiosbusiness.get(this,{
         url:'/notice/count?accountId='+accountId,
         data:'noticeCount',
         success:()=>{
           console.log(this.noticeCount)
         }
         })
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
          if(this.routerPath.indexOf("/main")>-1||this.routerPath.indexOf("/login")>-1){
             this.$nextTick(()=>{
            this.$router.push('/login')
             });
          }else if(this.routerPath.indexOf("/register")>-1){
             this.$nextTick(()=>{
            this.$router.push('/register')
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
                this. getFinance(this.account.accountId);
                this. getNoticeCount(this.account.accountId);
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
</style>
