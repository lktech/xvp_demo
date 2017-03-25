'use strict'
exports.routes = [
    {
        path: '/',
        redirect:'/home/home'
    },
    {//首页
        path: '/home',
        name:'home',
        component: function(resolve){
            require(['./views/home/index.vue'],resolve);
        },
        children:[
            {
                path:'home',
                name:'home',
                component: function(resolve){
                    require(['./views/home/home.vue'],resolve);
                }
            },
        ]
    },
    {//商品详情
        path: '/product',
        name:'product',
        component: function(resolve){
            require(['./views/product/index.vue'],resolve);
        },
        children:[
            {
                path:'detail',
                name:'detail',
                component: function(resolve){
                    require(['./views/product/detail.vue'],resolve);
                }
            },
        ]
    },
    {//提交订单
        path: '/order',
        name:'order',
        component: function(resolve){
            require(['./views/order/index.vue'],resolve);
        },
        children:[
            {
                path:'add',
                name:'add',
                component: function(resolve){
                    require(['./views/order/add.vue'],resolve);
                }
            },
            {
                path:'list',
                name:'list',
                component: function(resolve){
                    require(['./views/order/list.vue'],resolve);
                }
            },
            {
                path:'detail',
                name:'detail',
                component: function(resolve){
                    require(['./views/order/detail.vue'],resolve);
                }
            },
        ]
    },
    {//地址
        path: '/addr',
        name:'addr',
        component: function(resolve){
            require(['./views/addr/index.vue'],resolve);
        },
        children:[
            {
                path:'add',
                name:'add',
                component: function(resolve){
                    require(['./views/addr/add.vue'],resolve);
                }
            },
            {
                path:'mod',
                name:'mod',
                component: function(resolve){
                    require(['./views/addr/mod.vue'],resolve);
                }
            }
        ]
    }
    
];
