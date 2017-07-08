package com.tdg.ato.repository;

import com.tdg.ato.model.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DerikZhang on 2017/7/6.
 */
public interface TickerRepository extends JpaRepository<Ticker, Long>{
    Ticker findById(Long id);
}
