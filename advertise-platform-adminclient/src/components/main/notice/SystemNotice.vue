<!--系统通知管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <Button type='error'  @click='add'>增加系统通知</Button>
    </div>
	<!--新增 -->
     <Modal v-model="addNoticeModel"
           title="新增系统通知"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addNotice" :model="addNotice" :label-width="100"  label-position="right"  :rules="addNoticeRules">
        <FormItem prop="title" label="标题:">
          <Input type="text" v-model="addNotice.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="imgAddress" label="封面(上传或者填写):">
            <my-upload :defaultUpload="addNotice.imgAddress" @uploadList="getAddImgAddress"></my-upload> 
            <div>
                <Input type="text" v-model="addNotice.imgAddress" placeholder="封面">
            </Input>
                <img :src="addNotice.imgAddress"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="content" label="内容:">
         <Input type="textarea" v-model="addNotice.content" :autosize="{minRows: 2,maxRows: 5}"  placeholder="内容">
          </Input>
        </FormItem>
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
     <Modal v-model="updateNoticeModel"
           title="修改系统通知"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateNotice" :model="updateNotice" :label-width="100" label-position="right"  :rules="updateNoticeRules">
        <FormItem prop="title" label="标题:">
          <Input type="text" v-model="updateNotice.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="imgAddress" label="封面(上传或者填写):">
            <my-upload :defaultUpload="updateNotice.imgAddress" @uploadList="getUpdateImgAddress"></my-upload> 
            <div>
                <Input type="text" v-model="updateNotice.imgAddress" placeholder="封面">
            </Input>
            <img :src="updateNotice.imgAddress"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="content" label="内容:">
         <Input type="textarea" v-model="updateNotice.content" :autosize="{minRows: 2,maxRows: 5}"  placeholder="内容">
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
      <Table border :columns='noticeColumns' :data='noticeList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'SystemNotice',
  data () {
    return {
        params:{
            region:1,//全局
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
			//增加参数
			addNoticeModel:false,
			addLoading:false,
			addNoticeRules: {
                title: [
                    {required: true, message: '标题为必填项', trigger: 'blur'}
                    ],
                content: [
                    {required: true, message: '内容为必填项', trigger: 'blur'}
                    ],
                imgAddress: [
                    {required: true, message: '封面为必填项', trigger: 'blur'}
                    ]
                },
			addNotice:{
                type:1,//系统消息
                region:1,//范围1全局，2个人
                title:'系统消息',
                imgAddress:'',//默认系统通知图片
			},
			//修改参数
			updateNoticeModel:false,
			updateLoading:false,
			updateNoticeRules: {
                title: [
                    {required: true, message: '标题为必填项', trigger: 'blur'}
                    ],
                content: [
                    {required: true, message: '内容为必填项', trigger: 'blur'}
                    ],
                imgAddress: [
                    {required: true, message: '封面为必填项', trigger: 'blur'}
                    ]
                },
			updateNotice:{
      },
      //删除参数
      deleteNotice:{},
      //列表
      noticeList: [],
	    noticeColumns: [
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
          title: '通知id',
          minWidth:100,
          key: 'noticeId',
          align:'center'
        },
        {
          title:'标题',
          minWidth:100,
        	key:'title',
          align:'center'
        },
        {
          title:'封面',
          minWidth:100,
        	key:'imgAddress',
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.imgAddress
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'内容',
          minWidth:100,
          key:'content',
          align:'center'
        },
        {
          title:'创建时间',
          minWidth:100,
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
          title:'修改时间',
          minWidth:100,
          key:'updateDate',
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          minWidth:200,
          key: 'action',
          align:'center',
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
              }, '编辑');
            var varhh2=  h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  margin: '10px'
                },
                on: {
                  click: () => {
                    this.delete(params.row)
                  }
                }
              }, '删除');
            	var s=h("div","");
			s=h("div",[
              varhh1,
              varhh2
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
       type:1,//系统通知
       region:1,//全局
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getList()
    },
      //获取增加的图片
    getAddImgAddress(data){
      this.addNotice.imgAddress=data[0].url
    },
    //获取修改的图片
    getUpdateImgAddress(data){
      this.updateNotice.imgAddress=data[0].url
    },
  //获取列表
   getList () {
     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     */
    this.params.type=JSON.parse(this.$route.params.pathParams).type
    this.params.region=JSON.parse(this.$route.params.pathParams).region
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/notice/count',
       listUrl:'/notice/list',
       data:'noticeList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addNoticeModel = true
      
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addNoticeModel = false
        this.$refs.addNotice.resetFields()
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
      ref:'addNotice',
      url:'/notice/add',
      requestObject:'addNotice',
      loading:'addLoading',
      showModel:'addNoticeModel'
    })
    },
	 update (params) {
      this.updateNoticeModel = true
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/notice/load?noticeId='+params.noticeId,
         data:'updateNotice'
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateNoticeModel = false
        this.$refs.updateNotice.resetFields()
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
      ref:'updateNotice',
      url:'/notice/update',
      requestObject:'updateNotice',
      loading:'updateLoading',
      showModel:'updateNoticeModel'
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
    this.deleteNotice={
      "noticeId":params.noticeId
    };
    this.axiosbusiness.delete(this,{
      url:'/notice/delete',
      requestObject:'deleteNotice'
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
  }
}
</script>
