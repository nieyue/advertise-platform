<!--权限管理 -->
<template>
    <div class="body-wrap">
    <Alert type="success">
    <p>权限类型:</p>
    <p>开放,（代表所有账户都能访问此接口数据，包括未授权、未登录等；即是开放。）</p>
    <p>鉴权,（代表需要登录或者授权，且拥有此权限的账户才能访问此接口数据）</p>
    </Alert>
    <div class="body-btn-wrap">
        <Button type='primary'  @click='add'>增加权限</Button>
        <Button type='error'  @click='god'>一键更新到最新</Button>
        <div class="search-wrap">
            <Select v-model="params.type" transfer class="search-wrap-input"  placeholder="权限类型，全部">
                <Option v-for="item in typeParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
            </Select>
            <Input v-model="params.managerName" class="search-wrap-input" placeholder="权限管理名，模糊查询"></Input>
            <Input v-model="params.name" class="search-wrap-input" placeholder="权限名，模糊查询"></Input>
            <Input v-model="params.route" class="search-wrap-input" placeholder="路由"></Input>
            <Button @click="search" type="info"  >查询</Button>
        </div>
    </div>
		 <!--新增 -->
     <Modal v-model="addPermissionModel"
           title="新增权限管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addPermission" :model="addPermission" :label-width="100" label-position="right"  :rules="addPermissionRules">
        <FormItem prop="type" label="权限类型:">
          <Select v-model="addPermission.type" transfer size="large" style="width:100px">
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="managerName" label="权限管理名:">
          <Input type="text" v-model="addPermission.managerName" placeholder="权限管理名">
          </Input>
        </FormItem>
        <FormItem prop="name" label="权限名:">
          <Input type="text" v-model="addPermission.name" placeholder="权限名">
          </Input>
        </FormItem>
        <FormItem prop="route" label="路由:">
          <Input type="text" v-model="addPermission.route" placeholder="路由">
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
     <Modal v-model="updatePermissionModel"
           title="修改权限管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updatePermission" :model="updatePermission" :label-width="100" label-position="right"  :rules="updatePermissionRules">
         <FormItem prop="type" label="权限类型:">
          <Select v-model="updatePermission.type" transfer size="large" style="width:100px">
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="managerName" label="权限管理名:">
          <Input type="text" v-model="updatePermission.managerName" placeholder="权限管理名">
          </Input>
        </FormItem>
        <FormItem prop="name" label="权限名:">
          <Input type="text" v-model="updatePermission.name" placeholder="权限名">
          </Input>
        </FormItem>
        <FormItem prop="route" label="路由:">
          <Input type="text" v-model="updatePermission.route" placeholder="路由">
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
      <Table border  :columns='permissionColumns' :data='permissionList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
    
</template>
<script>
export default {
  name: 'Permission',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
         //权限类型，默认0开放，1鉴权
          typeList:[
              {id:0,value:'开放'},
          {id:1,value:'鉴权'}
          ],
         //权限类型，默认0开放，1鉴权
          typeParamsList:[
              {id:'',value:'全部'},
          {id:0,value:'开放'},
          {id:1,value:'鉴权'}
          ],
			//增加参数
			addPermissionModel:false,
			addLoading:false,
			addPermissionRules: {
                type: [
                    {type:"number",required: true, message: '权限类型为必选项', trigger: 'change'}
                    ],
                managerName: [
                    {required: true, message: '权限管理名为必填项', trigger: 'blur'}
                    ],
                name: [
                    {required: true, message: '权限名为必填项', trigger: 'blur'}
                    ],
                route: [
                    {required: true, message: '路由为必填项', trigger: 'blur'}
                    ]
                },
			addPermission:{
                },
			//修改参数
			updatePermissionModel:false,
			updateLoading:false,
			updatePermissionRules: {
                type: [
                    {type:"number",required: true, message: '权限类型为必选项', trigger: 'change'}
                    ],
                managerName: [
                    {required: true, message: '权限管理名为必填项', trigger: 'blur'}
                    ],
                name: [
                    {required: true, message: '权限名为必填项', trigger: 'blur'}
                    ],
                route: [
                    {required: true, message: '路由为必填项', trigger: 'blur'}
                    ]
                },
			updatePermission:{},
      //删除参数
      deletePermission:{},
	    permissionList: [],
            permissionListTree:[],
	    permissionColumns: [
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
              title: '权限id',
              minWidth:100,
              key: 'permissionId',
              align:'center'
            },
           {
            title:'权限类型',
            minWidth:100,
            key:'type',
            align:'center',
                render: (h, params) => {
                let typevalue="";
            this.typeList.forEach(element => {
              if(element.id==params.row.type){
                typevalue=element.value;
              }
            });
             return  h('span',typevalue);
                }
        },
        {
          title:'权限管理名',
          minWidth:100,
            key:'managerName',
            align:'center'
        },
        {
          title:'权限名',
          minWidth:100,
            key:'name',
            align:'center'
        },
        {
          title:'路由',
          minWidth:100,
            key:'route',
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
    //一键更新到最新
    god(){
      this.$Spin.show();
      this.axios({
          method:"post",
          url:'/permission/init',
          withCredentials: true,  
          })
        .then((res)=>{
          console.log(res.data);
            this.$Spin.hide();
            if(res.data.code==200){
            this.getList();
            this.$Message.success("成功")
            }else{
            this.$Message.error(res.data.msg)
            }


        }).catch((error)=>{
            console.log(error);
            this.$Message.error("错误")
         });
    },
      //查询
    search(){
      this.getList()
    },
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
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
       countUrl:'/permission/count',
       listUrl:'/permission/list',
       data:'permissionList',
       success:()=>{
           this.addPermission={
               type:1
           }
       }
     },this.params)
    },
  //增加
	 add (params) {
      this.addPermissionModel = true
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addPermissionModel = false
        this.$refs.addPermission.resetFields()
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
      ref:'addPermission',
      url:'/permission/add',
      requestObject:'addPermission',
      loading:'addLoading',
      showModel:'addPermissionModel'
    })
    },
	 update (params) {
      this.updatePermissionModel = true
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/permission/load?permissionId='+params.permissionId,
         data:'updatePermission',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updatePermissionModel = false
        this.$refs.updatePermission.resetFields()
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
      ref:'updatePermission',
      url:'/permission/update',
      requestObject:'updatePermission',
      loading:'updateLoading',
      showModel:'updatePermissionModel'
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
    this.deletePermission={
      "permissionId":params.permissionId
    };
    this.axiosbusiness.delete(this,{
      url:'/permission/delete',
      requestObject:'deletePermission'
    })
    }
  },
  created () {
    console.log(1111)
    this.getList();
  },
  mounted () {

  }
}
</script>
