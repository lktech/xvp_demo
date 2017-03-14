<template>
    <div class="weui_msg">
        <div class="weui_icon_area"><i class="weui_icon_msg" :class="{'weui_icon_success':status==1?true:false,'weui_icon_warn':status==0?true:false,'weui_icon_info':status==2?true:false}"></i></div>
        <div class="weui_text_area">
            <div class="weui_msg_title">{{msg}}</div>
            <div class="weui_msg_desc">{{desc}}</div>
            <div class="money" v-if="money">￥{{money}}</div>
            <div class="money" v-if="score">{{score}}积分</div>
        </div>
        <slot name="list"></slot>
        <div class="weui_opr_area">
            <p class="weui_btn_area">
                <slot name="btn"></slot>
            </p>
        </div>
    </div>
</template>

<script>
    export default{
        props: ['status','msg','money','desc','score']
    }
</script>

<style lang="less">
    @import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
    @import '../assets/styles/weui/icon/weui_icon_font.less';
    @import '../assets/styles/weui/widget/weui_page/weui_msg.less';
    @import "../assets/styles/components/msg.less";
</style>