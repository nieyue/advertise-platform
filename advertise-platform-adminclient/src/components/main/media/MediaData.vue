<!--媒体详细数据管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <div class="search-wrap">
        <Input v-model="params.mediaId" class="search-wrap-input" placeholder="媒体id"></Input>
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
        <DatePicker type="date" placeholder="创建时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getParamsCreateDate" style="width: 200px" ></DatePicker>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
      <Table border :columns='mediaDataColumns' :data='mediaDataList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'MediaData',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
    //文章位置,1头条，2二条，3三条，4四条，5五条，6六条
        positionList:[
          {id:1,value:'头条'},
          {id:2,value:'二条'},
          {id:3,value:'三条'},
          {id:4,value:'四条'},
          {id:5,value:'五条'},
          {id:6,value:'六条'}
          ],
      //列表
      mediaDataList: [],
	  mediaDataColumns: [
        {
          title: '序号',
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '媒体详细数据id',
          key: 'mediaDataId',
          align:'center'
        },
        {
          title:'账户id',
          key:'accountId',
          align:'center'
        },
         {
          title:'媒体id',
          key:'mediaId',
          align:'center'
        },
        {
          title:'文章标题',
          key:'name',
          align:'center'
        },
        {
          title:'文章位置',
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
          title:'阅读数',
          key:'readingNumber',
          align:'center'
        },
        {
          title:'点赞数',
          key:'pointNumber',
          align:'center'
        },
        {
          title:'创建时间',
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
        	title:'修改时间',
          key:'updateDate',
          sortable: true,
          align:'center'
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
     //查询时间
    getParamsCreateDate(array){
        this.params.createDate=array;
    },
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       mediaId:JSON.parse(this.$route.params.pathParams).mediaId,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
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
    this.params.mediaId=JSON.parse(this.$route.params.pathParams).mediaId
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/mediaData/count',
       listUrl:'/mediaData/list',
       data:'mediaDataList'
     },this.params)
    },
	
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
