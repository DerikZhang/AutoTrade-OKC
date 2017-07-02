package com.bihang.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bihang.api.bean.AddressesParams;
import com.bihang.api.bean.AddressesResponse;
import com.bihang.api.bean.Application;
import com.bihang.api.bean.ApplicationResponse;
import com.bihang.api.bean.ApplicationsResponse;
import com.bihang.api.bean.Button;
import com.bihang.api.bean.ButtonResponse;
import com.bihang.api.bean.ButtonsResponse;
import com.bihang.api.bean.ContactsParams;
import com.bihang.api.bean.ContactsResponse;
import com.bihang.api.bean.Nation;
import com.bihang.api.bean.NationsResponse;
import com.bihang.api.bean.BihangException;
import com.bihang.api.bean.Order;
import com.bihang.api.bean.OrderResponse;
import com.bihang.api.bean.OrdersParams;
import com.bihang.api.bean.OrdersResponse;
import com.bihang.api.bean.PageParams;
import com.bihang.api.bean.RequestParams;
import com.bihang.api.bean.Response;
import com.bihang.api.bean.SendParams;
import com.bihang.api.bean.Transaction;
import com.bihang.api.bean.TransactionResponse;
import com.bihang.api.bean.TransactionsParams;
import com.bihang.api.bean.TransactionsResponse;
import com.bihang.api.bean.User;
import com.bihang.api.bean.UserBalance;
import com.bihang.api.bean.UserBalanceResponse;
import com.bihang.api.bean.UserResponse;
import com.bihang.api.bean.Wallet;
import com.bihang.api.bean.WalletResponse;
import com.bihang.api.bean.WalletsResponse;

public class BihangImpl implements Bihang {
	
	public static int limit = 25;
	
	private HttpUtil httpUtil;

	public BihangImpl(BihangBuilder builder) {
		//this.httpUtil = new HttpUtil(builder.apiKey, builder.apiSecret, builder.accessToken);
		this.httpUtil = new HttpUtil(builder.apiKey, builder.apiSecret, builder.accessToken, builder.host);
	}
	

	@Override
	public AddressesResponse getAddresses() throws BihangException, Exception {
		return getAddresses(null);
	}

	@Override
	public AddressesResponse getAddresses(AddressesParams pageParams) throws BihangException, Exception {
		String nationsUrl = "/api/v1/addresses";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
			params.put("query", pageParams.getQuery());
			params.put("type", pageParams.getType());
			params.put("wallet_id", pageParams.getWalletId());
		}
		return handleResponse(httpUtil.doGET(nationsUrl, params), AddressesResponse.class);
	}
	

	@Override
	public ButtonsResponse getButtons() throws BihangException, Exception {
		return this.getButtons(null);
	}


	@Override
	public ButtonsResponse getButtons(PageParams pageParams) throws BihangException, Exception {
		String buttonUrl = "/api/v1/buttons";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(buttonUrl, params), ButtonsResponse.class);
	}
	
