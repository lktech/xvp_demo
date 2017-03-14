<template>
    <div class="headStatus bg-yellow" :style="headHeight">
        <i :class="'hds'+item.status"></i>
        <div class="msg">{{item.msg}}</div>
    </div>
</template>

<script>
    export default{
        props: ['item'],
        data(){
            return {}
        },
        computed: {
            headHeight:function () {
                return {
                    height:document.body.clientWidth/3+"px"
                }
            }
        }
    }
</script>

<style lang="less">
    @import "../assets/styles/components/head-status.less";
</style>