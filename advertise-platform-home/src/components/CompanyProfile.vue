<!--公司简介 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
      <Card  style="margin:102px auto;width:800px;font-size:1.8rem;">
          <p slot="title" style="font-size:1.6rem">雅耀，让成本更低，让覆盖更广</p>
          <p>
          雅耀（湖南）科技有限公司于2012年6月成立，是一家为企业提供线上广告投放服务的公司。
          </p>
          <br/>
          <p>
          在互联网媒体平台，雅耀拥有包括光明网、央视网等各领域纵深丰富的投放资源；
          </p>
          <br/>
          <p>
            在PC端及移动互联网端，雅耀更积累了包括优酷、360、37玩等各行业大量优势资源。成立至今，雅耀已服务企业客户类型有PC 端游戏，手游，H5游戏，今日头条等游戏和应用，为不同行业客户量身打造传播解决方案。
          </p>
          <br/>
          <p>
            目前，公司正运用现有资源及多年运营经验，积极开发线上一站式广告投放平台，链接企业主与平台，快速匹配企业主投放需求，让品牌推广更简单，让流量变现更快速。
          公司拥有一支年轻的团队，他们充满活力而不失沉稳，在广告及互联网行业经验丰富。公司为员工提供充分发挥自身价值的舞台，同时提供优厚的报酬和丰富的福利。
          加入雅耀，你将发现更广阔的职场空间。
          </p>
      </Card>
      <footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar'
  export default {
    name: 'CompanyProfile',
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
