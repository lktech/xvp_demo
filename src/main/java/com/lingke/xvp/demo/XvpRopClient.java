package com.lingke.xvp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.Rop.api.ApiException;
import com.Rop.api.DefaultRopClient;
import com.Rop.api.RopClient;
import com.Rop.api.RopRequest;
import com.Rop.api.RopResponse;
import com.lingke.xvp.demo.exception.DemoException;

@Component
public class XvpRopClient implements InitializingBean {
	private final Logger logger = LoggerFactory.getLogger(XvpRopClient.class);
	@Value("${com.lingke.xvp.rop.url}")
	private String ropUrl;
	@Value("${com.lingke.xvp.rop.key}")
	private String appKey;
	@Value("${com.lingke.xvp.rop.secret}")
	private String appSecret;
	private RopClient ropClient;
	private String sessionKey = String.valueOf(System.currentTimeMillis());

	public <T extends RopResponse> T ropInvoke(RopRequest<T> ropRequest)
			throws ApiException {
		logger.info("rop 调用开始，method：{},request：{}", ropRequest
				.getApiMethodName(), ropRequest.getTextParams().toString());
		T resp = null;
		resp = ropClient.execute(ropRequest, sessionKey);
		logger.info("rop 调用结束，response：{}", resp.getBody());
		if (!resp.isSuccess()) {
			if (!StringUtils.isEmpty(resp.getSubCode())) {
				throw new DemoException(resp.getSubCode(),resp.getSubMsg());
			} else {
				throw new DemoException(resp.getErrorCode(),resp.getMsg());
			}
		}
		return resp;
	}
	public <T extends RopResponse> T ropInvokeErrorReturnNull(RopRequest<T> ropRequest)
			throws ApiException {
		logger.info("rop 调用开始，method：{},request：{}", ropRequest
				.getApiMethodName(), ropRequest.getTextParams().toString());
		T resp = null;
		resp = ropClient.execute(ropRequest, sessionKey);
		logger.info("rop 调用结束，response：{}", resp.getBody());
		if (!resp.isSuccess()) {
			return null;
		}
		return resp;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "json");
	}
	
	public String getAppId(){
		return appKey;
	}

}
