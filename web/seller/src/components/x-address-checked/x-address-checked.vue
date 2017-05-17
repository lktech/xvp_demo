<template>
    <div class="vux-actionsheet x-address-checked">
        <div class="weui_mask_transition" :class="{'weui_fade_toggle': show}"
             :style="{display: show ? 'block' : 'none'}" @click="show=false"></div>
        <div class="weui_actionsheet" :class="{'weui_actionsheet_toggle': show}">
            <div class="weui_actionsheet_menu">
                <div class="addTitle" v-if='title'>{{title}}</div>
                <div class="weui_cells weui_cells_checkbox" v-if="show">
                    <div class="weui_cell weui_check_label" v-for="item in formatList"
                         @click.stop="getid(item.id,{trueName:item.trueName,userName:item.userName,time:item.time,e:$event})">
                        <div class="weui_cell_hd">
                            <input type="radio" name="dizhi" class="weui_check">
                            <i class="weui_icon_checked" :class="{'active':status,'active':defaultStatus==item.id}"></i>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary" @click="goUrl(item.url)">
                            <p class="trueName">{{item.trueName}}</p>
                            <p class="userName">{{item.userName}}</p>
                            <p class="time">{{item.time}}</p>
                        </div>
                        <div class="weui_cell_ft with_arrow" v-if="item.url?true:false"></div>
                    </div>
                </div>
                <div v-if="maxhide && formatList.length > maxhide ? false:true">
                    <div class="wrap">
                        <r-button :type="type" @click.native="addBtn" :text="btnTxt" :size="size?size:'small-block'"
                                  :disabled="disabled"></r-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default {

        data() {
            return {
                show: false,
                formatList: []
            }
        },
        props: {
            value: Boolean,
            revealtitle: true,
            showCancel: Boolean,
            cancelText: {
                type: String,
                default: '取消'
            },
            address: Array,
            type: {
                type: String,
                default: "default"
            },
            status: false,
            mstyle: false,
            btnTxt: {
                type: String,
                default: "新增收货地址"
            },
            title: String,
            btnSkin: String,
            maxhide: [String, Number],
            size: [String],
            disabled: {
                type: Boolean,
                default: false
            },
            arrow: Boolean,
            defaultStatus: [String, Number], //默认选中
            format: {
                type: Object,
                default() {
                    return {
                        "notneed": true
                    }
                }
            }

        },

        watch: {
            show(val) {
                this.$emit('input', val)
                if (val) {
                    this.fixIos(-1)
                } else {
                    setTimeout(() => {
                        this.fixIos(100)
                    }, 200)
                }
            },
            value(val) {
                this.show = val
            },
            address(newList) {
                //this.formatList = this.formatList.concat(this.formatFunc(newList));
                this.formatList = this.formatFunc(newList);
            }
        },
        beforeDestroy() {
            this.fixIos(100)
        },

        methods: {
            formatFunc: function (list) {
                var that = this;
                var formats = [];
                if (that.format.notneed) {
                    return list;
                } else {
                    let id = that.format.id,
                        trueName = that.format.trueName,
                        userName = that.format.userName,
                        time = that.format.time,
                        url = that.format.url;
                    list.forEach(function (item, index) {
                        var fitem = {};
                        fitem.id = item[id];
                        fitem.trueName = item[trueName];
                        fitem.userName = item[userName];
                        fitem.time = item[time];
                        fitem.url = item[url];
                        formats.push(fitem);
                    });
                }
                return formats;
            },
            getid(id, obj) {
                $(obj.e.target).parents(".weui_check_label").find("i").addClass("active").parents(".weui_check_label").siblings().find("i").removeClass("active");
                this.$emit("get-addressid", id, obj);
            },
            newAdd() {
                this.$dispatch("newAdd");
                this.show = false
            },
            goUrl(url) {
                if (url) {
                    if (url.callback) {
                        this.$emit("callback", url);
                    } else {
                        utils.go(url, this.$router);
                    }
                }
            },
            addBtn() {
                this.$emit("address-add");

            },
            fixIos(zIndex) {
                if (this.$tabbar && /iphone/i.test(navigator.userAgent)) {
                    this.$tabbar.style.zIndex = zIndex
                }
            }
        },
        components: {
            "rButton": require('../button/button.vue'),
        }
    }
</script>

<style lang="less">
    @import '../../assets/styles/components/weui/widget/weui_tips/weui_mask.less';
    @import '../../assets/styles/components/weui/widget/weui_tips/weui_actionsheet.less';
    @import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';
    @import '../../assets/styles/components/weui/widget/weui_cell/weui_check.less';
    @import "../../assets/styles/components/rui/action-sheet.less";
    @import "../../assets/styles/components/rui/address-checked.less";
</style>