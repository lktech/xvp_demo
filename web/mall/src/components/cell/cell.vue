<template>
  <div class="weui_cell" :class="{'vux-tap-active': isLink || !!link}" @click="onClick">
    <div class="weui_cell_hd">
      <slot name="icon"></slot>
    </div>
    <div class="weui_cell_bd" :class="{'weui_cell_primary':primary==='title'}">
      <div><!--//bob 将p标签换成div标签-->
        {{title}}
        <slot name="after-title"></slot>
      </div>
      <inline-desc>{{inlineDesc}}</inline-desc>
    </div>
    <div class="weui_cell_ft" :class="{'weui_cell_primary':primary==='content', 'with_arrow': isLink || !!link}">
      {{value}}
      <slot name="value"></slot>
      <slot></slot>
    </div>
    <slot name="child"></slot>
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
    value: [String, Number, Array],
    isLink: Boolean,
    inlineDesc: [String, Number],
    primary: {
      type: String,
      default: 'title'
    },
    link: {
      type: [String, Object]
    }
  },
  methods: {
    onClick () {
      utils.go(this.link, this.$router)
    }
  }
}
</script>

<style lang="less">
@import '../../assets/styles/common/var.less';
@import '../../assets/styles/components/variable.less';
@import '../../assets/styles/components/tap.less';
@import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';

.weui_cell_bd > p {
  color: @cell-body-label-color;
}

.weui_cell_ft.with_arrow {
  margin-right: 15px;
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
  position: absolute;
  top: 50%;
  right: 15px;
}
</style>
