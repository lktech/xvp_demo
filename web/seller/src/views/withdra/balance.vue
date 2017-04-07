<template>
<div class="balance">
    <c-top-back></c-top-back>
    <c-group>
        <c-messages :status="3" msgColor='red' desc="我的账户余额" :money="50.00" :fivetext='fivetext'></c-messages>
        <c-cell title="提现账号" :value="account" is-link></c-cell>
        <c-cell title="提现纪录" is-link></c-cell>
       
    </c-group>
    <div class="wrap-pd"  style="margin-top:10px">
        <c-button text="提现" :type="color" :disabled="disabled" size="block"></c-button>
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
                account:"未实名认证",
                color:'default',
                disabled:true,
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
            "cGroup":require('../../components/group/group.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cCell": require('../../components/cell/cell.vue'),
        }
    }
</script>
<style>
    .balance .weui_cell_ft{
        padding-right:10px;
    }
</style>

