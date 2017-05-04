<!-- 子组件   -->
<template>
	<div class="weui_cell" :class="{'vux-tap-active': isLink || !!link}" @click="onClick">
		<div class="weui_cell_hd">
			<slot name="icon"></slot>
		</div>
		<div class="weui_cell_bd" :class="{'weui_cell_primary':primary==='title'}">
			<p>
				{{title}}
				<slot name="after-title"></slot>
			</p>
			<div class="label9">{{inlineDesc}}</div>
		</div>
		<div class="weui_cell_ft" 
			:class="{'weui_cell_primary':primary==='content', 'with_arrow': isLink || !!link,'colorred':colorred,'colororg':colororg}" 
			@click="rightclick(id)">
			<span :class="'clr-'+rgtcolor">{{value}}</span>
			<slot name="value"></slot>
			<slot></slot>
		</div>
		
			
		<slot name="child"></slot>
	</div>
</template>

<script>
	import utils from '../../libs/utils.js';
	export default {
		props: {
			title: String,
			id:Number,
			value: [String, Number],
			isLink: Boolean,
			inlineDesc: String,
			colorred: Boolean,
			colororg: Boolean,
			rightcolor:String,
			primary: {
				type: String,
				default: 'title'
			},
			link: {
				type: [String, Object]
			}
		},
		data(){
			return{
				rgtcolor:this.rightcolor?this.rightcolor:''
			}
		},
		methods: {
			onClick() {
				utils.go(this.link, this.$router)
			},
			rightclick(id) {
				this.$emit('ondelete', id);
			}
		}

	}
</script>
<style scoped>
	.weui_cell .colorred {
		color: red;
	}
	.weui_cell .colororg {
        color: #F57B00;
    }
</style>