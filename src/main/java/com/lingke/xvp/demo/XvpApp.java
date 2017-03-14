package com.lingke.xvp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yuwb on 2017-3-13.
 */
@SpringBootApplication
public class XvpApp {
	private static final Logger loogger = LoggerFactory.getLogger(XvpApp.class);

	public static void main(String[] args) throws Exception {
		loogger.info("#######################  APP Starting ... ################################");
		SpringApplication.run(XvpApp.class, args);
		loogger.info("#######################  APP Success Started . ################################");
	}

}
