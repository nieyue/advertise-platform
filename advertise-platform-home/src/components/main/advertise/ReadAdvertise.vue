<!--阅读广告管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <div class="search-wrap">
       <!--  <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input> -->
        <Select v-model="params.type" transfer class="search-wrap-input"  placeholder="广告形式，全部">
            <Option v-for="item in typeParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
        <Button type='primary'  @click='add' style="float:right;" shape="circle" icon="md-add" size="large">增加阅读广告</Button>
      </div>
    </div>
	<!--新增 -->
     <Modal v-model="addReadAdvertiseModel"
           title="新增阅读广告"
           :closable="false"
           :mask-closable="false"
           :width="1000"
    >
      <Form ref="addReadAdvertise" :model="addReadAdvertise" :label-width="100"  label-position="right"  :rules="addReadAdvertiseRules">
        <FormItem prop="type" label="广告形式:">
          <!-- <Select v-model="addReadAdvertise.type" transfer size="large" style="width:100px">
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select> -->
          <RadioGroup v-model="addReadAdvertise.type" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in typeList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:">
          <Input type="text" v-model="addReadAdvertise.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadAdvertise.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="totalPrice" label="'广告预算（总额）:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadAdvertise.totalPrice"
            placeholder="广告预算（总额）">
          </InputNumber >
        </FormItem>
        <FormItem prop="guideContent" label="引导文案:"v-if="addReadAdvertise.type==2">
          <Input type="textarea" v-model="addReadAdvertise.guideContent" placeholder="引导文案">
          </Input>
        </FormItem>
        <FormItem prop="guideImg" label="广告图片（690* 420或500* 800,px）(上传或者填写):"v-if="addReadAdvertise.type==2">
            <my-upload :defaultUpload="addReadAdvertise.guideImg" @uploadList="getAddGuideImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadAdvertise.guideImg" placeholder="广告图片">
            </Input>
                <img :src="addReadAdvertise.guideImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="title" label="标题:" v-if="addReadAdvertise.type==1">
          <Input type="text" v-model="addReadAdvertise.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" v-if="addReadAdvertise.type==1">
          <Input type="text" v-model="addReadAdvertise.author" placeholder="作者">
          </Input>
        </FormItem>
        <FormItem prop="topImg" label="头条封面(上传或者填写):" v-if="addReadAdvertise.type==1">
            <my-upload :defaultUpload="addReadAdvertise.topImg" @uploadList="getAddTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadAdvertise.topImg" placeholder="头条封面">
            </Input>
                <img :src="addReadAdvertise.topImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="notopImg" label="非头条封面(上传或者填写):" v-if="addReadAdvertise.type==1">
            <my-upload :defaultUpload="addReadAdvertise.notopImg" @uploadList="getAddNotopImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadAdvertise.notopImg" placeholder="非头条封面">
            </Input>
                <img :src="addReadAdvertise.notopImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <!-- <FormItem prop="content" label="正文内容:" v-if="addReadAdvertise.type==1">
          <Input type="textarea" v-model="addReadAdvertise.content" placeholder="正文内容">
          </Input>
        </FormItem> -->
         <FormItem prop="content" label="正文内容:"  v-if="addReadAdvertise.type==1">
            <Input type="text" v-model="addWeiXinUrl" placeholder="输入微信链接" style="width:80%;"></Input>
            <Button  @click='getAddWeiXinContent' type="error">导入</Button>
            <my-wangeditor  :content="addReadAdvertise.content" @getWangEditorContent="getAddEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" v-if="addReadAdvertise.type==1">
          <Input type="text" v-model="addReadAdvertise.originUrl" placeholder="原文链接">
          </Input>
        </FormItem>
        <FormItem  label="投放开始时间/投放结束时间:">
          <DatePicker type="datetimerange" placeholder="投放开始时间/投放结束时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getAddStartEndDate" style="width: 300px" ></DatePicker>
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
     <Modal v-model="updateReadAdvertiseModel"
           title="修改阅读广告"
           :closable="false"
           :mask-closable="false"
            :width="1000"
    >
      <Form ref="updateReadAdvertise" :model="updateReadAdvertise" :label-width="100" label-position="right"  :rules="updateReadAdvertiseRules">
        <FormItem prop="type" label="广告形式:">
          <!-- <Select v-model="updateReadAdvertise.type" transfer size="large" style="width:100px" disabled>
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select> -->
          <RadioGroup v-model="updateReadAdvertise.type" type="button" >
            <Radio 
            :label="item.id" v-for="item in typeList" :value="item.id" :key="item.id"  :disabled="updateReadAdvertise.type!=item.id">
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:">
          <Input type="text" v-model="updateReadAdvertise.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadAdvertise.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="totalPrice" label="'广告预算（总额）:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadAdvertise.totalPrice"
            placeholder="广告预算（总额）">
          </InputNumber >
        </FormItem>
        <FormItem prop="guideContent" label="引导文案:" v-if="updateReadAdvertise.type==2">
          <Input type="textarea" v-model="updateReadAdvertise.guideContent" placeholder="引导文案">
          </Input>
        </FormItem>
        <FormItem prop="guideImg" label="广告图片（690* 420或500* 800,px）(上传或者填写):" v-if="updateReadAdvertise.type==2">
            <my-upload :defaultUpload="updateReadAdvertise.guideImg" @uploadList="getUpdateGuideImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadAdvertise.guideImg" placeholder="广告图片">
            </Input>
                <img :src="updateReadAdvertise.guideImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="title" label="标题:" v-if="updateReadAdvertise.type==1">
          <Input type="text" v-model="updateReadAdvertise.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" v-if="updateReadAdvertise.type==1">
          <Input type="text" v-model="updateReadAdvertise.author" placeholder="作者">
          </Input>
        </FormItem>
        <FormItem prop="topImg" label="头条封面(上传或者填写):" v-if="updateReadAdvertise.type==1">
            <my-upload :defaultUpload="updateReadAdvertise.topImg" @uploadList="getUpdateTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadAdvertise.topImg" placeholder="头条封面">
            </Input>
                <img :src="updateReadAdvertise.topImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="notopImg" label="非头条封面(上传或者填写):" v-if="updateReadAdvertise.type==1">
            <my-upload :defaultUpload="updateReadAdvertise.notopImg" @uploadList="getUpdateNotopImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadAdvertise.notopImg" placeholder="非头条封面">
            </Input>
                <img :src="updateReadAdvertise.notopImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
       <!--  <FormItem prop="content" label="正文内容:" v-if="updateReadAdvertise.type==1">
          <Input type="textarea" v-model="updateReadAdvertise.content" placeholder="正文内容">
          </Input>
        </FormItem> -->
         <FormItem prop="content" label="正文内容:"  v-show="updateReadAdvertise.type==1">
          <Input type="text" v-model="updateWeiXinUrl" placeholder="输入微信链接" style="width:80%"></input>
          <Button  @click='getUpdateWeiXinContent' type="error">导入</Button>
          <my-wangeditor :content="updateReadAdvertise.content" @getWangEditorContent="getUpdateEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" v-if="updateReadAdvertise.type==1">
          <Input type="text" v-model="updateReadAdvertise.originUrl" placeholder="原文链接">
          </Input>
        </FormItem>
        <FormItem  label="投放开始时间/投放结束时间:">
          <DatePicker type="datetimerange" placeholder="投放开始时间/投放结束时间" 
          @on-change="getUpdateStartEndDate" style="width: 300px" ></DatePicker>
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
      <Table border :columns='readAdvertiseColumns' :data='readAdvertiseList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'ReadAdvertise',
  data () {
    return {
        addWeiXinUrl:'',
        addWeiXinContent:'',
        updateWeiXinUrl:'',
        updateWeiXinContent:'',
        
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        //广告形式,1图文、2贴片
        typeParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'图文'},
          {id:2,value:'贴片'}
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
         //广告形式,1图文、2贴片
        typeList:[
          {id:1,value:'图文'},
          {id:2,value:'贴片'}
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
			addReadAdvertiseModel:false,
			addLoading:false,
			addReadAdvertiseRules: {
                name: [
                    {required: true, message: '阅读广告名称为必填项', trigger: 'blur'}
                    ]
                },
			addReadAdvertise:{
                content:'',
                guideImg:'',
                topImg:'',
                notopImg:'',
                type:1,
                unitPrice:0,
                totalPrice:0,
                consumePrice:0,
                qualityPrice:0,
                finalPrice:0,
                status:1
           
			},
			//修改参数
			updateReadAdvertiseModel:false,
			updateLoading:false,
			updateReadAdvertiseRules: {
                name: [
                    {required: true, message: '阅读广告名称为必填项', trigger: 'blur'}
                    ]
                },
			updateReadAdvertise:{
                content:'',
                type:1,
      },
      //删除参数
      deleteReadAdvertise:{},
      //列表
      readAdvertiseCateList:[],
      readAdvertiseList: [],
	  readAdvertiseColumns: [
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
          title: '阅读广告id',
          key: 'readAdvertiseId',
          align:'center'
        },
        {
          title:'阅读广告名称',
          key:'name',
          align:'center'
        },
        {
          title:'广告形式',
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
          title:'广告单价(元/阅读)',
          key:'unitPrice',
          align:'center'
        },
         {
          title:'投放开始时间/结束时间',
          sortable: true,
          align:'center',
          render:(h,params)=>{
              return h('div',[
                  h('div',params.row.startDate),
                  h('div',params.row.endDate)
              ])
          }
        },
        {
          title:'广告预算（总额）',
          key:'totalPrice',
          align:'center'
        },
        {
          title:'广告金额',
          key:'consumePrice',
          align:'center'
        },
        {
          title:'质检返款',
          key:'qualityPrice',
          align:'center'
        },
        {
          title:'实际消费',
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
          title: '操作',
          key: 'action',
          render: (h, params) => {
            var varhh1=  h('Button', {
                props: {
                  type: 'primary',
                  ghost:'',
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
              }, '修改');
            var varhh2=  h('Button', {
                props: {
                  type: 'error',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: '10px'
                },
                on: {
                  click: () => {
                     /**
                     * post发送请求
                     * $this  vue组件
                     * p.url 修改url
                     * p.title 名称
                     * p.content 请求内容
                     * p.requestObject 请求参数对象
                     * p.success 成功回调
                     */
                    this.readAdvertisePaymentRequestObject={
                      accountId:this.business.getAccount().accountId,
                      readAdvertiseId:params.row.readAdvertiseId,
                      method:7,//余额支付
                    }
                      this.axiosbusiness.post(this,
                      {url:'/finance/readAdvertisePayment',
                      title:'阅读广告支付',
                      content:'支付金额'+params.row.totalPrice+'元，确定支付？',
                      requestObject:'readAdvertisePaymentRequestObject',
                      success:()=>{
                        //成功后需要重置，
                        this.Hub.$emit('getFinance',this.business.getAccount().accountId); //Hub触发事件
                      }
                      }
                      );
                  }
                }
              }, '支付');
            var varhh3=  h('Button', {
                props: {
                  type: 'primary',
                  ghost:'',
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
              }, '文案');
            	var s=h("div","");
            //状态，1待支付、2待审核、3投放中、4暂停中、5已完成
            if(params.row.status==1){
              s=h("div",[
              varhh1,
              varhh2,
            ]);
            }else if(params.row.status==2){
                s=h("div",[
                  '待审核，审核成功后自动投放'
                ]);
            }else if(params.row.status==3){
                s=h("div",[
                  '投放中'
                ]);
            }else if(params.row.status==4){
                s=h("div",[
                  '暂停中'
                ]);
            }else if(params.row.status==5){
                s=h("div",[
                  '已完成'
                ]);
            }

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
    //增加微信内容
    getAddWeiXinContent(){
       this.axiosbusiness.get(this,{
         url:'/tool/contentByUrl?selector=%23js_content&url='+encodeURIComponent(this.addWeiXinUrl),
         data:'addWeiXinContent',
         success:(res)=>{
           this.addReadAdvertise.content=this.addWeiXinContent
           //this.$set(this.addReadAdvertise,"content", this.addWeiXinContent)
           console.log(this.addReadAdvertise)
           this.$Message.success(res.data.msg)
         }
         }) 
    },
    //修改微信内容
    getUpdateWeiXinContent(){
      this.axiosbusiness.get(this,{
         url:'/tool/contentByUrl?selector=%23js_content&url='+encodeURIComponent(this.updateWeiXinUrl),
         data:'updateWeiXinContent',
         success:(res)=>{
           this.updateReadAdvertise.content=this.updateWeiXinContent
            this.$Message.success(res.data.msg)
         }
         }) 
    },
      //获取增加的图片
    getAddGuideImg(data){
      this.addReadAdvertise.guideImg=data[0].url
    },
    getAddTopImg(data){
      this.addReadAdvertise.topImg=data[0].url
    },
    getAddNotopImg(data){
      this.addReadAdvertise.notopImg=data[0].url
    },
    //获取修改的图片
    getUpdateGuideImg(data){
      this.updateReadAdvertise.guideImg=data[0].url
    },
    getUpdateTopImg(data){
      this.updateReadAdvertise.topImg=data[0].url
    },
    getUpdateNotopImg(data){
      this.updateReadAdvertise.notopImg=data[0].url
    },

    //获取增加的编辑器内容
    getAddEditor(data){
      this.addReadAdvertise.content=data
    },
    //获取修改的编辑器内容
    getUpdateEditor(data){
      this.updateReadAdvertise.content=data
    },

    //获取增加的开始结束时间
    getAddStartEndDate(array){
        this.addReadAdvertise.startDate=this.utils.getTime(array[0])
        this.addReadAdvertise.endDate=this.utils.getTime(array[1])
    },
    //获取修改的开始结束时间
    getUpdateStartEndDate(array){
        this.updateReadAdvertise.startDate=this.utils.getTime(array[0])
        this.updateReadAdvertise.endDate=this.utils.getTime(array[1])
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
       countUrl:'/readAdvertise/count',
       listUrl:'/readAdvertise/list',
       data:'readAdvertiseList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addReadAdvertiseModel = true
      this.addReadAdvertise.content=" "
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addReadAdvertiseModel = false
        this.$refs.addReadAdvertise.resetFields()
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
    this.addReadAdvertise.accountId=JSON.parse(this.$route.params.pathParams).accountId
    this.axiosbusiness.add(this,{
      ref:'addReadAdvertise',
      url:'/readAdvertise/add',
      requestObject:'addReadAdvertise',
      loading:'addLoading',
      showModel:'addReadAdvertiseModel'
    })
    },
	 update (params) {
      this.updateReadAdvertiseModel = true
      this.updateReadAdvertise.content=" "
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/readAdvertise/load?readAdvertiseId='+params.readAdvertiseId,
         data:'updateReadAdvertise',
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateReadAdvertiseModel = false
        this.$refs.updateReadAdvertise.resetFields()
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
      ref:'updateReadAdvertise',
      url:'/readAdvertise/update',
      requestObject:'updateReadAdvertise',
      loading:'updateLoading',
      showModel:'updateReadAdvertiseModel'
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
    this.deleteReadAdvertise={
      "readAdvertiseId":params.readAdvertiseId
    };
    this.axiosbusiness.delete(this,{
      url:'/readAdvertise/delete',
      requestObject:'deleteReadAdvertise'
    })
    }
  },
   watch: {
    //当前页面参数修改动态启动
      $route (to,from){
        this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
      },
    }, 
  created () {
    this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  }
}
</script>
