<!-- 子组件   -->
<template>
	<div class="datanull">
		<div class="msg">{{msg?msg:'暂无数据'}}</div>
		<div class="slot"><slot></slot></div>
	</div>
</template>

<script>
	export default {
		props: ['msg']
	}
</script>
<style lang="less" scoped>
	@import "../assets/styles/components/datanull.less";
</style>