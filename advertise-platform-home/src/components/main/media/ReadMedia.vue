<!--阅读媒体管理 -->
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
        <Button type='primary'  @click='add' style="float:right;" shape="circle" icon="md-add" size="large">增加阅读媒体</Button>
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
        <FormItem prop="type" label="广告形式:">
          <!-- <Select v-model="addReadMedia.type" transfer size="large" style="width:100px">
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select> -->
          <RadioGroup v-model="addReadMedia.type" type="button">
            <Radio style="" 
            :label="item.id" v-for="item in typeList" :value="item.id" :key="item.id"  >
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:">
          <Input type="text" v-model="addReadMedia.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadMedia.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="totalPrice" label="'广告预算（总额）:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="addReadMedia.totalPrice"
            placeholder="广告预算（总额）">
          </InputNumber >
        </FormItem>
        <FormItem prop="guideContent" label="引导文案:"v-if="addReadMedia.type==2">
          <Input type="textarea" v-model="addReadMedia.guideContent" placeholder="引导文案">
          </Input>
        </FormItem>
        <FormItem prop="guideImg" label="广告图片（690* 420或500* 800,px）(上传或者填写):"v-if="addReadMedia.type==2">
            <my-upload :defaultUpload="addReadMedia.guideImg" @uploadList="getAddGuideImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadMedia.guideImg" placeholder="广告图片">
            </Input>
                <img :src="addReadMedia.guideImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="title" label="标题:" v-if="addReadMedia.type==1">
          <Input type="text" v-model="addReadMedia.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" v-if="addReadMedia.type==1">
          <Input type="text" v-model="addReadMedia.author" placeholder="作者">
          </Input>
        </FormItem>
        <FormItem prop="topImg" label="头条封面(上传或者填写):" v-if="addReadMedia.type==1">
            <my-upload :defaultUpload="addReadMedia.topImg" @uploadList="getAddTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadMedia.topImg" placeholder="头条封面">
            </Input>
                <img :src="addReadMedia.topImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="notopImg" label="非头条封面(上传或者填写):" v-if="addReadMedia.type==1">
            <my-upload :defaultUpload="addReadMedia.notopImg" @uploadList="getAddNotopImg"></my-upload> 
            <div>
                <Input type="text" v-model="addReadMedia.notopImg" placeholder="非头条封面">
            </Input>
                <img :src="addReadMedia.notopImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <!-- <FormItem prop="content" label="正文内容:" v-if="addReadMedia.type==1">
          <Input type="textarea" v-model="addReadMedia.content" placeholder="正文内容">
          </Input>
        </FormItem> -->
         <FormItem prop="content" label="正文内容:"  v-if="addReadMedia.type==1">
          <my-wangeditor  :content="addReadMedia.content" @getWangEditorContent="getAddEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" v-if="addReadMedia.type==1">
          <Input type="text" v-model="addReadMedia.originUrl" placeholder="原文链接">
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
     <Modal v-model="updateReadMediaModel"
           title="修改阅读媒体"
           :closable="false"
           :mask-closable="false"
            :width="1000"
    >
      <Form ref="updateReadMedia" :model="updateReadMedia" :label-width="100" label-position="right"  :rules="updateReadMediaRules">
        <FormItem prop="type" label="广告形式:">
          <!-- <Select v-model="updateReadMedia.type" transfer size="large" style="width:100px" disabled>
              <Option v-for="item in typeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select> -->
          <RadioGroup v-model="updateReadMedia.type" type="button" >
            <Radio 
            :label="item.id" v-for="item in typeList" :value="item.id" :key="item.id"  :disabled="updateReadMedia.type!=item.id">
                {{item.value}}
            </Radio>
        </RadioGroup>
        </FormItem>
        <FormItem prop="name" label="广告名称:">
          <Input type="text" v-model="updateReadMedia.name" placeholder="广告名称">
          </Input>
        </FormItem>
        <FormItem prop="unitPrice" label="广告单价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadMedia.unitPrice"
            placeholder="广告单价">
          </InputNumber >
        </FormItem>
        <FormItem prop="totalPrice" label="'广告预算（总额）:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01"  style="width:180px" v-model="updateReadMedia.totalPrice"
            placeholder="广告预算（总额）">
          </InputNumber >
        </FormItem>
        <FormItem prop="guideContent" label="引导文案:" v-if="updateReadMedia.type==2">
          <Input type="textarea" v-model="updateReadMedia.guideContent" placeholder="引导文案">
          </Input>
        </FormItem>
        <FormItem prop="guideImg" label="广告图片（690* 420或500* 800,px）(上传或者填写):" v-if="updateReadMedia.type==2">
            <my-upload :defaultUpload="updateReadMedia.guideImg" @uploadList="getUpdateGuideImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadMedia.guideImg" placeholder="广告图片">
            </Input>
                <img :src="updateReadMedia.guideImg"  style='height:100px;width:160px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="title" label="标题:" v-if="updateReadMedia.type==1">
          <Input type="text" v-model="updateReadMedia.title" placeholder="标题">
          </Input>
        </FormItem>
        <FormItem prop="author" label="作者:" v-if="updateReadMedia.type==1">
          <Input type="text" v-model="updateReadMedia.author" placeholder="作者">
          </Input>
        </FormItem>
        <FormItem prop="topImg" label="头条封面(上传或者填写):" v-if="updateReadMedia.type==1">
            <my-upload :defaultUpload="updateReadMedia.topImg" @uploadList="getUpdateTopImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadMedia.topImg" placeholder="头条封面">
            </Input>
                <img :src="updateReadMedia.topImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="notopImg" label="非头条封面(上传或者填写):" v-if="updateReadMedia.type==1">
            <my-upload :defaultUpload="updateReadMedia.notopImg" @uploadList="getUpdateNotopImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateReadMedia.notopImg" placeholder="非头条封面">
            </Input>
                <img :src="updateReadMedia.notopImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
       <!--  <FormItem prop="content" label="正文内容:" v-if="updateReadMedia.type==1">
          <Input type="textarea" v-model="updateReadMedia.content" placeholder="正文内容">
          </Input>
        </FormItem> -->
         <FormItem prop="content" label="正文内容:"  v-show="updateReadMedia.type==1">
          <my-wangeditor :content="updateReadMedia.content" @getWangEditorContent="getUpdateEditor"></my-wangeditor>
        </FormItem> 
        <FormItem prop="originUrl" label="原文链接:" v-if="updateReadMedia.type==1">
          <Input type="text" v-model="updateReadMedia.originUrl" placeholder="原文链接">
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
      <Table border :columns='ReadMediaColumns' :data='ReadMediaList' ref='table' size="small"></Table>
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
			addReadMediaModel:false,
			addLoading:false,
			addReadMediaRules: {
                name: [
                    {required: true, message: '阅读媒体名称为必填项', trigger: 'blur'}
                    ]
                },
			addReadMedia:{
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
			updateReadMediaModel:false,
			updateLoading:false,
			updateReadMediaRules: {
                name: [
                    {required: true, message: '阅读媒体名称为必填项', trigger: 'blur'}
                    ]
                },
			updateReadMedia:{
                type:1,
      },
      //删除参数
      deleteReadMedia:{},
      //列表
      ReadMediaCateList:[],
      ReadMediaList: [],
	  ReadMediaColumns: [
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
          title: '阅读媒体id',
          key: 'ReadMediaId',
          align:'center'
        },
        {
          title:'阅读媒体名称',
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
              }, '查看');
            var varhh2=  h('Button', {
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
              }, '设置');
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
			s=h("div",[
              varhh1,
              varhh2,
              varhh3,
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
    getAddGuideImg(data){
      this.addReadMedia.guideImg=data[0].url
    },
    getAddTopImg(data){
      this.addReadMedia.topImg=data[0].url
    },
    getAddNotopImg(data){
      this.addReadMedia.notopImg=data[0].url
    },
    //获取修改的图片
    getUpdateGuideImg(data){
      this.updateReadMedia.guideImg=data[0].url
    },
    getUpdateTopImg(data){
      this.updateReadMedia.topImg=data[0].url
    },
    getUpdateNotopImg(data){
      this.updateReadMedia.notopImg=data[0].url
    },

    //获取增加的编辑器内容
    getAddEditor(data){
      this.addReadMedia.content=data
    },
    //获取修改的编辑器内容
    getUpdateEditor(data){
      this.updateReadMedia.content=data
    },

    //获取增加的开始结束时间
    getAddStartEndDate(array){
        this.addReadMedia.startDate=this.utils.getTime(array[0])
        this.addReadMedia.endDate=this.utils.getTime(array[1])
    },
    //获取修改的开始结束时间
    getUpdateStartEndDate(array){
        this.updateReadMedia.startDate=this.utils.getTime(array[0])
        this.updateReadMedia.endDate=this.utils.getTime(array[1])
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
       countUrl:'/ReadMedia/count',
       listUrl:'/ReadMedia/list',
       data:'ReadMediaList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addReadMediaModel = true
      this.addReadMedia.content=" "
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
    this.addReadMedia.accountId=JSON.parse(this.$route.params.pathParams).accountId
    this.axiosbusiness.add(this,{
      ref:'addReadMedia',
      url:'/ReadMedia/add',
      requestObject:'addReadMedia',
      loading:'addLoading',
      showModel:'addReadMediaModel'
    })
    },
	 update (params) {
      this.updateReadMediaModel = true
      this.updateReadMedia.content=" "
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/ReadMedia/load?ReadMediaId='+params.ReadMediaId,
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
      url:'/ReadMedia/update',
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
      "ReadMediaId":params.ReadMediaId
    };
    this.axiosbusiness.delete(this,{
      url:'/ReadMedia/delete',
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
