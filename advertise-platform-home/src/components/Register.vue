<!--首页 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar @islogin="getIslogin"></top-bar>
  <!--顶部 end -->
    <div class="register-wrap">
        <ButtonGroup  class="register-buttongroup">
            <Button :type="roleType1"  @click="changeRoleType(1)" class="register-buttongroup-button" >广告主</Button>
            <Button :type="roleType2" class="register-buttongroup-button" @click="changeRoleType(2)">媒体主</Button>
        </ButtonGroup>
      <Form ref="account"  :label-width="0" class="register-account" :model="account" :rules="loginAccountRules">
        <FormItem prop="adminName" class="register-account-inputwrap">
            <Input type="text"  v-model="account.adminName" size="large" placeholder="输入手机号/email" class="register-account-input">
                <Icon type="ios-phone-portrait" slot="prefix"/>
            </Input>
        </FormItem>
        <FormItem prop="verificationCode" class="register-account-inputwrap">
            <Input type="text"  v-model="account.verificationCode" size="large"  class="register-account-input50"  placeholder="验证码" > 
                <Icon type="ios-image-outline" slot="prefix"/>
            </Input>
            <div class="register-account-input50">
                <img :src="verificationCode" @click="getVerificationCode()" style="height:50px;vertical-align:middle" />
            </div>
        </FormItem>
        <FormItem prop="validCode" class="register-account-inputwrap">
            <Input type="text"  v-model="account.validCode" size="large"  class="register-account-input50"  placeholder="手机/email 验证码"> 
                <Icon type="ios-mail-outline" slot="prefix"/>
            </Input>
            <Button type="primary"  @click="getValidCode()"  class="register-account-input50"  :disabled="validCodeDisabled">{{validCodeValue}}</Button>
        </FormItem>
        <FormItem prop="password" class="register-account-inputwrap">
          <Input type="password"  v-model="account.password" placeholder="密码" class="register-account-input" >
            <Icon type="ios-lock-outline" slot="prefix"/>
          </Input>
        </FormItem>
        <FormItem prop="password2" class="register-account-inputwrap">
          <Input type="password"  v-model="account.password2" placeholder="再次确认密码" class="register-account-input" @keyup.enter.native="register('account')" >
            <Icon type="ios-lock-outline" slot="prefix"/>
          </Input>
        </FormItem>
        <FormItem prop="userprotocol" style="text-align:left;">
          <Checkbox label="用户协议" v-model="account.userprotocol" ></Checkbox>已阅读并同意<router-link to="/help">服务条款</router-link>
        </FormItem>

        <Button type="primary" long  class="register-account-inputwrap" >
          <span v-if="!loading" style="width:100%;display:inline-block;font-size:1.2rem;"  @click="register('account')">{{roleType1=='primary'?'广告主':'媒体主'}}注册</span>
          <span v-else>Loading...</span>
        </Button>
      </Form>
    </div>

    <footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar';
  export default {
    name: 'Register',
    components:{
      TopBar,
      FooterBar
    },
    created(){
        //默认广告主
      this.changeRoleType(1);
    },
    data () {
      return {
        // resource:'',
        //广告主type
        roleType1:'',
        //媒体主type
        roleType2:'',
        //登陆信息
        account: {
          adminName: '',
          password: '',
          roleType:1,//默认广告主
          verificationCode: '',
          validCode:'',
          userprotocol:true
        },
        //是否登陆默认false
        islogin:false,
        //验证码
        verificationCode:'',
        //手机、邮箱验证码
        validCode:'',
        //验证码数字
        validCodeNumber:60,
        //验证码值
        validCodeValue:'获取验证码',
        //验证码Disabled
        validCodeDisabled:false,
        // 点击登录之后等待登录结果而不是多次点击
        loading: false,
        loginAccountRules: {
          adminName: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          password2: [
            { required: true, message: '请再次输入密码', trigger: 'blur' }
          ],
          verificationCode: [
            { required: true, message: '请输入验证码', trigger: 'blur' }
          ],
          validCode: [
            { required: true, message: '请输入手机、email验证码', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      // aduiClick(p){
      //   this.$refs[p].click();
      // },
      //切换角色类型1广告主，2媒体主
      changeRoleType(type){
          this.account.roleType=type;
          if(type==1){
             this.roleType1='primary'; 
             this.roleType2='default'; 
          }
          if(type==2){
             this.roleType1='default'; 
             this.roleType2='primary'; 
          }
      },
      //获取验证码
      getVerificationCode(){
        this.axios({
          method:"post",
          url:'/tool/getVerificationCode',
          withCredentials: true,  
          responseType:'blob'
          })
        .then((res)=>{
          var blob = new Blob([res.data], {type: "image/png"}); 
            console.log(blob);  
          this.verificationCode=window.URL.createObjectURL(blob);

        }).catch((error)=>{
            console.log(error);
            this.$Message.error("错误")
         });
      },
      //注册
      register (name) {
          if(this.account.password!=this.account.password2){
               this.$Message.error("两次密码不一致")
              return;
          }
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.loading = true
            this.axios({
               method:"post",
               url:'/account/webregister',
               withCredentials: true,
               data: this.Qs.stringify(this.account)
            }).
            then(res => {
              this.loading = false
              if (res.data.code == 200) {
                this.$Message.success(res.data.msg)
                
                sessionStorage.setItem("account",JSON.stringify(res.data.data[0].account))
                sessionStorage.setItem("role",JSON.stringify(res.data.data[0].role))
                sessionStorage.setItem("rolePermissionList",JSON.stringify(res.data.data[0].rolePermissionList))
                if(res.data.data[0].role.name=='广告主'){
                  this.$router.push('/main/advertise/index/'+JSON.stringify(this.pathParams))
                }
                if(res.data.data[0].role.name=='媒体主'){
                  this.$router.push('/main/media/index/'+JSON.stringify(this.pathParams))
                }
              
              } else {
                this.$Message.error(res.data.msg)
                this.loading = false
              }
            }).catch(res => {
              this.loading = false
              this.$Message.error('系统异常')
            })
          } else {
            this.$Message.error('用户名和密码是必填项')
          }
        })
      },
      //获取子组件传递上来的值
      getIslogin(islogin){
        this.islogin=islogin;
        if(islogin){
          if(JSON.parse(sessionStorage.getItem("role")).name=='广告主'){
             this.$router.push('/main/advertise/index/'+JSON.stringify(this.pathParams))
          }
          if(JSON.parse(sessionStorage.getItem("role")).name=='媒体主'){
            this.$router.push('/main/media/index/'+JSON.stringify(this.pathParams))
          }
        }else{
          //获取验证码
          this.getVerificationCode();
        }
      },
      //点击按钮获取手机、邮箱验证码
      getValidCode(){
          //不存在，或者即不是手机号也不是邮箱
          if(!this.account.adminName||(!/^1[0-9]{10}$/.test(this.account.adminName)&&!/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/.test(this.account.adminName))){
              this.$Message.error("请填写正确手机号或邮箱")
              return ;
          }
          this.axios({
          method:"post",
          url:'/account/validCode',
          withCredentials: true,  
          data: this.Qs.stringify({
              adminName:this.account.adminName,
              templateCode:1
          })
          })
        .then((res)=>{
         if (res.data.code == 200) {
                let setinterval=setInterval(()=>{
                    this.validCodeDisabled=true;
                    this.validCodeValue=this.validCodeNumber+'秒后可点击';
                    this.validCodeNumber--;
                  if(this.validCodeNumber<0){
                      clearInterval(setinterval)
                      this.validCodeDisabled=false;
                      this.validCodeValue='获取验证码';
                      this.validCodeNumber=60;
                      return;
                  }
                },1000)
                this.$Message.success(res.data.msg)
         }else{
                this.$Message.error(res.data.msg)
         }

        }).catch((error)=>{
            this.$Message.error("错误")
         });

      }
    }
  }
</script>
<style lang="less">
@import "Register.less";
</style>
