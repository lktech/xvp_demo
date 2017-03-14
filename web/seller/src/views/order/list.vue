<template>
    <div>
        <c-top-back></c-top-back>
        <c-tab :list="[{key:0,value:'全部'},{key:1,value:'待付款'},{key:2,value:'待发货'},{key:3,value:'已发货'},{key:4,value:'已完成'}]"
               active="0"
               active-color='#f05b0b' @on-change="tabChange"></c-tab>
        <c-order :order-data="orderData" :odr="odr" @look="look"></c-order>
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
                    /*dqr: constants.orderStatus.dqr,//待确认
                    dfk: constants.orderStatus.dfk,//待付款
                    dfh: constants.orderStatus.dfh,//待发货
                    dsh: constants.orderStatus.dsh,//待收货
                    ok: constants.orderStatus.ok,//完成交易
                    close: constants.orderStatus.close//订单关闭（用户关闭/订单超时）*/
                },
                url: basepath + "/mall/order/order_list",
            }
        },
        ready(){
            //所有订单列表
            this.orderList();
        },
        methods: {
            load(data) {
                if (data) {
                    this.list = data;
                }
            },
            look(id){
                utils.go({name:'oddetail',query:{'id':id}},this.$router);
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
                    url: basepath + "/mall/order/order_list", data: id?JSON.stringify({orderStatus:id}):{}, success: function (data) {
                        if (data.success) {
                            that.orderData = data.obj.list;
                        }else{
                            that.orderData = [];
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            }

        },

        components: {
            "cTab": require('../../components/tab.vue'),
            "cOrder": require('../../components/order.vue'),
            "cTopBack": require('../../components/top-back.vue'),
            // 滚动加载
            "cScrollLoad": require('../../components/scroll-load.vue'),
        }
    }
</script>

