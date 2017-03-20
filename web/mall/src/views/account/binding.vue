<template>
<div>
    <c-top-back></c-top-back>
    <div v-if="!res.verification">
        <c-cell-wrap :title="res.title">
            <c-cell title="发卡银行" is-link :value='res.formData.bank' @click="choice_bank"></c-cell>
            <c-group>
                <c-city title="卡所在地" @on-hide="logHide" ></c-city>
            </c-group>
        </c-cell-wrap>
        <c-cell-wrap>
            <c-cell title="开户行" is-link :value='res.formData.open_bank' @click="choice_open_bank"></c-cell>
            <c-input title="银行卡" :min="16" :max="20" @validate="validate" placeholder="请输入银行卡号" name="bank_code" :value.sync="res.formData.bank_code" is-type="number" ::min="12"></c-input>
        </c-cell-wrap>
        <c-cell-wrap v-if="res.account">
            <c-input title="真实姓名" :max="20" @validate="validate" placeholder="请输入真实姓名" name="name" :value.sync="res.formData.name" is-type="name"></c-input>
            <c-input title="身份证号" :max="18" :min="15" @validate="validate" placeholder="请输入身份证号" name="id_code" :value.sync="res.formData.id_code"></c-input>
        </c-cell-wrap>
        <c-cell-wrap v-else>
            <c-input title="企业名称" :max="50" @validate="validate" placeholder="请输入企业名称" name="comp_name" :value.sync="res.formData.comp_name" is-type="name"></c-input>
            <c-input title="营业执照" :max="20" @validate="validate" placeholder="请输入营业执照号" name="license" :value.sync="res.formData.license"></c-input>
        </c-cell-wrap>
        <c-addr title="发卡银行" @get-addressid="getbankId"  @address-add="cancel_bank" :show.sync="res.bank_status" :address="res.bank_list" :mstyle=true btn-txt="取消"></c-addr>
        <c-addr title="开户行" @get-addressid="getopenId"  @address-add="cancel_bank" :show.sync="res.open_bank_status" :address="res.open_bank_list" :mstyle=true btn-txt="取消"></c-addr>
        <div class="wrap-pd">
            <c-button :type="res.color" text="下一步" @click="next" :disabled="res.disabled" size="block"></c-button>
            <c-button type="default" text="返回" @click="back_return" size="block"></c-button>
        </div>
    </div>
    <div v-else>
        <c-group>
            <c-input title="手机号" placeholder="请输入手机号" @validate="validate" :value.sync="res.formData.phone" name="phone" is-type="mobile"></c-input>
            <c-input title="验证码" placeholder="请输入验证码" @validate="validate" :value.sync="res.formData.code" name="code" :min="1">
                <c-button-send slot="right" type="primary" :validate="res.phone_status" @on-send="send_out">获取验证码</c-button-send>
            </c-input>
        </c-group>
        <c-agree terms="绑卡协议" @on-change="agreeChange" @showterms="agreeShowTerms" :checked="res.agree_status"></c-agree>
        <c-button :type="res.color1" text="启用并绑定" @click="binding" :disabled="res.disabled1" size="block"></c-button>
    </div>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return { 
                res:{
                    color:'default',                        //下一步按钮颜色
                    disabled:true,                                 //是否禁用下一步按钮  
                    color1:'default',                        //绑定按钮颜色
                    disabled1:true,                                 //是否禁用绑定按钮            
                    title:'个人账户提现账号绑定',                 //title
                    formData:{
                        bank:'',                                        //发卡银行
                        bank_code:'',                                        //银行账号
                        name:'',                                        //真实姓名
                        id_code:'',                                        //身份证号
                        comp_name:'',                                        //企业名称
                        license:'',                                        //营业执照
                        open_bank:'',                                        //开户行
                        phone:'',                                               //手机号
                        code:'',                                             //验证码
                        bank_addr:''                                           //卡所在地
                    },
                    bank_status:false,                                  //发卡银行列表是否显示
                    bank_list:[],                                    //发卡银行列表
                    open_bank_list:[],                                    //开户行列表
                    bank_addr_status:false,                                  //卡所在地校验
                    bank_code_status:false,                                  //银行账号校验
                    name_status:false,                                  //真实姓名校验
                    id_code_status:false,                                  //身份证校验
                    comp_name_status:false,                                  //企业名称校验
                    license_status:false,                                  //营业执照校验
                    open_bank_status:false,                                  //开户行列表是否显示
                    account:true,                                            //是否为个人账户
                    verification:false,                                          //是否显示发送验证码
                    phone_status:false,                                  //手机号校验
                    code_status:false,                                  //验证码校验
                    agree_status:false                                  //验证码校验
                }

            }
        },
        ready(){
            utils.MenuShare();
            utils.loadingHide();
            if(this.$route.query.id){
                this.res.title='企业账户提现账号绑定';
                this.res.account=false;
            }
            if(sessionStorage.getItem('data_')){
                this.res=JSON.parse(sessionStorage.getItem('data_'));
            }
        },
        methods:{
            choice_bank(){              //点击发卡银行显示列表
                let that=this;
                utils.ajax({
                    url: basepath + "/app/bank",
                    dataType: 'json',
                    type: 'POST',
                    data:{},
                    success: function(data){
                        if(data.success){
                            that.res.bank_list=data.obj;
                            that.res.bank_status=true;
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            },
            getbankId(idd){                             
                let that=this
                $.each(this.res.bank_list,function(i,v){
                    if(v.id==idd){
                        that.res.formData.bank=v.trueName;
                        that.res.bank_status=false;
                    }
                })
                this.judge();
            },
            cancel_bank(){
                this.res.bank_status=false;
                this.res.open_bank_status=false;
            },
            logHide(obj){
                this.res.bank_addr_status=true;
                this.res.bank_addr=obj.code;
                this.judge();
            },
            getopenId(idd){                             
                let that=this
                $.each(this.res.open_bank_list,function(i,v){
                    if(v.id==idd){
                        that.res.formData.open_bank=v.trueName;
                        that.res.open_bank_status=false;
                    }
                })
                this.judge();
            },
            choice_open_bank(){
                let that=this;
                utils.ajax({
                    url: basepath + "/app/open_bank",
                    dataType: 'json',
                    type: 'POST',
                    data:{},
                    success: function(data){
                        if(data.success){
                            that.res.open_bank_list=data.obj;
                            that.res.open_bank_status=true;
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            },
            validate(obj){
                if(obj.name=='bank_code'){
                    this.res.bank_code_status=obj.status;
                }
                if(obj.name=='name'){
                    this.res.name_status=obj.status;
                }
                if(obj.name=='id_code'){
                    this.res.id_code_status=obj.status;
                }
                if(obj.name=='comp_name'){
                    this.res.comp_name_status=obj.status;
                }
                if(obj.name=='license'){
                    this.res.license_status=obj.status;                   
                }
                if(obj.name=='phone'){
                    this.res.phone_status=obj.status;                   
                }
                if(obj.name=='code'){
                    this.res.code_status=obj.status;                   
                }
                this.judge();
                this.judge1();
            },
            back_return(){
                var link=that.$router._currentTransition.from.name;
                utils.go({name:link,query:{}},that.$router,true);
            },
            next(){
                if(!this.res.disabled){
                    this.res.verification=true;
                }  
            },
            send_out(){
                let that=this;
                utils.ajax({
                    url: basepath + "/app/send_out",
                    dataType: 'json',
                    type: 'POST',
                    data:{},
                    success: function(data){
                        if(data.success){

                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            },
            binding(){
                if(!this.res.disabled1){
                    let that=this;
                    utils.ajax({
                        url: basepath + "/app/send_out",
                        dataType: 'json',
                        type: 'POST',
                        data:{
                            'userId':this.$route.query.id,
                            'bank':that.res.formData.bank,                                        //发卡银行
                            'bank_code':that.res.formData.bank_code,                                        //银行账号
                            'name':that.res.formData.name,                                        //真实姓名
                            'id_code':that.res.formData.id_code,                                        //身份证号
                            'comp_name':that.res.formData.comp_name,                                        //企业名称
                            'license':that.res.formData.license,                                        //营业执照
                            'open_bank':that.res.formData.open_bank,                                        //开户行
                            'bank_addr':that.res.formData.bank_addr
                        },
                        success: function(data){
                            if(data.success){
                                var link=that.$router._currentTransition.from.name;
                                utils.go({name:link,query:{}},that.$router,true);
                            }else{
                                that.$vux.alert.show(data.msg);
                            }
                        }
                    });
                }
            },
            agreeChange(status){
                this.res.agree_status=status;
                this.judge1();
            },
            agreeShowTerms(){
                sessionStorage.setItem('data_',JSON.stringify(this.res));
                utils.go({name:'agreement'},this.$router,true);
            },
            judge(){
                if(this.res.account){
                    if(this.res.name_status && this.res.id_code_status && this.res.bank_code_status && this.res.bank_addr_status && this.res.formData.bank!='' && this.res.formData.open_bank!=''){
                        this.res.disabled=false;
                        this.res.color='org';
                    }else{
                        this.res.disabled=true;
                        this.res.color='default';
                    }
                }else{
                    if(this.res.comp_name_status && this.res.license_status && this.res.bank_code_status && this.res.bank_addr_status && this.res.formData.bank!='' && this.res.formData.open_bank!=''){
                        this.res.disabled=false;
                        this.res.color='org';
                    }else{
                        this.res.disabled=true;
                        this.res.color='default';
                    }
                }
            },
            judge1(){
                if(this.res.verification){
                    if(this.res.phone_status && this.res.code_status && this.res.agree_status){
                        this.res.disabled1=false;
                        this.res.color1='org';
                    }else{
                        this.res.disabled1=true;
                        this.res.color1='default';
                    }
                }
            }
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cInput":require('../../components/x-input.vue'),
            "cButton":require('../../components/button.vue'),
            "cCellWrap": require('../../components/cell-wrap.vue'),
            "cCell": require('../../components/cell.vue'),
            "cAddr": require('../../components/address.vue'),
            "cUploadmul": require('../../components/uploadmul.vue'),
            "cCity":require('../../components/city.vue'),
            "cAgree":require('../../components/agree.vue'),
            "cButtonSend":require('../../components/button-send.vue')
        }
    }
</script>

