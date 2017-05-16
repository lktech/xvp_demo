<template>
  <div>
    <div class="tabbar-wrap" v-if='firstloading==true'>
      <!-- banner -->
      <c-top-back :mar='mar'></c-top-back>
      <!--店铺标志-->
      <c-banner-info :logo="baseinfo.logo" banner='http://dl.bizhi.sogou.com/images/2012/01/25/91851.jpg'>
        <div slot="inner">
          <div>
            <div class="fontbg">{{baseinfo.title}}</div>
          </div>
        </div>
        <!-- 待付款tab栏 -->
        <c-banner-tab slot="footer">
          <c-tabbar :positions='inherit'>
            <c-tabbar-item v-for='item in buy' :link="item.link" :icon-class='item.iconClass'>
              <span slot="label">{{item.title}}</span>
            </c-tabbar-item>
          </c-tabbar>
        </c-banner-tab>
      </c-banner-info>
      <div v-for='item in component'>
        <!--九宫格-->
        <c-entrance v-if="item.type == 'entranceData'" :list="item.entranceData"></c-entrance>
        <!-- swiper-->
        <c-swiper loop auto v-if="item.type == 'swiperlist'" :list="item.swiperlist"></c-swiper>
        <!-- 底部导航栏 -->
      </div>
      <c-tabbar>
        <c-tabbar-item v-for='item in iconlist' :link="item.link" :icon-class='item.iconClass' :selected='item.selected'>
          <!--<img slot="icon" src="../../assets/images/cart.png">-->
          <span slot="label">{{item.text}}</span>
        </c-tabbar-item>
      </c-tabbar>
    </div>
  </div>

</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          mar: true, //去掉边距
          inherit: true,
          firstloading: false,
          baseinfo: {},
          buy: [],
          entranceData: [],
          swiperlist: [],
          iconlist: [],
          component:[],
        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        utils.ajax({
            url:"/app/msg/user",
            success:function(data){
              if(data.success) {
                that.firstloading = true;
                that.baseinfo = data.obj.baseinfo;
                that.buy = data.obj.buy;
                that.component = data.obj.component;
                that.iconlist = data.obj.iconlist;
            } else {
              that.$vux.alert.show(data.msg);
            }
            },
          });
      },
      methods: {},
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cBannerInfo": require('../../components/banner-info.vue'),
        "cBannerTab": require('../../components/banner-tab.vue'),
        "cEntrance": require('../../components/entrance.vue'),
        "cSwiper": require('../../components/swiper.vue'),
        //底部导航
        "cTabbar": require('../../components/tabbar.vue'),
        "cTabbarItem": require('../../components/tabbar-item.vue'),
      },
  }
</script>
<style lang="less" scoped>
  .fontbg {
    margin-top: 5px;
  }
</style>