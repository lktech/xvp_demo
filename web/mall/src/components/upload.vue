<!-- 图文列表子组件 -->
<template>
	<c-cell-wrap>
		<a class="weui_media_box weui_media_appmsg" href="javascript:;">
			<div class="weui_media_hd dispnone">
				<input v-if='!noUpload' type="file" value="" @change='upload' v-el:fileipt/>
				<!-- 转成base64-->
				<canvas v-el:canvas style="display:none;"></canvas>
				<img class="weui_media_appmsg_thumb" alt="" v-bind:src="imglink">
			</div>
			<div v-if='arrow' class="weui_cell_ft with_arrow">
				<span class="headsculpture">{{headcapture?headcapture:'更改头像'}}</span>
			</div>
		</a>
		<slot></slot>
	</c-cell-wrap>
	
</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		replace: true,
		props: [
			'arrow',
			'imglink',
			'headcapture',
			'isuploading',
			'noUpload'
		],
		data() {
			return {}
		},
		ready() {
		},
		methods: {
			goUrl: function() {
				utils.go(this.link, this.$router);
			},
			upload:function(){
				let that = this;
				if(!that.isuploading) {
					that.$vux.toast.show({
						text: '加载中'
					});
					//  将文件转成base64
					utils.imgToBase64(this.$els.canvas,this.$els.fileipt.files[0],function(base64){
						that.$emit('upload',base64);
					});
				}
	
			}
		},
		components: {
			"cCellWrap": require('../components/cell-wrap.vue')

		}

	}
</script>
<style lang="less" scoped>
	@import '../assets/styles/weui/widget/weui_panel/weui_panel';
	@import '../assets/styles/weui/widget/weui_media_box/weui_media_box';
	@import '../assets/styles/components/panelImg.less';
	.with_arrow .headsculpture{
		padding-right: 0;
		line-height:60px;
	}
	.dispnone input {
		
			position: absolute;
			display: block;
			width: 100%;
			height: 100%;
			opacity: 0;
			filter: "alpha(opacity=0)";
			filter: alpha(opacity=0);
			-moz-opacity: 0;
			left: -5px;
			top: -2px;
			cursor: pointer;
			z-index: 2;
		
	}
	
.weui_media_appmsg .with_arrow {
		position: absolute;
		right: 0;
		padding-right: 10px;
	}

	   
</style>