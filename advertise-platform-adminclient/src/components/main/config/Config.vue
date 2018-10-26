<!--公共配置管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
     <!-- <Button type='primary'  @click='add'>增加公共配置</Button>  -->
      <Button type='primary' v-if="isSuperAdmin" @click='update(updateConfig)'>修改公共配置</Button>
    </div>
		 <!--新增 -->
     <Modal v-model="addConfigModel"
           title="新增公共配置管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addConfig" :model="addConfig" :label-width="100" label-position="right"  :rules="addConfigRules">
        <FormItem prop="platformName" label="平台开户名称:">
          <Input type="text" v-model="addConfig.platformName" placeholder="平台开户名称">
          </Input>
        </FormItem>
        <FormItem prop="bankName" label="平台开户银行:">
          <Input type="text" v-model="addConfig.bankName" placeholder="平台开户银行">
          </Input>
        </FormItem>
        <FormItem prop="bankAccount" label="平台银行账户:">
          <Input type="text" v-model="addConfig.bankAccount" placeholder="平台银行账户">
          </Input>
        </FormItem>
        <FormItem prop="servicePhone" label="平台联系电话:">
          <Input type="text" v-model="addConfig.servicePhone" placeholder="平台联系电话">
          </Input>
        </FormItem>
        <FormItem prop="serviceQq" label="平台联系qq:">
          <Input type="text" v-model="addConfig.serviceQq" placeholder="平台联系qq">
          </Input>
        </FormItem>
        <FormItem prop="serviceProportion"  label="平台服务费率，单位%:">
          <InputNumber   :max="100" :min="0" :step="0.01" style="width:180px"  v-model="addConfig.serviceProportion" 
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="平台服务费率，单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="taxRate" label="税率，单位%:">
          <InputNumber   :max="100" :min="0" :step="0.01" style="width:180px" v-model="addConfig.taxRate"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="税率，单位%">
          </InputNumber >
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
     <Modal v-model="updateConfigModel"
           title="修改公共配置管理"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateConfig" :model="updateConfig" :label-width="100" label-position="right"  :rules="updateConfigRules">
        <FormItem prop="platformName" label="平台开户名称:">
          <Input type="text" v-model="updateConfig.platformName" placeholder="平台开户名称">
          </Input>
        </FormItem>
        <FormItem prop="bankName" label="平台开户银行:">
          <Input type="text" v-model="updateConfig.bankName" placeholder="平台开户银行">
          </Input>
        </FormItem>
        <FormItem prop="bankAccount" label="平台银行账户:">
          <Input type="text" v-model="updateConfig.bankAccount" placeholder="平台银行账户">
          </Input>
        </FormItem>
        <FormItem prop="servicePhone" label="平台联系电话:">
          <Input type="text" v-model="updateConfig.servicePhone" placeholder="平台联系电话">
          </Input>
        </FormItem>
        <FormItem prop="serviceQq" label="平台联系qq:">
          <Input type="text" v-model="updateConfig.serviceQq" placeholder="平台联系qq">
          </Input>
        </FormItem>
        <FormItem prop="serviceProportion"  label="平台服务费率，单位%:">
          <InputNumber   :max="100" :min="0" :step="0.01" style="width:180px"  v-model="updateConfig.serviceProportion" 
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="平台服务费率，单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="taxRate" label="税率，单位%:">
          <InputNumber  :max="100" :min="0" :step="0.01" style="width:180px" v-model="updateConfig.taxRate"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="税率，单位%">
          </InputNumber >
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
      <Card v-if="!updateConfig.configId" style="text-align:center">暂无</Card>
      <Card v-if="updateConfig.configId" :bordered="true">
            <p slot="title">配置项</p>
            <p>
              <span>平台开户名称：</span>
              <span v-text="updateConfig.platformName"></span>
            </p>
            <p>
              <span>平台开户银行：</span>
              <span v-text="updateConfig.bankName"></span>
            </p>
            <p>
              <span>平台银行账户：</span>
              <span v-text="updateConfig.bankAccount"></span>
            </p>
            <p>
              <span>平台联系电话：</span>
              <span v-text="updateConfig.servicePhone"></span>
            </p>
            <p>
              <span>平台联系qq：</span>
              <span v-text="updateConfig.serviceQq"></span>
            </p>
            <p>
              <span>平台服务费率，单位%：</span>
              <span v-text="updateConfig.serviceProportion"></span>%
            </p>
            <p>
              <span>税率，单位%：</span>
              <span v-text="updateConfig.taxRate"></span>%
            </p>
            <p>
              <span>创建时间：</span>
              <span v-text="updateConfig.createDate"></span>
            </p>
            <p>
              <span>更新时间：</span>
              <span v-text="updateConfig.updateDate"></span>
            </p>
      </Card>
    </div>
</template>
<script>
export default {
  name: 'Config',
  data () {
    return {
      isSuperAdmin:this.business.getIsSuperAdmin(),
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
			//增加参数
			addConfigModel:false,
			addLoading:false,
			addConfigRules: {
                servicePhone: [
                    {required: true, message: '平台联系电话为必填项', trigger: 'blur'}
                    ]
                },
			addConfig:{
			},
			//修改参数
			updateConfigModel:false,
			updateLoading:false,
			updateConfigRules: {
                servicePhone: [
                    {required: true, message: '平台联系电话为必填项', trigger: 'blur'}
                    ]
                },
			updateConfig:{ },
      //删除参数
      deleteConfig:{},
	    configList: [{}]
    }
  },
  methods: {
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
       countUrl:'/config/count',
       listUrl:'/config/list',
       data:'configList',
       success:()=>{
         this.updateConfig=this.configList[0];
       }
     },this.params)
    },
  //增加
	 add (params) {
      this.addConfigModel = true
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addConfigModel = false
        this.$refs.addConfig.resetFields()
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
      ref:'addConfig',
      url:'/config/add',
      requestObject:'addConfig',
      loading:'addLoading',
      showModel:'addConfigModel'
    })
    },
	 update (params) {
      this.updateConfigModel = true
     //获取修改实体
     if(params.configId){
       this.axiosbusiness.get(this,{
         url:'/config/load?configId='+params.configId,
         data:'updateConfig'
         })
     }
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateConfigModel = false
       // this.$refs.updateConfig.resetFields()
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
      ref:'updateConfig',
      url:'/config/update',
      requestObject:'updateConfig',
      loading:'updateLoading',
      showModel:'updateConfigModel'
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
    this.deleteConfig={
      "configId":params.configId
    };
    this.axiosbusiness.delete(this,{
      url:'/config/delete',
      requestObject:'deleteConfig'
    })
    }
  },
  created () {
    this.getList();
  },
  mounted () {

  }
}
</script>
