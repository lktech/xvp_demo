<template>
    <div class="txBind">
        <c-top-back></c-top-back>
        <r-group>
            <r-cell title="手机号" value="15210903258"></r-cell>
            <r-input title="验证码" :max="6" v-model="yzm" class="weui_vcode" placeholder="请输入手机验证码">
                <r-button slot="right" type="primary" class="sendYzmBtn" :disabled="sendYzmBtn"
                          @click.native="sendCodeBtn">{{txt}}
                </r-button>
            </r-input>
        </r-group>
        <div class="agreement"><label><input type="radio" value="1" v-model="radioValue"/>我已阅读并同意</label><a
                href="javascript:;" @click="agreementClick">《支付协议》</a></div>
        <div class="btn">
            <r-button type="primary" text="启用并绑定" @click.native="bindBtnClick" :disabled="btnDisabled"></r-button>
        </div>
        <r-popup v-model="agreement" >
            <div class="agreementCon" :style="{height:h}">
                <div class="desc">
                    本服务由大连领客科技有限公司向您提供。
                    <p>1.您选择使用的本服务应提供个人的支付信息，且需保证您所提供的所有信息是真实的、合法的、正确的、完整的。本协议所指支付信息是指您使用本服务时需要提供的个人信息，包括但不限于账户名、个人姓名、身份证号码、银行卡号等。</p>
                    <p>2.为了提供本服务的技术需要，我们有权采集、获取或在系统内保存您的部分支付信息。我们将严格确保您的个人信息和支付信息的安全。</p>
                    <p>3.您如使用本服务进行支付的，应当在确认支付金额后输入密码进行支付。您认可和同意：输入密码即视为您确认交易和交易金额并已不可撤销地向我们的支付系统发出指令，我们有权根据您的指令委托银行或第三方从您绑定的银行卡中将您确认的交易资金划扣给收款人。您不应以非本人意愿交易或其他任何原因要求退款或承担其他责任。</p>
                    <p>4.您需对使用本服务过程中发出的所有指令的真实性、有效性承担全部责任,我们依照您的指令进行操作的一切风险由您自行承担。</p>
                    <p>5.您使用本服务所涉及的账户的使用记录数据、交易金额数据等均以我们系统平台记录的数据为准。</p>
                    <p>6.您知晓，您应妥善保管银行卡、卡号、密码以以及您的账号、密码、数字证书等与银行卡或与您的支付账户有关的一切信息。如您遗失银行卡、泄露账户密码或相关信息的，您应及时通知发卡行及/或我们，以减少可能发生的损失。因泄露密码、数字证书、丢失银行卡等导致的损失需由您自行承担。</p>
                    <p>7.在我们有合理理由怀疑您提供的资料错误、不实、过时或不完整的情况下，本公司有权暂停或终止向您提供部分或全部本服务，您将承担因此产生的一切责任，公司对此不承担任何责任。 </p>
                    <p>8.如果您未按上述要求进行操作，我们有权暂停或终止向您提供部分或全部本服务，您将承担因此产生的一切责任，本公司对此不承担任何责任。</p>
                    <p>9.如您发现有他人冒用或盗用您的账户及密码或任何其他未经合法授权之情形时，应立即以书面方式通知本公司并要求本公司暂停本服务。我们将积极响应您的要求，但您理解，对您的要求采取行动需要合理期限，在此之前，本公司对已执行的指令及(或)所导致的您的损失不承担任何责任。</p>
                    <p>10.若您的支付信息变更而您未及时更新资料，导致本服务无法提供或提供时发生任何错误，您不得将此作为取消交易、拒绝付款的理由，您将承担因此产生的一切后果，本公司不承担任何责任。</p>
                    <p>11.您使用本服务时同意并认可，可能由于银行本身系统问题、银行相关作业网络连线问题或其他不可抗拒因素，造成本服务无法提供。您确保您所输入的您的资料无误，如果因资料错误造成本公司于上述异常状况发生时，无法及时通知您相关交易后续处理方式的，本公司不承担任何损害赔偿责任。</p>
                    <p>12.基于运行和交易安全的需要，本公司有权暂停提供或者限制本服务部分功能,或提供新的功能，在减少、增加或者变化任何功能时，只要您仍然使用本服务，表示您仍然同意本协议或者变更后的协议。 </p>
                    <p>13.本公司有权随时对本协议内容进行单方面的变更，并以公告的方式予以公布，无需另行单独通知您；若您在本协议内容公告变更后继续使用本服务的，表示您已充分阅读、理解并接受修改后的协议内容，也将遵循修改后的协议内容使用本服务；若您不同意修改后的协议内容，您应停止使用本服务。</p>
                    <p>14.本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国法律，没有相关法律规定的，参照通用国际商业惯例和（或）行业惯例。 </p>
                    <p>15.本协议产生之争议，双方应当友好协商解决，无法达成一致的，以本公司所在地法院为一审管辖法院。</p>
                </div>
                <div class="submit">
                    <r-button type="primary" @click.native="agreement=false;radioValue=1">我同意</r-button>
                </div>
            </div>
        </r-popup>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default{
        data(){
            return {
                yzm: "",
                sendYzmBtn: false,
                txt: "发送验证码",
                btnDisabled: true,
                radioValue: "",
                agreement: false,
            }
        },
        computed:{
            h(){
                return $(window).height()+"px";
            }
        },
        mounted: function () {
            this.$nextTick(function () {

            })
        },
        methods: {
            sendCodeBtn(){
                let that = this;
                var second = 59;
                that.sendYzmBtn = true;
                utils.ajax({
                    url: "/1",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            let json = res.result;
                            that.txt = "已发送(" + second + ")秒";
                            var timer = setInterval(function () {
                                if (second > 1) {
                                    second--;
                                    that.txt = "已发送(" + second + ")秒";
                                } else {
                                    clearInterval(timer);
                                    that.txt = '重新发送';
                                    that.sendYzmBtn = false;
                                }
                            }, 1000);
                        } else {
                            that.sendYzmBtn = false;
                            that.$vux.alert.show(res.code);
                        }
                    }
                });

            },
            bindBtnClick(){
                let that = this;
                utils.ajax({
                    url: "/1",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.$vux.toast.show('提交成功');
                            setTimeout(function () {
                                //
                            }, 2000)
                        } else {
                            that.$vux.alert.show(res.code);
                        }
                    }
                });
            },
            //支付协议
            agreementClick(){
                this.agreement = true;
            }
        },
        watch: {
            yzm(val, oldVal){
                if (val.length == 6 && this.radioValue == 1) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            radioValue(val, oldVal){
                if (this.yzm.length == 6 && val == 1) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            }
        },
        components: {
            "cTopBack": require('../components/x-top-back/x-top-back.vue'),
            "rCell": require("../components/cell/cell.vue"),
            "rGroup": require("../components/group/group.vue"),
            "rInput": require("../components/input/input.vue"),
            "rButton": require("../components/button/button.vue"),
            "rPopup": require("../components/popup/popup.vue"),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../assets/styles/views/personal";
</style>
