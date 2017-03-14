<!-- 子组件   -->
<template>
	<c-flexbox-item :span="1/(pernum?pernum:3)" @click="clk">
		<div class="bt">
			<div class="bt-top">{{num}}<span v-if="des">/{{des}}</span></div>
			<div class="bt-btn">{{title}}</div>
		</div>
	</c-flexbox-item>
</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		props: ['title','num','des','link','pernum'],
		methods:{
			clk(){
				utils.go(this.link,this.$router);
			}
		},
		components:{
			"cFlexboxItem":require('./flexbox-item.vue')
		}

	}
</script>