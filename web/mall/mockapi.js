

//商品详情 页面初始化
Mock.mock(basepath + "/mall/product/detail",'post',{
    "success": true,
    "msg": null,
    "obj":{
        "product":{
            id:1,
            type: "PRODUCT",
            title: '小米V领短袖T恤 男款小米V领短袖T恤 男款小米V领短袖T恤 男款',
            price: 8880,
            hideOldPrice:false,
            oldPrice: 10000,
            minPrice: 6660,
            maxPrice: 12000,
            pic: '32.jpg',
            postageType:1,
            postage:1200,
            stock:11
        },
        "storeInfo":{
            "id":1,
            "name":"吴亮的店铺",
            "logo":"c32.jpg"
        },
        content:{
            "title":"应季水果",
            "value":'<img src="//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg"><h3>这个是商品的介绍</h3><p>无论走到哪里，都应该记住，过去都是假的，回忆是一条没有尽头的路，一切以往的春天都不复存在，就连那最坚韧而又狂乱的爱情归根结底也不过是一种转瞬即逝的现实。</p><img src="//img13.360buyimg.com/da/jfs/t3643/10/649102478/43997/d9929403/58107467Nae4e4c32.jpg"><h3>这个是商品的介绍</h3><p>无论走到哪里，都应该记住，过去都是假的，回忆是一条没有尽头的路，一切以往的春天都不复存在，就连那最坚韧而又狂乱的爱情归根结底也不过是一种转瞬即逝的现实。</p>'
        },
        sku:{

            "goods":{

                "id":11,//商品id，int，必填

                "type":"PRODUCT",//商品类型，string，必填

            "pic":"http://www.baidu.com/img/demo.png",//商品图片，string，必填

                    "title":"小米5Plus 白色",//商品名称，string，必填

            "price":9999,//商品价格，int，必填

            "minPrice":9999,//商品最低价格，int，选填

            "maxPrice":9999,//商品最高价格，int，选填

            "oldPrice":9999,//商品原价，int，必填

            "stock":111, //库存，init,选填

            "stock":111, //库存，init,选填

            "limit":{//限购信息

                "limitNum":5,//限制购买的数量,int,必填

                "buyNum":5//已购买的数量,int,必填
                }

            },

            "sku":[

                {

            "id":123,  //sku的id,int,必填

            "name":"红色 XLL"  //sku名称，string,必填

            }

            ]

            }

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