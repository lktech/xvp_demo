<template>
    <div>
        <r-button type="primary" text="下一步" @click.native="btnClick"></r-button>
        <div v-html="provinceValue.name"></div>
        <!--省-->
        <r-popup v-model="showProvince" :hide-on-blur="false">
            <div class="popupArea">
                <h3>请选择省 <a href="javascript:;" class="close" @click="showProvince=false">关闭</a></h3>
                <div class="areaContent" :style="{maxHeight:maxH,overflow: 'auto'}">
                    <r-group>
                        <r-radio :options="province" v-model="provinceValue" :defaultValue="defaultProvince"
                                 @on-change="changeProvince"></r-radio>
                    </r-group>
                </div>
            </div>
        </r-popup>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        data(){
            return {

                showProvince: false,
                province: [],
                provinceValue: {},
                defaultProvince: {}
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                let that = this;
                //省列表
                utils.ajax({
                    url: "/seller/account/getBankCity",
                    success: function (res) {
                        if (res.code == "SUCCESS") {
                            that.province = [];
                            res.result.forEach(function (obj, i) {
                                that.province.push({id: obj.code, name: obj.name});
                            })
                        } else {
                            that.$vux.alert.show(res.message);
                        }
                    }
                });
            })
        },
        methods: {
            btnClick(){
                this.showProvince = true;
            },
            changeProvince(obj){
                console.log(obj)
            }
        },
        computed: {
            maxH(){
                return $(window).height() * 0.5 + "px";
            }
        },
        components: {
            "rGroup": require("../../components/group/group.vue"),
            "rInput": require("../../components/input/input.vue"),
            "rButton": require("../../components/button/button.vue"),
            "rPopup": require("../../components/popup/popup.vue"),
            "rRadio": require("../../components/radio/x-radio.vue"),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../../assets/styles/views/personal";
</style>
