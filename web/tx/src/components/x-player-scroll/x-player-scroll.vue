<template>
  <div class="player-scroll">
    <div class="info" :class="{'info-fixed':isFixed}">
      <div class="ctn">
        <img class="uimg" :src="course.lecturerHeadPic" @click="clkImg"/>
        <div class="toolbar" @click="clkToolBar"></div>
        <div class="name">
          <span>{{course.lecturerName}}</span>
         <!-- <span class="clr-blue" @click="clkDesc">查看讲师介绍</span>-->
        </div>
        <div class="title txt-ofl">{{course.courseName}}</div>
      </div>
    </div>

    <div class="p-main">

      <div v-for="(item,index) in course.contentList">
        <r-player :name="item.chapterName" :title="item.chapterTitle" :time="item.voiceTimeLong" :current="playTime" :num="index" @change="change" @play="setPlayer" :index="playIndex"></r-player>
        <div class="m-txt" v-html="item.content"></div>
      </div>


    </div>

    <div class="btp" v-show="showBack" @click="backToTop"><div class="btp-ctn"></div></div>
    <r-list v-model="showList" :list="course.contentList" :title="course.courseName" :img="course.lecturerHeadPic" :active="posIndex" @change="changeIndex"></r-list>
    <audio :src="playSrc" ref="audio"></audio>
  </div>
</template>

<script>
  import utils from '../../libs/utils.js';
  export default {
    props: {
      course: Object
    },
    data(){
      return {
        isFixed:false,
        showBack:false,//是否显示 返回顶部
        showList:false,//显示列表
        playIndex:-1,//当前播放的章节序号
        posIndex:0,//当前定位的章节序号
        playTime:0,//当前播放的时间
        playList:[],//当前播放的音频列表
        playSrc:"",//当前文件资源
        playSrcIndex:0//当前文件资源
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.scroll();
        this.setPlayer({"playing":false,"num":-1});
        this.sepakListening();
      })
    },
    methods: {
      backToTop(){//返回顶部
        $("body,html").animate({scrollTop:0},500);
      },
      scroll(){
        let that = this;
        if(document.body.scrollHeight - window.screen.availHeight > 70){
          $(window).scroll(function() {
            var sTop = $(this).scrollTop();
            if(sTop > 0){
              that.isFixed = true;
              if(sTop > 300){
                that.showBack = true;
              }else{
                that.showBack = false;
              }

            }else{
              that.isFixed = false;
            }
          });
        }


      },
      clkImg(){
        this.$emit("clickimg");
      },
      clkToolBar(){//点击toolbar
        this.showList = true;
      },
      clkDesc(){//点击查看介绍
        //alert("点击查看介绍");
      },
      change(data){

      },
      setPlayer(data){//切换播放的音频
        if(data.playing){
          if(this.playIndex != data.num){
            this.playList = this.course.contentList[data.num]?this.course.contentList[data.num].voiceList:[];
            this.playIndex = data.num;
            this.playSrcIndex = 0;
            this.playSrc = this.playList[this.playSrcIndex].filepath;
          }

          this.play();

        }else{
          this.pause();
        }
      },
      changeIndex(index){//点击弹层，进行定位
        this.showList = false;
        this.posIndex = index;
        var offsetTop = $(".player-scroll .pos"+index)[0].offsetTop - 49;
        $("body,html").animate({scrollTop:offsetTop},500);
      },
      //监听
      sepakListening() {
        let that = this;
        if(that.$refs.audio){
          that.$refs.audio.addEventListener('waiting', function () {
            that.$vux.loading.show({text: "正在读取中^_^"})
          }, false);
          that.$refs.audio.addEventListener("playing", function () {
            that.$vux.loading.hide()

          }, false);
          that.$refs.audio.addEventListener('canplay', function () {

            that.$refs.audio.addEventListener(
                    'timeupdate',
                    function () {
                      that.playTime = parseInt(that.$refs.audio.currentTime*1000);
                      that.setTime();
                    }, false);
          }, false);
          that.$refs.audio.addEventListener('ended', function () {
            that.playSrcIndex++;
            if(that.playSrcIndex < that.playList.length){
              that.setSrc(that.playSrcIndex);
              that.play();
            }else{
              //播放完了
              that.playIndex = -1;
//              if(that.playIndex < that.course.contentList.length - 1){
//                let num = that.playIndex+1;
//                that.setPlayer({"playing":true,"num":num});
//              }else{
//                console.log("over了");
//                that.playIndex = -1;
//              }

            }



          }, false);
        }

      },

      setTime(){
        let time = 0,that = this;
        if(this.playList.length > 0){
          this.playList.forEach(function(item,index){
            if(index < that.playSrcIndex){
              time += item.duration;
            }

          });
        }
        this.playTime += time;
      },
      formatTime(time){
        return format.formatTime(time);
      },
      play(){
        let that = this;
        if(that.$refs.audio){
          setTimeout(function(){that.$refs.audio.play();},100);
        }
      },
      pause(){
        let that = this;
        if(that.$refs.audio){
          setTimeout(function(){that.$refs.audio.pause();},100);
        }
      },
      //下一首
      setSrc(index) {
        if(this.playList[index] && this.playList[index].filepath){
          this.playSrc = this.playList[index].filepath;
          if(this.$refs.audio){
            this.$refs.audio.src = this.playSrc;
          }

        }
      },

    },
    components: {
      "rPlayer": require('./player.vue'),
      "rList": require('./list.vue'),
    }
  }
  require('../../assets/styles/components/rui/x-player-scroll.less');
</script>