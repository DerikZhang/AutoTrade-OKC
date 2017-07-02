package com.bihang.api.bean;

public class AddressesParams extends PageParams {
	
	private String query;
	/**
	 * 货币类型type: 0:btc 1:ltc'
	 */
	private int type;
	private long walletId;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
