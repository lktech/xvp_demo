<!-- 子组件   -->
<template>
  <div id="container">
    <div class="aside">{{left_title}}</div>
    <div class="section">{{right_content}}</div>
  </div>
</template>

<script>
  export default {
    props: {
      left_title: String,
      right_content: String,
      
    },

  }
</script>
<style lang="less" scoped>
@import '../assets/styles/components/two-line-cell.less';
</style>