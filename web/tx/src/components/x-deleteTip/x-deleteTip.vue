<template>
  <!-- 删除 -->
  <span class="msg-more" id="msg-more">
		<ul>
      <li @click='deleteItem'>删除</li>
    </ul>
	</span>
  <!-- 删除 -->
</template>

<script>
  export default {
    props: {

    },
    mounted() {
      this.$nextTick(() => {

      })
    },
    created() {},
    data() {
      return {

      }
    },
    computed: {

    },
    watch: {},
    methods: {

    },
    components: {

    }
  }
</script>

<style lang="less">
  /* 删除 */
  
  .msg-more {
    position: absolute;
    display: none;
    width: 50px;
    padding: 3px;
    left: 0;
    background: #000;
    color: #fff;
    border-radius: 4px;
    z-index: 22;
  }
  
  .msg-more:after {
    width: 0;
    height: 0;
    position: absolute;
    left: 18px;
    top: 30px;
    content: "";
    border: 6px solid transparent;
    border-top-color: #000
  }
  
  .msg-more ul li {
    float: left;
    width: 46px;
    border-right: 1px solid #fff;
    text-align: center
  }
  
  .msg-more ul li:last-child {
    border-right-width: 0
  }
</style>