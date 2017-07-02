package com.bihang.api.bean;

public class Transaction {
	private long id;
	private String type; // 交易类型
	private String status; // 交易状态
	private int cashFlow; // 资金流向 1:流入 2 流出
	private String recipientAddress = ""; // 用于交易的地址
	private String createdAt; // 创建时间
	private String message = ""; // 交易留言
	private User sender;
	private User recipient;
	private Amount amount;
	
	private String hash;
	private Integer confirmations;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(int cashFlow) {
		this.cashFlow = cashFlow;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Integer getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(Integer confirmations) {
		this.confirmations = confirmations;
	}

}
