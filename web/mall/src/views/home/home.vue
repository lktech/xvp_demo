<template>
  <div class="wq_home" v-if='firstloading' >
    <div class="tabbar-wrap">
      <c-top-back :mar='mar' hide="true"></c-top-back>
      <!--店铺信息 -->
      <c-banner-info :logo="baseinfo.logo" :upload-type='false'  banner='http://img1.xiaovpu.com/3028216099602841.png'>
        <div slot="inner">
          <div>
            <div class="fontbg">{{baseinfo.title}}</div>
          </div>
        </div>
      </c-banner-info>
      <div v-if='component.length > 0'>
          <c-product-wrap type="smaller">
            <c-product v-for="(item,index) in component" :title="item.name" :price="item.price | formatPriceCNY" :pic="item.pics+'?imageMogr2/thumbnail/173x'" @click.native="click(item.id)"></c-product>
          </c-product-wrap>
      </div>
      <c-data-null v-else msg='这个店家很懒，什么都没有留下！'></c-data-null>
      <c-scroll-load @on-load="load" eleid="search_show" :url='searchurl' :params='params'></c-scroll-load>
      
    </div>
    <c-tabbar style='height:58px;'>
        <c-tabbar-item selected>
          <img slot="icon"  src="http://static.taggole.com/sithbrobot/poster/1490339048405.jpg">
          <span slot="label">首页</span>
        </c-tabbar-item>
        <c-tabbar-item link="/order/list">
          <img slot="icon" src="http://static.taggole.com/sithbrobot/poster/1490339019315.jpg">
          <span slot="label">我的</span>
        </c-tabbar-item>
      </c-tabbar>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  import Vue from 'vue';
  export default {
    data() {
        return {
          mar: true, //去掉边距
          firstloading: false, // 首次加载
          baseinfo: {
            logo:'http://okxm1ge1z.bkt.clouddn.com/c64c93ad-01a6-440f-9acd-7dabc0408cdb.png',
            title:''
          }, //店铺基本信息
          component: [], // 商品列表的判断
          component1: [], // 商品列表的判断
          searchurl:basepath + "/user/store/main", // 搜索接口
          params:{"pageNumber":1,"pagesize":10}, // 滚动加载数量控制
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          this.$vux.alert.hide();
          let that = this ;

          utils.ajax({
            url: "/user/user/getIsvInfo",
            success: function(data) {
              if(data.code=="SUCCESS") {  

                $xvp.login({
                    app_key : data.result.appId,
                    isv_url: data.result.isvUrl,
                    success : function(xvp_uid){
                      utils.ajax({
                        url: "/user/user/login",
                        data:{'xvp_uid':xvp_uid},
                        success: function(res) {
                          if(res.code=="SUCCESS") { 
                            that.init();

                          } else {
                            that.$vux.alert.show(res.message);
                          }
                        },
                      });
                    }
                });

                } else {
                  that.$vux.alert.show(data.message);
                }
              },
            });

        
        })
      },
      methods: { // 搜索
        // 滚动加载
        load(data) {
          if(data) {
            let that = this;
            that.searchList = that.searchList.concat(data);
          }
        },
        click(id){
          utils.go({path:'/product/detail',query:{store_id:this.$route.query.id,product_id:id}},this.$router);
        },
        init(){
          utils.setSession('pageId',this.$route.query.id);
          let that = this;
          utils.ajax({
            url: "/user/store/main",
            data:{'store_id':that.$route.query.id*1},
            success: function(data) {
              if(data.code=="SUCCESS") {
                that.firstloading = true;
                that.baseinfo.title = data.result.store_name; //店铺名称
                localStorage.setItem('store_name',that.baseinfo.title);
                if(data.result.logo){
                  that.baseinfo.logo = data.result.logo; //店铺名称
                }
                that.component1=data.result.products;
                that.component=that.component1;
                $.each(that.component1,function(i,v){
                    utils.ajax({
                      url: "/user/product/sku/get",
                      data:{'product_id':v.id},
                      success: function(res) {
                        if(res.code=="SUCCESS") {
                          var min=res.result[0].price*1;
                          var len=res.result.length;

                          for(var ii=0; ii<len; ii++){
                            if(res.result[ii].price*1 < min){
                              min = res.result[ii].price*1;
                            }
                          }
                          that.component1[i].price = min;
                          Vue.set(that.component, i, that.component1[i]);
                          //that.component = that.component1;
                        } else {
                          that.$vux.alert.show(res.message);
                        }
                      },
                    });
                })

                sessionStorage.setItem('title_',that.baseinfo.title);
                sessionStorage.setItem('link_','http://demo.open.xiaovpu.com/mall/index.html#/home/home?id='+that.$route.query.id+'&xv=enter');
                sessionStorage.setItem('logo_',that.baseinfo.logo);
                sessionStorage.setItem('product_id',that.$route.query.id);
                utils.MenuShare();
              } else {
                that.$vux.alert.show(data.message);
              }
            },
          });
        }
      },
      components: {
        "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
        "cBannerInfo": require('../../components/x-banner/x-banner-info.vue'),
        "cProduct": require('../../components/x-product/x-product.vue'),
        "cProductWrap": require('../../components/x-product/x-product-wrap.vue'),
        "cDataNull": require('../../components/x-datanull/x-datanull.vue'),
        // 滚动加载
        "cScrollLoad": require('../../components/x-scroll-load/x-scroll-load.vue'),
        "cTabbar": require('../../components/tabbar/tabbar.vue'),
        "cTabbarItem": require('../../components/tabbar/tabbar-item.vue'),
      },
  }
</script>

<style>
  .wq_home .weui_tabbar{
    position: fixed;
  }
  .wq_home .pdt-tit{
    display: -webkit-box;
        overflow: hidden;
        text-overflow: ellipsis;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
  }
</style>