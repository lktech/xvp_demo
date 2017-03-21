<template>
    <div class="tabbar-wrap2">
        <div>
            <c-top-back></c-top-back>
            <c-cell-wrap  v-if="commodity_no!=''">
                <c-cell title=""></c-cell>
            </c-cell-wrap>
            <c-cell-wrap title="选择发票类型">
                <c-cell title="发票类型" is-link :value='res.inv_type' @click="Choice"></c-cell>
            </c-cell-wrap>
            <div v-if="res.increment">
                <c-cell-wrap  v-if="!res.mail_addr_status">
                    <c-cell title="添加发票邮寄地址" is-link @click="addmail">
                        <img slot="icon" src="../../assets/images/icon-add.png">
                    </c-cell>
                </c-cell-wrap>
                <c-cell-wrap title='发票邮寄地址'  v-else>
                    <c-address-info id="1" :name="res.mail_name" :phone="res.mail_phone" :address="res.mail_addr" arrows="true" @click="addr_mail"></c-address-info>
                </c-cell-wrap>
            </div>
            <div v-if="res.inv_ordinary">
                <c-cell-wrap title="发票抬头">
                    <c-radio :value="res.inv_default_rise" @on-change='rise_change' :options="res.options"></c-radio>
                    <c-input v-if="res.company_status" @validate="validate" title="单位名称" placeholder="请输入单位名称" name="risename" :value.sync="res.formData.company_name" is-type="notnull"></c-input>
                </c-cell-wrap>
                <c-cell-wrap title="发票内容">
                    <c-radio :value="res.inv_default_cen" @on-change='cen_change' :options="res.content"></c-radio>
                </c-cell-wrap>
            </div>
            <c-cell-wrap title="发票信息" v-if="res.increment">
                <c-input @validate="validate" title="公司名称" placeholder="请输入公司名称" name="comp_name" :value.sync="res.formData.comp_name" is-type="notnull"></c-input>
                <c-input @validate="validate" title="识别码" placeholder="请输入纳税人识别码" name="identifica" :value.sync="res.formData.identifica" is-type="notnull"></c-input>
                <c-input @validate="validate" title="注册地址" placeholder="请输入注册地址" name="regist_addr" :value.sync="res.formData.regist_addr" is-type="notnull"></c-input>
                <c-input @validate="validate" title="注册电话" placeholder="请输入注册电话" name="regist_phone" :value.sync="res.formData.regist_phone" is-type="notnull"></c-input>
                <c-input @validate="validate" title="开户银行" placeholder="请输入开户银行" name="open_bank" :value.sync="res.formData.open_bank" is-type="notnull"></c-input>
                <c-input @validate="validate" title="银行账户" placeholder="请输入银行账户" name="bank_code" :value.sync="res.formData.bank_code" is-type="notnull"></c-input>
            </c-cell-wrap>
        </div>
        <c-addr title="发票类型" @get-addressid="getInvId"  @address-add="cancel" :show.sync="res.adsStatus" :address="res.invoice" type="default" size='block' :mstyle=true btn-txt="取消"></c-addr>
        <c-addr title="选择邮寄地址" maxhide=4 @get-addressid="getmailId" @callback="addrmod" @address-add="addmail" :show.sync="res.mailStatus" :address="res.mailaddr" type="default" size='block' :mstyle=true btn-txt="新增邮寄地址"></c-addr>
        <div class="wrap-pd">
            <c-button :type="res.color" text="保存" @click="hold" :disabled="res.disabled" size="block"></c-button>
        </div>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                res:{
                    inv_type:'无需发票',           //发票类型
                    color:'org',             //保存按钮颜色
                    adsStatus:false,                //是否弹出发票类型列表
                    mailStatus:false,                //是否弹出邮寄地址列表
                    invoice:[],                      //发票类型列表数据                       
                    mailaddr:[],                        //邮寄地址列表数据
                    options:[{id:1,name:'个人'},{id:2,name:'单位'}],            //发票抬头
                    content:[],                     //发票内容
                    inv_default_rise:1,              //发票抬头默认
                    inv_default_cen:'',              //发票内容默认
                    formData:{                      //填写的数据
                        company_name:'',                   //单位名称（发票抬头）
                        category_name:'',                         //发票内容
                        category_id:'',                         //发票内容id
                        inv_rise:'个人',                        //发票抬头
                        comp_name:'',                       //公司名称（增值税发票）
                        identifica:'',                        //识别码
                        regist_addr:'',                         //注册地址
                        regist_phone:'',                        //注册手机号
                        open_bank:'',                              //开户银行
                        bank_code:'',                            //银行账户
                        addr_id:''                                            //地址id
                    },
                    company_status:false,                       //是否显示单位名称input
                    inv_ordinary:false,                            //发票类型是否为普通发票
                    disabled:false,                              //是否禁用保存按钮
                    risename_status:false,                      //单位名称校检
                    increment:false,                            //发票类型是否为增值税发票
                    comp_name_status:false,                        //公司名称校检
                    identifica_status:false,                        //识别码校检
                    regist_addr_status:false,                       //注册地址校检
                    regist_phone_status:false,                              //注册手机号校检
                    open_bank_status:false,                         //开户银行校检
                    bank_code_status:false,                         //银行账户校检
                    mail_name:'',                                //邮寄地址名称
                    mail_phone:'',                            //邮寄地址手机号
                    mail_addr:'',                                 //邮寄地址
                    mail_addr_status:false,                              //是否选择邮寄地址
                    commodity_no:''                                     //不可开发票的商品
                    
                }
            }
        },
        ready(){
            utils.MenuShare();
            utils.loadingHide();
            let that = this;
            if(this.$route.query.com_name){
                this.commodity_no=decodeURI(this.$route.query.com_name)+'商品暂不能开具发票'
            }
            if(this.$route.query.id){
                utils.ajax({
                    url: basepath + "/mall/invoice/get",
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({'id':this.$route.query.id}),
                    success: function(data){
                        if(data.success){
                            that.res.inv_type=data.obj.invoice_type_name;
                            if(data.obj.invoice_type=='NORMAL'){
                                utils.ajax({
                                    url: basepath + "/mall/invoice_category/list",
                                    dataType: 'json',
                                    type: 'POST',
                                    success: function(data){
                                        if(data.success){
                                            that.res.content=data.obj.list;
                                            invoice_set();
                                        }else{
                                            that.res.$vux.alert.show(data.msg);
                                        }
                                    }
                                });
                                function invoice_set(){
                                    that.res.inv_default_cen=data.obj.invoice_category_id;
                                    that.res.formData.category_id=data.obj.invoice_category_id;
                                    that.res.formData.category_name=data.obj.invoice_category_name;
                                    that.res.adsStatus=false;
                                    that.res.inv_ordinary=true;
                                    that.res.formData.inv_rise=data.obj.invoice_title;
                                    if(data.obj.invoice_title=='单位'){
                                        that.res.inv_default_rise=2;
                                        that.res.formData.company_name=data.obj.invoice_title_name;
                                        that.res.company_status=true;
                                        that.res.risename_status=true;
                                    }
                                }
                            }else if(data.obj.invoice_type=='电子发票'){
                                that.res.adsStatus=false;
                                that.res.inv_ordinary=false;
                            }else if(data.obj.invoice_type=='VAT'){
                                that.res.formData.comp_name=data.obj.company_name;
                                that.res.formData.identifica=data.obj.identification_code;
                                that.res.formData.regist_addr=data.obj.regist_address;
                                that.res.formData.regist_phone=data.obj.regist_telephone;
                                that.res.formData.open_bank=data.obj.bank_name;
                                that.res.formData.bank_code=data.obj.bank_account;
                                that.res.adsStatus=false;
                                that.res.increment=true;
                                var add_r=JSON.parse(sessionStorage.getItem('add_'));
                                that.res.mail_name=add_r.name;
                                that.res.mail_phone=add_r.phone;
                                that.res.mail_addr=add_r.address;
                                that.res.mail_addr_status=true;
                            }
                        }else{
                            that.res.$vux.alert.show(data.msg);
                        }
                    }
                });
            }
            if(sessionStorage.getItem('data_')){
                this.res=JSON.parse(sessionStorage.getItem('data_'));
                sessionStorage.removeItem('data_');
                if(this.res.mail_addr_status){
                    //需要重新渲染地址列表
                    this.addr_mail();
                }else{
                    var add_r=JSON.parse(sessionStorage.getItem('add_'));
                    this.res.mail_name=add_r.name;
                    this.res.mail_phone=add_r.phone;
                    this.res.mail_addr=add_r.province_name+add_r.city_name+add_r.county_name+add_r.address;
                    this.res.mail_addr_status=true;
                    this.res.formData.addr_id=this.$route.query.add;
                    if(this.res.formData.comp_name_status && this.res.formData.identifica_status && this.res.formData.regist_addr_status && this.res.formData.regist_phone_status && this.res.formData.open_bank_status && this.res.formData.bank_code_status && this.res.mail_addr_status){
                        this.res.color='org';
                        this.res.disabled=false;
                    }else{
                        this.res.color='default';
                        this.res.disabled=true;
                    }

                }
            }
        },
        methods: { 
            Choice(){
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/invoice_type/list",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        if(data.success){
                            that.res.invoice=data.obj;
                            let buy_info = JSON.parse(utils.getSession("buy_info"));
                            if(buy_info.invoiceTypes.indexOf('NORMAL')==-1){
                                for(var k=0;k<that.res.invoice.length;k++){
                                    if(that.res.invoice[k].id=='NORMAL'){
                                        that.res.invoice.splice(k,1);
                                    }
                                }
                            }
                            if(buy_info.invoiceTypes.indexOf('VAT')==-1){
                                for(var k=0;k<that.res.invoice.length;k++){
                                    if(that.res.invoice[k].id=='VAT'){
                                        that.res.invoice.splice(k,1);
                                    }
                                }
                            }
                            for(var i=0;i<buy_info.products.length;i++){
                                if(buy_info.products[i].type=='VIRTUAL'){
                                    for(var k=0;k<that.res.invoice.length;k++){
                                        if(that.res.invoice[k].id=='NORMAL'){
                                            that.res.invoice.splice(k,1);
                                        }
                                    }
                                }
                            }
                            
                            that.res.adsStatus=true;
                        }else{
                            that.res.$vux.alert.show(data.msg);
                        }
                    }
                });
                
            },
            cancel(){
                this.res.adsStatus=false;
            },
            getInvId(idd){
                var that=this;
                $.each(this.res.invoice,function(i,v){
                    if(v.id==idd){
                        that.res.inv_type=v.trueName;
                        that.res.color='org';
                        that.res.disabled=false;
                        that.res.inv_ordinary=false;
                        that.res.increment=false;
                        that.res.adsStatus=false;
                        if(that.res.inv_type=='普通发票'){
                            that.res.inv_ordinary=true;
                            utils.ajax({
                                url: basepath + "/mall/invoice_category/list",
                                dataType: 'json',
                                type: 'POST',
                                async:false,
                                success: function(data){
                                    if(data.success){
                                        that.res.content=data.obj.list;
                                        that.res.formData.category_id=that.res.content[0].id;
                                        that.res.formData.category_name=that.res.content[0].name;
                                    }
                                }
                            });
                            that.res.inv_default_cen=that.res.content[0].id;
                        }else if(that.res.inv_type=='增值税发票'){
                            that.res.increment=true;
                            that.res.color='default';
                            that.res.disabled=true;
                            that.addr_default();
                            if(that.res.formData.comp_name_status && that.res.formData.identifica_status && that.res.formData.regist_addr_status && that.res.formData.regist_phone_status && that.res.formData.open_bank_status && that.res.formData.bank_code_status && that.res.mail_addr_status){
                                that.res.color='org';
                                that.res.disabled=false;
                            }
                        }
                        
                    }
                })
            },
            rise_change(obj){
                this.res.formData.inv_rise=obj.name;
                if(obj.name=='单位'){
                    this.res.company_status=true;
                    if(!this.res.risename_status){
                        this.res.color='default';
                        this.res.disabled=true;
                    } 
                }else{
                    this.res.company_status=false;
                    this.res.color='org';
                    this.res.disabled=false;
                }
            },
            cen_change(obj){
                this.res.formData.category_name=obj.name;
                this.res.formData.category_id=obj.id;
                console.log(this.res.formData.category_id);
            },
            validate(obj){
                if(obj.name=='comp_name'){
                    this.res.formData.comp_name_status=obj.status;
                }
                if(obj.name=='identifica'){
                    this.res.formData.identifica_status=obj.status;
                }
                if(obj.name=='regist_addr'){
                    this.res.formData.regist_addr_status=obj.status;
                }
                if(obj.name=='regist_phone'){
                    this.res.formData.regist_phone_status=obj.status;
                }
                if(obj.name=='open_bank'){
                    this.res.formData.open_bank_status=obj.status;
                }
                if(obj.name=='bank_code'){
                    this.res.formData.bank_code_status=obj.status;
                }
                if(obj.name=='risename'){
                    if(obj.status){
                        this.res.risename_status=true;
                        this.res.color='org';
                        this.res.disabled=false;
                    }else{
                        this.res.risename_status=false;
                        this.res.color='default';
                        this.res.disabled=true;
                    }
                }else{
                    if(this.res.formData.comp_name_status && this.res.formData.identifica_status && this.res.formData.regist_addr_status && this.res.formData.regist_phone_status && this.res.formData.open_bank_status && this.res.formData.bank_code_status && this.res.mail_addr_status){
                        this.res.color='org';
                        this.res.disabled=false;
                    }else{
                        this.res.color='default';
                        this.res.disabled=true;
                    }
                }
            },
            hold(){
                if(!this.res.disabled){
                    if(this.res.inv_type!='无需发票'){
                        var parem={
                                 "type": "",
                                 "inv_rise": "",
                                 "category_name": "",
                                 "company_name": "",
                                 "category_id": "",
                                 "comp_name": "",
                                 "identifica": "",
                                 "regist_phone": "",
                                 "open_bank": "",
                                 "bank_code": "",
                                 "regist_address":""
                            };
                        if(this.res.inv_type=='普通发票'){
                           parem.type='NORMAL';
                           parem.inv_rise=this.res.formData.inv_rise;
                           parem.category_name=this.res.formData.category_name;
                           parem.category_id=this.res.formData.category_id;
                           if(this.res.company_status){
                              parem.company_name=this.res.formData.company_name;
                           }
                        }else if(this.res.inv_type=='增值税发票'){
                            parem.type='VAT';
                            parem.comp_name=this.res.formData.comp_name;
                            parem.identifica=this.res.formData.identifica;
                            parem.regist_address=this.res.formData.regist_addr;
                            parem.regist_phone=this.res.formData.regist_phone;
                            parem.open_bank=this.res.formData.open_bank;
                            parem.bank_code=this.res.formData.bank_code;
                        }
                        let that = this;
                        utils.ajax({
                            url: basepath + "/mall/invoice/add",
                            dataType: 'json',
                            type: 'POST',
                            data:JSON.stringify(parem),
                            success: function(data){
                                if(data.success){
                                    if(that.res.inv_type=='增值税发票'){
                                        var add_={
                                            name:that.res.mail_name,                                //邮寄地址名称
                                            phone:that.res.mail_phone,                            //邮寄地址手机号
                                            address:that.res.mail_addr,
                                        }
                                        sessionStorage.setItem('add_',JSON.stringify(add_));
                                        utils.go({name:'odadd',query:{id:data.obj.id,name:encodeURI(that.res.inv_type),add:that.res.formData.addr_id}},that.$router,true);
                                    }else{
                                        utils.go({name:'odadd',query:{id:data.obj.id,name:encodeURI(that.res.inv_type)}},that.$router,true);
                                    }

                                }else{
                                    that.res.$vux.alert.show(data.msg);
                                }
                            }
                        });
                    }else{
                        utils.go({name:'odadd'},this.$router,true);
                    }
                }
                
            },
            addrmod(url){
                sessionStorage.setItem('data_',JSON.stringify(this.res));
                utils.go(url,this.$router);
            },
            addr_mail(){
                this.res.mailStatus=true;
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/user_address/list",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        that.res.mailaddr=data.obj;
                        if(data.success){
                            for(var i=0;i<that.res.mailaddr.length;i++){
                                that.res.mailaddr[i].url={name:"addrmod",params:{id:that.res.mailaddr[i].id},callback:true}
                            }
                        }else{
                            that.res.mailStatus=false;
                            that.res.mail_addr_status=false;
                        }
                    }
                });
            },
            getmailId(idd){
                this.res.mailStatus=false;
                var that=this;
                $.each(this.res.mailaddr,function(i,v){
                    if(v.id==idd){
                        that.res.mail_name=v.trueName;
                        that.res.mail_phone=v.userName;
                        that.res.mail_addr=v.time;
                        that.res.formData.addr_id=v.id;
                    }
                })
                if(this.res.formData.comp_name_status && this.res.formData.identifica_status && this.res.formData.regist_addr_status && this.res.formData.regist_phone_status && this.res.formData.open_bank_status && this.res.formData.bank_code_status && this.res.mail_addr_status){
                    this.res.color='org';
                    this.res.disabled=false;
                }
            },
            addmail(){
                sessionStorage.setItem('data_',JSON.stringify(this.res));
                utils.go({name:'addradd',query:{id:'1'}},this.$router);
            },
            addr_default(){
                let that = this;
                utils.ajax({
                    url: basepath + "/mall/invoice_address/get",
                    dataType: 'json',
                    type: 'POST',
                    success: function(data){
                        if(data.success){
                            that.res.mail_name=data.obj.name;
                            that.res.mail_phone=data.obj.phone;
                            that.res.mail_addr=data.obj.addr_name[0]+data.obj.addr_name[1]+data.obj.addr_name[2]+data.obj.address;
                            that.res.mail_addr_status=true;
                            that.res.formData.addr_id=data.obj.id;
                        }
                    }
                });
            }
        },

        components: {
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue'),
            "cInput":require('../../components/x-input.vue'),
            "cCellWrap": require('../../components/cell-wrap.vue'),
            "cCell": require('../../components/cell.vue'),
            "cButton": require('../../components/button.vue'),
            "cAddr": require('../../components/address.vue'),
            "cAddressInfo": require('../../components/address-info.vue'),
            "cRadio": require('../../components/radio.vue')

        }
    }
</script>

