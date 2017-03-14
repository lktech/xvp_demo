<template>
    <div>
        <c-top-back></c-top-back>
        <c-group>
            <c-step :list="items" :status="json.orderStatus | stepStatus" title="订单状态" :reveal="true"></c-step>
        </c-group>
        <c-panel-img listname="商品列表">
            <c-panel-list v-for='item in json.list'
                          :id='item.id'
                          :others='item.num'
                          :link='{name:"detail",params:{id:item.id}}'
                          :title='item.goodsName'
                          :details='item.sku?item.sku:"无"'
                          :from='item.price | formatPrice'
                          :imglink="item.goodsUrl"
                          :buttonicon='json.orderStatus==odr.dfh?true:false'
                          type="org"
                          :colororg='true'
                          :btnstats="item.btnStatus"
                          size="smaller" :text="item.btnStatus | goodsBtnTxt">
            </c-panel-list>
        </c-panel-img>
        <c-group>
            <c-input title="优惠" placeholder="可输入优惠金额" :value.sync="discount"></c-input>
            <c-cell title="实付金额" :value="json.pay|formatPriceCNY"></c-cell>
        </c-group>
        <c-panel>
            订单编号：{{json.orderNum}}<br/>
            创建时间：{{json.creatTime}}<br/>
            <div v-if="json.orderStatus==odr.close">订单超时：{{json.timeOut}}</div>
            <div v-if="json.orderStatus==odr.dfh">付款时间：{{json.payTime}}</div>
            <div v-if="json.orderStatus==odr.dsh">发货时间：{{json.fhTime}}</div>
            <div v-if="json.orderStatus==odr.ok">收货时间：{{json.shTime}}</div>
        </c-panel>
        <div class="wrap-pd">
            <c-flexbox>
                <c-flexbox-item>
                    <div class="flex-demo">
                        <c-button type="org" @click="updateOrder" size="block"
                                  text="更新订单"></c-button>
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
                items: ["买家付款", "商家发货", "完成交易"],
                json: {//初始化数据
                    take: {},
                    ticket: {},
                    discountInfo: {},
                    subjoin: [],
                },
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
            }
        },
        computed: {},
        ready(){
            utils.MenuShare();
            let that = this;
            $.ajax({
                url: basepath + "/orderdetail",
                dataType: 'json',
                type: 'POST',
                data: {
                    id: that.$route.params.id
                },
                success: function (data) {
                    if (data.success) {
                        that.json = data.obj;
                        utils.loadingHide();
                    }
                }
            });
        },
        methods: {
            //更新订单
            updateOrder(){
                let that = this;
                alert(this.discount);
//                that.$vux.confirm.show({
//                    content: "确认收货后，订单交易完成，钱款将立即到达商家账户。",
//                    onConfirm: function () {
//                        utils.loadingShow();
//                        utils.getJSON_POST(basepath + "/orderdetail", {id: that.$route.params.id}, function (data) {
//                            if (data.success) {
//                                that.json = data.obj;
//                                //something,需要跳转请在标签内配置link
//                                utils.loadingHide();
//                            } else {
//                                that.$vux.toast.show(data.msg);
//                            }
//                        });
//                    }
//                })
            },
        },
        components: {
            "cTopBack": require('../../components/top-back.vue'),
            "cStep": require('../../components/step.vue'),
            "cGroup": require('../../components/group.vue'),
            "cPanelImg": require('../../components/panelImg.vue'),
            "cPanelList": require('../../components/panellist.vue'),
            "cCellWrap": require('../../components/cell-wrap.vue'),
            "cCell": require('../../components/cell.vue'),
            "cPanel": require('../../components/panel.vue'),
            "cButton": require('../../components/button.vue'),
            "cFlexbox": require('../../components/flexbox.vue'),
            "cFlexboxItem": require('../../components/flexbox-item.vue'),
            "cInfo": require('../../components/info.vue'),
            "cAddressInfo": require('../../components/address-info.vue'),
            "cInput": require('../../components/x-input.vue'),
        }
    }
</script>

