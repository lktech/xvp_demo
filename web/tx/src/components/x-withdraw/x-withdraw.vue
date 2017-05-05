<template>
  <div class="input-num-wrap">
    <div class="vux-1px-tb infor">
      <div class="title">{{title?title:'提现金额'}}</div>
      <div class="num">
        <div class="num-mark">￥</div>
        <div class="of">
        	<input @keyup="verification" data_id="inputNum" style='font-size: 18px;'>
        	<span style="color: #F05B0B;font-size: 18px;" class="float-right" @click='toall' v-if='allcash'>全部提现</span>
        </div>
      </div>
    </div>
    <div class="vux-1px-b tips">{{tips}}</div>
  </div>
</template>

<script>
  export default {
  	props: {
	    title: String,
	    maxvalue: Number,
	    cancelText: String,
	    minvalue: Number,
	    digit: Number,
	    tip:String,
	    allcash:Boolean
	},
    mounted() {
      this.$nextTick(() => {})
    },
    created() {},
    data() {
      return {
        tips: this.tip,
        
      }
    },
    computed: {

    },
    watch: {
    	tip(newd){    // 点全部提现，需要把tip置为空
    		this.tips = newd;
    	}
    },
    methods: {
      verification() {
        var value = $.trim($('[data_id=inputNum]').val());
        var status = '';
        if(value) {
        	value = parseInt(value);
          if(this.digit) {
            var reg = new RegExp("^[0-9]+(\.\\d{1," + this.digit + "})?$");
          } else {
            var reg = new RegExp("^[0-9]+(\.\\d+)?$");
          };
          if(!reg.test(value)) {
            this.tips = '请输入正确值';
            status = 'fail';
            this.$emit('on-input', value, status);
            return false;
          } else if(value - 0 < this.minvalue) {
            this.tips = '提现金额需大于100元';
            status = 'fail';
            this.$emit('on-input', value, status);
            return false;
          } else if(value - 0 > this.maxvalue) {
            this.tips = '输入金额超过本次可提现金额';
            status = 'fail';
            this.$emit('on-input', value, status);
            return false;
          } else {
            this.tips = '';
            status = 'success';
            this.$emit('on-input', value, status);
          }
        } else {
          this.tips = '输入值不能为空';
          status = 'fail';
          this.$emit('on-input', value, status);
          return false;
        };
      },
      toall(){
      	this.$emit('toallla');
      }
    },
    components: {

    }
  }
</script>

<style lang="less">
  @import '../../assets/styles/components/rui/withdraws.less';
</style>