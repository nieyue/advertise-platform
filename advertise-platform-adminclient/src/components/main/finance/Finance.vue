<!--财务管理 -->
<template>
    <div class="body-wrap">

		 <!--修改 -->
     <Modal v-model="updateFinanceModel"
           title="修改财务管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateFinance" :model="updateFinance" :label-width="100" label-position="right"  :rules="updateFinanceRules">
       <FormItem prop="password" label="安全密码:">
          <Input type="text" v-model="updateFinance.password" placeholder="安全密码">
          </Input>
        </FormItem>
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
		 <!--充值 -->
     <Modal v-model="update2FinanceModel"
           title="修改财务管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="update2Finance" :model="update2Finance" :label-width="100" label-position="right"  :rules="update2FinanceRules">
       <FormItem prop="money" label="充值金额:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="update2Finance.money"
            placeholder="充值金额">
          </InputNumber >
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button  @click='update2Cancel'>取消</Button>
        <Button type='primary' :loading='update2Loading'>
          <span v-if="!update2Loading" @click='update2Sure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--充值end -->
      <Table border  :columns='financeColumns' :data='financeList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage"  :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
    
</template>
<script>
export default {
  name: 'Finance',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        //修改参数
        updateFinanceModel:false,
        updateLoading:false,
        updateFinanceRules: {
            password: [
                {required: true, message: '安全密码为必填项', trigger: 'blur'}
                ],
            },
		updateFinance:{},
        //充值参数
        update2FinanceModel:false,
        update2Loading:false,
        update2FinanceRules: {
            password: [
                {required: true, message: '安全密码为必填项', trigger: 'blur'}
                ],
            },
		update2Finance:{},
	    financeList: [],
	    financeColumns: [
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
          title: '财务id',
          key: 'financeId',
          minWidth:100,
          align:'center'
        },
         {
        	title:'账户id',
            key:'accountId',
            minWidth:100,
            align:'center'
        },
        {
        	title:'安全密码',
            key:'password',
             minWidth:100,
            align:'center',
          render: (h, params) => {
            return h('span','*******')
          }
        },
        {
        	title:'余额',
            key:'money',
             minWidth:100,
            align:'center'
        },
        {
        	title:'充值金额',
            key:'recharge',
            minWidth:100,
            align:'center'
        },
        {
        	title:'消费金额',
            key:'consume',
            minWidth:100,
            align:'center'
        },
        {
        	title:'收入金额',
            key:'income',
            minWidth:100,
            align:'center'
        },
        {
        	title:'提现金额',
            key:'withdrawals',
            minWidth:100,
            align:'center'
        },
        {
        	title:'退款金额',
            key:'refund',
            minWidth:100,
            align:'center'
        },
        {
        	title:'冻结金额',
            key:'frozen',
            minWidth:100,
            align:'center'
        },
        {
        	title:'赠送金钱',
            key:'baseProfit',
            minWidth:100,
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
                  margin: '10px'
                },
                on: {
                  click: () => {
                    this.update(params.row)
                  }
                }
              }, '修改安全密码');
            var varhh2=  h('Button', {
                props: {
                  type: 'error',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: '10px'
                },
                on: {
                  click: () => {
                    this.update2(params.row)
                  }
                }
              }, '充值');
			let s=h("div",[
              varhh1
            ]);
            if(this.business.getIsSuperAdmin()){
              s=h("div",[
              varhh1,
              varhh2
            ]);
            }
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
       countUrl:'/finance/count',
       listUrl:'/finance/list',
       data:'financeList'
     },this.params)
    },
	 update (params) {
      this.updateFinanceModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/finance/load?financeId='+params.financeId,
         data:'updateFinance',
         success:()=>{
             this.updateFinance.password='';
         }
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateFinanceModel = false
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
      ref:'updateFinance',
      url:'/finance/updatePasswordByFinanceId',
      requestObject:'updateFinance',
      loading:'updateLoading',
      showModel:'updateFinanceModel'
    })
 
    },
    update2 (params) {
      this.update2FinanceModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/finance/load?financeId='+params.financeId,
         data:'update2Finance',
         success:()=>{
             this.update2Finance.money=0;
         }
       })
    },
		//修改取消
		 update2Cancel () {
      if (!this.update2Loading) {
        this.update2FinanceModel = false
      }
    },
		//修改确定
    update2Sure () {
      /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.ro={
      accountId:this.update2Finance.accountId,
      method:7,
      money:this.update2Finance.money
    }
    this.axiosbusiness.update(this,{
      ref:'update2Finance',
      url:'/finance/recharge',
      requestObject:'ro',
      loading:'update2Loading',
      showModel:'update2FinanceModel'
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
