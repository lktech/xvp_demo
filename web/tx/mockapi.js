//机器人列表 获取机器人列表
Mock.mock(basepath + "/getRobotList",'post',{
    "success": true,
    "msg": null,
    "data":{
        list:[{
            id:1,
            rkey: "001",
            wx: "151850106",
            time: 1478938859302,
            sku: "规格一",
            status: 1
        }, {
            id:2,
            rkey: "002",
            wx: "151850106",
            time: 1478935859302,
            sku: "规格二",
            status: 2
        }, {
            id:3,
            rkey: "003",
            wx: "151850106",
            time: 1478135859302,
            sku: "规格三",
            status: 3
        }],
        total:100
    }
});

//消费明细 获取消费明细列表
Mock.mock(basepath + "/getConsume",'post',{
    "success": true,
    "msg": null,
    "data":{
        list:[{
            id:1,
            name: "这里是一个名称1",
            price: "999999",
            time: 1478938859302,
            num: 798798798798
        },{
            id:2,
            name: "这里是一个名称2",
            price: "999999",
            time: 1478938859302,
            num: 798798798798
        },{
            id:3,
            name: "这里是一个名称3",
            price: "999999",
            time: 1478938859302,
            num: 798798798798
        }],
        total:100
    }
});