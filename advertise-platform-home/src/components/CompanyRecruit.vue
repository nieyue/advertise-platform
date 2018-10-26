<!--求贤若渴 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
      <Card  style="margin:102px auto 50px;width:800px;font-size:2rem;">
          <div style="color:red;text-align:center;">销售精英</div>
          <div style="font-size:1.6rem;color:#2d8cf0;">岗位职责：</div>
          <div style="font-size:1.2rem;color:#999;">
              <div>1、经过公司系统的培训，能够熟悉企业业务流程，识别目标客户潜在需求；</div>
              <div>2、通过电话沟通预约时间再上门拜访, 寻求销售机会并努力完成各项销售指标；</div>
              <div>3、维护客户档案，对客户需求进行定期追踪；</div>
              <div>4、根据市场数据分析报告，改善销售渠道，提高客户满意度。</div>
          </div>
          <div style="font-size:1.6rem;color:#2d8cf0;">任职资格：</div>
          <div style="font-size:1.2rem;color:#999;">
              <div>1、学历要求不限，热爱销售，有团队精神；</div>
              <div>2、积极主动的心态，愿意接受挑战，能承受较大的工作压力；</div>
              <div>3、性格外向，反应灵敏，沟通能力强；</div>
              <div>4.热爱电话销售工作，语言表达能力优秀；</div>
              <div>5.有销售经验者优先考虑；</div>
              <div>6.有明确的职业发展方向和坚持的职业精神。</div>
          </div>
      </Card>
      <Card  style="margin:50px auto 50px;width:800px;font-size:1.8rem;">
          <div style="color:red;text-align:center;">媒介精英</div>
          <div style="font-size:1.6rem;color:#2d8cf0;">岗位职责：</div>
          <div style="font-size:1.2rem;color:#999;">
              <div>1.媒体资源采购洽谈、价格把控；</div>
              <div>2.公司产品推广媒体排布方案支持；</div>
              <div>3、维护客户档案，对客户需求进行定期追踪；</div>
              <div>3.资源发布执行、跟踪、监控、总结。</div>
          </div>
          <div style="font-size:1.6rem;color:#2d8cf0;">任职资格：</div>
          <div style="font-size:1.2rem;color:#999;">
              <div>1.备具优秀商务谈判能力、议价能力；</div>
              <div>2.有资源竞争意识、了解网络媒体价值定位；</div>
              <div>3.备具良好人际交往能力；</div>
              <div>4.熟悉媒体单位或一年以上相关领域经验者优先。</div>
              <div>5.有明确的职业发展方向和坚持的职业精神。</div>
          </div>
      </Card>
      <footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar'
  export default {
    name: 'CompanyCulture',
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
 
    }
  }
</script>
