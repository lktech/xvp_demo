<!-- 子组件   -->
<template>
	<div class="banner-tab">
		<c-flexbox :gutter="0" wrap="wrap">
			<slot></slot>
		</c-flexbox>
	</div>


</template>

<script>
	export default {
		props: [],
		components:{
			"cFlexbox":require('./flexbox.vue')
		}

	}
</script>
<style lang="less">
	@import "../assets/styles/components/banner-tab.less";
</style>