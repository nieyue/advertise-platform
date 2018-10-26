<!--汇总管理 -->
<template>
    <div class="body-wrap">
      <Card v-if="!updateCollect.collectId" style="text-align:center">暂无</Card>
      <Card v-if="updateCollect.collectId" :bordered="true">
            <p slot="title">汇总</p>
            <p>
              <span>待发布：</span>
              <span v-text="updateCollect.waitRelease"></span>
            </p>
            <p>
              <span>发布中：</span>
              <span v-text="updateCollect.released"></span>
            </p>
            <p>
              <span>已完成：</span>
              <span v-text="updateCollect.completed"></span>
            </p>
            <p>
              <span>已取消：</span>
              <span v-text="updateCollect.cancelled"></span>
            </p>
            <p>
              <span>已结算：</span>
              <span v-text="updateCollect.settled"></span>
            </p>
            <p>
              <span>覆盖粉丝数：</span>
              <span v-text="updateCollect.funs"></span>
            </p>
            <p>
              <span>实际阅读量：</span>
              <span v-text="updateCollect.readingNumber"></span>
            </p>
            <p>
              <span>实际消费：</span>
              <span v-text="updateCollect.consume"></span>
            </p>
            <p>
              <span>实际收入：</span>
              <span v-text="updateCollect.income"></span>
            </p>
            <p>
              <span>创建时间：</span>
              <span v-text="updateCollect.createDate"></span>
            </p>
            <p>
              <span>更新时间：</span>
              <span v-text="updateCollect.updateDate"></span>
            </p>
      </Card>
    </div>
</template>
<script>
export default {
  name: 'Collect',
  data () {
    return {
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
		//修改参数
        updateCollectModel:false,
        updateLoading:false,
        updateCollectRules: {
            },
        updateCollect:{ },
    }
  },
  methods: {
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
       countUrl:'/collect/count',
       listUrl:'/collect/list',
       data:'collectList',
       success:()=>{
         this.updateCollect=this.collectList[0];
       }
     },this.params)
    }
  },
  created () {
    this.getList();
  },
  mounted () {

  }
}
</script>
