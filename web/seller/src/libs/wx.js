'use strict'
import store from '../vuex/store'


/**
 * @about 微信jssdk初始化
 * @param
 *      user:user信息
 * @return 无
 * @author bob
 */
exports.init = function (config,fn) {
    var isDebug = config.isDebug;

    var pageUrl = window.location.href;
    if(pageUrl.indexOf('#') != -1){
        pageUrl = pageUrl.substring(0,url.indexOf('#'));
    }
    if (isDebug == true) {
        alert("开始远程获取微信配置，获取地址：get_config_url:" + config.url);
        alert("请求参数：{appid:" + config.appid + ",pageUrl:" + pageUrl + "}");
    }

    $.ajax({
        type: "get",
        dataType:"json",
        async: false,
        url: config.url,
        data: {
            app: config.appid,
            url: pageUrl
        },
        success: function(data) {
            data["jsApiList"] = jsApiList;
            if (isDebug == true) {
                alert("拉取JSAPI配置成功：" + JSON.stringify(data));
            }
            wx.config(data);

            wx.ready(function(){
                if (isDebug == true) {
                    alert("微信初始化成功");
                }
                if(fn){
                    fn();
                }
            });

            wx.error(function(res){
                if (isDebug == true) {
                    alert('微信初始化失败:'+JSON.stringify(res));
                }
            });
        },
        error: function() {
            if (isDebug == true) {
                alert('拉取JSAPI配置失败');
            }

        }
    });

/**
 * 分享朋友圈
 * params:
 *      config:{title:"1",link:"2",imgUrl:"3"，desc:"123"}，参数同微信参数
 *      callback
 *      error
 * */
exports.onMenuShareTimeline = function(config,callback,error){
    wx.onMenuShareTimeline({
        title: config.title, // 分享标题
        link: config.link,
        imgUrl: config.img, // 分享图标
        success: function(res) {
            if(res){
                callback(res);
            }
        },
        cancel: function(res) {
            if(error){
                error(res);
            }
        }
    });
}

/**
 * 发送朋友
 * params:
 *      config:{title:"1",link:"2",imgUrl:"3"，desc:"123"}，参数同微信参数
 *      callback
 *      error
 * */
exports.onMenuShareTimeline = function(config,callback,error){
    wx.onMenuShareTimeline({
        title: config.title, // 分享标题
        link: config.link,
        imgUrl: config.img, // 分享图标
        success: function(res) {
            if(res){
                callback(res);
            }
        },
        cancel: function(res) {
            if(error){
                error(res);
            }
        }
    });
}

    /**
     * 分享朋友圈
     * params:
     *      config:{title:"1",link:"2",imgUrl:"3"，desc:"123"}，参数同微信参数
     *      callback
     *      error
     * */
    exports.share = function(config,callback,error){
        //分享朋友圈
        wx.onMenuShareTimeline({
            title: config.title, // 分享标题
            link: config.link,
            imgUrl: config.imgUrl, // 分享图标
            success: function(res) {
                if(res){
                    callback(res);
                }
            },
            cancel: function(res) {
                if(error){
                    error(res);
                }
            }
        });
        //分享朋友
        wx.onMenuShareAppMessage({
            title: config.title, // 分享标题
            desc: config.desc, // 分享描述
            link: config.link, // 分享链接
            imgUrl: config.imgUrl, // 分享图标
            type: config.type?config.type:'', // 分享类型,music、video或link，不填默认为link
            dataUrl: config.dataUrl?config.dataUrl:'', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });


    }

    /**
     * 分享朋友圈
     * params:
     *      config:{title:"1",link:"2",imgUrl:"3"，desc:"123"}，参数同微信参数
     *      callback
     *      error
     * */
exports.share = function(config,callback,error){
    //分享朋友圈
    wx.onMenuShareTimeline({
        title: config.title, // 分享标题
        link: config.link,
        imgUrl: config.imgUrl, // 分享图标
        success: function(res) {
            if(res){
                callback(res);
            }
        },
        cancel: function(res) {
            if(error){
                error(res);
            }
        }
    });
    //分享朋友
    wx.onMenuShareAppMessage({
        title: config.title, // 分享标题
        desc: config.desc, // 分享描述
        link: config.link, // 分享链接
        imgUrl: config.imgUrl, // 分享图标
        type: config.type?config.type:'', // 分享类型,music、video或link，不填默认为link
        dataUrl: config.dataUrl?config.dataUrl:'', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });


}


}

