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
        <c-order :order-data="orderData" type="primary" :order-status="odr"  @fukuan="fukuan"  @qrsh="qrsh" @goto='look'  @look="look" @fahuo="fahuo"></c-order>
        <c-scroll-load @on-load="load" :url='url'><!-- 列表滚动加载--></c-scroll-load>
        <c-confirm v-model="show" title="确认收货"
                 @on-cancel="onCancel"
                 @on-confirm="onConfirm">
          <p style="text-align:center;">确认收货后，订单交易完成，钱款将立即到达商家账户</p>
        </c-confirm>
        <c-tabbar style='height:58px;'>
        	<c-tabbar-item  @click.native="next">
				<img slot="icon" src="../../assets/images/home1.png">
				<span slot="label">首页</span>
			</c-tabbar-item>
			<c-tabbar-item selected>
				<img slot="icon" src="../../assets/images/my1.png">
				<span slot="label">我的</span>
			</c-tabbar-item>
            <!--<c-tabbar-item  @click.native="next">
              <img slot="icon"  src="http://static.taggole.com/sithbrobot/poster/1490951254855.jpg">
              <span slot="label">首页</span>
            </c-tabbar-item>
            <c-tabbar-item selected >
              <img slot="icon" src="http://static.taggole.com/sithbrobot/poster/1490951277761.jpg">
              <span slot="label">我的</span>
            </c-tabbar-item>-->
          </c-tabbar>
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
                status:'',
                order_id:-1,
                show:false
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
            //所有订单列表
                this.orderList();
                utils.MenuShare();
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
                utils.ajax({
                    url: "/user/order/payurl", data: {order_id:id}, success: function (res) {
                        if (res.code=="SUCCESS") {
                            location.href=res.result.url;
                        }else{
                            that.$vux.alert.show(res.message);
                        }
                    }
                });
            },
            qrsh(id){
                this.order_id=id;
                this.show=true;
            },
            //订单列表
            orderList(id){
                let that = this;
                that.status=id?id:'';
                utils.ajax({
                    url:"/user/order/query", type:'post', data: {order_status:id?id:''}, success: function (data) {
                        if (data.code=="SUCCESS") {
                            that.orderData = data.result;
                        }else if(data.code=='user_seller_error'){
                                that.$vux.alert.show({content:'访问超时',onHide :function(){
                                  utils.go({path:'/home/home',query:{id:sessionStorage.getItem('product_id')}},that.$router);
                                }});
                        }else{
                            that.orderData = [];
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
                    url: "/user/order/confirm",
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        order_id: that.order_id,
                    },
                    success: function (data) {
                        if (data.code=="SUCCESS") {
                            that.orderList(that.status);
                            that.show=false;
                        }else{
                            that.$vux.alert.show(data.message);
                        }
                    }
                });
            },
            next(){
                utils.go({path:'/home/home',query:{id:sessionStorage.getItem('product_id')}},this.$router);
            }

        },

        components: {
            "cTab": require('../../components/tab/tab.vue'),
            "cTabItem": require('../../components/tab/tab-item.vue'),
            "cOrder": require('../../components/x-order/x-order.vue'),
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            // 滚动加载
            "cScrollLoad": require('../../components/x-scroll-load/x-scroll-load.vue'),
            "cConfirm": require('../../components/confirm/confirm.vue'),
            "cTabbar": require('../../components/tabbar/tabbar.vue'),
            "cTabbarItem": require('../../components/tabbar/tabbar-item.vue'),
        }
    }
</script>
<style>
    .wq-list button{
        width:auto !important; display:inline-block !important;
    }
    .wq-list .weui_tabbar{
      position: fixed;
    }
</style>
