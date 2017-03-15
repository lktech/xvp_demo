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
import com.Rop.api.domain.XvpSku;
import com.Rop.api.request.XvpProductCreateRequest;
import com.Rop.api.request.XvpProductDeleteRequest;
import com.Rop.api.request.XvpProductGetRequest;
import com.Rop.api.request.XvpProductQueryRequest;
import com.Rop.api.request.XvpProductUpdateRequest;
import com.Rop.api.request.XvpSkuAddskuRequest;
import com.Rop.api.request.XvpSkuDeleteskuRequest;
import com.Rop.api.request.XvpSkuGetskuRequest;
import com.Rop.api.request.XvpSkuGetstockRequest;
import com.Rop.api.request.XvpSkuUpdateskuRequest;
import com.Rop.api.response.XvpProductCreateResponse;
import com.Rop.api.response.XvpProductGetResponse;
import com.Rop.api.response.XvpProductQueryResponse;
import com.Rop.api.response.XvpSkuGetskuResponse;
import com.Rop.api.response.XvpSkuGetstockResponse;
import com.Rop.api.response.XvpSkuUpdateskuResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ProductCreateRequest;
import com.lingke.xvp.demo.controller.request.ProductDeleteRequest;
import com.lingke.xvp.demo.controller.request.ProductGetRequest;
import com.lingke.xvp.demo.controller.request.ProductQueryRequest;
import com.lingke.xvp.demo.controller.request.ProductUpdateRequest;
import com.lingke.xvp.demo.controller.request.SkuAddRequest;
import com.lingke.xvp.demo.controller.request.SkuUpdateRequest;
import com.lingke.xvp.demo.controller.response.ProductGetResponse;
import com.lingke.xvp.demo.controller.response.ProductQueryResponse;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.SkuGetResponse;
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
		logger.info("add调用，请求参数：{} ", request);
		//添加商品
		XvpProductCreateRequest ropRequest = new XvpProductCreateRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_name(request.getProduct_name());
		ropRequest.setPics(request.getPics());
		ropRequest.setProduct_detail(request.getProduct_detail());
		ropRequest.setPay_type(request.getPay_type());
		ropRequest.setProduct_desc(request.getProduct_desc());
		// TODO
//		ropRequest.setStore_id(request.getStore_id());
		XvpProductCreateResponse productCreateResponse = ropClientAdapter.ropInvoke(ropRequest);
		// 添加sku
		List<SkuAddRequest> sku_list = request.getSku();
		for (SkuAddRequest skuRequest : sku_list) {
			XvpSkuAddskuRequest ropSkuRequest = new XvpSkuAddskuRequest();
			ropSkuRequest.setApp_id(ropClientAdapter.getAppId());
			ropSkuRequest.setPrice(skuRequest.getPrice());
			ropSkuRequest.setProduct_id(Long.valueOf(productCreateResponse.getProduct().getId()));
			ropSkuRequest.setSku_str(skuRequest.getSku_str());
			ropSkuRequest.setStock(skuRequest.getStock());
//			ropSkuRequest.setLogistics_fee(skuRequest.getLogistics_fee());
			ropClientAdapter.ropInvoke(ropSkuRequest);
		}
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
		logger.info("get调用，请求参数：{} ", request);
		// 根据商品id获取商品信息
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
		// 根据商品的id获取Sku信息
		XvpSkuGetskuRequest ropSkuRequest = new XvpSkuGetskuRequest();
		ropSkuRequest.setApp_id(ropClientAdapter.getAppId());
//		ropSkuRequest.setStore_id(arg0);
		ropSkuRequest.setProduct_id(Long.valueOf(product.getId()));
		XvpSkuGetskuResponse ropSkuResponse = ropClientAdapter.ropInvoke(ropSkuRequest);
		List<SkuGetResponse> sku_list = new ArrayList<>();
		List<XvpSku> xvpskus = ropSkuResponse.getXvpskus();
		for (XvpSku xvpSku : xvpskus) {
			SkuGetResponse skuResponse = new SkuGetResponse();
			skuResponse.setId(Long.valueOf(xvpSku.getId()));
			skuResponse.setPrice(Long.valueOf(xvpSku.getPrice()));
			skuResponse.setStock(Long.valueOf(xvpSku.getStock()));
			skuResponse.setSku_str(xvpSku.getSku_str());
//			skuResponse.setLogistics_fee(Long.valueOf(xvpSku.getLogistics_fee()));
			sku_list.add(skuResponse);
		}
		response.setSku(sku_list);
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
		logger.info("delete调用，请求参数：{} ", request);
		// 删除sku
		XvpSkuGetskuRequest ropSkuGetRequest = new XvpSkuGetskuRequest();
		ropSkuGetRequest.setApp_id(ropClientAdapter.getAppId());
