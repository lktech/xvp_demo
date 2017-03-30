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
        <c-panel-img v-if="json.orderdeliverys" :listname="json.orderdeliverys[0].logistics_company_name?json.orderdeliverys[0].logistics_company_name:'商品列表'" :moreinform="json.orderdeliverys[0].logistics_num?'运单编号：'+json.orderdeliverys[0].logistics_num:''">
            <c-panel-list v-for='item in json.goods'
                          :id='item.id'
                          :others='item.num'
                          :title='item.goodsName'
                          :details='item.sku?"规格："+item.sku:"规格：无"'
                          :from='item.price | formatPrice'
                          type="org"
                          :imglink="item.goodsUrl+'?imageMogr2/thumbnail/60x'"
                          :colororg='true'
                          :cheng="true">
            </c-panel-list>
        </c-panel-img>
        <c-panel-img v-else listname="商品列表">
            <c-panel-list v-for='item in json.goods'
                          :id='item.id'
                          :others='item.num'
                          :title='item.goodsName'
                          :details='item.sku?"规格："+item.sku:"规格：无"'
                          :from='item.price | formatPrice'
                          type="org"
                          :imglink="item.goodsUrl+'?imageMogr2/thumbnail/60x'"
                          :colororg='true'
                          :cheng="true">
            </c-panel-list>
        </c-panel-img>
        <c-group>
            <c-cell title="优惠" :value="json.seller_discount_fee | formatPriceCNY"></c-cell>
            <c-cell title="运费" :value="json.logistic_fee | formatPriceCNY"></c-cell>
            <c-cell title="实付金额" :value="json.pay | formatPriceCNY"></c-cell>
        </c-group>
        <c-cell-wrap style="padding:10px;">
            订单编号：{{json.orderNum}}<br/>
            创建时间：{{json.create_time}}<br/>
            <div v-if="json.orderStatus==odr.close">订单超时：{{json.timeOut}}</div>
            <div v-if="json.orderStatus==odr.dfh || json.orderStatus==odr.dsh || json.orderStatus==odr.ok">付款时间：{{json.pay_time}}</div>
            <div v-if="json.orderStatus==odr.dsh || json.orderStatus==odr.ok">发货时间：{{json.orderdeliverys[0].create_time}}</div>
            <div v-if="json.orderStatus==odr.ok">收货时间：{{json.confirm_receive_time}}</div>
        </c-cell-wrap>
        <div class="wrap-pd" style="margin-top:10px">
            <c-flexbox>
                <c-flexbox-item>
                    <div class="flex-demo">
                        <c-button v-if="json.orderStatus=='DFK'" type="primary" @click.native="updateOrder" size="block"
                                  text="微信支付"></c-button>
                        <c-button v-if="json.orderStatus=='DSH'" type="primary" @click.native="qrsh" size="block"
                                  text="确认收货"></c-button>
                    </div>
                </c-flexbox-item>
            </c-flexbox>
        </div>

        <c-confirm v-model="show" title="确认收货"
                 @on-cancel="onCancel"
                 @on-confirm="onConfirm">
          <p style="text-align:center;">确认收货后，订单交易完成，钱款将立即到达商家账户</p>
        </c-confirm>
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
                step:0,
                orderStatus:'',
                discount1:'',
                show:false
            }
        },
        computed: {},
        mounted: function () {
            this.$nextTick(function () {
                this.into();
                utils.MenuShare();
            })
        },
        methods: {
            //更新订单
            updateOrder(){
                let that = this;
                utils.ajax({
                    url: basepath + "/user/order/payurl", data: {order_id:that.$route.query.id}, success: function (res) {
                        if (res.code=="SUCESS") {
                            location.href=res.result.url;
                        }else if(data.code=='user_seller_error'){
                                that.$vux.alert.show({content:'访问超时',onHide :function(){
                                  utils.go({path:'/home/home',query:{id:sessionStorage.getItem('product_id')}},that.$router);
                                }});
                        }else{
                            that.$vux.alert.show(res.message);
                        }
                    }
                });
            },
            qrsh(){
                this.show=true;
            },
            into(){
                let that = this;
                utils.ajax({
                    url: "/user/order/get",
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        order_id: that.$route.query.id
                    },
                    success: function (data) {
                        if (data.code=="SUCESS") {
                            that.json=data.result;
                            utils.loadingHide();
                           // that.discount1=data.result.discountInfo;

                            if(data.result.orderStatus=='DFH'){
                                that.step=1;
                            }
                            if(data.result.orderStatus=='DSH'){
                                that.step=2;
                            }
                            if(data.result.orderStatus=='YSH'){
                                that.step=3;
                            }


                            if (data.result.orderStatus == 'DQR') {
                                that.orderStatus_text= "订单状态：订单待确认";
                            } else if (data.result.orderStatus == 'DFK') {
                                that.orderStatus_text= "订单状态：等待买家付款";
                            } else if (data.result.orderStatus == 'DFH') {
                                that.orderStatus_text= "订单状态：等待卖家发货";
                            } else if (data.result.orderStatus == 'DSH') {
                                that.orderStatus_text= "订单状态：卖家已经发货";
                            } else if (data.result.orderStatus == 'YSH') {
                                that.orderStatus_text= "订单状态：完成交易";
                            } else if (data.result.orderStatus == 'YGB') {
                                that.orderStatus_text= "订单状态：订单关闭";
                            }


                        }else if(data.code=='user_seller_error'){
                                that.$vux.alert.show({content:'访问超时',onHide :function(){
                                  utils.go({path:'/home/home',query:{id:sessionStorage.getItem('product_id')}},that.$router);
                                }});
                        }else{
                                that.$vux.alert.show(data.message);
                        }
                    }
                });
            },
            onCancel(){
                this.show=false;
            },
            onConfirm(){
                let that = this;
                utils.ajax({
                    url: basepath + "/user/order/confirm",
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        order_id: that.$route.query.id,
                    },
                    success: function (data) {
                        if (data.code=="SUCESS") {
                            that.into();
                            that.show=false;
                        }else if(data.code=='user_seller_error'){
                                that.$vux.alert.show({content:'访问超时',onHide :function(){
                                  utils.go({path:'/home/home',query:{id:sessionStorage.getItem('product_id')}},that.$router);
                                }});
                        }else{
                            that.$vux.alert.show(data.message);
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
            "cConfirm": require('../../components/confirm/confirm.vue'),
        }
    }
</script>
<style>
    .wq-detail{
        margin-bottom:30px;
    }   
    .wq-detail .vux-step-item-main{
        background-color:#fff !important;
    }
</style>

