<!-- 子组件   -->
<template>
  <div class="product" @click="handleClick">
    <div class="pdt-img"><img v-lazy="pic" :style="{'height':imgHeight}"/></div>
    <div class="pdt-info">
      <div class="pdt-tit">{{title}}</div>
      <div class="pdt-price txt-ofl">
         <span class="pnow">{{price}}</span>
         <span class="pold">{{oldprice}}</span>
      </div>
      <div class="pdt-postage txt-ofl">{{des}}</div>
    </div>
  </div>

</template>

<script>
  import utils from '../../libs/utils.js';
  export default {
    props: ['title','price','oldprice','pic','des','url'],
    data() {
      return {
        imgHeight:""
      }
    },

    mounted () {
      this.$nextTick(() => {
        let type = this.$parent.type,height;
        if(type == "small"){
          height = '100px';
        }else if(type == "smaller"){
          height = (document.body.offsetWidth -20)*0.485 + 'px';
        }else{
          height = document.body.offsetWidth + 'px';
        }
        this.imgHeight = height;
      });
    },
    methods: {
      handleClick(){
        if(this.url){
          utils.go(this.url,this.$router);
        }

      }
    }
    

  }
</script>
<style lang="less">
  @import "../../assets/styles/components/rui/x-product.less";
</style>