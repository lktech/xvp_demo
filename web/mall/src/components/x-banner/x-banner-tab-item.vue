<template>
  <x-flexbox-item :span="1/(pernum?pernum:3)" @click.native="clk">
		<div class="bt">
			<div class="bt-top">{{num}}<span v-if="des">/{{des}}</span></div>
			<div class="bt-btn">{{title}}</div>
		</div>
	</x-flexbox-item>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props:{
		title:String,
		num:[String,Number],
		des:String,
		link:[String,Object],
		pernum:Number,
		id:[String,Number]
		
	},
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
      
    }
  },
  computed: {
  	
  },
  watch: {
  },
  methods: {
  	clk(){
  		if(this.link){
  			utils.go(this.link,this.$router);
  		}else{
  			this.$emit('clk',this.id);
  		}
			
		}
  },
  components: {
		"xFlexboxItem":require('../flexbox/flexbox-item.vue')
	}
}
</script>