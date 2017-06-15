package com.lingke.xvp.demo.controller.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.request.XvpPhoneVerifycodeRequest;
import com.Rop.api.request.XvpTlstoreaccountAddstorebankcardRequest;
import com.Rop.api.request.XvpTlstoreaccountCompanycreateRequest;
import com.Rop.api.request.XvpTlstoreaccountGetRequest;
import com.Rop.api.request.XvpTlstoreaccountGetbankcitycodeRequest;
import com.Rop.api.request.XvpTlstoreaccountGetbankinfoRequest;
import com.Rop.api.request.XvpTlstoreaccountGetstoreaccountamountRequest;
import com.Rop.api.request.XvpTlstoreaccountGetstorebankcardRequest;
import com.Rop.api.request.XvpTlstoreaccountPersoncreateRequest;
import com.Rop.api.request.XvpTlstoreaccountQuerywithdrawsRequest;
import com.Rop.api.request.XvpTlstoreaccountUpdatecompanystorebankcardRequest;
import com.Rop.api.request.XvpTlstoreaccountWithdrawalsRequest;
import com.Rop.api.response.XvpPhoneVerifycodeResponse;
import com.Rop.api.response.XvpTlstoreaccountGetResponse;
import com.Rop.api.response.XvpTlstoreaccountGetbankcitycodeResponse;
import com.Rop.api.response.XvpTlstoreaccountGetbankinfoResponse;
import com.Rop.api.response.XvpTlstoreaccountGetstoreaccountamountResponse;
import com.Rop.api.response.XvpTlstoreaccountGetstorebankcardResponse;
import com.Rop.api.response.XvpTlstoreaccountQuerywithdrawsResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.AccountBankCardRequest;
import com.lingke.xvp.demo.controller.request.AccountBankCityGetRequest;
import com.lingke.xvp.demo.controller.request.AccountBankInfoGetRequest;
import com.lingke.xvp.demo.controller.request.AccountCompanyCreateRequest;
import com.lingke.xvp.demo.controller.request.AccountPersonCreateRequest;
import com.lingke.xvp.demo.controller.request.AccountWithDrawsQueryRequest;
import com.lingke.xvp.demo.controller.request.AccountWithDrawsRequest;
import com.lingke.xvp.demo.controller.response.AccontBankCityCodeGetListGetResponse;
import com.lingke.xvp.demo.controller.response.AccontBankInfoListGetResponse;
import com.lingke.xvp.demo.controller.response.AccontWithDrawsQueryListResponse;
import com.lingke.xvp.demo.controller.response.AccontWithDrawsQueryResponse;
import com.lingke.xvp.demo.controller.response.AccountAmountResponse;
import com.lingke.xvp.demo.controller.response.AccountBankCardGetResponse;
import com.lingke.xvp.demo.controller.response.AccountBankInfoResponse;
import com.lingke.xvp.demo.controller.response.AccountCityCodeGetResponse;
import com.lingke.xvp.demo.controller.response.AccountGetResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.exception.DemoException;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Created by yuwb on 2017-03-13. 账户相关业务处理
 */
