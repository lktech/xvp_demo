<template>
<div class="tabbar-wrap">
    <!-- banner -->
    <c-top-back :mar='true'></c-top-back>
    <div v-if="dataReady">
        <c-product :goods="goods"></c-product>
        <c-cell-wrap>
            <c-cell :title="storeInfo.name" value="进入店铺" is-link @click="link_home">
                <img slot="icon" v-lazy="storeInfo.logo"/>
            </c-cell>
        </c-cell-wrap>

        <c-title title="商品详情"></c-title>
        <div class="wrap-detail">
            <p>{{goods.productDesc}}</p>
            <p v-for='i in picsDetail'><img v-bind:src="i.url" alt="" style="width:100%; height:auto;"></p>
        </div>

        <c-sku :show.sync="sku.status" :limit="rule_flag" :text="sku.buttonTxt" /*:max="limitNum"*/ :sku='skuList' :goodsId="goods_id" :product='goods' :url="sku.url" @submit="submit"></c-sku>
        <c-pay-bar1 @on-button="barClick" :link="{name:'cart'}" v-if="hasCart"></c-pay-bar1>
        <c-button-txt :text="noCartBtn" size="block" @btnclick="barClick('buy')" type="org" fixed v-else :disabled="!buyFlag"></c-button-txt>
    </div>

