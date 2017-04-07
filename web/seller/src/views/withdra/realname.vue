<template>
<div class="realname">
    <c-top-back></c-top-back>
    <c-cell-wrap title="选择类型">
        <c-cell title="实名主体" :value="account" @click.native="accountClick" is-link></c-cell>
    </c-cell-wrap>
    <c-cell-wrap title="请填写实名信息" v-if="account=='个人'">
        <c-input title="姓名  " placeholder="请输入姓名" required @on-change="validate" v-model="formData.name" name="name" :max='20'></c-input>
        <c-cell title="证件类型" :value="certificates"></c-cell>
        <c-input title="证件号码" placeholder="请输入证件号码" required @on-change="validate" v-model="formData.card" name="card" :is-type="card_judge"></c-input>
    </c-cell-wrap>

    <c-cell-wrap title="请填写实名信息" v-else>
        <c-input title="企业名称" @on-change="validate" required placeholder="请输入企业名称" name="company_name" v-model="formData.company_name" :max="30"></c-input>
        <c-input title="营业执照" @on-change="validate" required placeholder="请输入营业执照号码" name="business_code" v-model="formData.business_code" :is-type="license_judge"></c-input>
        <c-upload-img title='上传执照' @upload='upload' :list="formData.img_list" :max="1">
            <span slot="after-title" class="placeholder">请上传您的营业执照照片</span> 
        </c-upload-img>
    </c-cell-wrap>
    <div class="wrap-pd"  style="margin-top:10px">
        <c-button text="确认" :type="color" :disabled="disabled" size="block"></c-button>
    </div>
    <c-addr title="选择物流" @get-addressid="accountId"  @address-add="cancel" v-model="accountStatus" :address="accountData" type="default" size='block' btn-txt="取消"></c-addr>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                fivetext:'提现预计24小时后到账',
                account:"个人",
                color:'default',
                disabled:true,
                certificates:'身份证',
                accountStatus:false,
                accountData:[],
                formData:{
                    name:'',
                    card:'',
                    company_name:'',
                    business_code:'',
                    img_list:[]
                },
                status:{
                    name:false,
                    card:false,
                    company_name:false,
                    business_code:false,
                },
                card_judge:function (value) {
                  return {
                    valid: value.search(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/) > -1
                  }
                },
                license_judge:function (value) {
                  return {
                    valid: value.search(/^[0-9a-zA-Z]{15,18}$/) > -1
                  }
                }

            }
        },

        methods:{
            validate(obj){
                this.status[obj.name]=obj.valid;
                this.judge();
            },
            judge(){
                if(this.account=='个人'){
                    if(this.status.name && this.status.card){
                        this.color='primary';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }else{
                    if(this.status.company_name && this.status.business_code && this.formData.img_list.length){
                        this.color='primary';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }
            },
            upload(url){
                this.formData.img_list=url;
                this.judge();
            },
            accountId(id,obj){
                this.account=obj.trueName;
                this.accountStatus=false;
                this.judge();
            },
            cancel:function(){
                this.accountStatus=false;
            },
            accountClick(){
                this.accountStatus=true;
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
                this.accountData=[{'id':'1','trueName':'个人'},{'id':'0','trueName':'企业'}];
           })
        },
        components:{
            "cMessages":require('../../components/x-messages/x-messages.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cCellWrap": require('../../components/cell/cell-wrap.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cInput":require('../../components/input/input.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cUploadImg": require('../../components/x-upload-img/x-upload-img-slice.vue'),
            "cAddr": require('../../components/x-address-checked/x-address-checked.vue'),

        }
    }
</script>
<style>
    .realname .weui_cell_ft.with_arrow:after{
        position:absolute !important;
        top:44% !important;
    }
    .realname .weui_cell_ft.with_arrow{
        margin-right:15px;
    }
    .realname .weui_panel:first-child{
        margin-top:10px !important;
    }
    .realname .placeholder{
        color:#999; margin-left:10px;
    }
    .realname .weui_cell{
      padding:10px !important;
    }
    .realname .weui_uploader_hd.weui_cell{
      padding: 0 !important; padding-bottom: 10px !important;
    }
</style>

