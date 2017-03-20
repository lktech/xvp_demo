<template>
<div>
    <c-top-back></c-top-back>
    <c-cell-wrap title="选择类型">
        <c-cell title="实名主体" is-link :value='subject' @click="choice_sub"></c-cell>
    </c-cell-wrap>
    
    <c-cell-wrap title="请填写实名信息" v-if="!subject_type">
        <c-input title="姓名" @validate="validate" placeholder="请输入姓名" name="name" :value.sync="formData.name" is-type="name" ></c-input>
        <c-cell title="证件类型" is-link :value='card' @click="choice_card"></c-cell>
        <c-input title="证件号码" @validate="validate" placeholder="请输入证件号码" name="card_code" :value.sync="formData.card_code" :min="5"></c-input>
    </c-cell-wrap>

    <c-cell-wrap title="请填写实名信息" v-else>
        <c-input title="企业名称" @validate="validate" placeholder="请输入企业名称" name="company_name" :value.sync="formData.company_name" is-type="name" ></c-input>
        <c-input title="营业执照" @validate="validate" placeholder="请输入营业执照" name="business_code" :value.sync="formData.business_code" :min="5"></c-input>
        <c-uploadmul title='上传执照' :list="formData.img_list" @upload="upload" :max="1"></c-uploadmul>
    </c-cell-wrap>
    <c-addr title="实名主体" @get-addressid="getsubId"  @address-add="cancel_sub" :show.sync="subject_status" :address="subject_list" :mstyle=true btn-txt="取消"></c-addr>
    <c-addr title="选择证件" @get-addressid="getcardId"  @address-add="cancel_card" :show.sync="card_status" :address="card_list" :mstyle=true btn-txt="取消"></c-addr>
    <div class="wrap-pd">
        <c-button :type="color" text="确认" @click="hold" :disabled="disabled" size="block"></c-button>
    </div>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                subject:'个人',                                           //实名主体
                subject_status:false,                                   //是否弹出主体列表
                subject_list:[{id:1,trueName:"个人用户"},{id:2,trueName:"企业用户"}],   //主体列表
                card:'身份证',                             //证件
                card_status:false,                          //是否弹出证件列表
                card_list:[],                              // 证件列表
                color:'default',                            //保存按钮颜色
                disabled:true ,                             //是否禁用保存按钮
                formData:{                                //填写的数据
                    name:'',                                 // 姓名
                    card_code:'',                                // 证件号码
                    company_name:'',                                 // 企业名称
                    business_code:'' ,                               // 营业执照
                    img_list:[]                                      //营业执照图片
                },
                name_status:false ,                          // 姓名校验
                card_code_status:false ,                          // 证件校验
                subject_type:false ,                               //主体是否为企业
                company_name_status:false ,                          // 企业名称校验
                business_status:false                           // 营业执照校验
                
            }
        },

        methods:{
            validate(obj){
                if(obj.name=='name'){
                    this.name_status=obj.status;
                }else if(obj.name=='card_code'){
                    this.card_code_status=obj.status;
                }else if(obj.name=='company_name'){
                    this.company_name_status=obj.status;
                }else if(obj.name=='business_code'){
                    this.business_status=obj.status;
                }

                if(this.subject=='个人'){
                    if(this.name_status && this.card_code_status){
                        this.color='org';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }else{
                    if(this.company_name_status && this.business_status && this.formData.img_list.length){
                        this.color='org';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }
            },
            cancel_sub(){
                this.subject_status=false;
            },
            cancel_card(){
                this.card_status=false;
            },
            getsubId(idd){
                this.subject_status=false;
                if(idd==1){
                    this.subject='个人';
                    this.subject_type=false;
                    if(this.name_status && this.card_code_status){
                        this.color='org';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }else{
                    this.subject='企业';
                    this.subject_type=true;
                    if(this.company_name_status && this.business_status && this.formData.img_list.length){
                        this.color='org';
                        this.disabled=false;
                    }else{
                        this.color='default';
                        this.disabled=true;
                    }
                }
            },
            getcardId(idd){
                this.card_status=false;
                let that = this;
                $.each(that.card_list,function(i,v){
                    if(v.id==idd){
                        that.card=v.trueName;
                    }
                })
            },
            choice_sub(){
                this.subject_status=true;
            },
            choice_card(){
                this.card_status=true;
            },
            hold(){
                if(!this.disabled){
                   let that = this;
                   utils.ajax({
                       url: basepath + "/app/Authentication",
                       dataType: 'json',
                       type: 'POST',
                       data:{
                           'userId':this.$route.query.id,
                           'subject':that.subject,
                           'name':that.subject=='个人'?that.name:'',
                           'card':that.subject=='个人'?that.card:'',
                           'card_code':that.subject=='个人'?that.formData.card_code:'',
                           'company_name':that.subject=='企业'?that.formData.company_name:'',
                           'business_code':that.subject=='企业'?that.formData.business_code:'',
                           'img_list':that.subject=='企业'?that.formData.img_list[0]:''
                       },
                       success: function(data){
                           if(data.success){
                               utils.go({name:that.$router._currentTransition.from.name},that.$router,true);
                           }else{
                               that.$vux.alert.show(data.msg);
                           }
                       }
                   }); 
                }
            },
            upload(src){                
                let that = this;
                utils.ajax({
                    url: basepath + "/app/upload",
                    dataType: 'json',
                    type: 'POST',
                    data:{'src':src},
                    success: function(data){
                        if(data.success){
                            that.formData.img_list=[src];
                            if(that.company_name_status && that.business_status && that.formData.img_list.length){
                                that.color='org';
                                that.disabled=false;
                            }else{
                                that.color='default';
                                that.disabled=true;
                            }
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            }
        },
        ready(){
            utils.MenuShare();
            let that = this;
            utils.ajax({
                url: basepath + "/app/card",
                dataType: 'json',
                type: 'POST',
                data:{'id':'1'},
                success: function(data){
                    if(data.success){
                        that.card_list=data.obj;
                    }else{
                        that.$vux.alert.show(data.msg);
                    }
                }
            });
            
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
            "cCity":require('../../components/city.vue')
        }
    }
</script>

