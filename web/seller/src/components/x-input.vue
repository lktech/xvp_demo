<template>
	<div class="weui_cell" :class="{'weui_cell_warn': !valid}">
    <div class="weui_cell_hd">
      <!--<label class="weui_label" :style="{width: $parent.labelWidth || (labelWidth + 'em'), textAlign: $parent.labelAlign, marginRight: $parent.labelMarginRight}" v-if="title">{{title}}</label>-->
      <label class="weui_label" :style="{width:'5em', textAlign: '$parent.labelAlign', marginRight: '1em',overflow:'hidden',whiteSpace:'nowrap'}" v-if="title">{{title}}</label>
      <inline-desc v-if="inlineDesc">{{inlineDesc}}</inline-desc>
    </div>
    <div class="weui_cell_bd weui_cell_primary">
      <input
      class="weui_input"
      :maxlength="max"
      :autocomplete="autocomplete"
      :autocapitalize="autocapitalize"
      :autocorrect="autocorrect"
      :spellcheck="spellcheck"
      :style="inputStyle"
      :type="type"
      :name="name"
      :pattern="pattern"
      :placeholder="placeholder"
      :readonly="readonly"
      v-model="value"
      @blur="blur"
      v-el:input/>
    </div>
    <div class="weui_cell_ft">
      <icon type="clear" v-show="showClear && value && !readonly" @click="clear"></icon>
      <icon type="warn" title="{{!valid ? firstError : ''}}" v-show="!equalWith && ((touched && !valid && firstError) || (forceShowError && !valid && firstError))"></icon>
      <icon type="warn" v-show="hasLengthEqual && dirty && equalWith && !valid"></icon>
      <icon type="success" v-show="equalWith && equalWith===value && valid"></icon>
      <slot name="right"><slot>
    </div>
  </div>
</template>

<script>
import Base from '../libs/base.js'
import Icon from './icon.vue'
import InlineDesc from './inline-desc.vue'

const validators = {
  'email': {
      fn(str){
         return str.search(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/) > -1
      },
      msg: '邮箱格式'
    },
    'mobile': {
      fn (str) {
        return str.search(/^1[0-9]{10}$/) > -1
      },
      msg: '手机号'
      },
      'vcode': {
        fn (str) {
          return str.search(/^[0-9]{6}$/) > -1
        },
        msg: '验证码'
      },
      'name': {
        fn (str) {
          return str.length > 0
        },
        msg: '名字'
      },
      'notnull': {
        fn (str) {
          return str.length > 0
        },
        msg: '不能为空'
      },
      'number': {
        fn (str) {
           return str.search(/^[0-9]{0,}$/) > -1;
        },
        msg: '纯数字'
      },
      'money': {
        fn (str) {
           return str.search(/^[0-9]{1}\d*(\.\d{1,2})?$/) > -1;
        },
        msg: '金额'
      }
    }
    export default {
      ready () {
          if (!this.title && !this.placeholder && !this.value) {
            console.warn('no title and no placeholder?')
          }
          if (this.equalWith) {
            this.showClear = false
          }
          if (this.required && !this.value) {
            this.valid = false
          }
          if (this.isType === 'email') {
            this.type = 'email'
          }
          if (this.isType === 'mobile') {
            this.type = 'mobile'
          }
          if (this.isType === 'vcode') {
            this.type = 'vcode'
          }
          if (this.isType === 'number') {
            this.type = 'number'
          }
    },
    mixins: [Base],
            components: {
      Icon,
              InlineDesc
    },
    props: {
      title: {
        type: String,
      default: ''
      },
      placeholder: String,
      value: {
        type: String,
        default: '',
        twoWay: true
      },
      name: String,
      readonly: {
        type: Boolean,
       default: false
      },
      keyboard: String,
      inlineDesc: String,
      isType: String,
      min: Number,
      max: Number,
      showClear: {
      type: Boolean,
      default: true
      },
      equalWith: String,
      type: {
        type: String,
       default: 'text'
      },
      textAlign: String,
        autocomplete: 'off',
        autocapitalize: 'off',
        autocorrect: 'off',
        spellcheck: 'false'
    },
  computed: {
    pattern () {
      if (this.keyboard === 'number' || this.isType === 'mobile' || this.isType === 'vcode' || this.isType === 'number') {
        return '[0-9]*'
      }
    },
    labelWidth () {
      return this.title.replace(/[^x00-xff]/g, '00').length / 2 + 1
    },
    hasErrors () {
      return Object.keys(this.errors).length > 0
    },
    inputStyle () {
      if (this.textAlign) {
        return {
          textAlign: this.textAlign
        }
      }
    }
  },
  methods: {
    clear () {
      this.value = ''
      this.focus = true
    },
    blur () {
      this.setTouched()
      this.validate()
    },
    getError () {
      let key = Object.keys(this.errors)[0]
      this.firstError = this.errors[key]
    },
    validate () {
      if (this.equalWith) {
        this.validateEqual()
        return
      }
      this.errors = {}

      if (!this.value && !this.required) {
        this.valid = true
        return
      }

      if (!this.value && this.required) {
        this.valid = false
        this.errors.required = '必填哦'
        return
      }

      const validator = validators[this.isType]
      if (validator) {
        this.valid = validator[ 'fn' ](this.value)
        if (!this.valid) {
          this.errors.format = validator[ 'msg' ] + '格式不对哦~'
          return
        } else {
          delete this.errors.format
        }
      }

      if (this.min) {
        if (this.value.length < this.min) {
          this.errors.min = this.$interpolate('最少应该输入{{min}}个字符哦')
          this.valid = false
          this.getError()
          return
        } else {
          delete this.errors.min
        }
      }

      if (this.max) {
        if (this.value.length > this.max) {
          this.errors.max = this.$interpolate('最多可以输入{{max}}个字符哦')
          this.valid = false
          this.forceShowError = true
          return
        } else {
          this.forceShowError = false
          delete this.errors.max
        }
      }

      this.valid = true
    },
    validateEqual () {
      let willCheck = this.dirty || this.value.length >= this.equalWith.length
      //只在长度符合时显示正确与否
      if (willCheck && this.value !== this.equalWith) {
        this.valid = false
        this.errors.equal = '输入不一致'
        return
      } else {
        this.valid = true
        delete this.errors.equal
      }
    }
  },
  data () {
    let data = {
      firstError: '',
      forceShowError: false,
      hasLengthEqual: false,
      focus: false
    }
    return data
  },
  watch: {
    focus (newVal) {
      if (newVal) {
        this.$els.input.focus()
      }
    },
    valid () {
      this.getError()
    },
    value (newVal) {
      if (this.equalWith) {
        if (newVal.length === this.equalWith.length) {
          this.hasLengthEqual = true
        }
        this.validateEqual()
      } else {
        this.validate()
      }

      this.$emit('validate',{"name":this.name,"status":this.valid,"type":this.isType,"error":this.errors});
    }
  }
}
</script>

<style lang="less" scoped>
@import '../assets/styles/weui/widget/weui_cell/weui_access.less';
@import '../assets/styles/weui/widget/weui_cell/weui_cell_global.less';
@import '../assets/styles/weui/widget/weui_cell/weui_form/weui_form_common.less';
@import '../assets/styles/weui/widget/weui_cell/weui_form/weui_vcode.less';
</style>
