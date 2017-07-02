package com.bihang.api.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gengchj@foxmail.com
 * @version 2014-11-12 上午11:37:36
 */
public class UserBalance {


	private long id;// 数据库表中记录唯一标识id
	private String email;// 邮箱
	private String nikeName;// 昵称
	private String phone;// 手机号
	
	private Amount totalBtcBalance;// 非冻结bitcoin余额
	private Amount totalLtcBalance;// 非冻结人民币余额
	private Amount totalCnyBalance;// 非冻结bitcoin余额
	private Amount totalUsdBalance;// 非冻结人民币余额
	
	private List<Wallet> walletBalances = new ArrayList<Wallet>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Amount getTotalBtcBalance() {
		return totalBtcBalance;
	}

	public void setTotalBtcBalance(Amount totalBtcBalance) {
		this.totalBtcBalance = totalBtcBalance;
	}

	public Amount getTotalLtcBalance() {
		return totalLtcBalance;
	}

	public void setTotalLtcBalance(Amount totalLtcBalance) {
		this.totalLtcBalance = totalLtcBalance;
	}

	public Amount getTotalCnyBalance() {
		return totalCnyBalance;
	}

	public void setTotalCnyBalance(Amount totalCnyBalance) {
		this.totalCnyBalance = totalCnyBalance;
	}

	public Amount getTotalUsdBalance() {
		return totalUsdBalance;
	}

	public void setTotalUsdBalance(Amount totalUsdBalance) {
		this.totalUsdBalance = totalUsdBalance;
	}

	public List<Wallet> getWalletBalances() {
		return walletBalances;
	}

	public void setWalletBalances(List<Wallet> walletBalances) {
		this.walletBalances = walletBalances;
	}

}
