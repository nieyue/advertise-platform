//axios.defaults.baseURL = '/';

axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.Qs=Qs;
Vue.prototype.getQueryString=function (name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  decodeURIComponent(r[2]); return null;
     //if(r!=null)return  unescape(r[2]); return null;
};
var openid=Vue.prototype.getQueryString("openid");
//dialog
Vue.component("loading-toast",{
	props:{
		loadingToast:Object,
	},
/*	props:['value','isshow'],
*/	data:function () {
		    return {
		    	loadingToast:{}
		    }
		  },
	template:
		`<div :style="{'display':loadingToast.isshow==true?'block':'none'}" style="width:100%;top:50%;left:0;position:fixed;z-index:999999999;text-align:center;">
		<div
			style='display:inline-block;color:#fff;background-color:black;text-align:center;line-height:30px;border:1px solid black;border-radius:5px;min-height:30px;max-width:200px;padding:0 10px;'>
		<span v-text="loadingToast.value"></span>
		</div>
	</div>`,
	watch:{
		loadingToast:function(n,o){
			if(n&&n.isshow){
				setTimeout(function(){
			n.isshow=false;
				},2000);
			}
		}
	},
	created: function(){
		console.log(this.loadingToast)
	  }
});
//自定义组件
Vue.component("sign-prize-list",{
props:['signPrize'],
template:
`
	<div>
	<div class="media-left" style="vertical-align:middle;width:32%;">
		<a href="#" style="padding:5px 0 !important;position: relative;left: 0;top:0;display:inline-block;width:100%;">
		 <img class="comment-list-item-img"  :src="signPrize.imgAddress||'resources/img/daozhang.png'">
		</a>
	</div>
	<div class="media-body comment-right-body">
		<div class="pull-left">
			<div class="comment-right-body-title">
				<span style="font-size:0.85rem;overflow: hidden;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;text-overflow:ellipsis;color:#000" v-text="signPrize.name"></span>
			</div>
			<div>
				<span style="font-size:0.6rem;color:#999"> 
				公众号：<span v-text="signPrize.subscription.name"style="font-size:0.6rem;color:green"></span>
				<span style="font-size:0.6rem;color:#999"> 
			</div>
			<div class="text-muted" style="color:#bfbebe;color:#999">
				<span style="float: left;font-size:0.6rem;" class="newsType ">
			连续天数：<span v-text="signPrize.dayNumber" style="color:red"></span>
				</span>
			<span style="float: left;margin:0 5px;line-height:16px;">|</span>
			    <span style="font-size:0.6rem;color:#999">
			数量：<span v-text="signPrize.number"style="color:blue"></span>
				</span>
			</div>
			<div>
				<span style="font-size:0.6rem;">
				状态：<span v-text="signPrize.status==1?'待领取':signPrize.status==2?'已申请':signPrize.status==3?'领取成功':signPrize.status==4?'拒绝发送':''"style="color:red">
					</span>
				</span>
				<button v-if="signPrize.status==1" @click="signPrizeSubmit(signPrize)" class="btn btn-danger btn-xs">
				点击申请
				</button>
			</div>
		</div>
	</div>
	<loading-toast :loading-toast="loadingToast"></loading-toast>
	</div>
`,
data:function(){
	return {
		 loadingToast:{
				isshow:false,
				value:''	
		}
	}
},
methods:{
	//提交申请
  	signPrizeSubmit:function(signPrize){
  		var _this=this;
  	 axios( {
  			  method: 'post',
  			  url: '/signPrize/apply',
  			  data: _this.Qs.stringify({
  			     signPrizeId:signPrize.signPrizeId
  			  }),
  			}).then(function (res) {
  			    console.log(res.data);
  			    if(res.data.code==200){
  			    	_this.loadingToast={
		  			isshow:true,
		  			value:'提交成功'
  			    	};
  			    signPrize.status=2;
  			  _this.$Message.success('提交成功');
		  		//this.loadingToast.isshow=false;
		  		//this.$emit("show-loading-toast",this.loadingToast)
		  		//console.log(this.loadingToast)  	
  			    }else{
  			    	_this.loadingToast={
  				  			isshow:true,
  				  			value:'提交失败'
  		  			    	};
  			    	_this.$Message.error('提交失败');
  			    }
  			    
  			  })
  			  .catch(function (error) {
  			    console.log(error);
  			  });
  	}
}
});
//初始化
new Vue({
  el: '#app',
  data: {
	  signPrizeList:[],
	  //三级联动数据
      threeCityData:threeCity.getThreeCity(),
      //更新的接受省、市、区
      updateProvinceCityArea:[],
    //修改参数
	updateReceiptInfoModel:false,
	updateLoading:false,
	updateReceiptInfoRules: {
      phone: [
          {required: true, message: '注册手机号为必填项', trigger: 'blur'}
          ]
      },
	updateReceiptInfo:{},
  },
  methods:{
	  //更新中的二级联动省和市
	  updateProvinceAndCityAndArea:function(value,label){
        this.updateReceiptInfo.province=label[0].label
        this.updateReceiptInfo.city=label[1].label
        this.updateReceiptInfo.area=label[2].label
    },
    //显示收货地址
    showReceiptInfo:function(){
    	var _this=this;
    	this.updateReceiptInfoModel = true
    	 axios( {
    		  method: 'post',
    		  url: '/receiptInfo/list',
    		  data:_this.Qs.stringify( {
    			  openid:openid
    		  }),
    		}).then(function (res) {
    		    console.log(res.data);
    		    if(res.data.code==200){
    		    	_this.updateReceiptInfo=res.data.data[0];
    		    	var province=_this.updateReceiptInfo.province;
    		    	var city=_this.updateReceiptInfo.city;
    		    	var area=_this.updateReceiptInfo.area;
    		    	_this.updateProvinceCityArea=threeCity.getValueByLabel(province,city,area)
    		    	console.log(_this.updateProvinceCityArea)
    		    }else{
    		    	console.log(res.data.msg)
    		    }
    		    
    		  })
    		  .catch(function (error) {
    		    console.log(error);
    		  });
    },
  //修改取消
	 updateCancel:function() {
		 if (!this.updateLoading) {
		   this.updateReceiptInfoModel = false
		  // this.$refs.updateReceiptInfo.resetFields()
		 }
	},
	//修改确定
    updateSure:function() {
    	var _this=this;
    	_this.updateReceiptInfo.openid=openid
    	if(/^1[0-9]{10}$/.test(_this.updateReceiptInfo.phone)==false){
    		_this.$Message.error('手机号格式不对');
    		return ;
    	}
   	 axios( {
   		  method: 'post',
   		  url: '/receiptInfo/openidAdd',
   		  data:_this.Qs.stringify(_this.updateReceiptInfo),
   		}).then(function (res) {
   		    console.log(res.data);
   		    if(res.data.code==200){
   		    	_this.$Message.success('提交成功');
   		    	_this.updateReceiptInfo=res.data.data[0];	
   		    }else{
   		    	console.log(res.data.msg)
   		    	_this.$Message.error(res.data.msg);
   		    }
   		 _this.updateReceiptInfoModel = false
   		    
   		  })
   		  .catch(function (error) {
   			_this.updateReceiptInfoModel = false
   			_this.$Message.error('提交异常失败');
   		  });
 
    },
	 //获取列表
  	getSignPrizeList:function(){
  	var signPrize={
  		dayNumber:5,
  		name:'小米手环小米手环，新的品种小米手环，新的品种小米手环，新的品种',
  		number:2,
  		imgAddress:'http://image.kejixun.com/2018/0608/20180608062403479.png',
  		content:'小米手环，新的品种',
  		prizeDate:'2018-06-09',
  		status:1,
  		subscription:{
  			name:'一品小说吧',
  			},
  	};
  	 var _this=this;
  	 //如果没有id，则返回
  	 if(!openid){
		return;
		}
  	 
  axios( {
	  method: 'post',
	  url: '/signPrize/list',
	  data:_this.Qs.stringify( {
		  openid:openid
	  }),
	}).then(function (res) {
	    console.log(res.data);
	    if(res.data.code==200){
	    	_this.signPrizeList=res.data.data;	
	    	
	    }else{
	    	console.log(res.data.msg)
	    }
	    
	  })
	  .catch(function (error) {
	    console.log(error);
	  });
  	},
  	//提交申请,回调
  	showLoadingToast:function(loadingToast){
  		//this.loadingToast=loadingToast
  		loadingToast.isshow=false;
  		this.loadingToast=loadingToast
  		console.log(this.loadingToast)
  	}
  },
  created:function(){
	  //this.$Spin.show();
	  this.getSignPrizeList();
	  //this.$Spin.hide();
	  document.querySelector("html").style.display="block";
  }
});
