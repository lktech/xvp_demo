<template>
  <i :class="className"></i>
</template>

<script>
export default {
  props: {
    type: String
  },
  computed: {
    className () {
      return `weui_icon weui_icon_${this.type}`
    }
  }
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/icon/weui_icon_font.less';

.icon_big:before {
  font-size: 104px;
}

.icon_small:before {
  font-size: 12px;
}
</style>
