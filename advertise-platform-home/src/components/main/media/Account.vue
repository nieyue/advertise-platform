<!--账户管理 -->
<template>
    <div class="body-wrap">
		 <!--公司信息 -->
    <div>
        <h2 style="margin-left:26%;width:200px;display:inline-block;">公司信息</h2>
        <h2 style="margin-left:22%;width:200px;display:inline-block;">个人信息</h2>
    </div>
<div style="margin-left:10%;width:30%;display:inline-block;height:500px;">
      <Form ref="updateCompany" :model="updateCompany"  :label-width="100" label-position="right"  :rules="updateCompanyRules">
        <FormItem prop="name" label="公司名称:">
          <Input type="text" v-model="updateCompany.name" placeholder="公司名称">
          </Input>
        </FormItem>
        <FormItem prop="industry" label="行业:">
          <Input type="text" v-model="updateCompany.industry" placeholder="行业">
          </Input>
        </FormItem>
        <FormItem prop="address" label="公司地址:">
          <Input type="text" v-model="updateCompany.address" placeholder="公司地址">
          </Input>
        </FormItem>
        <FormItem prop="website" label="公司网址:">
          <Input type="text" v-model="updateCompany.website" placeholder="公司网址">
          </Input>
        </FormItem>
        <FormItem prop="description" label="公司描述:">
          <Input type="textarea"  :autosize="{minRows:5,maxRows:5}" v-model="updateCompany.description" placeholder="公司描述">
          </Input>
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button type='primary' :loading='updateLoading1'@click='updateSure1' size="large" style="width:70%;margin-left:30%;">
          <span v-if="!updateLoading1"  >保存</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
</div>
    <!--公司信息end -->
		 <!--个人修改 -->
<div style="margin-left:10%;width:30%;display:inline-block;height:500px;">
      <Form ref="updateAccount" :model="updateAccount" :label-width="100" label-position="right" >
        <FormItem prop="icon" label="图像(上传或者填写):" >
            <my-upload :defaultUpload="updateAccount.icon" @uploadList="getUpdateIcon"></my-upload> 
        </FormItem>
       <FormItem prop="phone" label="注册手机号:">
          <span v-text="updateAccount.phone||'暂无'"></span>
        </FormItem>
        <FormItem prop="email" label="email:">
          <span v-text="updateAccount.email||'暂无'"></span>
        </FormItem>
        <FormItem prop="nickname" label="昵称:">
          <Input type="text" v-model="updateAccount.nickname" placeholder="昵称">
          </Input>
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button type='primary' :loading='updateLoading2' @click='updateSure2' size="large" style="width:70%;margin-left:30%;">
          <span v-if="!updateLoading2">保存</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
</div>
    <!--个人修改end -->
		 <!--收款方式 -->
     <hr/>
      <h2 style="margin-left:50%;margin-top:3rem;width:200px;display:inline-block;">收款方式</h2>
<div style="margin-left:35%;width:30%;display:inline-block;height:500px;">
      <Form ref="updateBankCard" :model="updateBankCard" :label-width="100" label-position="right" >
        <FormItem prop="realname" label="姓名:">
          <Input type="text" v-model="updateBankCard.realname" placeholder="姓名">
          </Input>
        </FormItem>
        <FormItem prop="identity" label="身份证:">
          <Input type="text" v-model="updateBankCard.identity" placeholder="身份证">
          </Input>
        </FormItem>
        <FormItem prop="bankName" label="银行名:">
          <Input type="text" v-model="updateBankCard.bankName" placeholder="银行名">
          </Input>
        </FormItem>
        <FormItem prop="number" label="银行卡卡号:">
          <Input type="text" v-model="updateBankCard.number" placeholder="银行卡卡号">
          </Input>
        </FormItem>
        <FormItem prop="phone" label="预留手机号:">
          <Input type="text" v-model="updateBankCard.phone" placeholder="预留手机号">
          </Input>
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button type='primary' :loading='updateLoading3' size="large" style="width:70%;margin-left:30%;" @click='updateSure3'>
          <span v-if="!updateLoading3">保存</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
