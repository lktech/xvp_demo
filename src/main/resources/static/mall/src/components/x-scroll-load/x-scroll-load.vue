<template>
  <div class="scroll-load">
    <slot></slot>
    <div class="load-more-wrap">
      <div class="load-more" v-show="noMore || isLoading">{{noMore?'无更多内容':'加载中..'}}</div>
    </div>
  </div>
</template>

<script>
  import utils from '../../libs/utils.js';
  export default {
    props: ['url', 'params', 'eleid', 'type'],
    mounted() {
      this.$nextTick(() => {
        let that = this,
          el = $(window);

        if(that.eleid) {
          el = $("#" + that.eleid);
        }
        el.scroll(function() {
          let scrollTop = $(this).scrollTop(),
            windowHeight = $(this).height(),
            scrollHeight = $(document).height();

          if(that.eleid) {
            scrollHeight = this.scrollHeight;
          }
          if(scrollTop + windowHeight == scrollHeight) {
            if(that.isLoading) {
              return;
            }
            if(that.noMore) {
              return;
            }
            that.isLoading = true;
            that.load();
          }
        });
      })
    },
    created() {},
    data() {
      return {
        isLoading: false,
        noMore: false,
        data: this.params ? this.params : {
          "pageNumber": 2,
          "pagesize": 15
        },
        ajaxtype: this.type ? this.type : 'POST'
      }
    },
    computed: {},
    watch: {},
    destroyed() {
      let that = this,
        el = $(window);

      if(that.eleid) {
        el = $("#" + that.eleid);
      }
      el.off('scroll');
    },
    methods: {
      load() {
        let that = this;
        var objList = {
          url: this.url,
          data: this.data,
          success: function(data) {
            that.isLoading = false;
            if(data.success) {
              if(data) {
                if(data.obj.length > 0) {
                  that.$emit('on-load', data);
                  that.data.pageNumber++;
                } else {
                  that.noMore = true;
                }
              } else {
                that.$emit('on-load', false);
              };
            } else {
              that.$emit('on-load', false);
            };
          },
          error: function() {
            that.isLoading = false;
            that.$emit('on-load', false);
          }
        };
        utils.ajax(objList);
      }
    },
    components: {
      "cIcon": require('../icon/icon.vue'),
      "cDatanull": require('../x-datanull/x-datanull.vue')
    }
  }
</script>

<style lang="less">
  @import '../../assets/styles/components/rui/scroll-load.less';
</style>