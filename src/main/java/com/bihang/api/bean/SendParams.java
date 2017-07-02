package com.bihang.api.bean;


public class SendParams {

	private String to;
	//0:邮件 1地址 2手机3 联系人备注
	private int targetType;
	private String areaCode;
	private Amount amount;
	private boolean offchain = true;
	private Long walletId;
	private String notes;
	
	public void setToEmail(String email) {
		this.to = email;
		this.targetType = 0;
	}
	
	public void setToCoinAddress(String address) {
		this.to = address;
		this.targetType = 1;
	}

	public void setToPhone(String phone, String areaCode) {
		this.to = phone;
		this.areaCode = areaCode;
		this.targetType = 2;
	}
	
	public void setToContact(String remark) {
		this.to = remark;
		this.targetType = 3;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public boolean isOffchain() {
		return offchain;
	}
	public void setOffchain(boolean offchain) {
		this.offchain = offchain;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public String getTo() {
		return to;
	}

	public int getTargetType() {
		return targetType;
	}

	public void check() throws BihangException {
		if(SendParams.isEmpty(to) || (targetType == 2 && SendParams.isEmpty(areaCode)) || amount == null) {
			throw new BihangException("params missing!!!");
		}
		if(amount == null || (!"BTC".equals(amount.getCurrency()) && !"LTC".equals(amount.getCurrency())
				&& !"CNY".equals(amount.getCurrency()) && !"USD".equals(amount.getCurrency()))){
			throw new BihangException("invalid amount");
		}
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
