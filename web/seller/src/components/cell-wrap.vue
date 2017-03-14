<!-- 子组件   -->
<template>
    <div class="weui_cells">
        <div v-if="title" class="cell-wrap-title" :class="{'cell-wrap-title-dark':dark}">{{title}}<span class="desc" :style="{color:descColor}" @click="descClick">{{desc}}</span>
        </div>
        <slot></slot>
    </div>
</template>

<script>

    export default {
        replace: true,
        props: ['title', 'dark', 'desc','descColor'],
        methods:{
            descClick(){
                this.$emit("desc");
            }
        }

    }
</script>
<style lang="less">
    @import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
	.weui_cells span.desc{float: right;padding-right: 10px;}

</style>