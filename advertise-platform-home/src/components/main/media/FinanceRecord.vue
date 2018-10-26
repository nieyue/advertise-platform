<!--财务记录管理 -->
<template>
    <div class="body-wrap">
      <div style="margin:2rem;">
        <Button v-for="t in financeTypes" @click="changeFinanceType(t)" :type="t.typeValue" :key="t.type" style="margin:5px;font-size:1.6rem;" size="large">
          {{t.value}}
        </Button>
        <Button v-if="financeTypes[1].typeValue=='primary'" type="error" size="large" style="float:right;margin:20px;">提现</Button>
        <InputNumber v-if="financeTypes[1].typeValue=='primary'"   style="float:right;margin:20px;width:200px;"  :max="10000000000" :min="0" :value="0" :step="0.01" placeholder="金额"></InputNumber>
      </div>
      <!--收入明细-->
      <Table border v-if="financeTypes[0].typeValue=='primary'"  :columns='financeRecordColumns6' :data='financeRecordList' ref='table' size="small"></Table>
      <!--收入明细end-->
      <!--提现-->
      <Table border v-if="financeTypes[1].typeValue=='primary'"  :columns='financeRecordColumns7' :data='financeRecordList' ref='table' size="small"></Table>
        <div  style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
      <!--提现end-->
    </div>
    
</template>
<script>
export default {
  name: 'FinanceRecord',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        config:JSON.parse(sessionStorage.getItem("config")),
        //财务类型
        financeTypes:[
          {
            type:6,value:'收入明细',typeValue:'primary'
          },
          {
            type:7,value:'提现',typeValue:'default'
          },
        ],

      //支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额
      methodList:[
        {id:1,value:'支付宝'},
        {id:2,value:'微信'},
        {id:3,value:'百度钱包'},
        {id:4,value:'Paypal'},
        {id:5,value:'网银'},
        {id:6,value:'ios内购'},
        {id:7,value:'余额'},
      ],
      /**
       *类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现
       * 
       */
      typeList:[
        {id:1,value:'充值'},
        {id:2,value:'消费'},
        {id:3,value:'退款'},
        {id:4,value:'冻结'},
        {id:5,value:'解冻'},
        {id:6,value:'收入'},
        {id:7,value:'提现'},
      ],
      //状态默认1待处理，2成功，3已拒绝
      statusList:[
        {id:1,value:'待处理'},
        {id:2,value:'成功'},
        {id:3,value:'已拒绝'}
      ],
      financeRecordList: [],
       //收入明细
	    financeRecordColumns6: [
        {
          title: '财务记录id',
          key: 'financeRecordId',
          align:'center'
        },
         {
        	title:'广告id',
            key:'advertiseId',
            align:'center'
        },
        {
        	title:'广告名称',
            key:'advertiseName',
            align:'center'
        },
        {
        	title:'金额',
            key:'money',
            align:'center'
        },
        {
        	title:'平台服务费',
            key:'brokerage',
            align:'center'
        },
        {
        	title:'实际收入',
            key:'realMoney',
            align:'center'
        },
        {
          title:'记录时间',
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
          title:'状态',
            key:'status',
            align:'center',
          render: (h, params) => {
            let statusvalue="";
            this.statusList.forEach(element => {
              if(element.id==params.row.status){
                statusvalue=element.value;
              }
            });
              return  h('span',statusvalue);
          }
        },
      ],
      //提现
	    financeRecordColumns7: [
        {
          title: '财务记录id',
          key: 'financeRecordId',
          align:'center'
        },
        {
        	title:'流水号',
            key:'transactionNumber',
            align:'center'
        },
        {
        	title:'提现金额',
            key:'money',
            align:'center'
        },
        {
        	title:'税金',
            key:'tax',
            align:'center'
        },
        {
        	title:'实际打款金额',
            key:'realMoney',
            align:'center'
        },
        {
          title:'提现时间',
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
          title:'打款时间',
          key:'updateDate',
          sortable: true,
          align:'center'
        },
        {
          title:'状态',
            key:'status',
            align:'center',
          render: (h, params) => {
            let statusvalue="";
            this.statusList.forEach(element => {
              if(element.id==params.row.status){
                statusvalue=element.value;
              }
            });
              return  h('span',statusvalue);
          }
        },
      ],
    }
  },
  methods: {
    //分页点击
    selectPage (currentPage,type) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       type:type,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getList()
    },
    /**
     * 点击改变按钮
     */
    changeFinanceType(t){
      this.financeTypes.forEach(e=>{
        e.typeValue='default';
      })
      t.typeValue="primary";
      if(t.type>=1){
        this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage,t.type)
      }
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
    this.params.type=JSON.parse(this.$route.params.pathParams).type
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/financeRecord/count',
       listUrl:'/financeRecord/list',
       data:'financeRecordList'
     },this.params)
    }
  },
   watch: {
    }, 
  created () {
    //this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  },
  mounted () {

  }
}
</script>
