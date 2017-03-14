<!-- 子组件   -->
<template>
  <div class="clearfix product-list" v-bind:class='"product-list-"+type' v-el:plist>
    <div class="product" v-for='item in list' @click='goUrl(item.imglink)'>
      <div class="pdt-img"><img v-lazy="item.pic" :style="{'height':imgHeight}"/></div>
      <div class="pdt-info">
        <div class="pdt-tit">{{item.title}}</div>
        <div class="pdt-price">
          <span class="pnow" v-if='item.price==0'>{{'免费领取'}}</span>
        <span class="pnow" v-else>{{item.type==1?item.min_price:item.price|formatPrice}}</span>
        <span class="pold" v-if='item.old_price'>{{item.old_price?item.old_price:''|formatPrice}}</span>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import utils from '../libs/utils.js';
  export default {
    props: ['goods','type','url','params','ajaxtype'],
    data() {
      return {
				list:this.goods?this.goods:[],
				ajxtype:this.ajaxtype?this.ajaxtype:"POST",
				imgHeight:"auto"
      }
    },
    ready() {
        let that = this;
        //获取列表
        if(!this.goods){
        	$.ajax({
	          url: that.url,
	          dataType: 'json',
	          type: that.ajxtype,
	       		 data: that.params,
	          success: function(data) {
	            if(data.success) {
	              that.list=data.obj;
	            } else {
	              that.$vux.toast.show({
	                text: data.msg,
	                type: 'text'
	              });
	            }
	          }
	        });
        }
      },
    methods: {
      goUrl: function(url) {
				utils.go(url, this.$router);
			}

    },
    watch:{
    	list(){
    		if($(this.$els.plist).find(".pdt-img").length > 0){
    			this.imgHeight = $(this.$els.plist).find(".pdt-img").width() + "px";
    		}else{
    			this.imgHeight = "auto";
    		}
    	}
    }
    

  }
</script>
<style lang="less">
  @import "../assets/styles/components/product-list.less";
</style>