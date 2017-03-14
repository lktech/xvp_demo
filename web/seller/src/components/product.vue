<!-- 子组件   -->
<template>
  <div class="product product-big" v-el:plist>
    <div class="pdt-img"><img v-lazy="goods.pic" :style="{'height':imgHeight}"/></div>
    <div class="pdt-info">
      <div class="pdt-tit">{{goods.title}}</div>
      <div class="pdt-price">
         <span class="pnow">{{showprice}}</span>
        <span class="pold" v-if='goods.hideOldPrice'>{{goods.oldPrice | formatPrice}}</span>
      </div>
      <div class="pdt-postage" v-if="goods.type!='VIRTUAL'">运费：{{postage}}</div>
    </div>
  </div>

</template>

<script>
  import utils from '../libs/utils.js';
  import constants from '../libs/constants.js';
  export default {
    props: ['goods'],
    data() {
      return {
        imgHeight:"",
        showprice:"",
        postage:""
      }
    },
    ready() {
        this.imgHeight = this.$el.offsetWidth + 'px';

        this.format();
      },
    methods: {
      format(){
        if(this.goods.minPrice){//有规格
            if(this.goods.minPrice == this.goods.maxPrice){
                this.showprice = "￥" + utils.formatPrice(this.goods.minPrice);
            }else{
              this.showprice = "￥" + utils.formatPrice(this.goods.minPrice) + "~" + utils.formatPrice(this.goods.maxPrice);
            }
        }else{
          this.showprice = "￥" + utils.formatPrice(this.goods.price);
        }

        if(this.goods.postageType == constants.postageType.baoyou){
          this.postage = "包邮";
        }else if(this.goods.postageType == constants.postageType.fixed){
          this.postage = "运费 ￥" + utils.formatPrice(this.goods.postage);
        }else if(this.goods.postageType == constants.postageType.template){
          this.postage = "按地区收取费用";
        }else{
          this.postage = this.goods.postage;
        }
      }
    }
    

  }
</script>
<style lang="less">
  @import "../assets/styles/components/product-list.less";
</style>