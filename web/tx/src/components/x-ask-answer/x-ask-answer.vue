<template>
    <div class="askAnswer">
        <div class="list" v-for="(item,index) in listNew">
            <dl class="common ask clearfix">
                <dt>
                	<img :src="item.ask.headPic" v-if="!item.ask.isAnonymity&&!detail">
                	<img v-if="item.ask.isAnonymity&&!detail" src="../../assets/images/defaultAvatar.png"/>
                	<img v-if="imgPreview&&detail" src="../../assets/images/wen.png"/>
                </dt>
                <dd @click='toContent(item)'>
                    <p class="nickname" v-if='!detail'>{{!item.ask.isAnonymity?item.ask.nickName:"匿名用户"}}</p>
                    <!-- imgPreview默认false,则内容显示3行 无图片-->
                    <div class="content" :class="imgPreview?'':'contentHidden'">
                        {{item.ask.ask}}
                        <ul class="picList" v-if='imgPreview'>
                            <li @click="imgClick(imgSrc)" v-for="(imgSrc,imgIndex) in arrayConvert(item.ask.extends2)"
                                :style="{background:'url('+imgSrc+')',backgroundSize:'cover'}"></li>
                        </ul>
                    </div>
                </dd>
            </dl>
            <dl class="common answer clearfix">
                <dt><img :src="item.answer.headPic" @click="teacherClick(item.answer.teacherId,item)"></dt>
                <dd>
                    <p class="nickname">{{item.answer.nickName?item.answer.nickName:"匿名用户"}}</p>
                    <div class="content">
                        <div class="playBar" :class="{touting:!item.ask.isFree,waiting:!item.ask.isAnswer}"
                             @click="playBarClick(item,index)">
                            <i></i>
                            <p class="txt">{{statusValue(item.ask.status)}}</p>
                            <div class="box">
                                <div class="progress" :id="'progress'+index"></div>
                            </div>
                        </div>
                        <span class="duration" v-if="item.ask.isAnswer">{{item.answer.long|timeConvert }}</span>
                    </div>
                </dd>
            </dl>
        	
        </div>
        <slot name='inner'></slot>
        <audio preload="auto" ref="mySpeak"></audio>
        <r-popup v-model="payStatus" height="100%">
            <div class="payStatus">
                <r-messages :status="statusNum" :msg="msgText" :money="money" :desc="desc">
                    <div slot="list" class="slot" v-if="show">
                        <r-group>
                            <r-cell title="支付" value="微信安全支付"></r-cell>
                            <r-cell title="关注妈妈公开课公众号" @click.native="openBtn" is-link></r-cell>
                        </r-group>
                    </div>
                    <div slot="btn">
                        <r-button type="primary" :text="btnText" @click.native="btnClick"></r-button>
                    </div>
                </r-messages>

                <r-dialog v-model="dialogEwm" :scroll="false" :hide-on-blur="true" class="dialogEwm">
                    <div class="img-box">
                        <img src="/hbrobot/wx/calendar/course/getQrcode">
                    </div>
                </r-dialog>
            </div>
        </r-popup>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        props: ["list", "payWxmpId","imgPreview","detail"],
        data(){
            return {
                dialogEwm: false,//二维码弹窗
                show: false,
                statusNum: "",//状态图标0,1，2
                msgText: '',//文本描述
                desc: "",//描述
                money: '',//金额
                btnText: '返回',
                payStatus: false,//是否弹出框


                speakIndex: 0,//当前播放音频的索引
                answerObj: {},//当前语音回答的数据
                timed: 0,//已经播放的时长
                clickIndex: 0,//当前点击的索引
                clickObj: {},//当前点击的数据
                listNew: [],//新数组
                init: false,//页面初始化值

                playStatus: 0,//当前播放状态
                playStatusXS:0,//限时播放状态

                playStatusBtn: false,//是否支付成功
                
//              imgPreview:false,  // 内容区是否有图片

            }
        },
        mounted: function () {
            this.$nextTick(function () {
                //监听语音回答
                this.sepakListening();

            })
        },
        methods: {
            arrayConvert(str){
                console.log("111111111",str)
                if (str) {

                    return str.split(',');
                } else {
                    return [];
                }
            },
            //图片放大
            imgClick(src){
                utils.bigIMG(src);
            },
            //老师头像点击
            teacherClick(id, obj){
                this.$emit("teacher", id, obj);
            },
            //语音条点击
            playBarClick(obj, index){
                let that = this;
                if (obj.ask.status != 2) {
                    if (index != this.clickIndex && this.init) {
                        //更新上一个播放的状态
                        //this.clickObj.ask.status = 0;
                        $("#progress" + this.clickIndex).width("0%");
                        this.list.splice(0, 0);
                    }
                    this.clickObj = obj;//保存当前点击的数据
                    if (obj.ask.status == 1) {
                        this.timed = 0;//重置已播放时长
                        this.$refs.mySpeak.src = "";//清空播放资源
                        //需要付款
                        utils.ajax({
                            url: "/wx/course/askListen",
                            data: {askId: that.clickObj.ask.id},
                            success: function (res) {
                                if (res.success) {
                                    utils.wxPay(res.payConfig, {
                                        success: function (resd) {
                                            utils.ajax({
                                                url: "/wx/course/paySuccessCall",
                                                data: {
                                                    orderNo: res.orderNo
                                                },
                                                success: function (d) {
                                                    if (d.success) {
                                                        that.show = true;
                                                        that.statusNum = 1;
                                                        that.msgText = "支付成功";
                                                        that.money = utils.formatPrice(d.payMoney);
                                                        that.payStatus = true;
                                                        that.playStatusBtn = true;
                                                    } else {
                                                        that.$vux.toast.show({
                                                            text: "执行回调错误",
                                                            type: "text",
                                                        });
                                                    }

                                                }
                                            });
                                        },
                                        fail: function (resd) {
                                            that.show = false;
                                            that.statusNum = 0;
                                            that.msgText = "支付失败";
                                            that.btnText = "返回";
                                            that.payStatus = true;
                                        }
                                    });
                                }
                            }
                        });


                    } else if (obj.ask.status == 0 || obj.ask.status == 5) {
                        //付完款后
                        this.answerObj = obj.answer;
                        this.clickIndex = index;//保存当前点击索引

                        this.timed = 0;//重置已播放时长
                        this.speakIndex = 0;//重置当前播放索引
                        this.$refs.mySpeak.src = obj.answer.list[this.speakIndex].filepath;
                        this.$refs.mySpeak.play();
                        this.init = true;
                        that.playStatus = 3;
                        that.playStatusXS=0;//重置
                        if (obj.ask.status == 5) {
                            that.playStatusXS = 5;
                        }
                        this.list.splice(0, 0);
                    } else if (obj.ask.status == 3) {
                        this.$refs.mySpeak.pause();
                        that.playStatus = 4;
                        this.list.splice(0, 0);
                    } else if (obj.ask.status == 4) {
                        this.$refs.mySpeak.play();
                        that.playStatus = 3;
                        this.list.splice(0, 0);
                    }
                }

            },
            //状态
            statusValue(value){
                switch (value) {
                    case 0:
                        return "点击播放";
                        break;
                    case 1:
                        return "1元偷偷听";
                        break;
                    case 2:
                        return "等待老师答复";
                        break;
                    case 3:
                        return "正在播放";
                        break;
                    case 4:
                        return "已经暂停";
                        break;
                    case 5:
                        return "限时免费听";
                        break;
                    default:
                        ""
                }
            },
            //监听
            sepakListening() {
                let that = this;
                that.$refs.mySpeak.addEventListener('waiting', function () {
                    that.$vux.loading.show({text: "正在读取中^_^"})
                }, false);
                that.$refs.mySpeak.addEventListener("playing", function () {
                    that.$vux.loading.hide()
                }, false);
                that.$refs.mySpeak.addEventListener('canplay', function () {
                    that.$refs.mySpeak.addEventListener(
                        'timeupdate',
                        function () {
                            that.progress(that.answerObj);
                        }, false);
                }, false);
                that.$refs.mySpeak.addEventListener('ended', function () {
                    that.nextMusic(that.answerObj);//下一首
                }, false);
            },
            //时间更新触发
            progress(obj) {
                try {
                    let progress = (this.$refs.mySpeak.currentTime + this.timed) / (obj.long / 1000) * 100 + "%";
                    $("#progress" + this.clickIndex).width(progress);
                } catch (e) {
                }

            },
            //下一首
            nextMusic(obj) {
                this.timed += obj.list[this.speakIndex].duration / 1000;
                this.speakIndex++;
                if (obj.list[this.speakIndex]) {
                    this.$refs.mySpeak.src = obj.list[this.speakIndex].filepath;
                    this.$refs.mySpeak.play();
                } else {
                    this.playStatus = "end";
                    $("#progress" + this.clickIndex).width("0%");
                    this.list.splice(0, 0);
                }
            },
            //返回
            btnClick(){
                if (this.playStatusBtn) {
                    this.payStatus = false;
                    location.reload();
                } else {
                    this.payStatus = false;
                }
            },
            //关注妈妈公开课公众号
            openBtn(){
                this.dialogEwm = true;
            },
            toContent(item){   // 详情
            	this.$emit("toContent", item);
            }
        },
        watch: {
            list(value, oldValue){
                let that = this;
                value.forEach(function (obj, index) {
                    if (obj.ask.isAnswer) {
                        //已回答
                        obj.ask.status = 0;
                        if (obj.ask.isFree) {
                            obj.ask.status = 5;
                        } else {
                            if (obj.ask.extends == 1) {
                                //偷听
                                obj.ask.status = 1;
                            }
                        }
                    } else {
                        //未回答
                        obj.ask.status = 2;
                    }
                    if (that.init && index == that.clickIndex) {
                        if (that.playStatus == 3) {
                            obj.ask.status = 3;
                        } else if (that.playStatus == 4) {
                            obj.ask.status = 4;
                        } else if (that.playStatus == "end") {
                            obj.ask.status = 0;
                            if (that.playStatusXS == 5) {
                                obj.ask.status = 5;
                            }
                        }
                    }
                    that.listNew.splice(index, 1, obj);

                });

            },
        },
        computed: {},
        components: {
            "rFlexbox": require("../../components/flexbox/flexbox.vue"),
            "rFlexboxItem": require("../../components/flexbox/flexbox-item.vue"),
            "rPopup": require("../../components/popup/popup.vue"),
            "r-messages": require("../../components/x-messages/x-messages.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rGroup": require("../../components/group/group.vue"),
            "rCell": require("../../components/cell/cell.vue"),
            "rDialog": require("../../components/dialog/dialog.vue"),
        }
    }
    require("../../assets/styles/components/ask-answer.less");
</script>
