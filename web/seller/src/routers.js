'use strict'
exports.routes = [
    {
        path: '/',
        redirect: '/store/store'
    },
    {//登录
        path: '/login',
        name: 'login',
        component: function (resolve) {
            require(['./views/login/login.vue'], resolve);
        }
    },
    {//注册
        path: '/register',
        name: 'register',
        component: function (resolve) {
            require(['./views/login/register.vue'], resolve);
        }
    },
    {//忘记密码
        path: '/forget',
        name: 'forget',
        component: function (resolve) {
            require(['./views/login/forget.vue'], resolve);
        }
    },
    {//店铺
        path: '/store',
        name: 'store',
        component: function (resolve) {
            require(['./views/store/index.vue'], resolve);
        },
        children: [
            {
                path: 'addinfo',
                name: 'addinfo',
                component: function (resolve) {
                    require(['./views/store/addinfo.vue'], resolve);
                }
            },
            {
                path: 'storeInfo',
                name: 'storeInfo',
                component: function (resolve) {
                    require(['./views/store/store.vue'], resolve);
                }
            },
            {
                path: 'setting',
                name: 'setting',
                component: function (resolve) {
                    require(['./views/store/setting.vue'], resolve);
                }
            }
        ]
    },
    {//商品
        path: '/product',
        name: 'product',
        component: function (resolve) {
            require(['./views/product/index.vue'], resolve);
        },
        children: [

            {
                path: 'addself',
                name: 'addself',
                component: function (resolve) {
                    require(['./views/product/addself.vue'], resolve);
                }
            },
            {
                path: 'modself',
                name: 'modself',
                component: function (resolve) {
                    require(['./views/product/modself.vue'], resolve);
                }
            },
            {
                path: 'warehouse',
                name: 'warehouse',
                component: function (resolve) {
                    require(['./views/product/warehouse.vue'], resolve);
                }
            },
            {
                path: 'detail',
                name: 'detail',
                component: function (resolve) {
                    require(['./views/product/detail.vue'], resolve);
                }
            }
        ]
    },
    {//订单
        path: '/order',
        name: 'order',
        component: function (resolve) {
            require(['./views/order/index.vue'], resolve);
        },
        children: [
            {
                path: 'list',
                name: 'list',
                component: function (resolve) {
                    require(['./views/order/list.vue'], resolve);
                }
            },
            {
                path: 'detail',
                name: 'detail',
                component: function (resolve) {
                    require(['./views/order/detail.vue'], resolve);
                }
            },
            {
                path: 'logistics',
                name: 'logistics',
                component: function (resolve) {
                    require(['./views/order/logistics.vue'], resolve);
                }
            }
        ]
    },
    // {//订单
    //     path: '/withdra',
    //     name: 'withdra',
    //     component: function (resolve) {
    //         require(['./views/withdra/index.vue'], resolve);
    //     },
    //     children: [
    //
    //         {
    //             path: 'withdra',    // 提现
    //             name: 'withdra',
    //             component: function (resolve) {
    //                 require(['./views/withdra/withdra.vue'], resolve);
    //             }
    //         },
    //         {
    //             path: 'agreem',      // 协议
    //             name: 'agreem',
    //             component: function (resolve) {
    //                 require(['./views/withdra/agreem.vue'], resolve);
    //             }
    //         },
    //         {
    //             path: 'balance',    // 余额
    //             name: 'balance',
    //             component: function (resolve) {
    //                 require(['./views/withdra/balance.vue'], resolve);
    //             }
    //         },
    //         {
    //             path: 'binding',    // 绑卡
    //             name: 'binding',
    //             component: function (resolve) {
    //                 require(['./views/withdra/binding.vue'], resolve);
    //             }
    //         },
    //         {
    //             path: 'realname',    // 实名认证
    //             name: 'realname',
    //             component: function (resolve) {
    //                 require(['./views/withdra/realname.vue'], resolve);
    //             }
    //         },
    //         {
    //             path: 'record',    // 提现记录
    //             name: 'record',
    //             component: function (resolve) {
    //                 require(['./views/withdra/record.vue'], resolve);
    //             }
    //         }
    //     ]
    // },
    {//提现
        path: '/tx',
        name: 'tx',
        component: function (resolve) {
            require(['./views/tx/index.vue'], resolve);
        },
        children: [
            {
                path: 'cashrecord',
                name: 'cashrecord',
                component: function (resolve) {
                    require(['./views/tx/cashrecord.vue'], resolve);
                }
            },
            {
                path: 'withdrawals',
                name: 'withdrawals',
                component: function (resolve) {
                    require(['./views/tx/withdrawals.vue'], resolve);
                }
            },
            {//我的店铺
                path: 'myStore',
                name: 'myStore',
                component: function (resolve) {
                    require(['./views/tx/myStore.vue'], resolve);
                }
            },
            {//我的余额
                path: 'balance',
                name: 'balance',
                component: function (resolve) {
                    require(['./views/tx/balance.vue'], resolve);
                }
            },
            {//提现绑定
                path: 'txBind',
                name: 'txBind',
                component: function (resolve) {
                    require(['./views/tx/txBind.vue'], resolve);
                }
            },
            {//实名认证
                path: 'verifyInfo',
                name: 'verifyInfo',
                component: function (resolve) {
                    require(['./views/tx/verifyInfo.vue'], resolve);
                }
            },
            {//提现绑卡
                path: 'bindCard',
                name: 'bindCard',
                component: function (resolve) {
                    require(['./views/tx/bindCard.vue'], resolve);
                }
            }
        ]
    },
];
