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
        <Button type='primary' :loading='updateLoading1' size="large" style="width:70%;margin-left:30%;">
          <span v-if="!updateLoading1" @click='updateSure1' >保存</span>
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
        <Button type='primary' :loading='updateLoading2' size="large" style="width:70%;margin-left:30%;">
          <span v-if="!updateLoading2" @click='updateSure2'>保存</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
</div>
    <!--个人修改end -->
    </div>
</template>
<script>
export default {
  name: 'Account',
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
  },
  created () {
    this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  },
  mounted () {

  }
}
</script>
