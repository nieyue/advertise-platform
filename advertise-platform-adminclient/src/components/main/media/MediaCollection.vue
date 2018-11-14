<!--媒体收藏管理 -->
<template>
    <div class="body-wrap">
    
      <Table border :columns='mediaCollectionColumns' :data='mediaCollectionList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'   @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'MediaCollection',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
	    mediaCollectionList: [],
	    mediaCollectionColumns: [
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
          title: '媒体收藏id',
          minWidth:100,
          key: 'mediaCollectionId',
          align:'center'
        },
        {
          title: '媒体id',
          minWidth:100,
          key: 'mediaId',
          align:'center'
        },
        {
          title: '账户id',
          minWidth:100,
          key: 'accountId',
          align:'center'
        },
        {
          title:'修改时间',
          minWidth:100,
          key:'updateDate',
          sortable: true,
          align:'center'
        }
      ],
    }
  },
  methods: {
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
      //构造path
     let pp=JSON.stringify({
       currentPage:currentPage
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
     this.axiosbusiness.getList(this,{
       countUrl:'/mediaCollection/count',
       listUrl:'/mediaCollection/list',
       data:'mediaCollectionList'
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
  },
  mounted () {

  }
}
</script>
