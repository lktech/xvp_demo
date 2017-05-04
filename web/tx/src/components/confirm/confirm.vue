<template>
  <div>
    <x-dialog class="weui_dialog_confirm"
    v-model="showValue"
    :mask-transition="maskTransition"
    :dialog-transition="dialogTransition"
    :hide-on-blur="hideOnBlur"
    @on-hide="$emit('on-hide')"
    @on-show="$emit('on-show')">
      <div class="weui_dialog_hd"><strong class="weui_dialog_title">{{title}}</strong></div>
      <div class="weui_dialog_bd"><slot><div v-html="content"></div></slot></div>
      <div class="weui_dialog_ft">
        <a href="javascript:;" class="weui_btn_dialog default" @click="onCancel">{{cancelText || '取消'}}</a>
        <a href="javascript:;" class="weui_btn_dialog primary" @click="onConfirm">{{confirmText || '确定'}}</a>
      </div>
    </x-dialog>
  </div>
</template>
<script>
import XDialog from '../dialog/dialog.vue'
export default {
  components: {
    XDialog
  },
  props: {
    value: {
      type: Boolean,
      default: false
    },
    hideOnBlur: {
      type: Boolean,
      default: false
    },
    title: String,
    confirmText: String,
    cancelText: String,
    maskTransition: {
      type: String,
      default: 'vux-fade'
    },
    dialogTransition: {
      type: String,
      default: 'vux-dialog'
    },
    content: String
  },
  created () {
    this.showValue = this.show
    if (this.value) {
      this.showValue = this.value
    }
  },
  watch: {
    value (val) {
      this.showValue = val
    },
    showValue (val) {
      this.$emit('input', val)
    }
  },
  data () {
    return {
      showValue: false
    }
  },
  methods: {
    onConfirm () {
      this.showValue = false
      this.$emit('on-confirm')
    },
    onCancel () {
      this.showValue = false
      this.$emit('on-cancel')
    }
  }
}
</script>
