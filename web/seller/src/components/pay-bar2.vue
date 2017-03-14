<template>
    <div class="bar2-wrap">
        <div class="total  vux-1px-t">合计：<span>￥{{price?price:0.00}}</span></div>
		<c-button type="org" :text="buytext?buytext:'立即购买'" @click="$emit('on-button')"></c-button>
	</div>
</template>

<script>
    export default {
        props: ['price','buytext'],
        components:{
            "cButton":require('../components/button.vue')
        }
    }
</script>
<style lang="less">
	@import "../assets/styles/weui/widget/weui_button/weui_button.less";
    @import "../assets/styles/components/pay-bar2.less";
</style>