<!-- 子组件   -->
<template>
	<div class="weui_cell">
		<div class="weui_cell_hd">
			<slot name="icon"></slot>
		</div>
		<div class="weui_cell_bd" :class="{'weui_cell_primary':primary==='title'}">
			<p>
				{{title}}
				<slot name="after-title"></slot>
			</p>
			<!-- 第二行文字 -->
			<div class="label9">{{{inlinedesc | emoji}}}</div>
			<p class="time groupname fl">{{time}}</p>
			<p class="time fl">{{groupname}}</p>
		</div>
		<div class="weui_cell_ft" :class="{'weui_cell_primary':primary==='content', 'with_arrow': isLink || !!link}">
			{{value}}
			<slot name="value"></slot>
			<slot></slot>
		</div>
		<slot name="child"></slot>
	</div>
</template>
<script>
	import utils from '../libs/utils.js';
	export default {
			props:['title','inlinedesc','time','groupname','value'],
			data() {
				return {
					
				}
			},
			ready() {
				
			},
			methods: {
				

			},
			components: {
			}
		}
</script>
<style lang="less">
	.fr {
		float: right;
	}
	
	.fl {
		float: left;
	}
	
	
	/* 第二行文字 */
	.weui_cell_bd .label9 {
		color: #666;
	}
	
	/* 第三行文字 */
	.weui_cell_bd .groupname {
		margin-right: 12px;
	}
	
	.weui_cell_bd .time {
		font-size: 12px;
		color: #999;
	}
</style>