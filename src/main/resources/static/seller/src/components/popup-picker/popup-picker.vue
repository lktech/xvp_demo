<template>
  <div class="vux-cell-box">
    <div class="weui_cell vux-tap-active" @click="onClick" v-show="showCell">
      <div class="weui_cell_hd">
        <label class="weui_label" :style="{display: 'block', width: $parent.labelWidth, textAlign: $parent.labelAlign, marginRight: $parent.labelMarginRight}" v-if="title" v-html="title"></label>
        <inline-desc v-if="inlineDesc">{{inlineDesc}}</inline-desc>
      </div>
      <div class="weui_cell_bd weui_cell_primary with_arrow vux-popup-picker-select-box">
        <div class="vux-popup-picker-select" :style="{textAlign: valueTextAlign}">
          <span class="vux-popup-picker-value" v-if="!showName && value.length">{{value | array2string}}</span>
          <span class="vux-popup-picker-value" v-if="showName && value.length">{{value | value2name(data)}}</span>
          <span v-if="!value.length && placeholder" v-html="placeholder"></span>
        </div>
      </div>
      <div class="weui_cell_ft">

      </div>
    </div>

    <popup v-model="showValue" class="vux-popup-picker" :id="'vux-popup-picker-'+uuid" @on-hide="onPopupHide" @on-show="$emit('on-show')">
      <div class="vux-popup-picker-container">
        <div class="vux-popup-picker-header">
          <flexbox>
            <flexbox-item class="vux-popup-picker-header-menu" @click.native="onHide(false)">{{cancelText || '取消'}}</flexbox-item>
            <flexbox-item class="vux-popup-picker-header-menu vux-popup-picker-header-menu-right" @click.native="onHide(true)">{{confirmText || '完成'}}</flexbox-item>
          </flexbox>
        </div>
        <picker
        :data="data"
        v-model="tempValue"
        @on-change="onPickerChange"
        :columns="columns"
        :fixed-columns="fixedColumns"
        :container="'#vux-popup-picker-'+uuid"></picker>
      </div>
    </popup>

  </div>
</template>

<script>
import Picker from '../picker/picker.vue'
import Cell from '../cell/cell.vue'
import Popup from '../popup/popup.vue'
import InlineDesc from '../inline-desc/inline-desc.vue'
import Flexbox from '../flexbox/flexbox.vue'
import FlexboxItem from '../flexbox/flexbox-item.vue'
import array2string from '../../libs/components/filters/array2String.js'
import value2name from '../../libs/components/filters/value2name.js'
import uuidMixin from '../../libs/components/mixin_uuid.js'

const getObject = function (obj) {
  return JSON.parse(JSON.stringify(obj))
}

export default {
  created () {
    if (typeof this.show !== 'undefined') {
      this.showValue = this.show
    }
  },
  mixins: [uuidMixin],
  components: {
    Picker,
    Cell,
    Popup,
    Flexbox,
    FlexboxItem,
    InlineDesc
  },
  filters: {
    array2string,
    value2name
  },
  props: {
    valueTextAlign: {
      type: String,
      default: 'right'
    },
    title: String,
    cancelText: String,
    confirmText: String,
    data: {
      type: Array,
      default () {
        return []
      }
    },
    placeholder: String,
    columns: {
      type: Number,
      default: 0
    },
    fixedColumns: {
      type: Number,
      default: 0
    },
    value: {
      type: Array,
      default () {
        return []
      }
    },
    showName: Boolean,
    inlineDesc: [String, Number, Array, Object, Boolean],
    showCell: {
      type: Boolean,
      default: true
    },
    show: Boolean
  },
  methods: {
    getNameValues () {
      return value2name(this.currentValue, this.data)
    },
    onClick () {
      this.showValue = true
    },
    onHide (type) {
      this.showValue = false
      if (type) {
        this.closeType = true
        this.currentValue = getObject(this.tempValue)
      }
      if (!type) {
        this.closeType = false
        if (this.value.length > 0) {
          this.tempValue = getObject(this.currentValue)
        }
      }
    },
    onPopupHide (val) {
      if (this.value.length > 0) {
        this.tempValue = getObject(this.currentValue)
      }
      this.$emit('on-hide', this.closeType)
    },
    onPickerChange (val) {
      if (JSON.stringify(this.currentValue) !== JSON.stringify(val)) {
        // if has value, replace it
        if (this.value.length) {
          const nowData = JSON.stringify(this.data)
          if (nowData !== this.currentData && this.currentData !== '[]') {
            this.tempValue = getObject(val)
          }
          this.currentData = nowData
        } else { // if no value, stay quiet
          // if set to auto update, do update the value
        }
      }
      this.$emit('on-shadow-change', getObject(val))
    }
  },
  watch: {
    value (val) {
      if (JSON.stringify(val) !== JSON.stringify(this.tempValue)) {
        this.tempValue = getObject(val)
      }
    },
    currentValue (val) {
      this.$emit('input', val)
    },
    show (val) {
      this.showValue = val
    }
  },
  data () {
    return {
      onShowProcess: false,
      tempValue: getObject(this.value),
      closeType: false,
      currentData: JSON.stringify(this.data), // used for detecting if it is after data change
      showValue: false,
      currentValue: this.value
    }
  }
}
</script>

<style lang="less">
@import '../../assets/styles/components/variable.less';

.vux-cell-box {
  position: relative;
}
.vux-cell-box:before {
  content: " ";
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 1px;
  border-top: 1px solid #D9D9D9;
  color: #D9D9D9;
  transform-origin: 0 0;
  transform: scaleY(0.5);
  left: 15px;
}
.vux-popup-picker-header {
  height: 44px;
  color: @popup-picker-header-text-color;
}
.vux-popup-picker-value {
  /* display: inline-block; */
}
.vux-popup-picker-header-menu {
  text-align: left;
  padding-left: 15px;
  line-height: 44px;
}
.vux-popup-picker-header-menu-right {
  text-align: right;
  padding-right: 15px;
}
.vux-popup-picker-select {
  width: 100%;
  position: relative;
}
.vux-popup-picker-select span {
  padding-right: 15px;
}
.vux-popup-picker-select-box.weui_cell_bd:after {
  content: " ";
  display: inline-block;
  transform: rotate(45deg);
  height: 6px;
  width: 6px;
  border-width: 2px 2px 0 0;
  border-color: #C8C8CD;
  border-style: solid;
  position: relative;
  top: -2px;
  position: absolute;
  top: 50%;
  right: 15px;
  margin-top: -3px;
}
</style>
