<!--微信推广 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
   <!--分割顶部-->
  <div style="margin-top:82px;"></div>
  <!---分割顶部 end -->
   <!--微信推广banner-->
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide">
        <img src="/static/weixinspread-banner1.png" style="height:460px;width:100%;min-width:1200px;" alt="">
        <div style="position:absolute;left:12rem;top:10rem;color:#fff;font-size:3rem;" >以效果为导向 按真实阅读计费 转化成本可控风险更低</div>
        <div style="position:absolute;left:12rem;top:15rem;color:#fff;font-size:2.2rem;" >实时监测数据 科学严谨的算法 保障每个阅读真实有效</div>
        <div style="position:absolute;left:50rem;top:20rem;">
             <router-link to="/login"><Button type="default" ghost size="large" style="width:200px;height:50px;" >立即推广</Button></router-link>
        </div>
      </div>
    </div>
  </div>
  <!--微信推广banner end-->
   <!--步骤条 -->
  <div style="width:100%;background-color:#fff;margin:50px 0;">
    <div style="font-size:3rem;text-align:center;">
     推广流程
    </div>
    <Steps :current="currentstep" style="margin:auto;width:1200px;">
          <Step  content="注册登录广告主"></Step>
          <Step  content="推广条件设置及添加推广文案"></Step>
          <Step  content="平台审核"></Step>
          <Step  content="媒体主接单并执行"></Step>
          <Step  content="平台质检验收"></Step>
          <Step  content="广告完成"></Step>
    </Steps>
  </div>
  <!--步骤条 end-->
  <div style="height:10px;background-color:#ececec;width:100%;"></div>
<!--微信推广形式-->
  <div style="width:100%;background-color:#fff;margin-top:50px;">
    <div style="font-size:3rem;text-align:center;">
     推广形式
    </div>
    <div style="width:100%;text-align:center;height:100%;margin:auto;">
      <div style="height:446px;width:388px;border-radius:5px;display:inline-block;border:1px solid #ececec;">
        <div style="color:#000;padding-top:100px;">
           <div style="width:100px;height:100px;margin:15px;border:1px solid #cceaff;display:inline-block;border-radius:100%;"><Icon type="ios-image" style="font-size:3.5rem;line-height:100px;color: #4cb5ff;" /></div>
           <div style="font-size:1.5rem;margin:0 0 15px 0;">图文广告</div>
           <div style="font-size:1rem;margin:5px 10px;text-align:left;">以标题、图片、文案等编辑制作成广告物料，直接推送给粉丝，快速获得大量曝光，同时锁定潜在用户</div>
           <div style="margin:40px 0;">
              <router-link to="/login"><Button type="primary" ghost>立即推广</Button></router-link>
           </div>
        </div>
      </div>
      <div style="height:446px;width:388px;border-radius:5px;display:inline-block;border:1px solid #ececec;vertical-align:top;">
        <div style="color:#000;padding-top:100px;">
          <div style="width:100px;height:100px;margin:15px;border:1px solid #cceaff;display:inline-block;border-radius:100%;"><Icon type="ios-photos" style="font-size:3.5rem;line-height:100px;color: #4cb5ff;" /></div>
           <div style="font-size:1.5rem;margin:0 0 15px 0;">贴片广告</div>
           <div style="font-size:1rem;margin:5px 10px;text-align:left;">以图片、广告语、联系方式（二维码）等设计制作成大尺寸广告图片，展示在文章顶部或底部，快速获得海量曝光，锁定潜在用户</div>
           <div  style="margin:20px 0;">
              <router-link to="/login"><Button type="primary" ghost>立即推广</Button></router-link>
           </div>
        </div>
      </div>
      <div style="height:446px;width:388px;border-radius:5px;display:inline-block;border:1px solid #ececec;">
        <div style="color:#000;padding-top:100px;text-align:center;">
          <div style="width:100px;height:100px;margin:15px;border:1px solid #cceaff;display:inline-block;border-radius:100%;"><Icon type="ios-paper-plane" style="font-size:3.5rem;line-height:100px;color: #4cb5ff;" /></div>
           <div style="font-size:1.5rem;margin:0 0 15px 0;">选号直投</div>
           <div style="font-size:1rem;margin:5px 10px;text-align:left;">海量优质媒体，一手价格，平台担保交易，可按需自主选择媒体投放，网红、意见领袖等大号接单几率更高</div>
           <div  style="margin:40px 0;">
              <router-link to="/login"><Button type="primary" ghost>立即推广</Button></router-link>
           </div>
        </div>
      </div>
    </div>
  </div>
  <!--微信推广形式 end-->
   <!--推广优势-->
  <div style="width:100%;background-color:#fff;margin-top:50px;">
    <div style="font-size:3rem;text-align:center;">
    推广优势
    </div>
    <div style="width:100%;height:100%;margin:auto;text-align:center;">
      <div style="height:450px;width:500px;border-radius:5px;display:inline-block; vertical-align:top;text-align:left;">
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">自动投放</div>
           <div style="font-size:1rem;margin:5px 0;">一站式全自动匹配海量媒体，自动投放省心省力</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">灵活推广</div>
           <div style="font-size:1rem;margin:5px 0;">自主设置投放周期、预算、单价，粉丝比例等条件，推广策略灵活掌控</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">费用透明</div>
           <div style="font-size:1rem;margin:5px 0;">按阅读计费，数据公开费用消耗透明</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">成本可控</div>
           <div style="font-size:1rem;margin:5px 0;">按真实阅读计费，成本可控，风险低</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">真实流量</div>
           <div style="font-size:1rem;margin:5px 0;">实时跟踪数据，科学严谨算法，保障真量投放</div>
        </div>
        <div style="color:#000;margin:20px 0;">
           <div style="font-size:1.2rem;margin:5px 0;color:#4cb5ff">成交担保</div>
           <div style="font-size:1rem;margin:5px 0;">平台担保交易，7*24小时服务</div>
        </div>
      </div>
      <div style="margin-left:10px;height:450px;width:660px;background:url('/static/weixinspread-summary.png') no-repeat 0 0;border-radius:5px;display:inline-block;">
      </div>
    </div>
  </div>
  <!--推广优势 end-->

  <footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar';
  export default {
    name: 'Weixinspread',
    components:{
      TopBar,
      FooterBar
    },
    created(){
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
@import "Weixinspread.less";
</style>
