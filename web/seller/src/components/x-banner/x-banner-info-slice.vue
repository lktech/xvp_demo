<template>
  <div class="distance">
		<div class="banner-info bg-height" :class="{'bg-width':isWidth}" ref='info' :style="bgStyle">
			<div><img class="bi-img" :src="imgurl"/> </div>
			<div><div class="bi-title txt-olf" v-if="title">{{title}}</div> </div>
			<div class="bi-inner"><slot name="inner"></slot></div>
			<div class="weui_media_hd dispnone" v-if="uploadType">
				<input id="banner_info_slice" type="file" value="" ref='fileipt'/>
				<!-- 转成base64-->
				<canvas ref='canvas' style="display:none;"></canvas>
			</div>
		</div>
		<div><slot name="footer"></slot></div>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props:{
		logo:String,
		title:String,
		banner:String,
		bgcolor:String,
		uploadType:{
			type:Boolean,
			default:true
		}
	},
	mounted: function () {
        this.$nextTick(function () {
        	var that = this;
    		if(!this.bgcolor){
				
				utils.imageIsWidth(this.banner,3,function(flag){
					that.isWidth = flag;
				});
			}
			var uploading = false;
			xvUpload.init({
				ids:['banner_info_slice'],
				type:1,
				start:function(){
					utils.loadingShow('上传中');
				},
				success:function(data){
					that.imgurl=data.url;
					utils.loadingHide();
				},
				error:function(data){
					that.$vux.alert.show('上传失败');
				}
			});
        })
    },
  created () {
  },
  data () {
    return {
      isWidth:false,
      imgurl:this.logo
    }
  },
  computed: {
  	bgStyle () {
			let obj = {};
			if(this.bgcolor){
				obj = {
					background:this.bgcolor,
					height:document.body.clientWidth/3 + 'px',
					minHeight:'120px'
				}
			}else{
				if(this.banner){
					obj = {
						backgroundImage:'url('+this.banner+')',
						height:document.body.clientWidth/3 + 'px',
						minHeight:'120px'
					}
				}else{
					obj = {
						background:'#000',
						height:document.body.clientWidth/3 + 'px',
						minHeight:'120px'
					}
				}
				
			}
			return obj;
		}
  },
  watch: {
  	imgurl(newImgurl){
  		this.$emit('upload' , newImgurl);
  	}
  },
  methods: {

  },
  components: {
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/banner-info.less';
</style>
