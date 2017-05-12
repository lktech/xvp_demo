Mock.mock(basepath + "/seller/account/getAccountAmount", "post", {
    code: "SUCCESS",
    result: {
        withdrawals_amount: 10000,
        today_income_amount: 100000,
        income_amount: 100000000000
    }
});

Mock.mock(basepath + "/seller/seller/verify", "post", {code:"SUCCESS",result:{sn:1}});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {
        "account_type": "1",
        "company_name": "融数金服",
        "buslince": "110105019962316",
        "buslince_pic": "{\"buslince_pic\": \"http://img4.xiaovpu.com/71.png\"}",
        "user_name": "张三",
        "certificate_type": "0",
        "certificate_number": "220102198706019690",
        "createTime": "2017-02-09 12:55:52"
    }
});
Mock.mock(basepath + "/seller/account/getBankInfo", "post", {
    code: "SUCCESS",
    result:
        [
            {
                "bankcode": "123456789",
                "bankname": "招商银行"
            }
        ]
});
Mock.mock(basepath + "/seller/account/getBankCity", "post", {
    code: "SUCCESS",
    result:
        [
            {
                code:"1000",
                name:"北京市"
            },
            {
                "code": "220100",
                "name": "石家庄市"
            },
            {
                "code": "220200",
                "name": "廊坊市"
            }
        ]

});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: [
        {id:1,name:"北京市"}
    ]
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});
Mock.mock(basepath + "/1", "post", {
    code: "SUCCESS",
    result: {}
});