<template>
  <div>
    <li>
        <div class="orderHeader border-b">
            <b>订单：{{num}}</b>
            <span :class="{'od-over':over}">{{status}}</span>
        </div>
        <ul class="goodsOrder">
            <li class="border-b" v-for="goods in formatList" @click="goodsclick(goods.id)">
                <div class="goodsImg">
                    <img :src="goods.img"/>
                </div>
                <div class="goodsDesc">
                    <div class="goodsName">{{goods.title}}</div>
                    <div class="sku">规格：{{goods.sku}}</div>
                    <div class="num">
                        <span v-if="formatPrice">￥{{goods.price | formatPrice}}</span>
                        <span v-else>￥{{goods.price}}</span>
                        x{{goods.num}}
                    </div>
                </div>
            </li>
        </ul>
        <div class="orderMoney">
            <b>实付金额</b>
            <span v-if="formatPrice">￥{{price | formatPrice}}</span>
            <span v-else>￥{{price}}</span>
        </div>
        <div class="orderBtn border-t">
            <slot></slot>
        </div>
    </li>
	</div>
</template>

<script>
export default {
	props: ["num", "status","over", "price","formatPrice", "list","format"],
	created () {
     this.formatList = this.formatFunc(this.list);
    },
  data () {
    return {
        formatList:[]
    }
  },
  watch: {
      list(newList) {
          this.formatList = this.formatFunc(newList);
      }
  },
  methods: {
      formatFunc(list) {
          let that = this,
              flist = [];
          if(!that.format) {
              return list;
          } else {
              list.forEach(function(item) {
                  let id = that.format.id,
                      img = that.format.img,
                      title = that.format.title,
                      sku = that.format.sku,
                      price = that.format.price,
                      num = that.format.num

                  let fitem = {};
                  fitem.id = item[id],
                  fitem.img = item[img],
                  fitem.title = item[title],
                  fitem.sku = item[sku],
                  fitem.price = item[price],
                  fitem.num = item[num]
                  flist.push(fitem);
              });
              return flist;
          }
      },
      goodsclick(id){
          this.$emit("handle", id);
      }
  }
}
</script>
