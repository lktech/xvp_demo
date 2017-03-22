<template>
    <div>
        <c-msg status="0" :msg="msg"></c-msg>
    </div>
</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                msg:''
            }
        },
        methods: {

        },
        ready(){
            this.msg='服务器'+this.$route.params.id+'错误'
        },
        components: {
            "cMsg": require("../../components/msg.vue"),
            "cButton": require('../../components/button.vue'),
            "cCell": require('../../components/cell.vue'),
        }
    }

</script>