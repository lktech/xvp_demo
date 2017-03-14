<template>
    <div :class="hasBtn?'tabbar-wrap msgbub-wrap':'msgbub-wrap'">
        <div>
            <div v-for='item in formatBubList'>
                <div class="text-center">
                    <span class="clr-white wrap-pd time">{{item.time | fmtDate}}</span>
                </div>
                <div class="wrap-pdt of">
                    <div class="head">
                        <div><img :src="item.headUrl" width="44" height="44"></div>
                        <div class="label3-small text-center margint8">{{{item.userName?item.userName:item.nike | emoji}}}</div>
                    </div>
                    <div class="bub">
                        <div class="arrow"></div>
                        <div class="label6-small bub-con" v-if="item.type==1">{{{item.msg | emoji}}}</div>
                        <div class="label6-small bub-con" v-if="item.type==34" @click="audioPlay(item.file)"><img src="../assets/images/msgbub-audio.png" width="22" height="22"></div>
                        <div class="label6-small bub-con" v-if="item.type==62" @click="videoPlay(item.file)"><img src="../assets/images/msgbub-video.png" width="44" height="44"></div>
                        <div class="label6-small bub-con" v-if="item.type==3" @click="imgPlay(item.file)"><img :src="item.file" width="44" height="44"></div>
                    </div>
                </div>
                <div class="label9-small text-right wrap-pd2 txt-ofl">{{item.group}}</div>
            </div>
        </div>
        <div>
            <audio :src="audioUrl" autoplay>
                您的浏览器不支持 audio 标签。
            </audio>
        </div>
        <div>
            <video :src="videoUrl" class="video" autoplay>
            您的浏览器不支持 video 标签。
            </video>
        </div>
        <div class="imgWrap" @click="imgHide">
            <div class="mask"></div>
            <div class="imgCon"><img :src="imgUrl"></div>
        </div>
        <div class="footer-fixed" v-if="hasBtn">
            <c-button :type="type?type:'blue2'" :text="btnTxt?btnTxt:'确认'" size="block" noradius="false" @click="$emit('on-certain')"></c-button>
        </div>
    </div>
</template>

<script>
    import utils from '../libs/utils.js';
    export default {
        props: ['type','bubList','format','btnTxt','hasBtn'],
        data (){
            return {
                audioUrl:'',
                videoUrl:'',
                imgUrl:''
            }
        },
        computed: {
            formatBubList(){
				if(this.format){
                    return this.formats(this.bubList);
                }else{
                    return this.bubList;
                };
            }
        },
        methods:{
            formats:function(list){
                var that=this;
                var formatList=[];
                if(list.length != 0){
                    var type = that.format.type;
                    var time=that.format.time;
                    var headUrl=that.format.headUrl;
                    var nike=that.format.nike;
                    var msg=that.format.msg;
                    var file=that.format.file;
                    var userName=that.format.userName;
                    var group=that.format.group;
                    list.forEach(function(item, index) {
                        var fitem = {};
                        fitem.type = item[type];
                        fitem.time = item[time];
                        fitem.headUrl = item[headUrl];
                        fitem.nike = item[nike];
                        fitem.msg = item[msg];
                        fitem.file = item[file];
                        fitem.userName=item[userName];
                        fitem.group=item[group];
                        formatList.push(fitem);
                    });
                };
                return formatList;
            },
            audioPlay:function(source){
                this.audioUrl=source;
                $('audio')[0].play();
            },
			videoPlay:function(source){
				this.videoUrl=source;
				$('video')[0].play();
				$('video').show();
				$('video')[0].onended=function(){
				  $('video').hide();  
				}
			},
            imgPlay:function(source){
              this.imgUrl=source;
              $('.imgCon img').load(function(){
                $('.imgCon').css('margin-top',-$('.imgCon img').height()/2+'px');
              }); 
              $('.imgWrap').show();
            },
            imgHide:function(){
                $('.imgWrap').hide();
            }
        },
        components:{
            "cButton":require('../components/button.vue'),
            "cScrollLoad":require('../components/scroll-load.vue')
        }
    }
</script>
<style lang="less">
	@import "../assets/styles/components/msg-bubble.less";
</style>