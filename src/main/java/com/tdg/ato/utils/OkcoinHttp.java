package com.tdg.ato.utils;

import com.tdg.ato.database.DbPoolConnection;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.*;

/**
 * Created by DerikZhang on 2017/7/2.
 */
public class OkcoinHttp {

    public static final String API_KEY = "api_key";
    public static final String SECRET_KEY = "secret_key";
//    public static final String SIGN = "sign";
    public static final String BASE_URL = "https://www.okcoin.cn";
    private String apiKey;
    private String secretKey;
//    private String sign;
//    private String accessToken;
    private String propertiesFile;
    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    public OkcoinHttp(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public OkcoinHttp(String propertiesFile) {
        this.propertiesFile = propertiesFile;
        Properties prop = loadPropertyFile(this.propertiesFile);
        this.apiKey = prop.getProperty(API_KEY);
        this.secretKey = prop.getProperty(SECRET_KEY);
    }

    public HttpEntity doGet(String url, Map<String, String> params) {
        url = BASE_URL + url;
        setParams(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String parameters = EntityUtils.toString(new UrlEncodedFormEntity(this.params, Consts.UTF_8));
            HttpGet httpRequest = new HttpGet(url + "?" + parameters);
            CloseableHttpResponse response = httpclient.execute(httpRequest);
            return response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpEntity doPost(String url, Map<String, String> params) {
        url = BASE_URL + url;
        setParams(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpRequest = new HttpPost(url);
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(this.params));
            CloseableHttpResponse response = httpclient.execute(httpRequest);
            return response.getEntity();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setParams(Map<String, String> params) {
        for (String key: params.keySet()) {
            this.params.add(new BasicNameValuePair(key, params.get(key)));
        }
    }

    public Properties loadPropertyFile(String fullFile) {
        String webRootPath = null;
        if (null == fullFile || fullFile.equals(""))
            throw new IllegalArgumentException(
                    "Properties file path can not be null : " + fullFile);
        webRootPath = DbPoolConnection.class.getClassLoader().getResource("").getPath();
//        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath
                    + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: "
                    + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return secretKey;
    }

}
