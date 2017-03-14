<template>
    <div class="vux-actionsheet">
        <div class="weui_mask_transition" :class="{'weui_fade_toggle': show}"
             :style="{display: show ? 'block' : 'none'}" @click="show=false"></div>
        <div class="weui_actionsheet" :class="{'weui_actionsheet_toggle': show}">
            <div class="weui_actionsheet_menu">
                <div class="addTitle" v-if='title'>{{title}}</div>
                <div class="weui_cells weui_cells_checkbox">
                    <div class="weui_cell weui_check_label" v-for="item in address"
                         @click.stop="getid(item.id,{trueName:item.trueName,userName:item.userName,time:item.time,e:$event})">
                        <div class="weui_cell_hd" v-if="mstyle?true:false">
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
                <div v-if="maxhide?address.length<maxhide:true">
                    <div class="vux-actionsheet-gap" style="background-color: #fff"></div>
                    <div class="wrap">
                        <c-button :type="type" @click="addBtn" :text="btnTxt" :size="size" :disabled="disabled"></c-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default {
        data(){
            return {}
        },
        props: {
            revealtitle: true,
            show: {
                type: Boolean,
                required: true,
                twoWay: true
            },
            showCancel: Boolean,
            cancelText: {
                type: String,
                default: '取消'
            },
            address: {
                type: Array,
                default: []
            },
            type: String,
            status: false,
            mstyle: false,
            btnTxt: {
                type: String,
                default: "新增收货地址"
            },
            title: String,
            btnSkin: String,
            maxhide: [String, Number],
            size:[String],
            disabled:Boolean,
            arrow:Boolean,
            defaultStatus:[String,Number],//默认选中

        },
        ready(){

        },
        methods: {
            getid(id, obj){
                $(obj.e.target).parents(".weui_check_label").find("i").addClass("active").parents(".weui_check_label").siblings().find("i").removeClass("active");
                this.$emit("get-addressid", id, obj);
            },
            newAdd(){
                this.$dispatch("newAdd");
                this.show = false;
            },
            goUrl(url){
                if (url) {
                    if (url.callback) {
                        this.$emit("callback", url);
                    } else {
                        utils.go(url, this.$router);
                    }
                }
            },
            addBtn(){
                this.$emit("address-add");

            }
        },
        components: {
            "cButton": require('../components/button.vue'),
        }
    }
</script>

<style lang="less">
    @import '../assets/styles/weui/widget/weui_tips/weui_mask.less';
    @import '../assets/styles/weui/widget/weui_tips/weui_actionsheet.less';
    @import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
    @import '../assets/styles/weui/widget/weui_cell/weui_check.less';
    @import "../assets/styles/components/action-sheet.less";
    @import "../assets/styles/components/address.less";
</style>
