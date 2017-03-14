<template>
    <div>
        <c-top-back></c-top-back>
        <c-group>
            <c-step v-if="step" :list="items" :status="json.orderStatus" title="订单状态" :reveal="true"></c-step>
        </c-group>
        <c-panel-img listname="商品列表">
            <c-panel-list v-for='item in json.list'
                          :id='item.id'
                          :others='item.num'
                          :link='{name:"detail",params:{id:item.id}}'
                          :title='item.goodsName'
                          :details='item.sku?"规格："+item.sku:"规格：无"'
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
            <c-cell title="优惠" v-if="readonly" :value="discount | formatPriceCNY"></c-cell>
            <c-input title="优惠" v-else placeholder="可输入优惠金额" :value.sync="discount"></c-input>
            <c-cell title="实付金额" :value="json.pay|formatPriceCNY"></c-cell>
        </c-group>
        <c-panel>
            订单编号：{{json.id}}<br/>
            创建时间：{{json.createTime}}<br/>
            <div v-if="json.orderStatus==odr.close">订单超时：{{json.timeOut}}</div>
            <div v-if="json.orderStatus==odr.dfh">付款时间：{{json.payTime}}</div>
            <div v-if="json.orderStatus==odr.dsh">发货时间：{{json.fhTime}}</div>
            <div v-if="json.orderStatus==odr.ok">收货时间：{{json.shTime}}</div>
        </c-panel>
        <div class="wrap-pd">
            <c-flexbox>
                <c-flexbox-item>
                    <div class="flex-demo">
                        <c-button v-if="orderStatus=='DFK' || orderStatus=='DFH'" type="org" @click="updateOrder" size="block"
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
                step:false,
                readonly:false,
                orderStatus:'',
                discount1:''
            }
        },
        computed: {},
        ready(){
            let that = this;
            utils.ajax({
                url: basepath + "/mall/order/order_detail",
                dataType: 'json',
                type: 'POST',
                data: JSON.stringify({
                    orderId: that.$route.query.id
                }),
                success: function (data) {
                    if (data.success) {
                        that.json = data.obj;
                        utils.loadingHide();
                        that.discount=data.obj.discountInfo/100+'';
                        that.discount1=data.obj.discountInfo;
                        that.step=true;
                        that.orderStatus=data.obj.orderStatus;
                        if(data.obj.orderStatus!='DFK'){
                            that.readonly=true;
                        }
                    }else{
                            that.$vux.alert.show(data.msg);
                    }
                }
            });
        },
        methods: {
            //更新订单
            updateOrder(){
                if(this.discount*100<=this.discount1+this.json.pay){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/mall/order/discount_amount_update",
                        dataType: 'json',
                        type: 'POST',
                        data: JSON.stringify({
                            orderId: that.$route.query.id,
                            discountInfo:that.discount*100
                        }),
                        success: function (data) {
                            if (data.success) {
                                that.$vux.alert.show({content:'更新成功',onHide :function(){
                                  utils.go({name:'order'},that.$router,true);
                                }});
                            }else{
                                that.$vux.alert.show('更新失败');
                            }
                        }
                    });
                }else{
                    this.$vux.alert.show('您输入的优惠金额大于实付金额');
                }
                

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

