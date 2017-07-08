package com.tdg.ato.service;

import com.tdg.ato.model.Ticker;
import com.tdg.ato.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by DerikZhang on 2017/7/8.
 */
@Service
@Transactional
public class TickerService {
    @Autowired
    private TickerRepository tickerRepository;

    public Ticker save(Ticker ticker) {
        return tickerRepository.save(ticker);
    }

}
