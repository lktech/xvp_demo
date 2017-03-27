<template>
  <div class="wq_home" v-if='firstloading==true' >
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
            <c-product v-for="(item,index) in component" :title="item.name" :price="item.price | formatPrice" :pic="item.pics" @click.native="click(item.id)"></c-product>
          </c-product-wrap>
      </div>
      <c-data-null v-else msg='这个店家很懒，什么都没有留下！'></c-data-null>
      <c-scroll-load @on-load="load" eleid="search_show" :url='searchurl' :params='params'></c-scroll-load>
      
    </div>
    <c-tabbar style='height:58px;'>
        <c-tabbac-item selected>
          <img slot="icon"  src="http://static.taggole.com/sithbrobot/poster/1490339048405.jpg">
          <span slot="label">首页</span>
        </c-tabbac-item>
        <c-tabbac-item link="/order/list">
          <img slot="icon" src="http://static.taggole.com/sithbrobot/poster/1490339019315.jpg">
          <span slot="label">我的</span>
        </c-tabbac-item>
      </c-tabbar>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
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
          let that = this ;


          utils.ajax({
            url: basepath + "/user/user/getIsvInfo",
            success: function(data) {
              if(data.code=="SUCESS") {  

                $xvp.login({
                    app_key : data.result.appId,
                    isv_url: data.result.isvUrl,
                    success : function(xvp_uid){

                      utils.ajax({
                        url: basepath + "/user/user/login",
                        data:{'xvp_uid':xvp_uid},
                        success: function(res) {
                          if(res.code=="SUCESS") {  
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
            url: basepath + "/user/store/main",
            data:{'store_id':that.$route.query.id*1},
            success: function(data) {
              if(data.code=="SUCESS") {
                that.firstloading = true;
                that.baseinfo.title = data.result.store_name; //店铺名称
                if(data.result.logo){
                  that.baseinfo.logo = data.result.logo; //店铺名称
                }
                that.component1=data.result.products;
                $.each(that.component1,function(i,v){
                    utils.ajax({
                      url: basepath + "/user/product/sku/get",
                      async:false,
                      data:{'product_id':v.id},
                      success: function(res) {
                        if(res.code=="SUCESS") {
                          var min=res.result[0].price;
                          var len=res.result.length;

                          for(var ii=1; ii<len; ii++){
                            if(res.result[ii].price < min){
                              min = res.result[ii].price;
                            }
                          }
                          that.component1[i].price = min;
                        } else {
                          that.$vux.alert.show(res.message);
                        }
                      },
                    });
                })
                that.component = that.component1; // 组件

                sessionStorage.setItem('title_',that.baseinfo.title);
                sessionStorage.setItem('link_','http://m.fvt.xiaovpu.com/wap/order/index.html#!/home/'+that.$route.query.id+'?xv=enter');
                sessionStorage.setItem('logo_',that.baseinfo.logo);
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
</style>