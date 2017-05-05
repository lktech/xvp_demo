<template>
    <div class="verifyInfo">
        <r-top-back></r-top-back>
        <r-group title="选择类型">
            <r-cell title="实名主体" :value="typeName" is-link @click.native="typeBtn"></r-cell>
        </r-group>
        <r-group title="请填写实名信息" v-if="typeName=='个人'">
            <r-input title="姓名" v-model="trueName" placeholder="请输入姓名"></r-input>
            <r-cell title="证件类型" value="身份证"></r-cell>
            <r-input title="证件号码" v-model="idCode" placeholder="请输入证件号码"></r-input>
        </r-group>
        <r-group title="请填写实名信息" v-else>
            <r-input title="企业名称" v-model="companyName" placeholder="请输入企业名称"></r-input>
            <r-input title="营业执照" v-model="businessLicense" placeholder="请输入营业执照号码"></r-input>
            <r-input title="上传执照" :placeholder="'请上传您的营业执照照片('+picList.length+'/1)'" value="" disabled></r-input>
            <div class="licensePic">
                <img :src="item" @click="imgClick(index)" v-for="(item,index) in picList">
                <a href="javascript:;" class="upload" v-if="picList.length<1">
                    <input type="file" accept="image/*" @change="uploadFile($event.target)"/>
                </a>
            </div>
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
    import utils from '../libs/utils.js';
    export default{
        data(){
            return {
                trueName: "",
                idCode: "",
                btnDisabled: true,
                address: [],
                adsStatus: false,
                typeName: "个人",

                //企业
                companyName: "",
                businessLicense: "",
                picList: [],
                loadingText: "",

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
                this.$vux.toast.show('提交成功');
            },
            getAddressId(id, obj){
                this.typeName = obj.trueName.substr(0, 2);
                this.adsStatus = false;
                this.trueName="";
                this.idCode="";
                this.companyName="";
                this.businessLicense="";
                this.picList=[];
            },
            typeBtn(){
                this.adsStatus = true;
            },
            //上传执照
            uploadFile(target) {
                let that = this;
                that.$vux.loading.show(that.loadingText);
                var myForm = new FormData();
                myForm.append("file", target.files[0]);
                var oReq = new XMLHttpRequest();
                oReq.open("POST", basepath + "/wx/course/ask/uploadPic");
                oReq.upload.onprogress = function (ev) {
                    let num = ev.loaded / ev.total;
                    if (num == 1) {
                        that.loadingText = '99%';
                    } else {
                        that.loadingText = parseInt(num * 100) + "%";
                    }
                }
                oReq.onload = function (response) {
                    if (oReq.status == 200) {
                        var data = JSON.parse(response.currentTarget.response);
                        that.picList.push(data.picUrl);
                        that.$vux.loading.hide();
                        that.loadingText = "";
                        that.checkCompany();
                    }
                }
                oReq.send(myForm);
            },
            imgClick(index){
                let that = this;
                that.$vux.confirm.show({
                    content: '确认删除照片？',
                    onConfirm() {
                        that.picList.splice(index, 1);
                        that.checkCompany();
                    }
                });
            },
            //检查个人实名认证
            checkPerson(){
                if (this.trueName.indexOf(" ") == -1 && this.trueName != "" && /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(this.idCode)) {
                    this.btnDisabled = false;
                } else {
                    this.btnDisabled = true;
                }
            },
            //检查企业实名认证
            checkCompany(){
                if(this.companyName.indexOf(" ")==-1 && this.companyName !="" && this.businessLicense.indexOf(" ")==-1 && this.businessLicense !="" && this.picList.length !=0){
                    this.btnDisabled = false;
                }else {
                    this.btnDisabled = true;
                }
            }

        },
        watch: {
            name(val, oldVal){
                this.checkPerson();
            },
            idCode(val, oldVal){
                this.checkPerson();
            },
            companyName(val,oldVal){
                this.checkCompany();
            },
            businessLicense(val,oldVal){
                this.checkCompany();
            }
        },
        components: {
            "rTopBack": require('../components/x-top-back/x-top-back.vue'),
            "rCell": require("../components/cell/cell.vue"),
            "rGroup": require("../components/group/group.vue"),
            "rInput": require("../components/input/input.vue"),
            "rButton": require("../components/button/button.vue"),
            "rAddressChecked": require('../components/x-address-checked/x-address-checked.vue'),
        }
    }
    require("../assets/styles/views/common.less");
</script>
<style lang="less">
    @import "../assets/styles/views/personal";
</style>
