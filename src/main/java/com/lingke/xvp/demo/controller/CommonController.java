package com.lingke.xvp.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.RopResponse;
import com.Rop.api.domain.Area;
import com.Rop.api.request.XvpCommonGetcityareasRequest;
import com.Rop.api.request.XvpCommonGetcountyareasRequest;
import com.Rop.api.request.XvpCommonGetprovinceareasRequest;
import com.Rop.api.response.XvpCommonGetcityareasResponse;
import com.Rop.api.response.XvpCommonGetcountyareasResponse;
import com.Rop.api.response.XvpCommonGetprovinceareasResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.CommonRequest;
import com.lingke.xvp.demo.controller.response.CommonListResponse;
import com.lingke.xvp.demo.controller.response.CommonResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;

/**
 * Created by yuwb on 2017-03-13. 通用类相关业务处理
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	/**
	 * 获取省行政区划
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/getprovinceareas", method = RequestMethod.POST)
	public XvpResponse getprovinceareas(@RequestBody CommonRequest commonRequest) throws ApiException {
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
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/getcityareas", method = RequestMethod.POST)
	public XvpResponse getcityareas(@RequestBody CommonRequest commonRequest) throws ApiException {
		XvpCommonGetcityareasRequest request = new XvpCommonGetcityareasRequest();
		BeanUtils.copyProperties(commonRequest, request);
		XvpCommonGetcityareasResponse ropResponse = ropClientAdapter.ropInvoke(request);
		return convertRopRespToXvpResp(ropResponse);
	}

	/**
	 * 获取县区行政区划
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/getcountyareas", method = RequestMethod.POST)
	public XvpResponse getcountyareas(@RequestBody CommonRequest commonRequest) throws ApiException {
		XvpCommonGetcountyareasRequest request = new XvpCommonGetcountyareasRequest();
		BeanUtils.copyProperties(commonRequest, request);
		XvpCommonGetcountyareasResponse ropResponse = ropClientAdapter.ropInvoke(request);
		return convertRopRespToXvpResp(ropResponse);
	}

	/**
	 * 从ropresp转换到ropresp
	 * 
	 * @param ropResponse
	 *            rop返回结果
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private CommonListResponse convertRopRespToXvpResp(RopResponse ropResponse) {
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
			response = new CommonResponse();
			BeanUtils.copyProperties(area, response);
			listResponse.add(response);
		}
		return listResponse;
	}
}
