package com.bihang.api.bean;

public class PageParams {

	private int page = 1;
	private int limit = 25;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		checkLimit(limit);
		this.limit = limit;
	}

	void checkLimit(int limit) {
		if(limit < 0 || limit > 1000) {
			throw new RuntimeException("default limit is 25 and the maximum is 1000");
		}
	}

}
