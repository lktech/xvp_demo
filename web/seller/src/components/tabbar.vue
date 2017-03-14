<template>
  <div class="weui_tabbar" v-bind:class="[positions?'nonpst':'']">
    <slot></slot>
  </div>
</template>

<script>
import { parentMixin } from './mixins/multi-items'

export default {
  mixins: [parentMixin],
  props: {
    iconClass: String,
    positions:Boolean,
  }
}
</script>

<style lang="less">
@import '../assets/styles/weui/widget/weui_tab/weui_tab_tabbar.less';
.nonpst{
	position: inherit;
}
</style>
