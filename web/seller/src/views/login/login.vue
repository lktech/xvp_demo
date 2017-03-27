<template>
<div>
    <div>
        <c-group>
            <c-input title="手机号" placeholder="请输入手机号" @on-change="validate" v-model="formData.phone" name="phone" is-type="china-mobile" :max='11'></c-input>
            <c-input title="密码 " type="password" placeholder="请输入密码" @on-change="validate" name="password" required v-model="formData.password" :max='20'></c-input>
        </c-group>
        <div class="wrap-pd" style="margin-top:10px">
            <c-button :disabled="disabled" :type="color" text="登录" @click.native="login" size="block"></c-button>
        </div>
        <div class="wrap-pd" style='margin-top:10px;'>
          <c-button text="注册" size="block" link="register"></c-button>
        </div>
        <div style="color:#5ABA5A; margin-top:10px; font-size:14px; margin-left:10px" @click="forget">忘记密码</div>
    </div>
    <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
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
                  password:''
              },
              phone_status:false,
              pass_status:false,
              color:'default',
              disabled:true
            }
        },
        mounted: function () {
            this.$nextTick(function () {
              utils.loadingHide();
              //this.login1('1');
            })
        },
        methods:{
          validate(obj){
              if(obj.name=='phone'){
                  this.phone_status=obj.valid;                   
              }
              if(obj.name=='password'){
                  this.pass_status=obj.valid;                
              }
              if(this.phone_status && this.pass_status){
                this.color='primary';
                this.disabled=false;
              }else{
                this.color='default';
                this.disabled=true;
              }
          },
          login(){
            if(!this.disabled){
              let that=this;
              utils.ajax({
                  url: basepath + "/seller/seller/login",
                  dataType: 'json',
                  type: 'POST',
                  data:{
                    'phone':that.formData.phone,
                    'password':md5(that.formData.password)
                  },
                  success: function(data){
                      console.log(data)
                      if(data.code=="SUCESS"){
                          //utils.header();
                          that.login1();
                      }else{
                          that.$vux.alert.show('用户名或密码不正确');
                      }
                  }
              });
            }
          },
          login1(){
            let that=this;
            utils.ajax({
                url: basepath + "/seller/store/get",
                dataType: 'json',
                type: 'POST',
                success: function(data){
                    if(data.code=="SUCESS"){
                        utils.go({path:'/store/store'},that.$router);
                    }else{
                        utils.go({path:'/store/addinfo'},that.$router);
                    }
                }
            });
          },
          forget(){
            utils.go({path:'forget'},this.$router);
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

