import App from '../App'

const home = r => require.ensure([], () => r(require('../page/home/home')), 'home')
const city = r => require.ensure([], () => r(require('../page/city/city')), 'city')
const msite = r => require.ensure([], () => r(require('../page/msite/msite')), 'msite')
const search = r => require.ensure([], () => r(require('../page/search/search')), 'search')
const shop = r => require.ensure([], () => r(require('../page/shop/shop')), 'shop')
const login = r => require.ensure([], () => r(require('../page/login/login')), 'login')
const profile = r => require.ensure([], () => r(require('../page/profile/profile')), 'profile')
const forget = r => require.ensure([], () => r(require('../page/forget/forget')), 'forget')
const order = r => require.ensure([], () => r(require('../page/order/order')), 'order')
const orderDetail = r => require.ensure([], () => r(require('../page/order/children/orderDetail')), 'orderDetail')
const vipcard = r => require.ensure([], () => r(require('../page/vipcard/vipcard')), 'vipcard')
const invoiceRecord = r => require.ensure([], () => r(require('../page/vipcard/children/invoiceRecord')), 'invoiceRecord')
const useCart = r => require.ensure([], () => r(require('../page/vipcard/children/useCart')), 'useCart')
const vipDescription = r => require.ensure([], () => r(require('../page/vipcard/children/vipDescription')), 'vipDescription')
const food = r => require.ensure([], () => r(require('../page/food/food')), 'food')
const confirmOrder = r => require.ensure([], () => r(require('../page/confirmOrder/confirmOrder')), 'confirmOrder')
const remark = r => require.ensure([], () => r(require('../page/confirmOrder/children/remark')), 'remark')
const payment = r => require.ensure([], () => r(require('../page/confirmOrder/children/payment')), 'payment')
const userValidation = r => require.ensure([], () => r(require('../page/confirmOrder/children/userValidation')), 'userValidation')
const invoice = r => require.ensure([], () => r(require('../page/confirmOrder/children/invoice')), 'invoice')
const chooseAddress = r => require.ensure([], () => r(require('../page/confirmOrder/children/chooseAddress')), 'chooseAddress')
const addAddress = r => require.ensure([], () => r(require('../page/confirmOrder/children/children/addAddress')), 'addAddress')
const searchAddress = r => require.ensure([], () => r(require('../page/confirmOrder/children/children/children/searchAddress')), 'searchAddress')
const foodDetail = r => require.ensure([], () => r(require('../page/shop/children/foodDetail')), 'foodDetail')
const shopDetail = r => require.ensure([], () => r(require('../page/shop/children/shopDetail')), 'shopDetail')
const shopSafe = r => require.ensure([], () => r(require('../page/shop/children/children/shopSafe')), 'shopSafe')
const info = r => require.ensure([], () => r(require('../page/profile/children/info')), 'info')
const setusername = r => require.ensure([], () => r(require('../page/profile/children/children/setusername')), 'setusername')
const address = r => require.ensure([], () => r(require('../page/profile/children/children/address')), 'address')
const add = r => require.ensure([], () => r(require('../page/profile/children/children/children/add')), 'add')
const addDetail = r => require.ensure([], () => r(require('../page/profile/children/children/children/children/addDetail')), 'addDetail')
const balance = r => require.ensure([], () => r(require('../page/balance/balance')), 'balance')
const balanceDetail = r => require.ensure([], () => r(require('../page/balance/children/detail')), 'balanceDetail')
const benefit = r => require.ensure([], () => r(require('../page/benefit/benefit')), 'benefit')
const coupon = r => require.ensure([], () => r(require('../page/benefit/children/coupon')), 'coupon')
const hbDescription = r => require.ensure([], () => r(require('../page/benefit/children/hbDescription')), 'hbDescription')
const hbHistory = r => require.ensure([], () => r(require('../page/benefit/children/hbHistory')), 'hbHistory')
const exchange = r => require.ensure([], () => r(require('../page/benefit/children/exchange')), 'exchange')
const commend = r => require.ensure([], () => r(require('../page/benefit/children/commend')), 'commend')
const points = r => require.ensure([], () => r(require('../page/points/points')), 'points')
const pointsDetail = r => require.ensure([], () => r(require('../page/points/children/detail')), 'pointsDetail')
const service = r => require.ensure([], () => r(require('../page/service/service')), 'service')
const questionDetail = r => require.ensure([], () => r(require('../page/service/children/questionDetail')), 'questionDetail')
const find = r => require.ensure([], () => r(require('../page/find/find')), 'find')
const download = r => require.ensure([], () => r(require('../page/download/download')), 'download')

