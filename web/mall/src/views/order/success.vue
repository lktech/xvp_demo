<template>
    <div>
        <c-msg status="1" msg="订单已支付" :money="money" :score="payWay==2?money:false">
            <c-cell slot="list" title="支付方式" :value="payWay"></c-cell>
            <c-cell slot="list" title="关注店铺公众号" link="http://www.baidu.com"></c-cell>
            <c-button slot="btn" type="primary" size="block"
                      text="返回到首页" @click="link_home"></c-button>
        </c-msg>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                payWay: "",//付款方式
                money: 0,//付款金额

            }
        },
        ready(){
            utils.MenuShare();
            this.payWay = decodeURI(this.$route.params.way);//获得支付方式
            this.money = utils.formatPrice(this.$route.params.money);//获得付款积分

//            window.addEventListener('popstate', function (e) {
//                history.replaceState('home', '小V铺', 'http://www.baidu.com');
//            });

            utils.loadingHide();
        },
        methods: {
            link_home(){
                utils.go({name:'home',params:{'id':this.$route.params.pageId}},this.$router);   
            }
        },
        components: {
            "cMsg": require("../../components/msg.vue"),
            "cButton": require('../../components/button.vue'),
            "cCell": require('../../components/cell.vue'),
        }
    }

</script>