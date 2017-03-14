<!-- 子组件   -->
<template>
	<div class="panel">
		<div class="panel-title border-b" v-bind:class="[type? 'org' : '']" >{{title}}</div>
		<div class="panel-body"><slot></slot></div>
	</div>
</template>

<script>
	
	export default {
		props: ['title','type']
	}
</script>
<style lang="less">
	@import "../assets/styles/components/panel.less";
</style>