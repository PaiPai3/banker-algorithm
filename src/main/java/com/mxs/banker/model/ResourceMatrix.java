package com.mxs.banker.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "resource_matrix")
public class ResourceMatrix {
    @Id
    private Long id;// ID
    @Column(name = "n", nullable = false)
    private Integer n;// N processes
    @Column(name = "m", nullable = false)
    private Integer m;// M kinds resources
    @Column(name = "available", nullable = false)
    private String available;
    @Column(name = "max", nullable = false)
    private String max;
    @Column(name = "allocation", nullable = false)
    private String allocation;
    @Column(name = "need", nullable = false)
    private String need;
    @Column(name = "time_stamp", nullable = false)
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
