<template>
	<div class="vux-actionsheet sku">
		<div class="weui_mask_transition" :class="{'weui_fade_toggle': show}" :style="{display: show ? 'block' : 'none'}" @click="show=false"></div>
		<div class="weui_actionsheet" :class="{'weui_actionsheet_toggle': show}">
			<div class="weui_actionsheet_menu">
				<div class="weui_media_box weui_media_appmsg">
					<div class="weui_media_hd imgheight">
						<img class="weui_media_appmsg_thumb" v-lazy="img">
					</div>
					<div class="weui_media_bd">
						<h4 class="weui_media_title">{{title}}</h4>
						<div class="weui_media_desc">
							<span class="show-price">{{unit}}{{skuprice?skuprice:price}}</span>
						</div>
					</div>
				</div>
				<div class="weui_media_box" v-if="hasSku>0">
					<div class="sku-list" :style="{maxHeight:height}">
						<span class="ki" :class="{'ki-act':item.id == actId}" v-for="item in sku" @click="selectSku(item)">{{item.name}}</span>
					</div>
				</div>
				<div class="weui_media_box">
					<div class="num">
						<r-number v-model="selectNUm" :fillable="fillable" :max="max1" :min="min" title="数量"></r-number>
						<p>剩余库存:{{skustock}}件</p>
					</div>
				</div>
				<div class="weui_media_box">
					<r-button type="primary" @click.native="submit" :text="text1"></r-button>
				</div>

			</div>
		</div>
	</div>
</template>

<script>
	import utils from '../../libs/utils.js';
	export default {
		data(){
			return {
				show:false,
				skuprice:null,
				actId:-1,
				actName:"",
				selectNUm:this.num,
				skustock:0,
				text1:this.text,
				disabled1:this.disabled,
				max1:this.max
			}
		},
		props: {
			value: {
				type: Boolean,
				default:false
			},
			text:{
				type:String,
				default:"下一步"
			},
			type:{
				type:String,
				default:"org"
			},
			sku:{
				type:Array,
				default(){
					return [];
				}
			},
			max:{
				type:Number
			},
			min:{
				type:Number,
				default(){
					return 1;
				}
			},
			num:{
				type:Number,
				default(){
					return 1;
				}
			},
			fillable: {
				type: Boolean,
				default:false
			},
			title:{
				type:String,
				default:""
			},
			price:{
				type:[String,Number],
				default:""
			},
			img:{
				type:String,
				default:""
			},
			unit:{
				type:String,
				default:"￥"
			},
			disabled: {
				type: Boolean,
				default:false
			}
		},
		mounted: function () {
            this.$nextTick(function () {
 


				this.actId = this.sku[0].id;
				this.skustock = this.sku[0].stock;
				this.skuprice = this.sku[0].price;
				if(this.skustock*1 <= 0){
					this.text1="已售罄";
					this.disabled1=true;
				}else{
					this.max1=this.skustock*1;
					this.text1=this.text;
					this.disabled1=false;
				}
			});
		},
		methods: {
			submit(){
				this.$emit("submit",{"num":this.selectNUm,"sku":{"id":this.actId,"name":this.actName,"price":this.skuprice,'stock':this.skustock}});
			},
			selectSku(item){
				this.actId = item.id;
				this.actName = item.name;
				this.skuprice = item.price;
				this.skustock = item.stock;
				this.selectNUm = 1;
				if(this.skustock*1 <= 0){
					this.text1="已售罄";
					this.disabled1=true;
				}else{
					this.max1=this.skustock*1;
					this.text1=this.text;
					this.disabled1=false;
				}
			},
			fixIos(zIndex) {
				if(this.$tabbar && /iphone/i.test(navigator.userAgent)) {
					this.$tabbar.style.zIndex = zIndex
				}
			}

		},

		watch: {
			show(val) {
				this.$emit('input', val)
				if(val) {
					this.fixIos(-1)
				} else {
					setTimeout(() => {
						this.fixIos(100)
					}, 200)
				}
			},
			value(val) {
				this.show = val
			}
		},
		computed: {
			height(){
				return $(window).height()*8/10 -180 + "px";
			},
			hasSku(){
				return this.sku && this.sku.length > 0;
			}
		},
		components:{
			"rButton":require('../button/button.vue'),
			"rNumber": require('../number/number.vue')
		}
	}
</script>

<style lang="less">
	@import '../../assets/styles/components/weui/widget/weui_tips/weui_mask.less';
	@import '../../assets/styles/components/weui/widget/weui_tips/weui_actionsheet.less';
	@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box.less';
	@import "../../assets/styles/components/rui/x-sku.less";

</style>
