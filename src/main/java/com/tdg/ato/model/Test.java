package com.tdg.ato.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by DerikZhang on 2017/6/29.
 */
@Component
public class Test implements Serializable {

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
