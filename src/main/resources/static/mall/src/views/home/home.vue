<template>
  <div class="wq_home">
    <div v-if='firstloading==true' class="tabbar-wrap">
      <c-top-back :mar='mar' hide="true"></c-top-back>
      <!--店铺信息 -->
      <c-banner-info :logo="baseinfo.logo" banner='http://img1.xiaovpu.com/3028216099602841.png'>
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
    <r-tabbar style='height:50px;'>
      <r-tabbar-item selected>
        <img slot="icon"  src="http://static.taggole.com/hbrobot/poster/1489739579290.jpg">
        <span slot="label">首页</span>
      </r-tabbar-item>
      <r-tabbar-item link="/order/list">
        <img slot="icon" src="http://static.taggole.com/hbrobot/poster/1489739656143.jpg">
        <span slot="label">我的</span>
      </r-tabbar-item>
    </r-tabbar>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          mar: true, //去掉边距
          firstloading: true, // 首次加载
          baseinfo: {
            logo:'http://pm2.fvt.xiaovpu.com/wap/order/seller/images/logo.png',
            title:''
          }, //店铺基本信息
          component: [], // 商品列表的判断
          component1: [], // 商品列表的判断
          searchurl:basepath + "/user/store/main", // 搜索接口
          params:{"pageNumber":1,"pagesize":10} // 滚动加载数量控制
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          let that = this ;


          utils.ajax({
            url: basepath + "user/user/getIsvInfo",
            async:false,
            success: function(data) {
              if(data.code=="SUCESS") {  

                $xvp.login({
                    app_key : data.result.appId,
                    isv_url: data.result.isvUrl,
                    success : function(xvp_uid){

                      utils.ajax({
                        url: basepath + "/user/user/login",
                        data:{'xvp_uid':xvp_uid},
                        async:false,
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



         // utils.MenuShare();
        
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
            async:false,
            success: function(data) {
              if(data.code=="SUCESS") {
                that.firstloading = true;
                that.baseinfo.title = data.result.store_name; //店铺名称
                that.component1=data.result.products;
                $.each(that.component1,function(i,v){
                    utils.ajax({
                      url: basepath + "/user/product/sku/get",
                      data:{'product_id':v.id},
                      async:false,
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
        "rTabbar": require('../../components/tabbar/tabbar.vue'),
        "rTabbarItem": require('../../components/tabbar/tabbar-item.vue'),
      },
  }
</script>

<style>
  .wq_home .weui_tabbar{
    position: fixed;
  }
</style>