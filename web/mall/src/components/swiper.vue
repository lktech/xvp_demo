<template>
  <div class="vux-slider">
    <div class="vux-swiper" :style="{height: xheight}">
      <slot></slot>
      <div class="vux-swiper-item" v-for="(index, item) in formatList" @click="clickListItem(item)" :data-index="index">
        <a href="javascript:">
          <!--<div class="vux-img" :style="{backgroundImage: buildBackgroundUrl(item.img)}"></div>-->
          <div class="vux-img" v-lazy:background-image="item.img"></div>
          <!--<p class="vux-swiper-desc" v-if="showDescMask">{{item.title}}</p>-->
        </a>
      </div>
    </div>
    <div :class="[dotsClass, 'vux-indicator', 'vux-indicator-' + dotsPosition]" v-show="showDots">
      <a href="javascript:" v-for="key in length">
        <i class="vux-icon-dot" :class="{'active': key === current}"></i>
      </a>
    </div>
  </div>
</template>

<script>
import Swiper from '../libs/swiper.js';
import utils from '../libs/utils.js';

export default {
  //bob
  computed: {
    formatList(){
      this.list?this.list:this.list=[];
    if(this.format.notneed){
      return this.list;
    }else{
      return this.formats(this.list);
    }

    }
  },
  ready () {
    if (!(this.list && this.list.length === 0)) {
      this.render()
    }
    this.xheight = this.getHeight()
  },
  methods: {
    //bob
    formats:function(list){
      var fList=[],that = this;
      if(list.length != 0){
        var url = that.format.url,
            img = that.format.img,
            title = that.format.title;
        list.forEach(function(item, index) {
          var fitem = {};
          fitem.url = item[url];
          fitem.img = item[img];
          if(title){
            fitem.title = item[title];
          };
          fList.push(fitem);
        });
      };
      return fList;
    },
    clickListItem (item) {
      if(item.url){
	  		utils.go(item.url, this.$router);
	  	}else{
	  		utils.go({"name":"detail","params":{"id":item.id}}, this.$router);
	  	}
      this.$emit('on-click-list-item', JSON.parse(JSON.stringify(item)))
    },
    buildBackgroundUrl (url) {
      return `url(${url})`
    },
    render () {
      this.swiper = new Swiper({
        container: this.$el,
        direction: this.direction,
        auto: this.auto,
        loop: this.loop,
        interval: this.interval,
        threshold: this.threshold,
        duration: this.duration,
        height: this.height || this._height,
        minMovingDistance: this.minMovingDistance,
        imgList: this.imgList
      })
      .on('swiped', (prev, index) => {
        this.current = index
        this.index = index
      })
    },
    rerender () {
      if (!this.$el) {
        return
      }
      this.$nextTick(() => {
        this.index = 0
        this.current = 0
        this.length = this.list.length || this.$children.length
        this.destroy()
        this.render()
      })
    },
    destroy () {
      this.swiper && this.swiper.destroy()
    },
    getHeight () {
      // when list.length > 0, it's better to set height or ratio
      const hasHeight = parseInt(this.height, 10)
      if (hasHeight) return this.height
      if (!hasHeight) {
        if (this.aspectRatio) {
          return this.$el.offsetWidth * this.aspectRatio + 'px'
        }
        return '180px'
      }
    }
  },
  props: {
    list: {
      type: Array,
      default () {
        return []
      }
    },
    direction: {
      type: String,
      default: 'horizontal'
    },
    showDots: {
      type: Boolean,
      default: true
    },
    showDescMask: {
      type: Boolean,
      default: true
    },
    dotsPosition: {
      type: String,
      default: 'center'
    },
    dotsClass: String,
    auto: {
      type: Boolean,
      default: false
    },
    loop: Boolean,
    interval: {
      type: Number,
      default: 3000
    },
    threshold: {
      type: Number,
      default: 50
    },
    duration: {
      type: Number,
      default: 300
    },
    height: {
      type: String,
      default: 'auto'
    },
    aspectRatio: Number,
    minMovingDistance: {
      type: Number,
      default: 0
    },
    index: {
      type: Number,
      default: 0
    },
    format: {
      type: Object,
      default () {
          return {"notneed":true}
      }
    }
  },
  data () {
    return {
      current: this.index,
      xheight: 'auto',
      length: this.list.length
    }
  },
  watch: {
    list (val) {
      this.rerender()
    },
    current (currentIndex) {
      this.$emit('on-index-change', currentIndex)
    },
    index (val) {
      if (val !== this.current) {
        this.$nextTick(() => {
          this.swiper.go(val)
        })
      }
    }
  },
  beforeDestroy () {
    this.destroy()
  }
}

</script>

<style lang="less">
@pre: vux;

.@{pre}-slider {
  overflow: hidden;
  position: relative;

  > .@{pre}-indicator, .@{pre}-indicator-right {
    position: absolute;
    right: 15px;
    bottom: 10px;

    > a {
      float: left;
      margin-left: 6px;

      > .@{pre}-icon-dot {
        display: inline-block;
        vertical-align: middle;
        width: 9px;
        height: 9px;
        border-radius: 100%;
        background-color: #d0cdd1;
      }
      > .@{pre}-icon-dot.active {
        background-color: #333;
      }

    }
  }

  > .@{pre}-indicator-center {
    right: 50%;
    transform: translateX(50%)
  }

  > .@{pre}-indicator-left {
    left: 15px;
    right: auto;
  }

  > .@{pre}-swiper {
    overflow: hidden;
    position: relative;

    > .@{pre}-swiper-item {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;

      > a {
        display: block;
        width: 100%;
        height: 100%;

        > .@{pre}-img {
          display: block;
          width: 100%;
          height: 100%;
          background: center center no-repeat;
          background-size: cover;
        }

        > .@{pre}-swiper-desc {
          position: absolute;
          left: 0;
          right: 0;
          bottom: 0;
          height: 44px;
          font-size: 16px;
          padding: 20px 50px 12px 13px;
          margin: 0;
          background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, .7) 100%);
          color: #fff;
          text-shadow: 0 1px 0 rgba(0, 0, 0, .5);
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          word-wrap: normal;
        }

      }
    }
  }
}
</style>
