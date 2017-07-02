package com.bihang.api;

import java.util.List;

import com.bihang.api.bean.AddressesParams;
import com.bihang.api.bean.AddressesResponse;
import com.bihang.api.bean.Amount;
import com.bihang.api.bean.Application;
import com.bihang.api.bean.ApplicationsResponse;
import com.bihang.api.bean.Button;
import com.bihang.api.bean.ButtonsResponse;
import com.bihang.api.bean.ContactsParams;
import com.bihang.api.bean.ContactsResponse;
import com.bihang.api.bean.Nation;
import com.bihang.api.bean.BihangException;
import com.bihang.api.bean.Order;
import com.bihang.api.bean.OrdersParams;
import com.bihang.api.bean.OrdersResponse;
import com.bihang.api.bean.PageParams;
import com.bihang.api.bean.RequestParams;
import com.bihang.api.bean.SendParams;
import com.bihang.api.bean.Transaction;
import com.bihang.api.bean.TransactionsParams;
import com.bihang.api.bean.TransactionsResponse;
import com.bihang.api.bean.User;
import com.bihang.api.bean.UserBalance;
import com.bihang.api.bean.Wallet;

public interface Bihang {
	
	/**
	 * 获取用户账户关联地址 
	 */
	public AddressesResponse getAddresses() throws BihangException, Exception;
	
	public AddressesResponse getAddresses(AddressesParams addressesParams) throws BihangException, Exception;
	
	/**
	 * 获取用户创建的button
	 */
	public ButtonsResponse getButtons() throws BihangException, Exception;
	
	public ButtonsResponse getButtons(PageParams pageParams) throws BihangException, Exception;
	
	/**
	 * 创建button
	 */
	public Button createButton(Button button) throws BihangException, Exception;
	
	/**
	 * 获取某button关联的order
	 */
	public OrdersResponse getOrders(String buttonId) throws BihangException, Exception;
	
	public OrdersResponse getOrders(String buttonId, PageParams pageParams) throws BihangException, Exception;
	
	/**
	 * 为某button创建order 
	 */
	public Order createOrder(String buttonId) throws BihangException, Exception;
	
	/**
	 * 为某button创建order 
	 */
	public Order createOrder(String buttonId, String custom, String remark) throws BihangException, Exception;
	
	/**
	 * 为某button创建order，添加用户信息
	 */
	public Order createOrder(String buttonId, String custom, String remark, String clientName, String clientAddress,String clientEmail, String clientPhone) throws BihangException, Exception;
	
	/**
	 * 获取联系人
	 */
	public ContactsResponse getContacts() throws BihangException, Exception;
	
	public ContactsResponse getContacts(ContactsParams params) throws BihangException, Exception;
	
	/**
	 * 获取国家编码
	 */
	public List<Nation> getNations() throws BihangException, Exception;
	
	public List<Nation> getNations(String query) throws BihangException, Exception;

	/**
	 * 获取应用信息
	 */
	public Application getApplication(long applicationId) throws BihangException, Exception;
	
	public ApplicationsResponse getApplications(PageParams pageParams) throws BihangException, Exception;
	
	/**
	 * 创建应用
	 */
	public Application createApplication(Application application) throws BihangException, Exception;
	
	/**
	 * 列出当前用户的order列表
	 */
	public OrdersResponse getOrders() throws BihangException, Exception;
	
	public OrdersResponse getOrders(OrdersParams ordersParams) throws BihangException, Exception;
	
	public Order getOrder(long orderId) throws BihangException, Exception;
	
	/**
	 * 创建button并为之创建一个order
	 */
	public Order createOrder(Button button) throws BihangException, Exception;
	
	/**
	 * 创建button并为之创建一个order
	 */
	public Order createOrder(Button button, String custom, String remark) throws BihangException, Exception;
	
	/**
	 * 创建button并为之创建一个order，添加用户信息
	 */
	public Order createOrder(Button button, String custom, String remark, String clientName, String clientAddress, String clientEmail, String clientPhone) throws BihangException, Exception;
	
	public User getUser() throws BihangException, Exception;
	
	public UserBalance getUserBalance() throws BihangException, Exception;
	
	/**
	 * 获取当前用户默认钱包
	 */
	public Wallet getDefaultWallet() throws BihangException, Exception;
	
	/**
	 * 获取用户钱包列表 
	 */
	public List<Wallet> getWallets() throws BihangException, Exception;
	
	public List<Wallet> getWallets(String query) throws BihangException, Exception;
	
	/**
	 * 创建钱包
	 */
	public Wallet createWallet(String name, int currencyType) throws BihangException, Exception;
	
	/**
	 * 删除钱包
	 */
	public Wallet deleteWallet(long walletId) throws BihangException, Exception;
	
	/**
	 * 修改钱包 
	 */
	public Wallet updateWallet(long walletId, String name) throws BihangException, Exception;
	
	/**
	 * 设置默认钱包
	 */
	public Wallet setDefault(long walletId) throws BihangException, Exception;
	
	/**
	 * 钱包间转账
	 */
	public long transferBetweenWallets(long from, long to, double amount) throws BihangException, Exception;
	
	/**
	 * 获取交易详情列表
	 */
	public TransactionsResponse getTransactions() throws BihangException, Exception;
	
	public TransactionsResponse getTransactions(TransactionsParams transactionsParams) throws BihangException, Exception;
	
	/**
	 * 获取某交易信息
	 */
	public Transaction getTransaction(long transactionId) throws BihangException, Exception;

	/**
	 * 付款
	 */
	public Transaction sendMoney(SendParams sendParams) throws BihangException, Exception;

	/**
	 * 收款 
	 */
	public Transaction requestMoney(RequestParams requestParams) throws BihangException, Exception;
	
	/**
	 * 取消收款
	 */
	public Transaction cancelRequest(long transactionId) throws BihangException, Exception;
	
}
