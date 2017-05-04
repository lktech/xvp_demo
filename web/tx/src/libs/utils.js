'use strict'
import store from '../vuex/store'

/**
 * @about  默认图
 * @param 无
 * @return 无
 * @author bob
 */
exports.defaultImage = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUAAAAFACAYAAADNkKWqAAAWcElEQVR4Xu3diW4bxxJGYSr7vjp5/wfyeyRx9n2PL35eVNBpNDeLtkjWJ4CwLJHDqVOlg+plhnePHz9+utlsNk+fPt3c3d3lW18IIIBACwJ3BNgiz4JEAIEFgX8FmN/pANUIAgh0IvCvAMmvU9rFigAC26avhsAEqCAQQKAbAUPgbhkXLwII/EtAB6gYEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAE2roGnT59u7u7utgTyfX3Vz+rn4/9vDdfI4NZiE89hAgR4mNHNPmMlwFl2syBGUV47mMRa8dyy5K89T8/z/AnwedK9gmPvEsDq5/Wza5Xg2O3W96MEx3QR4hUU7xlOkQDPAPHaDrEa7h4SYWJ86aWXto9xyDwPl/O7S5LHHNcY+z///KMDvLbiPfP5EuCZgV7D4WYBzh3dPC/48ssvb1599dXt45VXXtkK7hSJPiSTXd1q5PfXX39tfv/9983ff/+9jeeSxP2QzDq9NwF2yvYQ6zHD2JLCW2+9tXnvvfc2+fe1117bK4pzSmTXwsypKZuH7jnHyO+3337bfP/995tff/1188cff/wnrnPGcer5ev6LI0CAL471xbxTiW2XBPPHnw4p/6bre//99zcff/zx9vt0g9f4NXd41QH++OOPmx9++GH7MAd4jZm93zkT4P34XeWrx3mxEt38x5+fZ77vzTff3Mrv0aNH26fk53/++ed/uqVRpJe8qjoufGQon690gV9//fXmyy+/JMCrrOb7nTQB3o/fVb760PC3OsB0e++8887mo48+2j4ivl9++WU7bMy82errkgWYc0tMGcZnSB+5Z+gbAT558sSCyFVW8/1OmgDvx+8qX32sANMBRhSR34cffrhdMMhQMbIoAa42UheU+8yjrc5xtY3llASke03nF/Glo43cI/USYH5/jnM/5Zw892EJEODD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4nnPjwDPy/NqjnZoL2AtiOQqjMyXPXr0aDt3mJsLZCi8+rr0RYSKKRLMZXb5N1eW5DNIchfp8UsHeDWlfK8TJcB74bvOF4975lZ792obTKLL9pEIMDcPSOeU/X638FUyz3XF9bnEtaFbF3gLGT4uBgI8jtNNPetQ9zcPh+suzLl2dr4F/Woe8FIEMsc5dnXpZDP8rbva5Pvx9zrAmyr5ncEQYI88/yfKUwSY59aNSDNsrC7w0BzgJQjkkAAzp5kbLMy38b8UgTcszRceMgG+cOSX8Yar+brVau78s0sQ2zkIJq56zNcy30qM5+B068cgwFvP8CK+cQ5w1xaWUZDHbnO5pEWQQ9t8Vr+/1D2NDUv0hYVMgC8M9eW80akCPPbML0mAOec5zn2d3SnPPZaH510+AQK8/Bw9lzM8ZQh8y0PCVad7y/E+l2K64oMS4BUn73mc+qV1cc8jRsdEoAgQoFpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIECAagABBNoSIMC2qRc4AggQoBpAAIG2BAiwbeoFjgACBKgGEECgLQECbJt6gSOAAAGqAQQQaEuAANumXuAIIPA/yu9euh7u2+cAAAAASUVORK5CYII=';
exports.spaceGif = "data:image/png;base64,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";

/**
 * @about 根据url判断是内部跳转还是跳出页面，并进行跳转
 * @param
 *      url:{链接(http://www.baidu.com),内部跳转({name:'test'})},
 *      $router:路由
 * @return 无返回值，进行页面跳转
 * @author bob
 */
exports.go = function (url, $router, replace) {
    if (/^javas/.test(url) || !url) {
        return;
    }
    const useRouter = typeof url === 'object' || ($router && typeof url === 'string' && !/http/.test(url));
    if (useRouter) {
        if (replace || url.urlreplace) {
            $router.replace(url);
        } else {
            $router.push(url);
        }

    } else {
        window.open(url, '_blank');
    }
}

