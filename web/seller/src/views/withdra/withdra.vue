<template>
<div class="withdra">
    <c-top-back></c-top-back>
    <c-group>
        <c-cell title="银行卡" :value="account"></c-cell>
    </c-group>

    <c-group>
         <c-withdraw :maxvalue="maxvalue" :placeholder="placeholder" :words="words" :val="in_money" :minvalue="minvalue" :digit="2" @on-input="getInput"></c-withdraw>
         <div v-if="!in_money" class="flex-demo-withdra" @click="q_withdra">全部提现</div>
      
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
                in_money:'',
                disabled:true,
                maxvalue:1000,
                minvalue:100,
                in_money_status:false,
                words:{
                    error:'请输入正确的金额',
                    min:'提现金额需大于100元',
                    max:'输入金额超过本次可提现金额',
                    empty:'金额不能为空',
                },
                placeholder:'账户余额1000.00'
            }
        },

        methods:{
            getInput(val,status){
                this.in_money=val;
                if(status=='success'){
                    this.color='primary';
                    this.disabled=false;
                }else{
                    this.color='default';
                    this.disabled=true;
                }
            },
            wit_cancel(){

            },
            q_withdra(){
                this.in_money=this.maxvalue+'';
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
    .withdra .weui_cell_ft{
        padding-right:10px;
    }
    .withdra .flex-demo-withdra{
         color:#F57B00;     position: absolute; right: 15px; top: 37px; height:45px; line-height:45px;
    }
</style>

