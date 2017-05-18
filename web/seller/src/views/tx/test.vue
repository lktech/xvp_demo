<template>
    <div>
        <r-button type="primary" text="下一步" @click.native="btnClick"></r-button>
        <!--<div v-html="provinceValue.name"></div>-->

        <r-group title="标题">
            <r-radio :options="radioData" :value="defaultValue" @on-change="change"></r-radio>
        </r-group>
        <r-group title="default">
            <r-radio :options="radio001" @on-change="change"></r-radio>
        </r-group>
        <r-button type="primary" text="清空" @click.native="clearClick"></r-button>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        data(){
            return {

                showProvince: false,
                radioData: [{
                    key: '001',
                    value: '北京市'
                }, {
                    key: '002',
                    value: '天津市'
                }],
                defaultValue:{},
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
                            that.defaultValue={key:"002",value:"天津市"};

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
            change(obj){
                console.log(obj)
            },
            clearClick(){
                this.defaultValue={};
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
            "rRadio": require("../../components/radio/radio.vue"),
        }
    }
    //require("../../assets/styles/views/batchExport.less");
</script>
<style lang="less">
    @import "../../assets/styles/views/personal";
</style>