/**
 * @about 根据url判断是内部跳转还是跳出页面，并进行跳转
 * @param
 *      url:{链接(http://www.baidu.com),内部跳转({name:'test'})},
 *      $router:路由
 * @return 无返回值，进行页面跳转
 * @author bob
 */
exports.back = function ($router, step) {
    let stp = step ? -step : -1;
    $router.go(stp);
}


/**
 * @about 更新sessionStorage user信息
 * @param
 *      user:user信息
 * @return 无
 * @author bob
 */
exports.setUser = function (user) {
    store.commit('UPDATE_USER', user);
    if (typeof user == "object") {
        user = JSON.stringify(user);
    }
    window.sessionStorage.setItem('user', user);
}

/**
 * @about 显示loading
 * @param
 *      text:显示文字，选填，默认值  '拼命加载中'
 * @return 无
 * @author bob
 */
exports.loadingShow = function (text) {
    let txt = text || "拼命加载中";
    store.commit("UPDATE_LOADING", {"isshow": true, "text": txt});
}
/**
 * @about 隐藏loading
 * @param 无
 * @return 无
 * @author bob
 */
exports.loadingHide = function () {
    store.commit("UPDATE_LOADING", {"isshow": false, "text": "拼命加载中"});
}


/**
 * @about 操作sessionStorage
 * @param 无
 * @return 无
 * @author bob
 */
exports.setSession = function (name, value) {
    if (typeof value == "object") {
        value = JSON.stringify(value);
    }
    sessionStorage.setItem(name, value);
}

exports.getSession = function (name) {
    return sessionStorage.getItem(name);
}
exports.delSession = function (name) {
    if (sessionStorage.getItem(name)) {
        sessionStorage.removeItem(name);
    }
}


/**
 * @about 格式化时间
 * @param
 *      date:{number或date}毫秒时间，必填
 *      fmt:{string}需要生成的时间格式，选填 默认  MM-dd hh:mm
 * @return string   格式化时间字符串
 * @author bob
 */
