<template>
  <span v-text="text" :class="['vux-badge', {'vux-badge-single': text.length === 1}]"></span>
</template>

<script>
export default {
  props: {
    text: [String, Number]
  }
}
</script>

<style lang="less" scoped>
  @import "../assets/styles/components/badge.less";
</style>
