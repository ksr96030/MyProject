package com.santhosh.myproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    @Column(nullable = false)
    private Integer paymentId;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Lob
    private String gatewayResponse;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(nullable = false)
    private double amount;

    private Integer customerId;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_description")
    private String errorDescription;

    // Default Constructor
    public TransactionDetails() {}

    // Parameterized Constructor
    public TransactionDetails(Integer paymentId, String paymentStatus, LocalDateTime paymentTime, String gatewayResponse,
                              String currency, double amount, Integer customerId, String errorCode, String errorDescription) {
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
        this.gatewayResponse = gatewayResponse;
        this.currency = currency;
        this.amount = amount;
        this.customerId = customerId;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    // Getters and Setters
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getGatewayResponse() {
        return gatewayResponse;
    }

    public void setGatewayResponse(String gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
