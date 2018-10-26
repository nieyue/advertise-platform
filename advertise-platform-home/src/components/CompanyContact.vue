<!--联系我们 -->
<template>
  <div>  
  <!--顶部-->
      <top-bar ></top-bar>
  <!--顶部 end -->
      <Card  style="margin:102px auto;width:800px;font-size:1.8rem;">
            <div style="width:770px;height:300px;" id="LBSMAP"></div>
            <br/>
            <div><Alert>公司地址：湖南省长沙市岳麓区麓谷企业广场E8栋3楼</Alert></div>
            <div><Alert type="success"><div style="display:inline-block;vertical-align:45px;margin-right:20px;">微信公众号：雅耀公司</div><img style="width:100px;height:100px;" src="/static/yayaogongzhonghao.jpg"/></Alert></div>
      </Card>
      <footer-bar></footer-bar>
  </div>
</template>
<script>
import TopBar from '@/components/common/TopBar'
import FooterBar from '@/components/common/FooterBar'
  export default {
    name: 'Companyontact',
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
    mounted(){
        this.baidumap()
    },
    methods: {
    baidumap(){
        	//创建地图
	var map = new AMap.Map('LBSMAP',{
        zoom: 14,
       // center: [112.945989,28.217831]
		center: [112.883570,28.207503]
    });
	/* var map = new AMap.Map('LBSMAP');
	    map.setZoom(10);
	    map.setCenter([112.945989,28.217831]);*/
	    var marker = new AMap.Marker({
	        position: [112.883570,28.207503],
	       // position: [112.945989,28.217831],
	    	map:map
	    });
	    var circle = new AMap.Circle({
	        center: [112.883570,28.207503],
	        //center: [112.945989,28.217831],
	        radius: 100,
	        fillOpacity:0.1,
	        strokeWeight:1
	    });
	    circle.setMap(map);
	    map.setFitView();
	    var info = new AMap.InfoWindow({
	        content:"<span style='color:#000;font-size:16px;'>欢迎您</span><br/><span style='font-size:16px;color:#000;'>这里是雅耀（湖南）科技有限公司</span>",
	        offset:new AMap.Pixel(0,-28)
	    });
	    info.open(map,marker.getPosition());
	    
	    //工具条比例尺
	    AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
	        var toolBar = new AMap.ToolBar();
	        var scale = new AMap.Scale();
	        map.addControl(toolBar);
	        map.addControl(scale);
	    });
	    //var map = new AMap.Map('LBSMAP');
	    map.setZoom(10);
	    //map.setCenter([112.945989,28.217831]);
	    map.setCenter( [112.883570,28.207503]);
	   /*  AMap.plugin('AMap.AdvancedInfoWindow',function(){
	    	  var infowindow = new AMap.AdvancedInfoWindow({
	    	    content: "<h3 class='title'>高德地图</h1>"+
	    	             "<div class='content'>高德是中国领先的数字地图内容、"+
	    	             "导航和位置服务解决方案提供商。</div>",
	    	    offset: new AMap.Pixel(0, -30),
	    	    asOrigin:false
	    	  });
	    	  infowindow.open(map,new AMap.LngLat(112.945989,28.217831));
	    	}); */
    }

    
    }
  }
</script>
