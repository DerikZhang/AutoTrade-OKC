package com.bihang.api.bean;

public class User {
	private long id;// 数据库表中记录唯一标识id
	private String email;// 邮箱
	private String nikeName;// 昵称
	private String phone;// 手机号
//	private String timeZone;// 时区
//	private String nativeCurrency;// 币种 0：人民币 1：美元

	private Amount btcBalance;//
	private Amount ltcBalance;//

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

//	public String getTimeZone() {
//		return timeZone;
//	}
//
//	public void setTimeZone(String timeZone) {
//		this.timeZone = timeZone;
//	}
//
//	public String getNativeCurrency() {
//		return nativeCurrency;
//	}
//
//	public void setNativeCurrency(String nativeCurrency) {
//		this.nativeCurrency = nativeCurrency;
//	}

	public Amount getBtcBalance() {
		return btcBalance;
	}

	public void setBtcBalance(Amount btcBalance) {
		this.btcBalance = btcBalance;
	}

	public Amount getLtcBalance() {
		return ltcBalance;
	}

	public void setLtcBalance(Amount ltcBalance) {
		this.ltcBalance = ltcBalance;
	}

}
