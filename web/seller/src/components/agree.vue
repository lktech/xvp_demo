<!-- 子组件   -->
<template>
	<div class="agree">
		<label>
			<input class="agree-ipt" type="checkbox" v-model="val">
			<c-icon :type="val?'success':'circle'"></c-icon>{{title?title:'我已阅读并同意'}}
		</label>
		<span @click="showterms" :class="'clr-'+clazz">《{{terms}}》</span>
	</div>

</template>

<script>
	import utils from '../libs/utils.js';
	export default {
		props: ['terms','title','checked','type','link'],
		data(){
			return {
				val:this.checked === false?false:true,
				clazz:this.type?this.type:'green'
			}
		},
		ready(){
			this.$emit('on-change', this.val);

		},
		methods:{
			showterms(){
				if(this.link){
					utils.go(this.link,this.$router);
				}else{
					this.$emit('showterms');
				}
			}
		},
		components: {
			"cIcon": require('./icon.vue')

		},
		watch: {
			val (newVal) {
				this.$emit('on-change', newVal);
			}
		}

	}
</script>
<style lang="less">
	@import "../assets/styles/components/agree.less";
</style>