</div>
    <!--收款方式end -->
    </div>
</template>
<script>
export default {
  name: 'Account2',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
			//公司修改参数
			updateCompanyModel:false,
			updateLoading1:false,
			updateCompanyRules: {
                name: [
                    {required: true, message: '公司名称为必填项', trigger: 'blur'}
                    ]
                },
			updateCompany:{
            },
			//修改参数
			updateAccountModel:false,
			updateLoading2:false,
			updateAccount:{},
			//修改参数
			updateBankCardModel:false,
			updateLoading3:false,
      updateBankCard:{},
      
    }
  },
  methods: {
      
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getCompanyList()
      this.getAccountList()
      this.getBankCardList()
    },
    //获取公司列表
    getCompanyList(){
       //获取
      this.axiosbusiness.get(this,{
          url:'/company/list?accountId='+JSON.parse(this.$route.params.pathParams).accountId,
         data:'updateCompany',
         success:()=>{
            console.log(this.updateCompany)
         }
       })  
    },
    //获取账户列表
    getAccountList(){
       //获取
      this.axiosbusiness.get(this,{
          url:'/account/list?accountId='+JSON.parse(this.$route.params.pathParams).accountId,
         data:'updateAccount',
         success:()=>{
            console.log(this.updateAccount)
         }
       })  
    },
    //获取银行卡列表
    getBankCardList(){
       //获取
       this.axios({
               method:"get",
               url:'/bankCard/list?accountId='+JSON.parse(this.$route.params.pathParams).accountId,
               withCredentials: true,
            }).
            then(res => {
              if (res.data.code == 200) {
               // this.$Message.success(res.data.msg)
               this.updateBankCard=res.data.data[0]
              } else {
                //this.$Message.error(res.data.msg)
              }
            }).catch(res => {
             // this.$Message.error('系统异常')
            })
    },
    //获取修改的图片
    getUpdateIcon(data){
      this.updateAccount.icon=data[0].url
    },
	//修改确定
    updateSure1 () {
      /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.axiosbusiness.update(this,{
      ref:'updateCompany',
      url:'/company/update',
      requestObject:'updateCompany',
      loading:'updateLoading1',
    })
 
    },
	//修改确定
    updateSure2 () {
      /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    delete this.updateAccount.role;
    this.axiosbusiness.update(this,{
      ref:'updateAccount',
      url:'/account/updateInfo',
      requestObject:'updateAccount',
      loading:'updateLoading2',
      success:()=>{
          sessionStorage.setItem("account",JSON.stringify(this.updateAccount))
      }
    })
 
    },
	//修改确定
    updateSure3 () {
       /**
     * 增加
     * $this  vue组件
     * p.ref 验证
     * p.url 增加url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.updateBankCard.accountId=JSON.parse(this.$route.params.pathParams).accountId
    console.log(this.updateBankCard)
    if(!this.updateBankCard.bankCardId){
     this.axios({
          method:"post",
          url:'/bankCard/add',
          withCredentials: true,
          data:this.Qs.stringify(this.updateBankCard)
      }).
      then(res => {
        if (res.data.code == 200) {
           this.$Message.success(res.data.msg)
          this.updateBankCard=res.data.data[0]
        } else {
          this.$Message.error(res.data.msg)
        }
      }).catch(res => {
         this.$Message.error('系统异常')
      })
    }
      /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    else{
      this.axiosbusiness.update(this,{
        ref:'updateBankCard',
      url:'/bankCard/update',
      requestObject:'updateBankCard',
      loading:'updateLoading3',
      success:()=>{
        sessionStorage.setItem("account",JSON.stringify(this.updateBankCard))
      }
    })
    }
 
    },
  },
  created () {
    this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  },
  mounted () {

  }
}
</script>