//	@Override
//	public Button createButton(Button button) throws BihangException, Exception {
//		return createButton(button, false, false, false, false);
//	}
	
	@Override
	public Button createButton(Button button) throws BihangException, Exception {
		
		String buttonUrl = "/api/v1/buttons";

		if(button == null) {
			throw new BihangException("button can't be null");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", button.getName()); 
		map.put("price", button.getPrice().getAmount());
		map.put("price_currency", button.getPrice().getCurrency());
		
		map.put("style", button.getStyle());
		map.put("wallet_id", button.getWalletId());
		map.put("custom", button.getCustom());
		
		map.put("callback_url", button.getCallbackUrl());
		map.put("success_url", button.getSuccessUrl());
		
		map.put("include_name", button.isIncludeName()+"");
		map.put("include_address", button.isIncludeAddress()+"");
		map.put("include_email", button.isIncludeEmail()+"");
		map.put("include_phone", button.isIncludePhone()+"");
		
		return handleResponse(httpUtil.doPOST(buttonUrl, map), ButtonResponse.class).getButton();
	}
	
	@Override
	public Order createOrder(String buttonId) throws BihangException, Exception {
		return createOrder(buttonId, null, null, null, null, null, null);
	}
	
	@Override
	public Order createOrder(String buttonId, String custom, String remark) throws BihangException, Exception {
		return createOrder(buttonId, custom, remark, null, null, null, null);
	}
	
	@Override
	public Order createOrder(String buttonId, String custom, String remark, String clientName, String clientAddress, 
				String clientEmail, String clientPhone)	throws BihangException, Exception {
		
//		if(buttonId <= 0) {
//			throw new BihangException("Button  with id("+buttonId+") does not exists");
//		}
		
		String createOrderUrl = "/api/v1/buttons/"+ buttonId +"/create_order";
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(!BihangImpl.isEmpty(clientName)) {
			map.put("client_name", clientName);
		}
		if(!BihangImpl.isEmpty(clientAddress)) {
			map.put("client_address", clientAddress);
		}
		if(!BihangImpl.isEmpty(clientEmail)) {
			map.put("client_email", clientEmail);
		}
		if(!BihangImpl.isEmpty(clientPhone)) {
			map.put("client_phone", clientPhone);
		}
		map.put("custom", custom);
		map.put("remark", remark);
		return handleResponse(httpUtil.doPOST(createOrderUrl, map), OrderResponse.class).getOrder();
	}
	
	@Override
	public OrdersResponse getOrders(String buttonId) throws BihangException, Exception {
		return getOrders(buttonId, null);
	}
	
	@Override
	public OrdersResponse getOrders(String buttonId, PageParams pageParams) throws BihangException, Exception {
//		if(buttonId<=0) {
//			throw new BihangException("Button  with id("+buttonId+") does not exists");
//		}
		String getOrderUrl = "/api/v1/buttons/"+buttonId+"/orders";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(getOrderUrl, params), OrdersResponse.class);
	}
	
	@Override
	public List<Nation> getNations() throws BihangException, Exception {
		return getNations(null);
	}

	@Override
	public List<Nation> getNations(String query) throws BihangException, Exception {
		String nationsUrl = "/api/v1/nations";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("query", query);
		return handleResponse(httpUtil.doGET(nationsUrl, params), NationsResponse.class).getNations();
	}
	
	@Override
	public ContactsResponse getContacts() throws BihangException, Exception {
		return getContacts(null);
	}

	@Override
	public ContactsResponse getContacts(ContactsParams contactsParams) throws BihangException, Exception {
		String nationsUrl = "/api/v1/contacts";
		Map<String, Object> params = new HashMap<String, Object>();
		if(contactsParams != null) {
			params.put("page", contactsParams.getPage());
			params.put("limit", contactsParams.getLimit());
			params.put("query", contactsParams.getQuery());
			params.put("type", contactsParams.getType());
		}
		return handleResponse(httpUtil.doGET(nationsUrl, params), ContactsResponse.class);
	}
	
	@Override
	public User getUser() throws BihangException, Exception {
		String usersUrl = "/api/v1/users";
		return handleResponse(httpUtil.doGET(usersUrl, null), UserResponse.class).getUser();
	}
	
	@Override
	public UserBalance getUserBalance() throws BihangException, Exception {
		String usersUrl = "/api/v1/users/balance";
		return handleResponse(httpUtil.doGET(usersUrl, null), UserBalanceResponse.class).getUser();
	}
	
	@Override
	public Application getApplication(long applicationId) throws BihangException, Exception {
		
		if(applicationId<=0) {
			throw new BihangException("Application  with id("+applicationId+") does not exists");
		}
		
		String detailApplicationUrl = "/api/v1/oauth/applications/" + applicationId;
		
		return handleResponse(httpUtil.doGET(detailApplicationUrl, null), ApplicationResponse.class).getApplication();
	}
	
	@Override
	public ApplicationsResponse getApplications(PageParams pageParams) throws BihangException, Exception {
		String oauthUrl = "/api/v1/oauth/applications";
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageParams != null) {
			params.put("page", pageParams.getPage());
			params.put("limit", pageParams.getLimit());
		}
		return handleResponse(httpUtil.doGET(oauthUrl, params), ApplicationsResponse.class);
	}
	
	@Override
	public Application createApplication(Application application) throws BihangException, Exception {
		if(application == null || BihangImpl.isEmpty(application.getName()) 
							|| BihangImpl.isEmpty(application.getRedirectUri()) || !application.getRedirectUri().startsWith("http")) {
			throw new BihangException("name、redirectUri field of application is not empty and redirectUri must start with 'http' ");
		}
		
		String oauthUrl = "/api/v1/oauth/applications";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", application.getName());
		map.put("redirect_uri", application.getRedirectUri());
		return handleResponse(httpUtil.doPOST(oauthUrl, map), ApplicationResponse.class).getApplication();
		
	}
	
	@Override
	public Order getOrder(long orderId) throws BihangException, Exception {
		if(orderId<=0) {
			throw new BihangException("Order  with id("+orderId+") does not exists");
		}
		String orderUrl = "/api/v1/orders/" + orderId;
		return handleResponse(httpUtil.doGET(orderUrl, null), OrderResponse.class).getOrder();
	}
	
	@Override
	public OrdersResponse getOrders() throws BihangException, Exception {
		return getOrders(new OrdersParams());
	}
	
	@Override
	public OrdersResponse getOrders(OrdersParams ordersParams) throws BihangException, Exception {
		String orderUrl = "/api/v1/orders";
		Map<String, Object> params = new HashMap<String, Object>();
		if(ordersParams != null) {
			params.put("page", ordersParams.getPage());
			params.put("limit", ordersParams.getLimit());
			params.put("wallet_id", ordersParams.getWalletId());
		}
		return handleResponse(httpUtil.doGET(orderUrl, params), OrdersResponse.class);
	}
	
	
	@Override
	public Order createOrder(Button button) throws BihangException, Exception {
		return createOrder(button, null, null, null, null, null, null);
	}
	
	@Override
	public Order createOrder(Button button, String custom, String remark) throws BihangException, Exception {
		return createOrder(button, custom, remark, null, null, null, null);
	}
	
	@Override
	public Order createOrder(Button button, String custom, String remark, String clientName,String clientAddress, String clientEmail, String clientPhone)
			throws BihangException, Exception {
		
		if(button == null) {
			throw new BihangException("button can't be null");
		}
		boolean flag = false;
		String message = "[";
		if(button.isIncludeAddress() && BihangImpl.isEmpty(clientAddress)) {
			flag = true;
			message += "clientAddress,";
		}
		if(button.isIncludeEmail() && BihangImpl.isEmpty(clientEmail)) {
			flag = true;
			message += "clientEmail,";
		}
		if(button.isIncludeName() && BihangImpl.isEmpty(clientName)) {
			flag = true;
			message += "clientName,";
		}
		if(button.isIncludePhone() && BihangImpl.isEmpty(clientPhone)) {
			flag = true;
			message += "clientPhone,";
		}
		if(message.endsWith(",")) {
			message = message.substring(0, message.length()-1);
		}
		if(flag) {
			throw new BihangException("Create Order for this button requires user information" + message);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", button.getName()); 
		map.put("price", button.getPrice().getAmount());
		map.put("price_currency", button.getPrice().getCurrency());
		
		map.put("style", button.getStyle());
		map.put("wallet_id", button.getWalletId());
		map.put("custom", button.getCustom());
		
		map.put("callback_url", button.getCallbackUrl());
		map.put("success_url", button.getSuccessUrl());
		
		map.put("include_name", button.isIncludeName()+"");
		map.put("include_address", button.isIncludeAddress()+"");
		map.put("include_email", button.isIncludeEmail()+"");
		map.put("include_phone", button.isIncludePhone()+"");
		
		map.put("custom", custom);
		map.put("remark", remark);
		
		String orderUrl = "/api/v1/orders";
		
		return handleResponse(httpUtil.doPOST(orderUrl, map), OrderResponse.class).getOrder();
	}
	
	@Override
	public Wallet getDefaultWallet() throws BihangException, Exception {
		String walletUrl = "/api/v1/wallets/default";
		return handleResponse(httpUtil.doGET(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public List<Wallet> getWallets() throws BihangException, Exception {
		return getWallets(null);
	}
	
	@Override
	public List<Wallet> getWallets(String query) throws BihangException, Exception {
		String walletUrl = "/api/v1/wallets";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(!BihangImpl.isEmpty(query)) {
			map.put("query", query);
		}
		
		return handleResponse(httpUtil.doGET(walletUrl, map), WalletsResponse.class).getWallets();
	}
	
	@Override
	public Wallet createWallet(String name, int currencyType) throws BihangException, Exception {
		String walletUrl = "/api/v1/wallets";
		
		if(BihangImpl.isEmpty(name)) {
			throw new BihangException("name can't be null");
		}
		String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,10}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
		if(!m.matches()){
			throw new BihangException("名称长度1-10，并且不包含特殊字符");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("currency_type", currencyType);
		
		return handleResponse(httpUtil.doPOST(walletUrl, map), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet setDefault(long walletId) throws BihangException, Exception {

		if(walletId <= 0) {
			throw new BihangException("Wallet  with id("+walletId+") does not exists");
		}
		String walletUrl = "/api/v1/wallets/" + walletId + "/default";
		
		return handleResponse(httpUtil.doPUT(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet updateWallet(long walletId, String name) throws BihangException, Exception {

		if(walletId <= 0) {
			throw new BihangException("Wallet  with id("+walletId+") does not exists");
		}
		if(BihangImpl.isEmpty(name)) {
			throw new BihangException("name can't be null");
		}
		String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,10}$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
		if(!m.matches()){
			throw new BihangException("name length should between 1-10");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		
		String walletUrl = "/api/v1/wallets/" + walletId + "/update";
		
		return handleResponse(httpUtil.doPUT(walletUrl, map), WalletResponse.class).getWallet();
	}
	
	@Override
	public Wallet deleteWallet(long walletId) throws BihangException, Exception {

		if(walletId <= 0) {
			throw new BihangException("Wallet  with id("+walletId+") does not exists");
		}
		
		String walletUrl = "/api/v1/wallets/"+walletId+"/delete";
		
		return handleResponse(httpUtil.doDELETE(walletUrl, null), WalletResponse.class).getWallet();
	}
	
	@Override
	public long transferBetweenWallets(long from, long to, double amount) throws BihangException, Exception {
		
		if (from <= 0 || to <= 0 || amount <= 0) {
			throw new BihangException("Invalid parameters");
		}
		
		String walletUrl = "/api/v1/wallets/transfer";
		
		Map<String, Object> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		map.put("amount", amount);
		
		String text = httpUtil.doPOST(walletUrl, map);
		try {
			JSONObject res = JSON.parseObject(text);
			if (!res.getBooleanValue("success"))
				throw new BihangException(res.getString("error"));
			
			return res.getLongValue("payrecord_id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		}
		
	}


	@Override
	public Transaction getTransaction(long transactionId) throws BihangException, Exception {
		if(transactionId <= 0) {
			throw new BihangException("Transaction  with id("+transactionId+") does not exists");
		}
		String transactionUrl = "/api/v1/transactions/" + transactionId;
		
		return handleResponse(httpUtil.doGET(transactionUrl, null), TransactionResponse.class).getTransaction();
	}
	
	@Override
	public TransactionsResponse getTransactions() throws BihangException,Exception {
		return getTransactions(null);
	}
	
	@Override
	public TransactionsResponse getTransactions(TransactionsParams transactionsParams) throws BihangException,
			Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(transactionsParams != null) {
			map.put("walletId", transactionsParams.getWalletId());
			map.put("page", transactionsParams.getPage());
			map.put("limit", transactionsParams.getLimit());
		}
		
		String transactionUrl = "/api/v1/transactions";
		
		return handleResponse(httpUtil.doGET(transactionUrl, map), TransactionsResponse.class);
	}
	
	@Override
	public Transaction requestMoney(RequestParams requestParams) throws BihangException, Exception {
		
		if(requestParams == null) {
			throw new BihangException("params missing!!!");
		}
		
		requestParams.check();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("from", requestParams.getFrom());
		map.put("target_type", requestParams.getTargetType()); //0email 1phone 2remark 
		map.put("amount", requestParams.getAmount().getAmount());
		int currencyType = 0;
		switch(requestParams.getAmount().getCurrency()) {
		case "LTC":
			currencyType = 1;
			break;
		case "CNY":
			currencyType = 2;
			break;
		case "USD":
			currencyType = 3;
			break;
		}
		map.put("currency_type", currencyType);
		map.put("wallet_id", requestParams.getWalletId());
		if(requestParams.getTargetType() == 1) {
			map.put("area_code", requestParams.getAreaCode());
		}
//		doGET("/api/v1/transactions/simple", map);
//		ApiUtil.doPUT("/api/v1/transactions/request_money", map);
		String transactionUrl = "/api/v1/transactions/request_money";
		return handleResponse(httpUtil.doPUT(transactionUrl, map), TransactionResponse.class).getTransaction();
	}	

	@Override
	public Transaction sendMoney(SendParams sendParams) throws BihangException, Exception {
		
		if(sendParams == null) {
			throw new BihangException("params missing!!!");
		}
		
		sendParams.check();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("to", sendParams.getTo());
		map.put("target_type", sendParams.getTargetType()); //0email 1phone 2remark 
		map.put("amount", sendParams.getAmount().getAmount());
		int currencyType = 0;
		switch(sendParams.getAmount().getCurrency()) {
		case "LTC":
			currencyType = 1;
			break;
		case "CNY":
			currencyType = 2;
			break;
		case "USD":
			currencyType = 3;
			break;
		}
		map.put("currency_type", currencyType);
		if(sendParams.getTargetType() == 1) {
			map.put("area_code", sendParams.getAreaCode());
		}
		String transactionUrl = "/api/v1/transactions/send_money";
		return handleResponse(httpUtil.doPUT(transactionUrl, map), TransactionResponse.class).getTransaction();
		
	}


	@Override
	public Transaction cancelRequest(long transactionId) throws BihangException, Exception {
		
		if(transactionId <= 0) {
			throw new BihangException("Transaction  with id("+transactionId+") does not exists");
		}
		
		String transactionUrl = "/api/v1/transactions/"+transactionId+"/cancel_request";
		
		return handleResponse(httpUtil.doPUT(transactionUrl, null), TransactionResponse.class).getTransaction();
	}


	public <T extends Response> T handleResponse(String text, Class<T> clazz) throws BihangException {
		try {
			//text = StringUtil.changeUnderline2Hump(text);
			
			T response = BihangImpl.parseJSON(text, clazz);
			if(response.getSuccess() != null && "false".equals(response.getSuccess())) {
				throw new BihangException(response.getError());
			}
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		}
	}
	
	private static <T extends Object> T parseJSON(String text, Class<T> clazz) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T o = mapper.readValue(text, clazz);

		return o;
	}
	
	private static boolean isEmpty(String str) {
		if(str == null) 
			return true; 
		String tempStr = str.trim(); 
		if(tempStr.length() == 0)
			return true; 
		if(tempStr.equals("null"))
			return true;
		return false; 
	}

}
