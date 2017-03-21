//机器人列表 获取机器人列表
Mock.mock(basepath + "/seller/product/query",'post',{
    "code": 'SUCESS',
    "msg": null,
    "result":[{
        'id':'1',
        'name':'sdsadsa',
        'pics':'',
        'product_detail':'sadasdsad',
        'logistics_fee':'1',
        'stock':'12',
        'price':'3'
    },{
        'id':'1',
        'name':'sdsadsa',
        'pics':'',
        'product_detail':'sadasdsad',
        'logistics_fee':'1',
        'stock':'12',
        'price':'3'
    },{
        'id':'1',
        'name':'sdsadsa',
        'pics':'',
        'product_detail':'sadasdsad',
        'logistics_fee':'1',
        'stock':'12',
        'price':'3'
    }]
});

Mock.mock(basepath + "/seller/order/query",'post',{
    "code": 'SUCESS',
    "msg": null,
    // "result":[{
    //         'order_id':'1',
    //         'pay_type':'微信支付',
    //         'items_total_fee':'32',

    //         'seller_discount_fee':'2',
    //         'logistic_fee':'10',
    //         'pay_amount':  '40'
    //         'user_remark':'用户的备注',
    //         'seller_remark':'卖家的备注',
    //         'order_status':,'DFK'          //DFK：待付款 DFH：待发货 DSH：待收货 YSH： 已收货,
    //         'address':'物流配送地址',
    //         'logistic_user':'收货人',
    //         'logistic_phone':'收货人联系电话',
    //         'create_time':'',                     //创建时间      
    //         'pay_time':'',                       //支付时间,
    //         'confirm_receive_time':'最后确认收货时间',
    //         'xvporderitems':[{
    //             'id':'111',
    //             'product_id':'111',
    //             'sku_id':'1111',
    //             'product_name':'商品',
    //             'price':'3',
    //             'sku_str':'L',
    //             'item_count':'3',
    //             'delivery_id':'4',
    //             'create_time':'1'
    //         }],        
    //         'orderdeliverys':[{
    //             'id':'value',
    //             'logistics_company_code':'快递公司编码',
    //             'logistics_company_name':'快递公司名称',
    //             'logistics_num':  '运单号',
    //             'need_logistics_flg':'是否需要物流', 0 不要物流 1 需要物流,
    //              'create_time':'value'
    //          }]                
    //     }]
    'result':[{
        orderNum: "2394847HGGR",
        orderStatus: "DFH",
        pay: 3000,
        goods: [
            {
                id: "123",
                goodsName: "26度果园 芒果攀枝花果树熟芒果8kg芒果攀枝花果树熟芒果8kg",
                goodsUrl: "http://tupian.enterdesk.com/2012/1115/gha/3/enterdesk%20%289%29.jpg",
                sku: "3kg",
                price: 1000,
                num: 3
            },
            {
                id: "456",
                goodsName: "26度果园 芒果攀枝花果树熟芒果8kg芒果攀枝花果树熟芒果8kg",
                goodsUrl: "http://tupian.enterdesk.com/2012/1115/gha/3/enterdesk%20%289%29.jpg",
                sku: "2kg",
                price: 1000,
                num: 3
            }, {
                id: "789",
                goodsName: "芒果攀枝花果树熟芒果8kg芒果攀枝花果树熟芒果8kg",
                goodsUrl: "http://tupian.enterdesk.com/2012/1115/gha/3/enterdesk%20%289%29.jpg",
                sku: "无",
                price: 2000,
                num: 2
            }
        ],
        'orderdeliverys':[{
            'id':'value',
            'logistics_company_code':'快递公司编码',
            'logistics_company_name':'快递公司名称',
            'logistics_num':  '运单号',
            'need_logistics_flg':'是否需要物流', //0 不要物流 1 需要物流,
             'create_time':'value'
         }] 
    }]
});


Mock.mock(basepath + "/seller/order/get",'post',{
    "code": 'SUCESS',
    "msg": null,
    "result":{
        'order_id':'1111',
        'pay_type':'value',
        'items_total_fee':'订单去除运费和优惠的商品金额',
        'seller_discount_fee':'1',
        'logistic_fee':'1',
        'pay_amount':  '1',
        'user_remark':'用户的备注',
        'seller_remark':'卖家的备注',
        'order_status':'DFH', //DFK：待付款 DFH：待发货 DSH：待收货 YSH： 已收货
        'address':'物流配送地址物流配送地址物流配送地址物流配送地址物流配送地址',
        'address_name':'收货人',
        'address_phone':'收货人联系电话',
        'create_time':'创建时间',
        'pay_time':'支付时间',
        'confirm_receive_time':'最后确认收货时间',
        'xvporderitems':[{
            'id':'value',
            'product_id':'value',
            'sku_id':'value',
            'product_name':'sadsadas',
            'price':'1',
            'sku_str':'value',
            'item_count':'1',
            'delivery_id':'物流ID',
            'create_time':'vlue',
            'goodsUrl':'http://tupian.enterdesk.com/2012/1115/gha/3/enterdesk%20%289%29.jpg'
        }],                 
        'orderdeliverys':[{
            'id':'value',
            'logistics_company_code':'快递公司编码',
            'logistics_company_name':'快递公司名称',
            'logistics_num':  '运单号',
            'need_logistics_flg':'是否需要物流',    //0 不要物流 1 需要物流,
             'create_time':'value'
        }]                
    }
});
Mock.mock(basepath + "/seller/order/delivery",'post',{
    "code": 'SUCESS',
    
});

Mock.mock(basepath + "/seller/seller/login",'post',{
    "code": 'SUCESS',
    
});

