<!--首页管理 -->
<template>
    <div class="body-wrap">
        <!-- 汇总栏-->
        <Row>
            <Col span="8" style="text-align:center;">
                <span style="color:#a4e0b7;font-size:6rem;width:33%;"><Icon type="ios-copy" /></span>
                <span style="display:inline-block;width:33%;">
                    <span style="display:block;font-size:1.2rem;">{{collect.waitRelease}}</span>
                    <span style="display:block;">待发布</span>
                </span>
                <span style="display:inline-block;width:33%;">
                    <span style="display:block;font-size:1.2rem;">{{collect.released}}</span>
                    <span style="display:block;">发布中</span>
                </span>
            </Col>
            <Col span="8" style="text-align:center;">
                <span style="color:#81b7d8;font-size:6rem;width:33%;"><Icon type="ios-paper" /></span>
                <span  style="display:inline-block;width:33%;">
                    <span style="display:block; font-size:1.2rem;">{{collect.completed}}</span>
                    <span style="display:block;">已完成</span>
                </span>
                <span  style="display:inline-block;width:33%;">
                    <span style="display:block;font-size:1.2rem;">{{collect.cancelled}}</span>
                    <span style="display:block;">已取消</span>
                </span>
            </Col>
            <Col span="8" style="text-align:center;">
                <span style="color:#e87676;font-size:6rem;width:50%;"><Icon type="logo-usd" /></span>
                <span  style="display:inline-block;width:50%;">
                    <span style="display:block;font-size:1.2rem;">{{collect.consume}}</span>
                    <span style="display:block;">实际消费</span>
                </span>
            </Col>
        </Row>
        <!-- 汇总栏 end-->
        <!-- 汇总图-->
        <div style="margin:6rem 0 0 0;">
            <div style="font-size:1.2rem;border-bottom:1px solid #ececec;">
                <div style="display:inline-block;width:3px;height:1rem;background-color:#4cb5ff;"></div>
                <div style="display:inline-block;color:#4cb5ff;">汇总图</div>
                <div style="display:inline-block;margin-left:37rem !important;">
                    <DatePicker type="datetimerange" placeholder="开始时间/结束时间"  format="yyyy-MM-dd HH:mm:ss"
                @on-change="getQueryDate" style="width: 300px" ></DatePicker>
                    <Button @click="search(params.startDate,params.endDate)" type="info">查询</Button>
                    <Button @click="search(new Date(new Date().getTime()-7*24*60*60*1000),new Date())" type="info">7天</Button>
                    <Button @click="search(new Date(new Date().getTime()-15*24*60*60*1000),new Date())" type="info">15天</Button>
                    <Button @click="search(new Date(new Date().getTime()-30*24*60*60*1000),new Date())" type="info">30天</Button>
                </div>
            </div>
            <div style="margin:6rem 0;">
                <Row>
                    <Col span="6" style="text-align:center;">
                        <span style="display:inline-block;">
                            <span style="display:block;">已结算</span>
                            <span style="display:block;font-size:2rem;color:#4cb5ff;">{{collect.settled}}</span>
                        </span>
                    </Col>
                    <Col span="6" style="text-align:center;">
                        <span  style="display:inline-block;">
                            <span style="display:block;">实际消费</span>
                            <span style="display:block; font-size:2rem;color:#4cb5ff;">{{collect.consume}}</span>
                        </span>
                    </Col>
                    <Col span="6" style="text-align:center;">
                        <span  style="display:inline-block;">
                            <span style="display:block;">覆盖粉丝量</span>
                            <span style="display:block; font-size:2rem;color:#4cb5ff;">{{collect.funs}}</span>
                        </span>
                    </Col>
                    <Col span="6" style="text-align:center;">
                        <span  style="display:inline-block;">
                            <span style="display:block;">实际阅读量</span>
                            <span style="display:block;font-size:2rem;color:#4cb5ff;">{{collect.readingNumber}}</span>
                        </span>
                    </Col>
                </Row>
            </div>
             <my-echarts :options="options" :config="{width:'100%',height:'500px'}"></my-echarts>
        </div>
        <!-- 汇总图 end-->

    </div>
</template>
<script>
export default {
  name: 'Collect',
  data () {
    return {
    //数据配置
    options:{},
    params:{
        startDate:new Date(),
        endDate:new Date(),
        startNum:1,//初始化个数
        currentPage:1,//当前页
        pageNum:1,//获取的第几个开始
        pageSize:10,//每页的个数
        total:0//总数
    },
    //汇总列表
    collectList:[],
    //汇总
    collect:{},
    //媒体日数据列表
    mediaDailyDataList:[],
    //图表数据
    echartsData:{
        dateList:[],
        readingNumbers:[],
        positionNumbers:[],
    }
    }
  },
  methods: {
   //查询
    search(startDate,endDate){
      this.params.currentPage=1;
      this.params.pageNum =1;
      this.selectPage(this.params.currentPage,startDate,endDate)
    },
    //分页点击
    selectPage (currentPage,startDate,endDate) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       startDate:this.utils.getTime(startDate),
       endDate:this.utils.getTime(endDate),
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getCollectList()
      this.getMediaDailyDataList(startDate,endDate)
    },
    //获取增加的预计实际时间
    getQueryDate(array){
        this.params.startDate=array[0]
        this.params.endDate=array[1]
    },
    //获取列表
   getCollectList () {
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
           this.collect=this.collectList[0];
       }
     },this.params)
    },
    //获取列表
   getMediaDailyDataList (startDate,endDate) {
     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     */
    this.params.startDate=JSON.parse(this.$route.params.pathParams).startDate
    this.params.endDate=JSON.parse(this.$route.params.pathParams).endDate
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/mediaDailyData/count',
       listUrl:'/mediaDailyData/list',
       data:'mediaDailyDataList',
       success:()=>{
           this.echartsData.dateList=this.utils.getAllDate(startDate,endDate);
           //this.echartsData.dateList=this.utils.getAllDate(this.utils.getDay(this.utils.getNDate(0)),this.utils.getDay(this.utils.getNDate(-6)));
         // console.log(this.echartsData)
               for(let j=0;j<this.echartsData.dateList.length;j++){
                   this.echartsData.readingNumbers[j]=0
                   this.echartsData.positionNumbers[j]=0
                    this.mediaDailyDataList.forEach((e)=>{
                        if(this.utils.getDay(e.createDate)==this.utils.getDay(this.echartsData.dateList[j])){
                            this.echartsData.readingNumbers[j]=e.totalReadingNumber
                            this.echartsData.positionNumbers[j]=e.totalReadingNumber
                        }  
                    })
               }
           this.initData(this.echartsData);
       }
     },this.params)
    },
    //初始化
    initData(echartsData){
    this.options={
       /*  title: {
            text: ''
        }, */
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            bottom:0,
            data:['按阅读计费','按位置计费']
        },
        toolbox: {
            feature: {
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar','stack','tiled']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap:  false,
        //data: list.createDates  
        data:echartsData.dateList 
        },
        yAxis: [
            {
                type: 'value',
                // scale: true,
                name: '数量'
                ,min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name:'按阅读计费',
                type:'line',
                data: echartsData.readingNumbers
            },
            {
                name:'按位置计费',
                type:'line',
                data: echartsData.positionNumbers
            },
            ]
        }
    }
  },
  created () {
      this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage,this.utils.getNDate(-6),new Date())
  },
  mounted () {
  }
}
</script>

