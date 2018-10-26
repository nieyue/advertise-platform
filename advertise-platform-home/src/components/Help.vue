<!--帮助中心 -->
<template>
<div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
  <div style="margin-top:82px;">
  </div>
   <!--帮助中心菜单-->
   <div style="margin: 80px;display:inline-block;border:1px solid #ececec">
        <Card title="帮助中心" icon="ios-options" :padding="0" shadow style="width: 200px;">
            <CellGroup style="text-align:center;">
                <div @click="selectMenu(menu)" v-for="menu of menuList">
                <Cell class="help-menu-item" :style="{backgroundColor:currentMenu==menu?'red':'#fff',color:currentMenu==menu?'#fff':'#000'}"   :title="menu.value"  >
                </Cell>
                </div>
            </CellGroup>
        </Card>
    </div>
    <!--帮助中心菜单 end-->
    <!--帮助中心列表 -->
    <div style="display:inline-block;margin-top:80px;vertical-align:top;width:800px;border:1px solid #ececec;">
      <!--帮助中心 常见问题 -->
        <Collapse simple  style="display:inline-block;width:800px;" v-if="status==1" value="commonProblem">
            <Panel :name="index+'commonProblem'" :key="index" v-for="( commonProblem,index) in commonProblemList">
              <Icon type="md-help-circle" style="color:red;font-size:2rem;"/>
                {{commonProblem.q}}
                <p slot="content" style="white-space:pre-line;display:inline-block;padding:10px;">
                  {{commonProblem.a}}
                </p>
            </Panel>
        </Collapse>
         <!--帮助中心 常见问题 end-->
          <!--帮助中心 广告主-->
        <Collapse simple  style="display:inline-block;width:800px;" v-if="status==2" value="advertiseProblem">
            <Panel :name="index+'advertiseProblem'" :key="index"  v-for="( advertiseProblem,index) in advertiseProblemList">
              <Icon type="md-help-circle" style="color:red;font-size:2rem;"/>
                {{advertiseProblem.q}}
                <p slot="content" style="white-space:pre-line;display:inline-block;padding:10px;">
                  {{advertiseProblem.a}}
                </p>
            </Panel>
        </Collapse>
        <!--帮助中心 广告主 end-->
        <!--帮助中心 媒体主-->
        <Collapse simple  style="display:inline-block;width:800px;" v-if="status==3" value="channelProblem">
            <Panel :name="index+'channelProblem'" :key="index"  v-for="( channelProblem,index) in channelProblemList">
              <Icon type="md-help-circle" style="color:red;font-size:2rem;"/>
                {{channelProblem.q}}
                <p slot="content" style="white-space:pre-line;display:inline-block;padding:10px;">
                  {{channelProblem.a}}
                </p>
            </Panel>
        </Collapse>
        <!--帮助中心 媒体主 end-->
      <!-- 用户协议 -->
        <div v-if="status==4" v-html='up' style="white-space:pre-line;display:inline-block;padding:20px;">
        </div>
      <!-- 用户协议 end-->
    </div>
     <!--帮助中心列表 end-->
  <footer-bar></footer-bar>
</div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar';
import userprotocol from '@/components/service/userprotocol';
import commonproblemlist from '@/components/service/commonproblemlist';
import advertiseproblemlist from '@/components/service/advertiseproblemlist';
import channelroblemlist from '@/components/service/channelproblemlist';
  export default {
    name: 'Help',
    components:{
      TopBar,
      FooterBar
    },
    created(){
     this.selectMenu(this.menuList[0])
    },
    data () {
      return {
        //菜单列表
        menuList:[
          {id:1,value:'常见问题'},
          {id:2,value:'广告主'},
          {id:3,value:'媒体主'},
          {id:4,value:'用户协议'},
        ],
        //当前选中菜单
        currentMenu:null,
        //常见问题
        commonProblemList:[],
        //广告主问题
        advertiseProblemList:[],
        //媒体主问题
        channelProblemList:[],
        //用户协议
        up:'',
        //状态，默认为1,即是常见问题
        status:1,

      }
    },
    methods: {
    //选择菜单
    selectMenu(menu){
      this.currentMenu=menu;
     // console.log(userprotocol.getUserProtocol())
        this.status=menu.id
      if(menu.id==1){
        //获取常见问题
       this.commonProblemList=commonproblemlist.getCommonProblemList();
      }else if(menu.id==2){
        this.advertiseProblemList=advertiseproblemlist.getAdvertiseProblemList();
      }else if(menu.id==3){
        this.channelProblemList=channelroblemlist.getChannelProblemList();
      }else if(menu.id==4){
        this.up=userprotocol.getUserProtocol();
      }
    },
    }
  }
</script>
<style lang="less">
@import "Help.less";
</style>
