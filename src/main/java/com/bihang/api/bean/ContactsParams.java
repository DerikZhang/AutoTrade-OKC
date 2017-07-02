package com.bihang.api.bean;

public class ContactsParams extends PageParams {
	
	private String query;
	/**
	 * type:0:我的联系人页面	1:最近交易联系人
	 */
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
