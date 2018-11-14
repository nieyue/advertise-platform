<!--角色管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <!-- <Button type='primary'  @click='add'>增加角色</Button> -->
      <!-- <Button type='error'  @click='deleteBatch'>批量删除</Button> -->
    </div>
		 <!--新增 -->
     <Modal v-model="addRoleModel"
           title="新增角色管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addRole" :model="addRole" :label-width="100" label-position="right"  :rules="addRoleRules">
        <FormItem prop="name" label="角色名:">
          <Input type="text" v-model="addRole.name" placeholder="角色名">
          </Input>
        </FormItem>
        <FormItem prop="duty" label="角色职责:">
          <Input type="text" v-model="addRole.duty" placeholder="角色职责">
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
     <Modal v-model="updateRoleModel"
           title="修改角色管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateRole" :model="updateRole" :label-width="100" label-position="right"  :rules="updateRoleRules">
        <FormItem prop="name" label="角色名:">
          <Input type="text" v-model="updateRole.name" placeholder="角色名">
          </Input>
        </FormItem>
        <FormItem prop="duty" label="角色职责:">
          <Input type="text" v-model="updateRole.duty" placeholder="角色职责">
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
      <Table border :columns='roleColumns' :data='roleList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;'  :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'Role',
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
			addRoleModel:false,
			addLoading:false,
			addRoleRules: {
                name: [
                    {required: true, message: '角色名为必填项', trigger: 'blur'}
                    ],
                duty: [
                    {required: true, message: '角色职责为必填项', trigger: 'blur'}
                    ]
                },
			addRole:{
			},
			//修改参数
			updateRoleModel:false,
			updateLoading:false,
			updateRoleRules: {
                name: [
                    {required: true, message: '角色名为必填项', trigger: 'blur'}
                    ],
                duty: [
                    {required: true, message: '角色职责为必填项', trigger: 'blur'}
                    ]
                },
			updateRole:{
    		 "roleId":1,
    		 "name":""
      },
      //删除参数
      deleteRole:{},
	    roleList: [],
	    roleColumns: [
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
          title: '角色id',
          minWidth:100,
          key: 'roleId',
          align:'center'
        },
        {
          title:'角色名称',
          minWidth:100,
        	key:'name',
          align:'center'
        },
        {
          title:'角色职责',
          minWidth:100,
        	key:'duty',
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
            var varhh0=  h('Button', {
                props: {
                  type: 'success',
                  size: 'small'
                },
                style: {
                  marginLeft: '10px'
                },
                on: {
                  click: () => {
                    this.$router.push('/main/rolePermission/'+params.row.roleId);
                  }
                }
              }, '角色权限');
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
              varhh0,
              varhh1,
              //varhh2
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
       accountId:JSON.parse(this.$route.params.pathParams).accountId
     })
     //console.log(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams)))
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
       countUrl:'/role/count',
       listUrl:'/role/list',
       data:'roleList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addRoleModel = true
      this.addRole.name = params.name
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addRoleModel = false
        this.$refs.addRole.resetFields()
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
      ref:'addRole',
      url:'/role/add',
      requestObject:'addRole',
      loading:'addLoading',
      showModel:'addRoleModel'
    })
    },
	 update (params) {
      this.updateRoleModel = true
      //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/role/load?roleId='+params.roleId,
         data:'updateRole',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateRoleModel = false
        this.$refs.updateRole.resetFields()
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
      ref:'updateRole',
      url:'/role/update',
      requestObject:'updateRole',
      loading:'updateLoading',
      showModel:'updateRoleModel'
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
    this.deleteRole={
      "roleId":params.roleId
    };
    this.axiosbusiness.delete(this,{
      url:'/role/delete',
      requestObject:'deleteRole'
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
    //this.getList();
  },
  mounted () {

  }
}
</script>
