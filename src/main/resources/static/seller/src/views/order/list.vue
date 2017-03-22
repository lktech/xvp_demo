<template>
    <div class="wq-list">
        <c-top-back></c-top-back>
        <c-tab>
            <c-tab-item @on-item-click='orderList' selected>全部</c-tab-item>
            <c-tab-item @on-item-click='orderList("DFK")'>待付款</c-tab-item>
            <c-tab-item @on-item-click='orderList("DFH")'>待发货</c-tab-item>
            <c-tab-item @on-item-click='orderList("DSH")'>已发货</c-tab-item>
            <c-tab-item @on-item-click='orderList("YSH")'>已完成</c-tab-item>
        </c-tab>
        <c-order :order-data="orderData" type="primary" :order-status="odr"  @look="look" @fahuo="fahuo"></c-order>
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
                    dfh: constants.orderStatus.dfh,//待发货
                },
                url: basepath + "/seller/order/query",
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
            tabChange(id){
                //tab标签
                switch (id) {
                    case 0:
                        this.orderList();
                        break;
                    case 1:
                        this.orderList("DFK");
                        break;
                    case 2:
                        this.orderList("DFH");
                        break;
                    case 3:
                        this.orderList("DSH");
                        break;
                    case 4:
                        this.orderList("YSH");
                        break;
                }
            },
            //订单列表
            orderList(id){
                let that = this;
                utils.ajax({
                    url:"/seller/order/query", type:'post', data: {order_status:id}, success: function (data) {
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
