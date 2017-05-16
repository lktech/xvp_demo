<template>
<div>
    
</div>

</template>
<script>
    export default {
        data (){
            return {
                
            }
        },

        methods:{
            demo(){
                let that = this;
                $.ajax({
                    url: "/app/sendRegMsg",
                    dataType: 'json',
                    type: 'POST',
                    data:{
                        phone:that.formData.phone
                    },
                    success: function(data){
                        if(!data.success){
                            that.$vux.toast.show({
                                text:data.msg,
                                type:'text'
                            });

                        }
                    }
                });
            }
            
        },
        ready(){
            utils.MenuShare();
        },
        components:{
            "cGroup":require('../../components/group.vue')
        }
    }
</script>

