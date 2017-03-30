<template>
  <a class="weui_media_box weui_media_appmsg pannel-list" href="javascript:;">
    <div class="weui_media_hd imgheight" @click='goUrl()'>
      <div v-if='icon' class='imgheight' :class='icon'></div>
		<template v-else>
			<img class="weui_media_appmsg_thumb" v-if="imglink" :src="imglink">
		</template>
    </div>
    <div class="weui_media_bd" @click='goUrl()'>
      <h4 class="weui_media_title">{{title?title:''}}</h4>
      <div class="weui_media_desc details" v-if='price'>{{price2}}</div>
      <div class="weui_media_desc details" v-else>{{details}}</div>
      <div class="weui_media_desc from">
        <span v-bind:class="[colororg?'colororg':'']">{{from}}</span>
        <span>{{time}}</span>
        <span v-if='line'>|</span>
        <span v-if='cheng'>x</span>
        <span>{{others}}</span>
      </div>
    </div>
		<div v-if='arrowtips&&!buttonicon==true' class="weui_cell_ft" @click='goUrl()'>{{righttips}}</div>
	  <div v-if='leftarrow&&!buttonicon==true' class="weui_cell_ft" @click='goUrl()'>{{leftarrow}}</div>
	  <div class="floatr label6 clr-org" v-if='righttips==0&&!buttonicon==true' @click='goUrl()'>已售罄</div>
	  <div class="floatr label6" v-if='righttips&&righttips!=0&!buttonicon==true' @click='goUrl()'>库存总数:{{righttips}}</div>
	  <div v-if='arrow&&!buttonicon==true' class="weui_cell_ft with_arrow" @click='goUrl'></div>
		<c-button  v-if='buttonicon==true' :size='size?size:"small"' :text='text?text:"提交"' :type='type' @click.native='hander'></c-button>
  </a>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
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
	  'leftarrow',
	  'righttips',
	  'buttonicon',
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
	mounted () {
    this.$nextTick(() => {
      this.format();
    })
  },
  created () {
  },
  data () {
    return {
      price2:this.price,
      minPrice2:this.minPrice,
      maxPrice2:this.maxPrice
    }
  },
  computed: {
  },
  watch: {
  	price(){
  		this.price2=this.price,
  		this.minPrice2=this.minPrice,
  		this.maxPrice2=this.maxPrice
  		this.format();
  	}
  },
  methods: {
	  goUrl() {
	    if(this.link) {
	      utils.go(this.link, this.$router);
	    } else {
	      this.$emit('handing', {
		      "id": this.id
	    	});
	    }
	  },
	  hander() {
	    this.$emit("hander", {
	      "id": this.id
	    });
	  },
	  format() {
	    if(this.minPrice2) { //有规格
	      if(this.minPrice2 == this.maxPrice2) {
	        this.price2 = "价格:￥" + utils.formatPrice(this.minPrice2);
			
	      } else if(this.minPrice2 != this.maxPrice2) {
	        this.price2 = "价格:￥" + utils.formatPrice(this.minPrice2) + "~" + utils.formatPrice(this.maxPrice2);
	        
	      }
	    } else if(this.price2) {
			this.price2 = "价格:￥" + utils.formatPrice(this.price2);
	    }
	  }
  },
  components: {
	  "cButton": require('../button/button.vue')
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/common/var.less';
@import '../../assets/styles/components/rui/panelImg.less';
@import '../../assets/styles/components/weui/widget/weui_panel/weui_panel';
@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box';

</style>
