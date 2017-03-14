<template>
    <div v-show="show" transition="vux-popup" :style="{height:height}" class="vux-popup">
        <slot></slot>
    </div>
</template>

<script>
    import Popup from '../libs/popup.js';

    export default {
        props: {
            show: {
                type: Boolean,
                twoWay: true
            },
            height: {
                type: String,
                default: 'auto'
            },
            hideOnBlur: {
                type: Boolean,
                default: true
            }
        },
        ready () {
            const _this = this
            this.popup = new Popup({
                container: _this.$el,
                innerHTML: '',
                hideOnBlur: _this.hideOnBlur,
                onOpen (dialog) {
                    _this.fixSafariOverflowScrolling('auto')
                    _this.show = true
                },
                onClose (dialog) {
                    _this.show = false
                    if (Object.keys(window.__$vuxPopups).length >= 1) return
                    _this.fixSafariOverflowScrolling('touch')
                }
            })
            this.$overflowScrollingList = document.querySelectorAll('.vux-fix-safari-overflow-scrolling')
        },
        methods: {
            /**
             * https://github.com/airyland/vux/issues/311
             * https://benfrain.com/z-index-stacking-contexts-experimental-css-and-ios-safari/
             */
            fixSafariOverflowScrolling (type) {
                if (!this.$overflowScrollingList.length) return
                if (!/iphone/i.test(navigator.userAgent)) return
                for (let i = 0; i < this.$overflowScrollingList.length; i++) {
                    this.$overflowScrollingList[i].style.webkitOverflowScrolling = type
                }
            }
        },
        data () {
            return {
                hasFirstShow: false
            }
        },
        watch: {
            show (val) {
                if (val) {
                    this.popup.show()
                    this.$emit('on-show')
                    if (!this.hasFirstShow) {
                        this.$emit('on-first-show')
                        this.hasFirstShow = true
                    }
                } else {
                    this.$emit('on-hide')
                    this.show = false
                    this.popup.hide(false)
                }
            }
        },
        beforeDestroy () {
            this.popup.destroy()
            this.fixSafariOverflowScrolling('touch')
        }
    }
</script>



<style lang="less" scoped>
    @import "../assets/styles/components/popup.less";
</style>
