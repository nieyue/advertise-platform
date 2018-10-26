<!--Echarts组件 -->
<template>
    <div>
     <div id="myEcharts" ref="myEcharts" :style="{width: config2.width, height:config2.height}"></div>
    </div>
</template>
<script>
export default {
  name: 'MyEcharts',
   props:{
      //父组件传默认的值,Object 
      options:{
          type:Object,
          required:false
      },
      config:{
          type:Object,
          required:false
      }
  },
  data () {
    return {
        echartsId:"myEcharts"+parseInt(Math.random()*100),
        myEcharts:null,
        config2:{
            width:'300px',
            height:'300px',

        }
    }
  },
  methods: {
      init(echartsId,options){
            // 基于准备好的dom，初始化echarts实例
            this.$refs.myEcharts.id=echartsId;
        this.myEcharts = this.echarts.init(this.$refs.myEcharts);
        // 指定图表的配置项和数据
         options=!options||options.series==null?{
            tooltip: {},
            legend: {
                bottom:0,
                data:['按阅读计费','按位置计费']
            },
            xAxis: {
                data:this.utils.getAllDate(this.utils.getDay(this.utils.getNDate(0)),this.utils.getDay(this.utils.getNDate(-6)))
            },
            yAxis: {},
            series: [
                {
                name: '按阅读计费',
                type: 'line',
                data: [0, 0, 0, 0, 0, 0, 0]
                },
                {
                name: '按位置计费',
                type: 'line',
                data: [0, 0, 0, 0, 0, 0, 0]
                },
            ]
        }:options;
        // 使用刚指定的配置项和数据显示图表。
        this.myEcharts.setOption(options);
        }

  },
  created () {
     this.config2=this.config||this.config2
  },
  mounted () {
       if(!this.myEcharts){
            this.init(this.echartsId,this.options) 
        }
  },
    watch: { 
        //获取父组件的数据 
     options: {  
    　　handler(newValue, oldValue) { 
       // console.log(newValue)
        //console.log(oldValue)
       // if(!this.myEcharts){
            this.init(this.echartsId,newValue) 
       // }
        },
    　　deep: true  
    },
    config:{
     handler(newValue, oldValue) { 
        // console.log(newValue)
        //console.log(oldValue)
       // console.log(this.config2)
       this.config2=newValue||this.config2
        },
    　　deep: true    
    } 
    },
}
</script>
<style lang="less" scoped>


</style>

