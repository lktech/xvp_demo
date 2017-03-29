<template>
  <div class="warehouse">
    <c-top-back></c-top-back>
    <div v-if='goods.length > 0'>
      <!-- 商品列表 -->
      <c-panel-img>
        <c-panel-list :id='item.id' :title='item.name' :price="item.price" :maxPrice='item.maxPrice' :minPrice='item.minPrice' :righttips='item.stock' :imglink='item.pics' v-for='item in goods' @handing='handing'></c-panel-list>
      </c-panel-img>
    </div>
    <c-data-null msg='暂无商品信息' v-else></c-data-null>
    <c-action-sheet v-model="show2" :menus="menus2" title='操作商品' show-cancel @on-click-menu="click"></c-action-sheet>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  import Vue from 'vue';
  export default {
    data() {
        return {
        	show2: false,   // 弹窗控制
          menus2: {       // 弹窗列表
            menu1: '查看商品',
            menu2: '编辑',
            menu3: '删除',
          },
          goods: [], // 商品列表
          url: "/seller/product/query", // 初始化页面的商品列表接口  
          selectId: -1
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          utils.MenuShare();
          let that = this;
          utils.ajax({
            url: that.url,
            type:'post',
            datatype:'json',
            success: function(data) {
              if(data.code=="SUCESS") {
                that.goods=data.result;
                $.each(data.result,function(k,o){
                  utils.ajax({
                      url:"/seller/product/sku/get", type:'post', data: {product_id:o.id}, success: function (res) {
                          if (res.code=="SUCESS") {

                              var min=res.result[0].price;
                              var max=min;
                              var len=res.result.length;
                              var _stock=0;

                              $.each(res.result,function(i,v){
                                _stock+=v.stock*1;
                                if(v.price > max){
                                  max = v.price*1;
                                }
                                if(v.price < min){
                                  min = v.price;
                                }
                              })
                              Vue.set(that.goods, k, {
                                id:o.id,
                                name:o.name,
                                maxPrice:max,
                                minPrice:min,
                                price:min,
                                stock:_stock,
                                pics:o.pics+'?imageMogr2/thumbnail/60x'
                              });
                          }else if(res.code=='auth_seller_error'){
                              utils.wang(that,utils,res.message);

                          }else{
                              that.$vux.alert.show(res.message);
                          }
                      }
                  });
                })
              }else if(data.code=='auth_seller_error'){
                                utils.wang(that,utils,data.message);

                            } else {
                that.$vux.alert.show(data.message);
              }
            }
          });
        })
      },
      methods: {

        handing(item) {    // 点击每行的回调
          let that = this;
          that.selectId = item.id;
          that.show2 = true;
          
        },
        click(key) {   //  查看 编辑  删除 取消
          let that = this;
          if(key == 'menu1') { // 查看商品
            utils.go({
              path: '/product/detail',
              query: {
                product_id: that.selectId
              }
            }, that.$router);
          } else if(key == 'menu2') { // 编辑商品
            utils.go({
              path:'modself',query:{'id':that.selectId}
            }, that.$router);
          } else if(key == 'menu3') { // 删除商品
            that.$vux.confirm.show({
              content: '商品删除后不可恢复请确认？',
              onConfirm() {
                utils.ajax({
                  url: basepath + "/seller/product/delete",
                  data: {
                    'id': that.selectId,
                  },
                  success: function(data) {
                    if(data.code=="SUCESS") {
                      for(var i=0;i<that.goods.length;i++){
                        if(that.goods[i].id==that.selectId){
                          that.goods.splice(i,1);
                        }
                      }
                      that.$vux.toast.show({
                        text: '删除成功',
                        type: 'success'
                      });
                    }else if(data.code=='auth_seller_error'){
                                utils.wang(that,utils,data.message);

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
        "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
        "cSearch": require('../../components/search/search.vue'),
        "cPanelImg": require('../../components/x-panel-list/x-panel-img.vue'),
        "cPanelList": require('../../components/x-panel-list/x-panel-list.vue'),
        "cDataNull": require('../../components/x-datanull/x-datanull.vue'),
        // 滚动加载
        "cScrollLoad": require('../../components/x-scroll-load/x-scroll-load.vue'),
        "cActionSheet": require('../../components/actionsheet/actionsheet.vue'),
      },
  }
</script>
<style>
  .warehouse .pannel-list.weui_media_box .from{
    padding-top: 20px !important;
  }
</style>