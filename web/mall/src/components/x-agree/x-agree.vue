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
import utils from '../../libs/utils.js';
export default {
	props:{
		terms:String,
		title:String,
		checked:{
			type:Boolean,
			default:false
		},
		type:String,
		link:{
			type:[Number,String]
		}
	},
	mounted () {
    this.$nextTick(() => {
    	this.$emit('on-change', this.val);
    })
  },
  created () {
  },
  data () {
    return {
      val:this.checked === false?false:true,
			clazz:this.type?this.type:'green'
    }
  },
  computed: {
  },
  watch: {
  	val (newVal) {
			this.$emit('on-change', newVal);
		}
  },
  methods: {
		showterms(){
			if(this.link){
				utils.go(this.link,this.$router);
			}else{
				this.$emit('showterms');
			}
		}
  },
  components: {
		"cIcon": require('../icon/icon.vue'),
	}
}
</script>

<style lang="less">
	@import '../../assets/styles/components/rui/agree.less';
</style>
