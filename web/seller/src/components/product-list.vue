<!-- 子组件   -->
<template>
  <div class="clearfix product-list" v-bind:class='"product-list-"+type' v-el:plist>
    <div class="product" v-for='item in list' @click='goUrl(item)'>
      <div class="pdt-img"><img v-lazy="item.pic" :style="{'height':imgHeight}" /></div>
      <div class="pdt-info">
        <div class="pdt-tit">{{item.title}}</div>
        <div class="pdt-price">
          <span class="pnow" v-if='item.price==0'>{{'免费领取'}}</span>
          <span class="pnow" v-else>￥{{item.price | formatPrice}}</span>
          <span class="pold" v-if='!item.hideOldPrice'>{{item.oldPrice | formatPrice}}</span>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import utils from '../libs/utils.js';
  export default {
    props: ['goods', 'type', 'url', 'params', 'ajaxtype','callback'],
    data() {
      return {
        list: this.goods ? this.goods : [],
        ajxtype: this.ajaxtype ? this.ajaxtype : "POST",
        imgHeight: ""
      }
    },
    ready() {
      let that = this;
      //获取列表
      if(!this.goods) {
        utils.ajax({
          url: that.url,
          data: that.params,
          success: function(data) {
            if(data.success) {
              that.list = data.obj;
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });

      }
      that.setImgHeight();
    },
    methods: {
      goUrl(item) {
      	if(this.callback){
      		this.$emit('handle',item);
      	}else{
      		if(item.url){
      			utils.go(item.url, this.$router);
	      	}else{
	      		utils.go({"name":"detail","params":{"id":item.id}}, this.$router);
	      		
	      	}
      	}
      	
        
      },
      setImgHeight() {
        if(this.type == "big") {
          this.imgHeight = ($(window).width() - 20) + "px";
        } else if(this.type != "small") {
          this.imgHeight = ($(window).width() - 20) * 485 / 1000 + "px";
        }
      }

    },
    watch: {
      goods(goods) {
        if(goods) {
          this.list = goods;
        }

      }
    }

  }
</script>
<style lang="less">
  @import "../assets/styles/components/product-list.less";
</style>