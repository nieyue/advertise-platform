<!--首页 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
  <!--分割顶部-->
  <div style="margin-top:82px;"></div>
  <!---分割顶部 end -->
  <!--首页banner-->
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide">
        <img src="/static/banner1.png" style="height:600px;width:100%;min-width:1200px;" alt="">
        <div style="position:absolute;left:12rem;top:10rem;color:#fff;font-size:3rem;" >优质媒体资源 覆盖亿级粉丝用户</div>
        <div style="position:absolute;left:12rem;top:15rem;color:#fff;font-size:2.2rem;" >按需定制推广方案 智能匹配媒体精准投放 转化成本更低</div>
        <div style="width:100%;height:128px;position:absolute;left:0;bottom:0;color:#fff;font-size:2rem;background-color:#fff;opacity:0.2" >
        </div>
        <div style="width:100%;height:128px;position:absolute;left:0;bottom:0;color:#fff;font-size:2rem;" >
           <Row>
              <Col span="6" style="text-align:center;">
                <div style="display:inline-block;height:128px;margin-top:22px;">
                  <div style="font-size:24px">精准投放</div>
                  <div style="font-size:16px">按需智能匹配 精准锁定目标用户</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">阅读计费</div>
                  <div style="font-size:16px">成本可控 投放风险更低</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">真实流量</div>
                  <div style="font-size:16px">双重防作弊机制 拒绝假号假量</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">高效曝光</div>
                  <div style="font-size:16px">实时自动投放 快速覆盖上亿用户</div>
                </div>
              </Col>
          </Row>
        </div>
      </div>
      <div class="swiper-slide">
        <img src="/static/banner2.png" style="height:600px;width:100%;min-width:1200px;" alt="">
        <div style="position:absolute;left:12rem;top:10rem;color:#fff;font-size:3rem;" >海量广告资源 多种广告形式 24小时任意接单</div>
        <div style="position:absolute;left:12rem;top:15rem;color:#fff;font-size:2.2rem;" >充分利用空闲广告位资源 剩余流量持续变现</div>
        <div style="width:100%;height:128px;position:absolute;left:0;bottom:0;color:#fff;font-size:2rem;background-color:#fff;opacity:0.2" >
        </div>
        <div style="width:100%;height:128px;position:absolute;left:0;bottom:0;color:#fff;font-size:2rem;" >
           <Row>
              <Col span="6" style="text-align:center;">
                <div style="display:inline-block;height:128px;margin-top:22px;">
                  <div style="font-size:24px">广告多样</div>
                  <div style="font-size:16px">广告资源丰富 品类全 形式多</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">高额收益</div>
                  <div style="font-size:16px">多种广告组合接单 收益可观</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">自由接单</div>
                  <div style="font-size:16px">品类、位置和时间 任意选择</div>
                </div>
              </Col>
              <Col span="6" style="text-align:center;margin-top:22px;">
                <div style="display:inline-block;height:128px;">
                  <div style="font-size:24px">高效结算</div>
                  <div style="font-size:16px">高效结算 随时提现</div>
                </div>
              </Col>
          </Row>
        </div>
      </div>
    </div>
        <div class="swiper-pagination" style="bottom:128px;"></div>
  </div>
  <!--首页banner end-->
  <!--首页 模式介绍-->
  <div style="width:100%;background-color:#fff;margin-top:50px;">
    <div style="font-size:3rem;text-align:center;">
     两大模式 按需选择
    </div>
    <div style="width:100%;text-align:center;height:100%;margin:auto;">
      <div style="height:320px;width:580px;background:url('/static/index-model1.png') no-repeat 100% 100%;border-radius:5px;display:inline-block;">
        <div style="color:#fff;padding-top:100px;width:580px;text-align:center;">
           <div style="font-size:1.5rem;margin:0 0 15px 0;">按阅读推广</div>
           <div style="font-size:1rem;margin:5px 0;">投放条件自由设置，24小时推广</div>
           <div style="font-size:1rem;margin:5px 0;">分钟级跟踪监测，实时优化投放策略</div>
           <div style="font-size:1rem;margin:5px 0;">阅读计费，风险低，高效达成营销目标</div>
           <div style="margin:20px 0;">
              <router-link to="/login"><Button type="default" ghost>立即推广</Button></router-link>
           </div>
        </div>
      </div>
      <div style="margin-left:30px;height:320px;width:580px;background:url('/static/index-model2.png') no-repeat 100% 100%;border-radius:5px;display:inline-block;">
        <div style="color:#fff;padding-top:100px;width:580px;text-align:center;">
           <div style="font-size:1.5rem;margin:0 0 15px 0;">按位置推广</div>
           <div style="font-size:1rem;margin:5px 0;">按需精选优质媒体，快速投放</div>
           <div style="font-size:1rem;margin:5px 0;">一手价格，平台担保交易</div>
           <div style="font-size:1rem;margin:5px 0;">免费数据跟踪，省心省力达成营销目标</div>
           <div  style="margin:20px 0;">
              <router-link to="/login"><Button type="default" ghost>立即推广</Button></router-link>
           </div>
        </div>
      </div>
    </div>
  </div>
  <!--首页 模式介绍 end-->
  <!--首页 真实广告投放案例 end-->
  <div style="width:100%;background-color:#fff;margin-top:50px;">
    <div style="font-size:3rem;text-align:center;">
     真实广告投放案例
    </div>
    <div style="text-align:center;">
      <Tabs value="name1"style="display:inline-block;text-align:center;width:1200px;" >
            <TabPane style="text-align:center;display:inline-block;" label="图文广告" name="name1">
              <div>GoGoTalk青少外教在线英语：将目标人群定位在全国范围的家长教育类公众号，提升品牌的知名度并带来更多的预约订单，352W+粉丝覆盖量，10.6W+阅读量。</div>
              <div style="margin-top:10px;">
                  <img src="/static/index-tuwen.png" alt="">
              </div>
            </TabPane>
            <TabPane  style="text-align:center;"label="贴片广告" name="name2">
              <div>西班牙旅游：通过二维码和创意图片的形式把该广告投放在拥有海量活跃粉丝群的旅游类公众号上推广，准确挖掘优质客群，提高潜在目标受众群的订单转化率，246W+粉丝覆盖量，11.3W+阅读量。</div>
              <div style="margin-top:10px;">
                  <img src="/static/index-tiepian.png" alt="">
              </div>
            </TabPane>
            <TabPane  style="text-align:center;"label="线上涨粉" name="name3">
              <div>线上涨粉：根据公众号的性质调整投放策略，沉浸式体验吸引用户阅读并关注，精准锁定优质潜客，顺畅的浏览体验大幅降低广告跳出率，成功引导10000+精准用户关注其公众号。</div>
              <div style="margin-top:10px;">
                  <img src="/static/index-onlineaddfuns.png" alt="">
              </div>
            </TabPane>
            <TabPane  style="text-align:center;"label="线下涨粉" name="name4">
              <div>线下涨粉：利用免费WiFi吸引用户关联，关注公众号上网， 根据WiFi热点区域，短时间内迅速获得粉丝，通过线下Wifi渠道为客户增加了14000个粉丝。</div>
              <div style="margin-top:10px;">
                  <img src="/static/index-offlineaddfuns.png" alt="">
              </div>
            </TabPane>
      </Tabs>
    </div>
  </div>
  <!--首页 真实广告投放案例 end-->
<footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar';
import FooterBar from '@/components/common/FooterBar';

  export default {
    name: 'Index',
    components:{
      TopBar,
      FooterBar
    },
    created(){
      
    },
    data () {
      return {
      }
    },
    methods: {
   

    
    },
    mounted(){
        var Swiper=this.Swiper;
       var mySwiper = new Swiper('.swiper-container', {
          autoplay:true,
          loop:true,
          pagination: {
            el: '.swiper-pagination',
            clickable :true,
          }
        })
    }
  }
</script>
<style lang="less">
@import "Index.less";

</style>

