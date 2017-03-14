<template>
<div class="tabbar-wrap">
    <!-- banner -->
    <c-top-back :mar='true'></c-top-back>
    <div v-if="dataReady">
        <c-product :goods="goods"></c-product>
        <c-cell-wrap>
            <c-cell :title="storeInfo.name" value="进入店铺" is-link >
                <img slot="icon" v-lazy="storeInfo.logo"/>
            </c-cell>
        </c-cell-wrap>

        <c-title title="商品详情"></c-title>
        <div class="wrap-detail">
            <p>{{goods.productDesc}}</p>
            <p v-for='i in picsDetail'><img src="{{i.url}}" alt="" style="width:100%; height:auto;"></p>
        </div>

        <c-pay-bar1 v-if="hasCart"></c-pay-bar1>
        <c-button-txt :text="noCartBtn" size="block" type="org" fixed v-else :disabled="!buyFlag"></c-button-txt>
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
                submitType:"buy",
                hasCart:false,//是否显示购物车
                noCartBtn:"立即购买",
                buyFlag:true,  //是否可购买
                picsDetail:[]
            }
        },
        ready(){
            
            let that = this;
            utils.ajax({
                url: basepath + "/mall/product/detail",
                data:JSON.stringify({"shelf_product_id":that.$route.params.id}),
                success: function(data){
                    if(data.success){
                        that.goods = data.obj.product;
                        that.storeInfo = data.obj.storeInfo;
                        that.content = data.obj.content;
                        console.log(that.storeInfo);
                        that.goods_id=data.obj.sku.goods.id;
                        that.picsDetail=JSON.parse(that.goods.picsDetail);
                        that.skuList = data.obj.sku.sku;
                        if(data.obj.limit.rule_flag == 0){
                            that.buyFlag = false;
                            that.hasCart = false;
                            that.noCartBtn = "限制购买";
                        }else if(data.obj.product.stock == 0){
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
                    }
                }
            });
        },
        methods:{
            
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

