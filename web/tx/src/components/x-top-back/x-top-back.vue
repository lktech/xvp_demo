<template>
    <div class="all_top border-b" v-bind:class="{'margin':mar}">
        <div class="top_forward" v-show="!hide" @click="onClick"></div>
        <div class="top_logo" v-bind:class="{ active: hide }" @click="onClick">
            <img :src="src" alt="">
        </div>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default {
        props: ['hide', 'link', 'Call', 'logo', 'mar'],
        mounted () {
            this.$nextTick(() => {

            })
        },
        created () {
        },
        data () {
            return {
                src: this.logo ? this.logo : "http://xiaovpu.com/static/image/logo.png"
            }
        },
        computed: {},
        watch: {},
        methods: {
            onClick(){
                if (!this.hide) {
                    if (this.link) {
                        utils.go(this.link, this.$router);
                    } else if (this.Call) {
                        this.$emit('on-change');
                    } else {
                        history.go(-1)
                    }
                }
            }
        },
        components: {}
    }
</script>

<style lang="less">
    @import '../../assets/styles/components/rui/top-back.less';

    .all_top .active {
        margin-left: 10px;
    }
</style>
