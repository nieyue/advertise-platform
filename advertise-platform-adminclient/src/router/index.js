import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

const router= new Router({
  // export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: '首页',
      redirect: "/login"
    },
 /*   {
      path: '/index',
      name: '首页',
      component: resolve=>require(['@/components/Index'],resolve)
    },*/
    {
      path: '/login',
      name: '登录',
      component: resolve=>require(['@/components/Login'],resolve)
    },
    
    {
      path: '/main',
      name: '主页',
      component: resolve=>require(['@/components/main/Main'],resolve),
      children:[
        {
          path: 'welcome/:pathParams',
          name: '欢迎页',
          meta: {
            icon: 'md-cloud-upload',
            title: '数据上传'
          },
          component: resolve=>require(['@/components/main/config/Welcome'],resolve)
        },
        {
          path: 'config',
          name: '平台配置',
          component: resolve=>require(['@/components/main/config/Config'],resolve)
        },
        {
          path: 'systemNotice/:pathParams',
          name: '系统通知',
          component:  resolve=>require(['@/components/main/notice/SystemNotice'],resolve)
        },
        {
          path: 'mediaCate/:pathParams',
          name: '媒体类型',
          component:  resolve=>require(['@/components/main/media/MediaCate'],resolve)
        },
        {
          path: 'media/:pathParams',
          name: '媒体',
          component:  resolve=>require(['@/components/main/media/Media'],resolve)
        },
        {
          path: 'mediaCollection/:pathParams',
          name: '媒体收藏',
          component:  resolve=>require(['@/components/main/media/MediaCollection'],resolve)
        },
        {
          path: 'readMedia/:pathParams',
          name: '阅读媒体',
          component:  resolve=>require(['@/components/main/media/ReadMedia'],resolve)
        },
        {
          path: 'mediaDailyData/:pathParams',
          name: '媒体日数据',
          component:  resolve=>require(['@/components/main/media/MediaDailyData'],resolve)
        },
        {
          path: 'mediaData/:pathParams',
          name: '媒体详细数据',
          component:  resolve=>require(['@/components/main/media/MediaData'],resolve)
        },
        {
          path: 'positionMedia/:pathParams',
          name: '位置媒体',
          component:  resolve=>require(['@/components/main/media/PositionMedia'],resolve)
        },
        {
          path: 'readAdvertise/:pathParams',
          name: '阅读广告',
          component:  resolve=>require(['@/components/main/advertise/ReadAdvertise'],resolve)
        },
        {
          path: 'positionAdvertise/:pathParams',
          name: '位置广告',
          component:  resolve=>require(['@/components/main/advertise/PositionAdvertise'],resolve)
        },
        {
          path: 'finance/:pathParams',
          name: '财务',
          component: resolve=>require(['@/components/main/finance/Finance'],resolve),
        },
        {
          path: 'financeRecord/:pathParams',
          name: '财务记录',
          component: resolve=>require(['@/components/main/finance/FinanceRecord'],resolve),
        },
        {
          path: 'bankCard/:pathParams',
          name: '银行卡',
          component: resolve=>require(['@/components/main/account/BankCard'],resolve),
        },
        {
          path: 'company/:pathParams',
          name: '公司',
          component: resolve=>require(['@/components/main/account/Company'],resolve),
        },
        {
          path: 'thirdInfo/:pathParams',
          name: '第三方信息',
          component: resolve=>require(['@/components/main/account/ThirdInfo'],resolve),
        },
        {
          path: 'collect/:pathParams',
          name: '汇总',
          component: resolve=>require(['@/components/main/collect/Collect'],resolve),
        },
        {
          path: 'account',
          name: '账户',
          component: resolve=>require(['@/components/main/account/ManagerAccount'],resolve),
          children:[
            {
              path: 'managerAccount',
              name: '管理员账户',
              component:  resolve=>require(['@/components/main/account/ManagerAccount'],resolve)
          },]
        },
        {
          path: 'account',
          name: '账户',
          component: resolve=>require(['@/components/main/account/Account'],resolve),
          children:[
            {
              path: 'userAccount',
              name: '用户',
              component: resolve=>require(['@/components/main/account/Account'],resolve),
            },
            {
              path: 'mediaAccount',
              name: '媒体主',
              component: resolve=>require(['@/components/main/account/Account'],resolve),
            },
            {
              path: 'advertiseAccount',
              name: '广告主',
              component: resolve=>require(['@/components/main/account/Account'],resolve),
            }
          ]
        },
        {
          path: 'role/:pathParams',
          name: '角色',
          component: resolve=>require(['@/components/main/rolePermission/Role'],resolve)
        },
        {
          path: 'permission',
          name: '权限',
          component: resolve=>require(['@/components/main/rolePermission/Permission'],resolve)
        },
        {
          path: 'rolePermission/:roleId',
          name: '角色权限',
          component: resolve=>require(['@/components/main/rolePermission/RolePermission'],resolve)
        },
        {
          path: 'system/druid',
          name: '数据库监控',
          component: resolve=>require(['@/components/main/system/Druid'],resolve)
        },
        {
          path: 'system/swagger',
          name: 'API接口管理（swagger）',
          component: resolve=>require(['@/components/main/system/Swagger'],resolve)
        }
      ]
    }
  ]
})
router.beforeEach((to, from, next) => {
  if(to.fullPath.indexOf("role")>0
    ||to.fullPath.indexOf("permission")>0
    ||to.fullPath.indexOf("rolePermission")>0){
      //判断是否超级管理员，是就显示账户管理
      if(sessionStorage.getItem("account")){
        let account=JSON.parse(sessionStorage.getItem("account"));
        if(account.role.name=="超级管理员"){
        next()
      }else{
        next(false)
      }
    }else{
      next()
    }

  }else{
    next()
  }

})
export default router
