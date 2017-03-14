<template>
	<div v-if="this.list.length > 0">
		<!-- cart -->
		<div class="weui_panel weui_panel_access cart">
			<!-- 店铺栏 -->
			<div class="weui_panel_hd">
				<span>{{shop}}</span>
				<span class="edit-item org" @click="changeState">{{isEdit?'完成':'编辑'}}</span>
			</div>
			<!-- 列表栏 -->
			<div class="weui_panel_bd weui_cells_checkbox border-b" v-for='item in formatList'>
				<a class="weui_media_box weui_media_appmsg" href="javascript:;">
					<input type="checkbox" v-model="item.checked" v-bind:class="[item.checked==true?'weui_check':'']" />
					<i class="weui_icon weui_icon_checked" @click='item.checked=!item.checked'></i>
					<div class="weui_media_hd" @click='goUrl(item.link)'> <img class="weui_media_appmsg_thumb cart-itemImg" alt="" v-bind:src="item.sourceUrl"> </div>
					<div class="weui_media_bd marleft" @click='goUrl(item.link)'>
						<!-- 第一行文字 -->
						<h4 class="weui_media_title sixtysize">{{item.name}}</h4>
						<!-- 第二行文字 -->
						<span class="weui_media_desc details">{{item.format2}}:{{item.formatsel}}</span>
						<!-- 第三行文字 价格-->
						<span class="weui_media_desc from fl">
						<span class="price">
							{{item.price|formatPrice}}
						</span>
						<!-- 第三行文字 数量-->
						<span>x</span><span>{{item.count}}</span></span>
					</div>
					<!-- 加减栏 -->
					<c-number :number.sync='item.count' v-if='isEdit' :max="item.num"></c-number>

				</a>
			</div>
		</div>
		<!-- 全选栏 -->
		<div class="bottom weui_cells_checkbox">
			<label class="fl">
				<input type="checkbox" class="weui_check fr" v-model="allChecked"/>
				<i class="weui_icon_checked fl all"></i>
				<span class="maright fortysize">{{checketext?checketext:'全选'}}</span>
			</label>
			<span class="fortysize org">合计：{{price|formatPrice}}</span>
			<span class="button fr" @click='tosetcount' v-bind:class="[price>0?'colororg':'tosetcount']">{{isEdit?'删除':'去结算'}}</span>

		</div>

	</div>

</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		replace: true,
		computed: {
			
			allChecked: {
				get: function() {
					return this.checkedCount == this.formatList.length;
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
			},
			price: function() {
				var price = 0;
				this.formatList.forEach(function(item) {
					if(item.checked) {
						let p = item.count * item.price
						price += p;
					}

				})

				return price;
			}

		},
		methods: {
			formatFunc(list) {
				
				var fList = [],
					that = this;
					if(that.format.notneed){
						return list;
					}else{
						if(list.length != 0) {
							var id = that.format.id,
								name = that.format.name,
								format2 = that.format.format2,
								formatsel = that.format.formatsel,
								price = that.format.price,
								count = that.format.count,
								sourceUrl = that.format.sourceUrl,
								link = that.format.link,
								checked = that.format.checked,
								num = that.format.num;
							console.log(that.format);
							list.forEach(function(item, index) {
								var fitem = {};
								fitem.id = item[id];
								fitem.name = item[name];
								fitem.format2 = item[format2];
								fitem.formatsel = item[formatsel];
								fitem.price = item[price];
								fitem.count = item[count];
								fitem.sourceUrl = item[sourceUrl];
								fitem.link = item[link];
								fitem.checked = item[checked];
								fitem.num = item[num];
								if(name) {
									fitem.name = item[name];
								};
								fList.push(fitem);
							});
						};
						return fList;
					}
				
			},
			tosetcount: function() {
				let checkList = [],
					surplusList = [],
					that = this;

				this.formatList.forEach(function(item, index) {
					if(item.checked == true) {
						checkList.push({
							id: item.id,
							num: item.count
						});
					} else {
						surplusList.push(item);
					}
				});

				this.$emit('hander', checkList, surplusList, this.isEdit ? 'delete' : 'edit');

			},

			changeState: function() {
				this.isEdit = !this.isEdit;
			},

			goUrl: function(url) {
				utils.go(url, this.$router);
			}

		},
		props: {
			list: {
				type: Array,
				default() {
					return []
				}
			},
			shop: String,
			format: {
				type: Object,
				default() {
					return {
						"notneed": true
					}
				}
			}

		},
		data() {
			return {
				formatList: [],
				isEdit: false,

			}
		},

		watch: {
			list(newList) {
				this.formatList = this.formatFunc(newList);
			}
		},

		components: {
			"cIcon": require('./icon.vue'),
			"cNumber": require('../components/number.vue'),

		},
		ready() {
			this.formatList = this.formatFunc(this.list);
		}
	}
</script>
<style lang="less" scoped>
	@import '../assets/styles/weui/widget/weui_panel/weui_panel.less';
	@import '../assets/styles/weui/widget/weui_media_box/weui_media_box.less';
	@import '../assets/styles/components/panelImg.less';
	@import '../assets/styles/weui/widget/weui_cell/weui_check.less';
	@import '../assets/styles/components/cart.less';
</style>