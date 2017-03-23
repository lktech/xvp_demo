<template>
    <div>
        <c-top-back></c-top-back>
        <c-group v-if="!addressStatus">
            <c-cell title="+ 添加收货地址" :link="{path:'/addr/add'}"></c-cell>
        </c-group>
        <c-group v-else>
            <c-address-info :id="addressData.id" :name="addressData.name" :phone="addressData.phone" :arrows="true"
                            :address="addressData.address"></c-address-info>
        </c-group>
        <!--商品列表-->
        <div>
            <c-panel-img listname="商品列表">
                <c-panel-list v-for='item in json.products'
                              :id='item.id'
                              :others='item.num'
                              :title='item.name'
                              :details='item.properties?"规格："+item.properties:"规格：无"'
                              :from='item.price/100'
                              :imglink="item.pic"
                              :colororg='true'>
                </c-panel-list>
            </c-panel-img>
        </div>
        <c-group>
            <c-cell title="优惠" :value="json.discount | formatPriceCNY"></c-cell>
            <c-cell title="运费" :value="json.logistics_fee | formatPriceCNY"></c-cell>
        </c-group>
        <c-pay-bar2 :disabled="disabled" :type="color" :price="totalMoney | formatPrice" @on-button="addOrder" buytext="提交订单"></c-pay-bar2>
    </div>


</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                //初始化数据
                json: {},
                //可选支付列表
                payList: [],
                ticket: {id: "", name: ""},
                noTicketName: "",//不支持开发票的货架商品
                totalMoney: "",//总金额
                addr_id:'',
                order_id:'',
                addressData:{},
                addressStatus:false,
                disabled:true,
                color:'default',
                arr:[]
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                //utils.MenuShare();

                this.json = JSON.parse(utils.getSession("buy_info"));//商品数据列表
                let that = this;
                $.each(this.json.products,function(i,v){
                    that.totalMoney+=v.price*v.num
                    that.arr.push({
                        sku_id:v.id,
                        price:v.price,
                        count:v.num
                    })
                })

                utils.ajax({
                    url: basepath + "/user/user/address/default", success: function (data) {
                        if (data.code=="SUCESS") {
                            if(data.result){
                                that.addressData=data.result;
                                that.addressStatus=true;
                                that.disabled=false;
                                that.color='primary';
                                that.addr_id=data.result.id;
                            }
                        }else{
                            that.$vux.alert.show(data.message);
                        }
                    }
                });

            })
        },
        methods: {
            //提交订单
            addOrder(){
                if (utils.getSession("buy_info")) {
                    let that = this;


                    let obj = {
                        logistic_flg:that.json.logistic_flg,
                        logistics_fee:that.json.logistics_fee,
                        pay_amount:that.totalMoney,
                        addressee_id:that.addr_id,
                        buy_sku_list:that.arr
                    }
                    utils.ajax({
                        url: basepath + "/user/order/add", data: obj, success: function (data) {
                            if (data.code=="SUCESS") {
                                location.href="http://m.sit.xiaovpu.com/wap/order/xvp_cashier.html?orderId="+data.result.order_id;+'&appId=xvp';
                            }else{
                                that.$vux.alert.show(data.message);
                            }
                        }
                    });
                } else {
                    this.$vux.alert.show('请重新选择商品');
                }

            },

        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cGroup": require('../../components/group/group.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cPanelImg": require('../../components/x-panel-list/x-panel-img.vue'),
            "cPanelList": require('../../components/x-panel-list/x-panel-list.vue'),
            "cPayBar2": require('../../components/x-pay-bar/x-pay-bar2.vue'),
            "cAddressInfo": require('../../components/x-address-info/x-address-info.vue'),
        }
    }
</script>

