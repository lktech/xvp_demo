<template>
<div>
    <c-top-back></c-top-back>
    <c-group>
        <c-input title="店铺名称" @on-change="validate" placeholder="请输入店铺名称" required name="name" v-model="formData.name" ></c-input>
        <c-city title="区域" v-model="formData.addr_code" @on-hide="logHide" placeholder="您所在的省市县" :list="addressData"></c-city>
    </c-group> 
    <c-group>   
        <c-input title="详细地址" @on-change="validate" placeholder="请输入您的详细地址" required name="address" v-model="formData.address" :max="50"></c-input>
        <c-input title="客服电话" @on-change="validate" placeholder="请输入客服电话" name="phone" required v-model="formData.phone" :max="50"></c-input>
        <c-input title="QQ号码" @on-change="validate" placeholder="请输入QQ号码(选填)" name="QQ" v-model="formData.QQ"></c-input>
    </c-group>
    <div class="wrap-pd" style="margin-top:10px">
        <c-button text="进入店铺" :type="color" :disabled="disabled" @click.native="preserve" size="block"></c-button>
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
                    QQ:'',             //邮编
                    address:'',             //详细地址
                    addr_code:[]             //省市区
                },
                addressData:ChinaAddressData,
                color:'default',            //保存按钮颜色
                name_status:false,          //收货人校检
                phone_status:false,             //手机号校检
                addr_status:false,             //详细地址校检
                disabled:true,             //是否禁用保存按钮
                addr_code_status:false             //省市区校检
            }
        },

        methods:{
            preserve(){
                if(!this.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/seller/store/add",
                        dataType: 'json',
                        type: 'POST',
                        data:JSON.stringify({
                            'store_name':that.formData.name,
                            'customer_service_phone':that.formData.phone,
                            'qq':that.formData.QQ,
                            'detail_address':that.formData.address,
                            'region_code':that.formData.addr_code[2]
                        }),
                        success: function(data){
                            if(data.code=="SUCESS"){
                                //that.login1();
                            }else{
                                that.$vux.alert.show(data.message);
                            }
                        }
                    });
                } 
            },
            validate(obj){
                if(obj.name=='name'){
                    this.name_status=obj.valid;
                }
                if(obj.name=='phone'){
                    this.phone_status=obj.valid;
                }
                if(obj.name=='address'){
                    this.addr_status=obj.valid;
                }
                if(this.name_status && this.phone_status && this.addr_status && this.addr_code_status){
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
                    if(this.name_status && this.phone_status && this.addr_status && this.addr_code_status){
                        this.color='primary';
                        this.disabled=false;
                    } 
                }
                
            },
            login1(){
              let that=this;
              utils.ajax({
                  url: basepath + "/mall/auth",
                  dataType: 'json',
                  type: 'POST',
                  success: function(data){
                      if(data.success){
                          utils.header();
                          utils.go({name:'store'},that.$router);
                      }else{
                        that.$vux.alert.show('授权失败');
                      }
                  }
              });
            }
            
        },
        mounted: function () {
            this.$nextTick(function () {
                utils.loadingHide();
           })
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

