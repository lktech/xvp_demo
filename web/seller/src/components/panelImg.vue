<!-- 列表标题子组件 -->
<template>
	<div class="weui_panel weui_panel_access">
		<div class="weui_panel_hd listname-color" v-if='listname'>
			<a>{{listname}}</a>
			<a href="javascript:;" class="amore" v-if='moreinform' @click='goUrl'>{{moreinform}}</a>
		</div>
		
		<div class="weui_panel_bd">
			<slot></slot>
		</div>	
	</div>
</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		replace: true,
		props: ['listname','moreinform','morelink'],
		data() {
			return {
			}
		},
		ready() {

		},
		methods: {
			goUrl:function(){
				utils.go(this.morelink, this.$router);
				
				
			}

		},
		

	}
</script>
<style lang="less" scoped>
@import '../assets/styles/common/var.less';
@import '../assets/styles/weui/widget/weui_panel/weui_panel';
@import '../assets/styles/weui/widget/weui_media_box/weui_media_box';
@import '../assets/styles/components/panelImg.less';
</style>