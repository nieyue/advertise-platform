<!--角色权限管理 -->
<template>
    <div class="body-wrap">
    <div v-if="roleIsSuperAdmin">
      <Alert type="success" style="text-align:center">
      拥有所有权限
      </Alert>
    </div>

    <div v-else-if="!roleIsSuperAdmin" >
    <Alert type="success">
        <p>范围:</p>
        <p>公共,（代表拥有此权限的人可以访问此接口的数据，包含非自身，即是其他账户接口的数据）</p>
        <p>自身,（代表拥有此权限的人可以访问此接口的数据，自能是自身账户接口的数据）</p>
    </Alert>
    <div class="body-btn-wrap">
        <Button type='primary'  @click='add'>增加角色权限</Button>
        <Button type='warning'  @click='addAllPublicPermission'>一键添加所有公共权限</Button>
        <Button type='info'  @click='addAllSelfPermission'>一键添加所有自身权限</Button>
        <Button type='error'  @click='delAllPermission'>一键删除所有权限</Button>
        <div class="search-wrap">
            <Select v-model="params.region" transfer class="search-wrap-input"  placeholder="范围，全部">
                <Option v-for="item in regionParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
            </Select>
            <Input v-model="params.permissionId" class="search-wrap-input" placeholder="权限id"></Input>
            <Button @click="search" type="info"  >查询</Button>
        </div>
    </div>
		 <!--新增 -->
     <Modal v-model="addRolePermissionModel"
           title="新增角色权限管理"
           :closable="false"
           :mask-closable="false"
           width="800px"
    >
      <Form ref="addRolePermission" :model="addRolePermission" :label-width="100" label-position="right"  :rules="addRolePermissionRules">
        <FormItem prop="permissionId" label="权限:">
        <RadioGroup v-model="addRolePermission.permissionId" type="button" >
            <Radio style="margin:5px;border-left:1px solid #dddee1" :label="item.permissionId" v-for="item in permissionList" :value="item.permissionId" :key="item.permissionId" >
                {{item.name}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="region" label="范围:">
          <Select v-model="addRolePermission.region" transfer size="large" style="width:100px">
              <Option v-for="item in regionList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
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
     <Modal v-model="updateRolePermissionModel"
           title="修改角色权限管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateRolePermission" :model="updateRolePermission" :label-width="100" label-position="right"  :rules="updateRolePermissionRules">
        <!-- <FormItem prop="permissionId" label="权限:">
        <RadioGroup v-model="updateRolePermission.permissionId" type="button" >
            <Radio style="margin:5px;border-left:1px solid #dddee1" :label="item.permissionId" v-for="item in permissionList" :value="item.permissionId" :key="item.permissionId" >
                {{item.name}}
            </Radio>
        </RadioGroup>
        </FormItem> -->
        <FormItem prop="permissionId" label="权限:">
          <span  v-for="value,key in updateRolePermission.permission" :value="value" :key="key" v-if="key=='name'" v-text="value"></span>
        </FormItem>
        <FormItem prop="region" label="范围:">
          <Select v-model="updateRolePermission.region" transfer size="large" style="width:100px">
              <Option v-for="item in regionList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
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
      <Table border height="500"  :current="params.currentPage" :columns='rolePermissionColumns' :data='rolePermissionList' ref='table' size="small"></Table>
    </div>
    
  </div>
</template>
<script>
export default {
  name: 'RolePermission',
  data () {
    return {
      //当前角色
        role:{},
        //临时permission
        permission:{},
       //角色是否超级管理员,默认不是
        roleIsSuperAdmin:false,
        params:{
          startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:100000000,//每页的个数
            total:0//总数
        },
         //范围，1公共，2自身
          regionList:[
          {id:1,value:'公共'},
          {id:2,value:'自身'}
          ],
          //权限类型，默认0开放，1鉴权
          typeList:[
              {id:0,value:'开放'},
          {id:1,value:'鉴权'}
          ],
         //范围，1公共，2自身
          regionParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'公共'},
          {id:2,value:'自身'}
          ],
			//增加参数
			addRolePermissionModel:false,
			addLoading:false,
			addRolePermissionRules: {
                permissionId: [
                    {type:"string",required: true, message: '权限为必选项', trigger: 'change'}
                    ],
                region: [
                    {type:"number",required: true, message: '范围为必选项', trigger: 'change'}
                    ]
                },
			addRolePermission:{
        region:2
			},
			//修改参数
			updateRolePermissionModel:false,
			updateLoading:false,
			updateRolePermissionRules: {
               /*  permissionId: [
                    {type:"number",required: true, message: '权限为必选项', trigger: 'change'}
                    ], */
                region: [
                    {type:"number",required: true, message: '范围为必选项', trigger: 'change'}
                    ]
                },
			updateRolePermission:{},
      //删除参数
      deleteRolePermission:{},
	    permissionList: [],
	    rolePermissionList: [],
	    rolePermissionColumns: [
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
          title: '角色权限id',
          minWidth:100,
          key: 'rolePermissionId',
          align:'center'
        },
        {
            title:'角色权限范围',
            minWidth:100,
            key:'region',
            align:'center',
                render: (h, params) => {
                let regionvalue="";
            this.regionList.forEach(element => {
              if(element.id==params.row.region){
                regionvalue=element.value;
              }
            });
             return  h('span',regionvalue);
                }
        },
        {
            title:'角色权限类型',
            minWidth:100,
            key:'permission.type',
            align:'center',
            render: (h, params) => {
                let typevalue="";
                this.typeList.forEach(element => {
                if(element.id==params.row.permission.type){
                    typevalue=element.value;
                }
                });
                return  h('span',typevalue);
            }
        },
        {
          title:'权限管理名',
          minWidth:100,
            align:'center',
            render: (h, params) => {
                return  h('span',params.row.permission.managerName);
            }
        },
        {
          title:'角色权限名',
          minWidth:100,
            align:'center',
            render: (h, params) => {
                return  h('span',params.row.permission.name);
            }
        },
        {
          title:'路由',
          minWidth:100,
            align:'center',
            render: (h, params) => {
                return  h('span',params.row.permission.route);
            }
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
    //一键添加所有公共权限
    addAllPublicPermission(){
      this.$Spin.show();
      let pll=this.permissionList.length;
           for(let i=0;i<pll;i++){
             setTimeout(()=>{
               this.addRolePermission.roleId=this.$route.params.roleId
              this.addRolePermission.region=1//公共
              this.addRolePermission.permissionId=this.permissionList[i].permissionId
               this.axios({
                 method:"post",
                url:'/rolePermission/add',
                data:this.Qs.stringify(this.addRolePermission),
                withCredentials: true
                }).then(res => {
                  if (res.data.code == 200) {
                    if(i==pll-1){
                      this.$Message.success(res.data.msg)
                      this.getList()
                      this.$Spin.hide();
                    }
                } else {
                  this.$Message.error(res.data.msg)
                }
              }).catch(res => {
                  this.$Message.error(res.data.msg)
              })
             },100*i)
           }

    },
    //一键添加所有自身权限
    addAllSelfPermission(){
      this.$Spin.show();
   let pll=this.permissionList.length;
           for(let i=0;i<pll;i++){
             setTimeout(()=>{
               this.addRolePermission.roleId=this.$route.params.roleId
              this.addRolePermission.region=2//自身
              this.addRolePermission.permissionId=this.permissionList[i].permissionId
               this.axios({
                method:"post",
                url:'/rolePermission/add',
                data:this.Qs.stringify(this.addRolePermission),
                withCredentials: true
                }).then(res => {
                  if (res.data.code == 200) {
                    if(i==pll-1){
                      this.$Message.success(res.data.msg)
                      this.getList()
                      this.$Spin.hide();
                    }
                } else {
                  this.$Message.error(res.data.msg)
                }
              }).catch(res => {
                  this.$Message.error(res.data.msg)
              })
             },100*i)
           }
    },
    //一键删除所有权限
    delAllPermission(){
      this.$Spin.show();
      let rpll=this.rolePermissionList.length;
           for(let i=0;i<rpll;i++){
              setTimeout(()=>{
              this.axios({
                      method:"post",
                      url:'/rolePermission/delete?rolePermissionId='+this.rolePermissionList[i].rolePermissionId,
                      withCredentials: true
                      }).then(res => {
                      if (res.data.code == 200) {
                        if(i==rpll-1){
                        this.$Message.success(res.data.msg)
                          this.getList();
                          this.$Spin.hide();
                          location.reload();
                        }
                      }else {
                        this.$Message.error(res.data.msg)
                      }
                    }).catch(res => {
                      this.$Message.error(res.data.msg)
                    })
                    },100*i)
           }
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
    //根据角色id获取角色
    getRoleByRoleId(){
      this.axiosbusiness.get(this,{
         url:'/role/load?roleId='+this.$route.params.roleId,
         data:'role',
         success:()=>{
           if(this.role.name=="超级管理员"){
             this.roleIsSuperAdmin=true
           }else{
              this.getPermissionList();
           }
           //console.error( this.roleIsSuperAdmin)
         }
       })
    },
    //获取权限总表
    getPermissionList(){
        this.params.type=1//只显示需要鉴权的
       this.axiosbusiness.getList(this,{
       countUrl:'/permission/count',
       listUrl:'/permission/list',
       data:'permissionList',
       success:()=>{
        this.getList()
       }
     },this.params)
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
    this.params.roleId=this.$route.params.roleId
     this.axiosbusiness.getList(this,{
       countUrl:'/rolePermission/count',
       listUrl:'/rolePermission/list',
       data:'rolePermissionList',
       success:()=>{
           //如果已有权限不显示permissionList里面
           let pll=this.permissionList.length;
           for(let i=0;i<pll;i++){
             for(let j=0;j<this.rolePermissionList.length;j++){
               if(this.permissionList[i].permissionId==this.rolePermissionList[j].permissionId){
            // console.error(this.permissionList)
                 this.permissionList.splice(i,1);
                    pll--;
                    i--;
                    break;//完成必须跳出当前循环，不然局部对象为undefined找不到
                    }
              }
           }
       }
     },this.params)
    },
  //增加
	 add (params) {
      this.addRolePermissionModel = true
      this.addRolePermission.roleId=this.$route.params.roleId
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addRolePermissionModel = false
        this.$refs.addRolePermission.resetFields()
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
      ref:'addRolePermission',
      url:'/rolePermission/add',
      requestObject:'addRolePermission',
      loading:'addLoading',
      showModel:'addRolePermissionModel'
    })
    },
	 update (params) {
      this.updateRolePermissionModel = true
      this.addRolePermission.roleId=this.$route.params.roleId
     //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/rolePermission/load?rolePermissionId='+params.rolePermissionId,
         data:'updateRolePermission',
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateRolePermissionModel = false
        this.$refs.updateRolePermission.resetFields()
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
    //修改必须去掉permission
    delete this.updateRolePermission.permission;
    this.axiosbusiness.update(this,{
      ref:'updateRolePermission',
      url:'/rolePermission/update',
      requestObject:'updateRolePermission',
      loading:'updateLoading',
      showModel:'updateRolePermissionModel'
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
    this.deleteRolePermission={
      "rolePermissionId":params.rolePermissionId
    };
    this.axiosbusiness.delete(this,{
      url:'/rolePermission/delete',
      requestObject:'deleteRolePermission',
      success:()=>{
       //获取删除实体，添加到增加角色列表中
      this.axiosbusiness.get(this,{
         url:'/permission/load?permissionId='+params.permissionId,
         data:'permission',
         success:()=>{
           this.permissionList.push(this.permission)
           this.getList()
         }
       })
      }
    })
    }
  },
  created () {
    this.getRoleByRoleId();
  },
  mounted () {

  }
}
</script>
