package com.bihang.api.bean;

import java.util.List;

public class TransactionsResponse extends Response {

	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
