<template>
    <div class="verifyInfo">
        <r-top-back></r-top-back>
        <r-group title="选择类型">
            <r-cell title="实名主体" :value="typeName" is-link @click.native="typeBtn"></r-cell>
        </r-group>
        <r-group title="请填写实名信息">
            <r-input title="姓名" v-model="name" placeholder="请输入姓名"></r-input>
            <r-cell title="证件类型" value="身份证"></r-cell>
            <r-input title="证件号码" v-model="idCode" placeholder="请输入证件号码"></r-input>
        </r-group>
        <div class="btn">
            <r-button type="primary" text="确认" @click.native="btnClick" :disabled="btnDisabled"></r-button>
        </div>

        <r-address-checked defaultStatus="1"
                           v-model="adsStatus"
                           :address="address"
                           @get-addressid="getAddressId"
                           @address-add="adsStatus=false" btn-txt="取消" title="实名主体"></r-address-checked>

    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        data(){
            return {
                name: "",
                nameStatus: false,
                idCode: "",
                idCodeStatus: false,
                btnDisabled: true,
                address: [],
                adsStatus: false,
                typeName: "个人",


            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.address = [{
                    id: 1,
                    trueName: "个人用户",
                }, {
                    id: 2,
                    trueName: "企业用户",
                }]
            })
        },
        methods: {
            btnClick(){

            },
            getAddressId(id, obj){
                this.typeName = obj.trueName.substr(0, 2);
                this.adsStatus = false;
            },
            typeBtn(){
                this.adsStatus = true;
            }

        },
        watch: {
            name(val, oldVal){
                console.log(val)
                if (val.indexOf(" ") == -1 && val !="") {
                    this.nameStatus = true;
                }else {
                    this.nameStatus = false;
                }
                if (this.nameStatus && this.idCodeStatus) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }

            },
            idCode(val, oldVal){
                if (/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(val)) {
                    this.idCodeStatus = true;
                } else {
                    this.idCodeStatus = false;
                }
                if (this.nameStatus && this.idCodeStatus) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            }
        },
        components: {
            "rTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "rCell": require("../../components/cell/cell.vue"),
            "rGroup": require("../../components/group/group.vue"),
            "rInput": require("../../components/input/input.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rAddressChecked": require('../../components/x-address-checked/x-address-checked.vue'),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../../assets/styles/views/personal";
</style>
