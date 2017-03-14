<template>
  <div class="vux-search-box" :class="{'vux-search-fixed':isFixed}" :style="{top: isFixed ? top : ''}">
    <div class="weui_search_bar" id="search_bar" :class="{weui_search_focusing: !isCancel}">
      <form class="weui_search_outer" @submit.prevent="$emit('on-submit', value)">
        <div class="vux-search-mask" @click="touch" v-show="!isFixed && autoFixed"></div>
        <div class="weui_search_inner">
          <i class="weui_icon_search"></i>
          <input type="search" class="weui_search_input" id="search_input" :placeholder="placeholder" autocomplete="off" :required="required" v-model="value" v-el:input
          @focus="isFocus = true"
          @blur="isFocus = false"/>
          <a href="javascript:" class="weui_icon_clear" id="search_clear" @click="clear"></a>
        </div>
        <label for="search_input" class="weui_search_text" id="search_text" v-show="!isFocus && !value">
          <i class="weui_icon_search"></i>
          <span>{{placeholder}}</span>
        </label>
      </form>
      <a href="javascript:" class="weui_search_cancel" :class="[btncolor?buttonColor:'']" id="search_cancel" @click="cancel">{{value?'搜索':cancelText}}</a>
    </div>
    <div class="weui_cells weui_cells_access vux-search_show aaa" id="search_show" v-show="isFixed" :style="{height: listheight}">
      <slot></slot>
      <!--<div class="weui_cell" v-for="item in results" @click="handleResultClick(item)">
        <div class="weui_cell_bd weui_cell_primary">
          <p>{{item.title}}</p>
        </div>
      </div>-->
      
      <c-product-list type='small' :goods='results'></c-product-list>
      <slot name="list"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    required: {
      type: Boolean,
      default: true
    },
    placeholder: {
      type: String,
      default: '搜索'
    },
    cancelText: {
      type: String,
      default: '取消'
    },
    value: {
      type: [String,Number],
      twoWay: true,
      default: ''
    },
    results: {
      type: Array,
      default () {
        return []
      }
    },
    autoFixed: {
      type: Boolean,
      default: true
    },
    top: {
      type: String,
      default: '0px'
    },
    btncolor:{
      type: String,
     default: ''
    }
  },
  methods: {
    clear () {
      this.value = ''
      this.isFocus = true
      this.setFocus()
    },
    cancel () {
      if(this.value){
        this.$emit('on-submit',this.value)
      }else{
        this.value = ''
        this.isCancel = true
        this.isFixed = false
        this.$emit('on-cancel')
        this.clearResults();
      }

    },
    handleResultClick (item) {
      this.$emit('result-click', item)
      this.isCancel = true
      this.isFixed = false
      this.clearResults();
    },
    touch () {
      this.isCancel = false
      if (this.autoFixed) {
        this.isFixed = true
      }
    },
    setFocus () {
      this.$els.input.focus()
    },
    clearResults(){
      this.results.splice(0,this.results.length);
    }
  },
  data () {
    return {
      isCancel: true,
      isFocus: false,
      isFixed: false,
      buttonColor:this.btncolor?"clr-"+this.btncolor:''
    }
  },
  watch: {
    isFixed (val) {
      if (val === true) {
        this.setFocus();
        this.isFocus = true;
        $("body").addClass("body-noscroll");
      } else {
        $("body").removeClass("body-noscroll");
      }
    },
    value (val) {
      this.$emit('on-change', this.value)
    }
  },
  computed:{
    listheight(){
      return $(window).height() - 40 + "px";
    }
  },
  components: {
        "cProductList": require('../components/product-list.vue'),

      },
      
  
}
</script>

<style lang="less">
@import '../assets/styles/weui/icon/weui_icon_font.less';
@import '../assets/styles/weui/widget/weui_searchbar/weui_searchbar.less';

.vux-search-fixed {
  position: fixed;
  left: 0;
  top: 0;
  z-index: 998;
  background: #fff;
  backdrop-filter: blur(5px);
}
.vux-search-box {
  width: 100%;
}
.weui_cells.vux-search_show {
  margin-top: 0;
  overflow-y: auto;
  padding-bottom: 50px;
}
.vux-search-mask {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 5;
}
.vux-search-box .weui_cells:after {
  display: none;
}
</style>
