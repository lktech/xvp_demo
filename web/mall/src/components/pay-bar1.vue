<template>
    <div class="bar1-wrap border-t">
        <div class="cart vux-1px-t" @click="cartClick" >
            <div class="cartImg"><img src="../assets/images/cart.png"></div>
		    <div>购物车</div>
        </div>
		<c-button type="default" text="加购物车" @click="$emit('on-button', 'addCart')"></c-button>
		<c-button type="org" :text="buytext?buytext:'立即购买'" @click="$emit('on-button', 'buy')"></c-button>
	</div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default {
        props: ['link','buytext'],
        methods: {
            cartClick(){
                if(this.link){
                    utils.go(this.link, this.$router);
                }else{
                   this.$emit('on-button', 'cart'); 
                } 
            }
        },
        components:{
            "cButton":require('../components/button.vue')
        }
    }
</script>
<style lang="less">
	@import "../assets/styles/weui/widget/weui_button/weui_button.less";
    @import "../assets/styles/components/pay-bar1.less";
</style>