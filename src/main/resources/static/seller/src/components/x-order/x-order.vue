<template>
  <div>
		<ul class="orderWrap">
	    <li v-for="item in formatList">
        <div class="orderHeader">
            <b>订单：{{item.orderNum}}</b>
            <span>{{item.orderStatus | orderStatus}}</span>
        </div>
        <ul class="goodsOrder">
            <li v-for="goods in item.goods" @click="goodsclick(goods.id)">
                <div class="goodsImg">
                    <img :src="goods.goodsUrl"/>
                </div>
                <div class="goodsDesc">
                    <div class="goodsName">{{goods.goodsName}}</div>
                    <div class="sku">规格：{{goods.sku}}</div>
                    <div class="num">￥{{goods.price | formatPrice}} <span>x{{goods.num}}</span></div>
                </div>
            </li>
        </ul>
        <div class="orderMoney" v-if="item.orderType=='xuni'">
            <b>实付积分</b>
            <span>{{item.score}}积分</span>
        </div>
        <div class="orderMoney" v-else>
            <b>实付金额</b>
            <span>￥{{item.pay | formatPrice}}</span>
        </div>
        <div class="orderBtn">
            <r-button :type="type" @click.native="fukuan(item.orderNum)" v-if="item.orderStatus==orderStatus.dfk"
                      :size="size" text="付款"></r-button>
            <r-button :type="type" @click.native="fahuo(item.orderNum)" v-if="item.orderStatus==orderStatus.dfh"
                      :size="size" text="发货"></r-button>
            <r-button :type="type" @click.native="cxgm(item.orderNum)" v-if="item.orderStatus==orderStatus.close"
                      :size="size" text="重新购买"></r-button>
            <r-button :type="type" @click.native="qrsh(item.orderNum)" v-if="item.orderStatus==orderStatus.dsh"
                      :size="size" text="确认收货"></r-button>
            <r-button :type="item.orderType=='xuni'?'primary':'default'" @click.native="look(item.orderNum)"
                      :size="size" text="查看详情"></r-button>
            <r-button type="default" @click.native="del(item.orderNum,$event)" v-if="item.orderStatus==orderStatus.dfk"
                      :size="size" text="删除订单"
                      :disabled="item.orderStatus==orderStatus.dfk"></r-button>
        </div>
	    </li>
		</ul>
	</div>
</template>

<script>
export default {
	props: ["orderData", "orderStatus", "format", "type","size"],
	mounted () {
    this.$nextTick(() => {
    	
    })
  },
  created () {
  },
  data () {
    return {
      
    }
  },
  computed: {
    formatList(){
	    if (this.format) {
	        return this.formats(this.orderData);
	    } else {
	        return this.orderData;
	    }
    }
  },
  watch: {
  	
  },
  methods: {
    fukuan: function (orderNum) {
        this.$emit("fukuan", orderNum);
    },
    fahuo: function (orderNum) {
        this.$emit("fahuo", orderNum);
    },
    del: function (orderNum, e) {
        if (e.target.getAttribute("disabled") == null) { //没有disabled属性
            this.$emit("del", orderNum);
        }
    },
    look: function (id) {
        this.$emit("look", id);
    }
    ,
    goodsclick: function (id) {
        this.$emit("goto", id);
    }
    ,
    qrsh: function (id) {
        this.$emit("qrsh", id);
    },
    formats:function(data){
        console.log(data)
    }
  },
  components: {
		"rButton": require('../button/button.vue'),
	}
}
</script>

<style lang="less">
@import "../../assets/styles/components/rui/order.less";
</style>
