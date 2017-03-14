<template>
  <div>
    <slot name="header"></slot>
    <div class="vux-fix-safari-overflow-scrolling" v-el:view-box-body>
      <slot></slot>
    </div>
    <slot name="bottom"></slot>
  </div>
</template>

