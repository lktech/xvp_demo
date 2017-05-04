<template>
  <div>
    <c-top-back></c-top-back>
    <div v-if='record.length>0'>
      <c-cell-wrap :title='item.create_time' v-for='item in record'>
        <c-cell :title="item.title" :value='item.withdraw_amount' rightcolor='red' v-if='item.title=="提现失败"'></c-cell>
        <c-cell :title="item.title" :value='item.withdraw_amount' rightcolor='green' v-else></c-cell>
      </c-cell-wrap>
    </div>
    <!--无数据提示-->
    <c-data-null v-else msg='最近没有提现记录哦！'>
      <div class="wrap-pd">
        <c-button text='返回首页' size='block' type='primary'></c-button>
      </div>
    </c-data-null>
    <!-- 列表滚动加载-->
    <c-scroll-load @on-load="load" :url='url'></c-scroll-load>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          record: [
            {
                "id": 1111,
                "store_id": 111,
                "card_no": "1234567890123456789",
                "bank_code": "123456789",
                "bank_name": "招商银行",
                "withdraw_amount": 1000,
                "withdraw_commission": 1,
                "create_time": "2017-01-09 15:07:51",
                "title":'提现成功'
            },{
                "id": 1111,
                "store_id": 111,
                "card_no": "1234567890123456789",
                "bank_code": "123456789",
                "bank_name": "招商银行",
                "withdraw_amount": 1000,
                "withdraw_commission": 1,
                "create_time": "2017-01-09 15:07:51",
                "title":'提现失败'
            }
	        ],   // 数据
          url:'',      // 滚动加载的链接
        }
      },
      mounted: function () {
        this.$nextTick(function () {
            let that = this;

            utils.ajax({
                url: "/seller/account/getAccountAmount",
                success: function (res) {
                    if (res.code == "SUCCESS") {
                        let json = res.result;
                        that.item.money = utils.formatPrice(json.withdrawals_amount);
                        that.item.subMoneyL = utils.formatPrice(json.today_income_amount);
                        that.item.subMoneyR = utils.formatPrice(json.income_amount);
                    } else {
                        that.$vux.alert.show(res.code);
                    }
                }
            })
        })
    	},
      ready() {
          utils.MenuShare();
	        let that = this;
	        that.url=basepath + "/seller/account/queryWithDraws";
	        utils.ajax({
	          url: that.url,
	          data: {},
	          success: function(data) {
	            if(data.success) {
	              that.record = data.obj;
	            } else {
	              that.$vux.alert.show(data.msg);
	            }
	          },
	        });
      },
      methods: {
				// 滚动加载
				load(data) {
					if(data) {
						this.record = data;
					}
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