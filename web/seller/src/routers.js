'use strict'

export default function(router){
    router.map({
        '/register':{//注册
            name:'register',
            component: function(resolve){
                require(['./views/login/register.vue'],resolve);
            }
        },
        '/login':{//登录
            name:'login',
            component: function(resolve){
                require(['./views/login/login.vue'],resolve);
            }
        },
        '/forget':{//忘记密码
            name:'forget',
            component: function(resolve){
                require(['./views/login/forget.vue'],resolve);
            }
        },
        '/':{//我的店铺
            name:'store',
            component: function(resolve){
                require(['./views/store/index.vue'],resolve);
            }
        },
        '/store':{//我的店铺
            name:'store',
            component: function(resolve){
                require(['./views/store/index.vue'],resolve);
            }
        },
        '/storeAddInfo':{//添加店铺信息
            name:'storeAddInfo',
            component: function(resolve){
                require(['./views/store/addinfo.vue'],resolve);
            }
        },
        '/storeSetting':{//店铺设置
            name:'storeSetting',
            component: function(resolve){
                require(['./views/store/setting.vue'],resolve);
            }
        },
        '/pdtAddSelf':{//添加自营商品
            name:'pdtAddSelf',
            component: function(resolve){
                require(['./views/product/addself.vue'],resolve);
            }
        },
        '/pdtModSelf':{//编辑自营商品
            name:'pdtModSelf',
            component: function(resolve){
                require(['./views/product/modself.vue'],resolve);
            }
        },
        '/order':{//我的订单
            name:'order',
            component: function(resolve){
                require(['./views/order/list.vue'],resolve);
            }
        },
        '/oddetail':{//订单详情
            name:'oddetail',
            component: function(resolve){
                require(['./views/order/detail.vue'],resolve);
            }
        },
        '/warehouse':{//我的仓库
            name:'warehouse',
            component: function(resolve){
                require(['./views/warehouse/list.vue'],resolve);
            }
        },
        '/category':{//添加发票类目
            name:'category',
            component: function(resolve){
                require(['./views/store/category.vue'],resolve);
            }
        },
        '/detail/:id':{ //商品详情
            name:'detail',
            component: function(resolve){
                require(['./views/product/detail.vue'],resolve);
            }

        },
        '/error/:id':{ //服务器错误友好也面
            name:'error',
            component: function(resolve){
                require(['./views/login/error.vue'],resolve);
            }

        },


    })
}