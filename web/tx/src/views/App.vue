<template>
    <div id="wrapper">
        <loading v-model="loading.isshow" :text="loading.text"></loading>
        <view-box ref="viewBox">
            <transition :name="'vux-pop-' + ($store.state.direction === 'forward' ? 'in' : 'out')">
                <router-view class="router-view"></router-view>
            </transition>
        </view-box>
    </div>
</template>
<script>
    import utils from '../libs/utils.js'
    export default {
        computed: {
            loading () {
                return this.$store.state.loading
            },
            leftOptions () {
                return {
                    showBack: this.route.path !== '/'
                }
            },
            headerTransition () {
                return this.$store.state.direction === 'forward' ? 'vux-header-fade-in-right' : 'vux-header-fade-in-left'
            }
        },
        methods: {},
        components: {
            "viewBox": require('../components/view-box/view-box.vue'),
            "loading": require('../components/loading/loading.vue')
        }
    }
    require('../assets/styles/common/main.less');
    //  require('../assets/styles/common/demo.less');
</script>
<style lang="less">
    .view-box {
        padding-top: 0 !important;
    }
</style>