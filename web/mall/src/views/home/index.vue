<template>
  <div>
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
        <c-search @on-submit="searchSubmit" :results="searchList" btncolor="org">
        	<c-data-null msg='暂无商品信息' v-if='status'></c-data-null>
        </c-search>
        <c-product-list :url='product_list_url' v-for="item in component" :params='{shelf_id:item.shelfId}'></c-product-list>
      </div>
      <c-data-null v-else msg='这个店家很懒，什么都没有留下！'></c-data-null>
      <c-scroll-load @on-load="load" eleid="search_show" :url='searchurl' :params='params'></c-scroll-load>
    </div>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
        	status:false,   // 没有搜索到匹配的商品则显示‘暂无商品信息’的控制开关
          mar: true, //去掉边距
          firstloading: false, // 首次加载
          baseinfo: {
            logo:'http://pm2.fvt.xiaovpu.com/wap/order/seller/images/logo.png',
            title:''
          }, //店铺基本信息
          searchList: [], // 搜索列表
          component: [], // 商品列表的判断
          product_list_url: basepath + "/mall/store/shelf_product_list", //货架接口
          searchurl:basepath + "/mall/store/product_search", // 搜索接口
          params:{"pageNumber":1,"pagesize":10} // 滚动加载数量控制
        }
      },
      ready() {
        utils.setSession('pageId',this.$route.params.id);
        let that = this;
        utils.ajax({
          url: basepath + "/mall/store/index",
          data:JSON.stringify({'page_id':that.$route.params.id*1}),
          async:false,
          success: function(data) {
            if(data.success) {
              that.firstloading = true;
              that.baseinfo = data.obj.baseinfo; //店铺基本信息
              that.component = data.obj.component; // 组件
              sessionStorage.setItem('title_',that.baseinfo.title);
              sessionStorage.setItem('link_','http://m.fvt.xiaovpu.com/wap/order/index.html#!/home/'+that.$route.params.id+'?xv=enter');
              sessionStorage.setItem('logo_',that.baseinfo.logo);
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });

        utils.MenuShare();
        
      },
      methods: { // 搜索
        searchSubmit(val) {
          let that = this;
          utils.ajax({
            url: that.searchurl,
            data: JSON.stringify({
              "product_name": val, // 输入的值
              "page_num":1,
              "page_size":utils.pageSize
            }),
            success: function(data) {
              if(data.success) {
                that.searchList = data.obj.searchList; // 搜索列表
                if(!that.searchList.length){
                	that.status=true;
                }
              } else {
                that.$vux.alert.show(data.msg);
              }
            },
          });
        },
        // 滚动加载
        load(data) {
          if(data) {
            let that = this;
            that.searchList = that.searchList.concat(data);
          }
        },
      },
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cBannerInfo": require('../../components/banner-info.vue'),
        "cSearch": require('../../components/search.vue'),
        "cProductList": require('../../components/product-list.vue'),
        "cDataNull": require('../../components/datanull.vue'),
        // 滚动加载
        "cScrollLoad": require('../../components/scroll-load.vue'),
      },
  }
</script>