<template>
  <div class="title-line" :class="{'title-noline':noline}">
		<span>{{title}}</span>
	</div>
</template>

<script>
export default {
	props: ['title','noline'],
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
	@import '../../assets/styles/components/rui/title.less';
</style>
