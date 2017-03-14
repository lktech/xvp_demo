<template>
  <div class="tabbar-wrap2">
      <c-top-back></c-top-back>
      <div v-if="!status.limit_status">
          <div v-if='category.status'>
              <c-choice-tag @on-submit="submitLabel" type="org" keyword="品类" all_url="/mall/dictionary/get" :all-list.sync="category.allList"></c-choice-tag>
          </div>
          <div v-else>
              <c-cell-wrap>
                  <c-cell title="品类" is-link :value='category.name' @click="Choice"></c-cell>
              </c-cell-wrap>
              <c-cell-wrap>
                  <c-input title="商品名称" @validate="validate" placeholder="请输入商品名称" name="name" :max="50" :value.sync="formData.name" is-type="name" ></c-input>
                  <c-uploadmul title='添加图片' :list.sync="img_list" @upload="upload" :max="1">
                    <span slot="after-title" class="placeholder">请添加商品封面图</span> 
                  </c-uploadmul>
              </c-cell-wrap>
              <c-cell-wrap>
                  <c-input title="商品描述" @validate="validate" placeholder="请输入商品描述" name="describe" :max="500" :value.sync="formData.describe" is-type="notnull" ></c-input>
                  <c-uploadmul title='添加图片' :list.sync="img_list1" @upload="upload1" :max="9">
                    <span slot="after-title" class="placeholder">请添加商品详情图</span> 
                  </c-uploadmul>
              </c-cell-wrap>
              <c-cell-wrap>
                  <c-cell title="付款方式" is-link :value='formData.payment' @click="payment"></c-cell>
                  <c-cell title="限购" is-link :value='formData.limit' @click="limit"></c-cell>
                  <c-switch v-if="status.invoice_flag" title="可开发票" :value.sync="formData.invoice"></c-switch>
              </c-cell-wrap>
              <c-cell-wrap title="无规格" v-if="!status.specifications">
                    <c-input title="价格" @validate="validate" placeholder="请输入价格" name="price" :value.sync="formData.price" is-type="money"></c-input>
                    <c-input title="库存" @validate="validate" placeholder="请输入库存" name="stock" :value.sync="formData.stock" is-type="number"  ></c-input>
              </c-cell-wrap>
              <div v-else>
                <c-cell-wrap title="规格设置" desc="删除" desc-color='red' v-for="item in formData.specifica_list"  @desc="descClick($index)" >
                      <c-input title="价格" @validate="validate" placeholder="请输入价格" :name="order('Yprice',$index)" :value.sync="item.price" is-type="money"></c-input>
                      <c-input title="库存" @validate="validate" placeholder="请输入库存" :name="order('Ystock',$index)" :value.sync="item.stock" is-type="number"  ></c-input>
                      <c-input title="规格" @validate="validate" placeholder="请输入商品规格，如颜色，尺寸" :name="order('Yvalue',$index)" :value.sync="item.value" ></c-input>
                </c-cell-wrap>
              </div>
              <div class="wrap-pd">
                <c-button type="org" text="添加商品型号" @click="add_specifica" size="block"></c-button>
              </div>
              <div class="wrap-pd" style='margin-top:10px;'>
                <c-button :type="color" text="确定" @click="hold" :disabled="disabled" size="block"></c-button>
              </div>
          </div>
      </div>
      <div v-else>
          <c-cell-wrap title="限购">
              <c-cell title="限购方式" is-link :value='formData.limit' @click="limit_change"></c-cell>
              <div class="weui_media_box" v-if="status.lim_num_status">
                <span>数量</span>
                <div class="num"><c-number :number.sync='formData.selectNum'></c-number></div>
              </div>
          </c-cell-wrap>
          <div class="wrap-pd">
            <c-button type="org" text="保存" @click="lim_hold" size="block"></c-button>
          </div>
      </div>
      <c-addr @get-addressid="getparId"  @address-add="parcancel" :show.sync="status.payment_status" :address="formData.payment_list" type="default" size='block' :mstyle=true btn-txt="取消"></c-addr>
      <c-addr @get-addressid="getlimId"  @address-add="limcancel" :show.sync="status.limit_ch_status" :address="formData.limit_list" type="default" size='block' :mstyle=true btn-txt="取消"></c-addr>
      
  </div>

