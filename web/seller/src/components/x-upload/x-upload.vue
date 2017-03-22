<template>
  <r-group id='upload'>
		<a class="weui_media_box weui_media_appmsg" href="javascript:;">
			<div class="weui_media_hd dispnone">
				<input  type="file" value="" @change='upload' ref='fileipt'/>
				<!-- 转成base64-->
				<canvas ref='canvas' style="display:none;"></canvas>
				<img class="weui_media_appmsg_thumb" alt="" v-bind:src="imglink">
			</div>
			<div v-if='arrow' class="weui_cell_ft with_arrow">
				<span class="headsculpture">{{headcapture?headcapture:'更改头像'}}</span>
			</div>
		</a>
		<slot></slot>
	</r-group>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
  props: [
		'arrow',
		'imglink',
		'headcapture',
		'isuploading',
	],
	props:{
		arrow:{
			type:Boolean,
			default:false
		},
		imglink:String,
		headcapture:{
			type:String,
			default:'更改头像'
		},
		iduploading:{
			type:Boolean,
			default:false
		}
	},
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
      
    }
  },
  computed: {
  },
  watch: {
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
				utils.imgToBase64(this.$refs.canvas,this.$refs.fileipt.files[0],function(base64){
					that.$emit('upload',base64);
					
				});
			}

		}
  },
  components: {
	  "rGroup": require('../group/group.vue')
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_panel/weui_panel';
@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box';
@import '../../assets/styles/components/rui/panelImg.less';
</style>
