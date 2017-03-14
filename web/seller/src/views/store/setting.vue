<template>
<div class="tabbar-wrap2">
    <div v-if="!res.inv_set">
         <c-top-back></c-top-back>
         <c-banner-info :title="res.formData.name" @upload='upload' :upload-type='res.uploadType' :logo.sync="res.formData.logo" banner='http://pm2.fvt.xiaovpu.com/wap/order/seller/images/banner.png'>
        </c-banner-info>
         <c-group>
             <c-input title="店铺名称" @validate="validate" placeholder="请输入店铺名称" name="name" :value.sync="res.formData.name" is-type="name" ></c-input>
             <c-city title="区域" :value.sync="res.formData.addr_code" @on-hide="logHide" placeholder="您所在的省市县" ></c-city>
         </c-group> 
         <c-group>   
             <c-input title="详细地址" @validate="validate" placeholder="请输入您的详细地址" name="address" :value.sync="res.formData.address" :min="1" :max="50"></c-input>
             <c-input title="客服电话" @validate="validate" placeholder="请输入客服电话" name="phone" :value.sync="res.formData.phone" :min="1" :max="50"></c-input>
             <c-input title="QQ号码" @validate="validate" placeholder="请输入QQ号码(选填)" name="QQ" :value.sync="res.formData.QQ"></c-input>
         </c-group>
         <c-cell-wrap>
             <c-cell title="订单超时" is-link :value='res.formData.order_time' @click="order_time"></c-cell>
             <c-cell title="发票设置" is-link :value='res.formData.inv_type' @click='open_set'></c-cell>
         </c-cell-wrap>
         <c-addr title="订单超时" @get-addressid="getTimeId"  @address-add="cancel" :show.sync="res.timeStatus" :address="res.time" type="default" size='block' :mstyle=true btn-txt="取消"></c-addr>
         <div class="wrap-pd">
             <c-button text="保存" :type="res.color" :disabled="res.disabled" @click="preserve" size="block"></c-button>
         </div>
    </div>

    <div v-else>
        <c-top-back></c-top-back>
        <c-group>
            <c-switch title="是否可以开发票" :value.sync="res.open" @on-change="open_status"></c-switch>
        </c-group>
        <div v-if="res.open">
            <c-cell-wrap title="选择发票类型">
                <c-checklist :options="res.inv_list" :value.sync="res.inv_default" :required=false @on-change="inv_change"></c-checklist>
            </c-cell-wrap>
            <c-cell-wrap v-if="res.inv_type_status">
                <c-cell title="发票类目" is-link :value='res.inv_type' @click="inv_category_type"></c-cell>
            </c-cell-wrap>
        </div>
        <div class="wrap-pd">
            <c-button text="确定" :type="res.color1" :disabled="res.disabled1" @click="preserve1" size="block"></c-button>
        </div>
    </div>
    
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                res:{
                    formData:{               //填写的数据
                        logo:'http://pm2.fvt.xiaovpu.com/wap/order/seller/images/logo.png',
                        phone:'',             //手机号
                        name:'',              //姓名                           
                        QQ:'',             //邮编
                        address:'',             //详细地址
                        addr_code:[],             //省市区
                        order_time:'30分钟',          //订单超时
                        time:'30',
                        inv_type:'不可开发票'             //发票设置
                    },
                    color:'org',            //保存按钮颜色
                    name_status:true,          //收货人校检
                    phone_status:true,             //手机号校检
                    addr_status:true,             //详细地址校检
                    disabled:false,             //是否禁用保存按钮
                    addr_code_status:true,            //省市区校检
                    timeStatus:false,                     //订单超时弹框是否显示
                    time:[{id:1,trueName:"30分钟"},{id:2,trueName:"1小时"},{id:3,trueName:"2小时"},{id:4,trueName:"4小时"},{id:5,trueName:"8小时"},{id:6,trueName:"24小时"}],                           //可选择的超时时间name
                    time_num:["30","60","120","240","480","1440"],                           //可选择的超时时间
                    color1:'org',            //确定按钮颜色
                    disabled1:false,             //是否禁用确定按钮
                    open:false,                            //是否可以开发票
                    inv_list:['普通发票','增值税发票'],    //发票类型列表
                    inv_default:[],                       //发票类型默认选中项
                    inv_type:'未设置',                              //发票类目
                    inv_type_status:false,                                //是否显示发票类目
                    inv_selected:[],                                           //选中的类目
                    inv_set:false,
                    uploadType:true
                }
            }
        },

        methods:{
            preserve(){
                if(!this.res.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/mall/store/info_update",
                        dataType: 'json',
                        type: 'POST',
                        data:JSON.stringify({
                            'logo':that.res.formData.logo,
                            'name':that.res.formData.name,
                            'phone':that.res.formData.phone,
                            'qq':that.res.formData.QQ,
                            'address':that.res.formData.address,
                            'province':that.res.formData.addr_code[0],
                            'city':that.res.formData.addr_code[1],
                            'county':that.res.formData.addr_code[2],
                            'invoice_flag':that.res.formData.inv_type=='不可开发票'?0:1,
                            'time':that.res.formData.time*1,
                            'inv_selected':that.res.formData.inv_type=='可开发票'?that.res.inv_selected:[]
                        }),
                        success: function(data){
                            if(data.success){
                                utils.go({name:'store',query:{id:1}},that.$router,true);
                            }else{
                                that.$vux.alert.show('店铺设置失败');
                            }
                        }
                    });
                } 
            },
            getTimeId(id,value){
              this.res.timeStatus=false;
              this.res.formData.order_time=value.trueName;
              this.res.formData.time=this.res.time_num[id-1];
              
            },
            order_time(){
              this.res.timeStatus=true;
            },
            cancel(){
                this.res.timeStatus=false;
            },
            validate(obj){
                if(obj.name=='name'){
                    this.res.name_status = obj.status;
                }
                if(obj.name=='phone'){
                    this.res.phone_status = obj.status;
                }
                if(obj.name=='address'){
                    this.res.addr_status = obj.status;
                }
                if(this.res.name_status && this.res.phone_status && this.res.addr_status && this.res.addr_code_status){
                    this.res.color='org';
                    this.res.disabled=false;
                }else{
                    this.res.color='default';
                    this.res.disabled=true;
                }
            },
            logHide(obj){
                this.res.formData.addr_code=obj.code;
                this.res.addr_code_status = true;
                if(this.res.name_status && this.res.phone_status && this.res.addr_status && this.res.addr_code_status){
                    this.res.color='org';
                    this.res.disabled=false;
                }
            },
            inv_change(v){
                this.res.inv_selected=v;
                this.judge(v);
                for(var i=0;i<v.length;i++){
                    if(v[i]=='普通发票') this.res.inv_selected[i]={invoice_type:'NORMAL',invoice_type_name:'普通发票'};
                    if(v[i]=='增值税发票') this.res.inv_selected[i]={invoice_type:'VAT',invoice_type_name:'增值税发票'};
                }
            },
            preserve1(){
                if(!this.res.disabled1){
                    if(this.res.open){
                        this.res.formData.inv_type='可开发票';
                    }else{
                        this.res.formData.inv_type='不可开发票';
                    }
                    this.res.inv_set=false;
                }
            },
            open_status(v){
                if(!v){
                    this.res.disabled1=false;
                    this.res.color1='org';
                }else{
                    this.res.disabled1=true;
                    this.res.color1='default';
                    let arr=[];
                    for(var i=0;i<this.res.inv_selected.length;i++){
                        arr.push(this.res.inv_selected[i].invoice_type_name);
                    }
                    this.judge(arr);
                }
            },
            judge(v){
                if(v.indexOf('普通发票')==-1){
                    this.res.inv_type_status=false;
                    if(v.length){
                        this.res.disabled1=false;
                        this.res.color1='org';
                    }else{
                        this.res.disabled1=true;
                        this.res.color1='default';
                    }
                }else{
                    this.res.inv_type_status=true;
                    if(this.res.inv_type=='已设置'){
                        this.res.disabled1=false;
                        this.res.color1='org';
                    }else{
                        this.res.disabled1=true;
                        this.res.color1='default';
                    }
                }
            },
            open_set(){
                this.res.inv_set=true;
            },
            upload(basr64){
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/content/base64_upload",
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({'base64ImgStr':basr64}),
                    success: function(data){
                        if(data.success){
                            that.res.formData.logo=data.obj.url;
                        }else{
                            that.$vux.alert.show('上传失败');
                        }
                    }
                });
            },
            inv_category_type(){
                utils.setSession("setting",this.res);
                utils.go({name:'category'},this.$router);
            }
            
        },
        ready(){
            
            if(utils.getSession("setting")){
                this.res=JSON.parse(utils.getSession("setting"));
                utils.delSession("setting");
            }else{
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/store/get",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        if(data.success){
                            that.res.formData.name=data.obj.name;
                            if(data.obj.logo) that.res.formData.logo=data.obj.logo;
                            that.res.formData.phone=data.obj.phone;
                            that.res.formData.QQ=data.obj.qq;
                            that.res.formData.address=data.obj.address;
                            that.res.formData.addr_code=[data.obj.province,data.obj.city,data.obj.county];
                            that.res.formData.order_time=data.obj.time/60<1?data.obj.time+'分钟':data.obj.time/60+'小时';
                            that.res.formData.time=data.obj.time;
                            if(data.obj.invoice_flag){
                                that.res.formData.inv_type='可开发票';
                                that.res.open=true;
                                that.res.inv_selected=data.obj.inv_selected;
                                for(var i=0;i<that.res.inv_selected.length;i++){
                                    that.res.inv_default.push(that.res.inv_selected[i].invoice_type_name);
                                    if(that.res.inv_selected[i].invoice_type_name=='普通发票'){
                                        utils.ajax({
                                            url: basepath + "/mall/invoice_category/list",
                                            dataType: 'json',
                                            type: 'POST',
                                            async:false,
                                            success: function(data){
                                                if(data.success){
                                                    if(data.obj.list.length){
                                                        that.res.inv_type='已设置';
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                                that.judge(that.res.inv_default);
                            }else{
                                that.res.formData.inv_type='不可开发票';
                                that.res.open=false;
                            }
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            }
            if(this.$route.query.category){
                this.res.inv_type='已设置';
                this.res.disabled1=false;
                this.res.color1='org';
            }else{
                this.res.inv_type='未设置';
                this.res.disabled1=true;
                this.res.color1='default';

            }
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cInput":require('../../components/x-input.vue'),
            "cButton":require('../../components/button.vue'),
            "cBannerInfo":require('../../components/banner-info.vue'),
            "cCity":require('../../components/city.vue'),
            "cCellWrap": require('../../components/cell-wrap.vue'),
            "cCell": require('../../components/cell.vue'),
            "cSwitch": require('../../components/switch.vue'),
            "cChecklist": require('../../components/checklist.vue'),
            "cAddr": require('../../components/address.vue')
        }
    }
</script>

