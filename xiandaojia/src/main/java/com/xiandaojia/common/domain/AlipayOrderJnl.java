package com.xiandaojia.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AlipayOrderJnl {
    private Long id;

    private String orderPayId;

    private String tradeNo;

    private String orderNo;

    private BigDecimal totalAmount;

    private BigDecimal receiptAmount;

    private BigDecimal buyerPayAmount;

    private BigDecimal pointAmount;

    private BigDecimal invoiceAmount;

    private Date sendPayDate;

    private Date payTime;

    private String orderStatus;

    private String buyerLogonId;

    private Date timeoutExpress;

    private Date receiveNotifyTime;

    private String tradeStatus;

    private Long version;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(String orderPayId) {
        this.orderPayId = orderPayId == null ? null : orderPayId.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public BigDecimal getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(BigDecimal pointAmount) {
        this.pointAmount = pointAmount;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getSendPayDate() {
        return sendPayDate;
    }

    public void setSendPayDate(Date sendPayDate) {
        this.sendPayDate = sendPayDate;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId == null ? null : buyerLogonId.trim();
    }

    public Date getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(Date timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public Date getReceiveNotifyTime() {
        return receiveNotifyTime;
    }

    public void setReceiveNotifyTime(Date receiveNotifyTime) {
        this.receiveNotifyTime = receiveNotifyTime;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}