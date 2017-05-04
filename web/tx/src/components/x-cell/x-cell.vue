<template>
	<div class="">
		<div class="bg-white cell-box" @click="onClick" style="padding: 20px;height: 80px;line-height: 20px;">
			<div class="" :class="{'vux-tap-active': isLink || !!link}">
			    <div class="weui_cell_hd">
			      <slot name="icon"></slot>
			    </div>
			    <div class="weui_cell_bd" :class="{'weui_cell_primary':primary==='title'}">
			    	<div class="prev">
			        {{titlePre}}
			        <slot name="before-title"></slot>
			      </div>
			      <div><!--//bob 将p标签换成div标签-->
			        {{title}}
			        <slot name="after-title"></slot>
			      </div>
			      <div class="inlineDesc">{{inlineDesc}}</div>
			    </div>
			    <div class="weui_cell_ft" :class="{'weui_cell_primary':primary==='content', 'with_arrow': isLink || !!link,'is-reverse':shows}">
			      {{value}}
			      <slot name="value"></slot>
			      <slot></slot>
			    </div>
			    <slot name="child"></slot>
			</div>
		</div>
	</div>
	
  
</template>

<script>
import InlineDesc from '../inline-desc/inline-desc.vue'
import utils from '../../libs/utils.js'

export default {
  components: {
    InlineDesc
  },
  props: {
    title: String,
    titlePre: String,
    value: [String, Number, Array],
    isLink: Boolean,
    inlineDesc: [String, Number],
    primary: {
      type: String,
      default: 'title'
    },
    link: {
      type: [String, Object]
    },
    show:Boolean,
    titlePreStatus:String
  },
  data () {
    return {
    	shows:this.show
    }
  },
  methods: {
    onClick (index) {
    	if(this.link){
    		utils.go(this.link, this.$router)
    	}else{
    		this.$emit('itemBtn');
    		this.shows = !this.shows
    	}
      
    }
  }
}
</script>

<style lang="less">
@import '../../assets/styles/common/var.less';
@import '../../assets/styles/components/variable.less';
@import '../../assets/styles/components/tap.less';
@import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';
.cell-box{
	padding: 20px;height: 80px;line-height: 20px;
	
	.weui_cell_ft.with_arrow {
	    position: absolute;
	    right: 0;
	    margin-top: -28px;
	    margin-right: 20px;
	}
	.weui_cell_ft.with_arrow:after {
	  content: " ";
	  display: inline-block;
	  transform: rotate(45deg) translateY(-50%);
	  height: @arrow-height;
	  width: @arrow-height;
	  border-width: 2px 2px 0 0;
	  border-color: #C8C8CD;
	  border-style: solid;
	}
	.weui_cell_ft.is-reverse:after{
        -webkit-transform: rotate(134deg);
        transform: rotate(134deg);
        top: 0;
        right: 10px;
        
    }
	.prev{
		font-size: 13px;
		color: #9b9b9b;
	}
	.inlineDesc{
		font-size: 15px;
	    color: #333;
	}
}
</style>
