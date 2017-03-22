<template>
  <div class="tabbar-wrap label-wrap add-tag">
        <div class="vux-1px-tb add">
            <r-button v-for='(item,index) in cList' :type="type?type:'primary'" :text="item.name" size="small" @click.native="labelDel(index)"></r-button>
            <div class="label0">
                <div :class="textColor" @click="addSubmit">完成</div>
                <div>
                    <r-input :placeholder="keyword?'输入'+keyword:'输入标签'" :max="max?max:10" v-model="addData.lname"></r-input>
                </div>
            </div>
        </div>
        <div class="all">
            <div class="label-white marginb10">所有{{keyword?keyword:'标签'}}</div>
            <r-button v-for='(item,index) in aList' :type="item.status?(type?type:'primary'):'default'" :text="item.name" size="small" @click.native="chooseLabel(item.status,index)"></r-button>
        </div>
        <div class="footer-fixed">
            <r-button :type="type?type:'primary'" text="提交" size="block" @click.native="chooseId"></r-button>
        </div>
        <r-action-sheet v-model="show" :menus="menus" @on-click-menu="del" show-cancel></r-action-sheet>
  </div>
</template>

<script>
import utils from '../../libs/utils.js';
export default {
  props: ['type','max','urlAdd','keyword','addMax','chooseList','format','allList'],
	mounted () {
    this.$nextTick(() => {
    	if(!this.addMax){
	      this.addMax=10;
	    };
	    if(!this.keyword){
	      this.keyword='标签';
	    };
    })
  },
  created () {
  },
  data () {
    return {
	    addData:{lname:''},
	    show:false,
	    menus: {
	        delete: '<span>删除</span>'
	    },
	    delChoose:0,
	    chooseid:[]
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
        if(this.format){
            return this.formats(this.chooseList);
        }else{
            return this.chooseList;
        }
    },
    aList(){
        this.allList?this.allList:this.allList=[];
        var formatAList;
        if(this.format){
            formatAList=this.formats(this.allList);
        }else{
            formatAList=this.allList;
        }
        for(var i=0;i<formatAList.length;i++){
            formatAList[i].status=false;
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
  watch: {
  },
  methods: {
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
    addSubmit:function(){
        var that=this;
        if(that.addData.lname){
            if(that.urlAdd){
                utils.ajax({
                    url:basepath + that.urlAdd,
                    dataType:'json',
                    type:'POST',
                    data:that.addData,
                    success: function(data){
                        if(data.success){
                            if(that.chooseList.length<that.addMax){
                                data.obj.status=true;
                                that.chooseList.push(data.obj);
                            }else{
                                data.obj.status=false;
                                that.$vux.toast.show({
                                    text: '最多可为群添加'+that.addMax+'个'+that.keyword,
                                    type:'text'
                                });
                            }
                            that.allList.push(data.obj);
                        }else{
                            that.$vux.toast.show({
                                text: data.msg,
                                type:'text'
                            });
                        };
                    }
                });
            }else{
                var addLabel={'name':that.addData.lname,'status':false};
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
            } 
        }else{
            that.$vux.toast.show({
                text: '添加'+that.keyword+'不能为空！',
                type:'text'
            }); 
        }
    },
    chooseLabel:function(status,index){
        if(!status){
            if(this.chooseList.length<this.addMax){
                this.chooseList.push(this.allList[index]);
                this.allList[index].status=true;
            }else{
                this.$vux.toast.show({
                    text: '最多可为群添加'+this.addMax+'个'+this.keyword,
                    type:'text'
                });
            }
        };
    },
    del:function(val){
        if(val=='delete'){
            var id=this.chooseList[this.delChoose].id;
            if(id){
                for(var i=0;i<this.allList.length;i++){
                    if(this.allList[i].id==id){
                       this.allList[i].status=false;
                    };
                };
            }else{
                var name=this.chooseList[this.delChoose].name;
                for(var i=0;i<this.allList.length;i++){
                    if(this.allList[i].name==name){
                       this.allList[i].status=false;
                    };
                };
            };
            this.chooseList.splice(this.delChoose,1);
        };
    },
    labelDel:function(index){
        this.delChoose=index;
        this.show=true;
    },
    chooseId:function(){
        this.chooseid=[];
        for(var i=0;i<this.chooseList.length;i++){
            if(this.chooseList[i].id){
                this.chooseid.push(this.chooseList[i].id);
            }else{
                this.chooseid.push(this.chooseList[i].name);
            };
        };
        this.$emit('on-submit',this.chooseid);
    }
  },
  components: {
	  "rButton":require('../button/button.vue'),
    "rActionSheet": require('../actionsheet/actionsheet.vue'),
    "rInput":require('../input/input.vue')
	
	}
}
</script>

<style lang="less">
@import '../../assets/styles/components/rui/add-tag.less';
</style>
