<template>
  <div>
    <c-msg status="1" msg="我的账户余额" :money="json.balance" :thirdtext='json.thirdtext'>
    </c-msg>
    <c-cell-wrap>
      <!-- 已绑卡 -->
      <c-cell title="提现账号" :value="json.cardNumber" is-link rightcolor='org' v-if='json.validateState==4'></c-cell>
      <c-cell title="提现账号" :value="json.value" is-link v-else></c-cell>
      <c-cell title="提现记录" link="{name:'cashrecord'}"></c-cell>
    </c-cell-wrap>
    <div class="wrap-pdb wrap-pd">
      <c-button slot="btn" type="org" size="block" text="提现" @click='tocashing'></c-button>
    </div>
    <c-cell-wrap title='温馨提醒'>
      <div class="border-t wrap label0">
        <div>1.提现手续费：3元/笔</div>
        <div>2.通道手续费：提现金额的0.6%</div>
        <div>3.提现计算公式：实际提现金额=提现金额-通道手续费-提现手续费</div>
        <div>4.例：申请提现1000元</div>
        <div>5.实际提现金额：1000-1000*0.6%-3=991元</div>
      </div>
    </c-cell-wrap>
  </div>
</template>
<script>
  import utils from '../../libs/utils.js';
  export default {
    data() {
        return {
          json: {
            balance: '', //余额
            thirdtext: '', // 余额下面的文字
            validateState: "", //验证状态
            value: '', // 验证名字
            cardNumber: '',  // 卡号
          }

        }
      },
      ready() {
          utils.MenuShare();
        let that = this;
        utils.ajax({
          url: basepath + "/app/account",
          data: {},
          success: function(data) {
            if(data.success) {
              that.json = data.obj;
              if(that.json.validateState == 1) { //未实名验证
                that.json.thirdtext = '退款后款项会进入我的账户';
                that.json.value = '未实名验证';
                return;
              }
              if(that.json.validateState == 2) { //待审核
                that.json.thirdtext = '提现预计24小时后到账';
                that.json.value = '实名验证待审核';
                return;
              }
              if(that.json.validateState == 3) { //实名验证已通过未绑卡
                that.json.thirdtext = '提现预计24小时后到账';
                that.json.value = '实名验证已通过';
                return;
              }
              if(that.json.validateState == 4) { //审核通过已绑卡
                that.json.value = that.json.cardNumber;
                return;
              }
            } else {
              that.$vux.alert.show(data.msg);
            }
          },
        });
      },
      methods: {
        tocashing() {
          let that = this;
          if(that.json.validateState == 1) { //未实名验证
            that.$vux.confirm.show({
              content: '您还未实名验证,必须实名验证后才能提现，是否继续？',
              onConfirm() {
                utils.go({
                  name: 'attesta',
                }, that.$router);
              },
            });
            return;
          }
          if(that.json.validateState == 2) { //实名验证待审核
            that.$vux.alert.show('您还没有实名验证,待审核');
            return;
          }
          if(that.json.validateState == 3) { //实名验证已通过未绑卡
            utils.go({
              name: 'binding',
            }, that.$router);
            return;
          }
          if(that.json.validateState == 4) { //实名验证已绑卡
            this.$vux.confirm.show({
              content: '确定要提现到' + that.json.cardNumber + '账号',
              onConfirm() {    // 提现
                utils.go({
                  name: 'withdrawals'
                }, that.$router);

              },
              onCancel() {    // 更换账号
                utils.go({
                  name: 'binding',
                }, that.$router);
              }
            });
            return;
          }

        },
      },
      components: {
        "cMsg": require("../../components/msg.vue"),
        "cButton": require('../../components/button.vue'),
        "cCell": require('../../components/cell.vue'),
        "cCellWrap": require('../../components/cell-wrap.vue'),
      }
  }
</script>