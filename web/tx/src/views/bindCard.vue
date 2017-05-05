<template>
    <div class="bindCard">
        <c-top-back></c-top-back>
        <r-group>
            <r-cell title="个人账户提现账号绑定" class="cellTitle"></r-cell>
            <r-cell title="发卡银行" :value="cardBank" is-link @click.native="cardBankClick"></r-cell>
            <r-cell title="卡所在地" :value="cityName" is-link @click.native="cityCodeClick"></r-cell>
        </r-group>
        <r-group>
            <r-cell title="开户行" :value="openingBank" is-link @click.native="openingBankClick" class="wd"></r-cell>
            <r-input title="银行卡" :max="21" v-model="cardNumber" placeholder="请输入银行卡号"></r-input>
        </r-group>
        <r-group v-if="type==1">
            <r-input title="真实姓名" :max="20" v-model="trueName" placeholder="请输入真实姓名"></r-input>
            <r-input title="身份证号" v-model="idCode" placeholder="请输入身份证号码"></r-input>
        </r-group>
        <r-group v-else>
            <r-input title="企业名称" v-model="companyName" placeholder="请输入企业名称"></r-input>
            <r-input title="营业执照" v-model="license" placeholder="请输入营业执照"></r-input>
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
        <r-popup v-model="showProvince">
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
        <r-popup v-model="showCity">
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
    import utils from '../libs/utils.js';
    export default{
        data(){
            return {
                type: this.$route.query.type,
                cityName: "",//城市
                cardBank: "",
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
                addressData: require("../libs/components/data/china_address.json"),

                //企业
                license: "",
                companyName: "",

                //省市
                showProvince: false,
                province: [{id: 1, name: "北京"}, {id: 2, name: "天津"}, {id: 3, name: "上海"}, {id: 4, name: "河北"}, {
                    id: 5,
                    name: "山东"
                }],
                provinceValue: {},
                showCity: false,
                city: [{id: 1, name: "北京市"}, {id: 2, name: "邯郸市"}, {id: 3, name: "衡水市"}],
                cityValue: {}
            }
        },
        computed: {
            maxH(){
                return $(window).height() * 0.5 + "px";
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.bankList = [{
                    id: 1,
                    trueName: "中国工商银行",
                }, {
                    id: 2,
                    trueName: "中国农业银行",
                }];
                this.subBankList = [{
                    id: 1,
                    trueName: "招商银行股份有限公司北京富力城支行",
                }, {
                    id: 2,
                    trueName: "招商银行股份有限公司北京富力城支行",
                }]
            })
        },
        methods: {
            //发卡银行
            cardBankClick(){
                this.showBank = true;
            },
            //卡所在地
            cardAddressClick(){
            },
            //开户行
            openingBankClick(){
                this.showSubBank = true;
            },
            //银行点击
            getBank(id, obj){
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

            },
            //省选择
            changeProvince (obj) {
                this.cityName = obj.name;
                this.showProvince = false;
                this.showCity = true;
            },
            //市选择
            changeCity(obj){
                this.cityName += " " + obj.name;
                this.showCity = false;
            },
            //点击省
            cityCodeClick(){
                this.showProvince = true;
            }
        },
        watch: {
            cardBank(val, oldVal){
                this.check();
            },
            cityCode(val, oldVal){
                this.check();
            },
            openingBank(val, oldVal){
                this.check();
            },
            cardNumber(val, oldVal){
                this.check();
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
            "cTopBack": require('../components/x-top-back/x-top-back.vue'),
            "rCell": require("../components/cell/cell.vue"),
            "rGroup": require("../components/group/group.vue"),
            "rInput": require("../components/input/input.vue"),
            "rButton": require("../components/button/button.vue"),
            "rAddressChecked": require('../components/x-address-checked/x-address-checked.vue'),
            "rAddress": require("../components/address/address.vue"),
            "rPopup": require("../components/popup/popup.vue"),
            "rRadio": require("../components/radio/x-radio.vue"),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../assets/styles/views/personal";
</style>
