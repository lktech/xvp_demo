<template>
  <div>
    <c-top-back></c-top-back>
    <div v-if='record.length>0'>
      <c-cell-wrap :title='item.time' v-for='item in record'>
        <c-cell :title="item.title" :value='item.value' rightcolor='red' v-if='item.title=="提现失败"'></c-cell>
        <c-cell :title="item.title" :value='item.value' rightcolor='green' v-else></c-cell>
      </c-cell-wrap>
    </div>
    <!--无数据提示-->
    <c-data-null v-else msg='最近没有提现记录哦！'>
      <div class="wrap-pd">
        <c-button text='返回首页' size='block' type='default' :link='{name:"home"}'></c-button>
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
          record: [],   // 数据
          url:'',      // 滚动加载的链接
        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        that.url=basepath + "/app/cashirecord";
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
        "cTopBack": require('../../components/top-back.vue'),
        "cCellWrap": require('../../components/cell-wrap.vue'),
        "cCell": require('../../components/cell.vue'),
        "cDataNull": require('../../components/datanull.vue'),
				// 滚动加载
				"cScrollLoad": require('../../components/scroll-load.vue'),
        "cButton": require('../../components/button.vue'),
      }
  }
</script>