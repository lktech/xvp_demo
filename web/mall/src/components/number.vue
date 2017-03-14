
<!-- 子组件   -->
<template>
	<div class="number-amount">
		<span @click.stop.prevent="minus(index)"  v-bind:class="[number <= min?'colorgray':'']">-</span>
		<input type="tel" v-model="number" onkeyup="if(/\D/.test(this.value)||this.value==0){this.value=this.value==0?'':this.value.replace(/\D/g,'')}" readonly>
		<span @click.stop.prevent="plus(index)" v-bind:class="[number==max?'colorgray':'']">+</span>
	</div>
</template>

<script>
	export default {
		replace: true,
		props: {
			number: {
		      type: [Number,String],
		      default:1
		    },
		    max: {
		      type: Number,
		      default:100
		    },
		    min: {
		      type: Number,
		      default:1
		    },
		    step: {
		      type: Number,
		      default:1
		    }
		},
		data() {
			return {
				
			}
		},
		ready() {

		},
		methods: {
			plus($index) {
				let count = this.number - 0 + this.step
				if (count >= this.min && count <= this.max) {
					this.number = count;
				}
			},
			minus($index) {
				let count = this.number - 0 - this.step
				if (count >= this.min && count <= this.max) {
					this.number = count
				}
			}
		}

	}
</script>
<style scoped>
@import "../assets/styles/common/var.less";
@import '../assets/styles/components/number.less';
.colorgray{
	background-color: #E6E6E6;
}
.colorwhite{
	background-color: white;
}
</style>