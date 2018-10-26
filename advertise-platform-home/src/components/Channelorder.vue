<!--媒体接单 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
  <!--分割顶部-->
  <div style="margin-top:82px;"></div>
  <!---分割顶部 end -->
   <!--媒体接单banner-->
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide">
        <img src="/static/channelorder-banner1.png" style="height:460px;width:100%;min-width:1200px;" alt="">
        <div style="position:absolute;left:12rem;top:10rem;color:#fff;font-size:3rem;" >广告类型、广告位置和投放时间任意选择</div>
        <div style="position:absolute;left:12rem;top:15rem;color:#fff;font-size:2.2rem;" >充分利用剩余流量持续变现 随时提现</div>
      </div>
    </div>
  </div>
  <!--媒体接单banner end-->
  <!--步骤条 -->
  <div style="width:100%;background-color:#fff;margin:50px 0;">
    <div style="font-size:3rem;text-align:center;">
     接单流程
    </div>
    <Steps :current="currentstep" style="margin:auto;width:1200px;">
          <Step  content="注册登录媒体主"></Step>
          <Step  content="添加接单公众号"></Step>
          <Step  content="媒体主自主选择广告"></Step>
          <Step  content="媒体执行推广并提交链接"></Step>
          <Step  content="申请结算并等待平台验收"></Step>
          <Step  content="订单完成"></Step>
    </Steps>
  </div>
  <!--步骤条 end-->
  <div style="height:10px;background-color:#ececec;width:100%;"></div>
   <!--接单优势-->
  <div style="width:100%;background-color:#fff;margin-top:50px;">
    <div style="font-size:3rem;text-align:center;">
     接单优势
    </div>
    <div style="width:100%;height:100%;margin:auto;text-align:center;">
      <div style="height:450px;width:500px;border-radius:5px;display:inline-block; vertical-align:top;text-align:left;">
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">自由接单</div>
           <div style="font-size:1rem;margin:5px 0;">自由设置广告位置，投放时间，想推就推</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">广告多样</div>
           <div style="font-size:1rem;margin:5px 0;">海量广告资源，品类丰富，形式多样</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">操作简单</div>
           <div style="font-size:1rem;margin:5px 0;">广告无需审核，推广文案一键复制，直接推广</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">持续变现</div>
           <div style="font-size:1rem;margin:5px 0;">充分利用剩余流量，可持续变现，增加收益</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">成交担保</div>
           <div style="font-size:1rem;margin:5px 0;">平台担保交易，7*24小时服务</div>
        </div>
      </div>
      <div style="margin-left:10px;height:450px;width:660px;background:url('/static/channelorder-summary.png') no-repeat 0 0;border-radius:5px;display:inline-block;">
      </div>
    </div>
  </div>
  <!--接单优势 end-->

<footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar';
  export default {
    name: 'Channelorder',
    components:{
      TopBar,
      FooterBar
    },
    created(){
      console.log(111)
      this.dynamicstep();
    },
    data () {
      return {
        //当前步骤
        currentstep:0
      }
    },
    methods: {
      dynamicstep(){
        setTimeout(()=>{
          this.currentstep++;
          if(this.currentstep>5){
            this.currentstep=0;
          }
          this.dynamicstep();
        },1000)
      }

    
    },
    mounted(){
       var Swiper=this.Swiper;
       var mySwiper = new Swiper('.swiper-container', {
          loop:false
        })
    }
  }
</script>
<style lang="less">
@import "Channelorder.less";
</style>
