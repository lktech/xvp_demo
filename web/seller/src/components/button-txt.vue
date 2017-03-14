<template>
    <div>
        <div class="button-txt border-t" :class="{fixed:fixed==''?true:false,block:size=='block'?true:false}">
            <div class="button-txt-l">
                <b>{{name}}</b><span>￥{{money}}</span>
            </div>
            <div class="button-txt-r">
                <c-button :type="type" @click="btnClick(money)" noradius="true" :size="size=='block'?'block':false" :text="text"
                          :disabled="dis"></c-button>
            </div>
        </div>
    </div>
</template>
<script>
    import utils from '../libs/utils.js';
    export default {
        props: ["text", "money", "fixed", "size", "name", "disabled", "link", "type"],
        data (){
            return {
                style: this.type,
                dis: false
            }
        },
        ready(){
        },
        methods: {
            btnClick(money){
                if (this.disabled!=true) {
                    if (this.link) {
                        utils.go(this.link, this.$router);
                    } else {
                        this.$emit("btnclick", money);
                    }
                }
            }
        },
        computed: {
            dis: function () {
                if (this.disabled) {
                    this.type="default";
                    return true;
                } else {
                    return false;
                }
            }
        },
        components: {
            "cButton": require('../components/button.vue'),
        }
    }

</script>

<style lang="less">
    @import "../assets/styles/components/button-txt.less";
</style>