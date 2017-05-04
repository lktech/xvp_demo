<template>
    <div class="balance">
        <c-top-back></c-top-back>
        <r-messages :status="1" msg="我的账户余额" :money="money" :desc="desc">
            <div slot="list" class="slot" v-if="show">
                <r-group>
                    <r-cell title="支付方式" :value="payWay"></r-cell>
                    <!--<r-cell title="关注妈妈公开课公众号" @click.native="openBtn" is-link></r-cell>-->
                </r-group>
            </div>
            <div slot="btn">
                <r-button type="primary" :text="btnText" @click.native="btnClick"></r-button>
            </div>
        </r-messages>
    </div>

</template>
<script>
    import utils from '../../libs/utils.js'
    export default {
        data (){
            return {
                money:1000
            }
        },

        methods: {},
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "rPopup": require("../../components/popup/popup.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rCell": require("../../components/cell/cell.vue"),
            "r-messages": require("../../components/x-messages/x-messages.vue"),
        }
    }

</script>
