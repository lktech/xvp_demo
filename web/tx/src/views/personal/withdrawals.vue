<template>
<div>
    <c-top-back></c-top-back>
    <c-cell-wrap>
        <c-cell title="银行卡" :value='bank_code'></c-cell>
    </c-cell-wrap>
    <c-cell-wrap>
        <c-input-num title="提现金额" :maxvalue="maxvalue" :minvalue="5" :digit="2" @on-input="getInput"></c-input-num>
    </c-cell-wrap>
    <c-cell-wrap>
        <c-cell title="提现手续费" :value='counter'></c-cell>
        <c-cell title="实际提现金额" :value='money'></c-cell>
        <c-cell title="提现审核周期" value='1-2个工作日'></c-cell>
    </c-cell-wrap>
    <div class="wrap-pd">
        <c-button text="确定提现" :type="color" :disabled="disabled" @click="preserve" size="block"></c-button>
        <c-button text="取消" @click="cancel" type="default" size="block"></c-button>
    </div>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return { 
                color:'default',                       //确定提现按钮颜色
                disabled:true,                       //确定提现是否禁用
                bank_code:'**** **** **** 6666',                           //银行卡
                counter:'3.00元',                           //提现手续费
                money:'0.00元',                           //实际提现金额
                input_money:'',                              //输入的金额
                maxvalue:'1000'                                 //最大提现金额

            }
        },
        ready(){
            utils.MenuShare();
            let that=this;
            utils.ajax({
                url: basepath + "/app/money",
                dataType: 'json',
                type: 'POST',
                data:{
                    'userId':this.$route.query.id
                },
                success: function(data){
                    if(data.success){
                        that.maxvalue=data.money;
                    }else{
                        that.$vux.alert.show(data.msg);
                    }
                }
            });
        },
        methods:{
            cancel(){                                               //取消按钮
                var link=this.$router._currentTransition.from.name;
                utils.go({name:link,query:{}},this.$router,true);
            },
            preserve(){
                this.$vux.alert.show('提现成功');
            },
            getInput(v,s){
                this.input_money=v;
                if(s=='success'){
                    this.disabled=false;
                    this.color='org';
                    this.money=(v-v*0.006-3).toFixed(2)+'元';
                }else{
                    this.disabled=true;
                    this.color='default';
                }
                
            }
        },
        components:{
            "cGroup":require('../../components/group/group.vue'),
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cButton": require('../../components/button/button.vue'),
            "cCellWrap": require('../../components/x-cell/cell-wrap.vue'),
        	"cCell": require('../../components/x-cell/cell.vue'),
            "cInputNum": require('../../components/x-withdraw/x-withdraw.vue')
        	
        }
    }
</script>

