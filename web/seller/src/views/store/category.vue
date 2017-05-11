<template>
<div>
    <c-top-back></c-top-back>
    <c-add-tag type="primary" :url-add="url_add" link="storeSetting" title="所有类目" :url-del="url_del" keyword="发票类目" :all-list="chooseList"></c-add-tag>
</div>

</template>
<script>
    import utils from '../../libs/utils.js';
    export default {
        data (){
            return {
                chooseList:[],
                url_add:"/mall/invoice_category/add",
                url_del:"/mall/invoice_category/delete",

            }
        },

        methods:{
        },  
        ready(){
            
            let that = this;
            utils.ajax({
                url: "/mall/invoice_category/list",
                dataType: 'json',
                type: 'POST',
                success: function(data){
                    if(data.success){
                        that.chooseList=data.obj.list;
                    }
                }
            });
        },
        components:{
            "cGroup":require('../../components/group.vue'),
            "cAddTag":require('../../components/add-tag-xvp.vue'),
            "cTopBack":require('../../components/top-back.vue')
        }
    }
</script>

