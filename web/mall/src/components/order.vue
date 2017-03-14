<template>
    <div>
        <ul class="orderWrap">
            <li v-for="item in orderData">
                <div class="orderHeader">
                    <b>订单：{{item.orderNum}}</b>
                    <span>{{item.orderStatus | orderStatus}}</span>
                </div>
                <ul class="goodsOrder">
                    <li v-for="goods in item.goods" @click="goodsclick(goods.id)">
                        <div class="goodsImg">
                            <img :src="goods.goodsUrl"/>
                        </div>
                        <div class="goodsDesc">
                            <div class="goodsName">{{goods.goodsName}}</div>
                            <div class="sku">规格：{{ goods.skuFlag?goods.sku:"无"}}</div>
                            <div class="num">￥{{goods.price | formatPrice}} <span>x{{goods.num}}</span></div>
                        </div>
                    </li>
                </ul>
                <div class="orderMoney">
                    <b>实付金额</b>
                    <span>￥{{item.pay | formatPrice}}</span>
                </div>
                <div class="orderBtn">
                    <c-button type="default" @click="look({id:item.id,num:item.orderNum})"
                              size="small" text="查看详情"></c-button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    import constants from '../libs/constants.js';
    export default{
        data(){
            return {}
        },
        props: ["orderData", "odr"],
        ready(){

        },
        computed: {},
        methods: {
            //查看详情
            look: function (obj) {
                utils.go({name:"oddetail",params:{id:obj.id}},this.$router)
            },
            //查看商品
            goodsclick: function (id) {
                utils.go({name:"detail",params:{id:id}},this.$router)
            }
        },
        components: {
            "cButton": require('../components/button.vue'),
        }
    }

</script>

<style lang="less">
    @import '../assets/styles/components/order.less';
</style>
