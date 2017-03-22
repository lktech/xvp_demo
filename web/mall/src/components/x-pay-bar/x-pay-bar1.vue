<template>
  <div class="bar1-wrap">
    <div class="cart vux-1px-t" @click="cartClick" >
        <div class="cartImg"><img src="../../assets/images/cart.png"></div>
	    <div>{{carttext?carttext:'购物车'}}</div>
    </div>
		<r-button :type="addtype?addtype:'default'" :text="addtext?addtext:'加购物车'" @click.native="$emit('on-button', 'addCart')"></r-button>
		<r-button :type="buytype?buytype:'default'" :text="buytext?buytext:'立即购买'" @click.native="$emit('on-button', 'buy')"></r-button>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
  props: ['link','buytext','type','carttext','addtext','addtype','buytype'],
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
    	
    }
  },
  computed: {
  	
  },
  watch: {
  },
  methods: {
    cartClick(){
	    if(this.link){
	        utils.go(this.link, this.$router);
	    }else{
	       this.$emit('on-button', 'cart'); 
	    } 
    }
  },
  components: {
	  "rButton":require('../button/button.vue'),
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/pay-bar1.less';
</style>