</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
            category:{
                allList:[],
                name:'',
                id:'',
                status:true
            },
            formData:{
              name:'',
              describe:'',
              payment:'在线付款',
              payment_type:'ONLINE',
              limit:'不限购',
              invoice:false,
              stock:'',
              price:'',
              specifica_list:[{stock:'',price:'',value:''}],
              selectNum:1,
              rule_id:'',
              payment_list:[{id:1,trueName:"在线付款"}],
              limit_list:[{id:1,trueName:"不限购"},{id:2,trueName:"按人限购"},{id:3,trueName:"按天限购"}]
            },
            status:{
              name_status:false,
              payment_status:false,
              limit_status:false,
              stock_status:false,
              price_status:false,
              limit_ch_status:false,
              lim_num_status:false,
              specifications:false,
              specifica_list_status:[{stock:false,price:false,value:false}],
              specifica_status:false,
              invoice_flag:1,
            },
            color:'default',
            disabled:true,
            img_list:[],
            img_list1:[]
        }
      },
      methods: {
          submitLabel(n,id){
              this.category.name=n;
              this.category.id=id;
              this.category.status=false;
          },
          Choice(){
              this.category.status=true;
          },
          upload(src){
            let that = this;
            utils.ajax({
                url: basepath + "/mall/content/base64_upload",
                dataType: 'json',
                type: 'POST',
                data:JSON.stringify({'base64ImgStr':src}),
                success: function(data){
                    if(data.success){
                        that.img_list.push(data.obj.url);
                    }else{
                        that.$vux.alert.show('上传失败');
                    }
                }
            });
          },
          upload1(src){
            let that = this;
            utils.ajax({
                url: basepath + "/mall/content/base64_upload",
                dataType: 'json',
                type: 'POST',
                data:JSON.stringify({'base64ImgStr':src}),
                success: function(data){
                    if(data.success){
                        that.img_list1.push(data.obj.url);
                    }else{
                        that.$vux.alert.show('上传失败');
                    }
                }
            });
          },
          validate(obj){
            if(obj.name=='name'){
              this.status.name_status=obj.status;
            }
            if(obj.name=='stock'){
              this.status.stock_status=obj.status;
            }
            if(obj.name=='price'){
              this.status.price_status=obj.status;
            }
            if(obj.name.indexOf('Yprice')!=-1){
              var i=obj.name.split('_')[1];
              this.status.specifica_list_status[i].price=obj.status
            }
            if(obj.name.indexOf('Ystock')!=-1){
              var i=obj.name.split('_')[1];
              this.status.specifica_list_status[i].stock=obj.status;
            }
            if(obj.name.indexOf('Yvalue')!=-1){
              var i=obj.name.split('_')[1];
              this.status.specifica_list_status[i].value=obj.status;
            }
            this.judge();
            
          },
          payment(){
            let that=this;
            utils.ajax({
                url: basepath + "/mall/pay_type/list",
                dataType: 'json',
                type: 'POST',
                success: function(data){
                    if(data.success){
                        that.formData.payment_list=data.obj;
                        that.status.payment_status=true;
                    }
                }
            });
          },
          limit(){
            this.status.limit_status=true;
          },
          parcancel(){
            this.status.payment_status=false;
          },
          limcancel(){
            this.status.limit_ch_status=false;
          },
          getparId(id,obj){
            this.formData.payment=obj.trueName;
            this.formData.payment_type=id;
            this.status.payment_status=false;
          },
          limit_change(){
            let that=this;
            utils.ajax({
                url: basepath + "/mall/limit_buy_type/list",
                dataType: 'json',
                type: 'POST',
                success: function(data){
                    if(data.success){
                        that.formData.limit_list=data.obj;
                        that.formData.limit_list.push({id:1,trueName:"不限购"});
                        that.status.limit_ch_status=true;
                    }
                }
            });
          },
          getlimId(id,obj){
            this.formData.limit=obj.trueName;
            this.status.limit_ch_status=false;
            if(obj.trueName!='不限购'){
              this.status.lim_num_status=true;
            }else{
              this.status.lim_num_status=false;
            }
          },
          lim_hold(){
            /*var lim_obj={};
            if(this.formData.limit=='按人限购'){
              lim_obj={'rule_type':'LIMIT_BY_PERSON','entity_num':this.formData.selectNum,'rule_entity_type':'SHELF_PRODUCT'};
            }else if(this.formData.limit=='按天限购'){
              lim_obj={'rule_type':'LIMIT_BY_DAY','entity_num':this.formData.selectNum,'rule_entity_type':'SHELF_PRODUCT'};
            }else{
              this.formData.rule_id='';
              this.status.limit_status=false;
              return false
            }
            let that=this;
            utils.ajax({
                url: basepath + "/mall/store/rule_add",
                dataType: 'json',
                type: 'POST',
                data:JSON.stringify(lim_obj),
                success: function(data){
                    if(data.success){
                        that.formData.rule_id=data.obj.id;
                        that.status.limit_status=false;
                    }else{
                        that.$vux.alert.show(data.msg);
                    }
                }
            });*/
            this.status.limit_status=false;
          },
          descClick(i){
            this.formData.specifica_list.splice(i,1);
            this.status.specifica_list_status.splice(i,1);
            if(!this.formData.specifica_list.length){
              this.status.specifications=false;
            }
            var num=0;
            for(var i=0;i<this.status.specifica_list_status.length;i++){
              if(this.status.specifica_list_status[i].stock && this.status.specifica_list_status[i].price && this.status.specifica_list_status[i].value){
                num++;
              }
            }
            if(this.status.name_status && this.img_list.length && this.status.specifica_list_status.length==num){
              this.disabled=false;
              this.color='org';
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
              this.formData.specifica_list.push({stock:'',price:'',value:''});
              this.status.specifica_list_status.push({stock:false,price:false,value:false});
            }else{
              this.formData.specifica_list.push({stock:'',price:'',value:''},{stock:'',price:'',value:''});
              this.status.specifica_list_status.push({stock:false,price:false,value:false},{stock:false,price:false,value:false});
            }
          },
          hold(){
            if(!this.disabled){
              let hold_sku_obj=this.formData.specifica_list;
              let stock=0;
              if(this.status.specifications){
                for(var i=0;i<hold_sku_obj.length;i++){
                  hold_sku_obj[i].price=hold_sku_obj[i].price*100;
                  stock+=hold_sku_obj[i].stock*1;
                }
              }
              
              var hold_obj={
                "category_id": 1,//this.category.id,
                "category_name": this.category.name,
                "name": this.formData.name,
                //商品封面图  
                "des": this.formData.describe,
                //商品详情图  
                "pay_type": this.formData.payment_type,
                //付款方式 
                "product_type": this.category.name=='虚拟商品'?'VIRTUAL':'ACTUAL',
                //商品类型ACTUAL(实物商品) || VIRTUAL(虚拟商品)
                'limit':null,

                "invoice_flag": this.formData.invoice?1:0,
                //是否可开发票

                "sku_exist_flag": this.status.specifications?1:0,
                //是否有规格

                "no_sku_price": this.formData.price?this.formData.price*100:0,
                //无规格价格

                "no_sku_stock": this.formData.stock?this.formData.stock*1:stock*1,
                //无规格库存

                "sku": this.status.specifications?hold_sku_obj:null

              };
              let obj_pics=[];
              for(var i=0;i<this.img_list.length;i++){
                obj_pics.push({'url':this.img_list[i]});
              }
              let obj_pics_detail=[];
              for(var i=0;i<this.img_list1.length;i++){
                obj_pics_detail.push({'url':this.img_list1[i]});
              }
              hold_obj.pics=JSON.stringify(obj_pics);
              hold_obj.pics_detail=JSON.stringify(obj_pics_detail);
              if(this.formData.limit=='按人限购'){
                hold_obj.limit={'type':'LIMIT_BY_PERSON','num':this.formData.selectNum};
              }else if(this.formData.limit=='按天限购'){
                hold_obj.limit={'type':'LIMIT_BY_DAY','num':this.formData.selectNum};
              }
              
              let that=this;
              utils.ajax({
                  url: basepath + "/mall/product/save",
                  dataType: 'json',
                  type: 'POST',
                  data:JSON.stringify(hold_obj),
                  success: function(data){
                      if(data.success){
                          var link=that.$router._currentTransition.from.name;
                          utils.go({name:link},that.$router,true);
                      }else{
                          that.$vux.alert.show('添加商品失败');
                      }
                  }
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
              if(this.status.name_status && this.img_list.length && this.status.specifica_list_status.length==num){
                this.disabled=false;
                this.color='org';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }else{
              if(this.status.name_status && this.img_list.length && this.status.price_status && this.status.stock_status){
                this.disabled=false;
                this.color='org';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }
          }
      },
      ready(){
        let that=this;
        utils.ajax({
            url: basepath + "/mall/store/get",
            dataType: 'json',
            type: 'POST',
            success: function(data){
                if(data.success){
                    that.status.invoice_flag=data.obj.invoice_flag;
                }
            }
        });
      },
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cCell": require('../../components/cell.vue'),
        "cCellWrap": require('../../components/cell-wrap.vue'),
        "cInput":require('../../components/x-input.vue'),
        "cSwitch": require('../../components/switch.vue'),
        "cUploadmul": require('../../components/uploadmul.vue'),
        "cAddr": require('../../components/address.vue'),
        "cChoiceTag": require('../../components/choice-tag.vue'),
        "cButton": require('../../components/button.vue'),
        "cNumber": require('../../components/number.vue')
      },
      watch:{img_list(){
          this.judge();
        }
      }
  }
</script>
<style lang="less">
  @import '../../assets/styles/weui/widget/weui_media_box/weui_media_box.less';
  .num{float:right;}
</style>
