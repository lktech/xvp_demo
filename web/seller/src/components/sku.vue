<template>
	<div class="vux-actionsheet sku">
		<div class="weui_mask_transition" :class="{'weui_fade_toggle': show}" :style="{display: show ? 'block' : 'none'}" @click="show=false"></div>
		<div class="weui_actionsheet" :class="{'weui_actionsheet_toggle': show}">
			<div class="weui_actionsheet_menu">
				<div class="weui_media_box weui_media_appmsg">
					<div class="weui_media_hd imgheight">
						<img class="weui_media_appmsg_thumb" v-lazy="'http://www.goingconcern.cn/f/d/eIVVra'">
					</div>
					<div class="weui_media_bd">
						<h4 class="weui_media_title">{{product.title}}</h4>
						<div class="weui_media_desc0"></div>
						<div class="weui_media_desc">
							<span :class="'clr-'+type">
								<span>{{fuhao}}{{showprice}}</span>
								<span>{{tip}}</span>
							</span>
						</div>
					</div>
				</div>
				<div class="weui_media_box" v-if="hasSku">
					<div class="sku-list" :style="{maxHeight:height}">
						<span class="ki" :class="{'ki-act':item.id == actId}" v-for="item in sku" track-by="$index" @click="selectSku(item.id,item.name)">{{item.name}}</span>
					</div>
				</div>
				<div class="weui_media_box">
					<span>数量</span>
					<div class="num"><c-number :number.sync='selectNum' :max="maxNum"></c-number></div>
				</div>
				<div class="weui_media_box">
					<c-button :type="disabled?'default':type" :disabled="disabled" @click="submit" size="block" :text="txtErr?txtErr:text"></c-button>
				</div>

			</div>
		</div>
	</div>
</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		props: {
			show: {
				type: Boolean,
				required: true,
				twoWay: true
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
			product:{
				type:Object,
				default(){
					return {};
				}
			},
			max:{
				type:Number,
				default(){
					return -1;
				}
			},
			url:{
				type:String,
				default:""
			},
			name:{
				type:String,
				default:"规格"
			},
			stringify:{
				type: Boolean,
				default: true
			}
		},
		data(){
			return {
				actId:-1,
				actName:"",
				actPrice:"",
				txtErr:"",
				tip:"",
				initprice:"",
				showprice:"",
				stock:1000,
				selectNum:1,
				fuhao:"￥"
			}
		},
		ready(){
			this.showprice = this.initprice;
			this.actPrice = this.initprice;
		},
		methods: {
			submit(){
				if(this.disabled){
					let errMsg = "请选择" + this.name;

					if(this.txtErr != ""){
						errMsg = this.text;
					}

					this.$vux.toast.show({
						text: errMsg,
						type:'text'
					});

				}else{
					this.$emit("submit",{"id":this.product.id,"num":this.selectNum,"sku":{"id":this.actId,"name":this.actName,"price":this.actPrice}});
				}

			},
			selectSku(id,name){
				let that = this;
				if(this.actId != id){
					//获取当前sku信息
					utils.ajax({
						url: this.url,
						data:{
							id:id
						},
						stringify:that.stringify,
						success: function(data){
							utils.loadingHide();
							if(data.success){
								if(data.obj.stock){
									that.txtErr = "";
									that.actId = id;
									that.actName = name;
									that.showprice = utils.formatPrice(data.obj.price);
									that.actPrice = data.obj.price;
									that.stock = data.obj.stock;
									that.selectNum = 1;
								}else{
									that.actId = -1;
									that.actName = '';
									that.txtErr = '已售罄';
									that.showprice = utils.formatPrice(that.initprice);
								}
							}else{
								that.$vux.toast.show({
									text:data.msg,
									type:'text'
								});
							}
						}
					});
				}
			}

		},
		computed: {
			height(){
				return $(window).height()*8/10 -180 + "px";
			},
			disabled(){
				if(this.hasSku && this.actId == -1){
					return true;
				}else{
					return false;
				}

			},
			initprice(){
				let sprice = "";
				if(this.product.minPrice && this.product.maxPrice){
					if(this.product.minPrice == this.product.maxPrice){
						sprice = utils.formatPrice(this.product.minPrice);
					}else{
						sprice = utils.formatPrice(this.product.minPrice) + "~" + utils.formatPrice(this.product.maxPrice);
					}

				}else{
					sprice = utils.formatPrice(this.product.price);
				}
				return sprice;
			},
			hasSku(){
				return this.sku && this.sku.length > 0;
			},
			maxNum(){
				if(this.max == -1){
					return this.stock;
				}else{
					return this.stock>this.max?this.max:this.stock;
				}

			}
		},
		components:{
			"cButton":require('./button.vue'),
			"cNumber": require('./number.vue')
		}
	}
</script>

<style lang="less">
	@import '../assets/styles/weui/widget/weui_tips/weui_mask.less';
	@import '../assets/styles/weui/widget/weui_tips/weui_actionsheet.less';
	@import '../assets/styles/weui/widget/weui_media_box/weui_media_box.less';
	@import "../assets/styles/components/sku.less";

</style>
