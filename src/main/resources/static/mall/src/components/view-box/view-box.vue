<template>
  <div class="weui_tab">
    <slot name="header"></slot>
    <!--<div class="weui_tab_bd vux-fix-safari-overflow-scrolling" ref="viewBoxBody" id="vux_view_box_body">-->
    <div class="view-box vux-fix-safari-overflow-scrolling">
      <slot></slot>
    </div>
    <slot name="bottom"></slot>
  </div>
</template>

<script>
  export default {

  }
</script>

<style lang="less">
  @import '../../assets/styles/components/weui/widget/weui_tab/weui_tab_tabbar.less';
</style>
