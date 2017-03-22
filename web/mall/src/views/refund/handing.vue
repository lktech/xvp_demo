<template>
  <div class="tabbar-wrap">
    <c-top-back></c-top-back>
    <c-panel :title='results' v-if='json.refund_states == sysRefund.handing'>
      <!-- 等待商家退款  -->
      <div class="wrap-pdb" v-if='refund_closed==false'>
        <!-- 等待商家退款 top -->
        <div>如商家同意：申请将达成并退款给您</div>
        <div>如商家拒绝：你需要重新修改退款申请或申请维权</div>
      </div>
      <div class="wrap-pdb" v-else>
        <!-- 退款关闭 top -->
        <div>退款关闭：申买家主动撤销退款，退款关闭</div>
        <div>结束时间：{{json.cancel_time}}</div>
      </div>
      <div class="border-t wrap-pd2 clr-9" v-if='refund_closed==false'>
        <!-- 等待商家退款 bottom -->
        <span class="fl">若商家在</span>
        <c-clocker color="red" :time="json.last_time"></c-clocker>
        <span>内未处理，则申请达成并退款给您</span>
      </div>
      <div class="border-t wrap-pd2 clr-9" v-else>
        <!-- 退款关闭  bottom -->
        <span>您已经主动关闭退款申请，无法再次发起退款，如有疑问请联系商家协商处理。</span>
      </div>
    </c-panel>
    <c-panel :title='results' type='org' v-else>
      <!-- 同意  或  拒绝退款页面 -->
      <div class="wrap-pdb">
        <div v-if='json.refund_states == 2'>同意理由：{{json.agree_reason}}</div>
        <!-- 同意退款top -->
        <div v-if='json.refund_states == 3'>
          <c-two-line-cell left_title='拒绝理由' :right_content='json.agree_reason'></c-two-line-cell>
        </div>
        <!-- 拒绝退款top -->
      </div>
      <div class="border-t wrap-pd2 clr-9">
        <span class="clr-org">{{reply_status}}</span>
        <!-- 同意或拒绝退款bottom -->
      </div>
    </c-panel>
    <c-cell-wrap title='退款记录'>
      <c-cell title='退款编号' :value="json.refund_code"></c-cell>
      <c-cell title='申请时间' :value="json.creat_time"></c-cell>
      <c-cell title='退款原因' :value="json.refund_reason"></c-cell>
      <c-cell title='处理方式' :value="json.hander_type"></c-cell>
      <c-cell title='退款金额' :value="json.refund_sum|formatPrice" rightcolor='org'></c-cell>
    </c-cell-wrap>
    <div class="wrap-pd">
      <c-button type="org" size="block" text='订单详情' :link='{name:"oddetail",params:{id:json.refund_id}}'></c-button>
      <!-- 订单详情 -->
      <c-button type="default" size="block" :text='text' @click='confirm' v-if='json.refund_states!==2&&refund_closed!=true&&json.overtime!=1'></c-button>
      <!-- 同意退款时，不显示 -->
    </div>

  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  import constants from '../../libs/constants.js';
  export default {
    data() {
        return {
          json: { //后台返回的数据
            refund_code: '', //退款编号
            creat_time: '', //申请时间                           
            refund_reason: '', //退款原因
            hander_type: '', //处理方式
            refund_sum: '', //退款金额
            last_time: '', // 倒计时
            refund_id: '', // 退款id
            cancel_time: '', //退款关闭时间
            agree_reason: '', // 同意、拒绝理由
            refund_states: '', //退款状态
          },
          // 静态数据
          sysRefund: constants.refund_state,
          refund_closed: false, // 退款关闭状态控制
          results: '', //处理结果
          reply_status: '', // 退款再次申请状态
          text: '', //撤销退款或者重新申请退款的按钮文字
          colororg: true, // 退款金额文字的颜色
        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        utils.ajax({
          url: basepath + "/app/handing",
          data: {
            id: that.$route.params.id
          },
          success: function(data) {
            if(data.success) {
              that.json = data.obj;
              if(that.json.refund_states == that.sysRefund.handing) { // 等待商家处理
                that.text = '撤销退款申请';
                that.results = '等待商家处理退款申请';
              }
              if(that.json.refund_states == that.sysRefund.agree) { //同意退款
                that.results = '商家同意退款';
                that.reply_status = '商家同意退款，钱款将在24小时内退回我的账户。如果有任何疑问请联系平台：29384847';
              }
              if(that.json.refund_states == that.sysRefund.disagree) { //商家不同意退款
                if(that.json.overtime == 1) {
                  that.reply_status = '您已超时未处理该申请，无法重新申请退款。';
                } else {
                  that.text = '重新申请退款';
                  that.reply_status = '可在4小时内重新发起退款申请，超时后不可再次申请。';
                }
                that.results = '商家不同意退款';
              }
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });
      },
      methods: {
        confirm() {
          let that = this;
          if(that.json.refund_states == 3) { //重新申请退款
            utils.go({
              name: 'refund',
              params: {
                id: that.json.refund_id,
              }
              //                 跳转退款处理页面，传入订单编号
            }, that.$router);
          } else {
            this.$vux.confirm.show({
              content: '如您主动关闭正在处理的退款后，您无法再次发起退款申请，请务必谨慎操作',
              onConfirm() { //  点击撤销退款
                utils.ajax({
                  url: basepath + "/app/closerefund",
                  data: {
                    id: that.json.refund_id
                  },
                  success: function(data) {
                    if(data.success) {
                      that.results = '退款关闭';
                      that.json.cancel_time = data.obj.cancel_time;
                      that.refund_closed = true;
                    } else {
                      that.$vux.alert.show(data.msg);
                    }
                  },
                  message: '撤销退款中'
                });
              },
            });
          }
        },
      },
      components: {
        "cTopBack": require('../../components/top-back.vue'),
        "cCell": require('../../components/cell.vue'),
        "cCellWrap": require('../../components/cell-wrap.vue'),
        "cButton": require('../../components/button.vue'),
        "cPanel": require('../../components/panel.vue'),
        "cClocker": require('../../components/clocker.vue'),
        "cTwoLineCell": require('../../components/two-line-cell.vue'),
      }
  }
</script>