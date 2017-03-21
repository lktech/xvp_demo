<template>
  <div class="banner-tab">
		<x-flexbox :gutter="0" wrap="wrap">
			<slot></slot>
		</x-flexbox>
	</div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
	props: [],
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
		"xFlexbox":require('../flexbox/flexbox.vue')
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/banner-tab.less';
</style>
