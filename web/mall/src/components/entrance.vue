<template>
  <div class="entrance" :class="[pernum == 3 ? 'vux-1px-t' : '']">
    <c-flexbox :gutter="0" wrap="wrap">
      <c-flexbox-item :span="splitNum" @click="iClick(item.link)" v-for="item in list">
        <div class="etc-item" :class="[pernum == 3?'border-b':'', pernum == 3 && ($index+1)%pernum != 0 ? 'border-r' : '']">
          <div class="etc-icon" :class="item.sysicon" v-if="item.sysicon"></div>
          <div class="etc-icon" :style="{backgroundImage: 'url('+item.icon+')'}" v-else></div>
          <div class="text">{{item.title}}</div>
        </div>
        </a>
      </c-flexbox-item>
     
    </c-flexbox>
  </div>

</template>

<script>
  import utils from '../libs/utils.js';

export default {

  props: {
    pernum: {
      type: Number,
      default: 3
    },
    list:{
      type: Array,
      default: []
    }
  },
  computed: {
    splitNum () {
      return 1/this.pernum
    }
  },
  data (){
    return {
    }

  },
  methods: {
    iClick(link){
      utils.go(link, this.$router);
    }
  },
  components:{
    "cFlexbox":require('./flexbox.vue'),
    "cFlexboxItem":require('./flexbox-item.vue'),
  }
}
</script>


<style lang="less">
  @import "../assets/styles/components/entrance.less";
</style>
