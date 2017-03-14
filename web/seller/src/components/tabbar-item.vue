<template>
  <a href="javascript:;" class="weui_tabbar_item" :class="{'weui_bar_item_on': $parent.index === index}" @click="onItemClick">
    <div class="weui_tabbar_icon" :class="[iconClass, {'vux-reddot': showDot},act]">
      <slot name="icon"></slot>
      <sup><badge v-if="badge" :text="badge"></badge></sup>
    </div>
    <p class="weui_tabbar_label">
      <slot name="label"></slot>
    </p>
  </a>
</template>

<script>
import { childMixin } from './mixins/multi-items'
import utils from '../libs/utils.js';
import Badge from './badge.vue'

export default {
	data(){
		return{
			act:this.selected?this.iconClass+'-act':''
		}
	},
  components: {
    Badge
  },
  mixins: [childMixin],
  props: {
    showDot: {
      type: Boolean,
      default: false
    },
    badge: String,
    link: [String, Object],
    iconClass: String
  },
  events: {
    'on-item-click': function () {
      utils.go(this.link, this.$router)
    }
  }
}
</script>
<style>
	.weui_tabbar_icon {
    background-size: auto 100%;
}

</style>
