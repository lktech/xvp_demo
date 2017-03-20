<template>
<div>
    <c-top-back></c-top-back>
    <c-group>
        <c-input title="收货人" @validate="validate" placeholder="请输入姓名" name="name" :value.sync="formData.name" is-type="name" ></c-input>
        <c-input title="电话" @validate="validate" placeholder="请输入手机号" name="phone" :value.sync="formData.phone" is-type="mobile" ></c-input>
        <c-group>
            <c-city title="" @on-hide="logHide" :value.sync="formData.addr_code" placeholder="选择省市区" inline-desc="选择地区"></c-city>
        </c-group>
        <c-input title="详细地址" @validate="validate" placeholder="请输入详细地址" name="address" :value.sync="formData.address" :min="5"></c-input>
        <c-input title="邮编" @validate="validate" placeholder="请输入邮编" name="post_code" :value.sync="formData.post_code" is-type="number" :min="6" :max="6"></c-input>
    </c-group>
    <div class="wrap-pd">
        <c-button text="保存" :type="color" :disabled="disabled" @click="preserve" size="block"></c-button>
    </div>
    <div class="wrap-pd" style='margin-top:10px;'>
        <c-button text="删除" @click="delete_addr" type="default" size="block"></c-button>
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
                    post_code:'',             //邮编
                    address:'',             //详细地址
                    addr_code:[],             //省市区
                    province_name:'',
                    city_name:'',
                    county_name:''
                },
                color:'org',            //保存按钮颜色
                name_status:true,          //收货人校检
                phone_status:true,             //手机号校检
                code_status:true,             //邮编校检
                addr_status:true,             //详细地址校检
                disabled:false,             //是否禁用保存按钮
                addr_code_status:true             //省市区校检
            }
        },

        methods:{
            init(id){
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/user_address/get",
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({id:id}),
                    success: function(data){
                        if(data.success){
                            that.formData.name = data.obj.name;
                            that.formData.phone = data.obj.phone;
                            that.formData.post_code = data.obj.post_code;
                            that.formData.address = data.obj.address;
                            that.formData.addr_code = data.obj.addr_code;
                            that.formData.province_name = data.obj.addr_name[0];
                            that.formData.city_name = data.obj.addr_name[1];
                            that.formData.county_name = data.obj.addr_name[2];
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            },
            preserve(){
                if(!this.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/mall/user_address/update",
                        dataType: 'json',
                        type: 'POST',
                        data:JSON.stringify({
                            'id':this.$route.params.id,
                            'name':that.formData.name,
                            'phone':that.formData.phone,
                            'post_code':that.formData.post_code,
                            'address':that.formData.address,
                            'province':that.formData.addr_code[0],
                            'city':that.formData.addr_code[1],
                            'county':that.formData.addr_code[2],
                            'province_name':that.formData.province_name,
                            'city_name':that.formData.city_name,
                            'county_name':that.formData.county_name
                        }),
                        success: function(data){
                            if(data.success){
                                var link=that.$router._currentTransition.from.name;
                                if(link=='odadd'){                   //提交订单页面
                                    utils.go({name:link,query:{id:data.obj.id}},that.$router,true);
                                }else if(link=='invoice'){           //发票页面
                                    sessionStorage.setItem('add_',JSON.stringify(that.formData));
                                    utils.go({name:link,query:{mod:1}},that.$router,true);
                                }
                            }else{
                                that.$vux.alert.show(data.msg);
                            }
                        },
                        message:'正在保存'
                    });
                } 
            },
            delete_addr(){
                utils.loadingShow();
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/user_address/delete",
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({'id':this.$route.params.id}),
                    success: function(data){
                        if(data.success){
                            var link=that.$router._currentTransition.from.name;
                            if(link=='odadd'){                   //提交订单页面
                                utils.go({name:link,query:{}},that.$router,true);
                            }else if(link=='invoice'){           //发票页面
                                utils.go({name:link,query:{mod:1}},that.$router,true);
                            }
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
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
                if(obj.name=='post_code'){
                    this.code_status = obj.status;
                }
                if(this.name_status && this.phone_status && this.addr_status && this.code_status && this.addr_code_status){
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
                if(this.name_status && this.phone_status && this.addr_status && this.code_status && this.addr_code_status){
                    this.color='org';
                    this.disabled=false;
                }
            }
            
        },
        ready(){
            utils.MenuShare();
            this.init(this.$route.params.id);
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

