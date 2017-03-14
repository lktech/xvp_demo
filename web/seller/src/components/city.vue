<template>
    <popup-picker :fixed-columns="hideDistrict ? 2 : 0" :columns="3" :data="list" :title="title" :value.sync="value" show-name :inline-desc="inlineDesc" :placeholder="placeholder" @on-hide="emitHide" @on-show="$emit('on-show')"></popup-picker>
</template>

<script>
    import name2value from '../libs/filters/name2value'
    import value2name from '../libs/filters/value2name'
    import PopupPicker from './popup-picker.vue'
    import AddressChinaData from '../libs/list.json'

    export default {
        components: {
            PopupPicker
        },
        data(){
            return{
                list:AddressChinaData
            }
        },
        props: {
            title: {
                type: String,
                required: true
            },
            value: {
                type: Array,
                default () {
                    return []
                }
            },
            rawValue: Boolean,
            inlineDesc: String,
            placeholder: String,
            hideDistrict: Boolean
        },
        beforeCompile () {
            if (this.value.length && this.rawValue) {
                const parsedVal = name2value(this.value, this.list)
                if (/__/.test(parsedVal)) {
                    console.error('Vux: Wrong address value', this.value)
                    this.value = []
                } else {
                    this.value = parsedVal.split(' ')
                }
            }
        },
        methods: {
            getName (value) {
                return value2name(value, AddressChinaData)
            },
            emitHide (val) {
                if(val){
                    var obj={code:this.value,name:this.getName(this.value)};
                    this.$emit('on-hide', obj)
                }
            }
        }
    }
</script>
