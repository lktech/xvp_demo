<!-- 购物车-->
<template>
  <div>
    <c-top-back></c-top-back>
    <c-cart @hander='hander' :list="list" :shop='shop' :format='{
    		"id":"id",
				"name":"name",
				"format2":"format2",
				"formatsel":"formatsel",
				"price":"price",
				"count":"count",
				"sourceUrl":"sourceUrl",
				"link":"link",
				"checked":"checked",
				"num":"num",
				"checked":"checked"
				}' v-if='list.length!=0'>
    </c-cart>
    <!--购物车清空提示-->
    <c-data-null v-else msg='购物车空空如也，来挑几件好货吧！'>
      <div class="wrap-pd">
        <c-button text='返回首页' size='block' type='default' :link='{name:"home"}'></c-button>
      </div>
    </c-data-null>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          shop: '',
          list: [],
        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        // 购物车列表
        utils.ajax({
          url: basepath + "/app/msg/cart",
          success: function(data) {
            if(data.success) {
              that.shop = data.obj.store.name;
              that.list = data.obj.list;
            } else {
              that.$vux.alert.show(data.msg);
            }
          }
        });

      },
      methods: {
        hander(checkList, surplusList, clicktype) {
          if(clicktype == 'delete') {
            if(checkList.length == 0) {
              this.$vux.alert.show('您还没有选择宝贝噢');
              return;
            }
            this.list = surplusList;
            this.first = false;
            // 删除
          } else {
            if(checkList.length == 0) {
              this.$vux.alert.show('您还没有选择宝贝噢');
              return;
            }
            utils.go({
              name: 'odadd',
              query: {
                id: 'checkList'
              }
            }, this.$router);
          }
          message: '提交中'
        },
      },
      components: {
        "cCart": require('../../components/cart.vue'),
        "cDataNull": require('../../components/datanull.vue'),
        "cButton": require('../../components/button.vue'),
        "cTopBack": require('../../components/top-back.vue'),
      }
  }
</script>