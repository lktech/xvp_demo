<!-- 子组件   -->
<template>
	<div class="distance">
		<div class="banner-info bg-height" :class="{'bg-width':isWidth}" v-el:info :style="bgStyle">
			<div><img class="bi-img" :src="logo"/> </div>
			<div><div class="bi-title txt-olf" v-if="title">{{title}}</div> </div>
			<div class="bi-inner"><slot name="inner"></slot></div>
			<div class="weui_media_hd dispnone" v-if="uploadType">
				<input type="file" value="" @change='upload' v-el:fileipt/>
				<!-- 转成base64-->
				<canvas v-el:canvas style="display:none;"></canvas>
			</div>
		</div>
		<div><slot name="footer"></slot></div>
	</div>

</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		props: ['logo','title','banner','bgcolor','uploadType'],
		data(){
			return {
				isWidth:false
			}
		},
		ready(){
			if(!this.bgcolor){
				var that = this;
				utils.imageIsWidth(this.banner,3,function(flag){
					that.isWidth = flag;
				});
			}

		},
		computed:{
			bgStyle () {
				let obj = {};
				if(this.bgcolor){
					obj = {
						background:this.bgcolor,
						height:document.body.clientWidth/3 + 'px',
						minHeight:'120px'
					}
				}else{
					obj = {
						backgroundImage:'url('+this.banner+')',
						height:document.body.clientWidth/3 + 'px',
						minHeight:'120px'
					}
				}
				return obj;
			}
		},
		methods: {
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
		}

	}
</script>
<style lang="less">
	@import "../assets/styles/components/banner-info.less";
	.dispnone input {
		
			position: absolute;
			display: block;
			width: 80px;
			height: 80px;
			opacity: 0;
			filter: "alpha(opacity=0)";
			filter: alpha(opacity=0);
			-moz-opacity: 0;
			left: 50%;
			top: -110px;
			margin-left:-40px;
			cursor: pointer;
			z-index: 2;
		
	}
	.dispnone{
		position:relative;
	}
</style>