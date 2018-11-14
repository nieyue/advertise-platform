<!--第三方信息管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <!-- <Button type='primary'  @click='add'>增加第三方信息</Button> -->
    </div>
		 <!--新增 -->
     <Modal v-model="addThirdInfoModel"
           title="新增第三方信息管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addThirdInfo" :model="addThirdInfo" :label-width="100" label-position="right"  :rules="addThirdInfoRules">
        <FormItem prop="wxUuid" label="微信uuid:">
          <Input type="text" v-model="addThirdInfo.wxUuid" placeholder="微信uuid">
          </Input>
        </FormItem>
        <FormItem prop="wechat" label="微信号:">
          <Input type="text" v-model="addThirdInfo.wechat" placeholder="微信号">
          </Input>
        </FormItem>
        <FormItem prop="qq" label="qq号:">
          <Input type="text" v-model="addThirdInfo.qq" placeholder="qq号">
          </Input>
        </FormItem>
        <FormItem prop="microblog" label="微博号:">
          <Input type="text" v-model="addThirdInfo.microblog" placeholder="微博号">
          </Input>
        </FormItem>
        <FormItem prop="alipay" label="支付宝号:">
          <Input type="text" v-model="addThirdInfo.alipay" placeholder="支付宝号">
          </Input>
        </FormItem>
        <FormItem prop="wxOpenid" label="微信openid:">
          <Input type="textarea" v-model="addThirdInfo.wxOpenid" placeholder="微信openid，多个，格式[{subscriptionId:\'1004908098840150018\',openid:\'sdfsdfdsf2fd\'}]">
          </Input>
        </FormItem>
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="addThirdInfo.accountId"></InputNumber>
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
     <Modal v-model="updateThirdInfoModel"
           title="修改第三方信息管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateThirdInfo" :model="updateThirdInfo" :label-width="100" label-position="right"  :rules="updateThirdInfoRules">
        <FormItem prop="wxUuid" label="微信uuid:">
          <Input type="text" v-model="updateThirdInfo.wxUuid" placeholder="微信uuid">
          </Input>
        </FormItem>
        <FormItem prop="wechat" label="微信号:">
          <Input type="text" v-model="updateThirdInfo.wechat" placeholder="微信号">
          </Input>
        </FormItem>
        <FormItem prop="qq" label="qq号:">
          <Input type="text" v-model="updateThirdInfo.qq" placeholder="qq号">
          </Input>
        </FormItem>
        <FormItem prop="microblog" label="微博号:">
          <Input type="text" v-model="updateThirdInfo.microblog" placeholder="微博号">
          </Input>
        </FormItem>
        <FormItem prop="alipay" label="支付宝号:">
          <Input type="text" v-model="updateThirdInfo.alipay" placeholder="支付宝号">
          </Input>
        </FormItem>
        <FormItem prop="wxOpenid" label="微信openid:">
          <Input type="textarea" v-model="updateThirdInfo.wxOpenid" placeholder="微信openid，多个，格式[{subscriptionId:\'1004908098840150018\',openid:\'sdfsdfdsf2fd\'}]">
          </Input>
        </FormItem>
         <!-- <FormItem prop="accountId" label="账户id:">
          <InputNumber  :min="0"  :step="1" :precision='0' v-model="updateThirdInfo.accountId"></InputNumber>
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
      <Table border  :columns='thirdInfoColumns' :data='thirdInfoList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
    
</template>
<script>
export default {
  name: 'ThirdInfo',
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
			addThirdInfoModel:false,
			addLoading:false,
			addThirdInfoRules: {
                wxUuid: [
                    {required: true, message: '微信uuid为必填项', trigger: 'blur'}
                    ]
                },
			addThirdInfo:{
			},
			//修改参数
			updateThirdInfoModel:false,
			updateLoading:false,
			updateThirdInfoRules: {
                wxUuid: [
                    {required: true, message: '微信uuid为必填项', trigger: 'blur'}
                    ]
                },
			updateThirdInfo:{},
      //删除参数
      deleteThirdInfo:{},
	    thirdInfoList: [],
	    thirdInfoColumns: [
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
          title: '第三方信息id',
          key: 'thirdInfoId',
          minWidth:100,
          align:'center'
        },
        {
        	title:'微信uuid',
            key:'wxUuid',
             minWidth:100,
            align:'center'
        },
        {
        	title:'微信号',
            key:'wechat',
             minWidth:100,
            align:'center'
        },
        {
        	title:'qq号',
            key:'qq',
            minWidth:100,
            align:'center'
        },
        {
        	title:'微博号',
            key:'microblog',
            minWidth:100,
            align:'center'
        },
        {
        	title:'支付宝号',
            key:'alipay',
            minWidth:100,
            align:'center'
        },
        {
        	title:'微信openid，多个',
            key:'wxOpenid',
            minWidth:200,
            align:'center'
        },
        {
        	title:'账户id',
            key:'accountId',
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
             // ,varhh2
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
       countUrl:'/thirdInfo/count',
       listUrl:'/thirdInfo/list',
       data:'thirdInfoList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addThirdInfoModel = true
      this.addThirdInfo.accountId=JSON.parse(this.$route.params.pathParams).accountId
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addThirdInfoModel = false
        this.$refs.addThirdInfo.resetFields()
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
      ref:'addThirdInfo',
      url:'/thirdInfo/add',
      requestObject:'addThirdInfo',
      loading:'addLoading',
      showModel:'addThirdInfoModel'
    })
    },
	 update (params) {
      this.updateThirdInfoModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/thirdInfo/load?thirdInfoId='+params.thirdInfoId,
         data:'updateThirdInfo',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateThirdInfoModel = false
        this.$refs.updateThirdInfo.resetFields()
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
      ref:'updateThirdInfo',
      url:'/thirdInfo/update',
      requestObject:'updateThirdInfo',
      loading:'updateLoading',
      showModel:'updateThirdInfoModel'
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
    this.deleteThirdInfo={
      "thirdInfoId":params.thirdInfoId
    };
    this.axiosbusiness.delete(this,{
      url:'/thirdInfo/delete',
      requestObject:'deleteThirdInfo'
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
