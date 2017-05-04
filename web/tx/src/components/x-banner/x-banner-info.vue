<template>
  <div class="distance">
		<div class="banner-info bg-height" :class="{'bg-width':isWidth}" ref='info' :style="bgStyle">
			<div class="distance"><img class="bi-img" :src="logo" @click='toTeacherHome'/> </div>
			<div><div class="bi-title txt-olf" v-if="title">{{title}}</div> </div>
			<div class="bi-inner"><slot name="inner"></slot></div>
			<div class="weui_media_hd dispnone" v-if="uploadType">
				<input type="file" value="" @change='upload' ref='fileipt'/>
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
		},
		height:String     // 背景层的高度
	},
	mounted () {
    this.$nextTick(() => {
    	if(!this.bgcolor){
				var that = this;
				utils.imageIsWidth(this.banner,3,function(flag){
					that.isWidth = flag;
				});
			}
    })
  },
  created () {
  },
  data () {
    return {
      isWidth:false
    }
  },
  computed: {
  	bgStyle () {
			let obj = {};
			if(this.bgcolor){
				obj = {
					background:this.bgcolor,
					height:this.height?this.height:document.body.clientWidth/3 + 'px',
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
  },
  methods: {
		upload:function(){
			let that = this;
			if(!that.isuploading) {
				that.$vux.toast.show({
					text: '加载中'
				});
				//  将文件转成base64
				utils.imgToBase64(this.$refs.canvas,this.$refs.fileipt.files[0],function(base64){
					that.$emit('upload',base64);
				});
			}

		},
		toTeacherHome(){
			this.$emit("toTeacherHome");
		}
  },
  components: {
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/banner-info.less';
</style>
