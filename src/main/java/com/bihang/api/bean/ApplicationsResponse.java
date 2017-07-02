package com.bihang.api.bean;

import java.util.List;

public class ApplicationsResponse extends Response {
	
	private List<Application> applications;

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	

}
