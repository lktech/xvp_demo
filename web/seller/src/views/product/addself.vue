<template>
  <div class="tabbar-wrap2">
      <c-top-back></c-top-back>
      <div>
          <c-cell-wrap>
              <c-input title="商品名称" @on-change="validate" placeholder="请输入商品名称" required name="name" :max="30" v-model="formData.name" :is-type="kong"></c-input>
              <c-uploadmul title='添加商品封面图' :list="img_list1" @upload="upload1" name="upload1" :max="1">
              </c-uploadmul>
          </c-cell-wrap>
          <c-cell-wrap>
              <c-input title="商品描述" @on-change="validate" placeholder="请输入商品描述" name="describe" :max="500" v-model="formData.describe" :is-type="kong"></c-input>
              <c-uploadmul title='添加商品详情图' :list="img_list2" @upload="upload2" name="upload2" :max="9">
              </c-uploadmul>
          </c-cell-wrap>
          <c-cell-wrap>
              <c-cell title="付款方式" value='微信支付'></c-cell>
              <c-input title="运费" @on-change="validate" placeholder="¥0.00" name="freight" v-model="formData.freight" :max="13" :is-type="money1"></c-input>
          </c-cell-wrap>

          <c-cell-wrap title="无规格" v-if="!status.specifications">
                <c-input title="价格" @on-change="validate" placeholder="请输入价格" required name="price" v-model="formData.price" :max="13" :is-type="money"></c-input>
                <c-input title="库存" @on-change="validate" placeholder="请输入库存" required name="stock" v-model="formData.stock" :max="13" :is-type="number"  ></c-input>
          </c-cell-wrap>
          <div v-else>
            <c-cell-wrap title="规格设置" desc="删除" desc-color='red' v-for="(item, index) in formData.specifica_list"  @desc="descClick(index)" >
                  <c-input title="价格" @on-change="validate" placeholder="请输入价格" required :name="order('Yprice',index)" v-model="item.price" :max="13" :is-type="money"></c-input>
                  <c-input title="库存" @on-change="validate" placeholder="请输入库存" required :name="order('Ystock',index)" v-model="item.stock" :max="13" :is-type="number"  ></c-input>
                  <c-input title="规格" @on-change="validate" placeholder="请输入商品规格，如颜色，尺寸" required :name="order('Yvalue',index)"  :max="20" v-model="item.sku_str" ></c-input>
            </c-cell-wrap>
          </div>
          <div class="wrap-pd" style='margin-top:10px;'>
            <c-button type="primary" text="添加商品型号" @click.native="add_specifica" size="block"></c-button>
          </div>
          <div class="wrap-pd" style='margin-top:10px;'>
            <c-button :type="color" text="确定" @click.native="hold" :disabled="disabled" size="block"></c-button>
          </div>
      </div>
      <c-confirm v-model="show"
               @on-cancel="onCancel"
               @on-confirm="onConfirm"
               cancelText="返回"
               confirmText="继续添加"
               title='保存成功'>
      </c-confirm>
      <p class="xv_copyright">版权所有@2016-2017 小V铺</p>
  </div>

