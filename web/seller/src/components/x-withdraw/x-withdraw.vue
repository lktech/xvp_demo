<template>
  <div class="input-num-wrap">
    <div class="vux-1px-tb infor">
      <div class="title">{{title?title:'提现金额'}}</div>
      <div class="num">
        <div class="num-mark">￥</div>
        <div class="of"><input @keyup="verification" :placeholder="placeholder" data_id="inputNum"></div>
      </div>
    </div>
    <div class="vux-1px-b tips">{{tips}}</div>
  </div>
</template>

<script>
  export default {
  	props: {
	    title: String,
	    maxvalue: [String,Number],
	    cancelText: String,
	    minvalue: [String,Number],
	    digit: Number,
	    tip:String,
      val:[String,Number],
      placeholder:String,
      words:Object
	},
    mounted() {
      this.$nextTick(() => {
        $('[data_id=inputNum]').val(this.val);
      })
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
      val(newValue){
        $('[data_id=inputNum]').val(newValue);
        this.verification();
      }
    },
    methods: {
      verification() {
        var value = $.trim($('[data_id=inputNum]').val());
        var status = '';
        if(value) {
          if(this.digit) {
            var reg = new RegExp("^[0-9]+(\.\\d{1," + this.digit + "})?$");
          } else {
            var reg = new RegExp("^[0-9]+(\.\\d+)?$");
          };
          if(!reg.test(value)) {
            this.tips = this.words.error || '请输入正确值';
            status = 'fail';
            this.$emit('on-input', value, status);
            return false;
          } else if(this.maxvalue){
          	console.log(111)
          	if(value - 0 < this.minvalue) {
	            this.tips = this.words.min || '输入值不能低于最小值';
	            status = 'fail';
	            this.$emit('on-input', value, status);
	            return false;
	          } else if(value - 0 > this.maxvalue) {
	            this.tips = this.words.max || '输入值不能超出最大值';
	            status = 'fail';
	            this.$emit('on-input', value, status);
	            return false;
	          } else {
	            this.tips = '';
	            status = 'success';
	            this.$emit('on-input', value, status);
	          }
          }else{
          	this.tips = '无可提现的余额';
          }
        } else {
          this.tips = this.words.empty || '输入值不能为空';
          status = 'fail';
          this.$emit('on-input', value, status);
          return false;
        };
      }
    },
    components: {

    }
  }
</script>

<style lang="less">
  @import '../../assets/styles/components/rui/withdraws.less';

</style>