<template>
    <div class="wl-step" v-if="list.length > 0">
        <p class="step-title" v-if="reveal||false">{{title||"订单状态"}}：{{status=='YGB'?'订单关闭':list[sta-1]}}</p>
        <div class="p-wrap">
            <div class="p-line"></div>
            <ul :style="{marginLeft:ulWidth}">
                <li v-for="v in list" :class="{'active':$index<sta?true:false}" :style="{width:liWidth,right:list.length-1==$index?rightL:false}">
                    <i></i>
                    <p>{{v}}</p>
                </li>
            </ul>
        </div>
    </div>
    <slot></slot>
</template>

<script>
    export default{
        props:['list','status','title','reveal'],
        data(){
            return{
                w:100/(this.list.length-1),
                sta:0,
                sta_list:['DFH','DSH','YSH']
            }
        },
        computed:{
            ulWidth:function () {
                return -this.w/2+"%";
            },
            liWidth:function () {
                return this.w+"%";
            },
            rightL:function () {
                return -this.w+"%";
            }
        },
        ready(){
            for(var i=0;i<this.sta_list.length;i++){
                if(this.sta_list[i]==this.status){
                    this.sta=i+1;
                }
            }

        }
    }
</script>

<style lang="less">
    @import "../assets/styles/components/step.less";
</style>