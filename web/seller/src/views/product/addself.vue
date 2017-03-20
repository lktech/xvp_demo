<template>
  <div class="tabbar-wrap2">
      <c-top-back></c-top-back>
      <div>
          <c-cell-wrap>
              <c-input title="商品名称" @on-change="validate" placeholder="请输入商品名称" required name="name" :max="20" v-model="formData.name"></c-input>
              <c-uploadmul title='添加图片' :list.sync="img_list" @upload="upload" :max="1">
                <span slot="after-title" class="placeholder">请添加商品封面图</span> 
              </c-uploadmul>
          </c-cell-wrap>
          <c-cell-wrap>
              <c-input title="商品描述" @on-change="validate" placeholder="请输入商品描述" name="describe" :max="500" v-model="formData.describe" ></c-input>
              <c-uploadmul title='添加图片' :list.sync="img_list1" @upload="upload1" :max="9">
                <span slot="after-title" class="placeholder">请添加商品详情图</span> 
              </c-uploadmul>
          </c-cell-wrap>
          <c-cell-wrap>
              <c-cell title="付款方式" value='微信支付'></c-cell>
              <c-input title="运费" @on-change="validate" placeholder="¥0.00" name="freight" v-model="formData.freight" :max="10" :is-type="money"></c-input>

          </c-cell-wrap>
          <c-cell-wrap title="无规格" v-if="!status.specifications">
                <c-input title="价格" @on-change="validate" placeholder="请输入价格" required name="price" v-model="formData.price" :max="10" :is-type="money"></c-input>
                <c-input title="库存" @on-change="validate" placeholder="请输入库存" required name="stock" v-model="formData.stock" :max="10" :is-type="number"  ></c-input>
          </c-cell-wrap>
          <div v-else>
            <c-cell-wrap title="规格设置" desc="删除" desc-color='red' v-for="(item, index) in formData.specifica_list"  @desc="descClick(index)" >
                  <c-input title="价格" @on-change="validate" placeholder="请输入价格" required :name="order('Yprice',index)" v-model="item.price" :max="10" is-type="money"></c-input>
                  <c-input title="库存" @on-change="validate" placeholder="请输入库存" required :name="order('Ystock',index)" v-model="item.stock" :max="10" is-type="number"  ></c-input>
                  <c-input title="规格" @on-change="validate" placeholder="请输入商品规格，如颜色，尺寸" :name="order('Yvalue',index)"  :max="50" v-model="item.value" ></c-input>
            </c-cell-wrap>
          </div>
          <div class="wrap-pd" style='margin-top:10px;'>
            <c-button type="primary" text="添加商品型号" @click.native="add_specifica" size="block"></c-button>
          </div>
          <div class="wrap-pd" style='margin-top:10px;'>
            <c-button :type="color" text="确定" @click.native="hold" :disabled="disabled" size="block"></c-button>
          </div>
      </div>
      
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
            },
            color:'default',
            disabled:true,
            img_list:[],
            img_list1:[],
            money: function (value) {
              return {
                valid: value.search(/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/) > -1
              }
            },
            number: function (value) {
              return {
                valid: value.search(/^[0-9]{0,}$/) > -1
              }
            },
        }
      },
      methods: {
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
              this.status.name_status=obj.valid;
            }
            if(obj.name=='stock'){
              this.status.stock_status=obj.valid;
            }
            if(obj.name=='price'){
              this.status.price_status=obj.valid;
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
              if(this.status.specifica_list_status[i].stock && this.status.specifica_list_status[i].price){
                num++;
              }
            }
            if(this.status.name_status  && this.status.specifica_list_status.length==num){
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
              let hold_sku_obj=this.formData.specifica_list;
              let stock=0;
              if(this.status.specifications){
                for(var i=0;i<hold_sku_obj.length;i++){
                  hold_sku_obj[i].price=hold_sku_obj[i].price*100;
                  stock+=hold_sku_obj[i].stock*1;
                  hold_sku_obj[i].logistics_fee=this.formData.freight;
                }
              }else{
                hold_sku_obj=[{
                  price:this.formData.price?this.formData.price*100:0,
                  stock:this.formData.stock?this.formData.stock*1:stock*1,
                  logistics_fee:this.formData.freight,
                  sku_str:this.formData.name
                }]
              }
              
              var hold_obj={
                "name": this.formData.name,
                //商品名称 
                "product_detail": this.formData.describe,
                //商品详情  
                "pay_type":'微信支付',
                //付款方式

                "sku": hold_sku_obj

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
              hold_obj.product_desc=JSON.stringify(obj_pics_detail);
              
              let that=this;
              utils.ajax({
                  url: basepath + "/seller/product/add",
                  dataType: 'json',
                  type: 'POST',
                  data:JSON.stringify(hold_obj),
                  success: function(data){
                      if(data.code=="SUCESS"){
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
                if(this.status.specifica_list_status[i].stock && this.status.specifica_list_status[i].price){
                  num++;
                }
              }
              if(this.status.name_status  && this.status.specifica_list_status.length==num){
                this.disabled=false;
                this.color='primary';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }else{
              if(this.status.name_status  && this.status.price_status && this.status.stock_status){
                this.disabled=false;
                this.color='primary';
              }else{
                this.disabled=true;
                this.color='default';
              }
            }
          }
      },
      components: {
        "cTopBack": require('../../components/x-top-back/x-top-back.vue'),
        "cCell": require('../../components/cell/cell.vue'),
        "cCellWrap": require('../../components/cell/cell-wrap.vue'),
        "cInput":require('../../components/input/input.vue'),
        "cSwitch": require('../../components/switch/switch.vue'),
        "cUploadmul": require('../../components/x-upload-img/x-upload-img.vue'),
        //"cChoiceTag": require('../../components/choice-tag.vue'),
        "cButton": require('../../components/button/button.vue'),
        "cNumber": require('../../components/number/number.vue')
      },
      watch:{
        img_list(){
          this.judge();
        }
      }
  }
</script>
<style lang="less">
  .num{float:right;}
</style>
