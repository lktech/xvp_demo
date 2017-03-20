"use strict"

import utils from  './utils.js'

/**
 * @about 格式化时间
 * @param
 *      date:{number或date}毫秒时间，必填
 *      fmt:{string}需要生成的时间格式，选填 默认  MM-dd hh:mm
 * @return string   格式化时间字符串
 * @author bob
 */
exports.formatTime = function (date, format) {
    return utils.formatTime(date, format);
}


/**
 * @about 格式化价格
 * @param price(分)
 * @return string   格式化价格字符串
 * @author bob
 */
exports.formatPrice = function (price) {
    return utils.formatPrice(price);
}

/**
 * @about 订单状态转换
 * @param
 *      value:{string} 接收的订单状态值
 * @return string   转换成对应文本
 * @author zhaoze
 */
exports.orderStatus = function (value) {
    if (value == 1) {
        return "等待买家付款";
    } else if (value == 2) {
        return "订单待确认";
    } else if (value == 3) {
        return "等待卖家发货";
    } else if (value == 4) {
        return "卖家已经发货";
    } else if (value == 5) {
        return "订单已关闭";
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
    return utils.fmtDate(date, format);
}
/**
 * @about emoji表情转换
 * @param
 *      value:{string} 消息内容，必填
 * @return string   带emoji表情的消息
 * @author bob
 */
exports.emoji = function (value) {
    return utils.emoji(value);
}

/**
 * @about 分转换成元带符号(￥)
 * @param
 *      value: 接收的分
 * @return string   转换成元带符号(￥)
 * @author zhaoze
 */
exports.formatPriceCNY = function (price) {
    return "￥"+utils.formatPrice(price);
}
