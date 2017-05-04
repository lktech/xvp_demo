'use strict'
import Vue from 'vue';
import VueRouter from 'vue-router';
import routerMap from './routers';
import App from './views/App.vue'
import filters from './libs/filters';
import utils from './libs/utils';
import AlertPlugin from './plugins/alert/index.js';
import ConfirmPlugin from './plugins/confirm/index.js';
import ToastPlugin from './plugins/toast/index.js';
import LoadingPlugin from './plugins/loading/index.js';
import VueLazyload from 'vue-lazyload';
import store from './vuex/store';
import directives from './libs/directives';
//import plugins from './plugins/index.js';

Vue.use(VueRouter);
Vue.use(AlertPlugin);
Vue.use(ConfirmPlugin);
Vue.use(ToastPlugin);
Vue.use(LoadingPlugin);
Vue.use(VueLazyload, {
    preLoad: 1.3,
    error: utils.defaultImage,
    loading: utils.defaultImage,
    attempt: 1
});
directives(Vue);

//Vue.use(plugins);

//实例化Vue的filter
Object.keys(filters).forEach(k => Vue.filter(k, filters[k]));

const router = new VueRouter({
    mode: 'hash',
    base: __dirname,
    routes: routerMap.routes,
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        } else {
            return {x: 0, y: 0};
        }
    }
});

const history = window.sessionStorage
var param, subscribe, isLecturer, previewsObj,shareUrlHead,wxmpId;
if (sessionStorage.param) {
    param = sessionStorage.param;
}
if (sessionStorage.subscribe) {
    subscribe = sessionStorage.subscribe;
}
if (sessionStorage.isLecturer) {
    isLecturer = sessionStorage.isLecturer;
}
if (sessionStorage.previewsObj) {
    previewsObj = sessionStorage.previewsObj;
}
if (sessionStorage.shareUrlHead) {
    shareUrlHead = sessionStorage.shareUrlHead;
}
if( sessionStorage.wxmpId){
    wxmpId=sessionStorage.wxmpId;
}
history.clear()
sessionStorage.param = param;
sessionStorage.subscribe = subscribe;
sessionStorage.isLecturer = isLecturer;
sessionStorage.previewsObj = previewsObj;
sessionStorage.shareUrlHead = shareUrlHead;
sessionStorage.wxmpId=wxmpId;
let historyCount = history.getItem('count') * 1 || 0
history.setItem('/', 0)

//初始化微信sdk
// utils.wxConfig();


router.beforeEach((to, from, next) => {
    utils.loadingShow();
    const toIndex = history.getItem(to.path)
    const fromIndex = history.getItem(from.path)

    if (toIndex) {
        if (toIndex > fromIndex || !fromIndex || (toIndex === '0' && fromIndex === '0')) {
            store.commit('UPDATE_DIRECTION', 'forward')
        } else {
            store.commit('UPDATE_DIRECTION', 'reverse')
        }
    } else {
        ++historyCount
        history.setItem('count', historyCount)
        to.path !== '/' && history.setItem(to.path, historyCount)
        store.commit('UPDATE_DIRECTION', 'forward')
    }
    next();
});
router.afterEach(route => {
    utils.loadingHide();
    window.scrollTo(0, 0);
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
