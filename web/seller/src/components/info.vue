<!-- 子组件   -->
<template>
    <div class="wrap-pd pd-bottom">
        <div class="weui_uploader">
            <div class="weui_uploader_hd weui_cell">
                <div class="weui_cell_bd weui_cell_primary pd-top">{{title}}</div>
                <div class="weui_cell_ft" v-show="max != 101">{{list.length}}/{{max}}</div>
            </div>
            <div class="weui_uploader_bd">
                <ul class="weui_uploader_files">
                    <li v-for="item in list" track-by="$index" class="weui_uploader_file"
                        :style="'background-image:url('+ item +')'"></li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default {
        props: {
            title: {
                type: String,
                default: ""
            },
            list: [],
            max: ""
        },
        data(){
            return {}
        },
        ready(){

        },
        methods: {},
        computed: {}

    }
</script>
<style lang="less">
    .pd-bottom{padding-bottom:10px;}
    .pd-top{padding-top: 5px;}
    @import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
    @import '../assets/styles/weui/widget/weui_cell/weui_uploader.less';
</style>