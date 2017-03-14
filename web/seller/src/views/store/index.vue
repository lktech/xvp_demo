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
            link: {
              name: 'pdtAddSelf'
            },
            icon:'http://img1.xiaovpu.com/3027352051236343.png'
          }, {
            title: '我的仓库',
            link: {
              name: 'warehouse'
            },
            icon:'http://img1.xiaovpu.com/3027346770478267.png'
          }, {
            title: '订单管理',
            link: {
              name: 'order'
            },
            icon:'http://img1.xiaovpu.com/3027353966276215.png'
          }, {
            title: '我的店铺',
            link: '',
            icon:'http://img1.xiaovpu.com/3027356383439235.png'
          }, {
            title: '店铺设置',
            link: {
              name: 'storeSetting'
            },
            icon:'http://img1.xiaovpu.com/3027349865458448.png'
          }],
        }
      },
      ready() {
        let that=this;
        utils.ajax({
            url: basepath + "/mall/auth",
            dataType: 'json',
            type: 'POST',
            async:false,
            success: function(data){
                if(!data.success){
                  utils.go({name:'login'},that.$router);
                }else{
                  utils.header();
                }
            }
        });
        utils.ajax({
            url: basepath + "/mall/store/get",
            dataType: 'json',
            type: 'POST',
            success: function(data){
                if(data.success){
                    document.setTitle(data.obj.name);
                    that.entranceData[3].link='http://m.fvt.xiaovpu.com/wap/order/index.html?#!/home/'+data.obj.page_id+'?xv=enter';
                }
            }
        });
      },
      methods: {},
      components: {
        "cEntrance": require('../../components/entrance.vue'),
      },
  }
</script>