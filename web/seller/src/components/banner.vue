<template>
	<div class="products-box container clearfix">
		<div class="itemlist-box">
			<div class="item-ad" v-bind:class='goodsmodal' v-for="good in goods">
				<a :href="good.sourceUrl" class="item-ad-pic" >
					<img :src="good.imgUrl">
				</a>
				<div class="item-ad-inf">
					<p class="item-inf-title">{{good.title}}</p>
					<!--  good.commission 有佣金的时候，原价是黑色字体，没有佣金的时候，原价是红色字体-->
					<span v-bind:class="[good.commission? 'item-black' : 'item-red']">{{good.disprice}}元</span> 
					&nbsp;
					<template v-if="good.discountType === 'discount'">
                    	<span class="old-price">{{good.oldprice}}元</span>
                    </template>
                    <!-- 包邮其他信息  good.locationinfor === true-->
                    <template v-if="good.locationinfor === true">
                    	<div class="item-desc"> 
							<span>{{good.others}}</span> 
							<span>{{good.discount}}</span> 
							<span v-if="good.discount">|</span> <span>{{good.locations}}</span> 
					    </div>
                    </template>
                    <!-- 佣金  commission: true-->
                    <template v-if="good.commission === true">
                    	<div class="item-desc"> 
							<span class="commision">佣金:¥188.88</span> 
							<p class="salesman">123人在卖</p>
					    </div>
                    </template>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	export
	default {
		replace: true,
		props: ['goods','goodsmodal']
		
	}
</script>

<style lang="less" scoped>
@import '../assets/styles/common/var.less';
@import '../assets/styles/components/banner.less';
</style>