//		ropSkuGetRequest.setStore_id(arg0);
		ropSkuGetRequest.setProduct_id(request.getId());
		XvpSkuGetskuResponse skuResponse = ropClientAdapter.ropInvoke(ropSkuGetRequest);
		List<XvpSku> xvpskus = skuResponse.getXvpskus();
		for (XvpSku xvpSku : xvpskus) {
			XvpSkuDeleteskuRequest ropSkuDeleteRequest = new XvpSkuDeleteskuRequest();
			ropSkuDeleteRequest.setApp_id(ropClientAdapter.getAppId());
			ropSkuDeleteRequest.setSku_id(Long.valueOf(xvpSku.getId()));
			ropClientAdapter.ropInvoke(ropSkuDeleteRequest);
		}
		// 删除商品
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
		logger.info("update调用，请求参数：{} ", request);
		// 修改商品
		XvpProductUpdateRequest ropRequest = new XvpProductUpdateRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		ropRequest.setPics(request.getPics());
		ropRequest.setProduct_detail(request.getProduct_detail());
		ropRequest.setPay_type(request.getPay_type());
		ropRequest.setProduct_desc(request.getProduct_desc());
		// TODO
		// ropRequest.setStore_id(request.getStore_id());
		ropClientAdapter.ropInvoke(ropRequest);
		
		List<SkuUpdateRequest> sku_list = request.getSku();
		for (SkuUpdateRequest skuUpdateRequest : sku_list) {
			// 获取库存
			XvpSkuGetstockRequest ropSkuGetStockRequest = new XvpSkuGetstockRequest();
			ropSkuGetStockRequest.setApp_id(ropClientAdapter.getAppId());
//			ropSkuGetStockRequest.setStore_id(arg0);
			ropSkuGetStockRequest.setProduct_id(request.getId());
			ropSkuGetStockRequest.setSku_id(skuUpdateRequest.getId());
			XvpSkuGetstockResponse ropSkuGetStockResponse = ropClientAdapter.ropInvoke(ropSkuGetStockRequest);
			// 变更SKU信息
			XvpSkuUpdateskuRequest ropSkuUpdateRequest = new XvpSkuUpdateskuRequest();
			ropSkuUpdateRequest.setApp_id(ropClientAdapter.getAppId());
			ropSkuUpdateRequest.setPrice(skuUpdateRequest.getPrice());
//			ropSkuUpdateRequest.setLogistics_fee(skuUpdateRequest.getLogistics_fee());
			ropSkuUpdateRequest.setStock(skuUpdateRequest.getStock());
			ropSkuUpdateRequest.setSku_str(skuUpdateRequest.getSku_str());
			ropSkuUpdateRequest.setOriginal_stock(Long.valueOf(ropSkuGetStockResponse.getStock().getCount()));
			ropClientAdapter.ropInvoke(ropSkuUpdateRequest);
		}
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
		logger.info("query调用，请求参数：{} ", request);
		// 查询商品
		XvpProductQueryRequest ropRequest = new XvpProductQueryRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		// TODO
		// ropRequest.setStore_id(arg0);
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
			XvpSkuGetskuRequest ropSkuGetRequest = new XvpSkuGetskuRequest();
			ropSkuGetRequest.setApp_id(ropClientAdapter.getAppId());
//			ropSkuGetRequest.setStore_id(arg0);
			ropSkuGetRequest.setProduct_id(Long.valueOf(product.getId()));
			// 根据商品的id获取Sku信息
			XvpSkuGetskuResponse ropSkuGetResponse = ropClientAdapter.ropInvoke(ropSkuGetRequest);
			List<SkuGetResponse> sku_list = new ArrayList<>();
			List<XvpSku> xvpskus = ropSkuGetResponse.getXvpskus();
			for (XvpSku xvpSku : xvpskus) {
				SkuGetResponse skuResponse = new SkuGetResponse();
				skuResponse.setId(Long.valueOf(xvpSku.getId()));
				skuResponse.setPrice(Long.valueOf(xvpSku.getPrice()));
				skuResponse.setStock(Long.valueOf(xvpSku.getStock()));
				skuResponse.setSku_str(xvpSku.getSku_str());
//				skuResponse.setLogistics_fee(Long.valueOf(xvpSku.getLogistics_fee()));
				sku_list.add(skuResponse);
			}
			productResponse.setSku(sku_list);
			list.add(productResponse);
		}
		ProductQueryResponse response = new ProductQueryResponse();
		response.setList(list);
		return response;
	}
}
