<template>
  <div>
    <c-entrance :list="entranceData"></c-entrance>
  </div>

</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          entranceData: [{ //九宫格
            title: '添加商品',
            link:'/product/addSelf',
            icon:'http://img1.xiaovpu.com/3027352051236343.png'
          }, {
            title: '我的仓库',
            link:'/product/warehouse',
            icon:'http://img1.xiaovpu.com/3027346770478267.png'
          }, {
            title: '订单管理',
            link: '/order/list',
            icon:'http://img1.xiaovpu.com/3027353966276215.png'
          }, {
            title: '我的店铺',
            link: '',
            icon:'http://img1.xiaovpu.com/3027356383439235.png'
          }, {
            title: '店铺设置',
            link:'Setting',
            icon:'http://img1.xiaovpu.com/3027349865458448.png'
          }],
        }
      },
      mounted: function () {
        this.$nextTick(function () {
          let that=this;
          utils.ajax({
              url: basepath + "/seller/store/get",
              dataType: 'json',
              type: 'POST',
              success: function(data){
                  if(data.code=="SUCESS"){
                    var Yin=location.href;
                    var Y=Yin.substring(0,Yin.indexOf('/seller'));
                      $('title').text(data.result.store_name);
                      that.entranceData[3].link=Y+'/mall/index.html#/home/home?id='+data.result.id+'&xv=enter';
                  }else if(data.code=='auth_seller_error'){
                              utils.wang(that,utils,data.message);

                          }
              }
          });

         })
        },
      methods: {},
      components: {
        "cEntrance": require('../../components/x-entrance/x-entrance.vue'),
      },
  }
</script>