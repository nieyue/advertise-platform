<!--账户管理 -->
<template>
    <div class="body-wrap">
    <div class="body-btn-wrap">
      <Button type='primary'  @click='add'>增加账户</Button>
      <div class="search-wrap">
          <Select v-model="params.roleId"  transfer class="search-wrap-input" >
            <Option v-for="item in roleList" :value="item.roleId" :key="item.roleId">{{ item.name }}</Option>
        </Select>
        <Input v-model="params.accountId" class="search-wrap-input" placeholder="账户Id"></Input>
        <Input v-model="params.phone" class="search-wrap-input" placeholder="手机号，模糊查询"></Input>
        <Input v-model="params.email" class="search-wrap-input" placeholder="email，模糊查询"></Input>
        <Input v-model="params.realname" class="search-wrap-input" placeholder="真实姓名，模糊查询"></Input>
        <Select v-model="params.auth" transfer class="search-wrap-input"  placeholder="认证，全部">
            <Option v-for="item in authParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Select v-model="params.status" transfer class="search-wrap-input"  placeholder="状态，全部">
            <Option v-for="item in statusParamsList" :value="item.id" :key="item.id">{{ item.value }}</Option>
        </Select>
        <Button @click="search" type="info"  >查询</Button>
      </div>
    </div>
		 <!--新增 -->
     <Modal v-model="addAccountModel"
           title="新增账户管理"
           :closable="false"
           :mask-closable="false"
           width="1000px"
    >
      <Form ref="addAccount" :model="addAccount" :label-width="100"  label-position="right"  :rules="addAccountRules">
        <FormItem prop="phone" label="注册手机号:">
          <Input type="text" v-model="addAccount.phone" placeholder="注册手机号">
          </Input>
        </FormItem>
        <FormItem prop="password" label="密码:">
          <Input type="password" v-model="addAccount.password" placeholder="密码">
          </Input>
        </FormItem>
        <FormItem prop="nickname" label="昵称:">
          <Input type="text" v-model="addAccount.nickname" placeholder="昵称">
          </Input>
        </FormItem>
          <FormItem prop="icon" label="图像(上传或者填写):">
            <my-upload :defaultUpload="addAccount.icon" @uploadList="getAddIcon"></my-upload> 
            <div>
              <Input type="text" v-model="addAccount.icon" placeholder="图像">
            </Input>
              <img :src="addAccount.icon"  style='width:30px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="sex" label="性别:">
          <Select v-model="addAccount.sex" transfer size="large" style="width:100px">
              <Option v-for="item in sexList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="country" label="国家:">
          <Input type="text" v-model="addAccount.country" placeholder="国家">
          </Input>
        </FormItem>
        <FormItem prop="realname" label="真实姓名:">
          <Input type="text" v-model="addAccount.realname" placeholder="真实姓名">
          </Input>
        </FormItem>
        <FormItem prop="email" label="email:">
          <Input type="text" v-model="addAccount.email" placeholder="email">
          </Input>
        </FormItem>
        <FormItem prop="safetyGrade" label="安全等级:">
          <Select v-model="addAccount.safetyGrade" transfer size="large" style="width:100px">
              <Option v-for="item in safetyGradeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="auth" label="认证:">
          <Select v-model="addAccount.auth" transfer size="large" style="width:100px">
              <Option v-for="item in authList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
         <FormItem prop="identityCards" label="身份证:">
          <Input type="text" v-model="addAccount.identityCards" placeholder="身份证">
          </Input>
        </FormItem>
        <FormItem prop="identityCardsHoldImg" label="手持身份证上半身照(上传或者填写):" >
          <my-upload :defaultUpload="addAccount.identityCardsHoldImg" @uploadList="getAddIdentityCardsHoldImg"></my-upload> 
          <div>
            <Input type="text" v-model="addAccount.identityCardsHoldImg" placeholder="手持身份证上半身照">
          </Input>
             <img :src="addAccount.identityCardsHoldImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="identityCardsFrontImg" label="身份证正面(上传或者填写):" >
          <my-upload :defaultUpload="addAccount.identityCardsFrontImg" @uploadList="getAddIdentityCardsFrontImg"></my-upload> 
          <div>
            <Input type="text" v-model="addAccount.identityCardsFrontImg" placeholder="身份证正面">
          </Input>
             <img :src="addAccount.identityCardsFrontImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="identityCardsBackImg" label="身份证反面(上传或者填写):">
          <my-upload :defaultUpload="addAccount.identityCardsBackImg" @uploadList="getAddIdentityCardsBackImg"></my-upload>
          <div>
            <Input type="text" v-model="addAccount.identityCardsBackImg" placeholder="身份证反面">
          </Input>
             <img :src="addAccount.identityCardsBackImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="addAccount.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="roleId" label="角色:">
          <Select v-model="addAccount.roleId" transfer size="large" style="width:100px">
              <Option v-for="item in roleList" :value="item.roleId" :key="item.roleId">{{ item.name }}</Option>
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
     <Modal v-model="updateAccountModel"
           title="修改账户管理"
           :closable="false"
           :mask-closable="false"
           width="1000px"
    >
      <Form ref="updateAccount" :model="updateAccount" :label-width="100" label-position="right"  :rules="updateAccountRules">
       <FormItem prop="phone" label="注册手机号:">
          <Input type="text" v-model="updateAccount.phone" placeholder="注册手机号">
          </Input>
        </FormItem>
        <FormItem prop="nickname" label="昵称:">
          <Input type="text" v-model="updateAccount.nickname" placeholder="昵称">
          </Input>
        </FormItem>
          <FormItem prop="icon" label="图像(上传或者填写):" >
            <my-upload :defaultUpload="updateAccount.icon" @uploadList="getUpdateIcon"></my-upload> 
            <div>
              <Input type="text" v-model="updateAccount.icon" placeholder="图像">
              </Input>
              <img :src="updateAccount.icon"  style='width:30px;'alt="">
            </div>
        </FormItem>
        <FormItem prop="sex" label="性别:">
          <Select v-model="updateAccount.sex" transfer size="large" style="width:100px">
              <Option v-for="item in sexList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="country" label="国家:">
          <Input type="text" v-model="updateAccount.country" placeholder="国家">
          </Input>
        </FormItem>
        <FormItem prop="realname" label="真实姓名:">
          <Input type="text" v-model="updateAccount.realname" placeholder="真实姓名">
          </Input>
        </FormItem>
        <FormItem prop="email" label="email:">
          <Input type="text" v-model="updateAccount.email" placeholder="email">
          </Input>
        </FormItem>
        <FormItem prop="safetyGrade" label="安全等级:">
          <Select v-model="updateAccount.safetyGrade" transfer size="large" style="width:100px">
              <Option v-for="item in safetyGradeList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="auth" label="认证:">
          <Select v-model="updateAccount.auth" transfer size="large" style="width:100px">
              <Option v-for="item in authList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
         <FormItem prop="identityCards" label="身份证:">
          <Input type="text" v-model="updateAccount.identityCards" placeholder="身份证">
          </Input>
        </FormItem>
        <FormItem prop="identityCardsHoldImg" label="手持身份证上半身照(上传或者填写):" >
          <my-upload :defaultUpload="updateAccount.identityCardsHoldImg" @uploadList="getUpdateIdentityCardsHoldImg"></my-upload> 
          <div>
            <Input type="text" v-model="updateAccount.identityCardsHoldImg" placeholder="手持身份证上半身照">
          </Input>
             <img :src="updateAccount.identityCardsHoldImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="identityCardsFrontImg" label="身份证正面(上传或者填写):" >
          <my-upload :defaultUpload="updateAccount.identityCardsFrontImg" @uploadList="getUpdateIdentityCardsFrontImg"></my-upload> 
          <div>
            <Input type="text" v-model="updateAccount.identityCardsFrontImg" placeholder="身份证正面">
            </Input>
             <img :src="updateAccount.identityCardsFrontImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="identityCardsBackImg" label="身份证反面(上传或者填写):">
          <my-upload :defaultUpload="updateAccount.identityCardsBackImg" @uploadList="getUpdateIdentityCardsBackImg"></my-upload> 
          <div>
            <Input type="text" v-model="updateAccount.identityCardsBackImg" placeholder="身份证反面">
            </Input>
             <img :src="updateAccount.identityCardsBackImg"  style='width:300px;'alt="">
          </div>
        </FormItem>
        <FormItem prop="status" label="状态:">
          <Select v-model="updateAccount.status" transfer size="large" style="width:100px">
              <Option v-for="item in statusList" :value="item.id" :key="item.id">{{ item.value }}</Option>
          </Select>
        </FormItem>
        <FormItem prop="roleId" label="角色:">
          <Select v-model="updateAccount.roleId"  transfer size="large" style="width:100px">
              <Option v-for="item in roleList" :value="item.roleId" :key="item.roleId">{{ item.name }}</Option>
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
      <Table border :columns='accountColumns' :data='accountList' ref='table' size="small"></Table>
        <div style='display: inline-block;float: right; margin-top:10px;'>
        <Page style='margin-right:10px;' :current="params.currentPage" :total='params.total' :pageSize='params.pageSize' ref='page' :show-total='true'  @on-change='selectPage' show-elevator ></Page>
      </div>
    </div>
