<template>
    <div class="myInfo">
        <div class="title">{{info.title}}</div>
        <div class="money">{{info.money}}<span class="weui_cell_ft with_arrow go" @click="btnClick"></span></div>
        <div class="tj">
            <div class="left">
                {{info.subTitleL}}<br />
                <b>{{info.subMoneyL}}</b>
            </div>
            <div class="right">
                {{info.subTitleR}}<br />
                <b>{{info.subMoneyR}}</b>
            </div>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default{
        props: ['info'],
        data(){
            return {}
        },
        computed: {},
        methods:{
            btnClick:function () {
                utils.go(this.info.link,this.$router);
            }
        }
    }
</script>

<style lang="less">
    @import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
    @import "../assets/styles/components/my-info.less";
</style>