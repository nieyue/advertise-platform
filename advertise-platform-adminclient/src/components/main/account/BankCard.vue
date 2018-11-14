<!--银行卡管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <Button type='primary'  @click='add'>增加银行卡</Button>
    </div>
		 <!--新增 -->
     <Modal v-model="addBankCardModel"
           title="新增银行卡管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addBankCard" :model="addBankCard" :label-width="100" label-position="right"  :rules="addBankCardRules">
        <FormItem prop="realname" label="姓名:">
          <Input type="text" v-model="addBankCard.realname" placeholder="姓名">
          </Input>
        </FormItem>
        <FormItem prop="identity" label="身份证:">
          <Input type="text" v-model="addBankCard.identity" placeholder="身份证">
          </Input>
        </FormItem>
        <FormItem prop="bankName" label="银行名:">
          <Input type="text" v-model="addBankCard.bankName" placeholder="银行名">
          </Input>
        </FormItem>
        <FormItem prop="number" label="银行卡卡号:">
          <Input type="text" v-model="addBankCard.number" placeholder="银行卡卡号">
          </Input>
        </FormItem>
        <FormItem prop="phone" label="预留手机号:">
          <Input type="text" v-model="addBankCard.phone" placeholder="预留手机号">
          </Input>
        </FormItem>
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="addBankCard.accountId"></InputNumber>
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
     <Modal v-model="updateBankCardModel"
           title="修改银行卡管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateBankCard" :model="updateBankCard" :label-width="100" label-position="right"  :rules="updateBankCardRules">
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
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="updateBankCard.accountId"></InputNumber>
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
      <Table border  :columns='bankCardColumns' :data='bankCardList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
    
</template>
<script>
export default {
  name: 'BankCard',
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
			addBankCardModel:false,
			addLoading:false,
			addBankCardRules: {
                realname: [
                    {required: true, message: '姓名为必填项', trigger: 'blur'}
                    ]
                },
			addBankCard:{
			},
			//修改参数
			updateBankCardModel:false,
			updateLoading:false,
			updateBankCardRules: {
                realname: [
                    {required: true, message: '姓名为必填项', trigger: 'blur'}
                    ]
                },
			updateBankCard:{},
      //删除参数
      deleteBankCard:{},
	    bankCardList: [],
	    bankCardColumns: [
        {
          title: '序号',
          minWidth:100,
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '银行卡id',
          key: 'bankCardId',
          minWidth:100,
          align:'center'
        },
        {
        	title:'姓名',
            key:'realname',
             minWidth:100,
            align:'center'
        },
        {
        	title:'身份证',
            key:'identity',
             minWidth:100,
            align:'center'
        },
        {
        	title:'银行名',
            key:'bankName',
            minWidth:100,
            align:'center'
        },
        {
        	title:'银行卡卡号',
            key:'number',
            minWidth:100,
            align:'center'
        },
        {
        	title:'预留手机号',
            key:'phone',
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
              ,varhh2
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
       countUrl:'/bankCard/count',
       listUrl:'/bankCard/list',
       data:'bankCardList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addBankCardModel = true
      this.addBankCard.accountId=JSON.parse(this.$route.params.pathParams).accountId
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addBankCardModel = false
        this.$refs.addBankCard.resetFields()
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
      ref:'addBankCard',
      url:'/bankCard/add',
      requestObject:'addBankCard',
      loading:'addLoading',
      showModel:'addBankCardModel'
    })
    },
	 update (params) {
      this.updateBankCardModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/bankCard/load?bankCardId='+params.bankCardId,
         data:'updateBankCard',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateBankCardModel = false
        this.$refs.updateBankCard.resetFields()
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
      ref:'updateBankCard',
      url:'/bankCard/update',
      requestObject:'updateBankCard',
      loading:'updateLoading',
      showModel:'updateBankCardModel'
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
    this.deleteBankCard={
      "bankCardId":params.bankCardId
    };
    this.axiosbusiness.delete(this,{
      url:'/bankCard/delete',
      requestObject:'deleteBankCard'
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
