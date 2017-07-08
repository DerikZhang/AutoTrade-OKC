package com.tdg.ato.test;

import com.bihang.api.Bihang;
import com.bihang.api.BihangBuilder;
import com.bihang.api.bean.UserBalance;
import com.tdg.ato.database.DbPoolConnection;
import com.tdg.ato.utils.OkcoinHttp;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by DerikZhang on 2017/6/1.
 */
public class ato2test {
    public static void main(String[] args) throws Exception {
        // testdb(); // test to connect database
        // testhttp();// test http connect
//        testOKCoin();
//        testBihang();
        OkcoinHttp okcHttp = new OkcoinHttp("okcoin.properties");
        HttpEntity httpEntity = okcHttp.doPost("/api/v1/userinfo.do", new HashMap<>());
        System.out.println("entity:" + EntityUtils.toString(httpEntity));
        HttpEntity getEntity = okcHttp.doGet("/api/v1/ticker.do?symbol=ltc_cny", new HashMap<>());
        System.out.println("getEntity:" + EntityUtils.toString(getEntity));
    }

    private static void testBihang() throws Exception {
        String api_key = "074fc4d7390a4092b7b4ab8a59f715af";
        String api_secret = "1b8e0665153e4361a6d2feed50b10c38";
        Bihang bihang = BihangBuilder.getInstance().build(api_key, api_secret);
        UserBalance userBalance = bihang.getUserBalance();
        System.out.println("userBalance:" + userBalance.getTotalBtcBalance().getAmount());

    }

    private static void testOKCoin() throws Exception {
        String url = "https://www.okcoin.cn/api/v1/userinfo.do";
        DbPoolConnection dbcon = new DbPoolConnection();
        Properties prop = dbcon.loadPropertyFile("okcoin.properties");
        Map<String, String> params = new HashMap<String, String>();
        String apiKey = prop.getProperty("apiKey");
        String secretKey = prop.getProperty("secretKey");
        params.put("api_key", apiKey);
        String sign = MD5Util.buildMysignV1(params, secretKey);
        params.put("sign", sign);
        System.out.println("api_key:" + apiKey);
        System.out.println("sign:" + sign);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("api_key", apiKey));
        nvps.add(new BasicNameValuePair("sign", sign));
        post.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(post);
        String entity = EntityUtils.toString(response.getEntity());
        System.out.println("response: \n" + response.toString());
        System.out.println("response.entity: \n" + entity);
    }

    private static void testhttp() throws IOException {
        String url = "http://www.baidu.com";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            System.out.println("output data : \n" + response.toString());
        } finally {
            response.close();
        }
    }

    public static void testdb() {
        try {
            Connection dbcon = new DbPoolConnection().getConnection();
            Statement st = dbcon.createStatement();
            st.execute("select * from test");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                System.out.println("name:" + rs.getString("name") + ":success");
            }
            st.close();
            dbcon.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect database");
        }
    }
}