//xiandaojia router
const xdjHome = r => require.ensure([], () => r(require('../page/xdjHome/xdjHome')), 'xdjHome')
const xdjProductDetail = r => require.ensure([], () => r(require('../page/xdjHome/children/xdjProductDetail')), 'xdjProductDetail')
const xdjTypeList = r => require.ensure([], () => r(require('../page/xdjHome/children/xdjTypeList')), 'xdjTypeList')
const xdjFlowersList = r => require.ensure([], () => r(require('../page/xdjHome/children/xdjFlowersList')), 'xdjFlowersList')
const xdjLogin = r => require.ensure([], () => r(require('../page/xdjLogin/xdjLogin')), 'xdjLogin')
const xdjRegeist = r => require.ensure([], () => r(require('../page/xdjRegeist/xdjRegeist')), 'xdjRegeist')
const xdjRegeistSetPwd = r => require.ensure([], () => r(require('../page/xdjRegeist/children/xdjRegeistSetPwd')), 'xdjRegeistSetPwd')
const xdjProfile = r => require.ensure([], () => r(require('../page/xdjProfile/xdjProfile')), 'xdjProfile')
const xdjshopCar = r => require.ensure([], () => r(require('../page/xdjshopCar/xdjshopcar')), 'xdjshopcar')
const xdjForget = r => require.ensure([], () => r(require('../page/xdjForget/xdjForget')), 'xdjForget')
const xdjForgetSetPwd = r => require.ensure([], () => r(require('../page/xdjForget/children/xdjForgetSetPwd')), 'xdjForgetSetPwd')
const xdjUpdatePassword = r => require.ensure([], () => r(require('../page/xdjProfile/children/xdjUpdatePassword')), 'xdjUpdatePassword')
const xdjInfo = r => require.ensure([], () => r(require('../page/xdjProfile/children/xdjInfo')), 'xdjInfo')
const xdjSetusername = r => require.ensure([], () => r(require('../page/xdjProfile/children/children/xdjSetusername')), 'xdjSetusername')
const xdjAddress = r => require.ensure([], () => r(require('../page/xdjProfile/children/children/xdjAddress')), 'xdjAddress')
const xdjAdd = r => require.ensure([], () => r(require('../page/xdjProfile/children/children/children/xdjAdd')), 'xdjAdd')
const xdjAddDetail = r => require.ensure([], () => r(require('../page/xdjProfile/children/children/children/children/xdjAddDetail')), 'xdjAddDetail')


