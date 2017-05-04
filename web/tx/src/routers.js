'use strict'
exports.routes = [
    {
        path: '/',
        redirect:'/index'
    },
    {//我的店铺
        path: '/index',
        name:'index',
        component: function(resolve){
            require(['./views/index.vue'],resolve);
        }
    },
    {//个人实名认证
        path: '/personal',
        name:'personal',
        component: function(resolve){
            require(['./views/personal/index.vue'],resolve);
        },
        children:[
            {
                path:'balance',
                name:'balance',
                component: function(resolve){
                    require(['./views/personal/balance.vue'],resolve);
                }
            },
            {
                path:'cashrecord',
                name:'cashrecord',
                component: function(resolve){
                    require(['./views/personal/cashrecord.vue'],resolve);
                }
            },
            {
                path:'withdrawals',
                name:'withdrawals',
                component: function(resolve){
                    require(['./views/personal/withdrawals.vue'],resolve);
                }
            },{
                path:'verifyInfo',
                name:'verifyInfo',
                component: function(resolve){
                    require(['./views/personal/verifyInfo.vue'],resolve);
                }
            }
        ]
    },
];
