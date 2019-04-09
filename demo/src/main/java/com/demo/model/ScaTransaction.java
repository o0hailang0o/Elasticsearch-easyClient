package com.demo.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liujian on 2018/11/24.
 */
public class ScaTransaction {

    private Long id;

    private Long factoryId;

    private String sn;

    private Long drugId;

    private String drugName;

    private Long drugCategoryId;

    private String drugCategory;

    private String spec;

    private Long specId;
    /**
     *药品数量
     */
    private Integer count;

    /**
     * 药品单价
     */
    private BigDecimal price;

    /**
     * 订单原始省信息
     */
    private String ProvinceName;

    private Long provinceCode;
    /**
     * 订单原始市信息
     */
    private String cityName;

    private Long cityCode;
    /**
     * 订单原始区信息
     */
    private String districtName;

    private Long districtCode;
    /**
     *终端名称
     */
    private String clientName;
    /**
     *终端地址
     */
    private String clientAddress;
    /**
     * 终端大类型
     */
    private Integer clientType;

    /**
     * 终端子类型
     */
    private Integer clientSubType;
    /**
     * 第三级子类型
     */
    private Integer clientThirdType;
    /**
     *商业公司
     */
    private String tradingCompany;

    /**
     *
     */
    private Long tradingCompanyId;

    /**
     * 实际的生产厂商
     */
    private String manufacturer;
    /**
     *是未匹配，其他值是匹配sid
     */
    private Long matchClientId;

    /**
     * 订单创建时间
     */
    private Date transactionTime;

    private Date createAt;

    private Date updateAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Long getDrugCategoryId() {
        return drugCategoryId;
    }

    public void setDrugCategoryId(Long drugCategoryId) {
        this.drugCategoryId = drugCategoryId;
    }

    public String getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(String drugCategory) {
        this.drugCategory = drugCategory;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Long districtCode) {
        this.districtCode = districtCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getClientSubType() {
        return clientSubType;
    }

    public void setClientSubType(Integer clientSubType) {
        this.clientSubType = clientSubType;
    }

    public String getTradingCompany() {
        return tradingCompany;
    }

    public void setTradingCompany(String tradingCompany) {
        this.tradingCompany = tradingCompany;
    }

    public Long getTradingCompanyId() {
        return tradingCompanyId;
    }

    public void setTradingCompanyId(Long tradingCompanyId) {
        this.tradingCompanyId = tradingCompanyId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getMatchClientId() {
        return matchClientId;
    }

    public void setMatchClientId(Long matchClientId) {
        this.matchClientId = matchClientId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getClientThirdType() {
        return clientThirdType;
    }

    public void setClientThirdType(Integer clientThirdType) {
        this.clientThirdType = clientThirdType;
    }
}
