package com.lingke.xvp.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.RopResponse;
import com.Rop.api.domain.Area;
import com.Rop.api.request.XvpCommonGetcityareasRequest;
import com.Rop.api.request.XvpCommonGetcountyareasRequest;
import com.Rop.api.request.XvpCommonGetprovinceareasRequest;
import com.Rop.api.request.XvpWxConfiggetRequest;
import com.Rop.api.response.XvpCommonGetcityareasResponse;
import com.Rop.api.response.XvpCommonGetcountyareasResponse;
import com.Rop.api.response.XvpCommonGetprovinceareasResponse;
import com.Rop.api.response.XvpWxConfiggetResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.CommonRequest;
import com.lingke.xvp.demo.controller.request.ShareRequest;
import com.lingke.xvp.demo.controller.response.CommonListResponse;
import com.lingke.xvp.demo.controller.response.CommonResponse;
import com.lingke.xvp.demo.controller.response.ShareResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;

/**
 * Created by yuwb on 2017-03-13. 通用类相关业务处理
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {
	@Autowired
	private XvpRopClient ropClientAdapter;
	@Value("${com.lingke.xvp.rop.key}")
	private String appId;
	/**
	 * 获取省行政区划
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/getprovinceareas", method = RequestMethod.POST)
	public XvpResponse getprovinceareas() throws Exception {
		XvpCommonGetprovinceareasRequest request = new XvpCommonGetprovinceareasRequest();
		XvpCommonGetprovinceareasResponse ropResponse = ropClientAdapter.ropInvoke(request);
		return convertRopRespToXvpResp(ropResponse);
	}

	/**
	 * 获取市行政区划
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/getcityareas", method = RequestMethod.POST)
	public XvpResponse getcityareas(@RequestBody CommonRequest commonRequest) throws Exception {
		XvpCommonGetcityareasRequest request = BeanCopyUtil.copy(commonRequest, XvpCommonGetcityareasRequest.class);
		XvpCommonGetcityareasResponse ropResponse = ropClientAdapter.ropInvoke(request);
		return convertRopRespToXvpResp(ropResponse);
	}

	/**
	 * 获取县区行政区划
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/getcountyareas", method = RequestMethod.POST)
	public XvpResponse getcountyareas(@RequestBody CommonRequest commonRequest) throws Exception {
		XvpCommonGetcountyareasRequest request = BeanCopyUtil.copy(commonRequest, XvpCommonGetcountyareasRequest.class);
		XvpCommonGetcountyareasResponse ropResponse = ropClientAdapter.ropInvoke(request);
		return convertRopRespToXvpResp(ropResponse);
	}

	@RequestMapping(path = "/wxconfig/get", method = RequestMethod.POST)
	public XvpResponse getWxConfig(@RequestBody ShareRequest shareRequest) throws Exception {
		XvpWxConfiggetRequest request = BeanCopyUtil.copy(shareRequest, XvpWxConfiggetRequest.class);
		XvpWxConfiggetResponse response = ropClientAdapter.ropInvoke(request);
		return BeanCopyUtil.copy(response.getJsonobject(), ShareResponse.class);
	}

	/**
	 * 从ropresp转换到ropresp
	 * 
	 * @param ropResponse
	 *            rop返回结果
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private CommonListResponse convertRopRespToXvpResp(RopResponse ropResponse) throws Exception {
		CommonListResponse listResponse = new CommonListResponse();
		List<Area> areas = new ArrayList<>();
		try {
			areas = (List<Area>) ropResponse.getClass().getMethod("getAreas").invoke(ropResponse);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new RuntimeException("获取行政区划出错", e);
		}
		CommonResponse response = null;
		for (Area area : areas) {
			response = BeanCopyUtil.copy(area, CommonResponse.class);
			listResponse.add(response);
		}
		return listResponse;
	}

}
