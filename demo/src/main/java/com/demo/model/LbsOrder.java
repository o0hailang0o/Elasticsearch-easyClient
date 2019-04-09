package com.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class LbsOrder {

    private Long id;

    private Integer orderSn;

    private Integer goodsCount;

    private BigDecimal total;

    private String address;

    private Long lbsAddressId;

    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(Integer orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getLbsAddressId() {
        return lbsAddressId;
    }

    public void setLbsAddressId(Long lbsAddressId) {
        this.lbsAddressId = lbsAddressId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