exports.formatTime = function (date, format) {
    if (typeof date == "number") {
        date = new Date(date);
        var o = {
            "M+": date.getMonth() + 1, //月份
            "d+": date.getDate(), //日
            "h+": date.getHours(), //小时
            "m+": date.getMinutes(), //分
            "s+": date.getSeconds(), //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        var fmt = format || "yyyy-MM-dd hh:mm:ss";
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    } else {
        return date;
    }

}

/**
 * @about 格式化价格
 * @param
 *      s:{string}  要转化的价格的数目，单位 “分”
 * @return string   格式化的价格字符串
 * @author bob
 */
exports.formatPrice = function (s) {
    var n = 2;
    s = parseInt(s) / 100 + "";
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    var t = "";
    for (var i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}


/**
 * @about 包含loading的显隐
 * @param
 *      obj:{
*             "url":"/app/demo",//请求url，必填
*             "dataType":"json",//数据格式，选填，默认json
*             "type":"POST",//请求类型，选填，默认POST
*             "data":{},//请求参数，选填，默认{}
*             "success":function(){},//成功回调，选填，默认function(){}
*             "error":function(){},//error回调，选填，默认function(){}
*             "async": true,//是否异步，选填，默认true
*             "message": '拼命加载中',//请求时的文字，选填，默认  拼命加载中
*             "stringify": 提交数据是否需要stringify，默认不stringify
*           }
 * @return 无
 * @author bob
 */
exports.ajax = function (obj) {
    if (!obj.url) {
        return;
    }
    let bpath = basepath;
    if (obj.aq) {     // http://demomp.hb.xiaov.cc/hbrobot_aq/course/chapter/lecturerIndex
        bpath = basepath2;
    }
    if (obj.ignoreBase) {
        bpath = "";
    }


    exports.loadingShow(obj.message);
    let url = bpath + obj.url,
        dataType = obj.dataType ? obj.dataType : 'json',
        contentType = obj.contentType ? obj.contentType : 'application/json',
        type = obj.type ? obj.type : 'POST',
        async = obj.async === false ? false : true,
        params = obj.data ? obj.data : {};
    let rdm = Math.random();
    //url += url.indexOf('?') == -1 ? '?r=' + rdm : '&r=' + rdm;
    $.ajax({
        url: url,
        dataType: dataType,
        //contentType: contentType,
        type: type,
        async: async,
        data: params,
        success: function (data) {
            exports.loadingHide();
            if (data.success) {
                if (obj.success) {
                    obj.success(data);
                }
            } else {
                if (data.code == "403") {
                    //Notification.error({
                    //    title: '错误',
                    //    message: '您的账号已被禁用，请联系客服'
                    //});
                } else if (data.code == "401") {
                    exports.setUser("");
                    //Notification.error({
                    //    title: '错误',
                    //    message: '您尚未登录，请登录'
                    //});
                } else {
                    //Notification.error({
                    //    title: '错误',
                    //    message: data.msg
                    //});
                    if (obj.success) {
                        obj.success(data);
                    }
                }
            }
        },
        error: function (o, xhr, property) {
            exports.loadingHide();
            if (obj.error) {
                obj.error(o, xhr, property);
            } else {
                //Notification.error({
                //    title: '错误',
                //    message: '网络繁忙，请稍后再试'
                //});
            }

        }
    });
}
/**
 * @about emoji表情转换
 * @param
 *      value:{string} 消息内容，必填
 * @return string   带emoji表情的消息
 * @author bob
 */
exports.emoji = function (value) {
    var qqfaceMap = {
        "[微笑]": "0",
        "[撇嘴]": "1",
        "[色]": "2",
        "[发呆]": "3",
        "[得意]": "4",
        "[流泪]": "5",
        "[害羞]": "6",
        "[闭嘴]": "7",
        "[睡]": "8",
        "[大哭]": "9",
        "[尴尬]": "10",
        "[发怒]": "11",
        "[调皮]": "12",
        "[呲牙]": "13",
        "[惊讶]": "14",
        "[难过]": "15",
        "酷": "16",
        "[冷汗]": "17",
        "[抓狂]": "18",
        "[吐]": "19",
        "[偷笑]": "20",
        "[愉快]": "21",
        "[白眼]": "22",
        "[傲慢]": "23",
        "[饥饿]": "24",
        "[困]": "25",
        "[惊恐]": "26",
        "[流汗]": "27",
        "[憨笑]": "28",
        "[悠闲]": "29",
        "[奋斗]": "30",
        "[咒骂]": "31",
        "[疑问]": "32",
        "[嘘]": "33",
        "[晕]": "34",
        "[疯了]": "35",
        "[衰]": "36",
        "[骷髅]": "37",
        "[敲打]": "38",
        "[再见]": "39",
        "[擦汗]": "40",
        "[抠鼻]": "41",
        "[鼓掌]": "42",
        "[糗大了]": "43",
        "[坏笑]": "44",
        "[左哼哼]": "45",
        "[右哼哼]": "46",
        "[哈欠]": "47",
        "[鄙视]": "48",
        "[委屈]": "49",
        "[快哭了]": "50",
        "[阴险]": "51",
        "[亲亲]": "52",
        "[吓]": "53",
        "[可怜]": "54",
        "[菜刀]": "55",
        "[西瓜]": "56",
        "[啤酒]": "57",
        "[篮球]": "58",
        "[乒乓]": "59",
        "[咖啡]": "60",
        "[饭]": "61",
        "[猪头]": "62",
        "[玫瑰]": "63",
        "[凋谢]": "64",
        "[嘴唇]": "65",
        "[爱心]": "66",
        "[心碎]": "67",
        "[蛋糕]": "68",
        "[闪电]": "69",
        "[炸弹]": "70",
        "[刀]": "71",
        "[足球]": "72",
        "[瓢虫]": "73",
        "[便便]": "74",
        "[月亮]": "75",
        "[太阳]": "76",
        "[礼物]": "77",
        "[拥抱]": "78",
        "[强]": "79",
        "[弱]": "80",
        "[握手]": "81",
        "[胜利]": "82",
        "[抱拳]": "83",
        "[勾引]": "84",
        "[拳头]": "85",
        "[差劲]": "86",
        "[爱你]": "87",
        "[NO]": "88",
        "[OK]": "89",
        "[爱情]": "90",
        "[飞吻]": "91",
        "[跳跳]": "92",
        "[发抖]": "93",
        "[怄火]": "94",
        "[转圈]": "95",
        "[磕头]": "96",
        "[回头]": "97",
        "[跳绳]": "98",
        "[投降]": "99",
        "[激动]": "100",
        "[乱舞]": "101",
        "[献吻]": "102",
        "[左太极]": "103",
        "[右太极]": "104"
    };
    var str = "\\[微笑\\]|\\[撇嘴\\]|\\[色\\]|\\[发呆\\]|\\[得意\\]|\\[流泪\\]|\\[害羞\\]|\\[闭嘴\\]|\\[睡\\]|\\[大哭\\]|\\[尴尬\\]|\\[发怒\\]|\\[调皮\\]|\\[呲牙\\]|\\[惊讶\\]|\\[难过\\]|\\[酷\\]|\\[冷汗\\]|\\[抓狂\\]|\\[吐\\]|\\[偷笑\\]|\\[愉快\\]|\\[白眼\\]|\\[傲慢\\]|\\[饥饿\\]|\\[困\\]|\\[惊恐\\]|\\[流汗\\]|\\[憨笑\\]|\\[悠闲\\]|\\[奋斗\\]|\\[咒骂\\]|\\[疑问\\]|\\[嘘\\]|\\[晕\\]|\\[疯了\\]|\\[衰\\]|\\[骷髅\\]|\\[敲打\\]|\\[再见\\]|\\[擦汗\\]|\\[抠鼻\\]|\\[鼓掌\\]|\\[糗大了\\]|\\[坏笑\\]|\\[左哼哼\\]|\\[右哼哼\\]|\\[哈欠\\]|\\[鄙视\\]|\\[委屈\\]|\\[快哭了\\]|\\[阴险\\]|\\[亲亲\\]|\\[吓\\]|\\[可怜\\]|\\[菜刀\\]|\\[西瓜\\]|\\[啤酒\\]|\\[篮球\\]|\\[乒乓\\]|\\[咖啡\\]|\\[饭\\]|\\[猪头\\]|\\[玫瑰\\]|\\[凋谢\\]|\\[嘴唇\\]|\\[爱心\\]|\\[心碎\\]|\\[蛋糕\\]|\\[闪电\\]|\\[炸弹\\]|\\[刀\\]|\\[足球\\]|\\[瓢虫\\]|\\[便便\\]|\\[月亮\\]|\\[太阳\\]|\\[礼物\\]|\\[拥抱\\]|\\[强\\]|\\[弱\\]|\\[握手\\]|\\[胜利\\]|\\[抱拳\\]|\\[勾引\\]|\\[拳头\\]|\\[差劲\\]|\\[爱你\\]|\\[NO\\]|\\[OK\\]|\\[爱情\\]|\\[飞吻\\]|\\[跳跳\\]|\\[发抖\\]|\\[怄火\\]|\\[转圈\\]|\\[磕头\\]|\\[回头\\]|\\[跳绳\\]|\\[投降\\]|\\[激动\\]|\\[乱舞\\]|\\[献吻\\]|\\[左太极\\]|\\[右太极\\]";
    var reg = new RegExp(str, 'g');
    var result = value.replace(reg, function (r) {
        return '<img class="qqemoji qqemoji' + qqfaceMap[r] + '" src="' + exports.spaceGif + '">';
    });
    return result;
}

/**
 * @about 照片预览
 * @param
 *
 * @return 无
 * @author zhaoze
 */
exports.layerPic = {
    gray: $('<div id="gray"></div>'),
    msg: $('<div id="msg"><div id="imgCon"></div></div>'),
    btn: $('<div class="rotateBtn" id="rotateBtn"></div>'),
    btnRight: $('<div class="out"><a id="rotateLeft" href="javascript:;" target="_blank" title="下载图片"></a><a href="javascript:;" id="rotateRight"></a></div>'),
    imgWidth: 0,
    imgHeight: 0,
    num: 0,
    init: function (img) {
        $("body").append(this.gray).append(this.msg);
        this.msg.append(this.btn);
        this.btn.append(this.btnRight);
        var imgSrc = $(img).attr("src");
        var img = new Image();
        var $this = this;
        img.onload = function () {
            var windowHeight = $(window).height();
            $this.msg.find("#imgCon").append('<img src="' + imgSrc + '"/>');
            if (this.height > windowHeight - 100) {
                $this.msg.height(windowHeight - 100);
                var newWith = this.width * (windowHeight - 100) / this.height;
                $this.msg.width(newWith);
                $this.imgWidth = newWith;
                $this.imgHeight = windowHeight - 100;
            } else {
                $this.msg.height(this.height);
                $this.msg.width(this.width);
                $this.imgWidth = this.width;
                $this.imgHeight = this.height;
            }
            $this.autoCenter();
        }
        img.src = imgSrc;
        this.gray.fadeIn();
        this.msg.fadeIn();
        this.gray.click(function () {
            $this.close(this);
        });
        $(window).resize(function () {
            $this.autoCenter();
        });
        this.btnRight.find("#rotateRight").click(function () {
            if (!window.addEventListener) {
                alert("请升级您的浏览器");
            } else {
                $this.rotateRight();//右旋转
            }
        });
        this.btnRight.find("#rotateLeft").attr("href", img.src);
    },
    autoCenter: function () {
        var w = $(window).width() - this.msg.width() - 10;
        var h = $(window).height() - this.msg.height() - 10;
        this.msg.css({left: w / 2, top: h / 2});
    },
    rotateLeft: function () {
        this.num++;
        this.msg.find("#imgCon").css({"transform": "rotate(" + 90 * this.num + "deg)"});
        this.rotatePosition();
    },
    rotateRight: function () {
        this.num--;
        this.msg.find("#imgCon").css({"transform": "rotate(" + 90 * this.num + "deg)"});
        this.rotatePosition();
    },
    rotatePosition: function () {
        var $imgCon = this.msg.find("#imgCon");
        var windowHeight = $(window).height();
        if (this.msg.width() > windowHeight - 100 && this.num % 2 != 0) {
            var newW = $(window).height() - 100;
            var newH = newW * this.imgHeight / this.imgWidth;
            this.msg.width(newW);
            this.msg.height(newH);
            var postop = $imgCon.position().top;
            this.btn.css({bottom: postop + 15 + "px"});
        } else if (this.msg.width() < windowHeight - 100 && this.num % 2 != 0) {
            var postop = $imgCon.position().top;
            this.btn.css({bottom: postop + 15 + "px"});
        } else {
            this.btn.css({bottom: "15px"});
            this.msg.width(this.imgWidth);
            this.msg.height(this.imgHeight);
        }
        this.autoCenter();
    },
    close: function (gray) {
        this.msg.find("#imgCon").empty();
        this.msg.remove();
        this.num = 0;
        this.btn.css({bottom: "20px"});
        this.msg.find("#imgCon").css({"transform": "rotate(0deg)", "top": "0px"});
        $(gray).remove();
    }
};


/**
 * @about 解决ios6、7 canvas2M以上图片压扁问题
 * @param
 * @return string   无
 * @author bob
 */
exports.drawImageIOSFix = function (ctx, img, sx, sy, sw, sh, dx, dy, dw, dh) {
    var vertSquashRatio = exports.detectVerticalSquash(img);
    ctx.drawImage(img, sx * vertSquashRatio, sy * vertSquashRatio,
        sw * vertSquashRatio, sh * vertSquashRatio,
        dx, dy, dw, dh);
}
exports.detectVerticalSquash = function (img) {
    var iw = img.naturalWidth, ih = img.naturalHeight;
    var canvas = document.createElement('canvas');
    canvas.width = 1;
    canvas.height = ih;
    var ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);
    var data = ctx.getImageData(0, 0, 1, ih).data;
    var sy = 0;
    var ey = ih;
    var py = ih;
    while (py > sy) {
        var alpha = data[(py - 1) * 4 + 3];
        if (alpha === 0) {
            ey = py;
        } else {
            sy = py;
        }
        py = (ey + sy) >> 1;
    }
    var ratio = (py / ih);
    return (ratio === 0) ? 1 : ratio;
}


/**
 * @about 图片file文件转base64
 * @param
 *      canvasId:canvas的id,
 *      file:file文件,
 *      callback:回调函数
 *      params:{"width":320,"cut":0.1,"imgtype":'png'}  生成的图片尺寸、质量、格式   选填
 * @return string   无
 * @author bob
 */
exports.imgToBase64 = function (cvs, file, callback, params) {
    let prms = params || {"width": 320, "cut": 0.1, "imgtype": 'png'};
    let canvas = cvs,
        context = canvas.getContext("2d"),
        tmpImage = new Image(),
        base64Str = "";
    let URL = URL || webkitURL;
    tmpImage.src = URL.createObjectURL(file);
    tmpImage.onload = function () {
        let width = tmpImage.width, height = tmpImage.height;
        let scale = width / height;
        let width1 = prms.width ? prms.width : 320;
        let height1 = parseInt(width1 / scale);
        canvas.width = width1;
        canvas.height = height1;
        //context.drawImage(tmpImage, 0, 0, width, height, 0, 0, width1, height1);
        exports.drawImageIOSFix(context, tmpImage, 0, 0, width, height, 0, 0, width1, height1);
        let realCut = prms.cut ? prms.cut : 0.1;
        base64Str = canvas.toDataURL("image/" + prms.type, realCut);
        callback(base64Str);
    }
}

/**
 * @about 判断图片和所在容器相比，是过长还是过宽
 * @param
 *      url:图片链接
 *      scale:容器 width/height
 *      callback:回调函数
 * @return true(图片高)，false（图片宽）
 * @author bob
 */
exports.imageIsWidth = function (url, scale, callback) {
    if (!url) {
        return false;
    }

    let img = new Image();
    img.src = url;

    if (img.complete) {
        if (img.width / img.height >= scale) {
            callback(true);
        } else {
            callback(false);
        }
    } else {
        img.onload = function () {
            if (img.width / img.height >= scale) {
                callback(true);
            } else {
                callback(false);
            }
        }

    }

}

/**
 * @about 格式化时间
 * @param
 *      date:{number或date}毫秒时间，必填
 *      fmt:{string}需要生成的时间格式，选填 默认  MM-dd hh:mm
 * @return string   格式化时间字符串
 * @author bob
 */
exports.fmtDate = function (date, format) {
    if (typeof date == "string") {
        return date;
    }
    if (typeof date == "number") {
        date = new Date(date);
    }

    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    var fmt = format || "MM-dd hh:mm:ss";
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//禁止分享
exports.noShare = function () {
    wx.ready(function () {
        //隐藏右上角菜单接口
        wx.hideOptionMenu();
    })
}
//开启分享
exports.openShare = function () {
    wx.ready(function () {
        //显示右上角菜单接口
        wx.showOptionMenu();
    })
}

//微信分享
exports.weixinShare = function (title, desc, link, icon, wxmpId, page, friends, callback) {
    let that = this;
    var imgUrl = "http://static.taggole.com/hbrobot/mmgkk.jpg";
    if (icon) {
        imgUrl = icon;
    }
    var config = {
        wxmpId: wxmpId,
        title: title,
        link: link,
        desc: desc,
        icon: imgUrl
    };
    wx.ready(function () {
        //分享给朋友
        wx.onMenuShareAppMessage({
            title: title,
            desc: desc,
            link: link,
            imgUrl: imgUrl,
            type: "",
            dataUrl: "",
            success: function () {
                that.doshare('appmessage', config, page);
            },
            cancel: function () {
            }
        });

        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: title,
            link: link,
            imgUrl: imgUrl,
            success: function () {
                that.doshare('timeline', config, page);
                if (friends) {
                    callback()
                }
            },
            cancel: function () {
            }
        });

        //分享到QQ
        wx.onMenuShareQQ({
            title: title,
            desc: desc,
            link: link,
            imgUrl: imgUrl,
            success: function () {
            },
            cancel: function () {
            }
        });

        //分享到腾讯微博
        wx.onMenuShareWeibo({
            title: title,
            desc: desc,
            link: link,
            imgUrl: imgUrl,
            success: function () {
            },
            cancel: function () {
            }
        });

        //分享到QQ空间
        wx.onMenuShareQZone({
            title: title,
            desc: desc,
            link: link,
            imgUrl: imgUrl,
            success: function () {
            },
            cancel: function () {
            }
        });
    });
    wx.error(function (res) {
        //$.toast(res, 2000, 'toastMsg');
    });

}

