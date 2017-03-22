<template>
  <r-button :type="btnTypeShow" :text="text" @click.native="send" code="code" :disabled="btnTypeShow == 'default'"></r-button>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props: ['type','validate'],
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
      text:'发送验证码',
			sendDelayTime:60,
			btnType:this.type?this.type:'primary',
			btnTypeShow:this.type?this.type:'primary'
    }
  },
  computed: {
  },
  watch: {
  	
  },
  methods: {
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
  components: {
		"rButton": require('../button/button.vue'),
	}
}
</script>
