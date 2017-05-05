'use strict'
exports.routes = [
    {
        path: '/',
        redirect: '/index'
    },
    {//我的店铺
        path: '/index',
        name: 'index',
        component: function (resolve) {
            require(['./views/index.vue'], resolve);
        }
    },
    {//我的余额
        path: '/balance',
        name: 'balance',
        component: function (resolve) {
            require(['./views/balance.vue'], resolve);
        }
    },
    {//提现绑定
        path: '/txBind',
        name: 'txBind',
        component: function (resolve) {
            require(['./views/txBind.vue'], resolve);
        }
    },
    {//实名认证
        path: '/verifyInfo',
        name: 'verifyInfo',
        component: function (resolve) {
            require(['./views/verifyInfo.vue'], resolve);
        }
    },
    {//提现绑卡
        path: '/bindCard',
        name: 'bindCard',
        component: function (resolve) {
            require(['./views/bindCard.vue'], resolve);
        }
    },
    {//个人实名认证
        path: '/personal',
        name: 'personal',
        component: function (resolve) {
            require(['./views/personal/index.vue'], resolve);
        },
        children: [
            {
                path: 'cashrecord',
                name: 'cashrecord',
                component: function (resolve) {
                    require(['./views/personal/cashrecord.vue'], resolve);
                }
            },
            {
                path: 'withdrawals',
                name: 'withdrawals',
                component: function (resolve) {
                    require(['./views/personal/withdrawals.vue'], resolve);
                }
            },
        ]
    },
];
