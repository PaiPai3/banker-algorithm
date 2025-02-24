package com.mxs.banker.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ResourceMatrix {
    @Id
    private Long id;// ID

    private Integer n;// N processes
    private Integer m;// M kinds resources
    private String available;
    private String max;
    private String allocation;
    private String need;
    private Date timeStamp;

    public ResourceMatrix() {
    }

    public ResourceMatrix(Integer n, Integer m) {
        this.n = n;
        this.m = m;
    }

    public ResourceMatrix(Integer n, Integer m, String available, String max, String allocation, String need) {
        this.n = n;
        this.m = m;
        this.available = available;
        this.max = max;
        this.allocation = allocation;
        this.need = need;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAllocation() {
        return allocation;
    }

    public void setAllocation(String allocation) {
        this.allocation = allocation;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date latestUpdateTime) {
        this.timeStamp = latestUpdateTime;
    }
}
