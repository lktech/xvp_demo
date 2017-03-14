<template>
  <div class="clocker">
    <span :class="'clr-'+color" v-if="showTimeString">{{timeString}}</span>
    <div class="vux-clocker-tpl"><slot></slot></div>
  </div>
</template>

<script>
import Clocker from '../libs/clocker.js';
export default {
  ready () {
    this.slot = this.$el.querySelector('.vux-clocker-tpl')
    this.slotString = this.slot.innerHTML
    if (this.slotString !== '') {
      this.showTimeString = false
    }
    this.render()
  },
  methods: {
    render () {
      //if (!this.time) return
      this.clocker = new Clocker(this.time)
      .on('tick', event => {
        this.update(event)
        this.$emit('on-tick', event)
      })
      .on('finish', () => {
        this.timeString = '00:00:00'
        this.$emit('on-finish')
      })
      .start()
    },
    update (event) {
      if (this.showTimeString) {
        this.timeString = event.strftime(this.format)
      } else {
        let string = event.strftime(this.slotString)
        if (string !== this.cacheSlotString) {
          this.slot.innerHTML = this.cacheSlotString = string
        }
      }
    }
  },
  props: {
    time: String,
    format: {
      type: String,
      default: '%D天%H小时%M分%S秒'
    },
    color: {
      type: String,
    default: 'org'
    }
  },
  watch: {
    time () {
      this.clocker.remove()
      this.render()
    }
  },
  data () {
    return {
      showTimeString: true,
      timeString: '',
      slotString: '',
      cacheSlotString: ''
    }
  },
  beforeDestroy () {
    this.clocker.remove()
    this.clocker = null
  }
}
</script>
<style lang="less">
	.clocker{display: inline-block;margin: 0 5px 0 0;}
</style>