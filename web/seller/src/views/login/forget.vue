<template>
<div>
    <c-top-back></c-top-back>

    <div v-if="!success">
        <c-group>
            <c-input title="手机号" placeholder="请输入注册时的手机号" @validate="validate" :value.sync="formData.phone" name="phone" :max='11' is-type="mobile"></c-input>
            <c-input title="验证码" placeholder="请输入验证码" @validate="validate" :value.sync="formData.code" name="code" :min="1"  :max='6'>
                <c-button-send slot="right" type="primary" :validate="phone_status" @on-send="send_out">获取验证码</c-button-send>
            </c-input>
        </c-group>
        <c-group>
            <c-input title="新密码" type="password" placeholder="请输入新密码" @validate="validate" :value.sync="formData.password" name="password" :max='20' is-type="notnull"></c-input>
            <c-input title="确认新密码"  type="password" placeholder="确认新密码" @validate="validate" :value.sync="formData.password1" name="password1" :max='20' is-type="notnull"></c-input>
        </c-group>
        <div class="wrap-pd">
            <c-button :type="color" :disabled='disabled' text="修改密码" @click="modify" size="block"></c-button>
        </div>
    </div>
    <div v-else>
       <c-msg status="1" msg="新密码设置成功" desc="请保存好你的新密码">
           <c-button slot="btn" type="primary" :link="{name:'login'}" size="block" text="返回登录页面"></c-button>
       </c-msg>
    </div>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    import md5 from 'md5';
    export default {
        data (){
            return { 
              success:false,                        //是否注册成功
              formData:{
                  phone:'',
                  code:'',
                  password:'',
                  password1:'',
                  sn:''
              },
              phone_status:false,
              code_status:false,
              pass_status:false,
              pass1_status:false,
              color:'default',
              disabled:true
            }
        },
        ready(){
            utils.loadingHide();
        },
        methods:{
           validate(obj){
              if(obj.name=='phone'){
                  this.phone_status=obj.status;                   
              }
              if(obj.name=='code'){
                  this.code_status=obj.status;                   
              }
              if(obj.name=='password'){
                  this.pass_status=obj.status;                   
              }
              if(obj.name=='password1'){
                  this.pass1_status=obj.status;                   
              }
              if(this.phone_status && this.code_status && this.pass_status && this.pass1_status){
                this.color='org';
                this.disabled=false;
              }else{
                this.color='default';
                this.disabled=true;
              }
           },
           send_out(){
                let that=this;
                utils.ajax({
                    url: basepath + "/mall/sms/send",
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({'phone':that.formData.phone}),
                    success: function(data){
                        if(data.success){
                            that.formData.sn=data.obj;
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            },
            modify(){
              if(!this.disabled){
                  var pa=/^[a-zA-Z]{6,20}$/;
                  if(pa.test(this.formData.password)){
                    this.$vux.alert.show('密码格式有误，请输入6-20位数字、字母或常用符号的组合，不能包含空格，字母区分大小写');
                    return false
                  }
                  var pa1=/^[\~.!@#_\+]{6,20}$/;
                  if(pa1.test(this.formData.password)){
                    this.$vux.alert.show('密码格式有误，请输入6-20位数字、字母或常用符号的组合，不能包含空格，字母区分大小写');
                    return false
                  }
                  var pa2=/^[0-9]{6,20}$/;
                  if(pa2.test(this.formData.password)){
                    this.$vux.alert.show('密码格式有误，请输入6-20位数字、字母或常用符号的组合，不能包含空格，字母区分大小写');
                    return false
                  }
                  var pa3=/^[a-zA-Z0-9\~!.@#_\+]{6,20}$/;
                  if(!pa3.test(this.formData.password)){
                    this.$vux.alert.show('密码格式有误，请输入6-20位数字、字母或常用符号的组合，不能包含空格，字母区分大小写');
                    return false
                  }
                  if(this.formData.password!=this.formData.password1){
                    this.$vux.alert.show('两次密码输入不一致');
                    return false
                  }
                  let that=this;
                  utils.ajax({
                      url: basepath + "/mall/seller/forgot_password",
                      dataType: 'json',
                      type: 'POST',
                      data:JSON.stringify({
                        'phone':that.formData.phone,
                        'security_code':that.formData.code,
                        'new_password':md5(that.formData.password),
                        'confirm_password':md5(that.formData.password1),
                        'sn':that.formData.sn
                      }),
                      success: function(data){
                          if(data.success){
                              that.success=true;
                          }else{
                              that.$vux.alert.show(data.msg);
                          }
                      }
                  });
                }
              }
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cInput":require('../../components/x-input.vue'),
            "cButton":require('../../components/button.vue'),
            "cButtonSend":require('../../components/button-send.vue'),
            "cMsg": require("../../components/msg.vue")
        }
    }
</script>

