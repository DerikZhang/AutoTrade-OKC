package com.tdg.ato.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tdg.ato.model.Ticker;
import com.tdg.ato.utils.OkcoinHttp;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DerikZhang on 2017/7/6.
 */
@Service
@Transactional
public class OkcoinApiService {
    OkcoinHttp okcoinHttp = new OkcoinHttp("okcoin.properties");

    @Autowired
    private TickerService tickerService;

    public String getTickerInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("symbol", "ltc_cny");
        HttpEntity response = okcoinHttp.doGet("/api/v1/ticker.do", params);
        try {
            Map maps = (Map) JSON.parse(EntityUtils.toString(response));
            System.out.println("JDT-responseEntity:" + maps.toString());
            Ticker ticker = JSONObject.parseObject(maps.get("ticker").toString(), Ticker.class);
            tickerService.save(ticker);
            return maps.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
