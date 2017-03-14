<template>
    <ul class="address-info">
        <li @click.stop="click" class="border-t">
            <div class="name">{{name}}</div>
            <div class="phone">{{phone}}</div>
            <div class="address">{{address}}</div>
            <div class="weui_cell_ft with_arrow" v-if="arrows"></div>
        </li>
    </ul>
</template>

<script>
    import utils from '../libs/utils.js';
    export default{
        data(){
            return {

            }
        },
        props:["id","name","phone","address","arrows","link"],
        methods:{
            click(){
                if(this.link){
                    utils.go(this.link, this.$router);
                }else {
                    if(this.click){
                        this.$emit("click",this.id);
                    }
                }
            }
        },
        components: {

        }
    }
</script>
<style lang="less" scoped>
    @import "../assets/styles/components/address-info.less";
</style>
