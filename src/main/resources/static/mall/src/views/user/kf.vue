<template>
	<div>
		<!-- banner -->
		<c-top-back></c-top-back>
		<!-- 联系客服-->
		<c-cell-wrap title="联系客服">
			<c-cell title='客服电话' value="010-5252552"></c-cell>
			<c-cell title='客服QQ' value="422166198"></c-cell>
		</c-cell-wrap>
		<div class="wrap-pd">
			<c-button @click="clk" type="org" size="block" text='返回' :disabled="disabled"></c-button>
		</div>
	</div>
</template>
<script>
	import utils from '../../libs/utils.js'
	export default {
		data() {
				return {
					
				}
			},
			ready() {
				utils.loadingHide();
			},
			methods: {
				clk (){
                this.disabled = false;
                utils.go({name:'user'},this.$router);
            	},
			},
			components: {
				"cCell": require('../../components/cell.vue'),
				"cCellWrap": require('../../components/cell-wrap.vue'),
            	"cTopBack": require('../../components/top-back.vue'),
            	"cButton":require('../../components/button.vue'),
			},
	}
</script>

