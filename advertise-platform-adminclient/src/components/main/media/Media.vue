<!--媒体管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <Button type='primary'  @click='add'>增加媒体</Button>
      <div class="search-wrap">
          <Select v-model="params.mediaCateId"  transfer class="search-wrap-input" >
            <Option v-for="item in mediaCateList" :value="item.mediaCateId" :key="item.mediaCateId">{{ item.name }}</Option>
        </Select>
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
        <Select v-model="params.auth" transfer class="search-wrap-input"  placeholder="授权，全部">
            <Option v-for="item in authParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.takeOrder" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in takeOrderParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
	<!--新增 -->
     <Modal v-model="addMediaModel"
           title="新增媒体"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="addMedia" :model="addMedia" :label-width="100"  label-position="right"  :rules="addMediaRules">
        <FormItem prop="mediaCateId" label="媒体类型:">
          <Select v-model="addMedia.mediaCateId" size="large" style="width:100px">
              <Option v-for="item in mediaCateList" :value="item.mediaCateId" :key="item.mediaCateId">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="name" label="媒体名称:">
          <Input type="text" v-model="addMedia.name" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="accountName" label="媒体账号:">
          <Input type="text" v-model="addMedia.accountName" placeholder="媒体账号">
          </Input>
        </FormItem>
        <FormItem prop="wechatImg" label="微信头像(上传或者填写):">
            <my-upload :defaultUpload="addMedia.wechatImg" @uploadList="getAddWechatImg"></my-upload> 
            <div>
                <Input type="text" v-model="addMedia.wechatImg" placeholder="微信头像">
            </Input>
                <img :src="addMedia.wechatImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="qrCode" label="二维码(上传或者填写):">
            <my-upload :defaultUpload="addMedia.qrCode" @uploadList="getAddQrCode"></my-upload> 
            <div>
                <Input type="text" v-model="addMedia.qrCode" placeholder="二维码">
            </Input>
                <img :src="addMedia.qrCode"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:">
          <Input type="text" v-model="addMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="funsImg" label="粉丝截图(上传或者填写):">
            <my-upload :defaultUpload="addMedia.funsImg" @uploadList="getAddFunsImg"></my-upload> 
            <div>
                <Input type="text" v-model="addMedia.funsImg" placeholder="粉丝截图">
            </Input>
                <img :src="addMedia.funsImg"  style='height:500px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="userImg" label="用户属性(上传或者填写):">
            <my-upload :defaultUpload="addMedia.userImg" @uploadList="getAddUserImg"></my-upload> 
            <div>
                <Input type="text" v-model="addMedia.userImg" placeholder="用户属性">
            </Input>
                <img :src="addMedia.userImg"  style='height:300px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="readingNumber" label="阅读量:">
          <Input type="text" v-model="addMedia.readingNumber" placeholder="阅读量">
          </Input>
        </FormItem>
        <FormItem prop="sexManScale" label="性别男比例,单位%:">
          <InputNumber  :max="100" :min="0" :step="0.01"  style="width:180px" v-model="addMedia.sexManScale"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="性别男比例,单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="sexWomanScale" label="性别女比例,单位%:">
          <InputNumber  :max="100" :min="0" :step="0.01" style="width:180px" v-model="addMedia.sexWomanScale"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="性别女比例,单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="funsTop1Address" label="粉丝top1地址:">
          <Select v-model="addMedia.funsTop1Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="funsTop2Address" label="粉丝top2地址:">
          <Select v-model="addMedia.funsTop2Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="funsTop3Address" label="粉丝top3地址:">
           <Select v-model="addMedia.funsTop3Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="auth" label="授权:">
          <Select v-model="addMedia.auth" transfer size="large" style="width:100px">
              <Option v-for="item in authList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="takeOrder" label="是否接单:">
          <Select v-model="addMedia.takeOrder" transfer size="large" style="width:100px">
              <Option v-for="item in takeOrderList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="top1Price" label="多图文头条报价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="addMedia.top1Price"
            placeholder="多图文头条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="top2Price" label="多图文二条报价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="addMedia.top2Price"
            placeholder="多图文二条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="top3nPrice" label="多图文3-N条报价:">
          <InputNumber   :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="addMedia.top3nPrice"
            placeholder="多图文3-N条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="singlePrice" label="单图文报价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="addMedia.singlePrice"
            placeholder="单图文报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="addMedia.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button  @click='addCancel'>取消</Button>
        <Button type='primary' :loading='addLoading'>
          <span v-if="!addLoading" @click='addSure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--新增end -->
		 <!--修改 -->
     <Modal v-model="updateMediaModel"
           title="修改媒体"
           :closable="false"
           :mask-closable="false"
    >
      <Form ref="updateMedia" :model="updateMedia" :label-width="100" label-position="right"  :rules="updateMediaRules">
        <FormItem prop="mediaCateId" label="媒体类型:">
          <Select v-model="updateMedia.mediaCateId" size="large" style="width:100px">
              <Option v-for="item in mediaCateList" :value="item.mediaCateId" :key="item.mediaCateId">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="name" label="媒体名称:">
          <Input type="text" v-model="updateMedia.name" placeholder="媒体名称">
          </Input>
        </FormItem>
        <FormItem prop="accountName" label="媒体账号:">
          <Input type="text" v-model="updateMedia.accountName" placeholder="媒体账号">
          </Input>
        </FormItem>
        <FormItem prop="wechatImg" label="微信头像(上传或者填写):">
            <my-upload :defaultUpload="updateMedia.wechatImg" @uploadList="getUpdateWechatImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateMedia.wechatImg" placeholder="微信头像">
            </Input>
                <img :src="updateMedia.wechatImg"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="qrCode" label="二维码(上传或者填写):">
            <my-upload :defaultUpload="updateMedia.qrCode" @uploadList="getUpdateQrCode"></my-upload> 
            <div>
                <Input type="text" v-model="updateMedia.qrCode" placeholder="二维码">
            </Input>
                <img :src="updateMedia.qrCode"  style='height:200px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="funs" label="粉丝数:">
          <Input type="text" v-model="updateMedia.funs" placeholder="粉丝数">
          </Input>
        </FormItem>
        <FormItem prop="funsImg" label="粉丝截图(上传或者填写):">
            <my-upload :defaultUpload="updateMedia.funsImg" @uploadList="getUpdateFunsImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateMedia.funsImg" placeholder="粉丝截图">
            </Input>
                <img :src="updateMedia.funsImg"  style='height:500px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="userImg" label="用户属性(上传或者填写):">
            <my-upload :defaultUpload="updateMedia.userImg" @uploadList="getUpdateUserImg"></my-upload> 
            <div>
                <Input type="text" v-model="updateMedia.userImg" placeholder="用户属性">
            </Input>
                <img :src="updateMedia.userImg"  style='height:300px;width:200px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="readingNumber" label="阅读量:">
          <Input type="text" v-model="updateMedia.readingNumber" placeholder="阅读量">
          </Input>
        </FormItem>
        <FormItem prop="sexManScale" label="性别男比例,单位%:">
          <InputNumber  :max="100" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.sexManScale"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="性别男比例,单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="sexWomanScale" label="性别女比例,单位%:">
          <InputNumber  :max="100" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.sexWomanScale"
            :formatter="value => `${value}%`"
            :parser="value => value.replace('%', '')"
            placeholder="性别女比例,单位%">
          </InputNumber >
        </FormItem>
        <FormItem prop="funsTop1Address" label="粉丝top1地址:">
          <Select v-model="updateMedia.funsTop1Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="funsTop2Address" label="粉丝top2地址:">
          <Select v-model="updateMedia.funsTop2Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="funsTop3Address" label="粉丝top3地址:">
          <Select v-model="updateMedia.funsTop3Address" filterable>
                <Option v-for="item in oneCityList" :value="item" :key="item" >{{ item }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="auth" label="授权:">
          <Select v-model="updateMedia.auth" transfer size="large" style="width:100px">
              <Option v-for="item in authList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="takeOrder" label="是否接单:">
          <Select v-model="updateMedia.takeOrder" transfer size="large" style="width:100px">
              <Option v-for="item in takeOrderList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="top1Price" label="多图文头条报价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.top1Price"
            placeholder="多图文头条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="top2Price" label="多图文二条报价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.top2Price"
            placeholder="多图文二条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="top3nPrice" label="多图文3-N条报价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.top3nPrice"
            placeholder="多图文3-N条报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="singlePrice" label="单图文报价:">
          <InputNumber  :max="10000000000" :min="0" :step="0.01" style="width:180px" v-model="updateMedia.singlePrice"
            placeholder="单图文报价">
          </InputNumber >
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="updateMedia.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
      </Form>
      <div slot='footer'>
        <Button  @click='updateCancel'>取消</Button>
        <Button type='primary' :loading='updateLoading'>
          <span v-if="!updateLoading" @click='updateSure'>确定</span>
          <span v-else>Loading...</span>
        </Button>
      </div>
    </Modal>
    <!--修改end -->
      <Table border :columns='mediaColumns' :data='mediaList' ref='table' size="small"></Table>
        <div style='display:inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
export default {
  name: 'Media',
  data () {
    return {
        oneCityList:this.utils.getOneCity(),
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
        //授权,1未授权，2已授权
        authParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'未授权'},
          {id:2,value:'已授权'}
          ],
        //是否接单,1是，2否
        takeOrderParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'是'},
          {id:2,value:'否'}
          ],
        //状态，1审核中、2正常、3审核未通过
        statusParamsList:[
          {id:'',value:'全部'},
          {id:1,value:'审核中'},
          {id:2,value:'正常'},
          {id:3,value:'审核未通过'}
          ],
         //授权,1未授权，2已授权
        authList:[
          {id:1,value:'未授权'},
          {id:2,value:'已授权'}
          ],
        //是否接单,1是，2否
        takeOrderList:[
          {id:1,value:'是'},
          {id:2,value:'否'}
          ],
        //状态，1审核中、2正常、3审核未通过
        statusList:[
          {id:1,value:'审核中'},
          {id:2,value:'正常'},
          {id:3,value:'审核未通过'}
          ],
			//增加参数
			addMediaModel:false,
			addLoading:false,
			addMediaRules: {
                name: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ],
                accountName: [
                    {required: true, message: '媒体账号为必填项', trigger: 'blur'}
                    ]
                },
			addMedia:{

           
			},
			//修改参数
			updateMediaModel:false,
			updateLoading:false,
			updateMediaRules: {
                name: [
                    {required: true, message: '媒体名称为必填项', trigger: 'blur'}
                    ],
                accountName: [
                    {required: true, message: '媒体账号为必填项', trigger: 'blur'}
                    ]
                },
			updateMedia:{
      },
      //删除参数
      deleteMedia:{},
      //列表
      mediaCateList:[],
      mediaList: [],
	    mediaColumns: [
        {
          title: '序号',
          width:100,
          align:'center',
          render: (h, params) => {
            return h('span', params.index
            +(this.params.currentPage-1)*this.params.pageSize+this.params.startNum);
          }
        },
        {
          title: '媒体id',
          width:100,
          key: 'mediaId',
          align:'center'
        },
        {
        	title:'媒体名称',
          width:100,
        	key:'name',
          align:'center'
        },
        {
        	title:'媒体账号',
          width:100,
        	key:'accountName',
          align:'center'
        },
        {
          title:'微信头像',
          width:100,
        	key:'wechatImg',
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.wechatImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
          title:'二维码',
          width:100,
        	key:'qrCode',
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.qrCode
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'粉丝数',
          width:100,
        	key:'funs',
          align:'center'
        },
        {
          title:'粉丝截图',
          width:100,
        	key:'funsImg',
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.funsImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
          title:'用户属性',
          width:100,
        	key:'userImg',
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.userImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'阅读量',
          width:100,
        	key:'readingNumber',
          align:'center'
        },
        {
        	title:'性别男比例,单位%',
          width:100,
        	key:'sexManScale',
          align:'center'
        },
        {
        	title:'性别女比例,单位%',
          width:100,
        	key:'sexWomanScale',
          align:'center'
        },
        {
        	title:'粉丝top1地址',
          width:100,
        	key:'funsTop1Address',
          align:'center'
        },
        {
        	title:'粉丝top2地址',
          width:100,
        	key:'funsTop2Address',
          align:'center'
        },
        {
        	title:'粉丝top3地址',
          width:100,
        	key:'funsTop3Address',
          align:'center'
        },
        {
        	title:'授权',
          width:100,
          key:'auth',
          align:'center',
          render: (h, params) => {
            let authvalue="";
            this.authList.forEach(element => {
              if(element.id==params.row.auth){
                authvalue=element.value;
              }
            });
             return  h('span',authvalue);
          }
        },
        {
        	title:'是否接单',
          width:100,
          key:'takeOrder',
          align:'center',
          render: (h, params) => {
            let takeOrdervalue="";
            this.takeOrderList.forEach(element => {
              if(element.id==params.row.takeOrder){
                takeOrdervalue=element.value;
              }
            });
             return  h('span',takeOrdervalue);
          }
        },
        {
        	title:'多图文头条报价',
          width:100,
        	key:'top1Price',
          align:'center'
        },
        {
        	title:'多图文二条报价',
          width:100,
        	key:'top2Price',
          align:'center'
        },
        {
        	title:'多图文3-N条报价',
          width:100,
        	key:'top3nPrice',
          align:'center'
        },
        {
        	title:'单图文报价',
          width:100,
        	key:'singlePrice',
          align:'center'
        },
        {
        	title:'状态',
          width:100,
          key:'status',
          align:'center',
          render: (h, params) => {
            let statusvalue="";
            this.statusList.forEach(element => {
              if(element.id==params.row.status){
                statusvalue=element.value;
              }
            });
             return  h('span',statusvalue);
          }
        },
        {
        	title:'创建时间',
          width:100,
          key:'createDate',
          sortable: true,
          align:'center'
        },
        {
        	title:'修改时间',
          width:100,
          key:'updateDate',
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          width:200,
          key: 'action',
          fixed:'right',
          render: (h, params) => {
            var varhh1=  h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  margin: '10px'
                },
                on: {
                  click: () => {
                    this.update(params.row)
                  }
                }
              }, '编辑');
            var varhh2=  h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  margin: '10px'
                },
                on: {
                  click: () => {
                    this.delete(params.row)
                  }
                }
              }, '删除');
              var varhh31=  h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  margin: '5px'
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      mediaId:params.row.mediaId
                    });
                    this.$router.push('/main/mediaDailyData/'+pp);
                  }
                }
              }, '媒体日数据');
              var varhh32=  h('Button', {
                props: {
                  type: 'warning',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: '5px'
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      mediaId:params.row.mediaId
                    });
                    this.$router.push('/main/mediaData/'+pp);
                  }
                }
              }, '媒体详细数据');
            	var s=h("div","");
			s=h("div",[
            h("div",[
              varhh1,
              varhh2
            ]),
            h("div",[
              varhh31,
              varhh32
            ]),
            ]);
            return s;
          }
        }
      ],
    }
  },
  methods: {
    //查询
    search(){
      this.params.currentPage=1;
      this.params.pageNum =1;
      this.selectPage(this.params.currentPage)
    },
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
       //构造path
     let pp=JSON.stringify({
       currentPage:currentPage,
       accountId:JSON.parse(this.$route.params.pathParams).accountId,
     })
      this.$router.push(this.$route.path.substr(0,this.$route.path.indexOf(this.$route.params.pathParams))+pp);
      this.getMediaCateList()
    },
      //获取增加的图片
    getAddWechatImg(data){
      this.addMedia.wechatImg=data[0].url
    },
    getAddQrCode(data){
      this.addMedia.qrCode=data[0].url
    },
    getAddFunsImg(data){
      this.addMedia.funsImg=data[0].url
    },
    getAddUserImg(data){
      this.addMedia.userImg=data[0].url
    },
    //获取修改的图片
    getUpdateWechatImg(data){
      this.updateMedia.wechatImg=data[0].url
    },
    getUpdateQrCode(data){
      this.addMedia.qrCode=data[0].url
    },
    getUpdateFunsImg(data){
      this.addMedia.funsImg=data[0].url
    },
    getUpdateUserImg(data){
      this.addMedia.userImg=data[0].url
    },
    //获取列表
   getMediaCateList () {
     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     */
     this.params.pageSize=1000000;
     this.axiosbusiness.getList(this,{
       countUrl:'/mediaCate/count',
       listUrl:'/mediaCate/list',
       data:'mediaCateList',
       success:()=>{
         this.addMedia={
           wechatImg:'',
           qrCode:'',
           funsImg:'',
           userImg:'',
           mediaCateId:this.mediaCateList[0].mediaCateId,
           funs:0,
           readingNumber:0,
           sexManScale:0,
           sexWomanScale:0,
           top1Price:0,
           top2Price:0,
           top3nPrice:0,
           singlePrice:0,
           auth:this.authList[0].id,
           takeOrder:this.takeOrderList[0].id,
           status:this.statusList[0].id
         }
         this.getList();
       }
     },
    this.params)
    },
  //获取列表
   getList () {
     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     */
    this.params.accountId=JSON.parse(this.$route.params.pathParams).accountId
     this.axiosbusiness.getList(this,{
       countUrl:'/media/count',
       listUrl:'/media/list',
       data:'mediaList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addMediaModel = true
      
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addMediaModel = false
        this.$refs.addMedia.resetFields()
      }
    },
		//增加确定
    addSure () {
       /**
     * 增加
     * $this  vue组件
     * p.ref 验证
     * p.url 增加url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.axiosbusiness.add(this,{
      ref:'addMedia',
      url:'/media/add',
      requestObject:'addMedia',
      loading:'addLoading',
      showModel:'addMediaModel'
    })
    },
	 update (params) {
      this.updateMediaModel = true
        //获取修改实体
      this.axiosbusiness.get(this,{
         url:'/media/load?mediaId='+params.mediaId,
         data:'updateMedia'
         })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateMediaModel = false
        this.$refs.updateMedia.resetFields()
      }
    },
		//修改确定
    updateSure () {
      /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     */
    this.axiosbusiness.update(this,{
      ref:'updateMedia',
      url:'/media/update',
      requestObject:'updateMedia',
      loading:'updateLoading',
      showModel:'updateMediaModel'
    })
 
    },
    //删除
    delete(params){
    /**
     * 删除
     * $this  vue组件
     * p.url 修改url
     * p.requestObject 请求参数对象
     */
    this.deleteMedia={
      "mediaId":params.mediaId
    };
    this.axiosbusiness.delete(this,{
      url:'/media/delete',
      requestObject:'deleteMedia'
    })
    }
  },
   watch: {
    //当前页面参数修改动态启动
      $route (to,from){
        this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
      }
    }, 
  created () {
    this.selectPage(JSON.parse(this.$route.params.pathParams).currentPage)
  }
}
</script>
