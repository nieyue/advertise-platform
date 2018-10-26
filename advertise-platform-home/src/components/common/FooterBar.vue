<!-- 底部 -->
<template>
    <div class="footer-bar">
        <div class="footer-bar-wrap">
            <div class="footer-bar-line-wrap">
              <router-link class="footer-bar-company" to="/companyProfile">公司简介</router-link>
              <router-link class="footer-bar-company" to="/companyCulture">企业文化</router-link>
              <router-link class="footer-bar-company" to="/companyRecruit">招聘英才</router-link>
              <router-link class="footer-bar-company" to="/companyContact">联系我们</router-link>
            </div>
            <!-- <div>
              <img src="/static/logo.jpg" style="width:80px;height:80px;color:#fff;"/>
              <span class="footer-bar-left-title">雅耀广告平台</span>
            </div> -->
            <div class="footer-bar-line-wrap">
              <span class="footer-bar-link">友情链接：</span>
              <a class="footer-bar-link" href="https://mp.weixin.qq.com/">微信公众平台</a>
              <a class="footer-bar-link" href="https://wx.qq.com/">微信网页版</a>
              <a class="footer-bar-link" href="http://weixin.sogou.com/">搜狗微信</a>
              <a class="footer-bar-link" href="https://baijia.baidu.com/">百度百家</a>
              <a class="footer-bar-link" href="https://om.qq.com/userAuth/index">企鹅号</a>
              <a class="footer-bar-link" href="https://weibo.com/">新浪微博</a>
            </div>
            <div class="footer-bar-line-wrap">
              <div>Copyright © 2018 雅耀（湖南）科技有限公司 版权所有   湘ICP备16000397号</div>
            </div>
        </div>
    </div>
</template>
<script>
  export default {
    name: 'FooterBar',
    created(){ 
    },
    data () {
      return {
      }
    },
    methods: {
     
    }
  }
</script>
<style lang="less">
@import "FooterBar.less";
</style>
