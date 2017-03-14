<!-- 子组件   -->
<template>
	<div class="title-line" :class="{'title-noline':noline}">
		<span>{{title}}</span>
	</div>

</template>

<script>
	export default {
		props: ['title','noline'],
		data(){
			return {

			}
		},
		ready(){

		}

	}
</script>
<style lang="less">
	@import "../assets/styles/components/title.less";
</style>