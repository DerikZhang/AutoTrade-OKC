package com.tdg.ato.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by DerikZhang on 2017/7/6.
 */
@Entity
@Table(name = "ticker")
public class Ticker implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false, name = "buy")
    float buy;
    @Column(nullable = false, name = "high")
    float high;
    @Column(nullable = false, name = "last")
    float last;
    @Column(nullable = false, name = "low")
    float low;
    @Column(nullable = false, name = "sell")
    float sell;
    @Column(nullable = false, name = "vol")
    float vol;
    @Column(nullable = true,name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Ticker() {
        this.buy = (float) 2.0;
        this.high = (float) 2.0;
        this.last = (float) 2.0;
        this.low = (float) 2.0;
        this.sell = (float) 2.0;
        this.vol = (float) 2.0;
    }

    public Ticker(float buy, float high, float last, float low, float sell, float vol) {
        this.buy = buy;
        this.high = high;
        this.last = last;
        this.low = low;
        this.sell = sell;
        this.vol = vol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

}
