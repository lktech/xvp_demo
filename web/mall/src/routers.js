'use strict'

export default function(router){
    router.map({
        '/':{//首页
            name:'/',
            component: function(resolve){
                require(['./views/home/index.vue'],resolve);
            }
        },
        '/home/:id':{//首页
            name:'home',
            component: function(resolve){
                require(['./views/home/index.vue'],resolve);
            }
        },
        '/detail/:id':{ //商品详情
            name:'detail',
            component: function(resolve){
                require(['./views/product/detail.vue'],resolve);
            }

        },
        '/order':{   //订单列表
            name:'order',
            component: function(resolve){
                require(['./views/order/list.vue'],resolve);
            }
        },
        '/odadd':{   //订单提交
            name:'odadd',
            component: function(resolve){
                require(['./views/order/add.vue'],resolve);
            }
        },
        '/oddetail/:id':{ //订单详情
            name:'oddetail',
                component: function(resolve){
                require(['./views/order/detail.vue'],resolve);
            }
        },
        '/cart':{ //购物车
            name:'cart',
                component: function(resolve){
                require(['./views/cart/index.vue'],resolve);
            }
        },
        '/user':{ //我的
            name:'user',
            component: function(resolve){
                require(['./views/user/index.vue'],resolve);
            }
        },
        '/ukf':{ //联系客服
            name:'ukf',
            component: function(resolve){
                require(['./views/user/kf.vue'],resolve);
            }
        },
        '/addr':{ //地址列表
            name:'addr',
            component: function(resolve){
                require(['./views/addr/list.vue'],resolve);
            }
        },
        '/addradd':{ //地址新增
            name:'addradd',
            component: function(resolve){
                require(['./views/addr/add.vue'],resolve);
            }
        },
        '/addrmod/:id':{ //地址编辑
            name:'addrmod',
            component: function(resolve){
                require(['./views/addr/mod.vue'],resolve);
            }
        },
        '/refund/:id':{  //提交退款申请
            name:'refund',
            component: function(resolve){
                require(['./views/refund/index.vue'],resolve);
            }
        },
        '/handing/:id':{  //退款处理中
            name:'handing',
            component: function(resolve){
                require(['./views/refund/handing.vue'],resolve);
            }
        },
        '/invoice':{  //选择发票
            name:'invoice',
            component: function(resolve){
                require(['./views/order/invoice.vue'],resolve);
            }
        },
        '/attesta':{  //实名认证
            name:'attesta',
            component: function(resolve){
                require(['./views/account/attesta.vue'],resolve);
            }
        },
        '/success/:money/:way/:pageId':{  //支付成功
            name:'success',
            component: function(resolve){
                require(['./views/order/success.vue'],resolve);
            }
        },
        '/binding':{  //提现绑定
            name:'binding',
            component: function(resolve){
                require(['./views/account/binding.vue'],resolve);
            }
        },
        '/agreement':{  //绑卡协议
            name:'agreement',
            component: function(resolve){
                require(['./views/account/agreement.vue'],resolve);
            }
        },
        '/withdrawals':{  //提现
            name:'withdrawals',
            component: function(resolve){
                require(['./views/account/withdrawals.vue'],resolve);
            }
        },
        '/cashrecord':{  //提现记录
            name:'cashrecord',
            component: function(resolve){
                require(['./views/account/cashrecord.vue'],resolve);
            }
        },
        '/account':{  //余额
            name:'account',
            component: function(resolve){
                require(['./views/account/index.vue'],resolve);
            }
        }

    })
}