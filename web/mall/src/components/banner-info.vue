<!-- 子组件   -->
<template>
	<div class="distance">
		<div class="banner-info bg-height" :class="{'bg-width':isWidth}" v-el:info :style="bgStyle">
			<div><img class="bi-img" :src="logo"/> </div>
			<div><div class="bi-title txt-olf" v-if="title">{{title}}</div> </div>
			<div class="bi-inner"><slot name="inner"></slot></div>
		</div>
		<div><slot name="footer"></slot></div>
	</div>

</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		props: ['logo','title','banner','bgcolor'],
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
		}

	}
</script>
<style lang="less">
	@import "../assets/styles/components/banner-info.less";
</style>