</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                dataReady:false,
                goods:{},//商品信息
                skuList:[],//商品sku列表
                storeInfo:{},//店铺信息
                content:{},//商品详细信息
                goods_id:0,//商品id
                sku:{
                    status:false,
                    url:basepath + "/mall/store/shelf_product_sku_get",
                    buttonTxt:"下一步",
                },
                submitType:"buy",
                hasCart:true,//是否显示购物车
                noCartBtn:"立即购买",
                buyFlag:true,  //是否可购买
                limitNum:'',
                picsDetail:[],
                rule_flag:'',
                pageId:'',
                invoiceTypes:[]
            }
        },
        ready(){
            utils.MenuShare();    
            let that = this;
            utils.ajax({
                url: basepath + "/mall/product/detail",
                async:false,
                data:JSON.stringify({"shelf_product_id":that.$route.params.id}),
                success: function(data){
                    if(data.success){
                        //that.limitNum=data.obj.limit.limitNum;
                        that.goods = data.obj.product;
                        that.storeInfo = data.obj.storeInfo;
                        that.content = data.obj.content;
                        console.log(that.storeInfo);
                        that.goods_id=data.obj.sku.goods.id;
                        that.goods.goodsId=that.goods_id;
                        that.skuList = data.obj.sku.sku;
                        if(data.obj.limit.rule_flag){
                            if(data.obj.limit.rule_flag.indexOf("按人限购")!=-1){
                                that.rule_flag = data.obj.limit.rule_flag.replace(/按人限购/g,'每人限购');
                            }else if(data.obj.limit.rule_flag.indexOf("按天限购")!=-1){
                                that.rule_flag = data.obj.limit.rule_flag.replace(/按天限购/g,'每天限购');
                            }
                        }
                        if(that.storeInfo.invoiceFlag){
                            if(data.obj.product.type=="VIRTUAL"){
                                if(data.obj.invoiceTypes.indexOf("VAT")==-1){
                                    that.storeInfo.invoiceFlag=0;
                                }
                            }
                        }
                        that.invoiceTypes=data.obj.invoiceTypes;
                        that.picsDetail=JSON.parse(that.goods.picsDetail);
                        /*if(data.obj.limit.rule_flag == 0){
                            that.buyFlag = false;
                            that.hasCart = false;
                            that.noCartBtn = "限制购买";
                        }else */if(data.obj.product.stock == 0){
                            that.buyFlag = false;
                            that.hasCart = false;
                            that.noCartBtn = "已售罄";
                        }else{
                            if(that.goods.price === 0){
                                that.hasCart = false;
                                that.noCartBtn = "免费领取";
                            }else{
                                if(that.goods.type == constants.productType.actual){//实物商品  有购物车
                                    that.hasCart = true;
                                    that.noCartBtn = "立即购买";
                                }else if(that.goods.type == constants.productType.virtual){//虚拟商品  无购物车
                                    that.hasCart = false;
                                    that.noCartBtn = "立即购买";
                                }
                            }

                        }

                        that.dataReady = true;
                    }else{
                        that.$vux.alert.show(data.msg);
                    }
                }
                
                
            });

            utils.ajax({
              url: "/mall/wxconfig/get",
              data:JSON.stringify({'url':window.location.href}),
              success: function(data) {
                if(data.success) {
                  wx.config({
                      debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                      appId: data.obj.appId, // 必填，公众号的唯一标识
                      timestamp: data.obj.timestamp, // 必填，生成签名的时间戳
                      nonceStr: data.obj.nonceStr, // 必填，生成签名的随机串
                      signature: data.obj.signature,// 必填，签名，见附录1
                      jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                  });
                  wx.ready(function(){
                    wx.onMenuShareAppMessage({
                        title: that.goods.title, // 分享标题
                        desc: '【'+that.storeInfo.name+'】发现好商品，立即分享给你，进店有惊喜呦。', // 分享描述
                        link: window.location.href+'?xv=enter', // 分享链接
                        imgUrl: that.goods.pic // 分享图标
                    });

                    wx.onMenuShareTimeline({
                        title: that.goods.title, // 分享标题
                        link: window.location.href+'?xv=enter', // 分享链接
                        imgUrl: that.goods.pic // 分享图标
                    });
                  })
                } else {
                  that.$vux.alert.show(data.msg);
                }
              },
            });

            if(utils.getSession('pageId')){
                that.pageId=utils.getSession('pageId');
            }else{
                utils.ajax({
                    url: basepath + "/mall/store/get",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        if(data.success){
                            that.pageId=data.obj.page_id;
                        }
                    }
                });
            }
        },
        methods:{
            submit(submitData){
                if(this.submitType == "buy"){
                    var total_price = submitData.sku.price*submitData.num + this.goods.postage;
                    var buy_info = {
                        "postage":{
                            "logistic_amount":this.goods.postage,
                            "logistic_flag":this.goods.postageFlag,
                            "product_amount":total_price
                        },
                        "products":[
                            {
                                "type": this.goods.type,//商品类型
                                "name": this.goods.title,//商品名称
                                "pic": this.goods.pic,//商品图片
                                "shelf_product_id": this.goods_id,//货架商品ID
                                "shelf_product_sku_id": submitData.sku.id<0?'':submitData.sku.id,//货架商品SKU ID
                                "properties": submitData.sku.name,//规格信息
                                "buy_price": submitData.sku.price*100,//货架商品价格
                                "num": submitData.num,//购买商品的数量
                                "ticket":this.goods.invoiceFlag,
                                "invoiceFlag":this.storeInfo.invoiceFlag
                            }
                        ],
                        "invoiceTypes":this.invoiceTypes
                    };
                    console.log(submitData.sku.price);
                    utils.setSession("buy_info",buy_info);
                    utils.go({name:"odadd"},this.$router);
                }else{
                    this.sku.status = false;
                    this.$vux.toast.show({
                        text:"操作成功",
                        type:"success"
                    });
                }


            },
            barClick(type){
                if(type == "addCart"){
                    this.sku.status = true;
                    this.sku.buttonTxt = "加入购物车";
                    this.submitType = "cart";
                }else if(type == "buy"){
                    this.sku.status = true;
                    this.sku.buttonTxt = "下一步";
                    this.submitType = "buy";

                }
            },
            link_home(){
                utils.go({name:'home',params:{id:this.pageId}},this.$router,true);
            }
            
        },
        components:{
            "cProduct":require('../../components/product.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cCellWrap":require('../../components/cell-wrap.vue'),
            "cCell":require('../../components/cell.vue'),
            "cTitle":require('../../components/title.vue'),
            "cPayBar1":require('../../components/pay-bar1.vue'),
            "cButtonTxt":require('../../components/button-txt.vue'),
            "cSku":require('../../components/sku.vue')
        }
    }
</script>

