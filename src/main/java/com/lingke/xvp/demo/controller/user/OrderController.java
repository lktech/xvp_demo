package com.lingke.xvp.demo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingke.xvp.demo.XvpRopClient;
/**
 * Created by yuwb on 2017-03-13. 订单相关业务处理
 */
@RestController("user_order")
@RequestMapping(value = "/user/store")
public class OrderController {
	@Autowired
	private XvpRopClient ropClientAdapter;
	private  final Logger logger = LoggerFactory
			.getLogger(OrderController.class);


}
