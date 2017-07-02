package com.bihang.api.bean;

import java.io.Serializable;

public class Nation implements Serializable {

	private static final long serialVersionUID = -914197528462924831L;
	
	private long areaCode;// 区域代码
	private String areaName;// 区域名称

	public long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(long areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
