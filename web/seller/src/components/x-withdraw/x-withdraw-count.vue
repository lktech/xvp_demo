<template>
	<div class="myInfo">
        <div class="title">{{title?title:'可提现金额'}}</div>
        <div class="money">{{money|formatPrice}}<span class="weui_cell_ft with_arrow go" @click="btnClick"></span></div>
        <div class="tj">
            <div class="left">
                {{subTitleL}}<br />
                <b>{{subMoneyL|formatPrice}}</b>
            </div>
            <div class="right">
                {{subTitleR}}<br />
                <b>{{subMoneyR|formatPrice}}</b>
            </div>
        </div>
    </div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
  	props:{
  		title:String,
  		money:{
  			type:Number,
  			default:0
  		},
  		subTitleL:String,
  		subMoneyL:{
  			type:Number,
  			default:0
  		},
  		subTitleR:String,
  		subMoneyR:{
  			type:Number,
  			default:0
  		},
  		link:String
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
	  	btnClick:function () {
	        utils.go(this.link,this.$router);
	    }
	},
  	components: {
  	
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/x-withdraw-count.less';
@import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';

</style>
