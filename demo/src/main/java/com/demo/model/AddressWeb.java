package com.demo.model;

import java.util.Date;

public class AddressWeb {
    //主键id
    private Long AddressId;
    //许可证编号
    private String certNo;
    //企业名称
    private String addressName;
    //注册地址
    private String address;
    //仓库地址
    private String warehouseAddress;
    //法人代表
    private String legalPerson;
    //企业负责人
    private String companyPrincipal;
    //质量负责人
    private String qualityPrincipal;
    //经营方式
    private String operationMode;
    //经营范围
    private String businessScope;
    //发证日期
    private Date issueCertTime;
    //证书有效期
    private Date certPeriodTime;
    //发证机构
    private String certIssueOrg;

    private String certStatus;

    public Long getAddressId() {
        return AddressId;
    }

    public void setAddressId(Long addressId) {
        AddressId = addressId;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getCompanyPrincipal() {
        return companyPrincipal;
    }

    public void setCompanyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal;
    }

    public String getQualityPrincipal() {
        return qualityPrincipal;
    }

    public void setQualityPrincipal(String qualityPrincipal) {
        this.qualityPrincipal = qualityPrincipal;
    }

    public String getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public Date getIssueCertTime() {
        return issueCertTime;
    }

    public void setIssueCertTime(Date issueCertTime) {
        this.issueCertTime = issueCertTime;
    }

    public Date getCertPeriodTime() {
        return certPeriodTime;
    }

    public void setCertPeriodTime(Date certPeriodTime) {
        this.certPeriodTime = certPeriodTime;
    }

    public String getCertIssueOrg() {
        return certIssueOrg;
    }

    public void setCertIssueOrg(String certIssueOrg) {
        this.certIssueOrg = certIssueOrg;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }
}
