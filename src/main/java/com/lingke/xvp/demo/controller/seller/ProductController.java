package com.lingke.xvp.demo.controller.seller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.domain.Product;
import com.Rop.api.request.XvpProductCreateRequest;
import com.Rop.api.request.XvpProductDeleteRequest;
import com.Rop.api.request.XvpProductGetRequest;
import com.Rop.api.request.XvpProductQueryRequest;
import com.Rop.api.request.XvpProductUpdateRequest;
import com.Rop.api.response.XvpProductGetResponse;
import com.Rop.api.response.XvpProductQueryResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ProductCreateRequest;
import com.lingke.xvp.demo.controller.request.ProductDeleteRequest;
import com.lingke.xvp.demo.controller.request.ProductGetRequest;
import com.lingke.xvp.demo.controller.request.ProductQueryRequest;
import com.lingke.xvp.demo.controller.request.ProductUpdateRequest;
import com.lingke.xvp.demo.controller.response.ProductGetResponse;
import com.lingke.xvp.demo.controller.response.ProductQueryResponse;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;

import tech.nodex.tutils2.jackson.JsonUtils;

/**
 * Created by yuwb on 2017-03-13. 商品相关业务处理
 */
@RestController("seller_product")
@RequestMapping(value = "/seller/product")
public class ProductController {
	@Autowired
	private XvpRopClient ropClientAdapter;
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * 新增商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse createProduct(@RequestBody ProductCreateRequest request) throws ApiException {
		XvpProductCreateRequest ropRequest = new XvpProductCreateRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_name(request.getProduct_name());
		ropRequest.setPics(request.getPics());
		ropRequest.setProduct_detail(request.getProduct_detail());
		ropRequest.setPay_type(request.getPay_type());
		ropRequest.setProduct_desc(request.getProduct_desc());
		// TODO
		// ropRequest.setStore_id(request.getStore_id());
		// ropRequest.setVirtual_flg(request.getVirtual_flg());
		// ropRequest.setProduct_no(request.getProduct_no());
		// ropRequest.setXvp_point_id(request.getXvp_point_id());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 获取商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getProduct(@RequestBody ProductGetRequest request) throws ApiException {
		XvpProductGetRequest ropRequest = new XvpProductGetRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		XvpProductGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		Product product = ropResponse.getProduct();
		ProductGetResponse response = new ProductGetResponse();
		response.setId(Long.valueOf(product.getId()));
		response.setProduct_name(product.getName());
		response.setPics(product.getPics());
		response.setProduct_detail(product.getProduct_detail());
		response.setPay_type(Integer.valueOf(product.getPay_type()));
		response.setProduct_desc(product.getProduct_desc());
		// TODO
		// response.setLogistics_fee(logistics_fee);
		// response.setSku(sku);
		return response;
	}

	/**
	 * 删除商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse deleteProduct(@RequestBody ProductDeleteRequest request) throws ApiException {
		XvpProductDeleteRequest ropRequest = new XvpProductDeleteRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 修改商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse updateProduct(@RequestBody ProductUpdateRequest request) throws ApiException {
		XvpProductUpdateRequest ropRequest = new XvpProductUpdateRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		ropRequest.setPics(request.getPics());
		ropRequest.setProduct_detail(request.getProduct_detail());
		ropRequest.setPay_type(request.getPay_type());
		ropRequest.setProduct_desc(request.getProduct_desc());
		// TODO
		// ropRequest.setStore_id(request.getStore_id());
		// ropRequest.setProduct_name(request.getProduct_name());
		// ropRequest.setVirtual_flg(request.getVirtual_flg());
		// ropRequest.setProduct_no(request.getProduct_no());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 我的仓库查询商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse queryProduct(@RequestBody ProductQueryRequest request) throws ApiException {
		XvpProductQueryRequest ropRequest = new XvpProductQueryRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		// TODO
		// ropRequest.setStore_id(arg0);
		// ropRequest.setKeyword(arg0);
		// ropRequest.setPage_no(arg0);
		// ropRequest.setPage_size(arg0);
		XvpProductQueryResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		List<Product> products = ropResponse.getProducts();
		List<ProductResponse> list = new ArrayList<>();
		for (Product product : products) {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setId(Long.valueOf(product.getId()));
			productResponse.setProduct_name(product.getName());
			productResponse.setPics(product.getPics());
			productResponse.setProduct_detail(product.getProduct_detail());
			productResponse.setPay_type(Integer.valueOf(product.getPay_type()));
			productResponse.setProduct_desc(product.getProduct_desc());
			// TODO
			// response.setLogistics_fee(logistics_fee);
			// response.setSku(sku);
			list.add(productResponse);
		}
		ProductQueryResponse response = new ProductQueryResponse();
		response.setList(list);
		return response;
	}
}