exports.doshare = function (targetType, config, page) {
    var locationUrl = window.location.href;
    $.ajax({
        url: basepath + '/wx/course/share/doshare',
        dataType: 'json',
        type: 'POST',
        data: {
            wxmpId: config.wxmpId,
            page: page,
            title: config.title,
            desc: config.desc,
            shareUrl: config.link,
            url: locationUrl,
            target: targetType
        },
        success: function (data) {
            if (data.status == 'success') {

            } else {
                //alert('分享失败');
            }

        },
        error: function () {
            if (data.status == 'success') {
                //alert('分享失败');
            }
        }
    });
}

//图片放大
exports.bigIMG = function (src) {
    var $this = $('<div id="gray" style="position: fixed; top: 0px; left: 0px; width: 100%; height: 100%; background: rgba(0,0,0,0.8); z-index: 100;">\
                                <div id="msg" style="position: fixed; z-index: 999; display: block;overflow-y:auto;">\
                                    <div id="imgCon" style="border-radius:3px;border: 3px solid #fff; cursor: pointer;overflow: auto"></div>\
                                </div>\
                            </div>');
    var img = new Image();
    img.src = src;
    img.onload = function () {
        $this.find("#imgCon").append('<img style="width: 100%; display: block;border-radius:3px;" src="' + src + '" />');
        $("body").append($this);
        var top = ($(window).height() - $this.find("#imgCon img").height()) / 2;
        $this.find("#imgCon").css("max-height", $(window).height() + "px");
        if (top < 0) {
            top = 0;
        }
        $this.find("#msg").css({top: top + "px"});
    }
    $this.click(function () {
        $(this).remove();
    });
}

