<template>
<div class="realname">
    <c-top-back></c-top-back>
    <c-cell-wrap :title="title">
        <c-cell title="发卡银行" :value="bank" @click.native="bankClick" is-link></c-cell>
        <c-city title="卡所在地" v-model="addr_code"  @on-hide="logHide" :list="addressData" hideDistrict></c-city>
    </c-cell-wrap>

    <c-cell-wrap>
        <c-cell title="开户行" :value="open_bank" @click.native="open_bankClick" is-link></c-cell>
        <c-input title="银行卡 " placeholder="请输入银行卡号" required @on-change="validate" v-model="formData.bank_card" name="bank_card" :is-type="bank_judge"></c-input>
    </c-cell-wrap>

    <c-cell-wrap>
        <c-input title="真实姓名" placeholder="请输入真实姓名" :disabled="disabled_i" required @on-change="validate" v-model="formData.name" name="name" :max='20'></c-input>
        <c-input title="身份证号" placeholder="请输入身份证号" :disabled="disabled_i" required @on-change="validate" v-model="formData.card" name="card" :is-type="card_judge"></c-input>
    </c-cell-wrap>

    <div class="wrap-pd"  style="margin-top:10px">
        <c-button text="下一步" :type="color" :disabled="disabled" size="block"></c-button>
    </div>
    <c-addr title="选择物流" @get-addressid="bankId"  @address-add="cancel" v-model="bankStatus" :address="bankData" type="default" size='block' btn-txt="取消"></c-addr>
    <c-addr title="选择物流" @get-addressid="open_bankId"  @address-add="cancel" v-model="open_bankStatus" :address="open_bankData" type="default" size='block' btn-txt="取消"></c-addr>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    var ChinaAddressData = require("../../libs/components/data/china_address.json");
    export default {
        data (){
            return {
                color:'default',
                disabled:true,
                bankData:[],
                open_bankData:[],
                title:'个人账户提现账号绑定',
                addr_code:[],
                addressData:ChinaAddressData,
                bank:'',
                bank_id:'',
                open_bank:'',
                bankStatus:false,
                open_bankStatus:false,
                disabled_i:false,
                formData:{
                    name:'',
                    bank_card:'',
                    card:'',
                },
                status:{
                    name:false,
                    bank_card:false,
                    card:false,
                    addr_code_status:false,
                    bank_status:false,
                    open_bank_status:false,
                },
                bank_judge:function (value) {
                  return {
                    valid: value.search(/^[0-9]{16,21}$/) > -1
                  }
                },
                card_judge:function (value) {
                  return {
                    valid: value.search(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/) > -1
                  }
                },

            }
        },

        methods:{
            validate(obj){
                this.status[obj.name]=obj.valid;
                this.judge();
            },
            judge(){
                console.log(this.status)
                if(this.status.name && this.status.bank_card && this.status.card && this.status.addr_code_status && this.status.bank_status && this.status.open_bank_status){
                    this.color='primary';
                    this.disabled=false;
                    console.log(1)
                }else{
                    console.log(2)
                    this.color='default';
                    this.disabled=true;
                }
            },
            cancel:function(){
                this.bankStatus=false;
            },
            bankId(id,obj){
                this.bank=obj.trueName;
                this.bank_id=id;
                this.bankStatus=false;
                this.status.bank_status=true;
                this.obtain();
                this.judge();
            },
            bankClick(){
                this.bankStatus=true;
            },
            open_bankId(id,obj){
                this.open_bank=obj.trueName;
                this.open_bankStatus=false;
                this.status.open_bank_status=true;
                this.judge();
            },
            open_bankClick(){
                if(this.open_bankData.length){
                    this.open_bankStatus=true;
                }
                
            },
            obtain(){
                if(this.status.addr_code_status && this.status.bank_status){
                    this.open_bankData=[{'id':'1','trueName':'朝阳区银行'},{'id':'0','trueName':'丰台区银行'}];
                }
            },
            logHide(success){
                if(success){
                    this.status.addr_code_status = true;
                    this.obtain();
                    this.judge();
                }
                
            },
            open_bank_switch(){
                this.open_bank='';
                this.status.open_bank_status=false;
                this.judge();
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
                this.bankData=[{'id':'1','trueName':'招商银行'},{'id':'0','trueName':'建设银行'},{'id':'2','trueName':'交通银行'}];
           })
        },
        watch:{
            addr_code(){
                this.open_bank_switch();
            },
            bank(){
                this.open_bank_switch();
            }
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
            "cCity":require('../../components/address/address.vue'),

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

