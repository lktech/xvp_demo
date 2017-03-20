<template>
  <div class="tabbar-wrap">
    <c-top-back></c-top-back>
    <c-cell-wrap>
      <c-cell title='商品名称' :value="json.goods_title"></c-cell>
      <c-cell title='商品金额' :value="json.sum|formatPrice" rightcolor='org'></c-cell>
      <c-cell title='订单编号' :value="json.number"></c-cell>
      <c-cell title='交易时间' :value="json.creat_time"></c-cell>
    </c-cell-wrap>
    <c-group>
      <c-cell title='处理方式' is-link @click="openAddress(1)">
        <span slot="after-title" class="placeholder">{{resons2==''?'请选择处理方式':resons2}}</span>
      </c-cell>
      <c-cell title='退款原因' is-link @click="openAddress(2)">
        <span slot="after-title" class="placeholder">{{resons==''?'请选择退款原因':resons}}</span>
      </c-cell>
      <c-input @validate="validate" title="退款金额" placeholder="退款金额不能大于商品金额" name="refund_sum" is-type="number" :value.sync="refund_sum"></c-input>
      <c-input @validate="validate" title="手机号码" placeholder="请输入手机号" name="phone" is-type="mobile" :value.sync="phone" :max="11"></c-input>
      <c-input @validate="validate" title="备注信息" placeholder="最多可填写200个字" name="remarks" is-type="name" :value.sync="json.remarks" :max="200"></c-input>
      <c-uploadmul title="图片举证" :list="uploadList" @upload="upload" :max="5" placeholder="13">
        <span slot="after-title" class="placeholder">请上传举证照片</span>
      </c-uploadmul>
    </c-group>
    <div class="footer-fixed">
      <c-button type="org" @click="submit" size="block" text='提交申请'></c-button>
    </div>
    <!-- 请选择退款原因弹框 -->
    <c-address :show.sync="adsStatus" :address="address" @get-addressid="getAddressId" @address-add="addBtn" :title="title" btn-txt="取消" btn-skin="blue" mstyle=true @callback="getAddressId">
    </c-address>
  </div>

</template>

<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          json: { //填写的数据
            goods_title: '', //商品名称
            sum: '', //商品金额                           
            number: '', //订单编号
            creat_time: '', //交易时间
            refund_id: '', // 退款id
          },
          adsStatus: false, //弹窗
          phone_status: false, //手机号校验
          name_status: false, // 备注
          refundsum_status: false, // 退款金额
          colororg: true, // 金额文字的颜色
          title: '',
          phone: '', //手机号
          resons: [], // 退款原因
          resons2: [], // 退款处理方式
          refund_sum: '', // 退款金额
          remarks: '', //备注
          uploadList: [], //上传图片
          address: [],
          address1: [{ // 退款原因
            id: 1,
            trueName: "多买/买错/不想要",
          }, {
            id: 2,
            trueName: "快递无记录",
          }, {
            id: 3,
            trueName: "少货/空包裹",
          }, {
            id: 4,
            trueName: "未按约定时间发货",
          }, {
            id: 5,
            trueName: "快递一直未送达",
          }, {
            id: 6,
            trueName: "其他",
          }],
          address2: [{ // 退款处理方式
            id: 1,
            trueName: "仅退款",
          }]
        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        utils.ajax({
          url: basepath + "/app/refund",
          success: function(data) {
            if(data.success) {
              that.json.goods_title = data.obj.title;
              that.json.sum = data.obj.sum;
              that.json.number = data.obj.number;
              that.json.creat_time = data.obj.creat_time;
              that.json.refund_id = data.obj.refund_id;
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });
      },
      methods: {
        // 表单验证
        validate(obj) {
        	if(obj.name == 'refund_sum') {
            this.refundsum_status = obj.status;
          }
          if(obj.name == 'phone') {
            this.phone_status = obj.status;
          }
          if(obj.name == 'remarks') {
            this.name_status = obj.status;
          }
        },
        // 图片举证
				upload(url) {
          let that = this;
          utils.ajax({
            url: basepath + "/app/upload",
            dataType: 'json',
            type: 'POST',
            data: {
              'url': url
            },
            success: function(data) {
              if(data.success) {
                that.uploadList.push(url);
              } else {
                that.$vux.alert.show(data.msg);
              }
            }
          });
        },
        // 提交退款申请
        submit() {
          let that = this;
          if(that.resons2 == '') {
            that.$vux.alert.show('请选择处理方式');
            return;
          }
          if(that.resons == '') {
            that.$vux.alert.show('请选择退款原因');
            return;
          }
          if(that.refund_sum == ''||that.refund_sum==0) {
            that.$vux.alert.show('请输入退款金额');
            return;
          }
          if(that.refund_sum > that.json.sum){
          	that.$vux.alert.show('退款金额不能大于商品金额');
          	return;
          }
          if(!that.phone_status) {
            that.$vux.alert.show('请输入正确手机号');
            return;
          }
          utils.ajax({
            url: basepath + "/app/submitrefund",
            dataType: 'json',
            type: 'POST',
            data: {
              'phone': that.phone,
              'resons': that.resons,
              'resons2': that.resons2,
              'remarks': that.remarks,
              'refund_sum': that.refund_sum,
              'uploadList': that.uploadList,
            },
            success: function(data) {
              if(data.success) {
                  utils.go({
                    name: 'handing',
                    params: {
                      "id": that.json.refund_id,
                    }
                    // 跳转退款处理页面，传入订单编号，退款原因
                  }, that.$router);
              } else {
                that.$vux.alert.show(data.msg);
              }
            },
            message: '提交申请中'
          });
        },
        // 处理方式，退款原因弹框
        openAddress: function(i) {
          this.adsStatus = true;
          if(i == 1) {
            this.address = this.address2;
            this.title = '请选择处理方式';
          } else {
            this.address = this.address1;
            this.title = '请选择退款原因';
          }
        },
        // 动态绑定退款原因以及处理方式的文字
        getAddressId: function(id, v) {
          this.adsStatus = false;
          if(v == '仅退款') {
            this.resons2 = v; // 处理方式
          } else {
            this.resons = v; // 退款原因
          }
        },
        // 取消弹框
        addBtn() {
          this.adsStatus = false;
        }
      },
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cCell": require('../../components/cell.vue'),
        "cCellWrap": require('../../components/cell-wrap.vue'),
        "cGroup": require('../../components/group.vue'),
        "cInput": require('../../components/x-input.vue'),
        "cUploadmul": require('../../components/uploadmul.vue'),
        "cButton": require('../../components/button.vue'),
        "cAddress": require('../../components/address.vue'),
      }
  }
</script>