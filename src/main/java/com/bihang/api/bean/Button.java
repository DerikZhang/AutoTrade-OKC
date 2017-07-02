package com.bihang.api.bean;



public class Button {

	private String id;

	private long walletId;
	
	private String name = "";
	private int style;
	private String custom = "";

	private String callbackUrl = "";
	private String successUrl = "";
	
	private boolean includeName;
	private boolean includeAddress;
	private boolean includeEmail;
	private boolean includePhone;

	private Amount price;



	public String getId() {
		return id;
	}


	public Button setId(String id) {
		this.id = id;
		return this;
	}


	public long getWalletId() {
		return walletId;
	}


	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}


	public String getName() {
		return name;
	}


	public Button setName(String name) {
		this.name = name;
		return this;
	}


	public int getStyle() {
		return style;
	}


	public Button setStyle(int style) {
		this.style = style;
		return this;
	}


	public String getCustom() {
		return custom;
	}


	public Button setCustom(String custom) {
		this.custom = custom;
		return this;
	}


	public String getCallbackUrl() {
		return callbackUrl;
	}


	public Button setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
		return this;
	}


	public String getSuccessUrl() {
		return successUrl;
	}


	public Button setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
		return this;
	}


	public Amount getPrice() {
		return price;
	}


	public Button setPrice(Amount price) {
		this.price = price;
		return this;
	}


	public boolean isIncludeName() {
		return includeName;
	}


	public Button setIncludeName(boolean includeName) {
		this.includeName = includeName;
		return this;
	}


	public boolean isIncludeAddress() {
		return includeAddress;
	}


	public Button setIncludeAddress(boolean includeAddress) {
		this.includeAddress = includeAddress;
		return this;
	}


	public boolean isIncludeEmail() {
		return includeEmail;
	}


	public Button setIncludeEmail(boolean includeEmail) {
		this.includeEmail = includeEmail;
		return this;
	}


	public boolean isIncludePhone() {
		return includePhone;
	}


	public Button setIncludePhone(boolean includePhone) {
		this.includePhone = includePhone;
		return this;
	}

}
