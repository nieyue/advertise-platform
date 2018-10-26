<!--媒体类型管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <Button type='primary'  @click='add'>增加媒体类型</Button>
    </div>
		 <!--新增 -->
     <Modal v-model="addMediaCateModel"
           title="新增媒体类型管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addMediaCate" :model="addMediaCate" :label-width="100" label-position="right"  :rules="addMediaCateRules">
        <FormItem prop="name" label="分类名称:">
          <Input type="text" v-model="addMediaCate.name" placeholder="分类名称">
          </Input>
        </FormItem>
        <FormItem prop="summary" label="描述:">
          <Input type="textarea" v-model="addMediaCate.summary" placeholder="描述">
          </Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button  @click='addCancel'>取消</Button>
        <Button type='primary' :loading='addLoading'>
          <span v-if="!addLoading" @click='addSure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--新增end -->
		 <!--修改 -->
     <Modal v-model="updateMediaCateModel"
           title="修改媒体类型管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateMediaCate" :model="updateMediaCate" :label-width="100" label-position="right"  :rules="updateMediaCateRules">
        <FormItem prop="name" label="分类名称:">
          <Input type="text" v-model="updateMediaCate.name" placeholder="分类名称">
          </Input>
        </FormItem>
        <FormItem prop="summary" label="描述:">
          <Input type="textarea" v-model="updateMediaCate.summary" placeholder="描述">
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
      <Table border :columns='mediaCateColumns' :data='mediaCateList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'MediaCate',
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
			addMediaCateModel:false,
			addLoading:false,
			addMediaCateRules: {
                name: [
                    {required: true, message: '分类名称为必填项', trigger: 'blur'}
                    ]
                },
			addMediaCate:{
    		   "name":""
			},
			//修改参数
			updateMediaCateModel:false,
			updateLoading:false,
			updateMediaCateRules: {
                name: [
                    {required: true, message: '分类名称为必填项', trigger: 'blur'}
                    ]
                },
			updateMediaCate:{
    		 "mediaCateId":1,
    		 "name":""
      },
      //删除参数
      deleteMediaCate:{},
	    mediaCateList: [],
	    mediaCateColumns: [
        {
          title: '序号',
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '媒体类型id',
          key: 'mediaCateId',
          align:'center'
        },
        {
        	title:'媒体类型名称',
        	key:'name',
          align:'center'
        },
        {
        	title:'媒体类型描述',
        	key:'summary',
          align:'center'
        },
        {
        	title:'修改时间',
          key:'updateDate',
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          key: 'action',
          align:'center',
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
       currentPage:currentPage
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
     * p.data 返回列表
     */
     this.axiosbusiness.getList(this,{
       countUrl:'/mediaCate/count',
       listUrl:'/mediaCate/list',
       data:'mediaCateList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addMediaCateModel = true
    },
		//增加取消
		 addCancel () {
       if (!this.addLoading) {
         this.addMediaCateModel = false
        this.$refs.addMediaCate.resetFields()
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
      ref:'addMediaCate',
      url:'/mediaCate/add',
      requestObject:'addMediaCate',
      loading:'addLoading',
      showModel:'addMediaCateModel'
    })
    },
	 update (params) {
      this.updateMediaCateModel = true
      this.updateMediaCate.mediaCateId = params.mediaCateId
      this.updateMediaCate.name = params.name
      this.updateMediaCate.summary = params.summary
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateMediaCateModel = false
        this.$refs.updateMediaCate.resetFields()
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
      ref:'updateMediaCate',
      url:'/mediaCate/update',
      requestObject:'updateMediaCate',
      loading:'updateLoading',
      showModel:'updateMediaCateModel'
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
    this.deleteMediaCate={
      "mediaCateId":params.mediaCateId
    };
    this.axiosbusiness.delete(this,{
      url:'/mediaCate/delete',
      requestObject:'deleteMediaCate'
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
