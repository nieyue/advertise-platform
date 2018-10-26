<!--阅读媒体管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <!-- <Button type='primary'  @click='add'>增加阅读媒体</Button> -->
      <div class="search-wrap">
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
        <Input v-model="params.mediaId" class="search-wrap-input" placeholder="媒体Id"></Input>
        <Input v-model="params.readAdvertiseId" class="search-wrap-input" placeholder="阅读广告Id"></Input>
        <Select v-model="params.position" transfer class="search-wrap-input"  placeholder="广告位置，全部">
            <Option v-for="item in positionParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
	<!--新增 -->
     <Modal v-model="addReadMediaModel"
           title="新增阅读媒体"
           :closable="false"
           :mask-closable="false"
           :width="1000"
    >
      <Form ref="addReadMedia" :model="addReadMedia" :label-width="100"  label-position="right"  :rules="addReadMediaRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="addReadMedia.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="媒体名称:">
          <Input type="text" v-model="addReadMedia.name" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:">
          <Input type="text" v-model="addReadMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="expectReadingNumber" label="预估阅读量:">
          <Input type="text" v-model="addReadMedia.expectReadingNumber" placeholder="预估阅读量">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadMedia.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="qualityPrice" label="'质检返款:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadMedia.qualityPrice"
            placeholder="质检返款">
          </InputNumber >
        </FormItem>
        <FormItem prop="url" label="发布链接:" >
          <Input type="text" v-model="addReadMedia.url" placeholder="发布链接">
          </Input>
        </FormItem>
        <FormItem  label="预计发布时间/实际发布时间:">
          <DatePicker type="datetimerange" placeholder="预计发布时间/实际发布时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getAddExpectFinalDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="addReadMedia.status" transfer size="large" style="width:100px">
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
     <Modal v-model="updateReadMediaModel"
           title="修改阅读媒体"
           :closable="false"
           :mask-closable="false"
            :width="1000"
    >
      <Form ref="updateReadMedia" :model="updateReadMedia" :label-width="100" label-position="right"  :rules="updateReadMediaRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="updateReadMedia.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="媒体名称:">
          <Input type="text" v-model="updateReadMedia.name" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:">
          <Input type="text" v-model="updateReadMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="expectReadingNumber" label="预估阅读量:">
          <Input type="text" v-model="updateReadMedia.expectReadingNumber" placeholder="预估阅读量">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadMedia.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="qualityPrice" label="'质检返款:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadMedia.qualityPrice"
            placeholder="质检返款">
          </InputNumber >
        </FormItem>
        <FormItem prop="url" label="发布链接:" >
          <Input type="text" v-model="updateReadMedia.url" placeholder="发布链接">
          </Input>
        </FormItem>
        <FormItem  label="预计发布时间/实际发布时间:">
          <DatePicker type="datetimerange" placeholder="预计发布时间/实际发布时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getUpdateExpectFinalDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="updateReadMedia.status" transfer size="large" style="width:100px">
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
      <Table border :columns='readMediaColumns' :data='readMediaList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'ReadMedia',
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
        //状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消
        statusParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'待审核'},
          {id:2,value:'待发布'},
          {id:3,value:'发布中'},
          {id:4,value:'待结算'},
          {id:5,value:'已完成'},
          {id:6,value:'审核未通过'},
          {id:7,value:'已取消'}
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
        //状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消
        statusList:[
          {id:1,value:'待审核'},
          {id:2,value:'待发布'},
          {id:3,value:'发布中'},
          {id:4,value:'待结算'},
          {id:5,value:'已完成'},
          {id:6,value:'审核未通过'},
          {id:7,value:'已取消'}
          ],
			//增加参数
			addReadMediaModel:false,
			addLoading:false,
			addReadMediaRules: {
                name: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ]
                },
			addReadMedia:{
                position:1,
                funs:0,
                expectReadingNumber:0,
                finalReadingNumber:0,
                unitPrice:0,
                consumePrice:0,
                qualityPrice:0,
                finalPrice:0,
                status:1
           
			},
			//修改参数
			updateReadMediaModel:false,
			updateLoading:false,
			updateReadMediaRules: {
                name: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ]
                },
			updateReadMedia:{
                position:1,
      },
      //删除参数
      deleteReadMedia:{},
      //列表
      readMediaCateList:[],
      readMediaList: [],
	  readMediaColumns: [
        {
          title: '序号',
          width:100,
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '阅读媒体id',
          width:100,
          key: 'readMediaId',
          align:'center'
        },
        {
          title:'账户id',
          width:100,
          key:'accountId',
          align:'center'
        },
        {
          title:'媒体id',
          width:100,
          key:'mediaId',
          align:'center'
        },
        {
          title:'阅读广告id',
          width:100,
          key:'readAdvertiseId',
          align:'center'
        },
        {
          title:'阅读媒体名称',
          width:100,
          key:'name',
          align:'center'
        },
        {
          title:'广告位置',
          width:100,
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
          title:'预估阅读量',
          width:100,
          key:'expectReadingNumber',
          align:'center'
        },
        {
          title:'实际阅读量',
          width:100,
          key:'finalReadingNumber',
          align:'center'
        },
        {
          title:'广告单价',
          width:100,
          key:'unitPrice',
          align:'center'
        },
        {
          title:'广告金额',
          width:100,
          key:'consumePrice',
          align:'center'
        },
        {
          title:'质检返款',
          width:100,
          key:'qualityPrice',
          align:'center'
        },
        {
          title:'实际消费',
          width:100,
          key:'finalPrice',
          align:'center'
        },
        {
          title:'发布链接',
          width:100,
          key:'url',
          align:'center'
        },
        {
          title:'状态',
          width:100,
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
          width:100,
          key:'expectDate',
          sortable: true,
          align:'center'
        },
        {
          title:'实际发布时间',
          width:100,
          key:'finalDate',
          sortable: true,
          align:'center'
        },
        {
          title:'创建时间',
          width:100,
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
        	title:'修改时间',
          width:100,
          key:'updateDate',
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          width:200,
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
       readAdvertiseId:JSON.parse(this.$route.params.pathParams).readAdvertiseId,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getList()
    },
    //获取增加的预计实际时间
    getAddExpectFinalDate(array){
        this.addReadMedia.expectDate=this.utils.getTime(array[0])
        this.addReadMedia.finalDate=this.utils.getTime(array[1])
    },
    //获取修改的预计实际时间
    getUpdateExpectFinalDate(array){
        this.updateReadMedia.expectDate=this.utils.getTime(array[0])
        this.updateReadMedia.finalDate=this.utils.getTime(array[1])
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
    this.params.readAdvertiseId=JSON.parse(this.$route.params.pathParams).readAdvertiseId
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/readMedia/count',
       listUrl:'/readMedia/list',
       data:'readMediaList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addReadMediaModel = true
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addReadMediaModel = false
        this.$refs.addReadMedia.resetFields()
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
    this.addReadMedia.mediaId=JSON.parse(this.$route.params.pathParams).mediaId
    this.addReadMedia.readAdvertiseId=JSON.parse(this.$route.params.pathParams).readAdvertiseId
    this.addReadMedia.accountId=JSON.parse(this.$route.params.pathParams).accountId
    this.axiosbusiness.add(this,{
      ref:'addReadMedia',
      url:'/readMedia/add',
      requestObject:'addReadMedia',
      loading:'addLoading',
      showModel:'addReadMediaModel'
    })
    },
	 update (params) {
      this.updateReadMediaModel = true
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/readMedia/load?readMediaId='+params.readMediaId,
         data:'updateReadMedia',
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateReadMediaModel = false
        this.$refs.updateReadMedia.resetFields()
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
      ref:'updateReadMedia',
      url:'/readMedia/update',
      requestObject:'updateReadMedia',
      loading:'updateLoading',
      showModel:'updateReadMediaModel'
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
    this.deleteReadMedia={
      "readMediaId":params.readMediaId
    };
    this.axiosbusiness.delete(this,{
      url:'/readMedia/delete',
      requestObject:'deleteReadMedia'
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
