

//商品详情 页面初始化
Mock.mock(basepath + "/product/detail",'post',{
    "success": true,
    "msg": null,
    "obj":{
        "goods":{
            id:1,
            type: "PRODUCT",
            title: '小米V领短袖T恤 男款小米V领短袖T恤 男款小米V领短袖T恤 男款',
            price: 8880,
            hideOldPrice:false,
            oldPrice: 10000,
            minPrice: 6660,
            maxPrice: 12000,
            pic: '//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg',
            postageType:1,
            postage:1200,
            stock:11
        },
        "storeInfo":{
            "id":1,
            "name":"吴亮的店铺",
            "logo":"//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg"
        },
        content:{
            "title":"应季水果",
            "detail":'<img src="//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg"><h3>这个是商品的介绍</h3><p>无论走到哪里，都应该记住，过去都是假的，回忆是一条没有尽头的路，一切以往的春天都不复存在，就连那最坚韧而又狂乱的爱情归根结底也不过是一种转瞬即逝的现实。</p><img src="//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg"><h3>这个是商品的介绍</h3><p>无论走到哪里，都应该记住，过去都是假的，回忆是一条没有尽头的路，一切以往的春天都不复存在，就连那最坚韧而又狂乱的爱情归根结底也不过是一种转瞬即逝的现实。</p>'
        },
        sku:[{"id":1,"name":"红色 XLL"},{"id":2,"name":"黑色 XL"},{"id":3,"name":"灰色 L"}]

    }
});


//商品详情 sku信息
Mock.mock(basepath + "/product/sku",'post',{
    "success": true,
    "msg": null,
    "obj":{
        "price":888,
        "stock":5
    }
});

//商品详情 购买
Mock.mock(basepath + "/product/buy",'post',{
    "success": true,
    "msg": null,
    "obj":{
        "id":111
    }
});

//商品详情 加入购物车
Mock.mock(basepath + "/product/setcart",'post',{
    "success": true,
    "msg": null,
    "obj":{
        "id":111
    }
});