package com.bihang.api.bean;

import java.util.List;

public class AddressesResponse extends Response {
	
	private List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	

}
