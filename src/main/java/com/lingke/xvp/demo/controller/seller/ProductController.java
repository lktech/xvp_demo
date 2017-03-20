package com.lingke.xvp.demo.controller.seller;

import java.util.List;

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
import com.Rop.api.response.XvpProductCreateResponse;
import com.Rop.api.response.XvpProductGetResponse;
import com.Rop.api.response.XvpProductQueryResponse;
import com.Rop.api.response.XvpSkuGetskuResponse;
import com.lingke.xvp.demo.XvpConstants;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ProductCreateRequest;
import com.lingke.xvp.demo.controller.request.ProductDeleteRequest;
import com.lingke.xvp.demo.controller.request.ProductGetRequest;
import com.lingke.xvp.demo.controller.request.ProductSkuGetRequest;
import com.lingke.xvp.demo.controller.request.ProductUpdateRequest;
import com.lingke.xvp.demo.controller.request.SkuAddRequest;
import com.lingke.xvp.demo.controller.request.SkuUpdateRequest;
import com.lingke.xvp.demo.controller.response.ProductQueryResponse;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.ProductSkuGetListResponse;
import com.lingke.xvp.demo.controller.response.ProductSkuGetResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Created by yuwb on 2017-03-13. 商品相关业务处理
 */
@RestController("seller_product")
@RequestMapping(value = "/seller/product")
public class ProductController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	/**
	 * 新增商品
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse createProduct(@RequestBody ProductCreateRequest request) throws Exception {
		XvpProductCreateRequest ropRequest = BeanCopyUtil.copy(request, XvpProductCreateRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.parseLong(SessionUtil.sellerGetStoreId()));
		ropRequest.setVirtual_flg(0);
		XvpProductCreateResponse productCreateResponse = ropClientAdapter.ropInvoke(ropRequest);

		for (SkuAddRequest skuRequest : request.getSku()) {
			XvpSkuAddskuRequest ropSkuRequest = BeanCopyUtil.copy(skuRequest, XvpSkuAddskuRequest.class);
			// TODO
			ropSkuRequest.setApp_id(ropClientAdapter.getAppId());
			ropSkuRequest.setProduct_id(Long.valueOf(productCreateResponse.getProduct().getId()));
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
	public XvpResponse getProduct(@RequestBody ProductGetRequest request) throws Exception {
		XvpProductGetRequest ropRequest = new XvpProductGetRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		XvpProductGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductResponse response = BeanCopyUtil.copy(ropResponse.getProduct(), ProductResponse.class);
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
	public XvpResponse updateProduct(@RequestBody ProductUpdateRequest request) throws Exception {
		XvpProductUpdateRequest ropRequest = BeanCopyUtil.copy(request, XvpProductUpdateRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.parseLong(SessionUtil.sellerGetStoreId()));
		ropRequest.setVirtual_flg(0);
		ropClientAdapter.ropInvoke(ropRequest);

		List<SkuUpdateRequest> sku_list = request.getSku();
		for (SkuUpdateRequest sku : sku_list) {
			if (sku.getId() != null) {
				XvpSkuDeleteskuRequest ropSkuDelRequest = new XvpSkuDeleteskuRequest();
				ropSkuDelRequest.setApp_id(ropClientAdapter.getAppId());
				ropSkuDelRequest.setSku_id(sku.getId());
				ropClientAdapter.ropInvoke(ropSkuDelRequest);
				sku.setId(null);
			}
			XvpSkuAddskuRequest ropSkuAddRequest = BeanCopyUtil.copy(sku, XvpSkuAddskuRequest.class);
			ropSkuAddRequest.setApp_id(ropClientAdapter.getAppId());
			ropSkuAddRequest.setProduct_id(ropRequest.getProduct_id());
			ropClientAdapter.ropInvoke(ropSkuAddRequest);
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
	public XvpResponse queryProduct() throws Exception {
		XvpProductQueryRequest ropRequest = new XvpProductQueryRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setPage_no(XvpConstants.PAGE_NO);
		ropRequest.setPage_size(XvpConstants.PAGE_SIZE);
		ropRequest.setStore_id(Long.parseLong(SessionUtil.sellerGetStoreId()));
		XvpProductQueryResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductQueryResponse response = new ProductQueryResponse();
		for (Product product : ropResponse.getProducts()) {
			ProductResponse prodcutResponse = BeanCopyUtil.copy(product, ProductResponse.class);
			response.add(prodcutResponse);
		}
		return response;
	}

	/**
	 * 获得商品sku
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/sku/get", method = RequestMethod.POST)
	public XvpResponse getProductSku(@RequestBody ProductSkuGetRequest request) throws Exception {
		XvpSkuGetskuRequest ropRequest = BeanCopyUtil.copy(request, XvpSkuGetskuRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		XvpSkuGetskuResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductSkuGetListResponse response = new ProductSkuGetListResponse();
		for (XvpSku sku : ropResponse.getXvpskus()) {
			ProductSkuGetResponse prodcutResponse = BeanCopyUtil.copy(sku, ProductSkuGetResponse.class);
			response.add(prodcutResponse);
		}
		return response;
	}
}
