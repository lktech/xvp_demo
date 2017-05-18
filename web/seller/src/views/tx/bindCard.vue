<template>
    <div class="bindCard">
        <c-top-back></c-top-back>
        <r-group>
            <r-cell :title="defaultTitle" class="cellTitle"></r-cell>
            <r-cell title="发卡银行" :value="cardBank" is-link @click.native="cardBankClick"></r-cell>
            <r-cell title="卡所在地" :value="provinceName +' '+cityName" is-link @click.native="cityCodeClick"></r-cell>
        </r-group>
        <r-group>
            <r-cell title="开户行" :value="openingBank" is-link @click.native="openingBankClick" class="wd"></r-cell>
            <r-input title="银行卡" :max="21" v-model="cardNumber" placeholder="请输入银行卡号"></r-input>
        </r-group>
        <r-group v-if="type==1">
            <r-input title="真实姓名" :max="20" v-model="trueName" placeholder="请输入真实姓名"></r-input>
            <r-input title="身份证号" :max="18" v-model="idCode" placeholder="请输入身份证号码"></r-input>
        </r-group>
        <r-group v-else>
            <r-input title="企业名称" :max="30" v-model="companyName" placeholder="请输入企业名称"></r-input>
            <r-input title="营业执照" :max="18" v-model="license" placeholder="请输入营业执照"></r-input>
        </r-group>
        <div class="btn">
            <r-button type="primary" text="下一步" @click.native="btnClick" :disabled="btnDisabled"></r-button>
        </div>
        <!--银行-->
        <r-address-checked :defaultStatus="cardBankCode"
                           v-model="showBank"
                           :address="bankList"
                           @get-addressid="getBank"
                           @address-add="showBank=false" btn-txt="取消" title="提现银行"></r-address-checked>
        <!--支行-->
        <r-address-checked
                :defaultStatus="openingBankCode"
                v-model="showSubBank"
                :address="subBankList"
                @get-addressid="getSubBank"
                @address-add="showSubBank=false" btn-txt="取消" title="选择支行"></r-address-checked>
        <!--省-->
        <r-popup v-model="showProvince" :hide-on-blur="false">
            <div class="popupArea">
                <h3>请选择省 <a href="javascript:;" class="close" @click="showProvince=false">关闭</a></h3>
                <div class="areaContent" :style="{maxHeight:maxH,overflow: 'auto'}">
                    <r-group v-if="init">
                        <r-radio :options="province" :value="provinceValue" @on-change="changeProvince"></r-radio>
                    </r-group>
                </div>
            </div>
        </r-popup>
        <!--市-->
        <r-popup v-model="showCity" :hide-on-blur="false">
            <div class="popupArea">
                <h3>请选择市<a href="javascript:;" class="close" @click="showCity=false">关闭</a></h3>
                <div class="areaContent" :style="{maxHeight:maxH,overflow: 'auto'}">
                    <r-group v-if="init">
                        <r-radio :options="city" :value="cityValue" @on-change="changeCity"></r-radio>
                    </r-group>
                </div>
            </div>
        </r-popup>

        <!--提现绑定-->
        <r-popup v-model="showTxBind" height="100%">
            <div class="txBind">
                <c-top-back :Call="true" @on-change="showTxBind=false"></c-top-back>
                <r-group>
                    <r-cell title="手机号" :value="mobile"></r-cell>
                    <r-input title="验证码" :max="6" v-model="yzm" class="weui_vcode" placeholder="请输入手机验证码">
                        <r-button slot="right" type="primary" class="sendYzmBtn" :disabled="sendYzmBtn"
                                  @click.native="sendCodeBtn">{{txt}}
                        </r-button>
                    </r-input>
                </r-group>
                <div class="agreement"><label><input type="radio" value="1" v-model="radioValue"/>我已阅读并同意</label><a
                        href="javascript:;" @click="agreementClick">《支付协议》</a></div>
                <div class="btn">
                    <r-button type="primary" text="启用并绑定" @click.native="bindBtnClick"
                              :disabled="btnDisabledBind"></r-button>
                </div>
                <r-popup v-model="agreement">
                    <div class="agreementCon" :style="{height:h}">
                        <div class="desc">
                            本服务由大连领客科技有限公司向您提供。
                            <p>
                                1.您选择使用的本服务应提供个人的支付信息，且需保证您所提供的所有信息是真实的、合法的、正确的、完整的。本协议所指支付信息是指您使用本服务时需要提供的个人信息，包括但不限于账户名、个人姓名、身份证号码、银行卡号等。</p>
                            <p>2.为了提供本服务的技术需要，我们有权采集、获取或在系统内保存您的部分支付信息。我们将严格确保您的个人信息和支付信息的安全。</p>
                            <p>
                                3.您如使用本服务进行支付的，应当在确认支付金额后输入密码进行支付。您认可和同意：输入密码即视为您确认交易和交易金额并已不可撤销地向我们的支付系统发出指令，我们有权根据您的指令委托银行或第三方从您绑定的银行卡中将您确认的交易资金划扣给收款人。您不应以非本人意愿交易或其他任何原因要求退款或承担其他责任。</p>
                            <p>4.您需对使用本服务过程中发出的所有指令的真实性、有效性承担全部责任,我们依照您的指令进行操作的一切风险由您自行承担。</p>
                            <p>5.您使用本服务所涉及的账户的使用记录数据、交易金额数据等均以我们系统平台记录的数据为准。</p>
                            <p>
                                6.您知晓，您应妥善保管银行卡、卡号、密码以以及您的账号、密码、数字证书等与银行卡或与您的支付账户有关的一切信息。如您遗失银行卡、泄露账户密码或相关信息的，您应及时通知发卡行及/或我们，以减少可能发生的损失。因泄露密码、数字证书、丢失银行卡等导致的损失需由您自行承担。</p>
                            <p>7.在我们有合理理由怀疑您提供的资料错误、不实、过时或不完整的情况下，本公司有权暂停或终止向您提供部分或全部本服务，您将承担因此产生的一切责任，公司对此不承担任何责任。 </p>
                            <p>8.如果您未按上述要求进行操作，我们有权暂停或终止向您提供部分或全部本服务，您将承担因此产生的一切责任，本公司对此不承担任何责任。</p>
                            <p>
                                9.如您发现有他人冒用或盗用您的账户及密码或任何其他未经合法授权之情形时，应立即以书面方式通知本公司并要求本公司暂停本服务。我们将积极响应您的要求，但您理解，对您的要求采取行动需要合理期限，在此之前，本公司对已执行的指令及(或)所导致的您的损失不承担任何责任。</p>
                            <p>
                                10.若您的支付信息变更而您未及时更新资料，导致本服务无法提供或提供时发生任何错误，您不得将此作为取消交易、拒绝付款的理由，您将承担因此产生的一切后果，本公司不承担任何责任。</p>
                            <p>
                                11.您使用本服务时同意并认可，可能由于银行本身系统问题、银行相关作业网络连线问题或其他不可抗拒因素，造成本服务无法提供。您确保您所输入的您的资料无误，如果因资料错误造成本公司于上述异常状况发生时，无法及时通知您相关交易后续处理方式的，本公司不承担任何损害赔偿责任。</p>
                            <p>
                                12.基于运行和交易安全的需要，本公司有权暂停提供或者限制本服务部分功能,或提供新的功能，在减少、增加或者变化任何功能时，只要您仍然使用本服务，表示您仍然同意本协议或者变更后的协议。 </p>
                            <p>
                                13.本公司有权随时对本协议内容进行单方面的变更，并以公告的方式予以公布，无需另行单独通知您；若您在本协议内容公告变更后继续使用本服务的，表示您已充分阅读、理解并接受修改后的协议内容，也将遵循修改后的协议内容使用本服务；若您不同意修改后的协议内容，您应停止使用本服务。</p>
                            <p>14.本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国法律，没有相关法律规定的，参照通用国际商业惯例和（或）行业惯例。 </p>
                            <p>15.本协议产生之争议，双方应当友好协商解决，无法达成一致的，以本公司所在地法院为一审管辖法院。</p>
                        </div>
                        <div class="submit">
                            <r-button type="primary" @click.native="agreement=false;radioValue=1">我同意</r-button>
                        </div>
                    </div>
                </r-popup>
            </div>
        </r-popup>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        data(){
            return {
                type: this.$route.query.type,
                defaultTitle: this.$route.query.type == 1 ? "个人账户提现账号绑定" : "企业账户提现账号绑定",
                provinceName: "",//省
                provinceCode: "",//省编码
                cityName: "",//城市
                cityCode: "",//城市编码
                cardBank: "",//银行
                cardBankCode: "",//银行编码
                cardAddress: "",
                openingBank: "",//支行
                openingBankCode: "",//支行编码
                cardNumber: "",
                trueName: "",
                idCode: "",
                bankList: [],
                btnDisabled: true,
                showBank: false,
                showSubBank: false,
                subBankList: [],

                //企业
                license: "",
                companyName: "",

                //省市
                showProvince: false,
                province: [],//省列表
                provinceValue: {},//选中的省
                showCity: false,
                city: [],//市列表
                cityValue: {},//选中的市
                proStatus: 0,
                proNum: 0,


                //提现绑定
                mobile: localStorage.mobile,
                showTxBind: false,
                yzm: "",
                sendYzmBtn: false,
                txt: "发送验证码",
                btnDisabledBind: true,
                radioValue: "",
                agreement: false,
                sn: "",
                showId: "",
                init: false,

            }
        },
        computed: {
            maxH(){
                return $(window).height() * 0.5 + "px";
            },
            h(){
                return $(window).height() + "px";
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;
                //绑定实名认证
                that.trueName = that.$route.query.name;
                that.idCode = that.$route.query.num;

                that.companyName = that.$route.query.company;
                that.license = that.$route.query.buslince;

                //银行列表
                utils.ajax({
                    url: "/seller/account/getBankInfo",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.bankList = [];
                            res.result.forEach(function (obj, i) {
                                that.bankList.push({id: obj.bankcode, trueName: obj.bankname});
                            });
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                });

                //省列表
                utils.ajax({
                    url: "/seller/account/getBankCity",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.province = [];
                            res.result.forEach(function (obj, i) {
                                that.province.push({key: obj.code, value: obj.name});
                            })
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                });

                //信息回显
                if (this.$route.query.rzStatus == "rzsb") {

                    that.init = true;
                    utils.ajax({
                        url: "/seller/account/getStoreBankCard",
                        success: function (res) {
                            if (res.code == "SUCCESS") {
                                let json = res.result;
                                that.showId = json.id;
                                that.cardBank = json.bank_name;
                                that.cardBankCode = json.bank_code;
                                //json.bank_province_name = "北京市";
                                that.provinceName = json.bank_province_name;
                                that.provinceCode = json.bank_province_code;
                                that.provinceValue = {key: that.provinceCode, value: that.provinceName};

                                //json.bank_city_name = "北京市";
                                that.cityName = json.bank_city_name;
                                that.cityCode = json.bank_city_code;
                                that.cityValue = {key: that.cityCode, value: that.cityName};

                                that.openingBank = json.bank_branch_name;
                                that.openingBankCode = json.bank_branch_code;

                                that.cardNumber = "";//银行卡号置空
                                that.companyName = json.company_name;
                                that.license = json.buslince;

                                //渲染支行
                                utils.ajax({
                                    url: "/seller/account/getBankInfo",
                                    data: {citycode: that.cityCode, bankcode: that.cardBankCode},
                                    success: function (res) {
                                        if (res.code == "SUCCESS") {
                                            that.subBankList = [];
                                            res.result.forEach(function (obj, i) {
                                                that.subBankList.push({id: obj.bankcode, trueName: obj.bankname});
                                            });
                                        } else {
                                            that.$vux.alert.show(res.message);
                                        }
                                    }
                                })
                            } else {
                                that.$vux.alert.show(res.message);
                            }
                        }
                    });
                }
            })
        },
        methods: {
            //发卡银行
            cardBankClick(){
                this.proNum++;
                this.showBank = true;
                this.init = false;
            },
            //卡所在地
            cityCodeClick(){
                if (this.cardBank) {
                    this.showProvince = true;
                } else {
                    this.$vux.alert.show("请选择发卡银行");
                }
            },
            //支行点击
            openingBankClick(){
                if (this.cityName) {
                    this.showSubBank = true;
                } else {
                    this.$vux.alert.show("请选择卡所在地");
                }

            },
            //银行点击
            getBank(id, obj){
                let that = this;
                this.cardBankCode = id;
                this.cardBank = obj.trueName;
                this.showBank = false;
//                this.proNum++;
//                if (this.proNum > 1) {
//                    this.provinceValue = {};
//                    this.cityValue = {};
//                }

                //重置所在地和支行

                this.provinceName = "";
                this.provinceCode = "";


                this.cityName = "";
                this.cityCode = "";
                this.openingBank = "";//支行
                this.openingBankCode = "";//支行编码
            },
            //支行点击
            getSubBank(id, obj){
                this.openingBankCode = id;
                this.openingBank = obj.trueName;
                this.showSubBank = false;
            },
            //验证个人
            check(){
                if (this.cardBank != "" && this.cityCode != "" && this.openingBank != "" && /^\d{16,21}$/.test(this.cardNumber) && this.trueName.indexOf(" ") == -1 && this.trueName != "" && /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(this.idCode)) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            //验证企业
            checkCompany(){
                if (this.cardBank != "" && this.cityCode != "" && this.openingBank != "" && /^\d{16,21}$/.test(this.cardNumber) && /^[0-9a-zA-Z]{15,18}$/.test(this.license) && this.license != "" && this.companyName.indexOf(" ") == -1 && this.companyName != "") {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            //下一步
            btnClick(){
                let that = this;
                that.showTxBind = true;
            },
            //省选择
            changeProvince (obj) {
                this.proStatus++;
                let that = this;
                that.provinceName = obj.value;
                that.provinceCode = obj.key;
                that.cityName = "";
                if (this.$route.query.rzStatus == "" || this.$route.query.rzStatus == undefined) {
                    this.proStatus = 2;
                }
                if (this.proStatus > 1) {
                    //渲染市
                    utils.ajax({
                        url: "/seller/account/getBankCity",
                        data: {citycode: obj.key},
                        success: function (res) {
                            if (res.code == "SUCCESS") {
                                that.city = [];
                                res.result.forEach(function (obj, i) {
                                    that.city.push({key: obj.code, value: obj.name});
                                });
                                that.showProvince = false;
                                that.showCity = true;
                            } else {
                                that.$vux.alert.show(res.message);
                            }
                        }
                    })
                }
            },
            //市选择
            changeCity(obj){
                let that = this;
                this.cityName = obj.value;
                this.showCity = false;
                this.cityCode = obj.key;
                //渲染开户行
                utils.ajax({
                    url: "/seller/account/getBankInfo",
                    data: {citycode: obj.key, bankcode: that.cardBankCode},
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.subBankList = [];
                            res.result.forEach(function (obj, i) {
                                that.subBankList.push({id: obj.bankcode, trueName: obj.bankname});
                            });
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                })
            },

            //提现绑定
            checkTxBind(){
                if (this.yzm.length == 6 && this.radioValue == 1) {
                    this.btnDisabledBind = false;
                } else {
                    this.btnDisabledBind = true;
                }
            },
            sendCodeBtn(){
                let that = this;
                var second = 59;
                that.sendYzmBtn = true;
                utils.ajax({
                    url: "/seller/seller/verify",
                    data: {phone: that.mobile},
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.sn = res.result.sn;
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
                            that.$vux.alert.show(res.message);
                        }
                    }
                });

            },
            bindBtnClick(){
                let that = this;
                let param = {
                    "user_name": that.trueName,
                    "certificate_number": that.idCode,
                    "card_no": that.cardNumber,
                    "bank_code": that.cardBankCode,
                    "bank_name": that.cardBank,
                    "bank_branch_code": that.openingBankCode,
                    "bank_branch_name": that.openingBank,
                    "bank_province_code": that.provinceCode,
                    "bank_city_code": that.cityCode,
                    "company_name": that.companyName,
                    "buslince": that.license,
                    "phone": that.mobile,
                    "sn": that.sn,
                    "verfiy_code": that.yzm,
                    bank_province_name: that.provinceName,
                    bank_city_name: that.cityName,
                    certificate_type: 0,
                    buslince_pic: "",
                };
                if (that.$route.query.rzStatus == "rzsb") {
                    param.id = that.showId;
                    utils.ajax({
                        url: "/seller/account/updateCompanyStoreBankCard",
                        data: param,
                        success: function (res) {
                            if (res.code == "SUCCESS") {
                                that.$vux.toast.show('提交成功');
                                setTimeout(function () {
                                    utils.go("/tx/balance", that.$router);
                                }, 2000)
                            } else {
                                if (res.code == "verify_code_error") {
                                    that.$vux.alert.show(res.message);
                                } else {
                                    that.$vux.alert.show({
                                        content: res.message,
                                        onHide(){
                                            that.showTxBind = false;
                                        }
                                    });
                                }
                            }
                        }
                    })
                } else {
                    utils.ajax({
                        url: "/seller/account/addStoreBankCard",
                        data: param,
                        success: function (res) {
                            if (res.code == "SUCCESS") {
                                that.$vux.toast.show('提交成功');
                                setTimeout(function () {
                                    utils.go("/tx/balance", that.$router);
                                }, 2000)
                            } else {
                                if (res.code == "verify_code_error") {
                                    that.$vux.alert.show(res.message);
                                } else {
                                    that.$vux.alert.show({
                                        content: res.message,
                                        onHide(){
                                            that.showTxBind = false;
                                        }
                                    });
                                }
                            }
                        }
                    })
                }

            },
            //支付协议
            agreementClick(){
                this.agreement = true;
            }
        },
        watch: {
            showBank(val){
                if (!val) {
                    this.init = true;
                }
            },
            cardBank(val, oldVal){
                if (this.type == 1) {
                    this.check();
                } else {
                    this.checkCompany();
                }
            },
            cityCode(val, oldVal){
                if (this.type == 1) {
                    this.check();
                } else {
                    this.checkCompany();
                }
            },
            openingBank(val, oldVal){
                if (this.type == 1) {
                    this.check();
                } else {
                    this.checkCompany();
                }
            },
            cardNumber(val, oldVal){
                if (this.type == 1) {
                    this.check();
                } else {
                    this.checkCompany();
                }
            },
            trueName(val, oldVal){
                this.check();
            },
            idCode(val, oldVal){
                this.check();
            },
            companyName(val, oldVal){
                this.checkCompany();
            },
            license(val, oldVal){
                this.checkCompany();
            },
            //提现绑卡
            yzm(val, oldVal){
                this.checkTxBind();
            },
            radioValue(val, oldVal){
                this.checkTxBind();
            },

        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "rCell": require("../../components/cell/cell.vue"),
            "rGroup": require("../../components/group/group.vue"),
            "rInput": require("../../components/input/input.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rAddressChecked": require('../../components/x-address-checked/x-address-checked.vue'),
            "rAddress": require("../../components/address/address.vue"),
            "rPopup": require("../../components/popup/popup.vue"),
            "rRadio": require("../../components/radio/x-radio.vue"),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../../assets/styles/views/personal";
</style>
