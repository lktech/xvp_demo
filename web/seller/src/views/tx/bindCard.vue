<template>
    <div class="bindCard">
        <c-top-back :Call="true" @on-change="sss"></c-top-back>
        <r-group>
            <r-cell title="个人账户提现账号绑定" class="cellTitle"></r-cell>
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
            <r-input title="企业名称" :max="50" v-model="companyName" placeholder="请输入企业名称"></r-input>
            <r-input title="营业执照" :max="20" v-model="license" placeholder="请输入营业执照"></r-input>
        </r-group>
        <div class="btn">
            <r-button type="primary" text="下一步" @click.native="btnClick" :disabled="btnDisabled"></r-button>
        </div>
        <r-address-checked
                v-model="showBank"
                :address="bankList"
                @get-addressid="getBank"
                @address-add="showBank=false" btn-txt="取消" title="提现银行"></r-address-checked>
        <r-address-checked
                v-model="showSubBank"
                :address="subBankList"
                @get-addressid="getSubBank"
                @address-add="showSubBank=false" btn-txt="取消" title="选择支行"></r-address-checked>
        <!--省-->
        <r-popup v-model="showProvince" :hide-on-blur="false">
            <div class="popupArea">
                <h3>请选择省</h3>
                <div class="areaContent" :style="{maxHeight:maxH,overflow: 'auto'}">
                    <r-group>
                        <r-radio :options="province" v-model="provinceValue" @on-change="changeProvince"></r-radio>
                    </r-group>
                </div>
            </div>
        </r-popup>
        <!--市-->
        <r-popup v-model="showCity" :hide-on-blur="false">
            <div class="popupArea">
                <h3>请选择市</h3>
                <div class="areaContent" :style="{maxHeight:maxH,overflow: 'auto'}">
                    <r-group>
                        <r-radio :options="city" v-model="cityValue" @on-change="changeCity"></r-radio>
                    </r-group>
                </div>
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
                provinceName: "",//省
                cityName: "",//城市
                cityCode: "",//城市编码
                cardBank: "",//银行
                cardBankCode: "",//银行编码
                cardAddress: "",
                openingBank: "",
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
                provinceValue: {},//默认选中省
                showCity: false,
                city: [],//市列表
                cityValue: {}//默认选择市
            }
        },
        computed: {
            maxH(){
                return $(window).height() * 0.5 + "px";
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;

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
                            that.$vux.alert.show(res.code);
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
                                that.province.push({id: obj.code, name: obj.name});
                            })
                        } else {
                            that.$vux.alert.show(res.code);
                        }
                    }
                });
            })
        },
        methods: {
            sss(){
                alert(3)
            },
            //发卡银行
            cardBankClick(){
                this.showBank = true;
            },
            //开户行
            openingBankClick(){
                if (this.cityName) {
                    this.showSubBank = true;
                } else {
                    this.$vux.alert.show("请选择卡所在地");
                }

            },
            //银行点击
            getBank(id, obj){
                this.cardBankCode = id;
                this.cardBank = obj.trueName;
                this.showBank = false;
            },
            //支行点击
            getSubBank(id, obj){
                this.openingBank = obj.trueName;
                this.showSubBank = false;
            },
            //检查个人
            check(){
                if (this.cardBank != "" && this.cityCode != "" && this.openingBank != "" && /^\d{16,21}$/.test(this.cardNumber) && this.trueName.indexOf(" ") == -1 && this.trueName != "" && /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(this.idCode)) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            //检查企业
            checkCompany(){
                if (this.cardBank != "" && this.cityCode != "" && this.openingBank != "" && /^\d{16,21}$/.test(this.cardNumber) && this.license.indexOf(" ") == -1 && this.license != "" && this.companyName.indexOf(" ") == -1 && this.companyName != "") {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            //下一步
            btnClick(){
                let that = this;
                let param = {
                    "user_name": "张三",
                    "certificate_number": "220102198706019690",
                    "card_no": "1234567890123456789",
                    "bank_code": "123456789",
                    "bank_name": "招商银行",
                    "bank_branch_code": "1234567890",
                    "bank_branch_name": "招商银行望京支行",
                    "bank_province_code": "110000",
                    "bank_city_code": "110100",
                    "company_name": "融数金服",
                    "buslince": "110105019962316",
                    "phone": "15148236954",
                    "sn": "11wewsfddasf",
                    "verfiy_code": "123456",
                };
                utils.ajax({
                    url: "/seller/account/addStoreBankCard",
                    data: {},
                    success: function (res) {
                        if (res.code == "SUCCESS") {

                        } else {
                            that.$vux.alert.show(res.code);
                        }
                    }
                })
            },
            //省选择
            changeProvince (obj) {
                let that = this;
                that.provinceName = obj.name;

                //渲染市
                utils.ajax({
                    url: "/seller/account/getBankCity",
                    data: {citycode: obj.id},
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.city = [];
                            res.result.forEach(function (obj, i) {
                                that.city.push({id: obj.code, name: obj.name});
                            });
                            that.showProvince = false;
                            that.showCity = true;
                        } else {
                            that.$vux.alert.show(res.code);
                        }
                    }
                })
            },
            //市选择
            changeCity(obj){
                let that = this;
                this.cityName = obj.name;
                this.showCity = false;
                this.cityCode = obj.id;

                //渲染开户行
                utils.ajax({
                    url: "/seller/account/getBankInfo",
                    data: {citycode: obj.id, bankcode: that.cardBankCode},
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.subBankList = [];
                            res.result.forEach(function (obj, i) {
                                that.subBankList.push({id: obj.bankcode, trueName: obj.bankname});
                            });
                        } else {
                            that.$vux.alert.show(res.code);
                        }
                    }
                })
            },
            //卡所在地
            cityCodeClick(){
                if (this.cardBank) {
                    this.showProvince = true;
                } else {
                    this.$vux.alert.show("请选择发卡银行");
                }
            }
        },
        watch: {
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
            }
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
