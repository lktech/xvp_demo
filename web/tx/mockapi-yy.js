//消息列表
Mock.mock(basepath + "/wx/course/getCourserListAndChapter", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    "wxmpId": 3,
    "course_id": 1,
    "course_name": '啦啦啦啦',
    "status": 0,
    "chapterList": [{
      "chapter_id": '1',
      "chapter_section": "第一张",
      "chapter_title": "这是第一章的描述",
      "list": [{
        "msgType": 'voice', // 声音
        "wxServerId": "asdasda122",
        "wxLocalId": "asdasd",
        "duration": 2016,
        "sourceFilepath": null,
        "id": null
      }, {
        "msgType": 'voice', // 声音
        "wxServerId": "asdasda122",
        "wxLocalId": "asdasd",
        "duration": 201688,
        "sourceFilepath": null,
        "id": null
      }]
    }, {
      "chapter_section": "第2张",
      "chapter_title": "这是第一章的描述",
      "list": []
    }, {
      "chapter_section": "第一张",
      "chapter_title": "这是第一章的描述",
      "list": [{
        "msgType": '2', // 图片
        "wxServerId": "asdasda122",
        "wxLocalId": "asdasd",
        "duration": 2016,
        "sourceFilepath": "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
        "id": null
      }, {
        "msgType": '2', // 图片
        "wxServerId": "asdasda122",
        "wxLocalId": "asdasd",
        "duration": 2016,
        "sourceFilepath": "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg",
        "id": null
      }]
    }]
  }
});

//消息列表
Mock.mock(basepath + "/wx/course/replyAskByAskId", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    'head_pic': '',
    "course_id": '27',
    "is_answer": false,
    "create_time": "2017-03-15 20:27:29",
    "list": [],
    "is_completed": false,
    "update_time": "2017-03-15 20:27:29",
    "ask": "黄疸退了还会反复吗？",
    "id": 1010,
    "answerList": [{
      "msgType": 'voice', // 声音
      "wxServerId": "asdasda122",
      "wxLocalId": "asdasd",
      "duration": 2016,
      "sourceFilepath": null,
      "id": null
    }, {
      "msgType": 'voice', // 声音
      "wxServerId": "asdasda122",
      "wxLocalId": "asdasd",
      "duration": 2016,
      "sourceFilepath": null,
      "id": null
    }, {
      "msgType": 'voice', // 声音
      "wxServerId": "asdasda122",
      "wxLocalId": "asdasd",
      "duration": 2016,
      "sourceFilepath": null,
      "id": null
    }, {
      "msgType": 'voice', // 声音
      "wxServerId": "asdasda122",
      "wxLocalId": "asdasd",
      "duration": 2016,
      "sourceFilepath": null,
      "id": null
    }]
  }
});

// 上传声音
Mock.mock(basepath + "/wx/course/addChapterContentForVoice", 'post', {
  "success": true,
  "msg": "成功",
  "obj": []
});

//账户余额
Mock.mock(basepath + "/app/money", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    "price": '27777000'
  }
});

//详情页
Mock.mock(basepath + "/order/detail", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    title: '阿尔法级小薇机器人',
    priceMoney: '45600',
    coverUrl: 'http://www.ipb.cc/uploads/allimg/c140414/13a449205550-B418.jpg',
    instalments: [{
      id: 1,
      time: '1个月'
    }, {
      id: 2,
      time: '2个月'
    }, {
      id: 3,
      time: '3个月'
    }, {
      id: 5,
      time: '4个月'
    }]
  }

});

//加入购物车
Mock.mock(basepath + "/order/add", 'post', {
  "success": true,
  "msg": "成功",

});

//应用
Mock.mock(basepath + "/app/application", 'post', {
  "success": true,
  "msg": "成功",
  "data": [{
    id: 1,
    title: '多群发',
    endTime: '1489111810'
  }, {
    id: 2,
    title: '入群通知',
    endTime: '1489111810'
  }, {
    id: 3,
    title: '批量建群',
    endTime: '1489111810'
  }, {
    id: 4,
    title: '特殊消息监控',
    endTime: '1489111810'
  }, {
    id: 5,
    title: '智能应答',
    endTime: '1489111810'
  }]
});

//删除
Mock.mock(basepath + "/app/update", 'post', {
  "success": true,
  "msg": "成功"
});

//订购应用
Mock.mock(basepath + "/web/ad/list", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    total: 300,
    list: [{
      id: 1,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'WDG'
    }, {
      id: 2,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'DXF'
    }, {
      id: 3,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'YDG'
    }, {
      id: 4,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'WDG'
    }, {
      id: 5,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'WDG'
    }, {
      id: 6,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'WDG'
    }, {
      id: 7,
      imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
      priceText: '谁删除了我',
      fansnum: '看通讯录里那些人删除了我',
      grayText: '49元 / 月',
      adStatus: 'WDG'
    }]
  }
});
//提交订单
Mock.mock(basepath + "/order/oderadd", 'post', {
  "success": true,
  "msg": "成功",
  "data": [{
    imgUrl: 'http://img.zcool.cn/community/01561555692f6100000043838e7dad.jpg',
    title: '阿尔法级小薇机器人',
    priceMoney: '235.00',
    number: 1,
    date: '2016-09-10 12:12:23',
    status: 'DFK',
    discount: '0',
  }]

});

