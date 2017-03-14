<template>
  <div>
    <c-top-back></c-top-back>
    <!-- 搜索以及 搜索列表 -->
    <div v-if='goods.length > 0'>
      <c-search @on-submit="searchSubmit" btncolor="org">
      	<!-- 搜索后的商品列表 -->
      	<c-panel-img>
        	<c-panel-list :id='item.id' :title='item.title' :min-price='item.minPrice' :max-price='item.maxPrice' :price='item.price' :righttips='item.righttips' :imglink='item.imglink' cheng='cheng' v-for='item in searchList' @handing='handing'></c-panel-list>
      	</c-panel-img>
      	<!-- 搜索列表的滚动价格 -->
      	<c-scroll-load @on-load="searchload" :url='url' :params="params" eleid="search_show"></c-scroll-load>
      </c-search>
      <!-- 商品列表 -->
      <c-panel-img>
        <c-panel-list :id='item.id' :title='item.title' :min-price='item.minPrice' :max-price='item.maxPrice' :price='item.price' :righttips='item.righttips' :imglink='item.imglink' cheng='cheng' v-for='item in goods' @handing='handing'></c-panel-list>
      </c-panel-img>
    </div>
    <c-data-null msg='暂无商品信息' v-else></c-data-null>
    <c-action-sheet :show.sync="show2" :menus="menus2" title='操作商品' show-cancel @on-click-menu="click"></c-action-sheet>
    <!-- 列表滚动加载-->
    <c-scroll-load @on-load="load" :url='url' :params="params"></c-scroll-load>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
        	show2: false,   // 弹窗控制
          menus2: {       // 弹窗列表
            menu1: '查看商品',
            menu2: '编辑',
            menu3: '删除',
          },
          searchList: [], // 搜索列表
          goods: [], // 商品列表
          url: basepath + "/mall/store/shelf_products", // 初始化页面的商品列表接口  
          params: {
            "pageNumber": 1,
            "pageSize": 20
          },
          selectId: -1
        }
      },
      ready() {
        
        let that = this;
        utils.ajax({
          url: that.url,
          data: JSON.stringify({
            page_num: 1
          }),
          success: function(data) {
            if(data.success) {
              that.goods = data.obj;
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });

      },
      methods: {
        // 搜索
        searchSubmit(val) {
          let that = this;
          utils.ajax({
            url: that.url,
            data: JSON.stringify({
              'page_num': 1,
              "name": val, // 输入的值
            }),
            success: function(data) {
              if(data.success) {
              	that.searchList = data.obj; // 搜索列表
                if(!that.searchList.length){
                	that.status=true;
                }
              } else {
                that.$vux.alert.show(data.msg);
              }
            },
          });
        },
        // 商品列表的滚动加载
        load(data) {
          if(data) {
            let that = this;
            that.goods = that.goods.concat(data);
          }
        },
        // 搜索列表的滚动加载
        searchload(data) {
          if(data) {
            let that = this;
            that.searchList = that.searchList.concat(data);
          }
        },
        handing(item) {    // 点击每行的回调
          let that = this;
          that.selectId = item;
          that.show2 = true;
          console.log(item);
        },
        click(key) {   //  查看 编辑  删除 取消
          let that = this;
          if(key == 'menu1') { // 查看商品
            utils.go({
              name: 'detail',
              params: {
                id: that.selectId
              }
            }, that.$router);
          } else if(key == 'menu2') { // 编辑商品
            utils.go({
              name:'pdtModSelf',query:{'id':that.selectId}
            }, that.$router);
          } else if(key == 'menu3') { // 删除商品
            that.$vux.confirm.show({
              content: '商品删除后不可恢复请确认？',
              onConfirm() {
                utils.ajax({
                  url: basepath + "/mall/store/shelf_product_delete",
                  data: JSON.stringify({
                    'id': that.selectId,
                  }),
                  success: function(data) {
                    if(data.success) {
                    	if(that.searchList.length){
                        for(var i=0;i<that.searchList.length;i++){
                          if(that.searchList[i].id==that.selectId){
                            that.searchList.splice(i,1);
                          }
                        }
                    	}else{
                        for(var i=0;i<that.goods.length;i++){
                          if(that.goods[i].id==that.selectId){
                            that.goods.splice(i,1);
                          }
                        }
                    	}
                      that.$vux.toast.show({
                        text: '删除成功',
                        type: 'success'
                      });
                    } else {
                      that.$vux.alert.show(data.msg);
                    }
                  },
                });
              },
            });
          }
        },
      },
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cSearch": require('../../components/search.vue'),
        "cPanelImg": require('../../components/panelImg.vue'),
        "cPanelList": require('../../components/panellist.vue'),
        "cDataNull": require('../../components/datanull.vue'),
        // 滚动加载
        "cScrollLoad": require('../../components/scroll-load.vue'),
        "cActionSheet": require('../../components/action-sheet.vue'),
      },
  }
</script>