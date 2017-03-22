<template>
  <div class="allchecked">
    <label class="weui_cell weui_cells_checkbox" v-for='item in formatList'>
			<div class="weui_cell_hd">
				<slot name="icon"></slot>
			</div>
      <input type="checkbox" v-model="item.checked" class="weui_check" />
      <i class="weui_icon weui_icon_checked iconlist" :id='item.id'></i>
			<div class="weui_cell_bd" :class="{'weui_cell_primary':primary==='title'}">
				<p>
					{{item.title}}
					<slot name="after-title"></slot>
				</p>
				<!-- 第二行文字 -->
				<div class="label9">{{item.inlineDesc}}</div>
				<p class="time groupname fl">{{item.time}}</p>
				<p class="time fl">{{item.groupname}}</p>
			</div>
			<div class="weui_cell_ft" :class="{'weui_cell_primary':primary==='content', 'with_arrow': islink || !!link}">
				{{item.value}}
				<slot name="value"></slot>
				<slot></slot>
			</div>
			<slot name="child"></slot>
		</label>
    <x-datanull v-show="!this.formatList.length" class="checkBg"></x-datanull>
    <div class="bottom border-t">
      <label class="fl allblue weui_cells_checkbox pos">
				<input type="checkbox" class="weui_check fr" v-model="allChecked" :disabled="list.length?false:true"/>
				<i  class="weui_icon_checked fl all"></i>
				<span :class="list.length?'':'clr-6'">{{checktext?checktext:'全选'}}</span>
			</label>
      <span :class="list.length?'fr button':'fr button btnDefault'" @click='submit'>{{btntext}}</span>
    </div>

  </div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props: {
	  btntext: String,
	  checktext: String,
	  islink: Boolean,
	  link:Boolean,
	  primary: {
	    type: String,
	    default: 'title'
	  },
	  list: [Array],
	  format: {
	    type: Object,
	    default() {
	      return {
	        "notneed": true
	      }
	    }
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
      formatList: []
    }
  },
  computed: {
	  allChecked: {
	    get: function() {
				if(this.formatList.length!=0){
					return this.checkedCount == this.formatList.length;
				}else{
					return false;
				}
	    },
	    set: function(value) {
	      this.formatList.forEach(function(item) {
	        item.checked = value;
	      })
	      return value;
	    }
	  },
	  checkedCount: {
	    get: function() {
	      var i = 0;
	      this.formatList.forEach(function(item) {
	        if(item.checked == true) i++;
	      })
	      return i;
	    }
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
	  submit() {
	    if(this.formatList.length){
	      let ids = [],
	      list = [];
	      this.formatList.forEach(function(item) {
	        if(item.checked) {
	          ids.push(item.id);
	          list.push(item);
	        }
	      });
	      this.$emit("submit", {
	        "ids": ids,
	        "list": list
	      });
	    };
	  },
	  formatFunc(list) {
	    let that = this,
	      flist = [];
	    if(that.format.notneed) {
	      return list;
	    } else {
	      list.forEach(function(item) {
	        let id = that.format.id,
	          title = that.format.title,
	          value = that.format.value,
	          inlineDesc = that.format.inlineDesc,
	          time = that.format.time,
	          groupname = that.format.groupname,
	          checked = that.format.checked;
	
	        let fitem = {};
	        fitem.id = item[id],
	          fitem.title = item[title],
	          fitem.value = item[value],
	          fitem.inlineDesc = item[inlineDesc],
	          fitem.time = item[time],
	          fitem.groupname = item[groupname],
	          fitem.checked = item[checked] ? item[checked] : false;
	        flist.push(fitem);
	      });
	      return flist;
	    }
	  }
  },
  components: {
		"cIcon": require('../icon/icon.vue'),
    "xDatanull":require('../x-datanull/x-datanull.vue')
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_cell/weui_check.less';
@import '../../assets/styles/components/rui/allchecked.less';
</style>
