<template>
  <div class="number-amount">
		<span @click.stop.prevent="minus()">-</span>
		<input type="tel" v-model="value2" onkeyup="if(/\D/.test(this.value)||this.value==0){this.value=this.value==0?'':this.value.replace(/\D/g,'')}" readonly>
		<span @click.stop.prevent="plus()">+</span>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
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
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
      value2:this.number
    }
  },
  computed: {
  },
  watch: {
  },
  methods: {
		plus() {
			let count = this.value2 - 0 + this.step
			if (count >= this.min && count <= this.max) {
				this.value2 = count;
			}
		},
		minus() {
			let count = this.value2 - 0 - this.step
			if (count >= this.min && count <= this.max) {
				this.value2 = count
			}
		}
  },
  components: {
	
	}
}
</script>

<style lang="less">
@import "../../assets/styles/common/var.less";
@import '../../assets/styles/components/rui/number.less';

</style>
