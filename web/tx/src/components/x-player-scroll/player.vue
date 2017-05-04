<template>
  <div class="m-pl-wrap" :class="'pos'+num">
    <div class="m-pl" :class="{'m-pl-fixed':fixed}" :style="style">
      <div class="m-pl-ctn" :class="{'border-t':fixed}">
        <div class="m-player">
          <span class="ic" :class="{'ic-act':playing}" @click="clk"></span>
          <div class="tp">
            <span>{{name}}</span>
            <span class="float-right">{{showTime}} / {{formatTime(time)}}</span>
          </div>
          <div class="btm txt-ofl">{{title}}</div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import format from './format.js';
  export default {
    props: {
      title: String,//标题
      name: String,//小标题
      time: Number,//时间
      current: Number,//当前播放时间
      index: Number,//正在播放的音频标号
      offset:{
        type:Number,
        default:45
      },
      num:{//在页面是第几段  0开始
        type:Number,
        default(){
          return 0;
        }
      }
    },
    data(){
      return {
        style:{},
        fixed:false,//是否fixed
        playing:false,//是否在播放
        showTime:"00:00",//显示的播放时间
      }
    },
    mounted() {
      this.$nextTick(() => {
        let that = this;

        var offset = this.offset;
      if(document.body.scrollHeight - window.screen.availHeight > 70){
        $(window).scroll(function() {
          var scrollTop = document.body.scrollTop;
          var offsetTop = that.$el.offsetTop;
          if(scrollTop == 0){
            that.fixed = false;
            that.style = {};
          }else{
            if (scrollTop > offsetTop - offset - 5) {
              that.fixed = true;
              that.style = {
                "top":offset + "px"
              };
            } else {
              that.fixed = false;
              that.style = {};
            }
          }

          that.change(that.fixed);
        });
      }


      })
    },
    methods: {

      formatTime(time){
        return format.formatTime(time);
      },
      change(){
        this.$emit("change",{"fixed":this.fixed,"num":this.num});
      },
      clk(){
        this.playing = !this.playing;
        this.$emit("play",{"playing":this.playing,"num":this.num});
      },

    },
    watch:{
      index(newVal){
        this.showTime = "00:00";
        if(newVal == this.num){
          this.playing = true;
        }else{
          this.playing = false;
        }
      },
      current(newVal){
        if(this.index == this.num){
          this.showTime = this.formatTime(newVal);
        }

      }
    },
    components: {

    }
  }
</script>