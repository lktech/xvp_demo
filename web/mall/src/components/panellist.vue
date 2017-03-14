<!-- 图文列表子组件 -->
<template>
  <a class="weui_media_box weui_media_appmsg" href="javascript:;">
    <div class="weui_media_hd imgheight" @click='goUrl(item)'>
      <div v-if='icon' class='imgheight' :class='icon'></div>
      <template v-else>
			<img class="weui_media_appmsg_thumb" v-if="imglink" v-lazy="imglink">
	  </template>
    </div>
    <div class="weui_media_bd" @click='goUrl(item)'>
      <h4 class="weui_media_title">{{title}}</h4>
      <div class="weui_media_desc details" v-if='price'>{{price}}</div>
      <div class="weui_media_desc details" v-else>{{details}}</div>
      <div class="weui_media_desc from">
        <span v-bind:class="[colororg?'colororg':'']">{{from}}</span>
        <span>{{time}}</span>
        <span v-if='line'>|</span>
        <span v-if='!cheng'>x</span>
        <span>{{others}}</span>
      </div>
    </div>
    <div v-if='arrowtips' class="weui_cell_ft" @click='goUrl(item)'>{{righttips}}</div>
    <div class="floatr label6 clr-org" v-if='righttips==0' @click='goUrl(item)'>已售罄</div>
    <div class="floatr label6" v-if='righttips&&righttips!=0' @click='goUrl(item)'>库存总数:{{righttips}}</div>
    <div v-if='arrow' class="weui_cell_ft with_arrow" @click='goUrl'></div>
    <c-button v-if='buttonicon' :type="type" class='floatr' :size='size' @click='hander' :text="text"></c-button>
  </a>

</template>

<script>
  import utils from '../libs/utils.js';
  export default {
    replace: true,
    props: [
      'id',
      'icon',
      'title',
      'price',
      'details',
      'from',
      'time',
      'others',
      'imglink',
      'arrow',
      'arrowtips',
      'righttips',
      'buttonicon',
      'btnstats',
      'line',
      'type',
      'link',
      'size',
      'text',
      'colororg',
      'cheng',
      'minPrice',
      'maxPrice',
    ],
    data() {
      return {}
    },
    ready() {
      this.format();
    },
    methods: {
      goUrl(item) {
        if(this.link) {
          utils.go(this.link, this.$router);
        } else {
          this.$emit('handing', item);
        }
      },
      hander() {
        this.$emit("hander", {
          "id": this.id,
          "btnstats": this.btnstats
        });
      },
      format() {
        if(this.minPrice) { //有规格
          if(this.minPrice == this.maxPrice) {
            this.price = "价格:￥" + utils.formatPrice(this.minPrice);

          } else if(this.minPrice != this.maxPrice) {
            this.price = "价格:￥" + utils.formatPrice(this.minPrice) + "~" + utils.formatPrice(this.maxPrice);
          }
        } else if(this.price) {
					this.price = "价格:￥" + utils.formatPrice(this.price);
        }
      }
    },
    components: {
      "cButton": require('../components/button.vue')

    }

  }
</script>
<style lang="less" scoped>
	@import '../assets/styles/common/var.less';
	@import '../assets/styles/weui/widget/weui_panel/weui_panel';
	@import '../assets/styles/weui/widget/weui_media_box/weui_media_box';
	@import '../assets/styles/components/panelImg.less';
</style>