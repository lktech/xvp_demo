package com.lingke.xvp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    /**
     * 初始化编码连接器
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        loogger.info("#######################  Init CharacterEncodingFilter ... ################################");
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        return filter;
    }
}
