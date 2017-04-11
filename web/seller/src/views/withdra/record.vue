<template>
<div class="balance">
    <c-top-back></c-top-back>
    <c-cell-wrap :title="item.time" v-for='item in json'>
        <c-cell :title="item.type" :value="item.money"></c-cell>
    </c-cell-wrap>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
              json:[{
                type:'提现手续费',
                time:'2016-08-24 13:23:12',
                money:'3'
              },{
                type:'提现成功',
                time:'2016-08-24 13:23:12',
                money:'3'
              },{
                type:'提现失败',
                time:'2016-08-24 13:23:12',
                money:'3'
              }]
            }
        },

        methods:{
            
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
           })
        },
        components:{
            "cMessages":require('../../components/x-messages/x-messages.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cCellWrap": require('../../components/cell/cell-wrap.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cCell": require('../../components/cell/cell.vue'),
        }
    }
</script>

