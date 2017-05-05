import store from '../vuex/store'

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


