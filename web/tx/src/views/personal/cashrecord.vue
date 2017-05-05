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
        <c-button text='返回首页' size='block' type='primary' :link='{name:"index"}'></c-button>
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
        	record:[],
          url:'',      // 滚动加载的链接
        }
      },
      mounted: function () {
        this.$nextTick(function () {
//        utils.MenuShare();     //微信分享
	        let that = this;
	        that.url=basepath + "/seller/account/queryWithDraws";
	        utils.ajax({
	          url: that.url,
	          data: {},
	          success: function(data) {
	            if(data.code=='SUCCESS') {
	              that.record = data.result;
	            } else {
	              that.$vux.toast.show(data.msg);
	            }
	          },
	        });
        })
    	},
      methods: {
				// 滚动加载
				load(data) {
					if(data.result) {
						this.record = data.result;
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