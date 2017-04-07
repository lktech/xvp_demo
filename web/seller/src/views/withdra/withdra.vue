<template>
<div class="balance">
    <c-top-back></c-top-back>
    <c-group>
        <c-cell title="银行卡" :value="account"></c-cell>
    </c-group>

    <c-group>
        <c-flexbox>
          <c-flexbox-item :span="10"><c-withdraw :maxvalue="maxvalue" :minvalue="minvalue" :digit="2" @on-input="getInput"></c-withdraw></c-flexbox-item>
          <c-flexbox-item :span="2"><div class="flex-demo">2</div></c-flexbox-item>
        </c-flexbox>
      
    </c-group>

    <c-group>
        <c-cell title="提现手续费" :value="counter"></c-cell>
        <c-cell title="实际提现金额" :value="money"></c-cell>
        <c-cell title="提现审核周期" :value="cycle"></c-cell>
    </c-group>
    <div class="wrap-pd"  style="margin-top:10px">
        <c-button text="确定提现" :type="color" :disabled="disabled" size="block"></c-button>
    </div>
    <div class="wrap-pd"  style="margin-top:10px">
        <c-button text="取消" type="default" size="block" @click.native="wit_cancel"></c-button>
    </div>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                fivetext:'提现预计24小时后到账',
                account:"**** **** **** 1212",
                color:'default',
                counter:'0.00元',
                money:'0.00元',
                cycle:'1-2个工作日',
                disabled:true,
                maxvalue:1000,
                minvalue:100,

            }
        },

        methods:{
            getInput(val){

            },
            wit_cancel(){

            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
           })
        },
        components:{
            "cMessages":require('../../components/x-messages/x-messages.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cGroup":require('../../components/group/group.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cWithdraw": require('../../components/x-withdraw/x-withdraw.vue'),
            "cFlexbox": require('../../components/flexbox/flexbox.vue'),
            "cFlexboxItem": require('../../components/flexbox/flexbox-item.vue'),
        }
    }
</script>
<style>
    .balance .weui_cell_ft{
        padding-right:10px;
    }
</style>

