<template>
<div class="addradd">
    <c-top-back></c-top-back>
    <c-group>
        <c-input title="收货人 " @on-change="validate" placeholder="请输入姓名" name="name" v-model="formData.name" required :max="20" ></c-input>
        <c-input title="电话  " @on-change="validate" placeholder="请输入手机号" name="phone" required v-model="formData.phone" is-type="china-mobile" ></c-input>
            <c-city title="选择地区" v-model="formData.addr_code" @getname="getname" @on-hide="logHide" placeholder="选择省市县" :list="addressData"></c-city>
        <c-input title="详细地址" @on-change="validate" placeholder="请输入详细地址" required name="address" v-model="formData.address" :min="5" :max="100"></c-input>
        <c-input title="邮编  " @on-change="validate" placeholder="请输入邮编" required name="post_code" v-model="formData.post_code" is-type="number" :min="6" :max="10"></c-input>
    </c-group>
    <div class="wrap-pd" style="margin-top:10px;">
        <c-button text="保存" :type="color" :disabled="disabled" @click.native="preserve" size="block"></c-button>
    </div>
    
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    var ChinaAddressData = require("../../libs/components/data/china_address.json");
    export default {
        data (){
            return {
                formData:{               //填写的数据
                    phone:'',             //手机号
                    name:'',              //姓名                           
                    post_code:'',             //邮编
                    address:'',             //详细地址
                    addr_code:[],             //省市区
                    addr_text:[]
                },
                addressData:ChinaAddressData,
                color:'default',            //保存按钮颜色
                name_status:false,          //收货人校检
                phone_status:false,             //手机号校检
                code_status:false,             //邮编校检
                addr_status:false,             //详细地址校检
                disabled:true,             //是否禁用保存按钮
                addr_code_status:false,            //省市区校检
            }
        },

        methods:{
            preserve(){
                if(!this.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/user/user/address/set",
                        dataType: 'json',
                        type: 'POST',
                        data:{
                            'name':that.formData.name,
                            'phone':that.formData.phone,
                            'post_code':that.formData.post_code,
                            'address':that.formData.address,
                            'province':that.formData.addr_code[0],
                            'city':that.formData.addr_code[1],
                            'county':that.formData.addr_code[2],
                            'str':that.formData.addr_text[0]+that.formData.addr_text[1]+that.formData.addr_text[2]+that.formData.address
                        },
                        success: function(data){
                            if(data.code=="SUCESS"){
                                    utils.go({path:'/order/add'},that.$router,true);
                            }else{
                                that.$vux.alert.show(data.message);
                            }
                        },
                        message:'正在保存'
                    });
                } 
            },
            validate(obj){
                if(obj.name=='name'){
                    this.name_status = obj.valid;
                }
                if(obj.name=='phone'){
                    console.log(obj.valid)
                    this.phone_status = obj.valid;
                }
                if(obj.name=='address'){
                    this.addr_status = obj.valid;
                }
                if(obj.name=='post_code'){
                    this.code_status = obj.valid;
                }
                if(this.name_status && this.phone_status && this.addr_status && this.code_status && this.addr_code_status){
                    this.color='primary';
                    this.disabled=false;
                }else{
                    this.color='default';
                    this.disabled=true;
                }
            },
            logHide(success){
                if(success){
                    this.addr_code_status = true;
                    if(this.name_status && this.phone_status && this.addr_status && this.code_status && this.addr_code_status){
                        this.color='primary';
                        this.disabled=false;
                    } 
                }
                
            },
            getname(txt){
                this.formData.addr_text=txt.split(' ');
            }
            
        },
        ready(){
            utils.MenuShare();
            utils.loadingHide();
        },
        components:{
            "cGroup":require('../../components/group/group.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cInput":require('../../components/input/input.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cCity":require('../../components/address/address.vue')
        }
    }
</script>
<style>
    .addradd .vux-popup-picker-value{
        white-space: nowrap; width:210px; overflow: hidden; display: block;
    }
</style>
