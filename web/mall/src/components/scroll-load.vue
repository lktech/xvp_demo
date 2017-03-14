<!-- 子组件   -->
<template>
	<div class="scroll-load">
		<slot></slot>
		<div class="load-more-wrap"><div class="load-more" v-show="noMore || isLoading">{{noMore?'无更多内容':'加载中..'}}</div></div>
	</div>

</template>

<script>
	export default {
		props: ['url','params','eleid','type'],
		data(){
			return {
				isLoading:false,
				noMore:false,
				data:this.params?this.params:{"pageNumber":2,"pagesize":15},
				ajaxtype:this.type?this.type:'POST'
			}
		},
		ready(){
			let that = this,
				el = $(window);

			if(that.eleid){
				el = $("#"+that.eleid);
			}
			el.scroll(function(){
				let scrollTop = $(this).scrollTop(),
						windowHeight = $(this).height(),
						scrollHeight = $(document).height();

				if(that.eleid){
					scrollHeight = this.scrollHeight;
				}
				if(scrollTop + windowHeight == scrollHeight){
					if(that.isLoading){
						return;
					}
					if(that.noMore){
						return;
					}
					that.isLoading = true;
					that.load();
				}
			});
		},
		destroyed(){
			let that = this,
				el = $(window);

			if(that.eleid){
				el = $("#"+that.eleid);
			}
			el.off('scroll');
		},
		methods: {
			load () {
				let that = this;
				$.ajax({
					url: that.url,
					dataType: 'json',
					type: this.ajaxtype,
					data:that.data,
					success: function(data){
						that.isLoading = false;
						if(data.success){
							if(data.obj){
								if(data.obj.length > 0){
									that.$emit('on-load',data.obj);
									that.data.pageNumber++;
								}else{
									that.noMore = true;
								}
							}else{
								that.$emit('on-load',false);
							}
						}else{
							that.$emit('on-load',false);
						}
					},
					error:function(){
						that.isLoading = false;
						that.$emit('on-load',false);
					}
				});
			}
		}

	}
</script>
<style lang="less" scoped>
	@import "../assets/styles/components/scroll-load.less";
</style>