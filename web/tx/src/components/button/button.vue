<template>
  <button class="weui_btn" :class="classes" :disabled="disabled || (type == 'disabled2')" :type="actionType" @click="buttonClick">
    {{text}}<slot></slot>
  </button>
</template>

<script>
  import utils from '../../libs/utils.js';
export default {
  props: {
    type: {
      default: 'default'
    },
    disabled: Boolean,
    mini: Boolean,
    plain: Boolean,
    text: String,
    actionType: String,
    size: String,//bob
    noradius: Boolean,//bob
    inline: Boolean,//bob
    link: String//bob
  },
  computed: {
    classes () {
      return [
        {
          weui_btn_disabled: this.disabled,
          weui_btn_disabled2: this.type == "disabled",
          weui_btn_mini: this.mini,
          weui_btn_noradius: this.noradius,
          weui_btn_small: this.size == "small",
          weui_btn_smaller: this.size == "smaller",
          weui_btn_small_block: this.size == "small-block",
          weui_btn_inline: this.inline
        },
        `weui_btn_${this.type}`,
        this.plain ? `weui_btn_plain_${this.type}` : ''
      ]
    }
  },
  methods: {
  buttonClick(){
    if(!this.disabled){
      utils.go(this.link, this.$router);
    }

  }

}
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_button/weui_button.less';
</style>
