<template>
<div>
    <c-top-back></c-top-back>
    <c-group>
        <p style="width:95%; line-height:30px; text-indent: 28px; margin:0 auto; font-size:14px;">
            
        </p>
    </c-group>
</div>
</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return { 

            }
        },
        ready(){
            utils.loadingHide();
            utils.MenuShare();
        },
        methods:{
            
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cTopBack":require('../../components/top-back.vue')
        }
    }
</script>