export default [{
    path: '/',
    component: App, //顶层路由，对应index.html
    children: [ //二级路由。对应App.vue
        //地址为空时跳转home页面
        {
            path: '/',
            redirect: '/xdjHome'
        },
        /*//首页城市列表页
        {
            path: '/home',
            component: home
        },*/
        //当前选择城市页
        {
            path: '/city/:cityid',
            component: city
        },
        //所有商铺列表页
        {
            path: '/msite',
            component: msite,
            meta: { keepAlive: true },
        },
        //特色商铺列表页
        {
            path: '/food',
            component: food
        },
        //搜索页
        {
            path: '/search/:geohash',
            component: search
        },
        //商铺详情页
        {
            path: '/shop',
            component: shop,
            children: [{
                path: 'foodDetail', //食品详情页
                component: foodDetail,
            }, {
                path: 'shopDetail', //商铺详情页
                component: shopDetail,
                children: [{
                    path: 'shopSafe', //商铺安全认证页
                    component: shopSafe,
                }, ]
            }]
        },
        //确认订单页
        {
            path: '/confirmOrder',
            component: confirmOrder,
            children: [{
                path: 'remark', //订单备注
                component: remark,
            }, {
                path: 'invoice', //发票抬头
                component: invoice,
            }, {
                path: 'payment', //付款页面
                component: payment,
            }, {
                path: 'userValidation', //用户验证
                component: userValidation,
            }, {
                path: 'chooseAddress', //选择地址
                component: chooseAddress,
                children: [{
                    path: 'addAddress', //添加地址
                    component: addAddress,
                    children: [{
                        path: 'searchAddress', //搜索地址
                        component: searchAddress,
                    }]
                }, ]
            }, ]
        },
        //登录注册页
        {
            path: '/login',
            component: login
        },
        //个人信息页
        {
            path: '/profile',
            component: profile,
            children: [{
                path: 'info', //个人信息详情页
                component: info,
                children: [{
                    path: 'setusername',
                    component: setusername,
                },{
                    path: 'address',
                    component: address,     //编辑地址
                    children:[{
                        path:'add',
                        component:add,
                        children:[{
                            path:'addDetail',
                            component:addDetail
                        }]
                    }]
                }]
            },
            {
                path: 'service', //服务中心
                component: service,
            },]
        },
        //修改密码页
        {
            path: '/forget',
            component: forget
        },
        //订单列表页
        {
            path: '/order',
            component: order,
            children: [{
                path: 'orderDetail', //订单详情页
                component: orderDetail,
            }, ]
        },
        //vip卡页
        {
            path: '/vipcard',
            component: vipcard,
            children: [{
                path: 'invoiceRecord', //开发票
                component: invoiceRecord,
            }, {
                path: 'useCart', //购买会员卡
                component: useCart,
            }, {
                path: 'vipDescription', //会员说明
                component: vipDescription,
            },]
        },
        //发现页
        {
            path: '/find',
            component: find
        },
        //下载页
        {
            path: '/download',
            component: download
        },
        //服务中心
        {
            path: '/service',
            component: service,
             children: [{
                path: 'questionDetail', //订单详情页
                component: questionDetail,
            }, ]
        },
        //余额
        {
            path: 'balance',
            component: balance,
            children: [{
                path: 'detail', //余额说明
                component: balanceDetail,
            }, ]
        },
        //我的优惠页
        {
            path: 'benefit',
            component: benefit,
            children: [{
                path: 'coupon', //代金券说明
                component: coupon,
            }, {
                path: 'hbDescription', //红包说明
                component: hbDescription,
            }, {
                path: 'hbHistory', //历史红包
                component: hbHistory,
            }, {
                path: 'exchange', //兑换红包
                component: exchange,
            }, {
                path: 'commend', //推荐有奖
                component: commend,
            },]
        },
        //我的积分页
        {
            path: 'points',
            component: points,
            children: [{
                path: 'detail', //积分说明
                component: pointsDetail,
            }, ]
        },
        //鲜到家首页
        {
            path: '/xdjHome',
            component: xdjHome
        },
        //鲜到家产品详情
        {
          path: '/xdjProductDetail',
          component: xdjProductDetail,
        },
        //鲜到家产品详情
        {
          path: '/xdjTypeList',
          component: xdjTypeList,
        },
        // 我爱鲜花
        {
          path: '/xdjFlowersList',
          component: xdjFlowersList,
        },
        //登录页
        {
          path: '/xdjLogin',
          component: xdjLogin
        },
        //注册页
        {
          path: '/xdjRegeist',
          component: xdjRegeist
        },
        //注册页2
        {
          name:'xdjRegeistSetPwd',
          path: '/xdjRegeistSetPwd',
          component: xdjRegeistSetPwd
        },
        //忘记密码
        {
          path: '/xdjForget',
          component: xdjForget
        },
        //忘记密码2
        {
          name:'xdjForgetSetPwd',
          path: '/xdjForgetSetPwd',
          component: xdjForgetSetPwd
        },
        //用户中心
        {
          path: '/xdjProfile',
          component: xdjProfile
        },
        //个人信息
        {
          path: '/xdjProfile/xdjInfo',
          component: xdjInfo
        },
        //设置用户名
        {
          path: '/xdjProfile/xdjInfo/xdjSetusername',
          component: xdjSetusername
        },
        //地址信息
        {
          path: '/xdjProfile/xdjInfo/xdjAddress',
          component: xdjAddress
        },
        //新增地址
        {
          path: '/xdjProfile/xdjInfo/xdjAddress/xdjAdd',
          component: xdjAdd
        },
        //新增地址明细
        {
          path: '/xdjProfile/xdjInfo/xdjAddress/xdjAdd/xdjAddDetail',
          component: xdjAddDetail
        },
        //密码修改
        {
          path: '/xdjProfile/xdjUpdatePassword',
          component: xdjUpdatePassword
        },
        // 购物车组件
        {
          path: '/xdjshopCar',
          component: xdjshopCar
        }
    ]
}]
