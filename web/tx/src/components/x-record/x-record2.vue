<template>
  <div class="courseBottom clearfix">
    <div class="courseBox">
      <div class="bottomLeft" :class='noImg?"widthblock":""'>
        <div class="chat-say" :class='{"chat-say_touched":!!state_say&&!boxHide}' v-touch:press='switch_way_press' @touchend='switch_way_end' v-touch:swipeup='switch_way_up'>
          <span class="one">按住 说话</span>
          <span class="two">{{test?"点击":"松开"}} 结束</span>
        </div>
      </div>
      <div class="bottomRight" @click='upload' v-if='!noImg'>
        <div class="RightImg"></div>
        <!--<img src="../../../dist2/images/e34b3824572d4e656f8974204e4a3c55.png" style="height: 37px;"/>-->
      </div>
      <div class="recordingWrap" v-show="state_say&&!boxHide">
        <div class="recording">
          <div class="recording-voice" v-show='!say_cancel&&state_say'>
            <div class="voice-inner">
              <div class="voice-icon"></div>
              <div class="voice-volume">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
              </div>
            </div>
            <p v-show='timeOut'>{{second}}秒</p>
            <p v-show='!timeOut'>手指上划,取消发送</p>
          </div>
          <div class="recording-cancel" v-show='say_cancel&&state_say'>
            <div class="cancel-inner"></div>
            <p>松开手指,取消发送</p>
          </div>
        </div>
      </div>
      <!--<div class="recording" v-show="state_say&&!boxHide">
				<div class="recording-voice" v-show='!say_cancel&&state_say'>
					<div class="voice-inner">
						<div class="voice-icon"></div>
						<div class="voice-volume">
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
							<span></span>
						</div>
					</div>
					<p v-show='timeOut'>{{second}}秒</p>
					<p v-show='!timeOut'>手指上划,取消发送</p>
				</div>
				<div class="recording-cancel" v-show='say_cancel&&state_say'>
					<div class="cancel-inner"></div>
					<p>松开手指,取消发送</p>
				</div>
			</div>-->

    </div>
  </div>
</template>

<script>
  import utils from '../../libs/utils.js';
  export default {
    props: {
      maskhide: {
        type: Boolean,
        default: false
      },
      noImg: {
        type: Boolean,
        default: false
      },
      timeOut: {
        type: Boolean,
        default: false
      },
      boxHide: {
        type: Boolean,
        default: false
      },
      second: Number
    },
    mounted() {
      this.$nextTick(() => {
        var u = navigator.userAgent;
        if(u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) { //安卓手机
          this.test = true;
          //				alert("安卓手机");
          // window.location.href = "mobile/index.html";
        } else if(u.indexOf('iPhone') > -1) { //苹果手机
          // window.location.href = "mobile/index.html";
          //				alert("苹果手机");
        } else if(u.indexOf('Windows Phone') > -1) { //winphone手机
          //				alert("winphone手机");
          // window.location.href = "mobile/index.html";
        }
      })
    },
    created() {},
    data() {
      return {
        state_say: false, //'chat-say_touched'
        say_cancel: false,
        is_up: false,
        test: false
          //				timeOver:this.timeOut
      }
    },
    computed: {

    },
    watch: {},
    methods: {
      switch_way_press() { // 按住
        this.maskhide2 = false;
        this.state_say = true;
        this.say_cancel = false;
        this.is_up = false;
        this.timeOut = false;
        this.$emit("start_tap");
      },
      switch_way_end() { // 松开
        this.state_say = false;
        this.say_cancel = false;
        this.timeOut = false;
        this.$emit('over_tap', this.is_up);
      },
      switch_way_up() {
        this.say_cancel = true;
        this.state_say = true;
        this.is_up = true;

      },
      send() {
        this.$emit("send");
      },
      upload: function() {
        let that = this;
        that.$emit('upload');

      }
    },
    components: {

    }
  }
</script>

<style lang="less">
  .courseBottom {
    position: fixed;
    height: 50px;
    padding: 4px 10px;
    width: 100%;
    bottom: 0;
    left: 0;
    transition: bottom 300ms;
    border: 1px solid #d8d8da;
    background-color: #f3f3f5;
    * {
      -webkit-touch-callout: none;
      -webkit-user-select: none;
      -khtml-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
      -o-user-select: none;
    }
    .courseBox {
      height: 100%;
      line-height: 40px;
      height: 100%;
      
      .widthblock {
	    width: 100% !important;
	  }
      
      .bottomLeft {
        float: left;
        width: 80%;
        height: 100%;
        padding: 1px;
        text-align: center;
        
        .chat-say {
          height: 100%;
          background-color: #f3f3f5;
          border: 1px solid #d3d3d5;
          border-radius: 5px;
          -webkit-touch-callout: none;
          -webkit-user-select: none;
          -khtml-user-select: none;
          -moz-user-select: none;
          -ms-user-select: none;
          user-select: none;
          -o-user-select: none;
        }
        .chat-say_touched {
          background-color: #c6c7ca;
          .two {
            display: block;
          }
          .one {
            display: none;
          }
        }
        .two {
          display: none;
        }
      }
      .bottomRight {
        float: right;
        height: 100%;
        width: 20%;
        padding-left: 15px;
      }
    }
  }
  
  .recordingWrap {
    position: fixed;
    left: 50%;
    top: 45%;
    transform: translate(-50%, -50%);
    width: 100%;
    height: 90%;
    
    .recording {
      position: fixed;
      left: 50%;
      top: 45%;
      transform: translate(-50%, -50%);
      width: 140px;
      height: 140px;
      padding: 5px;
      background-color: rgba(0, 0, 0, .5);
      color: #ffffff;
      border-radius: 5px;
      font-size: 14px;
      text-align: center;
      z-index: 22;
      
      .recording-voice .voice-inner {
        display: flex;
        justify-content: center;
        align-items: center;
        
        .voice-volume {
          width: 30px;
          height: 55px;
          
          span {
            display: block;
            height: 2px;
            margin-top: 4px;
            min-width: 8px;
            float: left;
            clear: both;
            animation-iteration-count: infinite;
            animation-timing-function: linear;
            animation-duration: 2000ms;
            background-color: #e4e4e5;
            /* opacity: 0 ; */
          }
          span:nth-child(1) {
            width: 24px;
            visibility: hidden;
          }
          span:nth-child(1) {
            width: 24px;
            visibility: hidden;
          }
          span:nth-child(2) {
            width: 22px;
          }
          span:nth-child(3) {
            width: 20px;
          }
          span:nth-child(4) {
            width: 18px;
          }
          span:nth-child(5) {
            width: 16px;
          }
          span:nth-child(6) {
            width: 14px;
          }
          span:nth-child(7) {
            width: 12px;
          }
          span:nth-child(8) {
            width: 10px;
          }
          span:nth-child(9) {
            width: 8px;
          }
        }
      }
      .recording-cancel {
        p {
          border-radius: 3px;
          background-color: #9d383e;
        }
      }
    }
  }
  
  
</style>