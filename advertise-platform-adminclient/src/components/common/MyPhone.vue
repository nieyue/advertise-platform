<!--手机页组件 -->
<template>
    <div class="phone-wrap">
        <!--滑动-->
    <Slider v-model="sliderValue" show-input v-if="isShowSlider" :min='5' :max="20"></Slider>
    <!-- 手机实体-->
        <div class="phone-bg-wrap" :style="{height:configSize.outerheight+'px',width:configSize.outerwidth+'px'}" >
    <!-- 手机背景-->
            <div class="phone-bg" :style="{height:configSize.outerheight+'px',width:configSize.outerwidth+'px'}">
            <img class="phone-bg-img" src="/static/devices-sprite.jpg"/>
            </div>
    <!-- 手机内嵌内容-->
            <div class="phone-white" :style="{height:configSize.innerheight+'px',width:configSize.innerwidth+'px',top:configSize.innertop+'px',marginLeft:configSize.innermarginleft+'px'}">
                <img class="phone-white-img" :src="currentWeixinImg">

            </div>
    <!-- 手机头-->
            <div class="phone-title" v-if="isShowTitle" :style="{width:configSize.innerwidth+'px',top:configSize.innertop+'px',marginLeft:configSize.innermarginleft+'px'}">
                <img class="phone-title-img" src="/static/ios-statusbar.png">
            </div>
    <!-- 手机底部公众号菜单-->
            <div class="phone-mpmenu" v-if="model==2" :style="{width:configSize.mpmenuwidth+'px',top:configSize.mpmenutop+'px',marginLeft:configSize.mpmenumarginleft+'px'}">
                <div class="phone-mpmenu-toggle" :style="{height:configSize.mpmenuh+'px',lineHeight:configSize.mpmenuh+'px',fontSize:configSize.mpmenufontsize+'px'}">
                    &nbsp;
                </div>
                <div class="phone-mpmenu-button1" :style="{height:configSize.mpmenuh+'px',lineHeight:configSize.mpmenuh+'px',fontSize:configSize.mpmenufontsize+'px'}">
                    <Icon type="plus-round"></Icon>
                </div>
                <div class="phone-mpmenu-button2" :style="{height:configSize.mpmenuh+'px',lineHeight:configSize.mpmenuh+'px',fontSize:configSize.mpmenufontsize+'px'}">
                    <Icon type="plus-round"></Icon>
                </div>
                <div class="phone-mpmenu-button3" :style="{height:configSize.mpmenuh+'px',lineHeight:configSize.mpmenuh+'px',fontSize:configSize.mpmenufontsize+'px'}">
                    <Icon type="plus-round"></Icon>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  name: 'MyPhone',
   props:{
      //父组件传默认的值,string
      //手机图的大小,默认比例为1
      size:{
          default:1,
          type:Number,
          required:false
      },
    //模式，默认1展示轮播，2自定义微信菜单按钮
      model:{
          default:1,
          type:Number,
          required:false
      },
    //手机类型，默认1ios，2安卓，3wp
      phoneType:{
          default:1,
          type:Number,
          required:false
      },
      //定时调度，默认2s轮播，如果为小于等于0，则不轮播显示第一张
      intervalTime:{
          default:2,
          type:Number,
          required:false
      },
      //数据列表
      weixinImg:{
          default:function () {
              return [
                    '/static/weixinstart.png',
                    '/static/weixin1.png',
                    '/static/weixin2.png',
                    '/static/weixin3.png',
                    '/static/weixin4.png',
            ]
        },
          type:Array,
          required:false
      },
       //默认隐藏滑块
      isShowSlider:{
          default:true,
          type:Boolean,
          required:false
      },
      //微信公众号菜单
      weixinMpMenuImg:{
          default:'/static/weixinmpmenu.png',
          type:String,
          required:false
      },

  },
  data () {
    return {
        //配置图显示大小，不变的值
        baseConfigSize:{
            outerheight:750,
            outerwidth:450,

            innerwidth:300,
            innertop:75,
            innermarginleft:75,
            innerheight:568,

            mpmenuwidth:300,
            mpmenutop:600,
            mpmenumarginleft:75,
            mpmenutogglewidth:12,
            mpmenubuttonwidth:29,
            mpmenuh:43,
            mpmenufontsize:16


        },
        //配置图显示大小，动态的值
        configSize:{
            outerheight:750,
            outerwidth:450,

            innerwidth:300,
            innertop:75,
            innermarginleft:75,
            innerheight:568,

            mpmenuwidth:300,
            mpmenutop:600,
            mpmenumarginleft:75,
            mpmenuh:43,
            mpmenufontsize:16

        },
       
        //真实的大小
        sliderValue:this.size*8,
        isShowTitle:false,
        currentWeixinImg:this.weixinImg[0]
    }
  },
  methods: {
      //轮播
      intervalWeixinImg(){
          let number=0;
          setInterval(()=>{
          number++;
          if(number>=this.weixinImg.length){
              number=0;
          }
          let url=this.weixinImg[number];
            this.currentWeixinImg=url
          },this.intervalTime*1000)
      }
  },
  watch:{
      sliderValue:{
          handler(n,o){
          //动态修改图的大小  
          for(let cs  in this.baseConfigSize){ 
         this.configSize[cs]=this.baseConfigSize[cs]*this.sliderValue/10;
        }    
          }
      }
  },
  created () {
      //初始化整体图大小
      for(let cs  in this.configSize){ 
         this.configSize[cs]=this.configSize[cs]*this.sliderValue/10;
       // console.log(this.configSize[cs]*this.size)   
        }
      //初始化模式，模式为1切定时大于0就轮播
      if(this.model==1&&this.intervalTime>0){
          this.intervalWeixinImg();
      }
      //模式等于2，显示微信公众号页面
      if(this.model==2){
          this.currentWeixinImg=this.weixinMpMenuImg
      }
  },
  mounted () {

  }
}
</script>
<style lang="less" scoped>
/**
*手机整体
*/
.phone-wrap{
display: inline-block;
}
//实体
.phone-bg-wrap{
    display: inline-block;
    height:750px;
    width:450px;
    position: relative;
    left: 0;
    top: 0;
    //手机背景图片
    .phone-bg{
        position: absolute;
        left: 0;
        top: 0;
        height: 750px;
        width:450px;
        overflow: hidden;
        .phone-bg-img{
            height: auto;
            width:100%;
        }
    }
    //手机背景白色
    .phone-white{
        position: absolute;
        left: 0;
        top: 75px;
        height: 568px;
        width:300px;
         margin-left:75px;
        background-color:#fff;
        .phone-white-img{
            height: 100%;
            width:100%;
        }
    }
    //手机title
    .phone-title{
        position: absolute;
        left: 0;
        top: 75px;
        height: auto;
        width:300px;
        margin-left:75px;
        overflow: hidden;
        .phone-title-img{
            height: auto;
            width:100%;
        }
    }
    //手机底部公众号菜单
    .phone-mpmenu{
        position: absolute;
        left: 0;
        top: 600px;
        height: auto;
        width:300px;
        margin-left:75px;
        font-size:0;
        text-align: center;
        vertical-align: middle;
        .phone-mpmenu-toggle{
            line-height: 43px;
            font-size:16px;
            display: inline-block;
            height: 43px;
            width:12%;
        }
        .phone-mpmenu-button1{
            line-height: 43px;
            font-size:16px;
            display: inline-block;
            height: 43px;
            width:29%;
            border-left:1px solid #ececec;
            background-color:#fff;
        }
        .phone-mpmenu-button2{
            line-height: 43px;
            font-size:16px;
            display: inline-block;
            height: 43px;
            width:29%;
            border-left:1px solid #ececec;
            background-color:#fff;
        }
        .phone-mpmenu-button3{
            line-height: 43px;
            font-size:16px;
            display: inline-block;
            height: 43px;
            width:29%;
            border-left:1px solid #ececec;
            background-color:#fff;
        }
    }
}

</style>

