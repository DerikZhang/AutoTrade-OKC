package com.bihang.api.bean;

import java.math.BigDecimal;

public class Amount {
	private BigDecimal amount;
	private String currency;

	public Amount() {
	}
	
	public Amount(double amount, CoinType coinType) {
		this.amount = new BigDecimal(String.valueOf(amount));
		this.currency = coinType.name();
	}
	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
