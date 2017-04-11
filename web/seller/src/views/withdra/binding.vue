<template>
<div class="binding">
    <c-top-back></c-top-back>
    <div v-if="!res.next">
        <c-cell-wrap :title="res.title">
            <c-cell title="发卡银行" :value="res.bank" @click.native="bankClick" is-link></c-cell>
            <c-city title="卡所在地" v-model="res.addr_code"  @on-hide="logHide" :list="res.addressData" hideDistrict></c-city>
        </c-cell-wrap>

        <c-cell-wrap>
            <c-cell title="开户行" :value="res.open_bank" @click.native="open_bankClick" is-link></c-cell>
            <c-input title="银行卡 " placeholder="请输入银行卡号" required @on-change="validate" v-model="res.formData.bank_card" name="bank_card" :is-type="res.bank_judge"></c-input>
        </c-cell-wrap>

        <c-cell-wrap>
            <c-input title="真实姓名" placeholder="请输入真实姓名" :disabled="res.disabled_i" required @on-change="validate" v-model="res.formData.name" name="name" :max='20'></c-input>
            <c-input title="身份证号" placeholder="请输入身份证号" :disabled="res.disabled_i" required @on-change="validate" v-model="res.formData.card" name="card" :is-type="res.card_judge"></c-input>
        </c-cell-wrap>

        <div class="wrap-pd"  style="margin-top:10px">
            <c-button text="下一步" :type="res.color" :disabled="res.disabled" size="block" @click.native="bin_next"></c-button>
        </div>
    </div>
    <div v-else>
        <c-group>
            <c-cell title="手机号" :value="res.phone"></c-cell>
            <c-input title="验证码" placeholder="请输入验证码" @on-change="validate" required v-model="res.formData.code" name="code" :max="6" class="weui_vcode">
                <c-button-send slot="right" type="primary" validate="true" @on-send="send_out">获取验证码</c-button-send>
            </c-input>
        </c-group>
        <c-agree 
             terms="支付协议"            
             @on-change="agreeChange"            
             :checked="res.check" 
             @showterms="showterms" style="margin-top:10px;">
         </c-agree>
        <div class="wrap-pd"  style="margin-top:10px">
            <c-button text="启用并绑定" :type="res.color1" :disabled="res.disabled1" size="block"></c-button>
        </div>
    </div>
    <c-addr title="选择银行" @get-addressid="bankId"  @address-add="cancel" v-model="res.bankStatus" :address="res.bankData" type="default" size='block' btn-txt="取消"></c-addr>
    <c-addr title="选择支行" @get-addressid="open_bankId"  @address-add="cancel" v-model="res.open_bankStatus" :address="res.open_bankData" type="default" size='block' btn-txt="取消"></c-addr>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    var ChinaAddressData = require("../../libs/components/data/china_address.json");
    export default {
        data (){
            return {
                res:{
                    color:'default',
                    disabled:true,
                    color1:'default',
                    disabled1:true,
                    bankData:[],
                    open_bankData:[],
                    check:false,
                    title:'个人账户提现账号绑定',
                    addr_code:[],
                    addressData:ChinaAddressData,
                    bank:'',
                    bank_id:'',
                    open_bank:'',
                    bankStatus:false,
                    open_bankStatus:false,
                    disabled_i:false,
                    next:false,
                    phone:'13111111111',
                    formData:{
                        name:'',
                        bank_card:'',
                        card:'',
                        code:''
                    },
                    status:{
                        name:false,
                        bank_card:false,
                        card:false,
                        addr_code_status:false,
                        bank_status:false,
                        open_bank_status:false,
                        code:false,
                        agree:false
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
                },
                bank_watch:'',
                addr_watch:[],
            }
        },

        methods:{
            validate(obj){
                this.res.status[obj.name]=obj.valid;
                this.judge();
            },
            judge(){
                if(!this.res.next){
                    if(this.res.status.name && this.res.status.bank_card && this.res.status.card && this.res.status.addr_code_status && this.res.status.bank_status && this.res.status.open_bank_status){
                        this.res.color='primary';
                        this.res.disabled=false;
                    }else{
                        this.res.color='default';
                        this.res.disabled=true;
                    }
                }else{
                    if(this.res.status.code && this.res.status.agree){
                        this.res.color1='primary';
                        this.res.disabled1=false;
                    }else{
                        this.res.color1='default';
                        this.res.disabled1=true;
                    }
                }
            },
            cancel:function(){
                this.res.bankStatus=false;
            },
            bankId(id,obj){
                this.res.bank=obj.trueName;
                this.bank_watch=obj.trueName;
                this.res.bank_id=id;
                this.res.bankStatus=false;
                this.res.status.bank_status=true;
                this.obtain();
                this.judge();
            },
            bankClick(){
                this.res.bankStatus=true;
            },
            open_bankId(id,obj){
                this.res.open_bank=obj.trueName;
                this.res.open_bankStatus=false;
                this.res.status.open_bank_status=true;
                this.judge();
            },
            open_bankClick(){
                if(this.res.open_bankData.length){
                    this.res.open_bankStatus=true;
                }
                
            },
            obtain(){
                if(this.res.status.addr_code_status && this.res.status.bank_status){
                    this.res.open_bankData=[{'id':'1','trueName':'朝阳区银行'},{'id':'0','trueName':'丰台区银行'}];
                }
            },
            logHide(success){
                if(success){
                    this.addr_watch=this.res.addr_code;
                    this.res.status.addr_code_status = true;
                    this.obtain();
                    this.judge();
                }
                
            },
            open_bank_switch(){
                this.res.open_bank='';
                this.res.status.open_bank_status=false;
                this.judge();
            },
            send_out(){

            },
            agreeChange(status){
                this.res.status.agree=status;
                this.judge();
            },
            bin_next(){
                this.res.next=true;
            },
            showterms(){
                utils.setSession("json",this.res);
                utils.go({path:'/withdra/agreem'},this.$router);
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.$vux.alert.hide();
                this.res.bankData=[{'id':'1','trueName':'招商银行'},{'id':'0','trueName':'建设银行'},{'id':'2','trueName':'交通银行'}];
                if(this.$route.query.agree){
                    this.res=JSON.parse(utils.getSession("json"));
                    this.judge();
                }
           })
        },
        watch:{
            addr_watch(){
                this.open_bank_switch();
            },
            bank_watch(){
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
            "cButtonSend":require('../../components/x-button-send/x-button-send.vue'),
            "cGroup":require('../../components/group/group.vue'),
            "cAgree": require('../../components/x-agree/x-agree.vue'),

        }
    }
</script>
<style>
    .binding .weui_cell_ft.with_arrow:after{
        position:absolute !important;
        top:44% !important;
    }
    .binding .weui_cell_ft.with_arrow{
        margin-right:15px;
    }
    .binding .weui_panel:first-child{
        margin-top:10px !important;
    }
    .binding .placeholder{
        color:#999; margin-left:10px;
    }
    .binding .weui_cell{
      padding:10px !important;
    }
    .binding .weui_uploader_hd.weui_cell{
      padding: 0 !important; padding-bottom: 10px !important;
    }
</style>

