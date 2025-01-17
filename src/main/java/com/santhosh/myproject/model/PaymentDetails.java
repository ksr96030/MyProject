package com.santhosh.myproject.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;

    private Integer customerId;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 10)
    private String cardType;

    @Transient
    private String cvv;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    private double amount;

    @Column(length = 10)
    private String currency;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "created_date")
    private LocalDate createdDate;

    // Default Constructor
    public PaymentDetails() {}

    // Parameterized Constructor
    public PaymentDetails(Integer customerId, String cardHolderName, String cardNumber, String cardType, String cvv,
                          LocalDate expiryDate, String billingAddress, String email, String phoneNumber, double amount,
                          String currency, String paymentMethod, LocalDate createdDate) {
        this.customerId = customerId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.billingAddress = billingAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