@RestController("seller_account")
@RequestMapping(value = "/seller/account")
public class AccountController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	private static final String VERIFY_CODE_ERROR = "verify_code_error";

	/**
	 * 查询账户金额信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/getAccountAmount", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getAccountAmount() throws Exception {
		XvpTlstoreaccountGetstoreaccountamountRequest ropRequest = new XvpTlstoreaccountGetstoreaccountamountRequest();
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		XvpTlstoreaccountGetstoreaccountamountResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		AccountAmountResponse response = BeanCopyUtil.copy(ropResponse.getTlstoreaccountamount(),
				AccountAmountResponse.class);
		return response;
	}

	/**
	 * 企业实名认证
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/createCompany", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse createCompany(@RequestBody AccountCompanyCreateRequest request) throws Exception {
		XvpTlstoreaccountCompanycreateRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountCompanycreateRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 个人实名认证
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/createPerson", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse createPerson(@RequestBody AccountPersonCreateRequest request) throws Exception {
		XvpTlstoreaccountPersoncreateRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountPersoncreateRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 查询账户信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse get() throws Exception {
		XvpTlstoreaccountGetRequest ropRequest = new XvpTlstoreaccountGetRequest();
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		XvpTlstoreaccountGetResponse ropResponse = ropClientAdapter.ropInvokeErrorReturnNull(ropRequest);
		if (ropResponse == null) {
			return null;
		}
		AccountGetResponse response = BeanCopyUtil.copy(ropResponse.getTlstoreaccount(), AccountGetResponse.class);
		return response;
	}

	/**
	 * 绑卡
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/addStoreBankCard", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse addStoreBankCard(@RequestBody AccountBankCardRequest request) throws Exception {
		String phone = SessionUtil.getSellerLoginPhone();
		if (!phone.equals(request.getPhone())) {
			throw new RuntimeException("当前登录用户电话号码与绑定银行卡电话号码不一致");
		}
		if (!checkCode(request.getPhone(), request.getSn(), request.getVerfiy_code())) {
			throw new DemoException(VERIFY_CODE_ERROR, "验证码输入错误");
		}
		XvpTlstoreaccountAddstorebankcardRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountAddstorebankcardRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 企业绑卡更新
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/updateCompanyStoreBankCard", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse updateCompanyStoreBankCard(@RequestBody AccountBankCardRequest request) throws Exception {
		String phone = SessionUtil.getSellerLoginPhone();
		if (!phone.equals(request.getPhone())) {
			throw new RuntimeException("当前登录用户电话号码与绑定银行卡电话号码不一致");
		}
		if (!checkCode(request.getPhone(), request.getSn(), request.getVerfiy_code())) {
			throw new DemoException(VERIFY_CODE_ERROR, "验证码输入错误");
		}
		XvpTlstoreaccountUpdatecompanystorebankcardRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountUpdatecompanystorebankcardRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 获取绑卡信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/getStoreBankCard", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getStoreBankCard() throws Exception {
		XvpTlstoreaccountGetstorebankcardRequest ropRequest = new XvpTlstoreaccountGetstorebankcardRequest();
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		XvpTlstoreaccountGetstorebankcardResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		AccountBankCardGetResponse response = BeanCopyUtil.copy(ropResponse.getTlstoreaccountcard(),
				AccountBankCardGetResponse.class);
		String cardNo = "**** **** **** " + response.getCard_no().substring(response.getCard_no().length() - 4);
		response.setCard_no(cardNo);
		return response;
	}

	/**
	 * 获取银行省市信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/getBankCity", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getBankCity(@RequestBody AccountBankCityGetRequest request) throws Exception {
		XvpTlstoreaccountGetbankcitycodeRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountGetbankcitycodeRequest.class);
		XvpTlstoreaccountGetbankcitycodeResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		AccontBankCityCodeGetListGetResponse response = new AccontBankCityCodeGetListGetResponse();
		List<AccountCityCodeGetResponse> list = BeanCopyUtil.copyList(ropResponse.getAreas(),
				AccountCityCodeGetResponse.class);
		if (!CollectionUtils.isEmpty(list)) {
			response.addAll(list);
		}
		return response;
	}

	/**
	 * 查询银行信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getBankInfo(@RequestBody AccountBankInfoGetRequest request) throws Exception {
		XvpTlstoreaccountGetbankinfoRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountGetbankinfoRequest.class);
		XvpTlstoreaccountGetbankinfoResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		List<AccountBankInfoResponse> list = BeanCopyUtil.copyList(ropResponse.getBanks(),
				AccountBankInfoResponse.class);
		AccontBankInfoListGetResponse response = new AccontBankInfoListGetResponse();
		if (!CollectionUtils.isEmpty(list)) {
			response.addAll(list);
		}
		return response;
	}

	/**
	 * 提现
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/withDrawals", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse withdrawals(@RequestBody AccountWithDrawsRequest request) throws Exception {
		XvpTlstoreaccountWithdrawalsRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountWithdrawalsRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 提现记录查询
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/queryWithDraws", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse queryWithDraws(@RequestBody AccountWithDrawsQueryRequest request) throws Exception {
		XvpTlstoreaccountQuerywithdrawsRequest ropRequest = BeanCopyUtil.copy(request,
				XvpTlstoreaccountQuerywithdrawsRequest.class);
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
		XvpTlstoreaccountQuerywithdrawsResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		AccontWithDrawsQueryListResponse response = new AccontWithDrawsQueryListResponse();
		List<AccontWithDrawsQueryResponse> list = BeanCopyUtil.copyList(ropResponse.getTlstoreaccountwithdraws(),
				AccontWithDrawsQueryResponse.class);
		if (!CollectionUtils.isEmpty(list)) {
			response.addAll(list);
		}
		return response;
	}

	/**
	 * 校验验证码是否正确
	 * 
	 * @param request
	 *            用户输入信息
	 * @return
	 * @throws Exception
	 */
	private Boolean checkCode(String phone, String sn, String verify_code) throws Exception {
		XvpPhoneVerifycodeRequest ropRequest = new XvpPhoneVerifycodeRequest();
		ropRequest.setVerify_code(verify_code);
		ropRequest.setPhone(phone);
		ropRequest.setSn(sn);
		XvpPhoneVerifycodeResponse response = ropClientAdapter.ropInvoke(ropRequest);
		return Boolean.valueOf(response.getPhoneresult().getFlag());
	}
}
