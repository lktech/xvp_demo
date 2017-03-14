<template>
    <div class="page-wrap text-center label9-small vux-1px-tb">
        第{{pageNumber}}页（{{pageNumber}}/{{totalPage}} 每页{{pageSize}}条）
        <div :class="usedNext?'with_arrow':'with_arrow arrow-color'" @click="pageNext"></div>
        <div :class="usedPrev?'with_arrow arrow-left':'with_arrow arrow-color arrow-left'" @click="pagePrev"></div>
	</div>
</template>

<script>
    export default {
        props: ['totalPage','pageNumber','pageSize'],
        data (){
            return {
                usedPrev:true,
                usedNext:true
            }
        },
        methods: {
            pagePrev:function(){
                if(this.pageNumber>1){
                    this.pageNumber-=1;
                    this.usedPrev=true;
                    this.$emit('on-page',this.pageNumber);
                };
                if(this.pageNumber!=this.totalPage){
                    this.usedNext=true;
                };
                if(this.pageNumber==1){
                    this.usedPrev=false;
                };
            },
            pageNext:function(){
                if(this.pageNumber<this.totalPage){
                    this.pageNumber+=1;
                    this.usedNext=true;
                    this.$emit('on-page',this.pageNumber);
                };
                if(this.pageNumber==this.totalPage){
                    this.usedNext=false;
                };
                if(this.pageNumber!=1){
                    this.usedPrev=true;
                };
            }
        },
        ready(){
            if(!this.pageNumber){
                this.pageNumber=1;
            };
            if(!this.pageSize){
                this.pageSize=50;
            };
            if(this.pageNumber==1){
                this.usedPrev=false;
            };
            
        },
        computed:{
            usedNext(){
                if(this.pageNumber==this.totalPage){
                    return false;
                }else{
                    return true;
                };
            }
        }
    }
</script>
<style lang="less">
    @import "../assets/styles/components/page.less";
</style>