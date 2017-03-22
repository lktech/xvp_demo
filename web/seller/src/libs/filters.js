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
 * @about 订单状态转换
 * @param
 *      value:{string} 订单状态，1：待确认，2：待付款，3：待发货，4：待收货，5：完成交易，6：订单关闭(用户关闭)
 * @return string   转换成对应文本
 * @author zhaoze
 */
exports.orderStatus = function (value) {
    if (value == 'DQR') {
        return "订单待确认";
    } else if (value == 'DFK') {
        return "等待买家付款";
    } else if (value == 'DFH') {
        return "等待卖家发货";
    } else if (value == 'DSH') {
        return "卖家已经发货";
    } else if (value == 'YSH') {
        return "完成交易";
    } else if (value == 'YGB') {
        return "订单关闭";
    }
}
/**
 * @about 步骤状态
 * @param
 *      value:{int} 订单状态，1：待确认，2：待付款，3：待发货，4：待收货，5：完成交易，6：订单关闭(用户关闭)
 * @return int   转换成对应步骤状态
 * @author zhaoze
 */
exports.stepStatus = function (value) {
    if (value == 3) {
        return 1;
    } else if (value == 4) {
        return 2;
    } else if (value == 5) {
        return 3;
    } else {
        return 0;
    }
}

/**
 * @about 商品按钮状态
 * @param
 *      value:{int} 1：申请退款，2：退款处理中，3：退款成功，4：拒绝退款，
 * @return string   转换成对应按钮文本
 * @author zhaoze
 */
exports.goodsBtnTxt = function (value) {
    if (value == 1) {
        return "申请退款";
    } else if (value == 2) {
        return "退款处理中";
    } else if (value == 3) {
        return "退款成功";
    } else if (value == 4) {
        return "拒绝退款";
    }
}


/**
 * @about 发票类型
 * @param
 *      value:{int} 1：普通发票，2：电子发票，3：增值税发票
 * @return string   转换成对应文本
 * @author zhaoze
 */
exports.ticketTxt = function (value) {
    if (value == 1) {
        return "普通发票";
    } else if (value == 2) {
        return "电子发票";
    } else if (value == 3) {
        return "增值税发票";
    }
}

/**
 * @about 支付方式文案
 * @param
 *      value:{int} 1：微信安全支付，2：积分付款,3：余额支付，4：线下支付
 * @return string   转换成对应文本
 * @author zhaoze
 */
exports.payWayTxt = function (value) {
    if (value == 1) {
        return "微信安全支付";
    } else if (value == 2) {
        return "积分付款";
    } else if (value == 3) {
        return "余额支付";
    } else if (value == 4) {
        return "线下支付";
    }
}
/**
 * @about 格式化价格
 * @param
 *      s: 接收的价格
 * @return string   转换成元
 * @author bob
 */
exports.formatPrice = function (s) {
    return utils.formatPrice(s);
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