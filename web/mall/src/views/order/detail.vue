<template>
    <div class="wq-detail">
        <c-top-back></c-top-back>
        <c-cell-wrap :title="orderStatus_text">
            <c-step v-model="step" background-color='#f5f5f5' gutter="10px" style="margin:0 10px">
              <c-step-item title="买家付款"></c-step-item>
              <c-step-item title="商家发货"></c-step-item>
              <c-step-item title="完成交易"></c-step-item>
            </c-step>
            <div style="margin:10px 10px; margin-top:10px; border-top:1px solid #eee;">
                <div style="margin-top:10px;">{{json.address_name}}</div>
                <div style="font-size:12px;">{{json.address_phone}}</div>
                <div style="font-size:12px; margin-top:10px; color:#999;">{{json.address}}</div>
            </div>
        </c-cell-wrap>
        <c-panel-img listname="商品列表">
            <c-panel-list v-for='item in json.xvporderitems'
                          :id='item.id'
                          :others='item.item_count'
                          :title='item.product_name'
                          :details='item.sku_str?"规格："+item.sku:"规格：无"'
                          :from='item.price | formatPrice'
                          type="org"
                          :imglink="item.goodsUrl"
                          :colororg='true'>
            </c-panel-list>
        </c-panel-img>
        <c-group>
            <c-cell title="优惠" :value="json.seller_discount_fee | formatPriceCNY"></c-cell>
            <c-cell title="运费" :value="json.logistic_fee | formatPriceCNY"></c-cell>
            <c-cell title="实付金额" :value="json.pay_amount | formatPriceCNY"></c-cell>
        </c-group>
        <c-cell-wrap style="padding:10px;">
            订单编号：{{json.order_id}}<br/>
            创建时间：{{json.create_time}}<br/>
            <div v-if="json.orderStatus==odr.close">订单超时：{{json.timeOut}}</div>
            <div v-if="json.orderStatus==odr.dfh">付款时间：{{json.payTime}}</div>
            <div v-if="json.orderStatus==odr.dsh">发货时间：{{json.fhTime}}</div>
            <div v-if="json.orderStatus==odr.ok">收货时间：{{json.shTime}}</div>
        </c-cell-wrap>
        <div class="wrap-pd">
            <c-flexbox>
                <c-flexbox-item>
                    <div class="flex-demo">
                        <c-button v-if="orderStatus=='DFK'" type="primary" @click.native="updateOrder" size="block"
                                  text="微信支付"></c-button>
                        <c-button v-if="orderStatus=='DSH'" type="primary" @click.native="qrsh" size="block"
                                  text="确认收货"></c-button>
                    </div>
                </c-flexbox-item>
            </c-flexbox>
        </div>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                json: {//初始化数据
                    take: {},
                    ticket: {},
                    discountInfo: {},
                    subjoin: [],
                },
                orderStatus_text:'订单状态：',
                //订单状态
                odr: {
                    dqr: constants.orderStatus.dqr,//待确认
                    dfk: constants.orderStatus.dfk,//待付款
                    dfh: constants.orderStatus.dfh,//待发货
                    dsh: constants.orderStatus.dsh,//待收货
                    ok: constants.orderStatus.ok,//完成交易
                    close: constants.orderStatus.close//订单关闭（用户关闭/订单超时）
                },
                discount:"",//优惠金额
                step:1,
                orderStatus:'',
                discount1:''
            }
        },
        computed: {},
        mounted: function () {
            this.$nextTick(function () {
                this.into();
            })
        },
        methods: {
            //更新订单
            updateOrder(){
                utils.go("http://m.sit.xiaovpu.com/wap/order/xvp_cashier.html?orderId="+this.$route.query.id+'&appId=xvp',this.$router);
            },
            qrsh(id){
                let that = this;
                utils.ajax({
                    url: basepath + "/user/order/confirm",
                    dataType: 'json',
                    type: 'POST',
                    data: JSON.stringify({
                        order_id: id,
                    }),
                    success: function (data) {
                        if (data.code="SUCESS") {
                            that.into();
                        }else{
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
            },
            into(){
                let that = this;
                utils.ajax({
                    url: "/user/order/get",
                    dataType: 'json',
                    type: 'POST',
                    data: JSON.stringify({
                        orderId: that.$route.query.id
                    }),
                    success: function (data) {
                        if (data.code=="SUCESS") {
                            that.json=data.result;
                            utils.loadingHide();
                           // that.discount1=data.result.discountInfo;
                            that.orderStatus=data.result.order_status;
                        }else{
                                that.$vux.alert.show(data.msg);
                        }
                    }
                });
            }
        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cStep": require('../../components/step/step.vue'),
            "cStepItem": require('../../components/step/step-item.vue'),
            "cGroup": require('../../components/group/group.vue'),
            "cPanelImg": require('../../components/x-panel-list/x-panel-img.vue'),
            "cPanelList": require('../../components/x-panel-list/x-panel-list.vue'),
            "cCellWrap": require('../../components/cell/cell-wrap.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cPanel": require('../../components/panel/panel.vue'),
            "cButton": require('../../components/button/button.vue'),
            "cFlexbox": require('../../components/flexbox/flexbox.vue'),
            "cFlexboxItem": require('../../components/flexbox/flexbox-item.vue'),
            "cAddressInfo": require('../../components/x-address-info/x-address-info.vue'),
            "cInput": require('../../components/input/input.vue'),
        }
    }
</script>
<style>
    .wq-detail .vux-step-item-main{
        background-color:#fff !important;
    }
</style>

