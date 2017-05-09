<template>
    <div>
        <c-top-back></c-top-back>
        <div v-if='record.length>0'>
            <div v-for='item in record' :key="item.id">
                <c-cell-wrap :title='item.create_time' v-if='item.status=="TXCG"'>
                    <c-cell title="提现手续费" :value='item.withdraw_cash_commission|formatPrice' rightcolor='green'></c-cell>
                </c-cell-wrap>
                <c-cell-wrap :title='item.create_time'>
                    <c-cell :title="adStatusView(item.status).text" :value='item.withdraw_cash_amount|formatPrice' rightcolor='green'
                            v-if='item.status=="TXCG"'></c-cell>
                    <c-cell :title="adStatusView(item.status).text" :value='item.withdraw_cash_amount|formatPrice' rightcolor='red'
                            v-else></c-cell>
                </c-cell-wrap>
            </div>
        </div>
        <!--无数据提示-->
        <c-data-null v-else msg='最近没有提现记录哦!'>
            <div class="wrap-pd">
                <c-button text='返回' size='block' type='primary' :link='linkUrl'
                          style='background-color: #5ABA5A;'></c-button>
            </div>
        </c-data-null>
        <!-- 列表滚动加载-->
        <c-scroll-load @on-load="load" :url='url'></c-scroll-load>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants';
    export default {
        data() {
            return {
            	linkUrl:'/tx/balance',
                record: [
//                  {
//                      "id": 1111,
//                      "store_id": 111,
//                      "card_no": "1234567890123456789",
//                      "bank_code": "123456789",
//                      "bank_name": "招商银行",
//                      "withdraw_amount": 1000,
//                      "withdraw_commission": 1,
//                      "create_time": "2017-01-09 15:07:51",
//                      "status": 'TXCG'
//                  }, {
//                      "id": 1111,
//                      "store_id": 111,
//                      "card_no": "1234567890123456789",
//                      "bank_code": "123456789",
//                      "bank_name": "招商银行",
//                      "withdraw_amount": 1000,
//                      "withdraw_commission": 1,
//                      "create_time": "2017-01-09 15:07:51",
//                      "status": 'TXCG'
//                  }, {
//                      "id": 1111,
//                      "store_id": 111,
//                      "card_no": "1234567890123456789",
//                      "bank_code": "123456789",
//                      "bank_name": "招商银行",
//                      "withdraw_amount": 1000,
//                      "withdraw_commission": 1,
//                      "create_time": "2017-01-09 15:07:51",
//                      "status": 'TXSB'
//                  }, {
//                      "id": 1111,
//                      "store_id": 111,
//                      "card_no": "1234567890123456789",
//                      "bank_code": "123456789",
//                      "bank_name": "招商银行",
//                      "withdraw_amount": 1000,
//                      "withdraw_commission": 1,
//                      "create_time": "2017-01-09 15:07:51",
//                      "status": 'TXSB'
//                  }, {
//                      "id": 1111,
//                      "store_id": 111,
//                      "card_no": "1234567890123456789",
//                      "bank_code": "123456789",
//                      "bank_name": "招商银行",
//                      "withdraw_amount": 1000,
//                      "withdraw_commission": 1,
//                      "create_time": "2017-01-09 15:07:51",
//                      "status": 'TXCG'
//                  }
                ],
                url: '',      // 滚动加载的链接
            }
        },
        mounted: function () {
            this.$nextTick(function () {
//        utils.MenuShare();     //微信分享
                let that = this;
                that.url = "/seller/account/queryWithDraws";
                utils.ajax({
                    url: that.url,
                    data: {},
                    success: function (data) {
                        if (data.code == 'SUCCESS') {
                            that.record = data.result;
                        } else {
                            that.$vux.alert.show(data.message);
                        }
                    },
                });
            })
        },
        methods: {
            // 滚动加载
            load(data) {
                if (data.result) {
                    this.record = data.result;
                }
            },
            adStatusView(state) {
                return constants.adStatusView(state);
            },
        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cCellWrap": require('../../components/x-cell/cell-wrap.vue'),
            "cCell": require('../../components/x-cell/cell.vue'),
            "cDataNull": require('../../components/x-datanull/x-datanull.vue'),
            // 滚动加载
            "cScrollLoad": require('../../components/x-scroll-load/x-scroll-load.vue'),
            "cButton": require('../../components/button/button.vue'),
        }
    }
</script>
<style lang="less" scoped>
	.weui_btn_primary:active {
	    background-color: #5ABA5A !important;
	    color: #fff !important;
	}
</style>