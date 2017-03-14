<template>
  <div style="height: 100%;">
    <loading :show="isLoading.isshow" :text="isLoading.text"></loading>
    <view-box v-ref:view-box>
      <router-view :transition="'vux-pop-' + (direction === 'forward' ? 'in' : 'out')"></router-view>
    </view-box>
  </div>
</template>

<script>
import store from '../vuex/store'

export default {
  components: {

  },
  store: store,
  vuex: {
    getters: {
      route: (state) => state.route,
      isLoading: (state) => state.isLoading,
      direction: (state) => state.direction
    }
  },
  data () {
    return {
      routerTransition: {
        forward: 'slideRL',
        back: 'slideLR'
      }
    }
  },
  methods: {
    scrollTop () {
      this.$refs.viewBox.$els.viewBoxBody.scrollTop = 0
    }
  },
  computed: {
    leftOptions () {
      return {
        showBack: this.route.path !== '/'
      }
    },
    headerTransition () {
      return this.direction === 'forward' ? 'vux-header-fade-in-right' : 'vux-header-fade-in-left'
    },
    componentName () {
      const parts = this.route.path.split('/')
      if (/component/.test(this.route.path) && parts[2]) return parts[2]
    },
    isDemo () {
      return /component|demo/.test(this.route.path)
    },
    isTabbarDemo () {
      return /tabbar/.test(this.route.path)
    },
    title () {
      if (this.route.path === '/') return 'Home'
      if (this.route.path === '/project/donate') return 'Donate'
      if (this.route.path === '/demo') return 'Demo list'
      return this.componentName ? `Demo/${this.componentName}` : 'Demo/~~'
    }
  },
  /*ready(){
    window.onscroll=function(){
        var allImg=$('*[w-src]');
        if(allImg.length){
          var Wh=$(window).height()+$(window).scrollTop();
          for(var i=0; i<allImg.length; i++){
              if(Wh>=allImg.eq(i).offset().top-50){
                allImg.eq(i).attr('src',allImg.eq(i).attr('w-src'));
              }
              
          }
        } 
    }
  },*/
  components:{
    "viewBox": require('../components/view-box.vue'),
            "loading": require('../components/loading.vue')
  }
}
require('../assets/styles/common/main.less');
require('../assets/styles/components/emoji.less');
</script>
