package com.bihang.api.bean;

import java.util.List;

public class ContactsResponse extends Response {
	
	private List<Contact> contacts;

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	

}