</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
            formData:{
              name:'',
              describe:'',
              invoice:false,
              stock:'',
              price:'',
              specifica_list:[{stock:'',price:'',sku_str:''}],
              rule_id:'',
              freight:''
            },
            status:{
              name_status:false,
              stock_status:false,
              price_status:false,
              lim_num_status:false,
              specifications:false,
              specifica_list_status:[{stock:false,price:false,value:false}],
              specifica_status:false,
              freight_status:true
            },
            color:'default',
            disabled:true,
            img_list1:[],
            img_list2:[],
            show:false,
            money: function (value) {
              return {
                valid: value.search(/^(([1-9]\d{0,9})|(0\.[1-9])|(0\.\d[1-9]))(\.\d{1,2})?$/) > -1
              }
            },
            money1: function (value) {
              return {
                valid: value.search(/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/) > -1
              }
            },
            number: function (value) {
              return {
                valid: value.search(/^[0-9]{0,}$/) > -1
              }
            },
            kong: function (value) {
              return {
                valid: value.search(/.*[^ ].*/) > -1
              }
            },
        }
      },
      methods: {
          upload1(src){
            $('input').blur();
            this.img_list1=src;
            this.judge();
          },
          upload2(src){
            $('input').blur();
            this.img_list2=src;
            this.judge();
          },
          validate(obj){
            if(obj.name=='name'){
              this.status.name_status=obj.valid;
            }
            if(obj.name=='stock'){
              this.status.stock_status=obj.valid;
            }
            if(obj.name=='price'){
              this.status.price_status=obj.valid;
            }
            if(obj.name=='freight'){
              this.status.freight_status=obj.valid;
            }
            if(obj.name.indexOf('Yprice')!=-1){
              var i=obj.name.split('_')[1];
              this.status.specifica_list_status[i].price=obj.valid;
              console.log(this.status.specifica_list_status)
            }
            if(obj.name.indexOf('Ystock')!=-1){
              var i=obj.name.split('_')[1];
              this.status.specifica_list_status[i].stock=obj.valid;
            }
            if(obj.name.indexOf('Yvalue')!=-1){
              var i=obj.name.split('_')[1];
              console.log(obj.valid)
              this.status.specifica_list_status[i].value=obj.valid;
            }
            this.judge();
            
          },
          limit(){
            this.status.limit_status=true;
          },
          limcancel(){
            this.status.limit_ch_status=false;
          },
          descClick(i){
            this.formData.specifica_list.splice(i,1);
            this.status.specifica_list_status.splice(i,1);
            if(!this.formData.specifica_list.length){
              this.status.specifications=false;
              this.judge();
            }
            var num=0;
            for(var i=0;i<this.status.specifica_list_status.length;i++){
              if(this.status.specifica_list_status[i].stock && this.status.specifica_list_status[i].price && this.status.specifica_list_status[i].value){
                num++;
              }
            }
            if(this.status.name_status  && this.status.specifica_list_status.length==num && this.img_list1.length && this.status.freight_status){
              this.disabled=false;
              this.color='primary';
            }else{
              this.disabled=true;
              this.color='default';
            }
          },
          add_specifica(){
            this.disabled=true;
            this.color='default';
            this.status.specifications=true;
            if(this.formData.specifica_list.length){
              this.formData.specifica_list.push({stock:'',price:'',sku_str:''});
              this.status.specifica_list_status.push({stock:false,price:false,value:false});
            }else{
              this.formData.specifica_list.push({stock:'',price:'',sku_str:''},{stock:'',price:'',sku_str:''});
              this.status.specifica_list_status.push({stock:false,price:false,value:false},{stock:false,price:false,value:false});
            }
          },
          hold(){
            if(!this.disabled){
              let hold_sku_obj=[];
              let stock=0;
              if(this.status.specifications){
                hold_sku_obj=JSON.stringify(this.formData.specifica_list);
                hold_sku_obj=JSON.parse(hold_sku_obj);
                for(var i=0;i<hold_sku_obj.length;i++){
                  hold_sku_obj[i].price=hold_sku_obj[i].price*100;
                  stock+=hold_sku_obj[i].stock*1;
                }
              }else{
                hold_sku_obj=[{
                  price:this.formData.price?this.formData.price*100:0,
                  stock:this.formData.stock?this.formData.stock*1:stock*1,
                  sku_str:'无规格'
                }];
              }
              
              var hold_obj={
                "name": this.formData.name,
                //商品名称 
                "product_detail": this.formData.describe,
                //商品详情  
                "pay_type":0,

                'logistics_fee':this.formData.freight?this.formData.freight*100:0,
                //付款方式

                "sku": hold_sku_obj,

                "pics":this.img_list1[0],

                "product_desc":JSON.stringify(this.img_list2)
              };
              // let obj_pics=[];
              // for(var i=0;i<this.img_list.length;i++){
              //   obj_pics.push({'url':this.img_list[i]});
              // }
              // let obj_pics_detail=[];
              // for(var i=0;i<this.img_list1.length;i++){
              //   obj_pics_detail.push({'url':this.img_list1[i]});
              // }
              //hold_obj.pics=JSON.stringify(obj_pics);
              //hold_obj.product_desc=JSON.stringify(obj_pics_detail);
              
              let that=this;
              utils.ajax({
                  url: basepath + "/seller/product/add",
                  dataType: 'json',
                  type: 'POST',
                  data:hold_obj,
                  success: function(data){
                      if(data.code=="SUCCESS"){
                          that.show=true;
                      }else if(data.code=='auth_seller_error'){
                                utils.wang(that,utils,data.message);

                      }else{
                          that.$vux.alert.show({content:data.message,onHide :function(){
                                                            return false
                                                          }});
                      }
                  },
                  message:'拼命保存中'
              });
            }
          },
          order(n,v){
            return n+'_'+v
          },
          judge(){
            if(this.status.specifications){
              var num=0;
              for(var i=0;i<this.status.specifica_list_status.length;i++){
                if(this.status.specifica_list_status[i].stock && this.status.specifica_list_status[i].price && this.status.specifica_list_status[i].value){
                  num++;
                }
              }
              if(this.status.name_status  && this.status.specifica_list_status.length==num && this.img_list1.length && this.status.freight_status){
                this.disabled=false;
                this.color='primary';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }else{
              if(this.status.name_status  && this.status.price_status && this.status.stock_status && this.img_list1.length && this.status.freight_status){
                this.disabled=false;
                this.color='primary';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }
          },
          onCancel(){
              this.show=false;
              utils.go({path:'/store/store'},this.$router,true);
          },
          onConfirm(){
            location.reload();
                // this.formData={
                //       name:'',
                //       describe:'',
                //       invoice:false,
                //       stock:'',
                //       price:'',
                //       specifica_list:[{stock:'',price:'',sku_str:''}],
                //       rule_id:'',
                //       freight:''
                //     }
                // this.status={
                //       name_status:false,
                //       stock_status:false,
                //       price_status:false,
                //       lim_num_status:false,
                //       specifications:false,
                //       specifica_list_status:[{stock:false,price:false,value:false}],
                //       specifica_status:false,
                //     }
                // this.color='default';
                // this.disabled=true;
                // this.img_list1=[];
                // this.img_list2=[];
                // this.show=false;

          },
          watch:{
            img_list1(newImgList1){
              this.judge();
            }
          }
      },
      components: {
        "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
        "cCell": require('../../components/cell/cell.vue'),
        "cCellWrap": require('../../components/cell/cell-wrap.vue'),
        "cInput":require('../../components/input/input.vue'),
        "cSwitch": require('../../components/switch/switch.vue'),
        "cUploadmul": require('../../components/x-upload-img/x-upload-img-Slice.vue'),
        //"cChoiceTag": require('../../components/choice-tag.vue'),
        "cButton": require('../../components/button/button.vue'),
        "cNumber": require('../../components/number/number.vue'),
        "cConfirm": require('../../components/confirm/confirm.vue'),
      },
      mounted: function () {
          this.$nextTick(function () {
              this.$vux.alert.hide();
              utils.MenuShare();
         })
      },
  }
</script>
<style>
  .tabbar-wrap2 .weui_cell{
    padding:10px !important;
  }
  .tabbar-wrap2 .weui_uploader_hd.weui_cell{
    padding: 0 !important; padding-bottom: 10px !important;
  }
</style>
