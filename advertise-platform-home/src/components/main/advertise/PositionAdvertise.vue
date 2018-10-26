<!--位置广告管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <div class="search-wrap">
        <!-- <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input> -->
        <Select v-model="params.position" transfer class="search-wrap-input"  placeholder="广告位置，全部">
            <Option v-for="item in positionParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
        <Button type='primary'  @click='add' style="float:right;" shape="circle" icon="md-add" size="large">选号直投</Button>
      </div>
    </div>
	<!--新增 -->
     <Modal v-model="addPositionAdvertiseModel"
           title="新增位置广告"
           :closable="false"
           :mask-closable="false"
           :width="1000"
    >
      <Form ref="addPositionAdvertise" :model="addPositionAdvertise" :label-width="100"  label-position="right"  :rules="addPositionAdvertiseRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="addPositionAdvertise.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:" >
          <Input type="text" v-model="addPositionAdvertise.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="title" label="标题:" >
          <Input type="text" v-model="addPositionAdvertise.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" >
          <Input type="text" v-model="addPositionAdvertise.author" placeholder="作者">
          </Input>
        </FormItem>
         <FormItem prop="topImg" label="封面(上传或者填写):">
            <my-upload :defaultUpload="addPositionAdvertise.topImg" @uploadList="getAddTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="addPositionAdvertise.topImg" placeholder="封面">
            </Input>
                <img :src="addPositionAdvertise.topImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="content" label="正文内容:"  >
          <my-wangeditor  :content="addPositionAdvertise.content" @getWangEditorContent="getAddEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" >
          <Input type="text" v-model="addPositionAdvertise.originUrl" placeholder="原文链接">
          </Input>
        </FormItem>
        <FormItem  label="投放开始时间:">
          <DatePicker type="datetime" placeholder="投放开始时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getAddStartDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="addPositionAdvertise.status" transfer size="large" style="width:100px">
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
     <Modal v-model="updatePositionAdvertiseModel"
           title="修改位置广告"
           :closable="false"
           :mask-closable="false"
            :width="1000"
    >
      <Form ref="updatePositionAdvertise" :model="updatePositionAdvertise" :label-width="100" label-position="right"  :rules="updatePositionAdvertiseRules">
        <FormItem prop="position" label="广告位置:">
          <RadioGroup v-model="updatePositionAdvertise.position" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in positionList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:" >
          <Input type="text" v-model="updatePositionAdvertise.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="title" label="标题:" >
          <Input type="text" v-model="updatePositionAdvertise.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" >
          <Input type="text" v-model="updatePositionAdvertise.author" placeholder="作者">
          </Input>
        </FormItem>
         <FormItem prop="topImg" label="封面(上传或者填写):">
            <my-upload :defaultUpload="updatePositionAdvertise.topImg" @uploadList="getUpdateTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="updatePositionAdvertise.topImg" placeholder="封面">
            </Input>
                <img :src="updatePositionAdvertise.topImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="content" label="正文内容:"  >
          <my-wangeditor  :content="updatePositionAdvertise.content" @getWangEditorContent="getUpdateEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" >
          <Input type="text" v-model="updatePositionAdvertise.originUrl" placeholder="原文链接">
          </Input>
        </FormItem>
        <FormItem  label="投放开始时间:">
          <DatePicker type="datetime" placeholder="投放开始时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getUpdateStartDate" style="width: 300px" ></DatePicker>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="updatePositionAdvertise.status" transfer size="large" style="width:100px">
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
      <Table border :columns='positionAdvertiseColumns' :data='positionAdvertiseList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'PositionAdvertise',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        //广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文
        positionParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'多图文头条'},
          {id:2,value:'多图文二条'},
          {id:3,value:'多图文3-N条'},
          {id:4,value:'单图文'}
          ],
        //状态，1待支付、2待审核、3投放中、4暂停中、5已完成
        statusParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'待支付'},
          {id:2,value:'待审核'},
          {id:3,value:'投放中'},
          {id:4,value:'暂停中'},
          {id:5,value:'已完成'}
          ],
         //广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文
        positionList:[
          {id:1,value:'多图文头条'},
          {id:2,value:'多图文二条'},
          {id:3,value:'多图文3-N条'},
          {id:4,value:'单图文'}
          ],
        //状态，1待支付、2待审核、3投放中、4暂停中、5已完成
        statusList:[
          {id:1,value:'待支付'},
          {id:2,value:'待审核'},
          {id:3,value:'投放中'},
          {id:4,value:'暂停中'},
          {id:5,value:'已完成'}
          ],
			//增加参数
			addPositionAdvertiseModel:false,
			addLoading:false,
			addPositionAdvertiseRules: {
                name: [
                    {required: true, message: '广告名称为必填项', trigger: 'blur'}
                    ]
                },
			addPositionAdvertise:{
                topImg:'',
                position:1,
                consumePrice:0,
                finalPrice:0,
                status:1
           
			},
			//修改参数
			updatePositionAdvertiseModel:false,
			updateLoading:false,
			updatePositionAdvertiseRules: {
                name: [
                    {required: true, message: '广告名称为必填项', trigger: 'blur'}
                    ]
                },
			updatePositionAdvertise:{
                position:1,
      },
      //删除参数
      deletePositionAdvertise:{},
      //列表
      positionAdvertiseCateList:[],
      positionAdvertiseList: [],
	  positionAdvertiseColumns: [
       /*  {
          title: '序号',
          width:100,
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        }, */
        {
          title: '位置广告id',
          key: 'positionAdvertiseId',
          align:'center'
        },
        {
          title:'广告名称',
          key:'name',
          align:'center'
        },
        {
          title:'广告位置',
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
          key:'consumePrice',
          align:'center'
        },
        {
          title:'实际金额',
          key:'finalPrice',
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
          title:'投放开始时间',
          key:'startDate',
          sortable: true,
          align:'center'
        },
		{
          title: '操作',
          key: 'action',
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
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getList()
    },

      //获取增加的图片
    getAddTopImg(data){
      this.addPositionAdvertise.topImg=data[0].url
    },
    //获取修改的图片
    getUpdateTopImg(data){
      this.updatePositionAdvertise.topImg=data[0].url
    },

    //获取增加的编辑器内容
    getAddEditor(data){
      this.addPositionAdvertise.content=data
    },
    //获取修改的编辑器内容
    getUpdateEditor(data){
      this.updatePositionAdvertise.content=data
    },
    //获取增加的预计实际时间
    getAddStartDate(array){
        this.addPositionAdvertise.startDate=this.utils.getTime(array)
    },
    //获取修改的预计实际时间
    getUpdateStartDate(array){
        this.updatePositionAdvertise.startDate=this.utils.getTime(array)
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
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/positionAdvertise/count',
       listUrl:'/positionAdvertise/list',
       data:'positionAdvertiseList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addPositionAdvertiseModel = true
      this.addPositionAdvertise.content = ' '
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addPositionAdvertiseModel = false
        this.$refs.addPositionAdvertise.resetFields()
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
    this.addPositionAdvertise.accountId=JSON.parse(this.$route.params.pathParams).accountId
    this.axiosbusiness.add(this,{
      ref:'addPositionAdvertise',
      url:'/positionAdvertise/add',
      requestObject:'addPositionAdvertise',
      loading:'addLoading',
      showModel:'addPositionAdvertiseModel'
    })
    },
	 update (params) {
      this.updatePositionAdvertiseModel = true
      this.addPositionAdvertise.content = ' '
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/positionAdvertise/load?positionAdvertiseId='+params.positionAdvertiseId,
         data:'updatePositionAdvertise',
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updatePositionAdvertiseModel = false
        this.$refs.updatePositionAdvertise.resetFields()
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
      ref:'updatePositionAdvertise',
      url:'/positionAdvertise/update',
      requestObject:'updatePositionAdvertise',
      loading:'updateLoading',
      showModel:'updatePositionAdvertiseModel'
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
    this.deletePositionAdvertise={
      "positionAdvertiseId":params.positionAdvertiseId
    };
    this.axiosbusiness.delete(this,{
      url:'/positionAdvertise/delete',
      requestObject:'deletePositionAdvertise'
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
      //console.log(this.business.getIsSuperAdmin())
    this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  }
}
</script>
