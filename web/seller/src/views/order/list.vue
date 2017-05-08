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
        <c-order :order-data="orderData" type="primary" :order-status="odr"  @look="look" @goto="look" @fahuo="fahuo"></c-order>
       <!-- <c-scroll-load @on-load="load" :url='url'>列表滚动加载</c-scroll-load>-->
       <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
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
                order_item_id_list:{}
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
                utils.MenuShare();
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
               utils.setSession("order_item_id_list",this.order_item_id_list[id]); 
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
                        if (data.code=="SUCCESS") {
                            that.orderData = data.result;
                            $.each(that.orderData,function(k,o){
                                var arr=[];
                                $.each(o.goods,function(i,v){
                                    arr.push(v.id);
                                })
                                that.order_item_id_list[o.orderNum]=arr;
                            })
                        }else if(data.code=='auth_seller_error'){
                                utils.wang(that,utils,data.message);

                        }else{
                            that.orderData = [];
                            that.$vux.alert.show({content:data.message,onHide :function(){
                                                            return false
                                                          }});
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
