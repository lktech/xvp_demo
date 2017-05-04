<template>
    <div class="x-msg-list">
        <r-scroller :lockX="true" :bounce="true" height="500px" v-if="sss">
            <ul>
                <li v-for="(item,index) in data">
                    <div class="headPic"><img :src="avatar">
                    </div>
                    <div class="msgWrap" v-if="item.contentType!='voice'">
                        <i></i>
                        <div class="msgCon">
                            <div v-if="item.contentType=='text'" v-html="item.content"></div>
                            <div v-if="item.contentType=='image'"><img :src="item.filepath"
                                                                       @click="imgClick(item.filepath)"/>
                            </div>
                        </div>
                    </div>
                    <div v-else>
                        <div class="playBar" @click="playBarClick(item,index)">
                            <i></i>
                            <p class="txt">{{statusValue(item.status)}}</p>
                            <div class="box">
                                <div class="progress" :id="'progress_'+index"></div>
                            </div>
                        </div>
                        <span class="duration">{{item.duration |timeConvert}}</span>
                    </div>
                </li>
            </ul>
        </r-scroller>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        props: ["data", "avatar", "height"],
        data(){
            return {
                sss: false,
                msgList: [],//消息列表
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;
                setTimeout(function () {
                    that.sss = true;
                }, 1000)
            })
        },
        methods: {
            imgClick(src){
                utils.bigIMG(src);
            },
            //语音播放
            playBarClick(obj, index){
//                if (index != this.clickIndex) {
//                    //更新上一个播放的状态
//                    this.updatePlayStatus(0);
//                    $("#progress_" + this.speakIndex).width("0%");
//                }
//                if (obj.status == 0) {
//                    $("#progress_" + this.speakIndex).width("0%");
//                    this.updatePlayStatus(0);
//                    this.speakIndex = index;
//                    this.clickIndex = index;//保存当前点击索引
//                    this.$refs.mySpeak.src = obj.filepath;
//                    this.$refs.mySpeak.play();
//                } else if (obj.status == 1) {
//                    this.updatePlayStatus(2);
//                    this.$refs.mySpeak.pause();
//                } else if (obj.status == 2) {
//                    this.updatePlayStatus(1);
//                    this.$refs.mySpeak.play();
//                }
            },
            //状态
            statusValue(value){
                switch (value) {
                    case 0:
                        return "点击播放";
                        break;
                    case 1:
                        return "正在播放";
                        break;
                    case 2:
                        return "已经暂停";
                        break;
                    default:
                        ""
                }
            },
        },
        computed: {},
        watch: {
            data: function (arry, oldVal) {
                arry.forEach(function (obj, i) {
                    if (obj.contentType == "voice") {
                        obj.status = 0;
                    }
                });
                console.log(arry)
            }
        },
        components: {
            //"elDatePicker": require("../../components/date-picker/date-picker.vue"),
            "rScroller": require('../scroller/scroller.vue'),
        }
    }
    require("../../assets/styles/components/public.less");
</script>
<style lang="less">
    @import "../../assets/styles/components/x-msg-list.less";
</style>
