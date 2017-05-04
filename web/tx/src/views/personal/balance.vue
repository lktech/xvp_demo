<template>
    <div class="balance">
        <c-top-back></c-top-back>
        <r-messages :status="1" msg="我的账户余额" :money="money" :desc="desc" class="account">
            <div slot="list" class="slot">
                <r-group>
                    <r-cell title="提现账号" value="未实名验证"></r-cell>
                    <r-cell title="提现记录" is-link></r-cell>
                </r-group>
            </div>
            <div slot="btn">
                <r-button type="primary" text="提现" @click.native="btnClick"></r-button>
            </div>
        </r-messages>
    </div>

</template>
<script>
    import utils from '../../libs/utils.js'
    export default {
        data (){
            return {
                money:1000,
                desc:"提现预计24小时后到账",
            }
        },

        methods: {
            btnClick(){

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
