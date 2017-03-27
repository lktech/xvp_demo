<template>
  <div v-if="flag">
    <c-entrance :list="entranceData"></c-entrance>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
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
          flag:false
        }
      },
      mounted: function () {
        this.$nextTick(function () {


          let that=this;

          utils.ajax({
              url: basepath + "/seller/seller/login/check",
              dataType: 'json',
              type: 'POST',
              async:false,
              success: function(data){
                  if(data.code=="SUCESS"){
                    if(data.result.flag=='no'){
                      utils.go({path:'/login/login'},that.$router,true);
                    }
                  }
              }
          });


          utils.ajax({
              url: basepath + "/seller/store/get",
              dataType: 'json',
              type: 'POST',
              success: function(data){
                  if(data.code=="SUCESS"){
                    that.flag=true;
                    var Yin=location.href;
                    var Y=Yin.substring(0,Yin.indexOf('/seller'));
                      $('title').text(data.result.store_name);
                      that.entranceData[3].link=Y+'/mall/index.html#/home/home?id='+data.result.id+'&xv=enter';
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