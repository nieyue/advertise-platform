<!--财务记录管理 -->
<template>
    <div class="body-wrap">
      <div style="margin:2rem;">
        <Button v-for="t in financeTypes" @click="changeFinanceType(t)" :type="t.typeValue" :key="t.type" style="margin:5px;font-size:1.6rem;" size="large">
          {{t.value}}
        </Button>
      </div>
      <!--充值-->
      <div v-if="financeTypes[0].typeValue=='primary'" style="font-size:1.2rem;margin-left:2rem;">
            <p style="margin:1rem;">
              <span>开户名称：</span>
              <span v-text="config.platformName"></span>
            </p>
            <p style="margin:1rem;">
              <span>开户银行：</span>
              <span v-text="config.bankName"></span>
            </p>
            <p style="margin:1rem;">
              <span>银行账户：</span>
              <span v-text="config.bankAccount"></span>
            </p>
            <p style="margin:1rem;">
              <span>联系电话：</span>
              <span v-text="config.servicePhone"></span>
            </p>
            <p style="margin:1rem;">
              <span>联系qq：</span>
              <span v-text="config.serviceQq"></span>
            </p>
            <p style="color:red;margin:1rem;">注意：充值前请联系客服（工作时间9:00-24:00）</p>
      </div>
      <!--充值end-->

      <!--充值记录-->
      <Table border v-if="financeTypes[1].typeValue=='primary'"  :columns='financeRecordColumns1' :data='financeRecordList' ref='table' size="small"></Table>
      <!--充值记录end-->
      <!--消费记录-->
      <Table border v-if="financeTypes[2].typeValue=='primary'"  :columns='financeRecordColumns2' :data='financeRecordList' ref='table' size="small"></Table>
      <!--消费记录end-->
      <!--退款记录-->
      <Table border v-if="financeTypes[3].typeValue=='primary'"  :columns='financeRecordColumns3' :data='financeRecordList' ref='table' size="small"></Table>
      <!--退款记录end-->
      <!--冻结记录-->
      <Table border v-if="financeTypes[4].typeValue=='primary'"  :columns='financeRecordColumns4' :data='financeRecordList' ref='table' size="small"></Table>
      <!--冻结记录end-->
      <!--解冻记录-->
      <Table border v-if="financeTypes[5].typeValue=='primary'"  :columns='financeRecordColumns5' :data='financeRecordList' ref='table' size="small"></Table>
        <div v-if="financeTypes[0].typeValue!='primary'" style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
      <!--解冻记录end-->
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
            type:-1,value:'充值',typeValue:'primary'
          },
          {
            type:1,value:'充值记录',typeValue:'default'
          },
          {
            type:2,value:'消费记录',typeValue:'default'
          },
          {
            type:3,value:'退款记录',typeValue:'default'
          },
          {
            type:4,value:'冻结记录',typeValue:'default'
          },
          {
            type:5,value:'解冻记录',typeValue:'default'
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
      //充值记录
      financeRecordColumns1: [
        {
          title: '财务记录id',
          key: 'financeRecordId',
          align:'center'
        },
        {
        	title:'交易单号',
            key:'transactionNumber',
            align:'center'
        },
        {
        	title:'金额',
            key:'money',
            align:'center'
        },
        {
          title:'充值时间',
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
       //消费记录
	    financeRecordColumns2: [
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
        	title:'媒体名称',
            key:'mediaName',
            align:'center'
        },
        {
        	title:'金额',
            key:'money',
            align:'center'
        },
        {
          title:'消费时间',
          key:'createDate',
          sortable: true,
          align:'center'
        }
      ],
      //退款记录
	    financeRecordColumns3: [
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
          title:'退款时间',
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
      //冻结记录
	    financeRecordColumns4: [
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
          title:'冻结时间',
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
      //解冻记录
	    financeRecordColumns5: [
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
        	title:'媒体名称',
            key:'mediaName',
            align:'center'
        },
        {
        	title:'金额',
            key:'money',
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
        {
          title:'解冻时间',
          key:'createDate',
          sortable: true,
          align:'center'
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
