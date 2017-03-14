<template>
    <div>
        <ul class="orderWrap">
            <li v-for="item in formatList">
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
                            <div class="sku">规格：{{goods.sku?goods.sku:'无'}}</div>
                            <div class="num">￥{{goods.price | formatPrice}} <span>x{{goods.num}}</span></div>
                        </div>
                    </li>
                </ul>
                <div class="orderMoney" v-if="item.orderType=='xuni'">
                    <b>实付积分</b>
                    <span>{{item.score}}积分</span>
                </div>
                <div class="orderMoney" v-else>
                    <b>实付金额</b>
                    <span>￥{{item.pay | formatPrice}}</span>
                </div>
                <div class="orderBtn">
                    <c-button type="org" @click="fukuan(item.orderNum)" v-if="item.orderStatus==odr.dfk"
                              size="small" text="付款"></c-button>
                    <c-button type="org" @click="cxgm(item.orderNum)" v-if="item.orderStatus==odr.close"
                              size="small" text="重新购买"></c-button>
                    <c-button type="org" @click="qrsh(item.orderNum)" v-if="item.orderStatus==odr.dsh"
                              size="small" text="确认收货"></c-button>
                    <c-button :type="item.orderType=='xuni'?'org':'default'" @click="look(item.orderNum)"
                              size="small" text="查看详情"></c-button>
                    <c-button type="default" @click="del(item.orderNum,$index,$event)" v-if="item.orderStatus==odr.dfk"
                              size="small" text="删除订单"
                              :disabled="item.orderStatus!=odr.dfk"></c-button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
    export default{
        data(){
            return {}
        },
        props: ["orderData", "odr", "format"],
        ready(){

        },
        computed: {
            formatList(){
                if (this.format) {
                    return this.formats(this.orderData);
                } else {
                    return this.orderData;
                }
            }
        },
        methods: {
            fukuan: function (orderNum) {
                this.$emit("fukuan", orderNum);
            },
            del: function (orderNum,index, e) {
                if (e.target.getAttribute("disabled") == null) { //没有disabled属性
                    this.$emit("del", orderNum,index);
                }
            },
            look: function (id) {
                this.$emit("look", id);
            }
            ,
            goodsclick: function (id) {
                this.$emit("goto", id);
            }
            ,
            qrsh: function (id) {
                this.$emit("qrsh", id);
            },
            formats:function(data){
                console.log(data)
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
