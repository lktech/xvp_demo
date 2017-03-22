<template>
  <div class="page-wrap text-center label9-small vux-1px-tb">
        第{{pageNum}}页（{{pageNum}}/{{totalPage}} 每页{{pageSi}}条）
        <div :class="usedN?'with_arrow':'with_arrow arrow-color'" @click="pageNext"></div>
        <div :class="usedP?'with_arrow arrow-left':'with_arrow arrow-color arrow-left'" @click="pagePrev"></div>
	</div>
</template>

<script>
export default {
	props: {
	  totalPage: Number,
	  pageNumber: Number,
	  pageSize: Number,
	  usedNext: {
	    type: Boolean,
	    default: true
	  },
	  usedPrev: {
	    type: Boolean,
	    default: true
	  },
	},
	mounted () {
    this.$nextTick(() => {
	    if(!this.pageNum){
	        this.pageNum=1;
	    };
	    if(!this.pageSi){
	        this.pageSi=50;
	    };
	    if(this.pageNum==1){
	        this.usedP=false;
	    };
    })
  },
  created () {
  },
  data () {
    return {
      usedP:this.usedPrev,
      usedN:this.usedNext,
      pageNum:this.pageNumber,
      pageSi:this.pageSize
    }
  },
  computed: {
  	
  },
  watch: {
  	
  },
  methods: {
    pagePrev:function(){
	    if(this.pageNum>1){
	        this.pageNum-=1;
	        this.usedP=true;
	        this.$emit('on-page',this.pageNum);
	    };
	    if(this.pageNum!=this.totalPage){
	        this.usedN=true;
	    };
	    if(this.pageNum==1){
	        this.usedP=false;
	    };
    },
    pageNext:function(){
	    if(this.pageNum<this.totalPage){
	        this.pageNum+=1;
	        this.usedN=true;
	        this.$emit('on-page',this.pageNum);
	    };
	    if(this.pageNum==this.totalPage){
	        this.usedN=false;
	    };
	    if(this.pageNum!=1){
	        this.usedP=true;
	    };
    }
  },
  components: {
  	
	}
}
</script>

<style lang="less">
@import "../../assets/styles/components/rui/page.less";
</style>
