package com.bihang.api.bean;


public class RequestParams {


	private String from;
	//0:邮件  手机 联系人备注
	private int targetType;
	private String areaCode;
	private Amount amount;
	private Long walletId;
	private String notes;
	
	public void setFromEmail(String email) {
		this.from = email;
		this.targetType = 0;
	}
	
	public void setFromPhone(String phone, String areaCode) {
		this.from = phone;
		this.areaCode = areaCode;
		this.targetType = 1;
	}
	
	public void setFromContact(String remark) {
		this.from = remark;
		this.targetType = 2;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getFrom() {
		return from;
	}

	public int getTargetType() {
		return targetType;
	}

	public void check() throws BihangException {
		if(RequestParams.isEmpty(from) || (targetType == 1 && RequestParams.isEmpty(areaCode)) || amount == null) {
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
