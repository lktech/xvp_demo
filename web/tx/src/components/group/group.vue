<template>
  <div>
    <div class="weui_cells_title" v-if="title" :style="{color:titleColor}" v-html="title"></div>
    <div class="weui_cells" :class="{'vux-no-group-title':!title}" :style="{marginTop: gutter}">
      <slot name="after-title"></slot>
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: String,
    titleColor: String,
    labelWidth: String,
    labelAlign: String,
    labelMarginRight: String,
    gutter: String
  }
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_cell/weui_access.less';
@import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';

.vux-no-group-title {
  margin-top:15px;
}
.weui_cells > a {
  color:#000;
}
</style>
