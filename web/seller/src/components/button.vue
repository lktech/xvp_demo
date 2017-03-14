<template>
    <a href="javascript:;" @click="buttonClick" v-bind:class="clazz" :disabled="disabled">{{text?text:'提交'}}</a>
</template>

<script>
    import utils from '../libs/utils.js';
    export default {
        props: ['link','text','type','size','code','disabled','noradius'],
        data (){
            return {

            }
        },
        computed: {
            clazz () {
                return {
                    'weui_btn_disabled': this.disabled,
                    'weui_btn': true,
                    'weui_btn_primary': !this.type || this.type == "primary",
                    'weui_btn_warn': this.type == "warn",
                    'weui_btn_default': this.type == "default",
                    'weui_btn_org': this.type == "org",
                    'weui_btn_blue': this.type == "blue",
                    'weui_btn_blue2': this.type == "blue2",
                    'weui_btn_plain_primary': this.type == "plain_primary",
                    'weui_btn_plain_default': this.type == "plain_default",
                    'weui_btn_small': this.size == "small",
                    'weui_btn_smaller': this.size == "smaller",
                    'weui_btn_block': this.size == "block",
                    'weui_btn_code': this.code == "code",
                    'weui_btn_noradius': this.noradius
                }
            }
        },
        methods: {
            buttonClick(){
                if(!this.disabled){
                    utils.go(this.link, this.$router);
                }

            }
        }
    }
</script>
<style lang="less" scoped>
    @import "../assets/styles/weui/widget/weui_button/weui_button.less";
	.weui_btn { overflow: inherit !important;}
</style>