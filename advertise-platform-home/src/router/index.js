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
      redirect: "/index"
    },
    {
      path: '/index',
      name: '首页',
      component: resolve=>require(['@/components/Index'],resolve)
    },
    {
      path: '/weixinspread',
      name: '微信推广',
      component: resolve=>require(['@/components/Weixinspread'],resolve)
    },
    {
      path: '/subscriptionfuns',
      name: '公众号涨粉',
      component: resolve=>require(['@/components/Subscriptionfuns'],resolve)
    },
    {
      path: '/channelorder',
      name: '媒体接单',
      component: resolve=>require(['@/components/Channelorder'],resolve)
    },
    {
      path: '/help',
      name: '帮助中心',
      component: resolve=>require(['@/components/Help'],resolve)
    },
    {
      path: '/companyProfile',
      name: '公司简介',
      component: resolve=>require(['@/components/CompanyProfile'],resolve)
    },
    {
      path: '/companyCulture',
      name: '企业文化',
      component: resolve=>require(['@/components/CompanyCulture'],resolve)
    },
    {
      path: '/companyRecruit',
      name: '招聘英才',
      component: resolve=>require(['@/components/CompanyRecruit'],resolve)
    },
    {
      path: '/companyContact',
      name: '联系我们',
      component: resolve=>require(['@/components/CompanyContact'],resolve)
    },
    {
      path: '/login',
      name: '登录',
      component: resolve=>require(['@/components/Login'],resolve)
    },
    {
      path: '/register',
      name: '注册',
      component: resolve=>require(['@/components/Register'],resolve)
    },
    {
      path: '/main',
      name: '主页',
      component: resolve=>require(['@/components/main/Main'],resolve),
      children:[
        {
          path: 'advertise/index/:pathParams',
          name: '首页',
          component: resolve=>require(['@/components/main/advertise/Index'],resolve)
        },
        {
          path: 'advertise/readAdvertise/:pathParams',
          name: '阅读广告',
          component: resolve=>require(['@/components/main/advertise/ReadAdvertise'],resolve)
        },
        {
          path: 'advertise/positionAdvertise/:pathParams',
          name: '位置广告',
          component: resolve=>require(['@/components/main/advertise/PositionAdvertise'],resolve)
        },
        {
          path: 'advertise/financeRecord/:pathParams',
          name: '财务管理',
          component: resolve=>require(['@/components/main/advertise/FinanceRecord'],resolve)
        },
        {
          path: 'advertise/account/:pathParams',
          name: '账户管理',
          component: resolve=>require(['@/components/main/advertise/Account'],resolve)
        },


        {
          path: 'media/index/:pathParams',
          name: '首页',
          component: resolve=>require(['@/components/main/media/Index'],resolve)
        },
        {
          path: 'media/media/:pathParams',
          name: '媒体管理',
          component: resolve=>require(['@/components/main/media/Media'],resolve)
        },
        {
          path: 'media/readMedia/:pathParams',
          name: '阅读接单',
          component: resolve=>require(['@/components/main/media/ReadMedia'],resolve)
        },
        {
          path: 'media/financeRecord/:pathParams',
          name: '财务管理',
          component: resolve=>require(['@/components/main/media/FinanceRecord'],resolve)
        },
        {
          path: 'media/account/:pathParams',
          name: '账户管理',
          component: resolve=>require(['@/components/main/media/Account'],resolve)
        },
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