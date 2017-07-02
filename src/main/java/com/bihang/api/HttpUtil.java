package com.bihang.api;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bihang.api.bean.BihangException;
import com.bihang.api.util.Coder;

public class HttpUtil {

	
	private String baseUrl;
	private String apiKey;
	private String apiSecret;
	private String accessToken;
	private boolean includeHostUrl;
	
	private static String KEY = "KEY";
	private static String SIGNATURE = "SIGNATURE";
	private static String NONCE = "NONCE";
	
	HttpUtil(String apiKey, String apiSecret,String accessToken, String host) {
		if(host == null || host.length()<=0) {
			this.baseUrl = "https://www.bihang.com";
		} else {
			this.baseUrl = host;
		}
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.accessToken = accessToken;
		this.includeHostUrl = false;
	}
	
	String doGET(String url, Map<String,Object> params) throws Exception {

		String query = "";
		
		if(params != null && params.size()>0) {
			query += "?";
			for(Map.Entry<String,Object> entry : params.entrySet()) {
				query += entry.getKey() + "=" + entry.getValue() + "&";
			}
			query.substring(0, query.length()-1);
		}
		
		HttpRequestBase request;
		
		
		if(this.accessToken != null && this.accessToken.length() > 0) {
			if(query.length()<=0) {
				query += "?access_token=" + this.accessToken;
			} else {
				query += "&access_token=" + this.accessToken;
			}
			
			request = new HttpGet(baseUrl + url + query);
			
		} else {
			request = new HttpGet(baseUrl + url + query);
			
			String nonce = String.valueOf(System.currentTimeMillis()*1000);
			String message = nonce + (includeHostUrl?baseUrl:"") + url;
			String signature = Coder.encryptHMAC(this.apiSecret, message);
			
			request.setHeader(HttpUtil.KEY, this.apiKey);
			request.setHeader(HttpUtil.SIGNATURE, signature);
			request.setHeader(HttpUtil.NONCE, nonce);
		}
		
		HttpClient httpClient = HttpClientBuilder.create().build(); //new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		} finally {
			if (is != null) {
                is.close();
            }
		}
	}
	
	String doDELETE(String url, Map<String,Object> params) throws Exception {

		String query = "";
		
		if(params != null && params.size()>0) {
			query += "?";
			for(Map.Entry<String,Object> entry : params.entrySet()) {
				query += entry.getKey() + "=" + entry.getValue() + "&";
			}
			query.substring(0, query.length()-1);
		}
		
		HttpRequestBase request;
		
		if(this.accessToken != null && this.accessToken.length() > 0) {
			if(query.length()<=0) {
				query += "?access_token=" + this.accessToken;
			} else {
				query += "&access_token=" + this.accessToken;
			}
			
			request = new HttpDelete(baseUrl + url + query);
			
		} else {
			request = new HttpDelete(baseUrl + url + query);
			
			String nonce = String.valueOf(System.currentTimeMillis()*1000);
			String message = nonce + (includeHostUrl?baseUrl:"") + url;
			String signature = Coder.encryptHMAC(this.apiSecret, message);
			
			request.setHeader(HttpUtil.KEY, this.apiKey);
			request.setHeader(HttpUtil.SIGNATURE, signature);
			request.setHeader(HttpUtil.NONCE, nonce);
		}
		
		HttpClient httpClient = HttpClientBuilder.create().build(); //new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		} finally {
			if (is != null) {
                is.close();
            }
		}
	}

	String doPOST(String url, Object requestBody) throws Exception {
		
		HttpRequestBase request;
		ObjectMapper mapper = new ObjectMapper();
		
		String query = "";
		
		if(this.accessToken != null && this.accessToken.length() > 0) {
			query += "?access_token=" + this.accessToken;
			
			HttpPost post = new HttpPost(baseUrl + url + query);
			if(requestBody != null) {
				post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), "utf-8"));
			}
			request = post;
			
		} else {
			HttpPost post = new HttpPost(baseUrl + url + query);
			if(requestBody != null) {
				post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), "utf-8"));
			}
			request = post;
			
			String nonce = String.valueOf(System.currentTimeMillis()*1000);
			String message = nonce + (includeHostUrl?baseUrl:"") + url + (requestBody == null ? "" : mapper.writeValueAsString(requestBody));
			String signature = Coder.encryptHMAC(this.apiSecret, message);
			
			request.setHeader(HttpUtil.KEY, this.apiKey);
			request.setHeader(HttpUtil.SIGNATURE, signature);
			request.setHeader(HttpUtil.NONCE, nonce);
		}
		

		HttpClient httpClient = HttpClientBuilder.create().build();//new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		} finally {
			if (is != null) {
                is.close();
            }
		}
	}
	
	public String doPUT(String url, Object requestBody) throws Exception {
		
		HttpRequestBase request;
		ObjectMapper mapper = new ObjectMapper();
		
		String query = "";
		
		if(this.accessToken != null && this.accessToken.length() > 0) {
			query += "?access_token=" + this.accessToken;
			
			HttpPut post = new HttpPut(baseUrl + url + query);
			if(requestBody != null) {
				post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), "utf-8"));
			}
			request = post;
			
		} else {
			HttpPut post = new HttpPut(baseUrl + url + query);
			if(requestBody != null) {
				post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), "utf-8"));
			}
			request = post;
			
			String nonce = String.valueOf(System.currentTimeMillis()*1000);
			String message = nonce + (includeHostUrl?baseUrl:"") + url + (requestBody == null ? "" : mapper.writeValueAsString(requestBody));
			String signature = Coder.encryptHMAC(this.apiSecret, message);
			
			request.setHeader(HttpUtil.KEY, this.apiKey);
			request.setHeader(HttpUtil.SIGNATURE, signature);
			request.setHeader(HttpUtil.NONCE, nonce);
		}
		

		HttpClient httpClient = HttpClientBuilder.create().build();//new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BihangException(e.getMessage());
		} finally {
			if (is != null) {
                is.close();
            }
		}
	}
}
