<template>
    <div class="label-wrap">
        <div class="vux-1px-tb add">
            <c-button v-for='item in cList' track-by="$index" :type="type?type:'blue2'" :text="item.name" size="small" @click="labelDel($index,'choose')"></c-button>
            <div class="label0">
                <div :class="textColor" @click="addSubmit()">完成</div>
                <div>
                    <c-input :placeholder="keyword?'输入'+keyword:'输入标签'" :max="max?max:10" :value.sync="addData.v" @keyup.32="addSubmit()"></c-input>
                    <!--<c-input :placeholder="keyword?'输入'+keyword:'输入标签'" :max="max?max:10" :value.sync="addData.v"></c-input>-->
                </div>
            </div>
        </div>
        <div class="all">
            <div class="label-white marginb10">{{title?title:'所有标签'}}</div>
            <c-button v-for='item in aList' :type="item.status?(type?type:'blue2'):'default'" :text="item.name" size="small" @click="labelDel($index,'all')"></c-button>
        </div>
        <c-action-sheet :show.sync="show" :menus="menus" @on-click-menu="del" show-cancel></c-action-sheet>
        <div class="wrap-pd">
            <c-button text="确定" type="org" @click="next()" size="block"></c-button>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js'
    export default {
        props: ['type','max','urlAdd','keyword','addMax','chooseList','format','allList','title','urlDel','paramAdd','link'],
        data (){
            return {
                addData: $.extend({},{k:'add_tag',v:''},this.paramAdd),
                openData:$.extend({},{k:'open_tag',v:''},this.paramAdd),
                delData:$.extend({},{k:'close_tag',v:''},this.paramAdd),
                show:false,
                menus: {
                    delete: '<span>删除</span>'
                },
                delChoose:0,
                biaoshi:'all'
            }
        },
        computed: {
            textColor () {
                return {
                    'add-submit':true,
                    'clr-green':this.type == "primary" || this.type == "plain_primary",
                    'clr-red': this.type == "warn",
                    'clr-gray': this.type == "default",
                    'clr-org': this.type == "org",
                    'clr-blue': this.type == "blue",
                    'clr-blue2': !this.type||this.type == "blue2",
                    'plain_default': this.type == "plain_default"
                }
            },
            cList(){
                this.chooseList?this.chooseList:this.chooseList=[];
                return this.formats(this.chooseList);
            },
            aList(){
                this.allList?this.allList:this.allList=[];
                var formatAList=this.formats(this.allList);
                for(var i=0;i<formatAList.length;i++){
                    formatAList[i].status=true;
                    for(var j=0;j<this.cList.length;j++){
                        if(formatAList[i].id&&this.cList[j].id){
                            if(formatAList[i].id==this.cList[j].id){
                                formatAList[i].status=true;
                            };
                        }else{
                            if(formatAList[i].name==this.cList[j].name){
                                formatAList[i].status=true;
                            };
                        }
                    };
                };
                return formatAList;
            }
        },
        methods:{
            formats:function(list){
                var that=this;
                var formatList=[];
                if(list.length != 0){
                    var name = that.format.name;
                    var id=that.format.id;
                    list.forEach(function(item, index) {
                        var fitem = {};
                        fitem.name = item[name];
                        if(id){
                            fitem.id = item[id];
                        };
                        formatList.push(fitem);
                    });
                };
                return formatList;
            },
            addSubmit:function(status,index){
                if(!status){
                    if(this.allList.length<this.addMax){
                        if(index==0|index){
                            this.openData.v=this.aList[index].name;
                            this.addRequest(this.openData,index);
                        }else{
                            if($.trim(this.addData.v)){
                                this.addRequest(this.addData);
                            }else{
                                this.$vux.toast.show({
                                    text: '添加'+this.keyword+'不能为空！',
                                    type:'text'
                                }); 
                            };
                        };
                    }else{
                        this.$vux.toast.show({
                            text: '最多可为群添加'+this.addMax+'个'+this.keyword,
                            type:'text'
                        });
                    };
                };
            },
            del:function(val){
                if(val=='delete'){
                    var id=this.biaoshi=='all'?this.allList[this.delChoose].id:this.chooseList[this.delChoose].id;
                    var name=this.biaoshi=='all'?this.allList[this.delChoose].name:this.chooseList[this.delChoose].name;
                    this.delData.v=name;
                    if(id){
                        this.delData.lid=id;
                        for(var i=0;i<this.allList.length;i++){
                            if(this.allList[i].id==id){
                               this.allList[i].status=false;
                            };
                        };
                    }else{
                        for(var i=0;i<this.allList.length;i++){
                            if(this.allList[i].name==name){
                               this.allList[i].status=false;
                            };
                        };
                    };
                    if(this.urlDel){
                        var that=this;
                        var objDel={
                            url:basepath + this.urlDel,
                            data:JSON.stringify(this.delData),
                            success:function(data){
                                if(data.success){
                                    if(that.biaoshi=='choose'){
                                        that.chooseList.splice(that.delChoose,1);
                                    }else{
                                        that.allList.splice(that.delChoose,1);
                                    }
                                    
                                }else{
                                    that.$vux.toast.show({
                                        text: data.msg,
                                        type:'text'
                                    });
                                };
                            }
                        };
                        utils.ajax(objDel);
                    }else{
                      this.chooseList.splice(this.delChoose,1);  
                    };
                };
            },
            labelDel:function(index,biaoshi){
                this.delChoose=index;
                this.show=true;
                this.biaoshi=biaoshi;
            },
            addRequest:function(param,index){
                if(this.urlAdd){
                    var that=this;
                    utils.ajax({
                        url:basepath + that.urlAdd,
                        data:JSON.stringify(param),
                        success: function(data){
                            if(data.success){
                                if(index||index==0){
                                    that.allList[index].status=true;
                                }else{
                                    var obj={status:true,name:param.v,id:data.obj.id};
                                    that.allList.push(obj);
                                    that.addData.v='';
                                };
                            }else{
                                that.$vux.toast.show({
                                    text: data.msg,
                                    type:'text'
                                });
                            };
                        }
                    });
                }else{
                    var addLabel={'name':that.addData.v,'status':false};
                    if(that.chooseList.length<that.addMax){
                        addLabel.status=true;
                        that.chooseList.push(addLabel);
                    }else{
                        that.$vux.toast.show({
                            text: '最多可为群添加'+that.addMax+'个'+that.keyword,
                            type:'text'
                        });
                    }
                    that.allList.push(addLabel);
                };
            },
            next:function(){
                if(this.link){
                    if(this.allList.length){
                        utils.go({name:this.link,query:{category:1}},this.$router,true);
                    }else{
                        utils.go({name:this.link},this.$router,true);
                    }
                }
            }
        },
        components:{
            "cButton":require('../components/button.vue'),
            "cActionSheet": require('../components/action-sheet.vue'),
            "cInput":require('../components/x-input.vue')
        },
        ready(){
            if(!this.addMax){
                this.addMax=10;
            };
            if(!this.format){
                this.format={'name':'name','id':'id'};
            };
            if(!this.keyword){
                this.keyword='标签';
            };
        }
    }
</script>
<style lang="less">
	@import "../assets/styles/components/add-tag.less";
</style>