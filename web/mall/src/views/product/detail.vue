<template>
<div class="tabbar-wrap">
    <!-- banner -->
    <c-top-back :mar='true'></c-top-back>
    <c-messages v-if="product_no" :status="2" msg="商品不存在或已删除">
        <div slot="btn">
          <c-button type="primary" text="返回首页" @click.native="btnClick"></c-button>
        </div>
    </c-messages>
    <div v-else>
        <div v-if="dataReady">
            <c-product-wrap type="normal">
              <c-product :title="goods.name" :des="goods.logistics_fee | formatPriceCNY" :price="goods.price" :pic="goods.pics"></c-product>
            </c-product-wrap>
            <c-cell-wrap>
                <c-cell :title="storeInfo.name" value="进入店铺" is-link @click.native="link_home">
                    <img class="wq-img" slot="icon" src='http://static.taggole.com/sithbrobot/poster/1489997146660.jpg'/>
                </c-cell>
            </c-cell-wrap>

            <c-title title="商品详情"></c-title>
            <div class="wrap-detail">
                <p>{{goods.product_detail}}</p>
                <p v-for='i in goods.product_desc'><img v-bind:src="i" alt="" style="width:100%; height:auto;"></p>
            </div>

            <c-sku v-model="sku.status" :title="goods.name" :img="goods.pics" :price="goods.price" :text="sku.buttonTxt" :sku='skuList' @submit="submit"></c-sku>
            <c-button text="立即购买" size="block" @click.native="barClick" type="primary" style="position:fixed; bottom:0"></c-button>
        </div>
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
                skuList1:[]
                storeInfo:{name:'测试'},//店铺信息
                content:{},//商品详细信息
                goods_id:0,//商品id
                sku:{
                    status:false,
                    url:basepath + "/mall/store/shelf_product_sku_get",
                    buttonTxt:"下一步",
                },
                submitType:"buy",
                hasCart:true,//是否显示购物车
                limitNum:'',
                rule_flag:'',
                pageId:'',
                invoiceTypes:[],
                logistic_flg:1,
                product_no:false
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                //utils.MenuShare();    
                let that = this;
                utils.ajax({
                    url: basepath + "/user/product/get",
                    async:false,
                    data:{"id":that.$route.query.product_id},
                    success: function(data){
                        if(data.code=='SUCESS'){
                            that.goods=data.result;
                            that.goods.logistics_fee=that.goods.logistics_fee;
                            that.goods.product_desc=JSON.parse(that.goods.product_desc);
                            utils.ajax({
                                url: basepath + "/user/product/sku/get",
                                async:false,
                                data:{"product_id":data.result.id},
                                success: function(res){
                                    if(res.code=='SUCESS'){
                                        var max=res.result[0].price;
                                        var min=res.result[0].price;
                                        var len=res.result.length;

                                        for(var ii=1; ii<len; ii++){
                                          if(res.result[ii].price > max){
                                            max = res.result[ii].price;
                                          }
                                        }

                                        for(var i=1; i<len; i++){
                                          if(res.result[i].price < min){
                                            min = res.result[i].price;
                                          }
                                        }
                                        if(min!=max){
                                            that.goods.price='¥'+utils.formatPrice(min)+' ~ ¥'+utils.formatPrice(max);
                                        }else{
                                            that.goods.price='¥'+utils.formatPrice(min);
                                        }
                                        that.skuList1=res.result;
                                        $.each(res.result,function(i,v){
                                            that.skuList.push({
                                                id:v.id,
                                                name:v.sku_str,
                                                price:utils.formatPrice(v.price),
                                                stock:v.stock
                                            })
                                        })
                                    }else{
                                        that.$vux.alert.show(res.message);
                                    }
                                }
                                
                            });

                            that.dataReady = true;
                        }else if(data.code=='1111'){
                            that.product_no=true;
                        }else{
                            that.$vux.alert.show(data.message);
                        }
                    }
                    
                    
                });

                utils.ajax({
                  url: "/mall/wxconfig/get",
                  data:{'base_url':window.location.href.split('#')[0]},
                  success: function(data) {
                    if(data.code=='SUCESS') {
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
                            link: window.location.href, // 分享链接
                            imgUrl: that.goods.pic // 分享图标
                        });

                        wx.onMenuShareTimeline({
                            title: that.goods.title, // 分享标题
                            link: window.location.href, // 分享链接
                            imgUrl: that.goods.pic // 分享图标
                        });
                      })
                    } else {
                      that.$vux.alert.show(data.message);
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
            })
        },
        methods:{
            submit(submitData){
                if(this.submitType == "buy"){
                    var buy_info_price = null;
                    $.each(this.skuList1,function(i,v){
                        if(v.id==submitData.sku.id){
                            buy_info_price = v.price;
                        }
                    })
                    
                    var buy_info = {
                        "products":[
                            {
                                "id":submitData.sku.id,
                                "name": this.goods.name,//商品名称
                                "pic": this.goods.pics,//商品图片
                                "properties": submitData.sku.name,//规格信息
                                "price": buy_info_price,//货架商品价格
                                "num": submitData.num,//购买商品的数量
                            }
                        ],
                        'logistics_fee':this.goods.logistics_fee,
                        'discount':0,
                        'logistic_flg':1
                    };
                    utils.setSession("buy_info",buy_info);
                    utils.go({path:"/order/add"},this.$router);
                }else{
                    this.sku.status = false;
                    this.$vux.toast.show({
                        text:"操作成功",
                        type:"success"
                    });
                }


            },
            barClick(type){
                this.sku.status = true;
                this.submitType = "buy";
            },
            link_home(){
                utils.go({path:'/home/home',query:{id:this.$route.query.store_id}},this.$router,true);
            },
            btnClick(){
                utils.go({path:'/home/home',query:{id:this.$route.query.store_id}},this.$router,true);
            }
            
        },
        components:{
            "cProduct": require('../../components/x-product/x-product.vue'),
            "cProductWrap": require('../../components/x-product/x-product-wrap.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cCellWrap":require('../../components/cell/cell-wrap.vue'),
            "cCell":require('../../components/cell/cell.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cTitle":require('../../components/x-title/x-title.vue'),
            "cPayBar1":require('../../components/x-pay-bar/x-pay-bar1.vue'),
            "cPayBar1":require('../../components/x-pay-bar/x-pay-bar2.vue'),
            "cSku":require('../../components/x-sku/x-sku.vue'),
            "cMessages": require('../../components/x-messages/x-messages.vue'),
        }
    }
</script>
<style>
    .tabbar-wrap .wq-img{
        width:20px; margin-right:10px;
    }
    .tabbar-wrap .weui_cell_ft.with_arrow {
        margin-right:0 !important;
    }
    .tabbar-wrap .weui_cell_ft.with_arrow:after{
        right:0 !important;
    }
</style>
