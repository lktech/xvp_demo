<template>
    <div>
        <c-top-back></c-top-back>
        <c-cell-wrap>
            <c-cell title="银行卡" :value='bank_code'></c-cell>
        </c-cell-wrap>
        <c-cell-wrap>
        	<c-input-num 
        		title="提现金额" 
        		:maxvalue="maxvalue/100" 
        		:words="words" 
        		:val="input_money" 
        		:minvalue="100" 
        		:digit="2" 
        		@on-input="getInput">
        	</c-input-num>
            <c-cell 
            	:title="placeholder" 
            	value='全部提现' 
            	@ondelete='toall' 
            	rightcolor='org' 
            	class='overcell'>
            </c-cell>
        </c-cell-wrap>
        <c-cell-wrap>
            <c-cell title="提现手续费" :value="counter*100|formatPrice"></c-cell>
            <c-cell title="实际提现金额" :value='money*100|formatPrice'></c-cell>
            <c-cell title="提现审核周期" value='1-2个工作日'></c-cell>
        </c-cell-wrap>
        <div class="wrap">
            <c-button 
            	text="确定提现" 
            	:type="color" 
            	:disabled="disabled" 
            	@click.native="preserve" 
            	size="block">
            </c-button>
            <c-button 
            	text="取消" 
            	@click.native="cancel" 
            	type="gray" 
            	size="block"
                style='color: #7D7D7D;background-color: #E6E6E6;'>
            </c-button>
        </div>
        <r-popup v-model="cash_result" height="100%">
            <div class="popup1">
                <c-msg :status="cash_status" :msg="cash_title">
                    <div slot="btn">
                        <c-button 
                        	:text="cash_text" 
                        	type="primary" 
                        	size="block" 
                        	@click.native="btnClick"
                            style='background-color: #5ABA5A;'>
                        </c-button>
                    </div>
                </c-msg>
            </div>
        </r-popup>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                color: 'default',                       //确定提现按钮颜色
                disabled: true,                       //确定提现是否禁用
                bank_code: '',                           //银行卡
                counter: 3,                           //提现手续费
                money: 0,                           //实际提现金额
                input_money: null,                              //输入的金额
                maxvalue: 100000,                                 //最大提现金额
                cash_result: false,
                cash_text: '查看提现记录',
                cash_title: '提现成功',
                cash_status: 0,
                words:{
                    error:'请输入正确的金额',
                    min:'提现金额需大于100元',
                    max:'输入金额超过本次可提现金额',
                    empty:'金额不能为空',
                },

            }
        },
        mounted: function () {
            this.$nextTick(function () {
//				utils.MenuShare();
                let that = this;
                utils.ajax({
                    url: "/seller/account/getStoreBankCard",   //绑卡信息
                    dataType: 'json',
                    type: 'POST',
                    data: {
//                      'userId': this.$route.query.id
                    },
                    success: function (data) {
                        if (data.code == 'SUCCESS') {
                            that.bank_code = data.result.card_no;
                        } else {
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
                utils.ajax({
                    url: "/seller/account/getAccountAmount",   //账户金额
                    dataType: 'json',
                    type: 'POST',
                    data: {
//                      'userId': this.$route.query.id
                    },
                    success: function (data) {
                        if (data.code == 'SUCCESS') {
                            that.maxvalue = data.result.withdrawals_amount;
                        } else {
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
            })
        },
        methods: {
            cancel(){
                //取消按钮
                utils.go({name: 'balance', query: {}}, this.$router);   //回到余额
            },
            tocash(that){
                console.log('that.money', that.money);
                console.log('commission', that.counter);
                utils.ajax({
                    url: "/seller/account/withDrawals",
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        'amount': that.money,    //实际提现金额
                        'commission': that.counter,        // 手续费
                    },
                    success: function (data) {
                        that.cash_result = true;
                        if (data.code == 'SUCCESS') {
                            that.cash_text = '查看提现记录';
                            that.cash_title = '提现成功';
                            that.cash_status = 1;
                        } else {
                            that.cash_text = '返回';
                            that.cash_title = '提现失败';
                            that.cash_status = 0;
                        }
                    }
                });
            },
            preserve(){    // 确定提现
                let that = this;
                that.tocash(that);
            },
            getInput(v, s){
                let that = this;
                that.input_money = v;
                if (s == 'success') {
                    that.statusCtrl(v, that);
                } else {
                    that.disabled = true;
                    that.color = 'default';
                }

            },
            toall(){   //全部提现
                let that = this;
                $('[data_id=inputNum]').val(that.maxvalue);
                that.statusCtrl(that.maxvalue/100, that);
            },
            btnClick(){
                if (this.cash_text == '查看提现记录') {
                    utils.go({name: 'cashrecord', query: {}}, this.$router);   // 回到余额
                } else {
                    utils.go({name: 'balance', query: {}}, this.$router);   // 回到余额
                }

            },
            statusCtrl(v, that){   // 状态变化
                that.input_money = v;
                that.disabled = false;
                if(that.input_money>0){
                	that.color = 'primary';
                	that.money = (that.input_money - that.input_money * 0.006 - 3).toFixed(2);
	                if (that.input_money > 100) {
	                    that.counter = 3 + that.input_money * 0.006;
	                } else {
	                    that.counter = 3;
	                }
                }else{
                	that.color = 'default';
                	that.disabled = true;
                };
            }
        },
        computed: {
            placeholder: function () {
                return "账号余额 " + this.maxvalue/100;
            }
        },
        components: {
            "cGroup": require('../../components/group/group.vue'),
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cButton": require('../../components/button/button.vue'),
            "cCellWrap": require('../../components/x-cell/cell-wrap.vue'),
            "cCell": require('../../components/x-cell/cell.vue'),
            "cInputNum": require('../../components/x-withdraw/x-withdraw.vue'),
            "cMsg": require('../../components/x-messages/x-messages.vue'),
            "rPopup": require('../../components/popup/popup.vue')
        }
    }
</script>


