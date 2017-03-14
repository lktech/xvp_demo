'use strict'

import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './views/App.vue'
import filters from './libs/filters';
import utils from './libs/utils';
import routerMap from './routers';
import ToastPlugin from './plugins/toast.js';
import AlertPlugin from './plugins/alert.js';
import ConfirmPlugin from './plugins/confirm.js';
import VueLazyload from 'vue-lazyload';

import { sync } from 'vuex-router-sync'
import store from './vuex/store'

Vue.use(VueRouter);
Vue.use(ToastPlugin);
Vue.use(AlertPlugin);
Vue.use(ConfirmPlugin);
Vue.use(VueLazyload, {
    preLoad: 1.3,
    error: utils.defaultImage,
    loading: utils.defaultImage,
    attempt: 1
});


//实例化Vue的filter
Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));

//实例化VueRouter
let router = new VueRouter({
    hashbang: true,
    history: false,
    saveScrollPosition: true,
    transitionOnLoad: true
});

//登录中间验证，页面需要登录而没有登录的情况直接跳转登录
/*
router.beforeEach((transition) => {
    if (transition.to.auth) {
        if (localStorage.userId) {
            transition.next();
        } else {
            var redirect = encodeURIComponent(transition.to.path);
            transition.redirect('/login?redirect=' + redirect);
        }
    } else {
        transition.next();
    }
});
 */

let history = window.sessionStorage
history.clear()
let historyCount = history.getItem('count') * 1 || 0
history.setItem('/', 0)

/**
 * sync router loading status
 */
const commit = store.commit || store.dispatch
router.beforeEach(({ to, from, next }) => {
    const toIndex = history.getItem(to.path)
    const fromIndex = history.getItem(from.path)
    if (toIndex) {
        if (toIndex > fromIndex) {
            commit('UPDATE_DIRECTION', 'forward')
        } else {
            commit('UPDATE_DIRECTION', 'reverse')
        }
    } else {
        ++historyCount
        history.setItem('count', historyCount)
        to.path !== '/' && history.setItem(to.path, historyCount)
        commit('UPDATE_DIRECTION', 'forward')
    }
    document.setTitle = function(t) {
      document.title = t;
      var i = document.createElement('iframe');
      i.src = '//m.baidu.com/favicon.ico';
      i.style.display = 'none';
      i.onload = function() {
        setTimeout(function(){
          i.remove();
        }, 9)
      }
      document.body.appendChild(i);
    }
    if(to.name == "home"){
        document.setTitle('店铺首页');
    }
    if(to.name == "detail"){
        document.setTitle('商品详情');
    }
    if(to.name == "odadd"){
        document.setTitle('待付款的订单');
    }
    if(to.name == "success"){
        document.setTitle('支付成功');
    }
    if(to.name == "invoice"){
        document.setTitle('发票类型');
    }
    if(to.name == "addradd"){
        document.setTitle('新增发票邮寄地址');
    }
    if(to.name == "addrmod"){
        document.setTitle('修改发票邮寄地址');
    }
    setTimeout(next,50);
});
router.afterEach(() => {
    //commit('UPDATE_LOADING', false)
});

sync(store, router)

routerMap(router);



// $xvpAuth.then(function(ok) {
//     if (ok) {
        router.start(App, "#app");
//     }
// });