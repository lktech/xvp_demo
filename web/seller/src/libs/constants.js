

/**
 * @about 自定义退款状态
 * @param
 *      "handing":1,//等待商家处理状态
 "agree":2, //同意退款
 "disagree":3 //不同意
 *
 * @return 无
 * @author pengqingyao
 */
exports.refund_state = {
    "handing": 1,//等待商家处理状态
    "agree": 2, //同意退款
    "disagree": 3 //不同意
}

/**
 * @about 商品类型
 * @param
 *     "normal":"PRODUCT",//普通商品
 *     "fenxiao":"FX", //分销商品
 *     "fenqi":"FQ", //分期商品
 *     "jifen":"JF" //积分商品
 *
 * @return 无
 * @author bob
 */
exports.productType = {
    "normal": "PRODUCT",//普通商品
    "fenxiao": "FX", //分销商品
    "fenqi": "FQ", //分期商品
    "jifen": "JF", //积分商品
    "rjl": "RJL" //瑞金麟预订商品
}

/**
 * @about 商品sku类型   暂时没有用到
 * @param
 *
 * @return 无
 * @author bob
 */
exports.skuType = {
    "has": "1",//有sku
    "none": "0"//无sku
}


/**
 * @about 发票类型
 *@param  无
 * @return 无
 * @author zhaoze
 */
exports.ticketType = {
    ordinary: "ordinary",//普通发票
    increment: "increment",//增值发票
    electronics: "electronics",//电子发票
    no_inv: "no_inv",//不开发票
}

/**
 * @about 全局订单状态
 *@param  无
 * @return 无
 * @author zhaoze
 */
exports.orderStatus={
    dqr: 'DQR',//待确认
    dfk: 'DFK',//待付款
    dfh: 'DFH',//待发货
    dsh: 'DSH',//待收货
    ok: 'YSH',//完成交易
    close: 'YGB'//订单关闭（用户关闭/订单超时）
}


/**
 * @about 运费类型
 *@param  无
 * @return 无
 * @author bob
 */
exports.postageType={
    baoyou: 0,//包邮
    normal: 1,//正常运费
    area: 2,//按地区
    other: 3//其他
}

/**
 * @about 订购应用状态
 *
 * @author yy
 */
exports.adStatus={
	
	"txcg":"TXCG",     //待支付
	"txsb":"TXSB",//待发货

}
/**
 *@about 提现状态
 *@param
 * 		   adStatus：订购应用状态
 * @return "status":status,
			"type":"",
			"text":"",
 * @author yy
 */
exports.adStatusView = function(status,breadName){
	let ads = exports.adStatus,
		result = {
			"status":status,
			"type":"",
			"text":"",
		};
    if(status == ads.txcg){//待支付
		result.text = "提现成功";
	}else if(status == ads.txsb){//待发货
		result.text = "提现失败";
		
	}
	return result;
}


