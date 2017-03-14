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
router.beforeEach((transition) => {
    const toIndex = history.getItem(transition.to.path)
    const fromIndex = history.getItem(transition.from.path)
    if (toIndex) {
        if (toIndex > fromIndex) {
            commit('UPDATE_DIRECTION', 'forward')
        } else {
            commit('UPDATE_DIRECTION', 'reverse')
        }
    } else {
        ++historyCount
        history.setItem('count', historyCount)
        transition.to.path !== '/' && history.setItem(transition.to.path, historyCount)
        commit('UPDATE_DIRECTION', 'forward')
    }
    if(transition.to.name == "store" || transition.to.name == "detail"){
        let url = transition.to.path;
        if(!transition.to.query.xv){
            if(url.indexOf("?") > -1){
                url = url.split("?")[0];
            }
            url = url + "?xv=enter";
            transition.redirect(url);
        }
        
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
    if(transition.to.name == "login"){
        document.setTitle('会员登录');
    }
    if(transition.to.name == "forget"){
        document.setTitle('忘记密码');
    }
    if(transition.to.name == "register"){
        document.setTitle('商家注册');
    }
    if(transition.to.name == "oddetail"){
        document.setTitle('订单详情');
    }
    if(transition.to.name == "order"){
        document.setTitle('我的订单');
    }
    if(transition.to.name == "pdtAddSelf"){
        document.setTitle('添加自营商品');
    }
    if(transition.to.name == "detail"){
        document.setTitle('商品详情');
    }
    if(transition.to.name == "pdtModSelf"){
        document.setTitle('编辑自营商品');
    }
    if(transition.to.name == "storeAddInfo"){
        document.setTitle('填写店铺信息');
    }
    if(transition.to.name == "category"){
        document.setTitle('发票类目');
    }
    if(transition.to.name == "storeSetting"){
        document.setTitle('店铺设置');
    }
    if(transition.to.name == "warehouse"){
        document.setTitle('我的仓库');
    }
    setTimeout(transition.next,50);
});
router.afterEach(() => {
    //commit('UPDATE_LOADING', false)
});

sync(store, router)

routerMap(router);

router.start(App, "#app");
