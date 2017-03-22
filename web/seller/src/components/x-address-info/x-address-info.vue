<template>
  <ul class="address-info">
    <li @click.stop="click" class="border-t">
        <div class="name">{{name}}</div>
        <div class="phone">{{phone}}</div>
        <div class="address">{{address}}</div>
        <div class="weui_cell_ft with_arrow" v-if="arrows"></div>
    </li>
	</ul>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props:["id","name","phone","address","arrows","link"],
	props:{
		id:{
			type:[String,Number]
		},
		name:{
			type:[String,Number]
		},
		phone:{
			type:[String,Number]
		},
		address:{
			type:[String,Number]
		},
		arrows:{
			type:Boolean,
			default:false
		},
		link:{
			type:[String,Object]
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
      
    }
  },
  computed: {
  },
  watch: {
  	
  },
  methods: {
    click(){
	    if(this.link){
	      utils.go(this.link, this.$router);
	    }else {
        if(this.click){
          this.$emit("click",this.id);
        }
	    }	
    }
  },
  components: {
		
	}
}
</script>

<style lang="less">
@import "../../assets/styles/components/rui/address-info.less";
</style>