//根据时间时长自动转换成00:00:00或00:00或00格式
exports.timeConvert = function (num) {
    num = parseFloat(num / 1000);//毫秒转成秒
    var h = this.fill(Math.floor(num / 3600));//时
    var m = this.fill(Math.floor(num % 3600 / 60));//分
    var s = this.fill(Math.floor(num % 60));//秒
    if (Math.floor(num / 3600)) {
        return h + ":" + m + ":" + s;
    } else if (Math.floor(num % 3600 / 60)) {
        return m + ":" + s;
    } else {
        return "00:" + s;
    }
}
//自动补零
exports.fill = function (num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}


/*微信配置信息
 * */
exports.wxConfig = function () {
    var wxmpId;
    var url = location.href;
    if (url.indexOf('#') != -1) {
        url = url.substring(0, url.indexOf('#'));
    }
    //获取wxmpId
    exports.ajax({
        url: '/wx/page/get_js_config/getWxmpId',
        data: {type: 2},
        type: "post",
        async: false,
        dataType: "json",
        success: function (response) {
            wxmpId = response.data.wxmpId;
            if (!wxmpId) {
                alert("获取wxmpId失败");
                return;
            }
            sessionStorage.subscribe = response.data.subscribe;//是否关注（true/false）
            sessionStorage.isLecturer = response.data.isLecturer;//是否是讲师(true/false)
            sessionStorage.shareUrlHead = response.data.shareUrlHead;//分享头链接
            sessionStorage.wxmpId = wxmpId;//wxmpId
            exports.ajax({
                url: '/wx/page/get_js_config',
                data: {
                    wxmpId: wxmpId,
                    url: url
                },
                type: "post",
                async: false,
                dataType: "json",
                success: function (response) {
                    wx.config({
                        debug: false,// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                        appId: response.appId,// 必填，公众号的唯一标识
                        timestamp: response.timestamp,// 必填，生成签名的时间戳
                        nonceStr: response.nonceStr, // 必填，生成签名的随机串
                        signature: response.signature,// 必填，签名，见附录1
                        jsApiList: ["onMenuShareAppMessage", "onMenuShareTimeline", "onMenuShareQQ", "onMenuShareWeibo", "onMenuShareQZone", "chooseWXPay", "startRecord", "stopRecord", "onVoiceRecordEnd", "playVoice", "uploadVoice", "downloadVoice", "uploadImage", "previewImage", "chooseImage", "translateVoice"]
                        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    });
                    wx.ready(function () {

                    });
                    wx.error(function (res) {
                        alert("微信初始化失败");
                    });
                }
            });
        }
    });
}


/*
 * 微信支付
 * wxmpId:公众号参数(必填)
 * payConfig:支付接口配置信息(必填)
 * obj:对象类型，回调函数success、cancel、fail
 * */
exports.wxPay = function (payConfig, obj) {
    wx.ready(function () {
        wx.chooseWXPay({
            timestamp: payConfig.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: payConfig.nonceStr, // 支付签名随机串，不长于 32 位
            package: payConfig.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
            signType: payConfig.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: payConfig.paySign, // 支付签名
            success: function (res) {
                obj.success(res);
            },
            cancel: function (res) {
                obj.cancel(res);
            },
            fail: function (res) {
                obj.fail(res);
            }
        });
    });
}

/*
 * 获取cookie
 * */
exports.getCookie = function (name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}