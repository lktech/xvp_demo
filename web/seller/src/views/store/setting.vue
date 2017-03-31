<template>
<div class="tabbar-wrap2" v-if="res.setting">
    <div v-if="!res.inv_set">
         <c-top-back></c-top-back>
         <c-banner-info :title="res.formData.name" @upload='upload' :upload-type='true' :logo="res.formData.logo" banner='http://img1.xiaovpu.com/3028216099602841.png'>
         </c-banner-info>
         <c-group>
             <c-input title="店铺名称" @on-change="validate" placeholder="请输入店铺名称" :max="20" name="name" required :is-type="kong" v-model="res.formData.name" ></c-input>
             <c-city title="地址" v-model="res.formData.addr_code" @on-hide="logHide" placeholder="您所在的省市县" :list="res.addressData"></c-city>
         </c-group> 
         <c-group>   
             <c-input title="详细地址" @on-change="validate" placeholder="请输入您的详细地址" name="address" required  v-model="res.formData.address":max="100" :is-type="kong"></c-input>
             <c-input title="客服电话" @on-change="validate" placeholder="请输入客服电话" name="phone" required  v-model="res.formData.phone" :max="30" :is-type="kong"></c-input>
             <c-input title="QQ号码 " @on-change="validate" placeholder="请输入QQ号码(选填)" name="QQ" v-model="res.formData.QQ" :max="20" :is-type="kong"></c-input>
         </c-group>
         
         <div class="wrap-pd"  style="margin-top:10px">
             <c-button text="保存" :type="res.color" :disabled="res.disabled" @click.native="preserve" size="block"></c-button>
         </div>
    </div>

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
                    formData:{               //填写的数据
                        logo:'http://okxm1ge1z.bkt.clouddn.com/026e2cee-d3f5-468a-9bbf-257ec9a993c2.png',
                        phone:'',             //手机号
                        name:'',              //姓名                           
                        QQ:'',             //邮编
                        address:'',             //详细地址
                        addr_code:[],             //省市区
                    },
                    addressData:ChinaAddressData,
                    color:'primary',            //保存按钮颜色
                    name_status:true,          //收货人校检
                    phone_status:true,             //手机号校检
                    addr_status:true,             //详细地址校检
                    disabled:false,             //是否禁用保存按钮
                    addr_code_status:true,            //省市区校检
                    setting:false,
                    kong: function (value) {
                      return {
                        valid: value.search(/.*[^ ].*/) > -1
                      }
                    },
                }
            }
        },

        methods:{
            preserve(){
                if(!this.res.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/seller/store/update",
                        dataType: 'json',
                        type: 'POST',
                        data:{
                            "logo":that.res.formData.logo,
                            'store_name':that.res.formData.name,
                            'customer_service_phone':that.res.formData.phone,
                            'qq':that.res.formData.QQ,
                            'detail_address':that.res.formData.address,
                            'region_code':that.res.formData.addr_code[2],
                        },
                        success: function(data){
                            if(data.code=="SUCESS"){
                                utils.go({path:'/store/store'},that.$router,true);
                            }else{
                                that.$vux.alert.show({content:data.message,onHide :function(){
                                                            return false
                                                          }});
                            }
                        }
                    });
                } 
            },
            validate(obj){
                if(obj.name=='name'){
                    this.res.name_status=obj.valid;
                }
                if(obj.name=='phone'){
                    this.res.phone_status=obj.valid;
                }
                if(obj.name=='address'){
                    this.res.addr_status=obj.valid;
                }
                if(this.res.name_status && this.res.phone_status && this.res.addr_status && this.res.addr_code_status){
                    this.res.color='primary';
                    this.res.disabled=false;
                }else{
                    this.res.color='default';
                    this.res.disabled=true;
                }
            },
            logHide(status){
                if(status){
                   this.res.addr_code_status = true;
                   if(this.res.name_status && this.res.phone_status && this.res.addr_status && this.res.addr_code_status){
                       this.res.color='primary';
                       this.res.disabled=false;
                   } 
                }
                
            },
            upload(url){
                this.res.formData.logo=url;
            }
            
        },
        mounted: function () {
            this.$nextTick(function () {
                utils.MenuShare();
                let that = this;
                utils.ajax({
                    url: basepath + "/seller/store/get",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        if(data.code=="SUCESS"){
                            that.res.formData.name=data.result.store_name;
                            if(data.result.logo){
                                that.res.formData.logo = data.result.logo; //店铺名称
                                console.log(that.res.formData.logo);
                            }
                            that.res.formData.phone=data.result.customer_service_phone;
                            that.res.formData.QQ=data.result.qq;
                            that.res.formData.address=data.result.detail_address;

                            let province=data.result.region_code.substr(0,2)+'0000';
                            let city=province.substr(0,2)+data.result.region_code.substr(2,2)+'00';

                            that.res.formData.addr_code=[province,city,data.result.region_code];

                            that.res.setting=true;
                        }else if(data.code=='auth_seller_error'){
                            utils.wang(that,utils,data.message);

                        }else{
                            that.$vux.alert.show({content:data.message,onHide :function(){
                                                            return false
                                                          }});
                        }

                    }
                });

           })
        },
        components:{
            "cGroup":require('../../components/group/group.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cInput":require('../../components/input/input.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cBannerInfo":require('../../components/x-banner/x-banner-info-slice.vue'),
            "cCity":require('../../components/address/address.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cSwitch": require('../../components/switch/switch.vue'),
            "cChecklist": require('../../components/checklist/checklist.vue'),
            "cAddr": require('../../components/x-address-checked/x-address-checked.vue')
        }
    }
</script>
<style>
    .tabbar-wrap2 .vux-popup-picker-value{
        white-space: nowrap; width:210px; overflow: hidden; display: block;
    }
</style>