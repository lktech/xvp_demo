<template>
  <div>
    <dialog class="weui_dialog_confirm"
    :show="show"
    :mask-transition="maskTransition"
    :dialog-transition="dialogTransition"
    @on-hide="$emit('on-hide')"
    @on-show="$emit('on-show')">
      <div class="weui_dialog_hd"><strong class="weui_dialog_title">{{title}}</strong></div>
      <div class="weui_dialog_bd"><slot></slot></div>
      <div class="weui_dialog_ft">
        <a href="javascript:;" class="weui_btn_dialog default" @click="onCancel">{{cancelText}}</a>
        <a href="javascript:;" class="weui_btn_dialog primary" @click="onConfirm">{{confirmText}}</a>
      </div>
    </dialog>
  </div>
</template>

<script>
import Dialog from './dialog.vue'

export default {
  components: {
    Dialog
  },
  props: {
    show: {
      type: Boolean,
      default: false,
      twoWay: true
    },
    title: {
      type: String,
      default: '提示',
      required: false
    },
    confirmText: {
      type: String,
      default: '确定'
    },
    cancelText: {
      type: String,
      default: '取消'
    },
    maskTransition: {
      type: String,
      default: 'vux-fade'
    },
    dialogTransition: {
      type: String,
      default: 'vux-dialog'
    }
  },
  data(){
    return {
      confirm:false,
      cancel:false
  }
  },
  methods: {
    onConfirm () {
      this.show = false
      this.confirm = true
      this.$emit('on-confirm')
    },
    onCancel () {
      this.show = false
      this.cancel = true
      this.$emit('on-cancel')
    }
  }
}
</script>

<style lang="less">
@import '../assets/styles/transition.less';
@import '../assets/styles/weui/widget/weui_tips/weui_mask.less';
@import '../assets/styles/weui/widget/weui_tips/weui_dialog.less';
</style>