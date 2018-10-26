<!--企业文化 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
      <Card  style="margin:102px auto;width:800px;font-size:1.8rem;">
          <div style="color:red;">核心价值观：</div>
          <div>创新进取 团结协作 成长共赢</div>
          <br/>
          <div style="color:red;">经营宗旨：</div>
          <div>诚信务实 稳健发展 追求卓越</div>
          <br/>
          <div style="color:red;">企业愿景：</div>
          <div>建设一个让员工有归属感，能够实现个人价值的企业</div>
          <div>让企业与客户的链接更精准、更快速</div>
          <br/>
          <div style="color:red;">企业使命：</div>
          <div>为客户创造价值&nbsp;&nbsp;为员工创造福利&nbsp;&nbsp;为社会创造财富</div>
          <br/>
          <div style="color:red;">企业slogan：</div>
          <div>让成本更低，让覆盖更广</div>
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
