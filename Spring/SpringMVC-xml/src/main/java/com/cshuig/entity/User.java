package com.cshuig.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/9/15.
 */
public class User implements Serializable {
    private Long id;
    private String name;

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
