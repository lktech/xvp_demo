<template>
	<div class="weui_msg weui_msg weui_msg_rui">
        <div class="weui_icon_area"><img :src="src" v-if="src"/><i class="weui_icon_msg" :class="{'weui_icon_success':status==1?true:false,'weui_icon_warn':status==0?true:false,'weui_icon_info':status==2?true:false}"></i></div>
        <div class="weui_text_area">
            <div class="weui_msg_title" :style="{color: msgColor}">{{msg}}</div>
            <div class="weui_msg_desc">{{desc}}</div>
            <div class="money" v-if="money">￥{{money}}</div>
            <div class="money" v-if="score">{{score}}积分</div>
            <div class="label9-small" v-if="fivetext">{{fivetext}}</div>
        </div>
        <slot name="list"></slot>
        <div class="weui_opr_area">
            <p class="weui_btn_area">
                <slot name="btn"></slot>
            </p>
        </div>
    </div>
</template>

<script>
export default {
  props: ['status','msg','money','desc','score','fivetext','msgColor',"src"],
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
  	
  },
  components: {
  	
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/x-messages.less';
 /*@import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';
@import '../../assets/styles/components/weui/icon/weui_icon_font.less';*/
@import '../../assets/styles/components/weui/widget/weui_page/weui_msg.less';
</style>
