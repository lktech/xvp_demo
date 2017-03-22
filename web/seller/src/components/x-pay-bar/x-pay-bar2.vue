<template>
  <div class="bar2-wrap">
    <div class="vux-1px-t">
      <div class="total">合计：<span>￥{{price}}</span></div>
      <r-button :type="type" :text="buytext" @click.native="$emit('on-button',{text:buytext,price:price})"></r-button>
     </div>
  </div>
</template>

<script>
export default {
  props:{
  	price:{
  		type:[Number,String],
  		default:0.00
  		
  	},
  	buytext:{
  		type:String,
  		default:'立即购买'
  	},
  	type:{
  		type:String,
  		default:'primary'
  	}
  },
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
  },
  components: {
	  "rButton":require('../button/button.vue'),
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/pay-bar2.less';
</style>
