<template>
<div>
    <div>
        <c-group>
            <c-input title="手机号" placeholder="请输入手机号" @validate="validate" :value.sync="formData.phone" name="phone" is-type="mobile" :max='11'></c-input>
            <c-input title="密码" type="password" placeholder="请输入密码" @validate="validate" :value.sync="formData.password" name="password" :max='20' is-type="notnull"></c-input>
        </c-group>
        <div class="wrap-pd">
            <c-button :type="color" :disabled='disabled' text="登录" @click="login" size="block"></c-button>
        </div>
        <div class="wrap-pd" style='margin-top:10px;'>
          <c-button type="default" text="注册" :link="{name:'register'}" size="block"></c-button>
        </div>
        <div style="color:#5ABA5A; margin-top:10px; font-size:14px; margin-left:10px" @click="forget">忘记密码</div>
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
                  password:''
              },
              phone_status:false,
              pass_status:false,
              color:'default',
              disabled:true
            }
        },
        ready(){
            utils.loadingHide();
            this.login1('1');
        },
        methods:{
          validate(obj){
              if(obj.name=='phone'){
                  this.phone_status=obj.status;                   
              }
              if(obj.name=='password'){
                  this.pass_status=obj.status;                   
              }
              if(this.phone_status && this.pass_status){
                this.color='org';
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
                  url: basepath + "/mall/auth",
                  dataType: 'json',
                  type: 'POST',
                  data:JSON.stringify({
                    'phone':that.formData.phone,
                    'password':md5(that.formData.password)
                  }),
                  success: function(data){
                      if(data.success){
                          utils.header();
                          that.login1();
                      }else{
                        if(data.msg.indexOf('用户或密码名不正确')!=-1){
                          that.$vux.alert.show('用户名或密码不正确');
                        }
                        if(data.msg.indexOf('授权失败')!=-1){
                          utils.go({name:'storeAddInfo'},that.$router);
                        }
                      }
                  }
              });
            }
          },
          login1(q){
            let that=this;
            utils.ajax({
                url: basepath + "/mall/auth",
                dataType: 'json',
                type: 'POST',
                success: function(data){
                    if(data.success){
                        
                        utils.go({name:'store'},that.$router);
                    }else{
                      if(!q){
                        utils.go({name:'storeAddInfo'},that.$router);
                      } 
                    }
                }
            });
          },
          forget(){
            utils.go({name:'forget'},this.$router);
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

