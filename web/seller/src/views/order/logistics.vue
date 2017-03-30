<template>
    <div>
        <c-top-back></c-top-back>
        <c-cell-wrap title="选择物流">
            <c-cell title="物流" is-link :value='logistics' @click.native="logistics_cli"></c-cell>
        </c-cell-wrap>
        
        <div v-if="logistics_Status==1">
            <c-group>
                <c-input title="快递单号" placeholder="请输入快递单号" @on-change="validate" v-model="num" name="num" required></c-input>
            </c-group>   
            <c-cell-wrap>
                <c-cell title="选择快递公司" is-link :value='company' @address-add="cancel" @click.native="company_cli"></c-cell>
            </c-cell-wrap>
        </div>
        <div class="wrap-pd" style="margin-top:10px;">
            <c-button :disabled="disabled" :type="color" text="保存" @click.native="next" size="block"></c-button>
        </div>
        <c-addr title="选择物流" @get-addressid="logisticsId"  @address-add="cancel" v-model="logisticsStatus" :address="logisticsData" type="default" size='block' btn-txt="取消"></c-addr>
        <c-addr title="选择快递公司" @get-addressid="companyId"  @address-add="cancel" v-model="companyStatus" :address="companyData" type="default" size='block' btn-txt="取消"></c-addr>
        <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
   </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    import constants from '../../libs/constants.js';
    export default {
        data (){
            return {
                logistics:'请选择',
                logisticsStatus:false,
                format:{},
                logisticsData:[],
                logistics_Status:'',
                company:'',
                companyStatus:false,
                companyData:[],
                num:'',
                color:'default',
                disabled:true,
            }
        },
        computed: {},
        mounted: function () {
            this.$nextTick(function () {
                utils.MenuShare();
                this.logisticsData=[{'id':'1','trueName':'有物流'},{'id':'0','trueName':'无物流'}];
                this.companyData=[{'id':'0','trueName':'申通快递'},{'id':'1','trueName':'圆通快递'},{'id':'2','trueName':'顺丰快递'},{'id':'3','trueName':'飞鸟快递'},{'id':'4','trueName':'EMS'},{'id':'5','trueName':'中通快递'},{'id':'6','trueName':'其他'}];
            })
        },
        methods: {
            logistics_cli:function(){
              this.logisticsStatus=true;
            },
            company_cli:function(){
              this.companyStatus=true;
            },
            logisticsId:function(id,obj){
                this.logistics=obj.trueName;
                this.logisticsStatus=false;
                this.logistics_Status=id;
                if(id==1){
                    this.color='default';
                    this.disabled=true;
                }else{
                    this.color='primary';
                    this.disabled=false;
                }
            },
            companyId:function(id,obj){
                this.company=obj.trueName;
                this.companyStatus=false;
                this.judge();
            },
            cancel:function(){
                this.logisticsStatus=false;
                this.companyStatus=false;
            },
            next:function(){
                if(!this.disabled){
                    let that = this;
                    utils.ajax({
                        url: basepath + "/seller/order/delivery",
                        dataType: 'json',
                        type: 'POST',
                        data: {
                            order_id: that.$route.query.id,
                            logistics_company_name:that.company,
                            logistics_num:that.num,
                            need_logistics_flg:that.logistics_Status,
                            order_item_id_list:utils.getSession("order_item_id_list")?JSON.parse(utils.getSession("order_item_id_list")):[]
                        },
                        success: function (data) {
                            if (data.code=="SUCESS") {
                                that.$vux.alert.show({content:'发货成功',onHide :function(){
                                  utils.go({path:'list'},that.$router,true);
                                }});
                            }else{
                                that.$vux.alert.show('发货失败');
                            }
                        }
                    });
                }
            },
            validate:function(obj){
                this.num_status=obj.valid;
                this.judge();
            },
            judge:function(){
                if(this.company && this.num_status){
                    this.color='primary';
                    this.disabled=false;
                }else{
                    this.color='default';
                    this.disabled=true;
                }
            }
        },
        components: {
            "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
            "cCellWrap": require('../../components/cell/cell-wrap.vue'),
            "cCell": require('../../components/cell/cell.vue'),
            "cInput":require('../../components/input/input.vue'),
            "cGroup":require('../../components/group/group.vue'),
            "cAddr": require('../../components/x-address-checked/x-address-checked.vue'),
            "cButton":require('../../components/button/button.vue'),

        }
    }
</script>

