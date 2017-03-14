<!-- 子组件   -->
<template>
	<c-button :type="btnTypeShow" :text="text" @click="send" code="code" :disabled="btnTypeShow == 'default'"></c-button>
</template>

<script>
	export default {
		props: ['type','validate'],
		data(){
			return {
				text:'发送验证码',
				sendDelayTime:60,
				btnType:this.type?this.type:'primary',
				btnTypeShow:this.type?this.type:'primary'
			}
		},
		ready(){
		},
		methods:{
			send(){
				let that = this;
				if(that.sendDelayTime == 60 && that.validate){
					that.text = "已发送"+that.sendDelayTime+"s";
					that.btnTypeShow = "default";
					that.$emit("on-send");
					var itv = setInterval(function(){
						that.sendDelayTime --;
						if(that.sendDelayTime > 0) {
							that.text = "已发送"+that.sendDelayTime+"s";
							that.btnTypeShow = "default";
						} else {
							clearInterval(itv);
							that.sendDelayTime = 60;
							that.text = "重新发送";
							that.btnTypeShow = that.btnType;
						}
					}, 1000);
				}
			}
		},
		components:{
			"cButton":require('../components/button.vue')
		}

	}
</script>