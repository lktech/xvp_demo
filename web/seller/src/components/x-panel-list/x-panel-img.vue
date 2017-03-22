<template>
  <div class="weui_panel weui_panel_access">
		<div class="weui_panel_hd weui_panel_hd1" v-if='listname||moreinform'>
			<span 
				class="float-left"
				:class="typeLeft ? 'clr-' + typeLeft : ''" v-html="listname"></span>
			<span class="float-right" 
				:class="typeRight ? 'clr-' + typeRight : ''"  @click='goUrl'>{{moreinform}}</span>
		</div>
		<div class="weui_panel_bd">
			<slot></slot>
		</div>	
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
  props: ['listname','moreinform','typeLeft','typeRight','morelink'],
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
  	goUrl:function(){
			utils.go(this.morelink, this.$router);
		}
  }
}
</script>

<style lang="less">
@import '../../assets/styles/common/var.less';
@import '../../assets/styles/components/rui/panelImg.less';
@import '../../assets/styles/components/weui/widget/weui_panel/weui_panel';
@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box';

</style>
