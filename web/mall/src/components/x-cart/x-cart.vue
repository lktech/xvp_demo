<template>
  <div class="cart">
    <!-- cart -->
    <div class="weui_panel weui_panel_access" v-if="this.formatList.length > 0">
      <!-- 店铺栏 -->
      <div class="weui_panel_hd">
        <span>{{shop}}</span>
        <span class="edit-item org" @click="changeState">{{isEdit?'完成':'编辑'}}</span>
      </div>
      <!-- 列表栏 -->
      <div class="weui_panel_bd weui_cells_checkbox border-b" v-for='item in formatList'>
        <a class="weui_media_box weui_media_appmsg" href="javascript:;">
          <input type="checkbox" v-model="item.checked" v-bind:class="[item.checked==true?'weui_check':'']" style="width: 10%;height: 10px;display: none;" />
          <i class="weui_icon weui_icon_checked" @click='item.checked=!item.checked'></i>
          <div class="weui_media_hd" @click='goUrl(item.link)'> <img class="weui_media_appmsg_thumb cart-itemImg" alt="" v-bind:src="item.sourceUrl"> </div>
          <div class="weui_media_bd marleft" @click='goUrl(item.link)'>
            <!-- 第一行文字 -->
            <h4 class="weui_media_title sixtysize">{{item.name}}</h4>
            <!-- 第二行文字 -->
            <span class="weui_media_desc details">{{item.formats}}:{{item.formatsel}}</span>
            <!-- 第三行文字 价格-->
            <span class="weui_media_desc from fl">
						<span class="price">
							{{item.price|formatPrice}}
						</span>
            <!-- 第三行文字 数量-->
            <span>x</span><span>{{item.count}}</span></span>
          </div>
          <!-- 加减栏 -->
          <r-number v-model='item.count' v-if='isEdit'></r-number>
        </a>
      </div>
    </div>
    <!-- 全选栏 -->
    <div class="bottom weui_cells_checkbox">
      <label class="fl">
			<input type="checkbox" class="weui_check fr" v-model="allChecked"/>
			<i class="weui_icon_checked fl all"></i>
			<span class="maright fortysize">{{checketext?checketext:'全选'}}</span>
		</label>
      <span class="fortysize org">合计：{{price|formatPrice}}</span>
      <span class="edit-item button fr" @click='tosetcount' v-bind:class="[price>0?'colororg':'tosetcount']">{{isEdit?'删除':'去结算'}}</span>
    </div>
  </div>

</template>

<script>
  import utils from "../../libs/utils.js";
  export default {
    props: {
      shop: String,
      list: Array,
      checketext: String,
      format: {
        type: Object,
        default() {
          return {
            "notneed": true
          }
        }
      }
    },
    mounted() {
      this.$nextTick(() => {

      })
    },
    created() {},
    data() {
      return {
        isEdit: false,
        formatList: [],
      }
    },
    computed: {
      allChecked: {
        get: function() {
          if(this.formatList.length != 0) {
            return this.checkedCount == this.formatList.length;
          } else {
            return false;
          }
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
      },
      price: function() {
        var price = 0;
        this.formatList.forEach(function(item) {
          if(item.checked) {
            let p = item.count * item.price
            price += p;
          }

        })

        return price;
      },

    },
    watch: {
      list(newList) {
        this.formatList = this.formatFunc(newList);
        if(!this.formatList.length) {
          $('.weui_cells').addClass('removeBorder');
        } else {
          $('.weui_cells').removeClass('removeBorder');
        };
      }
    },
    methods: {
      tosetcount: function() {
        let checkList = [],
          surplusList = [],
          that = this;

        this.formatList.forEach(function(item, index) {
          if(item.checked == true) {
            checkList.push({
              id: item.id,
              num: item.count
            });
          } else {
            surplusList.push(item);
          }
        });

        this.$emit('hander', checkList, surplusList, this.isEdit ? 'delete' : 'edit');

      },

      changeState: function() {
        this.isEdit = !this.isEdit;
      },

      goUrl: function(url) {
        utils.go(url, this.$router);
      },
      formatFunc(list) {
        let that = this,
          flist = [];
        if(that.format.notneed) {
          return list;
        } else {
          list.forEach(function(item) {
            let id = that.format.id,
              name = that.format.name,
              formats = that.format.formats,
              formatsel = that.format.formatsel,
              checked = that.format.checked,
              count = that.format.count,
              price = that.format.price,
              sourceUrl = that.format.sourceUrl,
              link = that.format.link,
              num = that.format.num

            let fitem = {};
            fitem.id = item[id],
              fitem.name = item[name],
              fitem.formats = item[formats],
              fitem.formatsel = item[formatsel],
              fitem.sourceUrl = item[sourceUrl],
              fitem.link = item[link],
              fitem.price = item[price],
              fitem.count = item[count],
              fitem.num = item[num],
              fitem.checked = item[checked] ? item[checked] : false;
            flist.push(fitem);
          });
          return flist;
        }
      },
      change(i, item) { // number 
        item = this.formatList[i].count;
      }
    },
    components: {
      "rNumber": require('../number/number.vue'),
    }
  }
</script>

<style lang="less">
  @import '../../assets/styles/components/rui/cart.less';
</style>