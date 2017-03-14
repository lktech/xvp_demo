<!-- 图文列表子组件 -->
<template>
	<a class="weui_media_box weui_media_appmsg togglerobot" href="javascript:;" v-bind:class="[itemmargin?'itemmargin':'']">
		<div class="togglename">
			<div v-if='arrow' class="weui_cell_ft with_arrow">
			<span class="headsculpture" v-if='arrowcontent'>切换机器人</span>
			</div>
			<span class="headsculpture">切换机器人</span>
		</div>
	</a>

</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		replace: true,
		props: [
			'arrow',
			'arrowcontent',
			'itemmargin',
		],
		data() {
			return {}
		},
		ready() {},
		methods: {
			goUrl: function() {
				utils.go(this.link, this.$router);

			}

		},
		components: {
			"cButton": require('../components/button.vue')

		}

	}
</script>
<style lang="less" scoped>
	@import '../assets/styles/weui/widget/weui_panel/weui_panel';
	@import '../assets/styles/weui/widget/weui_media_box/weui_media_box';
	@import '../assets/styles/components/panelImg.less';
.itemmargin{
	margin-top: 10px;
}
.weui_media_appmsg .with_arrow {
		position: absolute;
		right: 0;
		padding-right: 10px;
	}
.weui_media_appmsg .togglename{
		 width: 100%;
   		 height: 28px;
   		 line-height: 28px;
}
	.togglerobot{
		background-color: white;
		
	}
	   
</style>