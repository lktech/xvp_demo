<template>
    <div>
        <ul class="tabBar">
            <li @click="courseClick" :class="{action:action==0}">听一听</li>
            <li @click="askClick" :class="{action:action==1}">问一问</li>
            <li @click="myAskClick" :class="{action:action==2}">我的</li>
        </ul>
    </div>
</template>

<script>
    import utils from '../../libs/utils.js';
    export default{
        props: ["action"],
        data(){
            return {}
        },
        mounted: function () {
            this.$nextTick(function () {

            })
        },
        methods: {
            //课程
            courseClick(){
                if (this.action != 0) {
                    utils.go("/courseList", this.$router);
                }
            },
            //问答
            askClick(){
                if (this.action != 1) {
                    utils.go("/askAnswer", this.$router);
                    //location.href = "/mphbrobot/?#/askAnswer";
                }
            },
            //我的
            myAskClick(){
                if (this.action != 2) {
                    if (sessionStorage.isLecturer) {
                        if(sessionStorage.isLecturer == "true"){
                            //讲师
                            utils.go("/allquestions", this.$router);
                        }else{
                            utils.go("/myAsk", this.$router);
                        }
                    } else {
                        this.$vux.toast.show({
                            text: "角色标识丢失",
                            type: "text",
                        });
                    }
                }
            }

        },
        computed: {},
        components: {
            //"elDatePicker": require("../../components/date-picker/date-picker.vue"),
        }
    }
</script>
