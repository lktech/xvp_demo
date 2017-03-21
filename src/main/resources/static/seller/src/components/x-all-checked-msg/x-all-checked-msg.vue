<template>
  <div class="allchecked">
		<div class=" msgbub-wrap">
			<div class="weui_cells_checkbox wrap-pdt of" v-for='item in formatList'>
				<div class="text-center">
					<span class="clr-white wrap-pd time">{{item.time | fmtDate}}</span>
				</div>
				<div class="wrap-pdt of">
					<div class="cb wrap-pdt">
						<input type="checkbox" v-model="item.checked" v-bind:class="[item.checked==true?'weui_check':'']" />
						<i class="weui_icon weui_icon_checked iconlist" @click='item.checked=!item.checked' :id='item.id'></i>
					</div>
					<div class="head">
						<div><img :src="item.headUrl" width="44" height="44"></div>
						<div class="label3-small text-center margint8">{{item.userName?item.userName:item.nike | emoji}}</div>
					</div>
					<div class="bub">
						<div class="arrow"></div>
						<div class="label6-small bub-con" v-if="item.type==1">{{item.msg | emoji}}</div>
						<div class="label6-small bub-con" v-if="item.type==34"><img src="../../assets/images/msgbub-audio.png" width="22" height="22"></div>
						<div class="label6-small bub-con" v-if="item.type==62"><img src="../../assets/images/msgbub-video.png" width="44" height="44"></div>
						<div class="label6-small bub-con" v-if="item.type==3"><img :src="item.file" width="44" height="44"></div>
					</div>
				</div>
			</div>
		</div>
		<x-datanull v-show="!this.formatList.length" class="checkBg"></x-datanull>
		<div class="bottom weui_cells_checkbox">
			<label class="fl">
				<div class="cbAll">
					<input type="checkbox" class="weui_check fr" v-model="allChecked" :disabled="list.length?false:true"/>
					<i class="weui_icon_checked fl all"></i>
				</div>
				<span :class="list.length?'allblue':'clr-6'">{{checktext}}</span>
			</label>
			<div class="weui_media_bd fr">
				<p :class="list.length?'button':'button btnDefault'" @click='submit'>{{btntext}}</p>
			</div>
		</div>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props: {
		btntext: {
			type:String,
			default:'确定'
		},
		checktext: {
			type:String,
			default:'全选'
		},
		list: [Array],
		format: {
			type: Object,
			default() {
				return {
					"notneed": true
				}
			}
		},
	},
	mounted () {
    this.$nextTick(() => {
    	formatList: []
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
				this.formatList = this.formatFunc(newList);
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
				}else{
					list.forEach(function(item) {
						let id = that.format.id,
						checked = that.format.checked,
						type = that.format.type,
						time = that.format.time,
						headUrl = that.format.headUrl,
						nike = that.format.nike,
						msg = that.format.msg,
						file = that.format.file,
						userName = that.format.userName;

						let fitem = {};
						fitem.id = item[id],
						fitem.checked = item[checked] ? item[checked] : false,
						fitem.type = item[type],
						fitem.time = item[time],
						fitem.headUrl = item[headUrl],
						fitem.nike = item[nike],
						fitem.msg = item[msg],
						fitem.file = item[file],
						fitem.userName = item[userName],
						flist.push(fitem);
					});
					return flist;
				}
			}
  },
  components: {
  	"xDatanull":require('../x-datanull/x-datanull.vue')
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box.less';
@import '../../assets/styles/components/weui/widget/weui_cell/weui_check.less';
@import '../../assets/styles/components/rui/msg-bubble.less';
@import '../../assets/styles/components/rui/allchecked.less';
</style>
