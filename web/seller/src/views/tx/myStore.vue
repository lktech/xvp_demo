<template>
    <div class="index">
        <x-withdraw-count :info="item" @goClick="goClick"></x-withdraw-count>
        <ul class="list">
            <li>
                <img src="../../assets/images/1.png"/>
                <p>添加商品</p>
            </li>
            <li>
                <img src="../../assets/images/2.png"/>
                <p>我的仓库</p>
            </li>
            <li>
                <img src="../../assets/images/3.png"/>
                <p>订单管理</p>
            </li>
            <li>
                <img src="../../assets/images/4.png"/>
                <p>我的店铺</p>
            </li>
            <li>
                <img src="../../assets/images/5.png"/>
                <p>店铺设置</p>
            </li>
        </ul>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        data(){
            return {
                item: {
                    title: "可提现金额",
                    money: "",
                    subTitleL: "今日收入",
                    subMoneyL: "",
                    subTitleR: "累计收入",
                    subMoneyR: "",
                },
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;

                utils.ajax({
                    url: "/seller/account/getAccountAmount",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            let json = res.result;
                            that.item.money = utils.formatPrice(json.withdrawals_amount);
                            that.item.subMoneyL = utils.formatPrice(json.today_income_amount);
                            that.item.subMoneyR = utils.formatPrice(json.income_amount);
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                })
            })
        },
        methods: {
            goClick(){
                utils.go("/balance", this.$router);
            }
        },
        components: {
            "xWithdrawCount": require('../../components/x-withdraw/x-withdraw-count.vue'),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../../assets/styles/views/index";
</style>
