<!--媒体日数据管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
       <!-- <Button type='primary'  @click='add'>增加媒体日数据</Button>   -->
      <div class="search-wrap">
        <Input v-model="params.mediaId" class="search-wrap-input" placeholder="媒体id"></Input>
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
         <DatePicker type="date" placeholder="创建时间"  format="yyyy-MM-dd HH:mm:ss"
            @on-change="getParamsCreateDate" style="width: 200px" ></DatePicker>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
      <Table border :columns='mediaDailyDataColumns' :data='mediaDailyDataList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'MediaDailyData',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },

      //列表
      mediaDailyDataList: [],
	  mediaDailyDataColumns: [
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
          title: '媒体日数据id',
          minWidth:100,
          key: 'mediaDailyDataId',
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
          title:'文章发布数',
          minWidth:100,
          key:'articleNumber',
          align:'center'
        },
        {
          title:'top阅读数',
          minWidth:100,
          key:'topReadingNumber',
          align:'center'
        },
        {
          title:'总阅读数',
          minWidth:100,
          key:'totalReadingNumber',
          align:'center'
        },
        {
          title:'平均阅读数',
          minWidth:100,
          key:'avgReadingNumber',
          align:'center'
        },
        {
            title:'头条阅读数',
            minWidth:100,
            key:'top1ReadingNumber',
            align:'center'
        },
        {
          title:'点赞数',
          minWidth:100,
          key:'pointNumber',
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
       countUrl:'/mediaDailyData/count',
       listUrl:'/mediaDailyData/list',
       data:'mediaDailyDataList'
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
