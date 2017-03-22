<template>
    <div class="wq-list">
        <c-top-back></c-top-back>
        <c-tab>
            <c-tab-item @on-item-click='orderList' selected>全部</c-tab-item>
            <c-tab-item @on-item-click='orderList("DFK")'>待付款</c-tab-item>
            <c-tab-item @on-item-click='orderList("DFH")'>待发货</c-tab-item>
            <c-tab-item @on-item-click='orderList("DSH")'>已发货</c-tab-item>
            <c-tab-item @on-item-click='orderList("YWC")'>已完成</c-tab-item>
        </c-tab>
        <c-order :order-data="orderData" type="primary" :order-status="odr"  @fukuan="fukuan"  @qrsh="qrsh"  @look="look" @fahuo="fahuo"></c-order>
        <c-scroll-load @on-load="load" :url='url'><!-- 列表滚动加载--></c-scroll-load>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                orderData: [],
                //订单状态
                odr: {
                    dqr: constants.orderStatus.dqr,//待确认
                    dfk: constants.orderStatus.dfk,//待付款
                    dfh: constants.orderStatus.dfh,//待发货
                    dsh: constants.orderStatus.dsh,//待收货
                    ok: constants.orderStatus.ok,//完成交易
                    close: constants.orderStatus.close//订单关闭（用户关闭/订单超时）
                },
                url: basepath + "/mall/order/order_list",
                status:''
            }
        },
        mounted: function () {
            this.$nextTick(function () {
            //所有订单列表
                this.orderList();
           })
        },
        methods: {
            load(data) {
                if (data) {
                    this.list = data;
                }
            },
            look(id){
                utils.go({path:'detail',query:{'id':id}},this.$router);
            },
            fahuo(id){
                utils.go({path:'logistics',query:{'id':id}},this.$router,true);
            },
            fukuan(id){
                utils.go("http://m.sit.xiaovpu.com/wap/order/xvp_cashier.html?orderId="+id+'&appId=xvp',this.$router);
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
                            that.orderList(that.status);
                        }else{
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
            },
            //订单列表
            orderList(id){
                let that = this;
                that.status=id?id:'';
                utils.ajax({
                    url:"/user/order/query", type:'post', data: JSON.stringify({order_status:id?id:''}), success: function (data) {
                        if (data.code=="SUCESS") {
                            that.orderData = data.result;
                        }else{
                            that.orderData = [];
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
            }

        },

        components: {
            "cTab": require('../../components/tab/tab.vue'),
            "cTabItem": require('../../components/tab/tab-item.vue'),
            "cOrder": require('../../components/x-order/x-order.vue'),
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            // 滚动加载
            "cScrollLoad": require('../../components/x-scroll-load/x-scroll-load.vue'),
        }
    }
</script>
<style>
    .wq-list button{
        width:auto !important; display:inline-block !important;
    }
</style>
