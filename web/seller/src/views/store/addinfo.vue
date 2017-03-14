<template>
<div>
    <c-top-back></c-top-back>
    <c-group>
        <c-input title="店铺名称" @validate="validate" placeholder="请输入店铺名称" name="name" :value.sync="formData.name" is-type="name" ></c-input>
        <c-city title="区域" @on-hide="logHide" placeholder="您所在的省市县" ></c-city>
    </c-group> 
    <c-group>   
        <c-input title="详细地址" @validate="validate" placeholder="请输入您的详细地址" name="address" :value.sync="formData.address" :min="1" :max="50"></c-input>
        <c-input title="客服电话" @validate="validate" placeholder="请输入客服电话" name="phone" :value.sync="formData.phone" :min="1" :max="50"></c-input>
        <c-input title="QQ号码" @validate="validate" placeholder="请输入QQ号码(选填)" name="QQ" :value.sync="formData.QQ"></c-input>
    </c-group>
    <div class="wrap-pd">
        <c-button text="进入店铺" :type="color" :disabled="disabled" @click="preserve" size="block"></c-button>
    </div>
    
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
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
                        url: basepath + "/mall/store/info_add",
                        dataType: 'json',
                        type: 'POST',
                        contentType:'application/json',
                        data:JSON.stringify({
                            'name':that.formData.name,
                            'phone':that.formData.phone,
                            'qq':that.formData.QQ,
                            'address':that.formData.address,
                            'province':that.formData.addr_code[0],
                            'city':that.formData.addr_code[1],
                            'county':that.formData.addr_code[2]
                        }),
                        success: function(data){
                            if(data.success){
                                that.login1();
                            }else{
                                that.$vux.alert.show(data.msg);
                            }
                        }
                    });
                } 
            },
            validate(obj){
                if(obj.name=='name'){
                    this.name_status = obj.status;
                }
                if(obj.name=='phone'){
                    this.phone_status = obj.status;
                }
                if(obj.name=='address'){
                    this.addr_status = obj.status;
                }
                if(this.name_status && this.phone_status && this.addr_status && this.addr_code_status){
                    this.color='org';
                    this.disabled=false;
                }else{
                    this.color='default';
                    this.disabled=true;
                }
            },
            logHide(obj){
                this.formData.addr_code=obj.code;
                this.addr_code_status = true;
                if(this.name_status && this.phone_status && this.addr_status && this.addr_code_status){
                    this.color='org';
                    this.disabled=false;
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
        ready(){
            utils.loadingHide();
            
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cInput":require('../../components/x-input.vue'),
            "cButton":require('../../components/button.vue'),
            "cCity":require('../../components/city.vue')
        }
    }
</script>

