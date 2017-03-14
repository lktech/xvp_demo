<template>
  <div class="vux-tab" :class="{'vux-tab-no-animate': !animate}">
    <c-tab-item v-for="item in list" :selected="active == item.key" @click="tabClick(item.key)">{{item.value}}</c-tab-item>
    <div v-if="animate" class="vux-tab-ink-bar" :class="barClass" :style="barStyle"></div>
  </div>
</template>

<script>
  import { parentMixin } from './mixins/multi-items';
  export default {
    mixins: [parentMixin],
    ready () {
    // stop bar anmination on first loading
    setTimeout(() => {
      this.hasReady = true
    }, 0)
  },
  props: {
    lineWidth: {
      type: Number,
    default: 2
    },
    activeColor: {
      type: String,
    default: '#F57B00'
    },
    defaultColor: {
      type: String,
    default: '#333'
    },
    animate: {
      type: Boolean,
    default: true
    },
    list:{
      type: Array,
    default: function(){
        return []
      }
    },
    active:{
      type: String,
    default: ''
    }
  },
  computed: {
    barLeft () {
      return `${this.index * (100 / this.number)}%`
    },
    barRight () {
      return `${(this.number - this.index - 1) * (100 / this.number)}%`
    },
    barStyle () {
      return {
        left: this.barLeft,
        right: this.barRight,
        display: 'block',
        backgroundColor: this.activeColor,
        height: this.lineWidth + 'px',
        transition: !this.hasReady ? 'none' : null
      }
    },
    barClass () {
      return {
        'vux-tab-ink-bar-transition-forward': this.direction === 'forward',
        'vux-tab-ink-bar-transition-backward': this.direction === 'backward'
      }
    }
  },
  watch: {
    index (newIndex, oldIndex) {
      this.direction = newIndex > oldIndex ? 'forward' : 'backward'
      this.$emit('on-index-change', newIndex)
    }
  },
  data () {
    return {
      direction: 'forward',
      right: '100%',
      hasReady: false
    }
  },
  methods: {
    tabClick (key) {
      if(this.active ==  key){
        return;
      }
      this.active =  key;
      this.$emit('on-change', key);
    }
  },
  components:{
    "cTabItem":require('./tab-item.vue')
  }
  }
</script>


<style lang="less">
  @import "../assets/styles/components/tab.less";
</style>