//myrobot
Mock.mock(basepath + "/web/myrobot", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    total: 300,
    list: [{
      id: 1,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微1号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'
    }, {
      id: 2,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微2号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'

    }, {
      id: 3,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微3号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'

    }, {
      id: 4,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微4号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'

    }, {
      id: 1,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微1号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'
    }, {
      id: 2,
      imgUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg',
      priceText: '智能小微2号',
      grayText: '2017年3月6日',
      qrcodeUrl: 'http://tupian.enterdesk.com/2014/mxy/10/2/6/3.jpg'
    }]
  }
});
//添加应用     已有列表
Mock.mock(basepath + "/web/addmass/haslist", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    total: 300,
    list: [{ // 添加群弹出框的列表
      id: 1,
      name: '群名称7',
    }, {
      id: 2,
      name: '群名称8',
    }, {
      id: 3,
      name: '群名称9',
    }, {
      id: 4,
      name: '群名称0',
    }]
  }
});
//添加应用
Mock.mock(basepath + "/web/addmass/list", 'post', {
  "success": true,
  "msg": "成功",
  "data": {
    total: 300,
    list: [{ // 添加群弹出框的列表
      name: '群名称1',
    }, {
      name: '群名称2',
    }, {
      name: '群名称3',
    }, {
      name: '群名称4',
    }]
  }
});
//添加群成功请求
Mock.mock(basepath + "/web/addmass/success", 'post', {
  "success": true,
  "msg": "成功"
});

// 老师主页
Mock.mock(basepath + "/wx/course/teacherHome", 'post', {
    "success": true,
    "msg": "100",
    "data": {
      "teacherInfo": {
        "nickName": "贾冕",
        "price": 800,
        "headPic": "http://ojnvf68tp.bkt.clouddn.com/course/images/1c1c0781-8107-4ba2-9eb4-ad57e948bed6.png",
        "tag": '全国家庭教育联盟特聘讲师'
      },
      "list": [{
        "answer": {
          "teacherId": 1,
          "nickName": "贾冕",
          "list": [{
            "duration": 2160,
            "contentType": null,
            "id": 234,
            "createTime": 1489460338000,
            "filepath": "http://static.taggole.com/jqr/mp3/sit4942.mp3",
            "msgId": 3886,
            "askId": 362,
            "updateTime": 1489460338000
          }],
          "headPic": "http://ojnvf68tp.bkt.clouddn.com/course/images/1c1c0781-8107-4ba2-9eb4-ad57e948bed6.png",
          "long": 2160
        },
        "ask": {
          "id": 362,
          "nickName": null,
          "openid": "oa_9ow5L12z8uyA8N40HH6q5IVNs",
          "courseId": 1,
          "createTime": 1489401512000,
          "extends2": null,
          "headPic": null,
          "userId": 308,
          "ask": "赵泽提问3",
          "isAnonymity": true,
          "updateTime": null,
          "isAnswer": true,
          "extends": "1",
          "isCompleted": null,
          "isFree": false
        }
      }],
      "isLecturer": 1,
      "subscribe": 1
    },
    "code": "100"
  })
  // 小讲
Mock.mock(basepath + "/wx/course/smallTel", 'post', {
  "success": true,
  "msg": "100",
  "data": {
    "list": [{
      id: 1,
      title: '7777',
      content: '999999',
      free: 1
    }, {
      id: 2,
      title: '7777',
      content: '999999',
      free: 1
    }]
  },
  "code": "100"
})

// 问题详情
Mock.mock(basepath + "/wx/course/detail", 'post', {
  "success": true,
  "msg": "100",
  "data": {
    "teacherInfo": {
      "nickName": "贾冕",
      "price": 800,
      "headPic": "http://ojnvf68tp.bkt.clouddn.com/course/images/1c1c0781-8107-4ba2-9eb4-ad57e948bed6.png",
      "tag": '全国家庭教育联盟特聘讲师'
    },
    "list": [{
      id: 1,
      title: '7777',
      content: '999999',
      free: 1
    }, {
      id: 2,
      title: '7777',
      content: '999999',
      free: 1
    }],
    "askAnswerList": {
      "answer": {
        "teacherId": 1,
        "nickName": "贾冕",
        "list": [{
          "duration": 2160,
          "contentType": null,
          "id": 234,
          "createTime": 1489460338000,
          "filepath": "http://static.taggole.com/jqr/mp3/sit4942.mp3",
          "msgId": 3886,
          "askId": 362,
          "updateTime": 1489460338000
        }],
        "headPic": "http://ojnvf68tp.bkt.clouddn.com/course/images/1c1c0781-8107-4ba2-9eb4-ad57e948bed6.png",
        "long": 2160
      },
      "ask": {
        "id": 362,
        "nickName": null,
        "openid": "oa_9ow5L12z8uyA8N40HH6q5IVNs",
        "courseId": 1,
        "createTime": 1489401512000,
        "extends2": null,
        "headPic": null,
        "userId": 308,
        "ask": "儿子今年6岁，马上面临择校问题，我们一直犹豫是上公立还是上私立，据说公立学校作业多、给家长也布置很多任务，私立学校相对会好一点，但教学没有公立抓得那么紧，以后上个好点的初中可能机会也没有公立学校的多，想听听您的建议，谢谢。",
        "isAnonymity": true,
        "updateTime": null,
        "isAnswer": true,
        "extends": "1",
        "isCompleted": null,
        "isFree": false
      }
    }
  },
  "code": "100"
})