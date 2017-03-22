<!-- 子组件   -->
<template>
  <div class="clearfix product-list" v-bind:class='"product-list-"+type'>
    <slot></slot>
  </div>

</template>

<script>
  export default {
    props: ['type']
  }
</script>