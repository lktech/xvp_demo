<template>
    <div class="tabbar-wrap label-wrap choice">
        <div class="all">
            <div class="label-white marginb10">请选择{{keyword?keyword:'品类'}}</div>
            <c-button v-for='item in allList' :type="item.status?(type?type:'blue2'):'default'" :text="item.data_value" size="small" @click="chooseLabel($index)"></c-button>
        </div>
        <div class="footer-fixed">
            <c-button :type="color" text="下一步" :disabled="disabled" size="block" @click="chooseId"></c-button>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js'
    export default {
        props: ['type','keyword','all_url','allList'],
        data (){
            return {
                addData:{lname:''},
                show:false,
                delChoose:0,
                chooseid:[],
                name:'',
                id:'',
                color:'default',
                disabled:true
            }
        },
        methods:{
            chooseLabel:function(index){
                for(var i=0;i<this.allList.length;i++){
                    this.allList[i].status=false;
                }
                this.allList[index].status=true;
                this.name=this.allList[index].data_value;
                this.id=this.allList[index].data_key;
                this.color=this.type?this.type:'blue2';
                this.disabled=false;
            },
            chooseId:function(){
                if(!this.disabled){
                    this.$emit('on-submit',this.name,this.id);
                }      
            }
        },
        components:{
            "cButton":require('../components/button.vue'),
            "cActionSheet": require('../components/action-sheet.vue'),
            "cInput":require('../components/x-input.vue')
        },
        ready(){
            if(this.all_url){
                var all_obj=[];
                let that = this;
                utils.ajax({
                    url: basepath + that.all_url,
                    dataType: 'json',
                    type: 'POST',
                    data:JSON.stringify({'type':'PRODUCT_TYPE'}),
                    async:false,
                    success: function(data){
                        if(data.success){
                            all_obj=data.obj;
                        }else{
                            that.$vux.alert.show(data.msg);
                        }
                    }
                });
            }
            if(this.allList.length){
                for(var i=0;i<this.allList.length;i++){
                    if(this.allList[i].status){
                        this.color=this.type?this.type:'blue2';
                        this.disabled=false;
                        this.name=this.allList[i].data_value;
                        this.id=this.allList[i].data_key;
                    }
                }
            }else{
                for(var i=0;i<all_obj.length;i++){
                    all_obj[i].status=false;
                }
                this.allList=all_obj;
            }
            
        }
    }
</script>
<style lang="less">
	@import "../assets/styles/components/add-tag.less";
    .choice{
        background:#fff;
    }
</style>