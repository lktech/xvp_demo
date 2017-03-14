<template>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">
      <label class="weui_label" :style="labelStyle" v-html="title"></label>
      <span class="vux-label-desc" v-if="inlineDesc">{{inlineDesc}}</span>
    </div>
    <div class="weui_cell_ft">
      <input class="weui_switch" type="checkbox" :disabled="disabled" v-model="value"/>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    labelStyle () {
      let isHTML = /<\/?[^>]*>/.test(this.title)
      let width = Math.min(isHTML ? 5 : (this.title.length + 1), 14) + 'em'
      return {
        width
      }
    }
  },
  props: {
    title: {
      type: String,
      required: true
    },
    disabled: Boolean,
    value: {
      type: Boolean,
      default: false
    },
    inlineDesc: String
  },
  watch: {
    value (newVal) {
      this.$emit('on-change', newVal)
    }
  }
}
</script>

<style lang="less" scoped>
@import '../assets/styles/weui/widget/weui_cell/weui_form/weui_form_common.less';
@import '../assets/styles/weui/widget/weui_cell/weui_switch.less';

.weui_cell_switch .weui_cell_ft {
  font-size: 0;
}
</style>
