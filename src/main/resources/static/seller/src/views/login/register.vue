<template>
<div>
    <div v-if="!success">
        <c-group>
            <c-input title="手机号" placeholder="请输入手机号" @on-change="validate" required v-model="formData.phone" name="phone" :max='11' is-type="china-mobile"></c-input>
            <c-input title="验证码" placeholder="请输入验证码" @on-change="validate" required v-model="formData.code" name="code" :max="6" class="weui_vcode">
                <c-button slot="right" type="primary" :validate="phone_status" @click.native="send_out">获取验证码</c-button>
            </c-input>
        </c-group>
        <c-group>
            <c-input title="密码" placeholder="请输入密码" type="password" @on-change="validate" required v-model="formData.password" name="password" :max="20" is-type="notnull"></c-input>
        </c-group>
        <div class="wrap-pd" style="margin-top:10px">
            <c-button :type="color" :disabled='disabled' text="注册" @click.native="register" size="block"></c-button>
        </div>
        <div class="wrap-pd" style='margin-top:10px;'>
          <c-button type="default" text="已有账号去登录" link="login" size="block"></c-button>
        </div>
    </div>
    <div v-else>
       <c-msg status="1" msg="注册成功">
           <c-button slot="btn" type="primary" link="login" size="block" text="返回登录页面"></c-button>
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
                  sn:''
              },
              phone_status:false,
              code_status:false,
              pass_status:false,
              color:'default',
              disabled:true
            }
        },
        mounted: function () {
            this.$nextTick(function () {
              utils.loadingHide();
            })
        },
        methods:{
           validate(obj){
              if(obj.name=='phone'){
                  this.phone_status=obj.valid;                   
              }
              if(obj.name=='code'){
                  this.code_status=obj.valid;                     
              }
              if(obj.name=='password'){
                  this.pass_status=obj.valid;                   
              }
              if(this.phone_status && this.code_status && this.pass_status){
                this.color='primary';
                this.disabled=false;
              }else{
                this.color='default';
                this.disabled=true;
              }
           },
           send_out(){
                let that=this;
                utils.ajax({
                    url: basepath + "/seller/seller/check",
                    dataType: 'json',
                    type: 'POST',
                    data:{'phone':that.formData.phone},
                    success: function(data){
                        if(data.result.flag=="no"){
                            utils.ajax({
                                url: basepath + "/seller/seller/verify",
                                dataType: 'json',
                                type: 'POST',
                                data:{'phone':that.formData.phone},
                                success: function(data){
                                    if(data.code=="SUCESS"){
                                        that.formData.sn=data.result.sn;
                                    }else{
                                        that.$vux.alert.show('发送失败，请重试');
                                    }
                                }
                            });
                        }else{
                            that.$vux.alert.show({content:'手机号已注册,点击确定去登陆',onHide :function(){
                              utils.go({name:'login'},that.$router);
                            }});
                        }
                    }
                });
            },
            register(){
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
                    let that=this;
                    utils.ajax({
                        url: basepath + "/seller/seller/register",
                        dataType: 'json',
                        type: 'POST',
                        data:{
                          'phone':that.formData.phone,
                          'verfiy_code':that.formData.code,
                          'password':md5(that.formData.password),
                          'sn':that.formData.sn
                        },
                        success: function(data){
                            if(data.code=="SUCESS"){
                                that.success=true;
                            }else{
                                that.$vux.alert.show(data.message);
                            }
                        }
                    });
                }
            }
        },
        components:{
            "cGroup":require('../../components/group/group.vue'),
            "cTopBack":require('../../components/x-top-back/x-top-back.vue'),
            "cInput":require('../../components/input/input.vue'),
            "cButton":require('../../components/button/button.vue'),
            "cButtonSend":require('../../components/x-button-send/x-button-send.vue'),
            "cMsg": require("../../components/x-messages/x-messages.vue")
        }
    }
</script>