</template>
<script>
import twocitylist from '@/components/service/twocitylist'
export default {
  name: 'Account',
  data () {
    return {
      routerPath:this.$route.path,
        params:{
            startNum:1,//初始化个数
            currentPage:1,//当前页
            pageNum:1,//获取的第几个开始
            pageSize:10,//每页的个数
            total:0//总数
        },
          //认证
          authParamsList:[
          {id:'',value:'全部'},
          {id:0,value:'没认证'},
          {id:1,value:'审核中'},
          {id:2,value:'已认证'}
          ],
        //状态
        statusParamsList:[
          {id:'',value:'全部'},
          {id:0,value:'正常'},
          {id:1,value:'封禁'},
          {id:2,value:'异常'}
          ],
        //性别
        sexList:[
        {id:0,value:'未知'},
        {id:1,value:'男性'},
        {id:2,value:'女性'}
        ],
        //安全等级
        safetyGradeList:[
        {id:1,value:'低'},
        {id:2,value:'中'},
        {id:3,value:'高'}
        ],
        //认证
        authList:[
        {id:0,value:'没认证'},
        {id:1,value:'审核中'},
        {id:2,value:'已认证'}
        ],
      //状态
      statusList:[
        {id:0,value:'正常'},
        {id:1,value:'封禁'},
        {id:2,value:'异常'}
        ],
			//增加参数
			addAccountModel:false,
			addLoading:false,
			addAccountRules: {
                phone: [
                    {required: true, message: '注册手机号为必填项', trigger: 'blur'}
                    ]
                },
			addAccount:{
        icon:'',
        identityCardsHoldImg:'',
        identityCardsFrontImg:'',
        identityCardsBackImg:''
      },
			//修改参数
			updateAccountModel:false,
			updateLoading:false,
			updateAccountRules: {
                phone: [
                    {required: true, message: '注册手机号为必填项', trigger: 'blur'}
                    ]
                },
			updateAccount:{},
      //删除参数
      deleteAccount:{},
      //列表
	    roleList: [],
        accountList: [],
	    accountColumns: [
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
          title: '账户id',
          key: 'accountId',
          width:100,
          align:'center'
        },
        {
        	title:'注册手机号',
            key:'phone',
            width:100,
          align:'center'
        },
        {
        	title:'昵称',
            key:'nickname',
            width:100,
          align:'center'
        },
         {
        	title:'图像',
            key:'icon',
            width:100,
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.icon
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'性别',
            key:'sex',
            width:100,
          align:'center',
          render: (h, params) => {
            let sexvalue="";
            this.sexList.forEach(element => {
              if(element.id==params.row.sex){
                sexvalue=element.value;
              }
            });
             return  h('span',sexvalue);
          }
        },
        {
        	title:'国家',
            width:100,
          align:'center',
          render: (h, params) => {
              let country=params.row.country||'';
              return h('span', country);  
          }
        },
        {
        	title:'真实姓名',
            key:'realname',
            width:100,
          align:'center'
        },
        {
        	title:'email',
            key:'email',
            width:100,
          align:'center'
        },
        {
        	title:'安全等级',
            key:'auth',
            width:100,
          align:'center',
          render: (h, params) => {
            let safetyGradevalue="";
            this.safetyGradeList.forEach(element => {
              if(element.id==params.row.safetyGrade){
                safetyGradevalue=element.value;
              }
            });
             return  h('span',safetyGradevalue);
          }
        },
        {
        	title:'认证',
            key:'auth',
            width:100,
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
        	title:'身份证',
            key:'identityCards',
            width:100,
          align:'center'
        },
        {
        	title:'手持身份证上半身照',
            key:'identityCardsHoldImg',
            width:100,
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.identityCardsHoldImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'身份证正面',
            key:'identityCardsFrontImg',
            width:100,
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.identityCardsFrontImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
           {
        	title:'身份证反面',
            key:'identityCardsFrontImg',
            width:100,
          align:'center',
          render: (h, params) => {
            return h('img', {
              attrs: {
                src: params.row.identityCardsBackImg
              },
              style: {
                width: '45px'
              }
            })
          }
        },
        {
        	title:'电话',
            key:'telephone',
            width:100,
          align:'center'
        },
         {
        	title:'角色名',
          width:100,
          align:'center',
          render: (h, params) => {
            return h('span',params.row.role.name)
          }
        },
        {
        	title:'状态',
            key:'status',
             width:100,
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
          key:'createDate',
           width:100,
          sortable: true,
          align:'center'
        },
        {
        	title:'最后登陆时间',
          key:'loginDate',
           width:100,
          sortable: true,
          align:'center'
        },
				{
          title: '操作',
          key: 'action',
           width:200,
           fixed:'right',
          align:'center',
          render: (h, params) => {
            var marginstyle="2px"
              var varhh11=  h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                    this.update(params.row)
                  }
                }
              }, '编辑');
            var varhh12=  h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                    this.delete(params.row)
                  }
                }
              }, '删除');
            var varhh21=  h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                   let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/finance/'+pp);
                  }
                }
              }, '财务');
            var varhh22=  h('Button', {
                props: {
                  type: 'info',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                    let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/financeRecord/'+pp);
                  }
                }
              }, '财务记录');
          
            var varhh31=  h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/bankCard/'+pp);
                  }
                }
              }, '银行卡');
            var varhh32=  h('Button', {
                props: {
                  type: 'warning',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/company/'+pp);
                  }
                }
              }, '公司');
            var varhh41=  h('Button', {
                props: {
                  type: 'success',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/thirdInfo/'+pp);
                  }
                }
              }, '第三方信息');
            var varhh42=  h('Button', {
                props: {
                  type: 'success',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/collect/'+pp);
                  }
                }
              }, '汇总');
            var varhh51=  h('Button', {
                props: {
                  type: 'default',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/media/'+pp);
                  }
                }
              }, '媒体');
            var varhh52=  h('Button', {
                props: {
                  type: 'dashed',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/readMedia/'+pp);
                  }
                }
              }, '阅读媒体');
            var varhh53=  h('Button', {
                props: {
                  type: 'info',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/positionMedia/'+pp);
                  }
                }
              }, '位置媒体');
            var varhh61=  h('Button', {
                props: {
                  type: 'error',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/readAdvertise/'+pp);
                  }
                }
              }, '阅读广告');
            var varhh62=  h('Button', {
                props: {
                  type: 'error',
                  ghost:'',
                  size: 'small'
                },
                style: {
                  margin: marginstyle
                },
                on: {
                  click: () => {
                      let  pp=JSON.stringify({
                      currentPage:1,//当前页
                      accountId:params.row.accountId
                    });
                    this.$router.push('/main/positionAdvertise/'+pp);
                  }
                }
              }, '位置广告');
              var s=h("div","");
              var commons=[
                h("div",[
                  varhh11
                  //,varhh12
                ]),
                h("div",[
                  varhh21
                  ,varhh22
                ]),
                h("div",[
                  varhh31,
                  varhh32
                ]),
                h("div",[
                  varhh41,
                  varhh42
                ]),
              ]
              var medias=[
                h("div",[
                  varhh51,
                  varhh52,
                ]),
                h("div",[
                  varhh53,
                ]),
              ]
              var advertises=[
                h("div",[
                  varhh61,
                  varhh62
                ]),
              ]
              //媒体主路径
              if(this.routerPath=="/main/account/mediaAccount"){
                commons=commons.concat(medias);
              }
              //广告主路径
              if(this.routerPath=="/main/account/advertiseAccount"){
                commons=commons.concat(advertises);
              }
			s=h("div",commons);
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
      this.getList()
    },
    //分页点击
    selectPage (currentPage) {
      this.params.currentPage=currentPage;
      this.params.pageNum = (this.params.currentPage-1)*this.params.pageSize+this.params.startNum;
      this.getList()
    },
    //获取增加的图片
    getAddIcon(data){
      this.addAccount.icon=data[0].url
    },
    getAddIdentityCardsHoldImg(data){
      this.addAccount.identityCardsHoldImg=data[0].url
    },
    getAddIdentityCardsFrontImg(data){
      this.addAccount.identityCardsFrontImg=data[0].url
    },
    getAddIdentityCardsBackImg(data){
      this.addAccount.identityCardsBackImg=data[0].url
    },
    //获取修改的图片
    getUpdateIcon(data){
      this.updateAccount.icon=data[0].url
    },
    getUpdateIdentityCardsHoldImg(data){
      this.updateAccount.identityCardsHoldImg=data[0].url
    },
    getUpdateIdentityCardsFrontImg(data){
      this.updateAccount.identityCardsFrontImg=data[0].url
    },
    getUpdateIdentityCardsBackImg(data){
      this.updateAccount.identityCardsBackImg=data[0].url
    },
  //获取列表
   getRoleList () {

     /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     */
    this.params.pageSize=1000000;
     this.axiosbusiness.getList(this,{
       countUrl:'/role/count',
       listUrl:'/role/list',
       data:'roleList',
       success:()=>{
           //初始化
        this.addAccount={
            icon:'',
            identityCardsHoldImg:'',
            identityCardsFrontImg:'',
            identityCardsBackImg:'',
            sex:0,
            age:18,
            safetyGrade:1,
            country:'中国',
            auth:0,
            status:0,
            roleId:this.roleList[0].roleId
        };
        let rll=this.roleList.length;
        for(let i=0;i<rll;i++){
            //如果当前管理员非超级管理员
              if(!this.business.getIsSuperAdmin()
              &&this.roleList[i].name!="用户" 
              &&this.roleList[i].name!="媒体主"
              &&this.roleList[i].name!="广告主"){
                this.roleList.splice(i,1)
              rll--;
              i--;
              }
              //路径为管理员账户
       else if(this.routerPath=="/main/account/managerAccount"){
          if(this.roleList[i].name=='用户'
          ||this.roleList[i].name=='媒体主'
          ||this.roleList[i].name=='广告主'){
           this.roleList.splice(i,1);
            rll--;
            i--;
          }
          //路径为用户账户
       }else if(this.routerPath=="/main/account/userAccount"){
          if(this.roleList[i].name!='用户'){
             this.roleList.splice(i,1);
            rll--;
            i--;
          }
          }
          else if(this.routerPath=="/main/account/mediaAccount"){
          if(this.roleList[i].name!='媒体主'){
             this.roleList.splice(i,1);
            rll--;
            i--;
          }
          }
          else if(this.routerPath=="/main/account/advertiseAccount"){
          if(this.roleList[i].name!='广告主'){
             this.roleList.splice(i,1);
            rll--;
            i--;
          }
          }
        }
        if(this.roleList[0] &&this.roleList[0].roleId){
          this.params.roleId=this.roleList[0].roleId;
          this.addAccount.roleId=this.roleList[0].roleId;
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
    this.params.pageSize=10
     this.axiosbusiness.getList(this,{
       countUrl:'/account/count',
       listUrl:'/account/list',
       data:'accountList'
     },this.params)
    },
  //增加
	 add (params) {
      this.addAccountModel = true
      
    },
		//增加取消
		 addCancel () {
      if (!this.addLoading) {
        this.addAccountModel = false
        this.$refs.addAccount.resetFields()
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
      ref:'addAccount',
      url:'/account/add',
      requestObject:'addAccount',
      loading:'addLoading',
      showModel:'addAccountModel'
    })
    },
	 update (params) {
      this.updateAccountModel = true
      this.updateAccount.accountId = params.accountId
     //获取修改实体
      this.axiosbusiness.get(this,{
          url:'/account/load?accountId='+params.accountId,
         data:'updateAccount',
         success:()=>{
            this.updateProvinceCity=twocitylist.getValueByLabel(this.updateAccount.province,this.updateAccount.city)
         }
       })
    },
		//修改取消
		 updateCancel () {
      if (!this.updateLoading) {
        this.updateAccountModel = false
        this.$refs.updateAccount.resetFields()
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
    delete this.updateAccount.role;
    this.axiosbusiness.update(this,{
      ref:'updateAccount',
      url:'/account/update',
      requestObject:'updateAccount',
      loading:'updateLoading',
      showModel:'updateAccountModel'
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
    this.deleteAccount={
      "accountId":params.accountId
    };
    this.axiosbusiness.delete(this,{
      url:'/account/delete',
      requestObject:'deleteAccount'
    })
    }
  },
   watch: {
      $route (to,from){
        //console.error(this.routerPath)
        this.routerPath=this.$route.path;
        this.getRoleList();
      }
    },
  created () {
    this.getRoleList();
  },
  mounted () {

  }
}
</script>
