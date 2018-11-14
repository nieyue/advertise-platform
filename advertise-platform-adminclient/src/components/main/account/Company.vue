<!--公司管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
     <!--  <Button type='primary'  @click='add'>增加公司</Button> -->
    </div>
		 <!--新增 -->
     <Modal v-model="addCompanyModel"
           title="新增公司管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addCompany" :model="addCompany" :label-width="100" label-position="right"  :rules="addCompanyRules">
        <FormItem prop="name" label="名称:">
          <Input type="text" v-model="addCompany.name" placeholder="名称">
          </Input>
        </FormItem>
        <FormItem prop="industry" label="行业:">
          <Input type="text" v-model="addCompany.industry" placeholder="行业">
          </Input>
        </FormItem>
        <FormItem prop="address" label="公司地址:">
          <Input type="text" v-model="addCompany.address" placeholder="公司地址">
          </Input>
        </FormItem>
        <FormItem prop="website" label="公司网址:">
          <Input type="text" v-model="addCompany.website" placeholder="公司网址">
          </Input>
        </FormItem>
        <FormItem prop="description" label="公司描述:">
          <Input type="textarea" v-model="addCompany.description" placeholder="公司描述">
          </Input>
        </FormItem>
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="addCompany.accountId"></InputNumber>
        </FormItem> -->
      </Form>
      <div slot='footer'>
        <Button  @click='addCancel'>取消</Button>
        <Button type='primary' :loading='addLoading'>
          <span v-if="!addLoading" @click='addSure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--新增end -->
		 <!--修改 -->
     <Modal v-model="updateCompanyModel"
           title="修改公司管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateCompany" :model="updateCompany"  :label-width="100" label-position="right"  :rules="updateCompanyRules">
        <FormItem prop="name" label="名称:">
          <Input type="text" v-model="updateCompany.name" placeholder="名称">
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
          <Input type="textarea" v-model="updateCompany.description" placeholder="公司描述">
          </Input>
        </FormItem>
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="updateCompany.accountId"></InputNumber>
        </FormItem> -->
      </Form>
      <div slot='footer'>
        <Button  @click='updateCancel'>取消</Button>
        <Button type='primary' :loading='updateLoading'>
          <span v-if="!updateLoading" @click='updateSure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--修改end -->
      <Table border  :columns='companyColumns' :data='companyList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
    
</template>
<script>
export default {
  name: 'Company',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
			//增加参数
			addCompanyModel:false,
			addLoading:false,
			addCompanyRules: {
                name: [
                    {required: true, message: '名称为必填项', trigger: 'blur'}
                    ]
                },
			addCompany:{
			},
			//修改参数
			updateCompanyModel:false,
			updateLoading:false,
			updateCompanyRules: {
                name: [
                    {required: true, message: '名称为必填项', trigger: 'blur'}
                    ]
                },
			updateCompany:{},
      //删除参数
      deleteCompany:{},
	    companyList: [],
	    companyColumns: [
        {
          title: '序号',
          width:80,
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '公司id',
          key: 'companyId',
          minWidth:100,
          align:'center'
        },
        {
        	title:'名称',
            key:'name',
             minWidth:100,
            align:'center'
        },
        {
        	title:'行业',
            key:'industry',
             minWidth:100,
            align:'center'
        },
        {
        	title:'公司地址',
            key:'address',
            minWidth:100,
            align:'center'
        },
        {
        	title:'公司网址',
            key:'website',
            minWidth:100,
            align:'center'
        },
        {
        	title:'公司描述',
            key:'description',
            minWidth:200,
            align:'center'
        },
        {
        	title:'账户id',
            key:'accountId',
            minWidth:200,
            align:'center'
        },
        {
          title:'创建时间',
          key:'createDate',
          minWidth:100,
          sortable: true,
          align:'center'
        },
        {
          title:'修改时间',
          key:'updateDate',
          minWidth:100,
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          key: 'action',
          minWidth:200,
          align:'center',
          fixed: 'right',
          render: (h, params) => {
            var varhh1=  h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginLeft: '10px'
                },
                on: {
                  click: () => {
                    this.update(params.row)
                  }
                }
              }, '编辑');
            var varhh2=  h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  marginLeft: '10px'
                },
                on: {
                  click: () => {
                    this.delete(params.row)
                  }
                }
              }, '删除');
            	var s=h("div","");
			s=h("div",[
              varhh1
              //,varhh2
            ]);
            return s;
          }
        }
      ],
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
      this.getList()
    },
  //获取列表
   getList () {
     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.list 返回列表
     */
     this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/company/count',
       listUrl:'/company/list',
       data:'companyList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addCompanyModel = true
      this.addCompany.accountId=JSON.parse(this.$route.params.pathParams).accountId
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addCompanyModel = false
        this.$refs.addCompany.resetFields()
      }
    },
		//增加确定
    addSure () {
       /**
     * 增加
     * $this  vue组件
     * p.ref 验证
     * p.url 增加url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.axiosbusiness.add(this,{
      ref:'addCompany',
      url:'/company/add',
      requestObject:'addCompany',
      loading:'addLoading',
      showModel:'addCompanyModel'
    })
    },
	 update (params) {
      this.updateCompanyModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/company/load?companyId='+params.companyId,
         data:'updateCompany',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateCompanyModel = false
        this.$refs.updateCompany.resetFields()
      }
    },
		//修改确定
    updateSure () {
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
      loading:'updateLoading',
      showModel:'updateCompanyModel'
    })
 
    },
    //删除
    delete(params){
    /**
     * 删除
     * $this  vue组件
     * p.url 修改url
     * p.requestObject 请求参数对象
     */
    this.deleteCompany={
      "companyId":params.companyId
    };
    this.axiosbusiness.delete(this,{
      url:'/company/delete',
      requestObject:'deleteCompany'
    })
    }
  },
    watch: {
    //当前页面参数修改动态启动
      $route (to,from){
        this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
      }
    }, 
  created () {
     this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  },
  mounted () {

  }
}
</script>
