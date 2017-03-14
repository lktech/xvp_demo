<!-- 子组件   -->
<template>
  <div class="allchecked">
    <div class=" msgbub-wrap">
      <div class="weui_cells_checkbox wrap-pdt of" v-for='item in formatList'>
        <div class="clr-white time">{{item.time | fmtDate}}</div>
        <div class="wrap-pdt of">
          <div class="cb wrap-pdt">
            <input type="checkbox" v-model="item.checked" v-bind:class="[item.checked==true?'weui_check':'']" />
            <i class="weui_icon weui_icon_checked iconlist" @click='item.checked=!item.checked' :id='item.id'></i>
          </div>
          <div class="head">
            <div><img :src="item.headUrl" width="44" height="44"></div>
            <div class="label3-small text-center margint8">{{{item.userName?item.userName:item.nike | emoji}}}</div>
          </div>
          <div class="bub">
            <div class="arrow"></div>
            <div class="label6-small bub-con" v-if="item.type==1">{{{item.msg | emoji}}}</div>
            <div class="label6-small bub-con" v-if="item.type==34"><img src="../assets/images/msgbub-audio.png" width="22" height="22"></div>
            <div class="label6-small bub-con" v-if="item.type==62"><img src="../assets/images/msgbub-video.png" width="44" height="44"></div>
            <div class="label6-small bub-con" v-if="item.type==3"><img :src="item.file" width="44" height="44"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="bottom weui_cells_checkbox">
      <label class="fl">
				<div class="cbAll">
					<input type="checkbox" class="weui_check fr" v-model="allChecked"/>
					<i class="weui_icon_checked fl all"></i>
				</div>
				<span class="allblue">{{checketext?checketext:'全选'}}</span>
			</label>

      <div class="weui_media_bd fr">
        <p class="button" @click='submit'>{{btntext}}</p>
      </div>
    </div>

  </div>
  </div>

</template>

<script>
  import utils from '../libs/utils.js';
  export default {
    props: {
      btntext: String,
      checketext: String,
      isLink: Boolean,
      primary: {
        type: String,
        default: 'title'
      },
      list: [Array],
      format: {
        type: Object,
        default() {
          return {
            "notneed": true
          }
        }
      },
      link: Object,
    },

    //		 props: ['type','bubList','link','format'],
    data() {
      return {
        formatList: []
      }
    },

    watch: {
      list(newList) {
        //console.log(newList);
        //this.formatList = this.formatList.concat(this.formatFunc(newList));
        this.formatList = this.formatFunc(newList);
      }
    },
    ready() {
      console.log("ready,list111");
      console.log(this.list);
      let that = this;
      //			setTimeout(function(){
      //				that.list = [];
      //			},2000);
    },
    methods: {
      submit() {
        let ids = [],
          list = [];
        this.formatList.forEach(function(item) {
          if(item.checked) {
            ids.push(item.id);
            list.push(item);
          }
        });
        this.$emit("submit", {
          "ids": ids,
          "list": list
        });
      },
      formatFunc(list) {
        let that = this,
          flist = [];

        if(that.format.notneed) {
          return list;
        } else {
          list.forEach(function(item) {
            let id = that.format.id,
              checked = that.format.checked,
              type = that.format.type,
              time = that.format.time,
              headUrl = that.format.headUrl,
              nike = that.format.nike,
              msg = that.format.msg,
              file = that.format.file,
              userName = that.format.userName;

            let fitem = {};
            fitem.id = item[id],
              fitem.checked = item[checked] ? item[checked] : false,
              fitem.type = item[type],
              fitem.time = item[time],
              fitem.headUrl = item[headUrl],
              fitem.nike = item[nike],
              fitem.msg = item[msg],
              fitem.file = item[file],
              fitem.userName = item[userName],
              flist.push(fitem);
          });
          return flist;
        }
      },
    },
    computed: {
      allChecked: {
        get: function() {
          return this.checkedCount == this.formatList.length;
        },
        set: function(value) {
          this.formatList.forEach(function(item) {
            item.checked = value;
          })
          return value;
        }
      },
      checkedCount: {
        get: function() {
          var i = 0;
          this.formatList.forEach(function(item) {
            if(item.checked == true) i++;
          })
          return i;
        }
      }

    },
    components: {
      "cIcon": require('./icon.vue')
    }
  }
</script>
<style lang="less">
  @import '../assets/styles/weui/widget/weui_media_box/weui_media_box.less';
  @import '../assets/styles/weui/widget/weui_cell/weui_check.less';
  @import "../assets/styles/components/msg-bubble.less";
  .allchecked .fr {
    float: right;
  }
  
  .allchecked .fl {
    float: left;
  }
  
  .allchecked input {
    height: 23px;
    width: 23px;
    z-index: 9999;
    display: none;
  }
  
  .allchecked .bottom {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 49px;
    line-height: 49px;
    padding-left: 10px;
    background-color: @color-white;
    z-index: 99;
  }
  
  .allchecked .button {
    width: 120px;
    height: 49px;
    color: @color-white;
    text-align: center;
    background-color: #31B3F5;
  }
  
  .allchecked .allblue {
    color: #31B3F5;
  }
  
  .allchecked .bottom> .fl>.all,
  .allchecked .bottom> .fl input {
    margin-top: 10px;
    margin-right: 10px;
  }
  
  .allchecked .weui_check {
    position: absolute;
    left: 10px;
  }
  /* 第二行文字 */
  
  .allchecked .weui_cell_bd .label9 {
    color: #666;
  }
  /* 第三行文字 */
  
  .allchecked .weui_cell_bd .groupname {
    margin-right: 12px;
  }
  
  .allchecked .weui_cell_bd .time {
    font-size: 12px;
    color: #999;
  }
  
  .allchecked .cb {
    float: left;
    width: 30px;
  }
  
  .allchecked .cbAll {
    float: left;
    width: 30px;
    margin-top: 15px;
  }
</style>