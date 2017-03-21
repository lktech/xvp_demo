<template>
  <div class="datanull" :style="{'paddingTop':top,'paddingBottom':bottom}">
		<div class="msg">{{msg?msg:'暂无数据'}}</div>
		<div class="slot"><slot></slot></div>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props: {
	  msg: String,
	  top: {
	    type: String,
	    default: '150px'
	  },
	  bottom: {
	    type: String,
	    default: '0'
	  },
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
  	
  },
  components: {
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/datanull.less';
</style>
