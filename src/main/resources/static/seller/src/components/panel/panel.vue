<template>
  <div class="weui_panel weui_panel_access">
    <div class="weui_panel_hd" v-if="header" @click="onClickHeader" v-html="header"></div>
    <div class="weui_panel_bd">
      <!--type==='1'-->
      <div v-if="type === '1'">
        <div v-for="item in list" @click="onItemClick(item)" class="weui_media_box weui_media_appmsg">
          <div class="weui_media_hd" v-if="item.src">
            <img class="weui_media_appmsg_thumb" :src="item.src" alt="">
          </div>
          <div class="weui_media_bd">
            <h4 class="weui_media_title">{{item.title}}</h4>
            <p class="weui_media_desc">{{item.desc}}</p>
          </div>
        </div>
      </div>
      <!--type==='2'-->
      <div v-if="type === '2'">
        <div class="weui_media_box weui_media_text" v-for="item in list" @click="onItemClick(item)">
            <h4 class="weui_media_title">{{item.title}}</h4>
            <p class="weui_media_desc">{{item.desc}}</p>
        </div>
      </div>
      <!--type==='3'-->
      <div v-if="type === '3'">
        <div class="weui_media_box weui_media_small_appmsg">
            <div class="weui_cells weui_cells_access">
              <div class="weui_cell" v-for="item in list" @click="onItemClick(item)">
                <div class="weui_cell_hd">
                  <img :src="item.src" alt="" style="width:20px;margin-right:5px;display:block">
                </div>
                <div class="weui_cell_bd weui_cell_primary">
                  <p>{{item.title}}</p>
                </div>
                <span class="weui_cell_ft"></span>
              </div>
            </div>
        </div>
      </div>
    </div>
    <div class="weui_panel_ft" v-if="footer && type !== '3'" @click="onClickFooter" v-html="footer.title"></div>
  </div>
</template>

<script>
import utils from '../../libs/utils.js'

export default {
  props: {
    header: String,
    footer: Object,
    list: Array,
    type: {
      type: String,
      default: '1'
    }
  },
  methods: {
    onClickFooter(){
      this.$emit('on-click-footer')
      utils.go(this.footer.url, this.$router)
    },
    onClickHeader () {
      this.$emit('on-click-header')
    },
    onItemClick (item) {
      this.$emit('on-click-item', item)
      utils.go(item.url, this.$router,item.replace)
    }
  }
}
</script>

<style lang="less">
@import '../../assets/styles/components/weui/widget/weui_panel/weui_panel.less';
@import '../../assets/styles/components/weui/widget/weui_media_box/weui_media_box.less';
</style>