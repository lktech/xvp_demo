<template>
    <div>
        <c-top-back></c-top-back>
        <!--商品列表-->
        <div>
            <c-panel-img listname="商品列表">
                <c-panel-list v-for='item in json.products'
                              :id='item.shelf_product_id'
                              :others='item.num'
                              :link='{name:"detail",params:{id:item.shelf_product_id}}'
                              :title='item.name'
                              :details='item.properties?"规格："+item.properties:"规格：无"'
                              :from='item.buy_price/100'
                              :imglink="item.pic"
                              :colororg='true'>
                </c-panel-list>
            </c-panel-img>
        </div>
        <c-group>
            <c-cell title="优惠" :link="json.discount?{name:'pdtdetail',params:{id:json.discountInfo.id}}:''"
                    :value="json.discount?json.discountInfo.name:'暂无优惠'"></c-cell>
            <c-cell title="发票" :value="ticket.name?ticket.name:'无需发票'" v-if="isShow"
                    :link="{name:'invoice',query:{id:ticket.id,com_name:noTicketName,add:addr_id?addr_id:''}}"></c-cell>
        </c-group>
        <c-button-txt text="提交订单" :money="json.postage.product_amount" name="实付：" @btnclick="addOrder"
                      type="org"></c-button-txt>
        <c-address :show.sync="payStatus" :address="payList" default-status="1" size="block" type="default"
                   @get-addressid="payEvent"
                   @address-add="btnClick"
                   btn-txt="取消" title="安全支付" :arrow="true" :mstyle="true"></c-address>
    </div>


</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                //订单状态
                odr: {
                    dqr: constants.orderStatus.dqr,//待确认
                    dfk: constants.orderStatus.dfk,//待付款
                    dfh: constants.orderStatus.dfh,//待发货
                    dsh: constants.orderStatus.dsh,//待收货
                    ok: constants.orderStatus.ok,//完成交易
                    close: constants.orderStatus.close//订单关闭（用户关闭/订单超时）
                },
                //默认隐藏支付
                payStatus: false,
                //初始化数据
                json: {
                    postage: {product_amount: 0},
                    products: []
                },
                //可选支付列表
                payList: [],
                ticket: {id: "", name: ""},
                isShow: false,//是否显示发票
                noTicketName: "",//不支持开发票的货架商品
                totalMoney: "",//总金额
                addr_id:'',
                order_id:''
            }
        },
        ready(){
            utils.MenuShare();
            if(this.$route.query.add){
                this.addr_id=this.$route.query.add
            }
            //ticket: true,//是否开发票(商品暂缺这个字段)
            if(localStorage.getItem("order_ID")){
                localStorage.removeItem("order_ID");
                utils.go({name:'oddetail',params:{'id':localStorage.getItem("order_ID")}},this.$router,true);
            }else if (utils.getSession("buy_info")) {
                let [that,arr,id,name] = [this, [], this.$route.query.id, this.$route.query.name];
                utils.loadingHide();
                this.json = JSON.parse(utils.getSession("buy_info"));//商品数据列表

                if (id && name) {
                    this.ticket.id = id;
                    this.ticket.name = decodeURI(name);
                }
                for (let v of this.json.products) {
                    if(v.invoiceFlag){
                        if (v.ticket) {
                            this.isShow = true;//显示发票
                        } else {
                            arr.push(v.name);//不支持开发票的货架商品
                        }
                    }
                    delete v.invoiceFlag;
                    delete v.ticket;
                }
                this.noTicketName = encodeURI(arr.join());

                console.log(JSON.parse(utils.getSession("buy_info")))
            }else{
                utils.go({name:'home',params:{'id':utils.getSession('pageId')}},this.$router,true);
            }

        },
        methods: {
            //提交订单
            addOrder(){
                if (utils.getSession("buy_info")) {
                    let that = this;
                    let obj = {
                        pay_type: "ONLINE",
                        product_amount: that.json.postage.product_amount*100,//实际付款金额
                        ticket_id: that.ticket.id,//发票ID
                        address_id:that.$route.query.add?that.$route.query.add:'',
                        logistic_amount: that.json.postage.logistic_amount, //运费金额
                        logistic_flag: that.json.postage.logistic_flag,//运费标识，1：有运费，0：没有运费
                        list: that.json.products//商品列表
                    }
                    utils.ajax({
                        url: basepath + "/mall/order/add", data: JSON.stringify(obj), success: function (data) {
                            if (data.success) {
                                that.order_id=data.obj.order_id;
                                that.totalMoney = "支付金额 ￥" + that.json.postage.product_amount;
                                that.payList = [{id: 1, trueName: "微信支付", userName: that.totalMoney}];
                                that.payStatus=true;
                                /*let that = this;
                                let obj = {
                                    type:'PAY_TYPE'
                                }
                                utils.ajax({
                                    url: basepath + "/mall/dictionary/get", data: JSON.stringify(obj), success: function (data) {
                                        if (data.success) {
                                            this.payList = data.obj.list;
                                            for(var i=0; i<data.obj.list.length;i++){
                                                this.payList.userName=this.json.postage.product_amount;
                                            }
                                            this.payStatus=true
                                        }
                                    }
                                });*/
                            }else{
                                that.$vux.alert.show(data.msg);
                            }
                        }
                    });
                } else {
                    this.$vux.alert.show('请重新选择商品');
                }

            },
            //支付状态取消
            btnClick(){
                this.payStatus = false;
            },
            //开始支付
            payEvent(id, obj){
                let that=this;
                localStorage.setItem('order_ID',that.order_id);
                utils.ajax({
                    url: "/mall/user/get ", type:'get', success: function (data) {
                        if (data.success) {
                            location.href="http://m.fvt.xiaovpu.com/wap/order/index.html?userId="+data.obj.userId+"&orderId="+that.order_id+'&pageId='+utils.getSession('pageId')+"&money="+that.json.postage.product_amount*100+'&shop='+encodeURI(sessionStorage.getItem('title_'));
                        }
                    }
                });

                //this.zhifu_fn(this.order_id);
            },
            zhifu_fn(order_id){
                let that = this;
                let obj = {
                   orderId:order_id
                }
                utils.ajax({
                    url: basepath + "/mall/trade/payment_param_get", data: JSON.stringify(obj), success: function (data) {
                        if (data.success) {
                            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                                   "appId" : data.obj.payment_param.app_id,     //公众号名称，由商户传入
                                   "timeStamp": data.obj.payment_param.time_stamp,         //时间戳，自1970年以来的秒数
                                   "nonceStr" : data.obj.payment_param.nonce_str, //随机串
                                   "package" : data.obj.payment_param._package,
                                   "signType" : data.obj.payment_param.sign_type,         //微信签名方式:
                                   "paySign" : data.obj.payment_param.pay_sign //微信签名
                                },
                                function(res){
                                    if(res.err_msg.indexOf('ok')>-1){
                                        utils.go({name:'success',params:{money:that.json.postage.product_amount*100,way:'微信支付',pageId:utils.getSession('pageId')}},that.$router);
                                    }
                               }
                            );
                        }
                    }
                });
            }

        },
        components: {
            "cTopBack": require('../../components/top-back.vue'),
            "cGroup": require('../../components/group.vue'),
            "cCell": require('../../components/cell.vue'),
            "cPanelImg": require('../../components/panelImg.vue'),
            "cPanelList": require('../../components/panellist.vue'),
            "cButtonTxt": require('../../components/button-txt.vue'),
            "cAddress": require('../../components/address.vue'),
        }
    }
</script>

