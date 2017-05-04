<template>
    <div class="myInfo">
        <div class="title">{{info.title}}</div>
        <div class="money">{{info.money}}<span class="weui_cell_ft with_arrow" @click="btnClick"></span></div>
        <div class="tj">
            <div class="left">
                {{info.subTitleL}}<br/>
                <b>{{info.subMoneyL}}</b>
            </div>
            <div class="right">
                {{info.subTitleR}}<br/>
                <b>{{info.subMoneyR}}</b>
            </div>
        </div>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default {
        props: ['info'],
        mounted () {
            this.$nextTick(() => {
            })
        },
        created () {
        },
        data () {
            return {}
        },
        computed: {},
        watch: {},
        methods: {
            btnClick: function () {
                if (this.info.link) {
                    utils.go(this.info.link, this.$router);
                } else {
                    this.$emit("goClick");
                }
            }
        },
        components: {}
    }
</script>

<style lang="less" scoped>
    @import '../../assets/styles/components/rui/x-withdraw-count.less';
    @import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';

</style>