<!--位置媒体管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
       <!-- <Button type='primary'  @click='add'>增加位置媒体</Button>   -->
      <div class="search-wrap">
        <Input v-model="params.mediaId" class="search-wrap-input" placeholder="媒体id"></Input>
        <Input v-model="params.positionAdvertiseId" class="search-wrap-input" placeholder="位置广告id"></Input>
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
        <Select v-model="params.position" transfer class="search-wrap-input"  placeholder="广告位置，全部">
            <Option v-for="item in positionParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.payStatus" transfer class="search-wrap-input"  placeholder="广告位置，全部">
            <Option v-for="item in payStatusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
	<!--新增 -->
     <Modal v-model="addPositionMediaModel"
           title="新增位置媒体"
           :closable="false"
           :mask-closable="false"
           :width="1000"
    >
      <Form ref="addPositionMedia" :model="addPositionMedia" :label-width="100"  label-position="right"  :rules="addPositionMediaRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="addPositionMedia.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="mediaName" label="媒体名称:" >
          <Input type="text" v-model="addPositionMedia.mediaName" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="advertiseName" label="广告名称:" >
          <Input type="text" v-model="addPositionMedia.advertiseName" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:" >
          <Input type="text" v-model="addPositionMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="qualityPrice" label="'质检扣款:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addPositionMedia.qualityPrice"
            placeholder="质检扣款">
          </InputNumber >
        </FormItem>
        <FormItem prop="url" label="发布链接:" >
          <Input type="text" v-model="addPositionMedia.url" placeholder="发布链接">
          </Input>
        </FormItem>
         <FormItem  label="预计发布时间/实际发布时间:">
          <DatePicker type="datetimerange" placeholder="预计发布时间/实际发布时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getAddExpectFinalDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="payStatus" label="支付状态:">
          <Select v-model="addPositionMedia.payStatus" transfer size="large" style="width:100px">
              <Option v-for="item in payStatusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="addPositionMedia.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
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
     <Modal v-model="updatePositionMediaModel"
           title="修改位置媒体"
           :closable="false"
           :mask-closable="false"
            :width="1000"
    >
      <Form ref="updatePositionMedia" :model="updatePositionMedia" :label-width="100" label-position="right"  :rules="updatePositionMediaRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="updatePositionMedia.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="mediaName" label="媒体名称:" >
          <Input type="text" v-model="updatePositionMedia.mediaName" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="advertiseName" label="广告名称:" >
          <Input type="text" v-model="updatePositionMedia.advertiseName" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:" >
          <Input type="text" v-model="updatePositionMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="qualityPrice" label="'质检扣款:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updatePositionMedia.qualityPrice"
            placeholder="质检扣款">
          </InputNumber >
        </FormItem>
        <FormItem prop="url" label="发布链接:" >
          <Input type="text" v-model="updatePositionMedia.url" placeholder="发布链接">
          </Input>
        </FormItem>
         <FormItem  label="预计发布时间/实际发布时间:">
          <DatePicker type="datetimerange" placeholder="预计发布时间/实际发布时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getUpdateExpectFinalDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="payStatus" label="支付状态:">
          <Select v-model="updatePositionMedia.payStatus" transfer size="large" style="width:100px">
              <Option v-for="item in payStatusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="updatePositionMedia.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
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
      <Table border :columns='positionMediaColumns' :data='positionMediaList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'PositionMedia',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        //广告位置,1头条，2二条，3三条，4四条，5五条，6六条
        positionParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'头条'},
          {id:2,value:'二条'},
          {id:3,value:'三条'},
          {id:4,value:'四条'},
          {id:5,value:'五条'},
          {id:6,value:'六条'}
          ],
        //支付状态，1待支付，2已支付
        payStatusParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'待支付'},
          {id:2,value:'已支付'}
          ],
        //状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消
        statusParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'待接单'},
          {id:2,value:'已接单'},
          {id:3,value:'已拒单'},
          {id:4,value:'执行中'},
          {id:5,value:'已完成'},
          {id:6,value:'已取消'}
          ],
         //广告位置,1头条，2二条，3三条，4四条，5五条，6六条
        positionList:[
          {id:1,value:'头条'},
          {id:2,value:'二条'},
          {id:3,value:'三条'},
          {id:4,value:'四条'},
          {id:5,value:'五条'},
          {id:6,value:'六条'}
          ],
           //支付状态，1待支付，2已支付
        payStatusList:[
          {id:1,value:'待支付'},
          {id:2,value:'已支付'}
          ],
        //状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消
        statusList:[
          {id:1,value:'待接单'},
          {id:2,value:'已接单'},
          {id:3,value:'已拒单'},
          {id:4,value:'执行中'},
          {id:5,value:'已完成'},
          {id:6,value:'已取消'}
          ],
			//增加参数
			addPositionMediaModel:false,
			addLoading:false,
			addPositionMediaRules: {
                mediaName: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ],
                advertiseName: [
                    {required: true, message: '广告名称为必填项', trigger: 'blur'}
                    ]
                },
			addPositionMedia:{
                position:1,
                consumePrice:0,
                qualityPrice:0,
                finalPrice:0,
                payStatus:1,
                status:1,
           
			},
			//修改参数
			updatePositionMediaModel:false,
			updateLoading:false,
			updatePositionMediaRules: {
                mediaName: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ],
                advertiseName: [
                    {required: true, message: '广告名称为必填项', trigger: 'blur'}
                    ]
                },
			updatePositionMedia:{
      },
      //删除参数
      deletePositionMedia:{},
      //列表
      positionMediaCateList:[],
      positionMediaList: [],
	  positionMediaColumns: [
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
          title: '位置媒体id',
          minWidth:100,
          key: 'positionMediaId',
          align:'center'
        },
        {
          title:'账户id',
          minWidth:100,
          key:'accountId',
          align:'center'
        },
         {
          title:'媒体id',
          minWidth:100,
          key:'mediaId',
          align:'center'
        },
        {
          title:'位置广告id',
          minWidth:100,
          key:'positionAdvertiseId',
          align:'center'
        },
        {
          title:'媒体名称',
          minWidth:100,
          key:'mediaName',
          align:'center'
        },
        {
          title:'广告名称',
          minWidth:100,
          key:'advertiseName',
          align:'center'
        },
        {
          title:'广告位置',
          minWidth:100,
          key:'position',
          align:'center',
          render: (h, params) => {
            let positionvalue="";
            this.positionList.forEach(element => {
              if(element.id==params.row.position){
                positionvalue=element.value;
              }
            });
             return  h('span',positionvalue);
          }
        },
        {
          title:'广告金额',
          minWidth:100,
          key:'consumePrice',
          align:'center'
        },
        {
          title:'质检扣款',
          minWidth:100,
          key:'qualityPrice',
          align:'center'
        },
        {
            title:'广告收入',
            minWidth:100,
            key:'finalPrice',
            align:'center'
        },
        {
          title:'发布链接',
          minWidth:100,
          key:'url',
          align:'center'
        },
        {
          title:'支付状态',
          minWidth:100,
          key:'status',
          align:'center',
          render: (h, params) => {
            let payStatusvalue="";
            this.payStatusList.forEach(element => {
              if(element.id==params.row.payStatus){
                payStatusvalue=element.value;
              }
            });
             return  h('span',payStatusvalue);
          }
        },
        {
          title:'状态',
          minWidth:100,
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
          title:'预计发布时间',
          minWidth:100,
          key:'expectDate',
          sortable: true,
          align:'center'
        },
        {
          title:'实际发布时间',
          minWidth:100,
          key:'finalDate',
          sortable: true,
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
          fixed:'right',
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
    //查询
    search(){
      this.params.currentPage=1;
      this.params.pageNum =1;
      this.selectPage(this.params.currentPage)
    },
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       mediaId:JSON.parse(this.$route.params.pathParams).mediaId,
       positionAdvertiseId:JSON.parse(this.$route.params.pathParams).positionAdvertiseId,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getList()
    },

    //获取增加的预计实际时间
    getAddExpectFinalDate(array){
        this.addPositionMedia.expectDate=this.utils.getTime(array[0])
        this.addPositionMedia.finalDate=this.utils.getTime(array[1])
    },
    //获取修改的预计实际时间
    getUpdateExpectFinalDate(array){
        this.updatePositionMedia.expectDate=this.utils.getTime(arrayy[0])
        this.updatePositionMedia.finalDate=this.utils.getTime(arrayy[1])
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
    this.params.mediaId=JSON.parse(this.$route.params.pathParams).mediaId
    this.params.positionAdvertiseId=JSON.parse(this.$route.params.pathParams).positionAdvertiseId
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/positionMedia/count',
       listUrl:'/positionMedia/list',
       data:'positionMediaList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addPositionMediaModel = true
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addPositionMediaModel = false
        this.$refs.addPositionMedia.resetFields()
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
    this.addPositionMedia.mediaId=JSON.parse(this.$route.params.pathParams).mediaId
    this.addPositionMedia.positionAdvertiseId=JSON.parse(this.$route.params.pathParams).positionAdvertiseId
    this.addPositionMedia.accountId=JSON.parse(this.$route.params.pathParams).accountId
    this.axiosbusiness.add(this,{
      ref:'addPositionMedia',
      url:'/positionMedia/add',
      requestObject:'addPositionMedia',
      loading:'addLoading',
      showModel:'addPositionMediaModel'
    })
    },
	 update (params) {
      this.updatePositionMediaModel = true
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/positionMedia/load?positionMediaId='+params.positionMediaId,
         data:'updatePositionMedia',
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updatePositionMediaModel = false
        this.$refs.updatePositionMedia.resetFields()
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
      ref:'updatePositionMedia',
      url:'/positionMedia/update',
      requestObject:'updatePositionMedia',
      loading:'updateLoading',
      showModel:'updatePositionMediaModel'
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
    this.deletePositionMedia={
      "positionMediaId":params.positionMediaId
    };
    this.axiosbusiness.delete(this,{
      url:'/positionMedia/delete',
      requestObject:'deletePositionMedia'
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
