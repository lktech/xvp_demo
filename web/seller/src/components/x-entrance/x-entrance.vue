<template>
  <div class="entrance" :class="[pernum == 3 ? 'vux-1px-t' : '']">
    <r-flexbox :gutter="0" wrap="wrap">
      <r-flexbox-item :span="splitNum" @click.native="iClick(item.link)" v-for="(item,index) in list">
        <div class="etc-item" :class="[pernum == 3?'border-b':'', pernum == 3 && (index+1)%pernum != 0 ? 'border-r' : '']">
          <div class="etc-icon" :class="item.sysicon" v-if="item.sysicon"></div>
          <div class="etc-icon" :style="{backgroundImage: 'url('+item.icon+')'}" v-else></div>
          <div class="text">{{item.title}}</div>
        </div>
     </r-flexbox-item>
    </r-flexbox>
  </div>
</template>

<script>
import utils from '../../libs/utils.js';
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
	mounted () {
    this.$nextTick(() => {
    })
  },
  created () {
  },
  data () {
    return {
      
    }
  },
  computed: {
  	splitNum () {
      return 1/this.pernum
    }
  },
  watch: {
  	list(newList) {
	    this.formatList = this.formatList.concat(this.formatFunc(newList));
	    if(!this.formatList.length){
	      $('.weui_cells').addClass('removeBorder');
	    }else{
	      $('.weui_cells').removeClass('removeBorder');
	    };
	  }
  },
  methods: {
    iClick(link){
      utils.go(link, this.$router);
    }
  },
  components: {
		"rFlexbox": require('../flexbox/flexbox.vue'),
    "rFlexboxItem":require('../flexbox/flexbox-item.vue')
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_cell/weui_check.less';
@import '../../assets/styles/components/rui/entrance.less';
</style>
