<template>
  <div class="m-pl-list">
    <r-popup v-model="show" height="100%">
      <div class="list-bg" @click="show=false">

        <div class="info info-fixed" @click.stop="stopClick">
          <div class="ctn">
            <img class="uimg" :src="img"/>
            <div class="toolbar"  @click.stop="hide"></div>
            <div class="title txt-ofl">{{title}}</div>
          </div>
        </div>
        <div class="topline border-b"></div>
        <div class="list-main" @click.stop="stopClick">
          <div class="item border-b" :class="{'item-active':index == active}" v-for="(item,index) in list" @click="change(index)">
            <div class="txt-ofl"><i></i>{{item.chapterTitle}}</div>
            <span class="time">{{formatTime(item.voiceTimeLong)}}</span>
          </div>

        </div>

      </div>
    </r-popup>
  </div>

</template>

<script>
  import format from './format.js';
  export default {
    props: {
      list: Array,
      value: {
        type: Boolean,
        default:false
      },
      active:{
        type:Number,
        default(){
          return 0;
         }
      },
      title:String,
      img:String
    },
    data(){
      return {
        show:false
      }
    },
    mounted() {
      this.$nextTick(() => {



      })
    },
    methods: {
      formatTime(time){
        return format.formatTime(time);
      },
      stopClick(){
        return false;
      },
      hide(){
        this.show = false;
      },
      change(index){
        this.$emit('change', index);
      }
    },
  watch: {
    show(val) {
      this.$emit('input', val);
    },
    value(val) {
      this.show = val
    }
  },
    components: {
      "rPopup": require('../popup/popup.vue'),
    }
  }
</script>