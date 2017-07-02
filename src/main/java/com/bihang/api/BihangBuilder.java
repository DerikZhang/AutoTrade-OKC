package com.bihang.api;

public class BihangBuilder {
	String accessToken;
	String apiKey;
	String apiSecret;
	String host;
	
	private static BihangBuilder instance;
	
	private BihangBuilder() {}
	
	public static synchronized BihangBuilder getInstance() {
		if(instance == null) {
			instance = new BihangBuilder();
		}
		return instance;
	}
	
	public Bihang build(String access_token) {
		this.accessToken = access_token;
		return new BihangImpl(this);
	}
	
	public Bihang build(String api_key, String api_secret) {
		this.apiKey = api_key;
		this.apiSecret = api_secret;
		return new BihangImpl(this);
	}

	public BihangBuilder setHost(String host) {
		this.host = host;
		return this;
	}
}
