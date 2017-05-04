<template>
  <div class="weui_cell question">
    <div class="weui_cell_bd weui_cell_primary">
      <textarea
              class="weui_textarea"
              :autocomplete="autocomplete"
              :autocapitalize="autocapitalize"
              :autocorrect="autocorrect"
              :spellcheck="spellcheck"
              :placeholder="placeholder"
              :readonly="readonly"
              :name="name"
              :rows="rows"
              :cols="cols"
              v-model="currentValue"
              :style="textareaStyle"
              :maxlength="max"
              ref="textarea"></textarea>
      <div v-if="upload" class="upload">
        <slot></slot>
        <div class="num"><span>{{count}}</span>/{{max}}</div>
      </div>
      <div v-else class="weui_textarea_counter" v-show="showCounter && max"><span>{{count}}</span>/{{max}}</div>
    </div>
  </div>
</template>

<script>
    import Base from '../../libs/components/base.js'

    export default {
        minxins: [Base],
        props: {
            emoji: Boolean,
            upload:Boolean,
            showCounter: {
                type: Boolean,
                default: true
            },
            max: Number,
            value: {
                type: String,
                default: ''
            },
            name: String,
            placeholder: String,
            readonly: {
                type: Boolean,
                default: false
            },
            rows: {
                type: Number,
                default: 3
            },
            cols: {
                type: Number,
                default: 30
            },
            height: Number,
            // https://github.com/yisibl/blog/issues/3
            autocomplete: {
                type: String,
                default: 'off'
            },
            autocapitalize: {
                type: String,
                default: 'off'
            },
            autocorrect: {
                type: String,
                default: 'off'
            },
            spellcheck: {
                type: String,
                default: 'false'
            }
        },
        created () {
            this.currentValue = this.value
        },
        watch: {
            value (val) {
                this.currentValue = val
            },
            currentValue (newVal) {
                if(!this.emoji){
                    try{
                        newVal = unescape(escape(newVal).replace(/\%uD.{3}|(\%u[a-zA-Z0-9]{4}[^\u0000-\u00FF])+/g, ''));
                        this.currentValue = newVal;
                    }catch(e){}
                }
                if (this.max && newVal > this.max) {
                    this.currentValue = newVal.slice(0, this.max)
                }
                this.$emit('on-change', this.currentValue)
                this.$emit('input', this.currentValue)
            }
        },
        data () {
            return {
                currentValue: ''
            }
        },
        computed: {
            count () {
                let len = 0
                if (this.currentValue) {
                    len = this.currentValue.replace(/\n/g, 'aa').length
                }
                return len > this.max ? this.max : len
            },
            textareaStyle () {
                if (this.height) {
                    return {
                        height: `${this.height}px`
                    }
                }
            }
        }
    }
</script>

<style lang="less">
  @import '../../assets/styles/components/weui/widget/weui_cell/weui_cell_global.less';
  @import '../../assets/styles/components/weui/widget/weui_cell/weui_form/weui_form_common.less';
</style>
