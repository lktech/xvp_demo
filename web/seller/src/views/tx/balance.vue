<template>
    <div class="balance">
        <c-top-back></c-top-back>
        <r-messages :src="src" msg="我的账户余额" :money="money" class="account" :fivetext="fivetext">
            <div slot="list" class="slot">
                <r-group>
                    <r-cell title="提现账号" :value="txtStatus" :is-link="islink" :link="link"
                            :class="{flag:isShow}"></r-cell>
                    <r-cell title="提现记录" link="/tx/cashrecord" is-link></r-cell>
                </r-group>
            </div>
            <div slot="btn">
                <r-button type="primary" text="提现" @click.native="btnClick" :disabled="btnDisabled"></r-button>
            </div>
        </r-messages>
    </div>

</template>
<script>
    import utils from '../../libs/utils.js'
    export default {
        data (){
            return {
                src: "data:image/jpeg;base64,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",
                money: 0,
                desc: "提现预计24小时后到账",
                btnDisabled: true,
                txtStatus: "",
                islink: true,
                link: "/tx/verifyInfo",
                accountInfo: {},
                isShow: false,
                fivetext: "提现预计24小时后到账",
                bindCard: false,//是否绑卡成功
                rzStatus: "",
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;
                //可提现金额
                utils.ajax({
                    url: "/seller/account/getAccountAmount",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            let json = res.result;
                            that.money = utils.formatPrice(json.withdrawals_amount);
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                })
                //认证信息
                utils.ajax({
                    url: "/seller/account/get",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            if (res.result) {
                                that.txtStatus = "实名验证已通过";
                                that.islink = false;
                                that.btnDisabled = false;
                                that.link = "";
                                that.accountInfo = res.result;
                                utils.ajax({
                                    url: "/seller/account/getStoreBankCard",
                                    success: function (response) {
                                        if (response.code == "SUCCESS") {
                                            that.isShow = true;
                                            that.txtStatus = response.result.card_no;
                                            that.bindCard = true;//绑卡成功
                                            if (res.result.account_type == 1) {
                                                switch (response.result.card_auth_status) {
                                                    case "WRZ":
                                                        that.fivetext = "银行账户认证待审核，需要等待1个工作日！";
                                                        that.btnDisabled = true;
                                                        break;
                                                    case "RZCG":
                                                        that.fivetext = "提现预计24小时后到账";
                                                        break;
                                                    case "RZSB":
                                                        that.fivetext = "银行账户认证失败，请重新编辑提现账号！";
                                                        that.rzStatus = "rzsb";
                                                        that.bindCard = false;
                                                        break;
                                                }
                                            }
                                        } else {
                                            //
                                        }
                                    }
                                });
                            } else {
                                that.txtStatus = "未实名验证";
                            }
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                });
            })
        },
        methods: {
            btnClick(){
                if (this.bindCard) {
                    utils.go("/tx/withdrawals", this.$router);
                } else {
                    if (this.accountInfo.account_type == 0) {
                        //个人
                        utils.go("/tx/bindCard?type=1", this.$router);
                    } else {
                        //企业
                        utils.go("/tx/bindCard?type=2&rzStatus=" + this.rzStatus, this.$router);
                    }
                }


            }
        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "rGroup": require("../../components/group/group.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rCell": require("../../components/cell/cell.vue"),
            "r-messages": require("../../components/x-messages/x-messages.vue"),
        }
    }

</script>
<style lang="less">
    @import "../../assets/styles/views/personal";
</style>